<?php

namespace App\Http\Controllers;

use Response;
use Dingo\Api\Routing\Helpers;
use Illuminate\Foundation\Bus\DispatchesJobs;
use Illuminate\Routing\Controller as BaseController;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;

class Controller extends BaseController
{
    use AuthorizesRequests, DispatchesJobs, ValidatesRequests;

    use Helpers;

    public function success($message, $data = [], $code = 200)
    {
        return Response::json([
            'message' => $message,
            'code' => $code,
            'data' => [
                $data
            ]
        ], $code);
    }
}
