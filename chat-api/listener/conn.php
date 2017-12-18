<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/16/2017
 * Time: 8:46 PM
 */

require_once __DIR__ . '\..\vendor\autoload.php';

use PhpAmqpLib\Connection\AMQPStreamConnection;
use PhpAmqpLib\Message\AMQPMessage;

$connection = new AMQPStreamConnection('35.198.231.167', 5672, 'chat-kpl', 'chat-kpl');
$channel = $connection->channel();

$chatApplicationMessage = 'chat-application-message';
$conversationOutgoing = 'conversation.outgoing';
$conversationIncoming = 'conversation.incoming';