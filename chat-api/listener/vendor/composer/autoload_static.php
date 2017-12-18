<?php

// autoload_static.php @generated by Composer

namespace Composer\Autoload;

class ComposerStaticInit59de452689a72dbe2aa46109c815c31e
{
    public static $files = array (
        'e2287167b0f474301790104b608b426f' => __DIR__ . '/..' . '/o2system/gear/src/Helper.php',
        '0f2e6637ed5f7e75263dc020d1d7f216' => __DIR__ . '/..' . '/o2system/kernel/src/Helpers/Common.php',
        'edb327dc8d51f9d36f63df9bbf8ed6a5' => __DIR__ . '/..' . '/o2system/kernel/src/Helpers/Inflector.php',
        '45a493ae863c5b179f8c443acba84260' => __DIR__ . '/..' . '/o2system/kernel/src/Kernel.php',
    );

    public static $prefixLengthsPsr4 = array (
        'P' => 
        array (
            'PhpAmqpLib\\' => 11,
        ),
        'O' => 
        array (
            'O2System\\Spl\\' => 13,
            'O2System\\Psr\\' => 13,
            'O2System\\Kernel\\' => 16,
            'O2System\\Gear\\' => 14,
            'O2System\\Curl\\' => 14,
        ),
    );

    public static $prefixDirsPsr4 = array (
        'PhpAmqpLib\\' => 
        array (
            0 => __DIR__ . '/..' . '/php-amqplib/php-amqplib/PhpAmqpLib',
        ),
        'O2System\\Spl\\' => 
        array (
            0 => __DIR__ . '/..' . '/o2system/spl/src',
        ),
        'O2System\\Psr\\' => 
        array (
            0 => __DIR__ . '/..' . '/o2system/psr/src',
        ),
        'O2System\\Kernel\\' => 
        array (
            0 => __DIR__ . '/..' . '/o2system/kernel/src',
        ),
        'O2System\\Gear\\' => 
        array (
            0 => __DIR__ . '/..' . '/o2system/gear/src',
        ),
        'O2System\\Curl\\' => 
        array (
            0 => __DIR__ . '/..' . '/o2system/curl/src',
        ),
    );

    public static function getInitializer(ClassLoader $loader)
    {
        return \Closure::bind(function () use ($loader) {
            $loader->prefixLengthsPsr4 = ComposerStaticInit59de452689a72dbe2aa46109c815c31e::$prefixLengthsPsr4;
            $loader->prefixDirsPsr4 = ComposerStaticInit59de452689a72dbe2aa46109c815c31e::$prefixDirsPsr4;

        }, null, ClassLoader::class);
    }
}
