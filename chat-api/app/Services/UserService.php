<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/7/2017
 * Time: 9:14 PM
 */

namespace App\Services;

use App\Repositories\FriendRepository;
use JWTAuth;
use App\Repositories\UserRepository;

class UserService
{
    private $userRepository;
    private $friendRepository;

    public function __construct(UserRepository $userRepository, FriendRepository $friendRepository)
    {
        $this->userRepository = $userRepository;
        $this->friendRepository = $friendRepository;
    }

    public function create($data)
    {
        $data = $this->userRepository->create((array)$data);

        return $data;
    }

    public function getFriends()
    {
        $user = $this->authenticatedUser();

        return $user->friends;
    }

    public function getConversations()
    {
        $user = $this->authenticatedUser();

        return $user->conversation;
    }

    public function addFriend($id)
    {
        $user = $this->authenticatedUser();

        return $this->friendRepository->create(['user_id' => $user->id, 'friend_id' => $id]);
    }

    public function detectString($input)
    {
        if (filter_var($input, FILTER_VALIDATE_EMAIL)) {
            return 'email';
        }

        return 'name';
    }

    public function findUserToAdd($params)
    {
        if (isset($params['email']))
            return $this->findByEmail($params['email']);
        elseif (isset($params['name'])){
            return $this->findByName($params['name']);
        }

        return null;
    }

    public function findByEmail($email)
    {
        $user = $this->userRepository->findBy('email', $email);

        return $user;
    }

    public function findByName($name)
    {
        return $this->userRepository->findBy('name', $name);
    }

    public function authenticatedUser()
    {
        try {
            if (!$user = JWTAuth::parseToken()->authenticate()) {
                return response()->json(['user_not_found'], 404);
            }
        } catch (\Tymon\JWTAuth\Exceptions\TokenExpiredException $e) {
            return response()->json(['token_expired'], $e->getStatusCode());
        } catch (\Tymon\JWTAuth\Exceptions\TokenInvalidException $e) {
            return response()->json(['token_invalid'], $e->getStatusCode());
        } catch (\Tymon\JWTAuth\Exceptions\JWTException $e) {
            return response()->json(['token_absent'], $e->getStatusCode());
        }
        // the token is valid and we have found the user via the sub claim
//        return response()->json(compact('user'));
        return $user;
    }

}