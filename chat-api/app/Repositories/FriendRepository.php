<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/15/2017
 * Time: 9:32 PM
 */

namespace App\Repositories;

use DB;

class FriendRepository extends BaseRepository
{
    function model()
    {
        return 'App\Models\Friend';
    }

    public function getFriendsToAdd($conversationid, $userId)
    {
        $sql = "SELECT id, name, email, phone from users where id in 
                (SELECT friend_id from friends
                where friend_id not in (
                    select user_id
                    from participants
                    where conversation_id = $conversationid)
                and user_id = $userId)";

        $datas = DB::select($sql);

        return $datas;
    }
}