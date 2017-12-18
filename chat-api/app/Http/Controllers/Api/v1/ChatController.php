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
use Illuminate\Http\Request;

class ChatController extends Controller
{
    private $chatService;

    public function __construct()
    {
        $this->chatService = app()->make('chatService');
        $this->userService = app()->make('userService');
    }

    public function getConversationDetail($id)
    {
        $data = $this->chatService->find($id);

        return $this->response->collection(collect([$data]), new ConversationTransformer);
    }

    public function isEligible(Request $request)
    {
        $userId = $request->get('user_id');
        $conversationId = $request->get('conversation_id');
        $data = $this->chatService->isEligible($userId, $conversationId);

        if ($data) {
            return $this->success('found', [], 200);
        }

        return $this->success('not found', [], 404);
    }

    public function getFriendsToAdd($conversationId)
    {
        $user = $this->userService->authenticatedUser();
        $datas = $this->chatService->getFriendsToAdd($conversationId, $user->id);

        return $this->success(
            'success',
            $datas,
            200
        );
    }

    public function addParticipantToChat($conversationId, $friendId)
    {
        $user = $this->userService->authenticatedUser();

        $isEligible = $this->chatService->isEligible($user->id, $conversationId);

        if ($isEligible) {
            $add = $this->chatService->addParticipantToChat($conversationId, $friendId);

            return $this->success(
                'success',
                [],
                201
            );
        }

        return $this->response->errorUnauthorized();
    }

}