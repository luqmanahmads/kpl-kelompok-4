<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Friend extends Model
{
    protected $table = "friends";

    public $incrementing = true;
    public $timestamps = true;

    protected $fillable = [
        'user_id',
        'friend_id',
    ];
}
