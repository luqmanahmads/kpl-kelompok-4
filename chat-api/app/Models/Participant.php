<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Participant extends Model
{
    use SoftDeletes;

    protected $table = "participants";

    public $incrementing = true;
    public $timestamps = true;

    protected $dates = [
        'deleted_at'
    ];

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
