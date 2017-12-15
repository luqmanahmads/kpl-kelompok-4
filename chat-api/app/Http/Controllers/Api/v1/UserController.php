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
use Dingo\Api\Routing\Helpers;

class UserController extends Controller
{
    use Helpers;

    private $userService;

    public function __construct()
    {
        $this->userService = app()->make('userService');
    }

    public function getFriends()
    {
        $friends = $this->userService->getFriends();

        return $this->response->collection($friends, new FriendsTransformer);
    }
}