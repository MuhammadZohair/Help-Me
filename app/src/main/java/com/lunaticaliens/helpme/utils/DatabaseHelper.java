package com.lunaticaliens.helpme.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lunaticaliens.helpme.models.Message;
import com.lunaticaliens.helpme.models.User;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * The type Database helper.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "helpMe.db";

    /**
     * Instantiates a new Database helper.
     *
     * @param context the context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.CREATE_TABLE);
        db.execSQL(Message.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Message.TABLE_NAME);

        onCreate(db);
    }

    //********************************* User CRUD Functions **********************************//

    /**
     * Insert user long.
     *
     * @param user the user
     * @return the long
     */
    public Long insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_USERNAME, user.getUserName());
        values.put(User.COLUMN_EMAIL, user.getEmailAddress());
        values.put(User.COLUMN_PASSWORD, user.getPassword());
        values.put(User.COLUMN_PHONE_NUMBER, user.getPhoneNumber());

        long id = db.insert(User.TABLE_NAME, null, values);

        db.close();

        Log.e(TAG, "INSERT USER IN TABLE: " + getUser(id).toString());
        return id;
    }


    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    public User getUser(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID, User.COLUMN_USERNAME, User.COLUMN_EMAIL,
                        User.COLUMN_PASSWORD, User.COLUMN_PHONE_NUMBER, User.DATE_CREATED},
                User.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(
                cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_USERNAME)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)),
                Long.parseLong(cursor.getString(cursor.getColumnIndex(User.COLUMN_PHONE_NUMBER))),
                Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(User.DATE_CREATED))));


        cursor.close();

        Log.e(TAG, "SELECT USER IN TABLE WHERE USER ID: " + user.toString());
        return user;
    }

    /**
     * Gets user.
     *
     * @param email the email
     * @return the user
     */
    public User getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID, User.COLUMN_USERNAME, User.COLUMN_EMAIL,
                        User.COLUMN_PASSWORD, User.COLUMN_PHONE_NUMBER, User.DATE_CREATED},
                User.COLUMN_EMAIL + "=?",
                new String[]{String.valueOf(email)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(
                cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_USERNAME)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)),
                Long.parseLong(cursor.getString(cursor.getColumnIndex(User.COLUMN_PHONE_NUMBER))),
                Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(User.DATE_CREATED))));


        cursor.close();

        Log.e(TAG, "SELECT USER IN TABLE WHERE USER EMAIL: " + user.toString());
        return user;
    }

    /**
     * This method to check user exist or not
     *
     * @param email    the email
     * @param password the password
     * @return true /false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                User.COLUMN_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = User.COLUMN_EMAIL + " = ?" + " AND " + User.COLUMN_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(User.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;

    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userArrayList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + User.TABLE_NAME + " ORDER BY " +
                User.COLUMN_ID + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)));
                user.setUserName(cursor.getString(cursor.getColumnIndex(User.COLUMN_USERNAME)));
                user.setEmailAddress(cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)));
                user.setPhoneNumber(Long.parseLong(cursor.getString(cursor.getColumnIndex(User.COLUMN_PHONE_NUMBER))));
                user.setDateCreated(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(User.DATE_CREATED))));

                userArrayList.add(user);
            } while (cursor.moveToNext());
        }

        db.close();

        Log.e(TAG, "RETURNED ALL USERS");
        return userArrayList;
    }


    /**
     * Update user int.
     *
     * @param user the user
     * @return the int
     */
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_USERNAME, user.getUserName());
        values.put(User.COLUMN_EMAIL, user.getEmailAddress());
        values.put(User.COLUMN_PASSWORD, user.getPassword());
        values.put(User.COLUMN_PHONE_NUMBER, user.getPhoneNumber());
        values.put(User.DATE_CREATED, user.getDateCreated().toString());

        Log.e(TAG, "UPDATE USER WITH USER ID:" + user.toString());
        return db.update(User.TABLE_NAME, values, User.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
    }

    /**
     * Delete user.
     *
     * @param user the user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(User.TABLE_NAME, User.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        Log.e(TAG, "DELETE USER WITH ID: " + user.getId());
        db.close();
    }


    //**************************************************************************************************//

    //********************************* Message Functions **********************************//

    /**
     * Insert message long.
     *
     * @param message the message
     * @return the long
     */
    public Long insertMessage(Message message) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Message.COLUMN_MESSAGE_TEXT, message.getMessage());
        values.put(Message.COLUMN_PHOTO,message.getPhoto());
        values.put(Message.COLUMN_USER_ID, message.getUserID());

        long id = db.insert(Message.TABLE_NAME, null, values);

        db.close();

        Log.e(TAG, "INSERT MESSAGE IN TABLE: " + getMessage(id).toString());
        return id;
    }

    /**
     * Gets message.
     *
     * @param id the id
     * @return the message
     */
    public Message getMessage(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Message.TABLE_NAME,
                new String[]{Message.COLUMN_ID, Message.COLUMN_MESSAGE_TEXT, Message.COLUMN_USER_ID, Message.COLUMN_PHOTO, Message.DATE_CREATED},
                Message.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Message message = new Message(
                cursor.getInt(cursor.getColumnIndex(Message.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Message.COLUMN_MESSAGE_TEXT)),
                Integer.parseInt(cursor.getString(cursor.getColumnIndex(Message.COLUMN_USER_ID))),
                cursor.getString(cursor.getColumnIndex(Message.COLUMN_PHOTO)),
                Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(Message.DATE_CREATED))));

        cursor.close();

        Log.e(TAG, "SELECT MESSAGE IN TABLE WHERE MESSAGE ID: " + message.toString());
        return message;
    }

    /**
     * Gets all messages.
     *
     * @return the all messages
     */
    public ArrayList<Message> getAllMessages() {
        ArrayList<Message> messageArrayList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Message.TABLE_NAME + " ORDER BY " +
                User.COLUMN_ID + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Message message = new Message();
                message.setId(cursor.getInt(cursor.getColumnIndex(Message.COLUMN_ID)));
                message.setMessage(cursor.getString(cursor.getColumnIndex(Message.COLUMN_MESSAGE_TEXT)));
                message.setUserID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Message.COLUMN_USER_ID))));
                message.setPhoto(cursor.getString(cursor.getColumnIndex(Message.COLUMN_PHOTO)));
                message.setDateCreated(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(Message.DATE_CREATED))));

                messageArrayList.add(message);
            } while (cursor.moveToNext());
        }

        db.close();

        Log.e(TAG, "RETURNED ALL USERS");
        return messageArrayList;
    }

}
