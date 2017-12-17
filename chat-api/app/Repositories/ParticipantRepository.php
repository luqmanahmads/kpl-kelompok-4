<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/17/2017
 * Time: 4:17 PM
 */

namespace App\Repositories;


class ParticipantRepository extends BaseRepository
{
    function model()
    {
        return 'App\Models\Participant';
    }

}