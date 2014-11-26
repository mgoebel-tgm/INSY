<?php

return [
    'propel' => [
        'database' => [
            'connections' => [
                'notes' => [
                    'adapter'    => 'mysql',
                    'classname'  => 'Propel\Runtime\Connection\ConnectionWrapper',
                    'dsn'        => 'mysql:host=localhost;dbname=notes',
                    'user'       => 'noteuser',
                    'password'   => 'note',
                    'attributes' => []
                ]
            ]
        ],
        'runtime' => [
            'defaultConnection' => 'notes',
            'connections' => ['notes']
        ],
        'generator' => [
            'defaultConnection' => 'notes',
            'connections' => ['notes']
        ]
    ]
];


