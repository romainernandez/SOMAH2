db_config.php
<?php

/**
 * DB configuration variables
 */

define('DB_SERVER', "localhost"); // db server
define('DB_USER', "root"); // db user
define('DB_PASSWORD', "emDXMYsGXatQyTMD"); // db password
define('DB_DATABASE', "somah2_web"); // database name

function db_connect()
{
  $result = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
  if (!$result)
    return false;
  return $result;
}
?>