package com.lunaticaliens.helpme.models;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The type Food ingredient.
 */
public class User implements Serializable {
    /**
     * The constant TABLE_NAME.
     */
    public static final String TABLE_NAME = "user_table";
    /**
     * The constant COLUMN_ID.
     */
    public static final String COLUMN_ID = "id";
    /**
     * The constant COLUMN_USERNAME.
     */
    public static final String COLUMN_USERNAME = "user_name";
    /**
     * The constant COLUMN_EMAIL.
     */
    public static final String COLUMN_EMAIL = "email_id";
    /**
     * The constant COLUMN_PASSWORD.
     */
    public static final String COLUMN_PASSWORD = "password";
    /**
     * The constant COLUMN_PHONE_NUMBER.
     */
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    /**
     * The constant DATE_CREATED.
     */
    public static final String DATE_CREATED = "date_created";


    /**
     * The constant CREATE_TABLE.
     */
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_USERNAME + " VARCHAR,"
                    + COLUMN_EMAIL + " VARCHAR,"
                    + COLUMN_PASSWORD + " VARCHAR,"
                    + COLUMN_PHONE_NUMBER + " BIGINT,"
                    + DATE_CREATED + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    private int id;
    private String userName;
    private String emailAddress;
    private String password;
    private Long phoneNumber;
    private Timestamp dateCreated;

    /**
     * Instantiates a new Food ingredient.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param id           the id
     * @param userName     the user name
     * @param emailAddress the email address
     * @param password     the password
     * @param phoneNumber  the phone number
     * @param dateCreated  the date created
     */
    public User(int id, String userName, String emailAddress, String password, Long phoneNumber, Timestamp dateCreated) {
        this.id = id;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dateCreated = dateCreated;
    }

    /**
     * Instantiates a new Food ingredient.
     *
     * @param userName     the user name
     * @param emailAddress the email address
     * @param password     the password
     * @param phoneNumber  the phone number
     */
    public User( String userName, String emailAddress, String password, Long phoneNumber) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets email address.
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets date created.
     *
     * @return the date created
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets date created.
     *
     * @param dateCreated the date created
     */
    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     *
     * @return string representation of object
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password=" + password +
                ", phoneNumber=" + phoneNumber +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
