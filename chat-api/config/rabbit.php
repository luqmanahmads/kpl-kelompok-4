<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/17/2017
 * Time: 8:58 PM
 */

return [
    'RABBIT_HOST' => env('RABBIT_HOST'),
    'RABBIT_PORT' => env('RABBIT_PORT'),
    'RABBIT_USERNAME' => env('RABBIT_USERNAME'),
    'RABBIT_PASSWORD' => env('RABBIT_PASSWORD'),
    'SERVER_QUEUE' => 'chat_application_message',
    'CONVERSATION_OUTGOING' => 'conversation.outgoing',
    'CONVERSATION_INCOMING' => 'conversation.incoming',
];