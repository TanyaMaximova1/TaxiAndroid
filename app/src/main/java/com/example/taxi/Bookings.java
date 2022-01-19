package com.example.taxi;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.taxi.DbHelper.KEY_ARR_ADDRESS;
import static com.example.taxi.DbHelper.KEY_COMPLITE_MARK;
import static com.example.taxi.DbHelper.KEY_DATETIME_ARRIVAL;
import static com.example.taxi.DbHelper.KEY_DEP_ADDRESS;
import static com.example.taxi.DbHelper.KEY_DEP_ADDRESS_BOOKING;
import static com.example.taxi.DbHelper.KEY_ARR_ADDRESS_BOOKING;
import static com.example.taxi.DbHelper.KEY_DATETIME_ARRIVAL_BOOKING;
import static com.example.taxi.DbHelper.KEY_DATETIME_COMPLITE_BOOKING;
import static com.example.taxi.DbHelper.KEY_BOOKING_NUMBER;
import static com.example.taxi.DbHelper.KEY_ID_BOOKING;
import static com.example.taxi.DbHelper.KEY_ID_CAR_ORDERS;
import static com.example.taxi.DbHelper.KEY_ID_CLIENT_BOOKING;
import static com.example.taxi.DbHelper.KEY_COMPLITE_MARK_BOOKING;
import static com.example.taxi.DbHelper.KEY_ID_CLIENT_ORDER;
import static com.example.taxi.DbHelper.KEY_ID_DRIVER;
import static com.example.taxi.DbHelper.KEY_ID_DRIVER_BOOKING;
import static com.example.taxi.DbHelper.KEY_ID_CAR_BOOKING;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Bookings extends AppCompatActivity implements AdapterView.OnItemClickListener {
    SQLiteDatabase database;
    DbHelper dbHelper;
    SimpleCursorAdapter simpleCursorAdapter;

    public ContentValues contentValues;

    public EditText etDep_address_booking;
    public EditText etArr_address_booking;
    public EditText etDate_time_arrival_booking;
    public EditText etDate_time_complite_booking;
    public EditText etBooking_number;
    public EditText etId_client_booking;
    public EditText etComplite_mark_booking;
    public EditText etId_driver_booking;
    public EditText etId_car_booking;
    public ListView listView;

    //массив, содержащий заголовки столбцов БД
    public static String[] from = new String[]{
            KEY_DEP_ADDRESS_BOOKING,
            KEY_ARR_ADDRESS_BOOKING,
            KEY_DATETIME_ARRIVAL_BOOKING,
            KEY_DATETIME_COMPLITE_BOOKING,
            KEY_BOOKING_NUMBER,
            KEY_ID_CLIENT_BOOKING,
            KEY_COMPLITE_MARK_BOOKING,
            KEY_ID_DRIVER_BOOKING,
            KEY_ID_CAR_BOOKING
    };

    //Массив to должен содержать идентификаторы компонент, в которые
    //будут отображаться данные
    public static int[] to = new int[]{
            R.id.tvDep_address_booking,
            R.id.tvArr_address_booking,
            R.id.tvDate_time_arrival_booking,
            R.id.tvDate_time_complite_booking,
            R.id.tvBooking_number,
            R.id.tvId_client_booking,
            R.id.tvComplite_mark_booking,
            R.id.tvId_driver_booking,
            R.id.tvId_car_booking
    };

    public static Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        dbHelper = new DbHelper(getApplicationContext());
        database = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();
        etDep_address_booking = findViewById(R.id.ptDep_address_booking);
        etArr_address_booking = findViewById(R.id.ptArr_address_booking);
        etDate_time_arrival_booking = findViewById(R.id.ptDate_time_arrival_booking);
        etDate_time_complite_booking = findViewById(R.id.ptDate_time_complite_booking);
        etBooking_number = findViewById(R.id.ptBooking_number);
        etId_client_booking = findViewById(R.id.ptId_client_booking);
        etComplite_mark_booking = findViewById(R.id.ptComplite_mark_booking);
        etId_driver_booking = findViewById(R.id.ptId_driver_booking);
        etId_car_booking = findViewById(R.id.ptId_car_booking);
        listView = findViewById(R.id.listViewBooking);
    }

    public void onRefresh(View view) {
        Log.d("zxc", "Вызов метода onRefresh");
        String booking_number = this.etBooking_number.getText().toString();
        cursor = database.query(dbHelper.TABLE_CONTACTS_BOOKING,
                null,
                KEY_BOOKING_NUMBER +" = ?",
                new String[] {booking_number},
                null,
                null,
                null);

        int idIndex = cursor.getColumnIndex(KEY_ID_BOOKING);
        int dep_address_bookingIndex = cursor.getColumnIndex(KEY_DEP_ADDRESS_BOOKING);
        int arr_address_bookingIndex = cursor.getColumnIndex(KEY_ARR_ADDRESS_BOOKING);
        int date_time_arrival_bookingIndex = cursor.getColumnIndex(KEY_DATETIME_ARRIVAL_BOOKING);
        int date_time_complite_bookingIndex = cursor.getColumnIndex(KEY_DATETIME_COMPLITE_BOOKING);
        int booking_numberIndex = cursor.getColumnIndex(KEY_BOOKING_NUMBER);
        int id_client_bookingIndex = cursor.getColumnIndex(KEY_ID_CLIENT_BOOKING);
        int complite_mark_bookingIndex = cursor.getColumnIndex(KEY_COMPLITE_MARK_BOOKING);
        int id_driver_bookingIndex = cursor.getColumnIndex(KEY_ID_DRIVER_BOOKING);
        int id_car_bookingIndex = cursor.getColumnIndex(KEY_ID_CAR_BOOKING);

        if (cursor.moveToFirst() == true) {
            ContentValues updatedValues = new ContentValues();

            updatedValues.put(KEY_DEP_ADDRESS_BOOKING, etDep_address_booking.getText().toString());
            updatedValues.put(KEY_ARR_ADDRESS_BOOKING, etArr_address_booking.getText().toString());
            updatedValues.put(KEY_DATETIME_ARRIVAL_BOOKING, etDate_time_arrival_booking.getText().toString());
            updatedValues.put(KEY_DATETIME_COMPLITE_BOOKING, etDate_time_complite_booking.getText().toString());
            updatedValues.put(KEY_BOOKING_NUMBER, etBooking_number.getText().toString());
            updatedValues.put(KEY_ID_CLIENT_BOOKING, etId_client_booking.getText().toString());
            updatedValues.put(KEY_COMPLITE_MARK_BOOKING, etComplite_mark_booking.getText().toString());
            updatedValues.put(KEY_ID_DRIVER_BOOKING, etId_driver_booking.getText().toString());
            updatedValues.put(KEY_ID_CAR_BOOKING, etId_car_booking.getText().toString());

            String where = KEY_ID_BOOKING + "=" + cursor.getInt(idIndex);

            database.update(dbHelper.TABLE_CONTACTS_BOOKING, updatedValues, where, null);
        }
        else {
            Toast.makeText(getApplicationContext(), "Изменение записи невозможно", Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }

    public void onDelete(View view) {
        Log.d("zxc", "Вызов метода onDelete");
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String booking_number = this.etBooking_number.getText().toString();
        String dep_address_booking = this.etDep_address_booking.getText().toString();
        cursor = database.query(dbHelper.TABLE_CONTACTS_BOOKING,
                null,
                KEY_BOOKING_NUMBER +" = ? AND "+ KEY_DEP_ADDRESS_BOOKING +" = ?",
                new String[] {booking_number, dep_address_booking},
                null,
                null,
                null);

        int idIndex = cursor.getColumnIndex(KEY_ID_BOOKING);
        int dep_address_bookingIndex = cursor.getColumnIndex(KEY_DEP_ADDRESS_BOOKING);
        int arr_address_bookingIndex = cursor.getColumnIndex(KEY_ARR_ADDRESS_BOOKING);
        int date_time_arrival_bookingIndex = cursor.getColumnIndex(KEY_DATETIME_ARRIVAL_BOOKING);
        int date_time_complite_bookingIndex = cursor.getColumnIndex(KEY_DATETIME_COMPLITE_BOOKING);
        int booking_numberIndex = cursor.getColumnIndex(KEY_BOOKING_NUMBER);
        int id_client_bookingIndex = cursor.getColumnIndex(KEY_ID_CLIENT_BOOKING);
        int complite_mark_bookingIndex = cursor.getColumnIndex(KEY_COMPLITE_MARK_BOOKING);
        int id_driver_bookingIndex = cursor.getColumnIndex(KEY_ID_DRIVER_BOOKING);
        int id_car_bookingIndex = cursor.getColumnIndex(KEY_ID_CAR_BOOKING);

        if (cursor.moveToFirst() == true) {
            Log.d("zxc", cursor.getString(idIndex));
            Log.d("zxc", cursor.getString(dep_address_bookingIndex));
            Log.d("zxc", cursor.getString(arr_address_bookingIndex));
            Log.d("zxc", cursor.getString(date_time_arrival_bookingIndex));
            Log.d("zxc", cursor.getString(date_time_complite_bookingIndex));
            Log.d("zxc", cursor.getString(booking_numberIndex));
            Log.d("zxc", cursor.getString(id_client_bookingIndex));
            Log.d("zxc", cursor.getString(complite_mark_bookingIndex));
            Log.d("zxc", cursor.getString(id_driver_bookingIndex));
            Log.d("zxc", cursor.getString(id_car_bookingIndex));

            long result = database.delete(dbHelper.TABLE_CONTACTS_BOOKING, KEY_ID_BOOKING +" = ?", new String[] {cursor.getString(idIndex)});
            Toast.makeText(getApplicationContext(), "Удалено " + result, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Удаление по заданным данным невозможно", Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }

    public void onAdd(View view) {
        Log.d("zxc", "Вызов метода onAdd");
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String dep_address_booking = this.etDep_address_booking.getText().toString();
        String arr_address_booking = this.etArr_address_booking.getText().toString();
        String date_time_arrival_booking = this.etDate_time_arrival_booking.getText().toString();
        String date_time_complite_booking = this.etDate_time_complite_booking.getText().toString();
        String booking_number = this.etBooking_number.getText().toString();
        String id_client_booking = this.etId_client_booking.getText().toString();
        String complite_mark_booking = this.etComplite_mark_booking.getText().toString();
        String id_driver_booking = this.etId_driver_booking.getText().toString();
        String id_car_booking = this.etId_car_booking.getText().toString();

        cursor = database.query(dbHelper.TABLE_CONTACTS_BOOKING,
                null,
                KEY_BOOKING_NUMBER +" = ?",
                new String[] {booking_number},
                null,
                null,
                null);

        if(cursor.moveToFirst() == false) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_DEP_ADDRESS_BOOKING, dep_address_booking);
            contentValues.put(KEY_ARR_ADDRESS_BOOKING, arr_address_booking);
            contentValues.put(KEY_DATETIME_ARRIVAL_BOOKING, date_time_arrival_booking);
            contentValues.put(KEY_DATETIME_COMPLITE_BOOKING, date_time_complite_booking);
            contentValues.put(KEY_BOOKING_NUMBER, booking_number);
            contentValues.put(KEY_ID_CLIENT_BOOKING, id_client_booking);
            contentValues.put(KEY_COMPLITE_MARK_BOOKING, complite_mark_booking);
            contentValues.put(KEY_ID_DRIVER_BOOKING, id_driver_booking);
            contentValues.put(KEY_ID_CAR_BOOKING, id_car_booking);
            long result = database.insert(dbHelper.TABLE_CONTACTS_BOOKING, null, contentValues);
            Toast.makeText(getApplicationContext(), "Добавлено " + result, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Запись с таким номером уже есть в базе данных", Toast.LENGTH_LONG).show();
        }

    }

    public void onRead(View view) {
        Log.d("zxc", "Вызов метода onRead");
        cursor = DbHelper.readAllBooking(database); //?
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        cursor = database.query(dbHelper.TABLE_CONTACTS_BOOKING,
                null,
                null,
                null,
                null,
                null,
                null);


        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.itembookings, cursor, from, to, 0);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(this);


        if(cursor.moveToFirst() == true){
            //Получение индексов
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID_BOOKING);
                int dep_address_bookingIndex = cursor.getColumnIndex(KEY_DEP_ADDRESS_BOOKING);
                int arr_address_bookingIndex = cursor.getColumnIndex(KEY_ARR_ADDRESS_BOOKING);
                int date_time_arrival_bookingIndex = cursor.getColumnIndex(KEY_DATETIME_ARRIVAL_BOOKING);
                int date_time_complite_bookingIndex = cursor.getColumnIndex(KEY_DATETIME_COMPLITE_BOOKING);
                int booking_numberIndex = cursor.getColumnIndex(KEY_BOOKING_NUMBER);
                int id_client_bookingIndex = cursor.getColumnIndex(KEY_ID_CLIENT_BOOKING);
                int complite_mark_bookingIndex = cursor.getColumnIndex(KEY_COMPLITE_MARK_BOOKING);
                int id_driver_bookingIndex = cursor.getColumnIndex(KEY_ID_DRIVER_BOOKING);
                int id_car_bookingIndex = cursor.getColumnIndex(KEY_ID_CAR_BOOKING);

                Log.d("zxc", cursor.getString(idIndex));
                Log.d("zxc", cursor.getString(dep_address_bookingIndex));
                Log.d("zxc", cursor.getString(arr_address_bookingIndex));
                Log.d("zxc", cursor.getString(date_time_arrival_bookingIndex));
                Log.d("zxc", cursor.getString(date_time_complite_bookingIndex));
                Log.d("zxc", cursor.getString(booking_numberIndex));
                Log.d("zxc", cursor.getString(id_client_bookingIndex));
                Log.d("zxc", cursor.getString(complite_mark_bookingIndex));
                Log.d("zxc", cursor.getString(id_driver_bookingIndex));
                Log.d("zxc", cursor.getString(id_car_bookingIndex));


                simpleCursorAdapter.changeCursor(cursor);
                listView.setOnItemClickListener(this);
                //Чтение данных и работа с ними
            } while (cursor.moveToNext() == true);
        } else {
//            outputTextView.setText("Таблица пуста");
        }
//        cursor.close();
    }

    public void onReadAll(View view) {
        /*Log.d("zxc", "Вызов метода onReadAll");
        //cursor = DbHelper.readAllBooking(database); //?
        //SQLiteDatabase database = dbHelper.getWritableDatabase();
        cursor = database.query(dbHelper.TABLE_CONTACTS_BOOKING,
                null,
                null,
                null,
                null,
                null,
                null);


        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.itembookings, cursor, from, to, 0);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(this);*/

        Log.d("zxc", "Вызов метода onReadAll");
        cursor = database.query(DbHelper.TABLE_CONTACTS_BOOKING,
                null,
                null,
                null,
                null,
                null,
                null);


        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.itembookings, cursor, from, to, 0);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(this);
    }

    public void onClearInput(View view) {
        etDep_address_booking.setText("");
        etArr_address_booking.setText("");
        etDate_time_arrival_booking.setText("");
        etDate_time_complite_booking.setText("");
        etBooking_number.setText("");
        etId_client_booking.setText("");
        etComplite_mark_booking.setText("");
        etId_driver_booking.setText("");
        etId_car_booking.setText("");
    }

    public void onSearch(View view) {
        Log.d("zxc", "Вызов метода onSearch");

        String searchableDep_address_booking = etDep_address_booking.getText().toString();
        String searchableArr_address_booking = etArr_address_booking.getText().toString();
        String searchableDate_time_arrival_booking = etDate_time_arrival_booking.getText().toString();
        String searchableDate_time_complite_booking = etDate_time_complite_booking.getText().toString();
        String searchableBooking_number = etBooking_number.getText().toString();
        String searchableId_client_booking = etId_client_booking.getText().toString();
        String searchableComplite_mark_booking = etComplite_mark_booking.getText().toString();
        String searchableId_driver_booking = etId_driver_booking.getText().toString();
        String searchableId_car_booking = etId_car_booking.getText().toString();

        //cursor = DbHelper.readAll(database);
        cursor = database.query(DbHelper.TABLE_CONTACTS_BOOKING,
                null,
                KEY_DEP_ADDRESS_BOOKING + " LIKE ? AND " +
                        KEY_ARR_ADDRESS_BOOKING + " LIKE ? AND " +
                        KEY_DATETIME_ARRIVAL_BOOKING + " LIKE ? AND " +
                        KEY_DATETIME_COMPLITE_BOOKING + " LIKE ? AND " +
                        KEY_BOOKING_NUMBER + " LIKE ? AND " +
                        KEY_ID_CLIENT_BOOKING + " LIKE ? AND " +
                        KEY_COMPLITE_MARK_BOOKING + " LIKE ? AND " +
                        KEY_ID_DRIVER_BOOKING + " LIKE ? AND " +
                        KEY_ID_CAR_BOOKING + " LIKE ? "
                ,
                new String[] {
                        searchableDep_address_booking + "%",
                        searchableArr_address_booking + "%",
                        searchableDate_time_arrival_booking + "%",
                        searchableDate_time_complite_booking + "%",
                        searchableBooking_number + "%",
                        searchableId_client_booking + "%",
                        searchableComplite_mark_booking + "%",
                        searchableId_driver_booking + "%",
                        searchableId_car_booking + "%"
                },
                null,
                null,
                null);

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.itembookings, cursor, from, to, 0);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        //int idIndex = cursor.getColumnIndex(KEY_ID_BOOKING);
        int dep_address_bookingIndex = cursor.getColumnIndex(KEY_DEP_ADDRESS_BOOKING);
        int arr_address_bookingIndex = cursor.getColumnIndex(KEY_ARR_ADDRESS_BOOKING);
        int date_time_arrival_bookingIndex = cursor.getColumnIndex(KEY_DATETIME_ARRIVAL_BOOKING);
        int date_time_complite_bookingIndex = cursor.getColumnIndex(KEY_DATETIME_COMPLITE_BOOKING);
        int booking_numberIndex = cursor.getColumnIndex(KEY_BOOKING_NUMBER);
        int id_client_bookingIndex = cursor.getColumnIndex(KEY_ID_CLIENT_BOOKING);
        int complite_mark_bookingIndex = cursor.getColumnIndex(KEY_COMPLITE_MARK_BOOKING);
        int id_driver_bookingIndex = cursor.getColumnIndex(KEY_ID_DRIVER_BOOKING);
        int id_car_bookingIndex = cursor.getColumnIndex(KEY_ID_CAR_BOOKING);

        cursor.moveToPosition(position);

        etDep_address_booking.setText(cursor.getString(dep_address_bookingIndex));
        etArr_address_booking.setText(cursor.getString(arr_address_bookingIndex));
        etDate_time_arrival_booking.setText(cursor.getString(date_time_arrival_bookingIndex));
        etDate_time_complite_booking.setText(cursor.getString(date_time_complite_bookingIndex));
        etBooking_number.setText(cursor.getString(booking_numberIndex));
        etId_client_booking.setText(cursor.getString(id_client_bookingIndex));
        etComplite_mark_booking.setText(cursor.getString(complite_mark_bookingIndex));
        etId_driver_booking.setText(cursor.getString(id_driver_bookingIndex));
        etId_car_booking.setText(cursor.getString(id_car_bookingIndex));
    }
}