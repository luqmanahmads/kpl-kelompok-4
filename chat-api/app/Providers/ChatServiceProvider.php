<?php

namespace App\Providers;

use App\Services\UserService;
use Illuminate\Support\ServiceProvider;

class ChatServiceProvider extends ServiceProvider
{
    /**
     * Bootstrap the application services.
     *
     * @return void
     */
    public function boot()
    {
        $this->app->bind('userService', 'App\Services\UserService');
        $this->app->bind('chatService', 'App\Services\ChatService');
    }

    /**
     * Register the application services.
     *
     * @return void
     */
    public function register()
    {

    }
}
