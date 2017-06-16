package com.kirak;

/**
 * Created by Kir on 16.06.2017.
 */
public class Profile {

    //Persistence provider profile
    public static final String DATAJPA = "datajpa";

    public static final String REPOSITORY_IMPLEMENTATION = DATAJPA;

    //DB profile
    public static final String
            MYSQL = "mysql",
            HEROKU = "heroku";

    //Getting DB profile depending of DB driver in classpath
    public static String getActiveDbProfile() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return MYSQL;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getCause().toString());
        }
        return null;
    }
}
