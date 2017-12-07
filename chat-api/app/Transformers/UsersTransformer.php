<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/7/2017
 * Time: 8:11 PM
 */

namespace App\Transformers;

use App\Models\User;
use League\Fractal\TransformerAbstract;

class UsersTransformer extends TransformerAbstract
{
    public function transform(User $user)
    {
        return [
            'id' => $user->id,
            'name' => $user->name,
            'phone' => $user->phone,
            'email' => $user->email,
        ];
    }
}