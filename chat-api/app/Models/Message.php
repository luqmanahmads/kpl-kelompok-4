<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Message extends Model
{
    use SoftDeletes;

    protected $table = "messages";

    public $incrementing = true;
    public $timestamps = true;

    protected $dates = [
        'deleted_at'
    ];

    protected $fillable = [
        'participant_id',
        'conversation_id',
        'user_id',
        'message_type',
        'message',
        'attachment_url',
    ];

    public function conversation()
    {
        return $this->belongsTo('App\Models\Message');
    }

    public function user()
    {
        return $this->belongsTo('App\Models\User');
    }

    public function participant()
    {
        return $this->belongsTo('App\Models\Participant');
    }
}
