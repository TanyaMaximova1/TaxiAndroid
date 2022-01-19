package com.example.taxi;

import static com.example.taxi.DbHelper.KEY_BRAND;
import static com.example.taxi.DbHelper.KEY_CAR;
import static com.example.taxi.DbHelper.KEY_COLOR;
import static com.example.taxi.DbHelper.KEY_DATE_LICENSE;
import static com.example.taxi.DbHelper.KEY_DRIVER_LIC_NUMBER;
import static com.example.taxi.DbHelper.KEY_ID;
import static com.example.taxi.DbHelper.KEY_ID_CAR;
import static com.example.taxi.DbHelper.KEY_NAME;
import static com.example.taxi.DbHelper.KEY_NUMBER_OF_SEATS;
import static com.example.taxi.DbHelper.KEY_PATRONYMIC;
import static com.example.taxi.DbHelper.KEY_REPAIR;
import static com.example.taxi.DbHelper.KEY_STATE_NUMBER;
import static com.example.taxi.DbHelper.KEY_SURNAME;
import static com.example.taxi.DbHelper.KEY_VACATION;
import static com.example.taxi.DbHelper.KEY_VRS_NUMBER;
import static com.example.taxi.DbHelper.KEY_YEAR_OF_ISSUE;

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

public class Cars extends AppCompatActivity implements AdapterView.OnItemClickListener{
    public static Cursor cursor;
    SQLiteDatabase database;
    DbHelper dbHelper;
    SimpleCursorAdapter simpleCursorAdapter;
    public ContentValues contentValues;

    public EditText etVRSNumber;
    public EditText etStateNumber;
    public EditText etBrand;
    public EditText etYearOfIssue;
    public EditText etColor;
    public EditText etNumberOfSeats;
    public EditText etRepair;
    public ListView listViewCars;

    //массив, содержащий заголовки столбцов БД
    public static String[] from = new String[]{
            KEY_VRS_NUMBER,
            KEY_STATE_NUMBER,
            KEY_BRAND,
            KEY_YEAR_OF_ISSUE,
            KEY_COLOR,
            KEY_NUMBER_OF_SEATS,
            KEY_REPAIR
    };

    //Массив to должен содержать идентификаторы компонент, в которые
    //будут отображаться данные
    public static int[] to = new int[]{
            R.id.tvVRSNumber,
            R.id.tvStateNumber,
            R.id.tvBrand,
            R.id.tvYearOfIssue,
            R.id.tvColor,
            R.id.tvNumberOfSeats,
            R.id.tvRepair
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);

        dbHelper = new DbHelper(getApplicationContext());
        database = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();
        etVRSNumber = findViewById(R.id.ptVRSNumber);
        etStateNumber = findViewById(R.id.ptStateNumber);
        etBrand = findViewById(R.id.ptBrand);
        etYearOfIssue = findViewById(R.id.ptYearOfIssue);
        etColor = findViewById(R.id.ptColor);
        etNumberOfSeats = findViewById(R.id.ptNumberOfSeats);
        etRepair = findViewById(R.id.ptRepair);
        listViewCars = findViewById(R.id.listViewCars);
    }

    public void onRefreshCar(View view) {
        Log.d("zxc", "Вызов метода onRefresh");
        String vrs_number = this.etVRSNumber.getText().toString();
        cursor = database.query(dbHelper.TABLE_CONTACTS_CAR,
                null,
                KEY_VRS_NUMBER +" = ?",
                new String[] {vrs_number},
                null,
                null,
                null);

        int idIndex = cursor.getColumnIndex(KEY_ID_CAR);
        int vRSNumberIndex = cursor.getColumnIndex(KEY_VRS_NUMBER);
        int stateNumberIndex = cursor.getColumnIndex(KEY_STATE_NUMBER);
        int brandIndex = cursor.getColumnIndex(KEY_BRAND);
        int yearOfIssueIndex = cursor.getColumnIndex(KEY_YEAR_OF_ISSUE);
        int colorIndex = cursor.getColumnIndex(KEY_COLOR);
        int numberOfSeatsIndex = cursor.getColumnIndex(KEY_NUMBER_OF_SEATS);
        int repairIndex = cursor.getColumnIndex(KEY_REPAIR);

        if (cursor.moveToFirst() == true) {
            ContentValues updatedValues = new ContentValues();

            updatedValues.put(KEY_VRS_NUMBER, etVRSNumber.getText().toString());
            updatedValues.put(KEY_STATE_NUMBER, etStateNumber.getText().toString());
            updatedValues.put(KEY_BRAND, etBrand.getText().toString());
            updatedValues.put(KEY_YEAR_OF_ISSUE, etYearOfIssue.getText().toString());
            updatedValues.put(KEY_COLOR, etColor.getText().toString());
            updatedValues.put(KEY_NUMBER_OF_SEATS, etNumberOfSeats.getText().toString());
            updatedValues.put(KEY_REPAIR, etRepair.getText().toString());

            String where = KEY_ID_CAR + "=" + cursor.getInt(idIndex);

            database.update(dbHelper.TABLE_CONTACTS_CAR, updatedValues, where, null);
        }
        else {
            Toast.makeText(getApplicationContext(), "Изменение записи невозможно", Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }

    public void onDeleteCars(View view) {
        Log.d("zxc", "Вызов метода onDelete");
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String vrs_number = this.etVRSNumber.getText().toString();
        String brand = this.etBrand.getText().toString();
        cursor = database.query(dbHelper.TABLE_CONTACTS_CAR,
                null,
                KEY_VRS_NUMBER +" = ? AND "+ KEY_BRAND +" = ?",
                new String[] {vrs_number, brand},
                null,
                null,
                null);

        int idIndex = cursor.getColumnIndex(KEY_ID_CAR);
        int vRSNumberIndex = cursor.getColumnIndex(KEY_VRS_NUMBER);
        int stateNumberIndex = cursor.getColumnIndex(KEY_STATE_NUMBER);
        int brandIndex = cursor.getColumnIndex(KEY_BRAND);
        int yearOfIssueIndex = cursor.getColumnIndex(KEY_YEAR_OF_ISSUE);
        int colorIndex = cursor.getColumnIndex(KEY_COLOR);
        int numberOfSeatsIndex = cursor.getColumnIndex(KEY_NUMBER_OF_SEATS);
        int repairIndex = cursor.getColumnIndex(KEY_REPAIR);

        if (cursor.moveToFirst() == true) {
            Log.d("zxc", cursor.getString(idIndex));
            Log.d("zxc", cursor.getString(vRSNumberIndex));
            Log.d("zxc", cursor.getString(stateNumberIndex));
            Log.d("zxc", cursor.getString(brandIndex));
            Log.d("zxc", cursor.getString(yearOfIssueIndex));
            Log.d("zxc", cursor.getString(colorIndex));
            Log.d("zxc", cursor.getString(numberOfSeatsIndex));
            Log.d("zxc", cursor.getString(repairIndex));

            long result = database.delete(dbHelper.TABLE_CONTACTS_CAR, KEY_ID_CAR +" = ?", new String[] {cursor.getString(idIndex)});
            Toast.makeText(getApplicationContext(), "Удалено " + result, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Удаление по заданным данным невозможно", Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }

    public void onAddCars(View view) {
        Log.d("zxc", "Вызов метода onAdd");
        //SQLiteDatabase database = dbHelper.getWritableDatabase();
        String vrs_number = this.etVRSNumber.getText().toString();
        String state_number = this.etStateNumber.getText().toString();
        String brand = this.etBrand.getText().toString();
        String year_of_issue = this.etYearOfIssue.getText().toString();
        String color = this.etColor.getText().toString();
        String number_of_seats = this.etNumberOfSeats.getText().toString();
        String repair = this.etRepair.getText().toString();

        cursor = database.query(dbHelper.TABLE_CONTACTS_CAR,
                null,
                KEY_VRS_NUMBER +" = ?",
                new String[] {vrs_number},
                null,
                null,
                null);

        if (cursor.moveToFirst() == false) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_VRS_NUMBER, vrs_number);
            contentValues.put(KEY_STATE_NUMBER, state_number);
            contentValues.put(KEY_BRAND, brand);
            contentValues.put(KEY_YEAR_OF_ISSUE, year_of_issue);
            contentValues.put(KEY_COLOR, color);
            contentValues.put(KEY_NUMBER_OF_SEATS, number_of_seats);
            contentValues.put(KEY_REPAIR, repair);
            long result = database.insert(dbHelper.TABLE_CONTACTS_CAR, null, contentValues);
            Toast.makeText(getApplicationContext(), "Добавлено " + result, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Запись с таким № свидетельства о регистрации ТС уже есть в базе данных", Toast.LENGTH_LONG).show();
        }
    }

    public void onReadCars(View view) {
        Log.d("zxc", "Вызов метода onRead");
        cursor = DbHelper.readAllCar(database); //?
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        cursor = database.query(dbHelper.TABLE_CONTACTS_CAR,
                null,
                null,
                null,
                null,
                null,
                null);


        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.itemcars, cursor, from, to, 0);
        listViewCars.setAdapter(simpleCursorAdapter);
        listViewCars.setOnItemClickListener(this);


        if(cursor.moveToFirst() == true){
            //Получение индексов
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID_CAR);
                int vRSNumberIndex = cursor.getColumnIndex(KEY_VRS_NUMBER);
                int stateNumberIndex = cursor.getColumnIndex(KEY_STATE_NUMBER);
                int brandIndex = cursor.getColumnIndex(KEY_BRAND);
                int yearOfIssueIndex = cursor.getColumnIndex(KEY_YEAR_OF_ISSUE);
                int colorIndex = cursor.getColumnIndex(KEY_COLOR);
                int numberOfSeatsIndex = cursor.getColumnIndex(KEY_NUMBER_OF_SEATS);
                int repairIndex = cursor.getColumnIndex(KEY_REPAIR);

                Log.d("zxc", cursor.getString(idIndex));
                Log.d("zxc", cursor.getString(vRSNumberIndex));
                Log.d("zxc", cursor.getString(stateNumberIndex));
                Log.d("zxc", cursor.getString(brandIndex));
                Log.d("zxc", cursor.getString(yearOfIssueIndex));
                Log.d("zxc", cursor.getString(colorIndex));
                Log.d("zxc", cursor.getString(numberOfSeatsIndex));
                Log.d("zxc", cursor.getString(repairIndex));


                simpleCursorAdapter.changeCursor(cursor);
                listViewCars.setOnItemClickListener(this);
                //Чтение данных и работа с ними
            } while (cursor.moveToNext() == true);
        } else {
//            outputTextView.setText("Таблица пуста");
        }
//        cursor.close();
    }

    public void onReadCarsAll(View view) {
        Log.d("zxc", "Вызов метода onRead");
        cursor = database.query(dbHelper.TABLE_CONTACTS_CAR,
                null,
                null,
                null,
                null,
                null,
                null);


        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.itemcars, cursor, from, to, 0);
        listViewCars.setAdapter(simpleCursorAdapter);
        listViewCars.setOnItemClickListener(this);
    }

    public void onClearInputCArs(View view) {
        etVRSNumber.setText("");
        etStateNumber.setText("");
        etBrand.setText("");
        etYearOfIssue.setText("");
        etColor.setText("");
        etNumberOfSeats.setText("");
        etRepair.setText("");
    }

    public void onSearchCars(View view) {
        Log.d("zxc", "Вызов метода onSearch");

        String searchableVRSNumber = etVRSNumber.getText().toString();
        String searchableStateNumber = etStateNumber.getText().toString();
        String searchableBrand = etBrand.getText().toString();
        String searchableYearOfIssue = etYearOfIssue.getText().toString();
        String searchableColor = etColor.getText().toString();
        String searchableNumberOfSeats = etNumberOfSeats.getText().toString();
        String searchableRepair = etRepair.getText().toString();

        //cursor = DbHelper.readAll(database);
        cursor = database.query(DbHelper.TABLE_CONTACTS_CAR,
                null,
                KEY_VRS_NUMBER + " LIKE ? AND " +
                        KEY_STATE_NUMBER + " LIKE ? AND " +
                        KEY_BRAND + " LIKE ? AND " +
                        KEY_YEAR_OF_ISSUE + " LIKE ? AND " +
                        KEY_COLOR + " LIKE ? AND " +
                        KEY_NUMBER_OF_SEATS + " LIKE ? AND " +
                        KEY_REPAIR + " LIKE ? "
                ,
                new String[] {
                        searchableVRSNumber + "%",
                        searchableStateNumber + "%",
                        searchableBrand + "%",
                        searchableYearOfIssue + "%",
                        searchableColor + "%",
                        searchableNumberOfSeats + "%",
                        searchableRepair + "%"
                },
                null,
                null,
                null);

//        if(cursor.moveToFirst() == true){
//            //Получение индексов
//            do {
//                int idIndex = cursor.getColumnIndex(KEY_ID);
//                int nameIndex = cursor.getColumnIndex(KEY_NAME);
//                int surnameIndex = cursor.getColumnIndex(KEY_SURNAME);
//                int patronymicIndex = cursor.getColumnIndex(KEY_PATRONYMIC);
//                int carIndex = cursor.getColumnIndex(KEY_CAR);
//                int vacationIndex = cursor.getColumnIndex(KEY_VACATION);
//                int driver_lic_numberIndex = cursor.getColumnIndex(KEY_DRIVER_LIC_NUMBER);
//                int date_licenseIndex = cursor.getColumnIndex(KEY_DATE_LICENSE);
//
//                Log.d("zxc", cursor.getString(idIndex));
//                Log.d("zxc", cursor.getString(nameIndex));
//                Log.d("zxc", cursor.getString(surnameIndex));
//                Log.d("zxc", cursor.getString(patronymicIndex));
//                Log.d("zxc", cursor.getString(carIndex));
//                Log.d("zxc", cursor.getString(vacationIndex));
//                Log.d("zxc", cursor.getString(driver_lic_numberIndex));
//                Log.d("zxc", cursor.getString(date_licenseIndex));
//
////                outputTextView.setText(
////                        outputTextView.getText() +
////                                "Имя: " + cursor.getString(nameIndex) +
////                                "; Фамилия: " + cursor.getString(surnameIndex) +
////                                "; Номер группы: " + cursor.getString(groupNumberIndex) +
////                                "; Телефон: " + cursor.getString(telephoneIndex) +
////                                "; E-mail: " + cursor.getString(mailIndex) + "\n\r");
//                //Чтение данных и работа с ними
//
//
//            } while (cursor.moveToNext() == true);
//
//        }
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.itemcars, cursor, from, to, 0);
        listViewCars.setAdapter(simpleCursorAdapter);
        listViewCars.setOnItemClickListener(this);
//        else {
////            outputTextView.setText("Таблица пуста");
//        }

//      cursor.close();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        int idIndex = cursor.getColumnIndex(KEY_ID_CAR);
        int vRSNumberIndex = cursor.getColumnIndex(KEY_VRS_NUMBER);
        int stateNumberIndex = cursor.getColumnIndex(KEY_STATE_NUMBER);
        int brandIndex = cursor.getColumnIndex(KEY_BRAND);
        int yearOfIssueIndex = cursor.getColumnIndex(KEY_YEAR_OF_ISSUE);
        int colorIndex = cursor.getColumnIndex(KEY_COLOR);
        int numberOfSeatsIndex = cursor.getColumnIndex(KEY_NUMBER_OF_SEATS);
        int repairIndex = cursor.getColumnIndex(KEY_REPAIR);

        cursor.moveToPosition(position);

        etVRSNumber.setText(cursor.getString(vRSNumberIndex));
        etStateNumber.setText(cursor.getString(stateNumberIndex));
        etBrand.setText(cursor.getString(brandIndex));
        etYearOfIssue.setText(cursor.getString(yearOfIssueIndex));
        etColor.setText(cursor.getString(colorIndex));
        etNumberOfSeats.setText(cursor.getString(numberOfSeatsIndex));
        etRepair.setText(cursor.getString(repairIndex));
    }
}