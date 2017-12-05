<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Participant extends Model
{
    protected $table = "participants";

    public $incrementing = true;
    public $timestamps = true;

    protected $fillable = [
        'conversation_id',
        'users_id',
    ];

    public function conversation()
    {
        return $this->belongsTo('App\Models\Conversation');
    }

    public function user()
    {
        return $this->belongsTo('App\Models\User');
    }
}
