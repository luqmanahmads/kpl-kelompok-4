<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

//Route::middleware('auth:api')->get('/user', function (Request $request) {
//    return $request->user();
//});

$api = app('Dingo\Api\Routing\Router');

$api->version('v1', function ($api) {

    $api->post('authenticate', 'App\Http\Controllers\Api\v1\AuthenticateController@authenticate');
    $api->post('logout', 'App\Http\Controllers\Api\v1\AuthenticateController@logout');
    $api->get('token', 'App\Http\Controllers\Api\v1\AuthenticateController@getToken');

    $api->post('register', 'App\Http\Controllers\Api\v1\AuthenticateController@register');

});

$api->version('v1', function ($api){

    $api->get('authenticated_user', 'App\Http\Controllers\Api\v1\AuthenticateController@authenticatedUser');


    $api->get('friends', 'App\Http\Controllers\Api\v1\UserController@getFriends');
});
