<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/15/2017
 * Time: 7:38 PM
 */

namespace App\Transformers;


use App\Models\Friend;
use League\Fractal\TransformerAbstract;

class FriendsTransformer extends TransformerAbstract
{
    public function transform(Friend $friend)
    {
        return [
            'name' => $friend->friend->name,
        ];
    }
}