<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/17/2017
 * Time: 8:06 PM
 */

namespace App\Services;

use App\Repositories\ParticipantRepository;
use PhpAmqpLib\Channel\AMQPChannel;
use PhpAmqpLib\Connection\AMQPStreamConnection;
use PhpAmqpLib\Message\AMQPMessage;

class RabbitService
{
    /**
     * The event dispatcher instance.
     *
     * @var \PhpAmqpLib\Connection\AMQPStreamConnection
     */
    private $connection;

    /**
     * The event dispatcher instance.
     *
     * @var AMQPChannel
     */
    private $channel;

    private $chatService;

    public function __construct(ChatService $chatService)
    {
        $this->connect();
        $this->getChannel();
        $this->chatService = $chatService;
    }

    public function connect()
    {
        $this->connection = new AMQPStreamConnection(
            config('rabbit.RABBIT_HOST'),
            config('rabbit.RABBIT_PORT'),
            config('rabbit.RABBIT_USERNAME'),
            config('rabbit.RABBIT_PASSWORD')
        );
    }

    public function getChannel()
    {
        $this->channel = $this->connection->channel();
    }

    public function serverListen()
    {
        $this->channel->queue_bind(config('rabbit.SERVER_QUEUE'), config('rabbit.CONVERSATION_OUTGOING'));
        $this->channel->basic_consume(config('rabbit.SERVER_QUEUE'), '', false, true, false, false, 'processMsg');
    }

    /**
     * @param \PhpAmqpLib\Message\AMQPMessage $msg
     */
    public function processMsg($msg)
    {
        $body = json_decode($msg->body);
        $routingKey = $body->conversation_id;
        $user = $body->user_id;
        $message = $body->message;

        if ($this->chatService->isEligible($user, $routingKey)) {
            $data = [
                'conversation_id' => $routingKey,
                'user_id' => $user,
                'message' => $message
            ];

            $msg = new AMQPMessage(json_encode($data));
        }

        $this->channel->basic_publish($msg, config('rabbit.CONVERSATION_INCOMING'), $routingKey);
    }

    public function __destruct()
    {
        $this->channel->close();
        $this->connection->close();
    }
}