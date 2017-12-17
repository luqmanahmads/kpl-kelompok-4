<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/17/2017
 * Time: 4:04 PM
 */

namespace App\Repositories;


class ConversationRepository extends BaseRepository
{
    function model()
    {
        return 'App\Models\Conversation';
    }

    public function getDetail($id)
    {
        return $this->model->with('participant')->find($id);
    }
}