<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/17/2017
 * Time: 5:56 PM
 */

namespace App\Transformers;


use App\Models\Conversation;
use App\Models\User;
use League\Fractal\TransformerAbstract;

class ConversationTransformer extends TransformerAbstract
{
    public function transform(Conversation $conversation)
    {
        $isGroup = is_null($conversation->title) ? false : true;
        $title = $conversation->title;
        if(!$isGroup) {
            $userService = app()->make('userService');
            $user = $userService->authenticatedUser();
            $participant = $conversation->participant()->where('user_id', '<>', $user->id)->first();
            $title = $participant->name;
        }

        return [
            'id' => $conversation->id,
            'isGroup' => $isGroup,
            'title' => $title,
            'participants' => $conversation->participant
        ];
    }
}