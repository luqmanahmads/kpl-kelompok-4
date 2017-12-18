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
    /**
     *@var \App\Services\ChatService
     */
    private $chatService;

    /**
     *@var \App\Services\UserService
     */
    private $userService;

    /**
     *@var \App\Services\RabbitService
     */
    private $rabbitService;

    public function __construct()
    {
        $this->chatService = app()->make('chatService');
        $this->userService = app()->make('userService');
        $this->rabbitService = app()->make('rabbitService');
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

    public function sendMessage(Request $request)
    {
        $input = $request->only('conversation_id', 'message');

        $user = $this->userService->authenticatedUser();
        $userId = $user->id;

        if ($this->chatService->isEligible($userId, $input['conversation_id'])) {
            $input['user_id'] = $userId;
            $this->rabbitService->publishMessage($input);

        } else {
            return $this->response->errorUnauthorized();
        }
    }
}