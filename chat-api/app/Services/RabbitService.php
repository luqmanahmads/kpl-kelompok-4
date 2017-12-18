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
    /*
     * @var \PhpAmqpLib\Connection\AMQPStreamConnection
     */
    private $connection;

    /**
     *@var AMQPChannel
     */
    private $channel;

    public function __construct()
    {
        $this->connect();
        $this->getChannel();
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
        $this->channel->basic_consume(config('rabbit.SERVER_QUEUE'), '', false, true, false, false, 'publishMessage');
    }

    public function publishMessage($input)
    {
        $routingKey = $input['conversation_id'];
        $message = $input['message'];
        $userId = $input['user_id'];

        $data = [
            'conversation_id' => $routingKey,
            'user_id' => $userId,
            'message' => $message
        ];

        $msg = new AMQPMessage(json_encode($data));
        $this->channel->basic_publish($msg, config('rabbit.CONVERSATION_INCOMING'), $routingKey);
    }

    /**
     * @param string $name
     * @param string $type
     */
    public function createExchange($name, $type = 'fanout')
    {
        $this->channel->exchange_declare($name, $type, false, true, false);
    }

    public function createExchangeNewUser($id, $type = 'fanout')
    {
        $name = 'user.'.$id;
        $this->createExchange($name, $type);
    }

    public function bindNewParticipant($dst, $src, $routingKey)
    {
        $this->channel->exchange_bind($dst, $src, $routingKey);
    }

    public function __destruct()
    {
        $this->channel->close();
        $this->connection->close();
    }
}