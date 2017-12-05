<?php
/**
 * Created by PhpStorm.
 * User: mreight
 * Date: 12/5/2017
 * Time: 7:24 PM
 */

namespace App\Repositories\Contracts;


interface RepositoryInterface
{
    public function all($columns = ['*']);

    public function paginate($perPage = 15, $columns = ['*']);

    public function create(array $data);

    public function update(array $data, $id);

    public function delete($id);

    public function find($id, $columns = ['*']);

    public function findBy($field, $value, $columns = ['*']);
}