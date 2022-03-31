package app.db;

import static app.Application.dotenv;

public class MyboxesDB {

    public String initDBSQL(){
        return "CREATE DATABASE IF NOT EXISTS " + dotenv.get("DB_NAME");
    }
}
