<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/16/2017
 * Time: 7:57 PM
 */

require_once __DIR__ . '\..\conn.php';


$channel->queue_bind($chatApplicationMessage, $conversationOutgoing);
echo "waiting\n";

/**
 * @param \PhpAmqpLib\Message\AMQPMessage $msg
 */
$callback = function ($msg) use($channel, $conversationIncoming){
    $body = json_decode($msg->body);
    $routingKey = $body->conversation_id;
    $userId = 1;
    $message = $body->message;

    try {
        echo "masuk\n";
        $client = new \GuzzleHttp\Client();
        $res = $client->request('GET', "localhost/chat-api/api/is-eligible?user_id=$userId&conversation_id=$routingKey");
        echo "process\n";

        $data = [
            'conversation_id' => $routingKey,
            'user_id' => $userId,
            'message' => $message
        ];

        $msgToPublish = new \PhpAmqpLib\Message\AMQPMessage(json_encode($data));

        $channel->basic_publish($msgToPublish, $conversationIncoming, $routingKey);
        $msg->delivery_info["channel"]->basic_ack($msg->delivery_info["delivery_tag"]);
        echo $message, " to ", $routingKey, "\n";
    } catch (\GuzzleHttp\Exception\ClientException $e) {
        echo "not eligible\n";
    } catch (\Exception $e) {
        echo $e->getMessage()."\n";
    }
};

$channel->basic_consume($chatApplicationMessage, '', false, true, false, false, $callback);

while (count($channel->callbacks)) {
    $channel->wait();
}

$channel->close();
$connection->close();