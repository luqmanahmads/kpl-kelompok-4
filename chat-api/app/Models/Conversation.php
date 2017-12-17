<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Conversation extends Model
{
    protected $table = "conversation";

    public $timestamps = true;
    public $incrementing = true;

    protected $fillable = [
        'title'
    ];

    public function participant()
    {
//        return $this->belongsToMany('App\Models\Participant', 'participants', 'conversation_id', 'id');
        return $this->belongsToMany('App\Models\User', 'participants');
    }
}
