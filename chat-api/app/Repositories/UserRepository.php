<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/5/2017
 * Time: 7:32 PM
 */

namespace App\Repositories;


class UserRepository extends BaseRepository
{
    function model()
    {
        return 'App\Models\User';
    }

    public function getFriends()
    {
        return $this->model->friend();
    }

    public function addFriend($user, $id)
    {
        return $user->friends()->attach($id);
    }
}