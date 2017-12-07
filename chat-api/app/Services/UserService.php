<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/7/2017
 * Time: 9:14 PM
 */

namespace App\Services;


use App\Repositories\UserRepository;

class UserService
{
    private $userRepository;

    public function __construct(UserRepository $userRepository)
    {
        $this->userRepository = $userRepository;
    }

    public function create($data)
    {
        $data = $this->userRepository->create((array)$data);

        return $data;
    }

}