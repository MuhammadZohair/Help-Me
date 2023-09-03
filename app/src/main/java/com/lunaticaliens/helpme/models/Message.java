package com.lunaticaliens.helpme.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable {


    public static final String TABLE_NAME = "message_table";

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_MESSAGE_TEXT = "message_text";

    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_PHOTO = "photo_image";

    public static final String DATE_CREATED = "date_created";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_MESSAGE_TEXT + " VARCHAR,"
                    + COLUMN_USER_ID + " INTEGER,"
                    + COLUMN_PHOTO + " VARCHAR,"
                    + DATE_CREATED + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    private int id;
    private String message;
    private int userID;
    private String photo;
    private Timestamp dateCreated;

    public Message() {
    }

    public Message(int id, String message, int userID, String photo, Timestamp dateCreated) {
        this.id = id;
        this.message = message;
        this.userID = userID;
        this.photo = photo;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", userID=" + userID +
                ", photo='" + photo + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
