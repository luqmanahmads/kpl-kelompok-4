<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/17/2017
 * Time: 4:02 PM
 */

namespace App\Services;


use App\Repositories\ConversationRepository;
use App\Repositories\ParticipantRepository;

class ChatService
{
    private $conversationRepo;
    private $participantRepo;

    public function __construct(
        ConversationRepository $conversationRepository,
        ParticipantRepository $participantRepository
    ){
        $this->conversationRepo = $conversationRepository;
        $this->participantRepo = $participantRepository;
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

        dd($data);
    }

    public function isEligible($userId, $conversationId)
    {
        return $this->participantRepo->findBy([
            'user_id' => $userId,
            'conversation_id' => $conversationId
        ]);
    }
}