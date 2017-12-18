<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/16/2017
 * Time: 8:45 PM
 */

require_once __DIR__ . '\config.php';

$channel->queue_bind($queue_name, $uniqueUserEx);

echo "waiting message\n";

$callback = function ($msg) use ($channel) {
    $msg->delivery_info["channel"]->basic_ack($msg->delivery_info["delivery_tag"]);
    $now = Date('Y-m-d H:i:s');
    echo $now, " ", $msg->body, "\n";
};

$channel->basic_consume($queue_name, '', false, true, false, false, $callback);

while(count($channel->callbacks)) {
    $channel->wait();
}

$channel->close();
$connection->close();