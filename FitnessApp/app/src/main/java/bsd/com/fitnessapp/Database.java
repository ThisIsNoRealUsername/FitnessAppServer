package bsd.com.fitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Dominik on 09.01.2017.
 */

public class Database {
    private DatabaseHelper dbhelper;
    private SQLiteDatabase db;
    private final Context context;

    static final String DB_NAME = "FitHuab";
    static final int DB_Version = 1;
    static final String DB_TABLE = "User";
    static final String KEY_ID = "id";
    static final String KEY_USERNAME = "username";
    static final String KEY_EMAIL = "email";
    static final String KEY_PASSWORD = "password";
    static final String KEY_LOGON = "login";
    static final String TABLE_CREATE = "create table " + DB_TABLE + " (" + KEY_ID + " text primary key, "
            + KEY_USERNAME + " text, " + KEY_EMAIL + " text, " + KEY_PASSWORD + " text, " + KEY_LOGON +  " text);";

    public Database(Context context) {
        this.context = context;
        dbhelper = new DatabaseHelper(context, DB_NAME, null, DB_Version);
    }

    public Database open(){
        db = dbhelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbhelper.close();
    }

    public void insertUser(String id, String username, String email, String password, String login){
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID, id);
        cv.put(KEY_USERNAME, username);
        cv.put(KEY_EMAIL, email);
        cv.put(KEY_PASSWORD, password);
        cv.put(KEY_LOGON, login);
        db.insert(DB_TABLE, null, cv);
    }

    /*public boolean deleteSavegame(String name){
        return db.delete(DB_TABLE, KEY_NAME + "=?",new String[]{name}) == 1;
    }*/

    public void updateUser(String id, String username, String email, String password, String login){
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID, id);
        cv.put(KEY_USERNAME, username);
        cv.put(KEY_EMAIL, email);
        cv.put(KEY_PASSWORD, password);
        cv.put(KEY_LOGON, login);
        db.update(DB_TABLE, cv, KEY_ID + "=?", new String[]{id});
    }

    public Cursor selectUsers(){
        Cursor c = db.query(DB_TABLE, new String[] {KEY_ID, KEY_USERNAME, KEY_EMAIL, KEY_PASSWORD, KEY_LOGON}, null, null, null, null, null);
        return c;
    }

    public Cursor selectUser(String id){
        Cursor c = db.query(DB_TABLE, new String[] {KEY_ID, KEY_USERNAME, KEY_EMAIL, KEY_PASSWORD, KEY_LOGON}, KEY_ID + "=?" , new String[]{id}, null, null, null);
        return c;
    }
}
