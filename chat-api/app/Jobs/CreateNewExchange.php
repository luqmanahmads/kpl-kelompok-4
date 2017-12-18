<?php

namespace App\Jobs;

use Illuminate\Bus\Queueable;
use Illuminate\Queue\SerializesModels;
use Illuminate\Queue\InteractsWithQueue;
use Illuminate\Contracts\Queue\ShouldQueue;
use Illuminate\Foundation\Bus\Dispatchable;

class CreateNewExchange implements ShouldQueue
{
    use Dispatchable, InteractsWithQueue, Queueable, SerializesModels;

    /**
     * @var \App\Services\RabbitService
     */
    private $rabbitService;
    /**
     * Create a new job instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->rabbitService = app()->make('rabbitService');
    }

    /**
     * Execute the job.
     *
     * @return void
     */
    public function handle()
    {

    }
}
