<html>
    <head>
        <meta charset="utf-8" />
        <title>View all named periods</title>
	    <link href="style.css" rel="stylesheet" />
    </head>

    <body>
        <h1>View every periods and their names in all languages</h1>
        <?php
            header('Content-Type: text/html; charset=utf-8');

            define('DB_SERVER', "localhost"); // db server
            define('DB_USER', "root"); // db user
            define('DB_PASSWORD', "emDXMYsGXatQyTMD"); // db password
            define('DB_DATABASE', "somah2_web"); // database name

            $con = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
            mysqli_set_charset($con, 'utf8');

            // get all periods from period table
            $sql = "SELECT id, image, language_code, name FROM period INNER JOIN period_tr ON period.id = period_tr.period_id WHERE language_code = 'en';";
            $result = mysqli_query($con, $sql) or die(mysqli_error());

            // looping through all results
            // products node
            while ($row = mysqli_fetch_array($result)) {
                ?>
                <p><strong><?php echo htmlspecialchars($row['id']); ?></strong> : </p>
                <p><?php echo '<img src="data:image/jpeg;base64,'.base64_encode($row['image']) .'" />' ?></p>
                <p><strong><?php echo htmlspecialchars($row['language_code']); ?></strong> : <strong><?php echo htmlspecialchars($row['name']); ?></strong></p>
                <?php
            }
        ?>
    </body>
</html>