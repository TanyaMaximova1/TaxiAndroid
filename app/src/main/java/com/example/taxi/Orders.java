package com.example.taxi;

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

import static com.example.taxi.DbHelper.KEY_NAME;
import static com.example.taxi.DbHelper.KEY_SURNAME;
import static com.example.taxi.DbHelper.KEY_PATRONYMIC;
import static com.example.taxi.DbHelper.KEY_CAR;
import static com.example.taxi.DbHelper.KEY_VACATION;
import static com.example.taxi.DbHelper.KEY_DRIVER_LIC_NUMBER;
import static com.example.taxi.DbHelper.KEY_DATE_LICENSE;
import static com.example.taxi.DbHelper.KEY_ID;

public class Orders extends AppCompatActivity implements AdapterView.OnItemClickListener{
    SQLiteDatabase database;
    DbHelper dbHelper;
    SimpleCursorAdapter simpleCursorAdapter;

    public ContentValues contentValues;

    public EditText etName;
    public EditText etSurname;
    public EditText etPatronymic;
    public EditText etCar;
    public EditText etVacation;
    public EditText etDriver_lic_number;
    public EditText etDate_license;
    public ListView listView;

    //массив, содержащий заголовки столбцов БД
    public static String[] from = new String[]{
            KEY_NAME,
            KEY_SURNAME,
            KEY_PATRONYMIC,
            KEY_CAR,
            KEY_VACATION,
            KEY_DRIVER_LIC_NUMBER,
            KEY_DATE_LICENSE
    };

    //Массив to должен содержать идентификаторы компонент, в которые
    //будут отображаться данные
    public static int[] to = new int[]{
            R.id.tvName,
            R.id.tvSurname,
            R.id.tvPatronymic,
            R.id.tvCar,
            R.id.tvVacation,
            R.id.tvDriver_lic_number,
            R.id.tvDate_license
    };

    public static Cursor cursor;

    //public static Object setOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        dbHelper = new DbHelper(getApplicationContext());
        database = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();
        etName = findViewById(R.id.ptName);
        etSurname = findViewById(R.id.ptSurname);
        etPatronymic = findViewById(R.id.ptPatronymic);
        etCar = findViewById(R.id.ptCar);
        etVacation = findViewById(R.id.ptVacation);
        etDriver_lic_number = findViewById(R.id.ptDriver_lic_number);
        etDate_license = findViewById(R.id.ptDate_license);
        listView = findViewById(R.id.listView);
    }

    public void onRefresh(View view) {
        Log.d("zxc", "Вызов метода onRefresh");
        String driver_lic_number = this.etDriver_lic_number.getText().toString();
        cursor = database.query(dbHelper.TABLE_CONTACTS,
                null,
                KEY_DRIVER_LIC_NUMBER +" = ?",
                new String[] {driver_lic_number},
                null,
                null,
                null);

        int idIndex = cursor.getColumnIndex(KEY_ID);
        int nameIndex = cursor.getColumnIndex(KEY_NAME);
        int surnameIndex = cursor.getColumnIndex(KEY_SURNAME);
        int patronymicIndex = cursor.getColumnIndex(KEY_PATRONYMIC);
        int carIndex = cursor.getColumnIndex(KEY_CAR);
        int vacationIndex = cursor.getColumnIndex(KEY_VACATION);
        int driver_lic_numberIndex = cursor.getColumnIndex(KEY_DRIVER_LIC_NUMBER);
        int date_licenseIndex = cursor.getColumnIndex(KEY_DATE_LICENSE);

        if (cursor.moveToFirst() == true) {
            ContentValues updatedValues = new ContentValues();

            updatedValues.put(KEY_NAME, etName.getText().toString());
            updatedValues.put(KEY_SURNAME, etSurname.getText().toString());
            updatedValues.put(KEY_PATRONYMIC, etPatronymic.getText().toString());
            updatedValues.put(KEY_CAR, etCar.getText().toString());
            updatedValues.put(KEY_VACATION, etVacation.getText().toString());
            updatedValues.put(KEY_DRIVER_LIC_NUMBER, etDriver_lic_number.getText().toString());
            updatedValues.put(KEY_DATE_LICENSE, etDate_license.getText().toString());

            String where = KEY_ID + "=" + cursor.getInt(idIndex);

            database.update(dbHelper.TABLE_CONTACTS, updatedValues, where, null);
        }
        else {
            Toast.makeText(getApplicationContext(), "Изменение записи невозможно", Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }

    public void onDelete(View view) {
        Log.d("zxc", "Вызов метода onDelete");
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String name = this.etName.getText().toString();
        String driver_lic_number = this.etDriver_lic_number.getText().toString();
        cursor = database.query(dbHelper.TABLE_CONTACTS,
                null,
                KEY_NAME +" = ? AND "+ KEY_DRIVER_LIC_NUMBER +" = ?",
                new String[] {name, driver_lic_number},
                null,
                null,
                null);

        int idIndex = cursor.getColumnIndex(KEY_ID);
        int nameIndex = cursor.getColumnIndex(KEY_NAME);
        int surnameIndex = cursor.getColumnIndex(KEY_SURNAME);
        int patronymicIndex = cursor.getColumnIndex(KEY_PATRONYMIC);
        int carIndex = cursor.getColumnIndex(KEY_CAR);
        int vacationIndex = cursor.getColumnIndex(KEY_VACATION);
        int driver_lic_numberIndex = cursor.getColumnIndex(KEY_DRIVER_LIC_NUMBER);
        int date_licenseIndex = cursor.getColumnIndex(KEY_DATE_LICENSE);

        if (cursor.moveToFirst() == true) {
            Log.d("zxc", cursor.getString(idIndex));
            Log.d("zxc", cursor.getString(nameIndex));
            Log.d("zxc", cursor.getString(surnameIndex));
            Log.d("zxc", cursor.getString(patronymicIndex));
            Log.d("zxc", cursor.getString(carIndex));
            Log.d("zxc", cursor.getString(vacationIndex));
            Log.d("zxc", cursor.getString(driver_lic_numberIndex));
            Log.d("zxc", cursor.getString(date_licenseIndex));

            long result = database.delete(dbHelper.TABLE_CONTACTS, KEY_ID +" = ?", new String[] {cursor.getString(idIndex)});
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
        String name = this.etName.getText().toString();
        String surname = this.etSurname.getText().toString();
        String patronymic = this.etPatronymic.getText().toString();
        String car = this.etCar.getText().toString();
        String vacation = this.etVacation.getText().toString();
        String driver_lic_number = this.etDriver_lic_number.getText().toString();
        String date_license = this.etDate_license.getText().toString();

        cursor = database.query(dbHelper.TABLE_CONTACTS,
                null,
                KEY_DRIVER_LIC_NUMBER +" = ?",
                new String[] {driver_lic_number},
                null,
                null,
                null);

        if(cursor.moveToFirst() == false) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_NAME, name);
            contentValues.put(KEY_SURNAME, surname);
            contentValues.put(KEY_PATRONYMIC, patronymic);
            contentValues.put(KEY_CAR, car);
            contentValues.put(KEY_VACATION, vacation);
            contentValues.put(KEY_DRIVER_LIC_NUMBER, driver_lic_number);
            contentValues.put(KEY_DATE_LICENSE, date_license);
            long result = database.insert(dbHelper.TABLE_CONTACTS, null, contentValues);
            Toast.makeText(getApplicationContext(), "Добавлено " + result, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Запись с таким водительским удостоверением уже есть в базе данных", Toast.LENGTH_LONG).show();
        }

    }

    public void onRead(View view) {
        Log.d("zxc", "Вызов метода onRead");
         cursor = DbHelper.readAll(database); //?
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        cursor = database.query(dbHelper.TABLE_CONTACTS,
                null,
                null,
                null,
                null,
                null,
                null);


        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to, 0);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(this);


        if(cursor.moveToFirst() == true){
            //Получение индексов
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int nameIndex = cursor.getColumnIndex(KEY_NAME);
                int surnameIndex = cursor.getColumnIndex(KEY_SURNAME);
                int patronymicIndex = cursor.getColumnIndex(KEY_PATRONYMIC);
                int carIndex = cursor.getColumnIndex(KEY_CAR);
                int vacationIndex = cursor.getColumnIndex(KEY_VACATION);
                int driver_lic_numberIndex = cursor.getColumnIndex(KEY_DRIVER_LIC_NUMBER);
                int date_licenseIndex = cursor.getColumnIndex(KEY_DATE_LICENSE);

                Log.d("zxc", cursor.getString(idIndex));
                Log.d("zxc", cursor.getString(nameIndex));
                Log.d("zxc", cursor.getString(surnameIndex));
                Log.d("zxc", cursor.getString(patronymicIndex));
                Log.d("zxc", cursor.getString(carIndex));
                Log.d("zxc", cursor.getString(vacationIndex));
                Log.d("zxc", cursor.getString(driver_lic_numberIndex));
                Log.d("zxc", cursor.getString(date_licenseIndex));


                simpleCursorAdapter.changeCursor(cursor);
                listView.setOnItemClickListener(this);
                //Чтение данных и работа с ними
            } while (cursor.moveToNext() == true);
        } else {
//            outputTextView.setText("Таблица пуста");
        }
//        cursor.close();
    }

    public void onClearInput(View view) {
        etName.setText("");
        etSurname.setText("");
        etPatronymic.setText("");
        etCar.setText("");
        etVacation.setText("");
        etDriver_lic_number.setText("");
        etDate_license.setText("");
    }

    public void onSearch(View view) {
        Log.d("zxc", "Вызов метода onSearch");

        String searchableName = etName.getText().toString();
        String searchableSurname = etSurname.getText().toString();
        String searchablePatronymic = etPatronymic.getText().toString();
        String searchableCar = etCar.getText().toString();
        String searchableVacation = etVacation.getText().toString();
        String searchableDriver_lic_number = etDriver_lic_number.getText().toString();
        String searchableDate_license = etDate_license.getText().toString();

        //cursor = DbHelper.readAll(database);
        cursor = database.query(DbHelper.TABLE_CONTACTS,
                null,
                KEY_NAME + " LIKE ? AND " +
                        KEY_SURNAME + " LIKE ? AND " +
                        KEY_PATRONYMIC + " LIKE ? AND " +
                        KEY_CAR + " LIKE ? AND " +
                        KEY_VACATION + " LIKE ? AND " +
                        KEY_DRIVER_LIC_NUMBER + " LIKE ? AND " +
                        KEY_DATE_LICENSE + " LIKE ? "
                ,
                new String[] {
                        searchableName + "%",
                        searchableSurname + "%",
                        searchablePatronymic + "%",
                        searchableCar + "%",
                        searchableVacation + "%",
                        //searchableDriver_lic_number + "%",
                        //searchableDate_license + "%"
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
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to, 0);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(this);
//        else {
////            outputTextView.setText("Таблица пуста");
//        }

//      cursor.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //int idIndex = cursor.getColumnIndex(KEY_ID);
        int nameIndex = cursor.getColumnIndex(KEY_NAME);
        int surnameIndex = cursor.getColumnIndex(KEY_SURNAME);
        int patronymicIndex = cursor.getColumnIndex(KEY_PATRONYMIC);
        int carIndex = cursor.getColumnIndex(KEY_CAR);
        int vacationIndex = cursor.getColumnIndex(KEY_VACATION);
        int driver_lic_numberIndex = cursor.getColumnIndex(KEY_DRIVER_LIC_NUMBER);
        int date_licenseIndex = cursor.getColumnIndex(KEY_DATE_LICENSE);

        cursor.moveToPosition(position);

        etName.setText(cursor.getString(nameIndex));
        etSurname.setText(cursor.getString(surnameIndex));
        etPatronymic.setText(cursor.getString(patronymicIndex));
        etCar.setText(cursor.getString(carIndex));
        etVacation.setText(cursor.getString(vacationIndex));
        etDriver_lic_number.setText(cursor.getString(driver_lic_numberIndex));
        etDate_license.setText(cursor.getString(date_licenseIndex));
    }
}