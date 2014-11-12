<?php

return [
    'propel' => [
        'database' => [
            'connections' => [
                'notizen' => [
                    'adapter'    => 'mysql',
                    'classname'  => 'Propel\Runtime\Connection\ConnectionWrapper',
                    'dsn'        => 'mysql:host=localhost;dbname=notizen',
                    'user'       => 'notizuser',
                    'password'   => 'notiz',
                    'attributes' => []
                ]
            ]
        ],
        'runtime' => [
            'defaultConnection' => 'notizen',
            'connections' => ['notizen']
        ],
        'generator' => [
            'defaultConnection' => 'notizen',
            'connections' => ['notizen']
        ]
    ]
];


