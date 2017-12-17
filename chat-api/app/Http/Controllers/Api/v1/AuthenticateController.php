<?php

namespace App\Http\Controllers\Api\v1;

use App\Http\Controllers\Controller;
use App\Http\Requests\RegisterRequest;
use App\Transformers\UsersTransformer;
use Dingo\Api\Routing\Helpers;
use JWTAuth;
use Illuminate\Http\Request;
use Tymon\JWTAuth\Exceptions\JWTException;
use App\Services\UserService;

class AuthenticateController extends Controller
{
    use Helpers;

    private $userService;

    public function __construct()
    {
        $this->userService = app()->make('userService');
    }

    public function authenticate(Request $request)
    {
        $credential = $request->only('email', 'password');

        try {
            if (!$token = JWTAuth::attempt($credential)) {
                return response()->json(['error' => 'invalid credential'], 401);
            }
        } catch (JWTException $e) {
            return response()->json(['error' => 'could not create token'], 500);
        }

        return response()->json(compact('token'));
    }

    public function logout(Request $request)
    {
        $this->validate($request, [
            'token' => 'required'
        ]);
        JWTAuth::invalidate($request->input('token'));
    }

    public function authenticatedUser()
    {
        $user = $this->userService->authenticatedUser();

        return $this->response->collection(collect([$user]), new UsersTransformer);
    }

    public function getToken()
    {
        $token = JWTAuth::getToken();
        if (!$token) {
            return $this->response->errorMethodNotAllowed('Token not provided');
        }
        try {
            $refreshedToken = JWTAuth::refresh($token);
        } catch (JWTException $e) {
            return $this->response->errorInternal('Not able to refresh Token');
        }
        return $this->response->withArray(['token' => $refreshedToken]);
    }

    public function register(RegisterRequest $request)
    {
        $data = $this->userService->create($request->all());

        return $this->response->item($data, new UsersTransformer)->setStatusCode(201);
    }

}
