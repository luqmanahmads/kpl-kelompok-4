<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/17/2017
 * Time: 4:02 PM
 */

namespace App\Services;


use App\Repositories\ConversationRepository;
use App\Repositories\FriendRepository;
use App\Repositories\ParticipantRepository;

class ChatService
{
    private $conversationRepo;
    private $participantRepo;
    private $friendRepo;

    private $userService;

    public function __construct(
        ConversationRepository $conversationRepository,
        ParticipantRepository $participantRepository,
        FriendRepository $friendRepository
    ){
        $this->conversationRepo = $conversationRepository;
        $this->participantRepo = $participantRepository;
        $this->friendRepo = $friendRepository;

        $this->userService = app()->make('userService');
    }

    public function createConversation(array $participants)
    {

    }

    public function find($id)
    {
        return $this->conversationRepo->find($id);
    }


    public function getConversationDetail($id)
    {
        $data = $this->conversationRepo->getDetail($id);

        return $data;
    }

    public function isEligible($userId, $conversationId)
    {
        return $this->participantRepo->findBy([
            'user_id' => $userId,
            'conversation_id' => $conversationId
        ]);
    }

    public function getFriendsToAdd($conversationId, $userId)
    {
        $datas = $this->friendRepo->getFriendsToAdd($conversationId, $userId);

        return $datas;
    }

    public function addParticipantToChat($conversationId, $friendId)
    {
        return $this->conversationRepo->addParticipant($conversationId, $friendId);
    }
}