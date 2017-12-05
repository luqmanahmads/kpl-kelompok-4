<?php

namespace Tests\Feature;

use Tests\TestCase;
use Illuminate\Foundation\Testing\WithoutMiddleware;
use Illuminate\Foundation\Testing\DatabaseMigrations;
use Illuminate\Foundation\Testing\DatabaseTransactions;

class UserTest extends TestCase
{
    use DatabaseMigrations;
    /**
     * @test
     *
     * Test: Get /api/authenticate
     */
    public function auth_a_user()
    {
        $user = factory(App\Models\User::class)->create([
            'password' => bcrypt('test')
        ]);

        $this->post('api/authenticate', [
            'email' => $user->email,
            'password' => 'foo'
        ])
            ->seeJsonStructure(['token']);
    }
}
