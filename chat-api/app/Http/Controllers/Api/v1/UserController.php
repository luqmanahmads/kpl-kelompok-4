<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/15/2017
 * Time: 7:41 PM
 */

namespace app\Http\Controllers\Api\v1;


use App\Http\Controllers\Controller;
use App\Transformers\FriendsTransformer;
use App\Transformers\UserFriendsTransformer;
use App\Transformers\UsersTransformer;
use Dingo\Api\Routing\Helpers;

class UserController extends Controller
{
    use Helpers;

    private $userService;

    public function __construct()
    {
        $this->userService = app()->make('userService');
    }

    public function findUserToAdd($query)
    {
        $user = $this->userService->findUserToAdd($query);

        if ($user) {
            return $this->response->item($user, new UsersTransformer);
        }

        return $this->response->errorNotFound();
    }

    public function getFriends()
    {
        $friends = $this->userService->getFriends();

        return $this->response->collection($friends, new FriendsTransformer());
    }

    public function addFriend($id)
    {
        $add = $this->userService->addFriend($id);

        return $this->response->created();
    }
}