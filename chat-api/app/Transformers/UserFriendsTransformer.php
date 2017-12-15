<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/15/2017
 * Time: 9:08 PM
 */

namespace App\Transformers;


use App\Models\User;
use League\Fractal\TransformerAbstract;

class UserFriendsTransformer extends TransformerAbstract
{
    public function transform(User $user)
    {
        $friend = $user;
        dd($friend);
        return [
            'id' => $friend->id,
            'name' => $friend->name
        ];
    }
}