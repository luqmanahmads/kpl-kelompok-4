<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/16/2017
 * Time: 8:43 PM
 */

require_once __DIR__ . '\config.php';

$channel->exchange_declare($uniqueUserEx, $uniqueUserExType, false, true, false);

if (empty($data)) {
    $data = "test message";
}

$message = $argv[1];
$conversationId = $argv[2];

$data = [
    'conversation_id' => $conversationId,
    'message' => $message
];

$msg = new \PhpAmqpLib\Message\AMQPMessage(json_encode($data));

$channel->basic_publish($msg, $conversationOutgoing);

$now = date('Y-m-d H:i:s');
echo "[x] $now send message $message to $conversationId\n";

$channel->close();
$connection->close();