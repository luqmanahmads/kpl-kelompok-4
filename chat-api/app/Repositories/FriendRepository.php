<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/15/2017
 * Time: 9:32 PM
 */

namespace App\Repositories;


class FriendRepository extends BaseRepository
{
    function model()
    {
        return 'App\Models\Friend';
    }
}