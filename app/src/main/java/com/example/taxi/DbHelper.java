package com.example.taxi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "usersDB";
    public static final String TABLE_CONTACTS = "contacts";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_PATRONYMIC = "patronymic";
    public static final String KEY_CAR = "car";
    public static final String KEY_VACATION = "vacation";
    public static final String KEY_DRIVER_LIC_NUMBER = "driver_lic_number";
    public static final String KEY_DATE_LICENSE = "date_license";

    public static final String TABLE_CONTACTS_CAR = "contactsCar";
    public static final String KEY_ID_CAR = "_id";
    public static final String KEY_VRS_NUMBER = "vrs_number";
    public static final String KEY_STATE_NUMBER = "state_number";
    public static final String KEY_BRAND = "brand";
    public static final String KEY_YEAR_OF_ISSUE = "year_of_issue";
    public static final String KEY_COLOR = "color";
    public static final String KEY_NUMBER_OF_SEATS = "number_of_seats";
    public static final String KEY_REPAIR = "repair";

    public static final String TABLE_CONTACTS_ORDER = "contactsOrder";
    public static final String KEY_ID_ORDER = "_id";
    public static final String KEY_DEP_ADDRESS = "dep_address";
    public static final String KEY_ARR_ADDRESS = "arr_address";
    public static final String KEY_DATETIME_ARRIVAL = "date_time";
    public static final String KEY_ORDER_NUMBER = "order_number";
    public static final String KEY_ID_CLIENT_ORDER = "id_client";
    public static final String KEY_COMPLITE_MARK = "complite_mark";
    public static final String KEY_ID_DRIVER = "id_driver";
    public static final String KEY_ID_CAR_ORDERS = "id_car_orders";

    public static final String TABLE_CONTACTS_BOOKING = "contactsBooking";
    public static final String KEY_ID_BOOKING = "_id";
    public static final String KEY_DEP_ADDRESS_BOOKING = "dep_address_booking";
    public static final String KEY_ARR_ADDRESS_BOOKING = "arr_address_booking";
    public static final String KEY_DATETIME_ARRIVAL_BOOKING = "date_time_arrival_booking";
    public static final String KEY_DATETIME_COMPLITE_BOOKING = "date_time_complite_booking";
    public static final String KEY_BOOKING_NUMBER = "booking_number";
    public static final String KEY_ID_CLIENT_BOOKING = "id_client_booking";
    public static final String KEY_COMPLITE_MARK_BOOKING = "complite_mark_booking";
    public static final String KEY_ID_DRIVER_BOOKING = "id_driver_booking";
    public static final String KEY_ID_CAR_BOOKING = "id_car_booking";

    public static final String TABLE_CONTACTS_CLIENT = "contactsClient";
    public static final String KEY_ID_CLIENT = "_id";
    public static final String KEY_CLIENT_NUMBERTEL = "client_numbertel";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("zxc", "Вызов конструктора DbHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("zxc", "Вызов метода onCreate класса DbHelper");
        db.execSQL("create table "
                + TABLE_CONTACTS
                + "("
                + KEY_ID + " integer primary key,"
                + KEY_NAME + " text,"
                + KEY_SURNAME + " text,"
                + KEY_PATRONYMIC + " text,"
                + KEY_CAR + " text,"
                + KEY_VACATION + " text,"
                + KEY_DRIVER_LIC_NUMBER + " text,"
                + KEY_DATE_LICENSE + " text"
                + ")"
                + "");

        db.execSQL("create table "
                + TABLE_CONTACTS_CAR
        + "("
                + KEY_ID_CAR + " integer primary key,"
        + KEY_VRS_NUMBER + " text,"
        + KEY_STATE_NUMBER + " text,"
        + KEY_BRAND + " text,"
        + KEY_YEAR_OF_ISSUE + " text,"
        + KEY_COLOR + " text,"
        + KEY_NUMBER_OF_SEATS + " text,"
        + KEY_REPAIR + " text"
        + ")"
        + "");

        db.execSQL("create table "
                + TABLE_CONTACTS_ORDER
                + "("
                + KEY_ID_ORDER + " integer primary key,"
                + KEY_DEP_ADDRESS + " text,"
                + KEY_ARR_ADDRESS + " text,"
                + KEY_DATETIME_ARRIVAL + " text,"
                + KEY_ORDER_NUMBER + " text,"
                + KEY_ID_CLIENT_ORDER + " text,"
                + KEY_COMPLITE_MARK + " text,"
                + KEY_ID_DRIVER + " text,"
                + KEY_ID_CAR_ORDERS + " text"
                + ")"
                + "");

        db.execSQL("create table "
                + TABLE_CONTACTS_BOOKING
                + "("
                + KEY_ID_BOOKING + " integer primary key,"
                + KEY_DEP_ADDRESS_BOOKING + " text,"
                + KEY_ARR_ADDRESS_BOOKING + " text,"
                + KEY_DATETIME_ARRIVAL_BOOKING + " text,"
                + KEY_DATETIME_COMPLITE_BOOKING + " text,"
                + KEY_BOOKING_NUMBER + " text,"
                + KEY_ID_CLIENT_BOOKING + " text,"
                + KEY_COMPLITE_MARK_BOOKING + " text,"
                + KEY_ID_DRIVER_BOOKING + " text,"
                + KEY_ID_CAR_BOOKING + " text"
                + ")"
                + "");

        db.execSQL("create table "
                + TABLE_CONTACTS_CLIENT
                + "("
                + KEY_ID_CLIENT + " integer primary key,"
                + KEY_CLIENT_NUMBERTEL + " text"
                + ")"
                + "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("zxc", "Вызов метода onUpgrade");
        db.execSQL("drop table if exists " + TABLE_CONTACTS);
        db.execSQL("drop table if exists " + TABLE_CONTACTS_CAR);
        db.execSQL("drop table if exists " + TABLE_CONTACTS_ORDER);
        db.execSQL("drop table if exists " + TABLE_CONTACTS_BOOKING);
        db.execSQL("drop table if exists " + TABLE_CONTACTS_CLIENT);


        onCreate(db);
    }

    public static Cursor readAll(SQLiteDatabase db){
        if (db != null)
            return db.query(DbHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        return null;
    }

    public static Cursor readAllCar(SQLiteDatabase db){
        if (db != null)
            return db.query(DbHelper.TABLE_CONTACTS_CAR, null, null, null, null, null, null);
        return null;
    }

    public static Cursor readAllOrder(SQLiteDatabase db){
        if (db != null)
            return db.query(DbHelper.TABLE_CONTACTS_ORDER, null, null, null, null, null, null);
        return null;
    }

    public static Cursor readAllBooking(SQLiteDatabase db){
        if (db != null)
            return db.query(DbHelper.TABLE_CONTACTS_BOOKING, null, null, null, null, null, null);
        return null;
    }

    public static Cursor readAllClient(SQLiteDatabase db){
        if (db != null)
            return db.query(DbHelper.TABLE_CONTACTS_CLIENT, null, null, null, null, null, null);
        return null;
    }

    public static void initForTest(SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DbHelper.KEY_NAME, "Иван");
        contentValues.put(DbHelper.KEY_DRIVER_LIC_NUMBER, "6745893475");
        db.insert(DbHelper.TABLE_CONTACTS, null, contentValues);

        contentValues.put(DbHelper.KEY_DRIVER_LIC_NUMBER, "76YB654332");
        db.insert(DbHelper.TABLE_CONTACTS_CAR, null, contentValues);

        /*contentValues.put(DbHelper.KEY_NAME, "Алексей");
        contentValues.put(DbHelper.KEY_DRIVER_LIC_NUMBER, "4356987654");
        db.insert(DbHelper.TABLE_CONTACTS, null, contentValues);

        contentValues.put(DbHelper.KEY_NAME, "Никита");
        contentValues.put(DbHelper.KEY_DRIVER_LIC_NUMBER, "9877856435");
        db.insert(DbHelper.TABLE_CONTACTS, null, contentValues);*/
    }
}

