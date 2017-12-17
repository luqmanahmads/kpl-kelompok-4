<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/17/2017
 * Time: 5:08 PM
 */

namespace app\Http\Controllers\Api\v1;


use App\Http\Controllers\Controller;
use App\Services\ChatService;
use App\Transformers\ConversationTransformer;

class ChatController extends Controller
{
    private $chatService;

    public function __construct(ChatService $chatService)
    {
        $this->chatService = $chatService;
    }

    public function getConversationDetail($id)
    {
        $data = $this->chatService->find($id);

        return $this->response->collection(collect([$data]), new ConversationTransformer);
    }
}