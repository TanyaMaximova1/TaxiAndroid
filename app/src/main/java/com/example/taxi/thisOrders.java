package com.example.taxi;

import static com.example.taxi.DbHelper.KEY_DEP_ADDRESS;
import static com.example.taxi.DbHelper.KEY_ARR_ADDRESS;
import static com.example.taxi.DbHelper.KEY_DATETIME_ARRIVAL;
import static com.example.taxi.DbHelper.KEY_ID_ORDER;
import static com.example.taxi.DbHelper.KEY_ORDER_NUMBER;
import static com.example.taxi.DbHelper.KEY_ID_CLIENT_ORDER;
import static com.example.taxi.DbHelper.KEY_COMPLITE_MARK;
import static com.example.taxi.DbHelper.KEY_ID_DRIVER;
import static com.example.taxi.DbHelper.KEY_ID_CAR_ORDERS;



import androidx.appcompat.app.AppCompatActivity;

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

public class thisOrders extends AppCompatActivity implements AdapterView.OnItemClickListener {
    SQLiteDatabase database;
    DbHelper dbHelper;
    SimpleCursorAdapter simpleCursorAdapter;

    public ContentValues contentValues;

    public EditText etDep_address;
    public EditText etArr_address;
    public EditText etDate_time;
    public EditText etOrder_number;
    public EditText etId_client;
    public EditText etComplite_mark;
    public EditText etId_driver;
    public EditText etId_car_orders;
    public ListView listView;

    //массив, содержащий заголовки столбцов БД
    public static String[] from = new String[]{
            KEY_DEP_ADDRESS,
            KEY_ARR_ADDRESS,
            KEY_DATETIME_ARRIVAL,
            KEY_ORDER_NUMBER,
            KEY_ID_CLIENT_ORDER,
            KEY_COMPLITE_MARK,
            KEY_ID_DRIVER,
            KEY_ID_CAR_ORDERS
    };

    //Массив to должен содержать идентификаторы компонент, в которые
    //будут отображаться данные
    public static int[] to = new int[]{
            R.id.tvDep_address,
            R.id.tvArr_address,
            R.id.tvDate_time,
            R.id.tvOrder_number,
            R.id.tvId_client,
            R.id.tvComplite_mark,
            R.id.tvId_driver,
            R.id.tvId_car_orders
    };

    public static Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_orders);

        dbHelper = new DbHelper(getApplicationContext());
        database = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();
        etDep_address = findViewById(R.id.ptDep_address);
        etArr_address = findViewById(R.id.ptArr_address);
        etDate_time = findViewById(R.id.ptDate_time);
        etOrder_number = findViewById(R.id.ptOrder_number);
        etId_client = findViewById(R.id.ptId_client);
        etId_driver = findViewById(R.id.ptId_driver);
        etComplite_mark = findViewById(R.id.ptComplite_mark);
        etId_car_orders = findViewById(R.id.ptId_car_orders);
        listView = findViewById(R.id.listViewOrders);
    }

    public void onRefresh(View view) {
        Log.d("zxc", "Вызов метода onRefresh");
        String order_number = this.etOrder_number.getText().toString();
        cursor = database.query(dbHelper.TABLE_CONTACTS_ORDER,
                null,
                KEY_ORDER_NUMBER +" = ?",
                new String[] {order_number},
                null,
                null,
                null);

        int idIndex = cursor.getColumnIndex(KEY_ID_ORDER);
        int dep_addressIndex = cursor.getColumnIndex(KEY_DEP_ADDRESS);
        int arr_addressIndex = cursor.getColumnIndex(KEY_ARR_ADDRESS);
        int date_timeIndex = cursor.getColumnIndex(KEY_DATETIME_ARRIVAL);
        int order_numberIndex = cursor.getColumnIndex(KEY_ORDER_NUMBER);
        int id_clientIndex = cursor.getColumnIndex(KEY_ID_CLIENT_ORDER);
        int complite_markIndex = cursor.getColumnIndex(KEY_COMPLITE_MARK);
        int id_driverIndex = cursor.getColumnIndex(KEY_ID_DRIVER);
        int id_car_ordersIndex = cursor.getColumnIndex(KEY_ID_CAR_ORDERS);

        if (cursor.moveToFirst() == true) {
            ContentValues updatedValues = new ContentValues();

            updatedValues.put(KEY_DEP_ADDRESS, etDep_address.getText().toString());
            updatedValues.put(KEY_ARR_ADDRESS, etArr_address.getText().toString());
            updatedValues.put(KEY_DATETIME_ARRIVAL, etDate_time.getText().toString());
            updatedValues.put(KEY_ORDER_NUMBER, etOrder_number.getText().toString());
            updatedValues.put(KEY_ID_CLIENT_ORDER, etId_client.getText().toString());
            updatedValues.put(KEY_COMPLITE_MARK, etComplite_mark.getText().toString());
            updatedValues.put(KEY_ID_DRIVER, etId_driver.getText().toString());
            updatedValues.put(KEY_ID_CAR_ORDERS, etId_car_orders.getText().toString());

            String where = KEY_ID_ORDER + "=" + cursor.getInt(idIndex);

            database.update(dbHelper.TABLE_CONTACTS_ORDER, updatedValues, where, null);
        }
        else {
            Toast.makeText(getApplicationContext(), "Изменение записи невозможно", Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }

    public void onDelete(View view) {
        Log.d("zxc", "Вызов метода onDelete");
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String order_number = this.etOrder_number.getText().toString();
        String dep_address = this.etDep_address.getText().toString();
        cursor = database.query(dbHelper.TABLE_CONTACTS_ORDER,
                null,
                KEY_ORDER_NUMBER +" = ? AND "+ KEY_ORDER_NUMBER +" = ?",
                new String[] {order_number, dep_address},
                null,
                null,
                null);

        int idIndex = cursor.getColumnIndex(KEY_ID_ORDER);
        int dep_addressIndex = cursor.getColumnIndex(KEY_DEP_ADDRESS);
        int arr_addressIndex = cursor.getColumnIndex(KEY_ARR_ADDRESS);
        int date_timeIndex = cursor.getColumnIndex(KEY_DATETIME_ARRIVAL);
        int order_numberIndex = cursor.getColumnIndex(KEY_ORDER_NUMBER);
        int id_clientIndex = cursor.getColumnIndex(KEY_ID_CLIENT_ORDER);
        int complite_markIndex = cursor.getColumnIndex(KEY_COMPLITE_MARK);
        int id_driverIndex = cursor.getColumnIndex(KEY_ID_DRIVER);
        int id_car_ordersIndex = cursor.getColumnIndex(KEY_ID_CAR_ORDERS);

        if (cursor.moveToFirst() == true) {
            Log.d("zxc", cursor.getString(idIndex));
            Log.d("zxc", cursor.getString(dep_addressIndex));
            Log.d("zxc", cursor.getString(arr_addressIndex));
            Log.d("zxc", cursor.getString(date_timeIndex));
            Log.d("zxc", cursor.getString(order_numberIndex));
            Log.d("zxc", cursor.getString(id_clientIndex));
            Log.d("zxc", cursor.getString(complite_markIndex));
            Log.d("zxc", cursor.getString(id_driverIndex));
            Log.d("zxc", cursor.getString(id_car_ordersIndex));

            long result = database.delete(dbHelper.TABLE_CONTACTS_ORDER, KEY_ID_ORDER +" = ?", new String[] {cursor.getString(idIndex)});
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
        String dep_address = this.etDep_address.getText().toString();
        String arr_address = this.etArr_address.getText().toString();
        String date_time = this.etDate_time.getText().toString();
        String order_number = this.etOrder_number.getText().toString();
        String id_client = this.etId_client.getText().toString();
        String complite_mark = this.etComplite_mark.getText().toString();
        String id_driver = this.etId_driver.getText().toString();
        String id_car_orders = this.etId_car_orders.getText().toString();

        cursor = database.query(dbHelper.TABLE_CONTACTS_ORDER,
                null,
                KEY_ORDER_NUMBER +" = ?",
                new String[] {order_number},
                null,
                null,
                null);

        if(cursor.moveToFirst() == false) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_DEP_ADDRESS, dep_address);
            contentValues.put(KEY_ARR_ADDRESS, arr_address);
            contentValues.put(KEY_DATETIME_ARRIVAL, date_time);
            contentValues.put(KEY_ORDER_NUMBER, order_number);
            contentValues.put(KEY_ID_CLIENT_ORDER, id_client);
            contentValues.put(KEY_COMPLITE_MARK, complite_mark);
            contentValues.put(KEY_ID_DRIVER, id_driver);
            contentValues.put(KEY_ID_CAR_ORDERS, id_car_orders);
            long result = database.insert(dbHelper.TABLE_CONTACTS_ORDER, null, contentValues);
            Toast.makeText(getApplicationContext(), "Добавлено " + result, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Запись с таким номером уже есть в базе данных", Toast.LENGTH_LONG).show();
        }

    }

    public void onRead(View view) {
        Log.d("zxc", "Вызов метода onRead");
        cursor = DbHelper.readAllOrder(database); //?
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        cursor = database.query(dbHelper.TABLE_CONTACTS_ORDER,
                null,
                null,
                null,
                null,
                null,
                null);


        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.itemorders, cursor, from, to, 0);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(this);


        if(cursor.moveToFirst() == true){
            //Получение индексов
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID_ORDER);
                int dep_addressIndex = cursor.getColumnIndex(KEY_DEP_ADDRESS);
                int arr_addressIndex = cursor.getColumnIndex(KEY_ARR_ADDRESS);
                int date_timeIndex = cursor.getColumnIndex(KEY_DATETIME_ARRIVAL);
                int order_numberIndex = cursor.getColumnIndex(KEY_ORDER_NUMBER);
                int id_clientIndex = cursor.getColumnIndex(KEY_ID_CLIENT_ORDER);
                int complite_markIndex = cursor.getColumnIndex(KEY_COMPLITE_MARK);
                int id_driverIndex = cursor.getColumnIndex(KEY_ID_DRIVER);
                int id_car_ordersIndex = cursor.getColumnIndex(KEY_ID_CAR_ORDERS);

                Log.d("zxc", cursor.getString(idIndex));
                Log.d("zxc", cursor.getString(dep_addressIndex));
                Log.d("zxc", cursor.getString(arr_addressIndex));
                Log.d("zxc", cursor.getString(date_timeIndex));
                Log.d("zxc", cursor.getString(order_numberIndex));
                Log.d("zxc", cursor.getString(id_clientIndex));
                Log.d("zxc", cursor.getString(complite_markIndex));
                Log.d("zxc", cursor.getString(id_driverIndex));
                Log.d("zxc", cursor.getString(id_car_ordersIndex));


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
        Log.d("zxc", "Вызов метода onRead");
        // = DbHelper.readAllOrder(database); //?
        //SQLiteDatabase database = dbHelper.getWritableDatabase();
        cursor = database.query(DbHelper.TABLE_CONTACTS_ORDER,
                null,
                null,
                null,
                null,
                null,
                null);


        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.itemorders, cursor, from, to, 0);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(this);
    }

    public void onClearInput(View view) {
        etDep_address.setText("");
        etArr_address.setText("");
        etDate_time.setText("");
        etOrder_number.setText("");
        etId_client.setText("");
        etComplite_mark.setText("");
        etId_driver.setText("");
        etId_car_orders.setText("");
    }

    public void onSearch(View view) {
        Log.d("zxc", "Вызов метода onSearch");

        String searchableDep_address = etDep_address.getText().toString();
        String searchableArr_address = etArr_address.getText().toString();
        String searchableDate_time = etDate_time.getText().toString();
        String searchableOrder_number = etOrder_number.getText().toString();
        String searchableId_client = etId_client.getText().toString();
        String searchableComplite_mark = etComplite_mark.getText().toString();
        String searchableId_driver = etId_driver.getText().toString();
        String searchableId_car_orders = etId_car_orders.getText().toString();

        //cursor = DbHelper.readAll(database);
        cursor = database.query(DbHelper.TABLE_CONTACTS_ORDER,
                null,
                KEY_DEP_ADDRESS + " LIKE ? AND " +
                        KEY_ARR_ADDRESS + " LIKE ? AND " +
                        KEY_DATETIME_ARRIVAL + " LIKE ? AND " +
                        KEY_ORDER_NUMBER + " LIKE ? AND " +
                        KEY_ID_CLIENT_ORDER + " LIKE ? AND " +
                        KEY_COMPLITE_MARK + " LIKE ? AND " +
                        KEY_ID_DRIVER + " LIKE ? AND " +
                        KEY_ID_CAR_ORDERS + " LIKE ? "
                ,
                new String[] {
                        searchableDep_address + "%",
                        searchableArr_address + "%",
                        searchableDate_time + "%",
                        searchableOrder_number + "%",
                        searchableId_client + "%",
                        searchableComplite_mark + "%",
                        searchableId_driver + "%",
                        searchableId_car_orders + "%"
                },
                null,
                null,
                null);

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.itemorders, cursor, from, to, 0);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        //int idIndex = cursor.getColumnIndex(KEY_ID_ORDER);
        int dep_addressIndex = cursor.getColumnIndex(KEY_DEP_ADDRESS);
        int arr_addressIndex = cursor.getColumnIndex(KEY_ARR_ADDRESS);
        int date_timeIndex = cursor.getColumnIndex(KEY_DATETIME_ARRIVAL);
        int order_numberIndex = cursor.getColumnIndex(KEY_ORDER_NUMBER);
        int id_clientIndex = cursor.getColumnIndex(KEY_ID_CLIENT_ORDER);
        int complite_markIndex = cursor.getColumnIndex(KEY_COMPLITE_MARK);
        int id_driverIndex = cursor.getColumnIndex(KEY_ID_DRIVER);
        int id_car_ordersIndex = cursor.getColumnIndex(KEY_ID_CAR_ORDERS);

        cursor.moveToPosition(position);

        etDep_address.setText(cursor.getString(dep_addressIndex));
        etArr_address.setText(cursor.getString(arr_addressIndex));
        etDate_time.setText(cursor.getString(date_timeIndex));
        etOrder_number.setText(cursor.getString(order_numberIndex));
        etId_client.setText(cursor.getString(id_clientIndex));
        etComplite_mark.setText(cursor.getString(complite_markIndex));
        etId_driver.setText(cursor.getString(id_driverIndex));
        etId_car_orders.setText(cursor.getString(id_car_ordersIndex));
    }
}