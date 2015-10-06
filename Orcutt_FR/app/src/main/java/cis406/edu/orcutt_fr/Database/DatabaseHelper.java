package cis406.edu.orcutt_fr.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tyler Orcutt on 9/27/2015.
 */
public class DatabaseHelper  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FR_contacts";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
		/*
		 * Create the employee table and populate it with sample data.
		 * In step 6, we will move these hardcoded statements to an XML document.
		 */

        String sql = "CREATE TABLE IF NOT EXISTS FR_contacts (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstName TEXT, " +
                "lastName TEXT, " +
                "middleName TEXT, " +
                "officePhone TEXT, " +
                "cellPhone TEXT, " +
                "email TEXT, " +
                "isFavorite INTEGER DEFAULT 0)";
        db.execSQL(sql);

        ContentValues values = new ContentValues();

        values.put("firstName", "John");
        values.put("lastName", "Smith");
        values.put("officePhone", "617-219-2001");
        values.put("cellPhone", "617-456-7890");
        values.put("email", "jsmith@email.com");
        db.insert("FR_contacts", "lastName", values);

        values.put("firstName", "Robert");
        values.put("lastName", "Jackson");
        values.put("officePhone", "617-219-3333");
        values.put("cellPhone", "781-444-2222");
        values.put("email", "rjackson@email.com");
        db.insert("FR_contacts", "lastName", values);

        values.put("firstName", "Marie");
        values.put("lastName", "Potter");
        values.put("officePhone", "617-219-2002");
        values.put("cellPhone", "987-654-3210");
        values.put("email", "mpotter@email.com");
        db.insert("FR_contacts", "lastName", values);

        values.put("firstName", "Lisa");
        values.put("lastName", "Jordan");
        values.put("officePhone", "617-219-2003");
        values.put("cellPhone", "987-654-7777");
        values.put("email", "ljordan@email.com");
        db.insert("FR_contacts", "lastName", values);

        values.put("firstName", "Christophe");
        values.put("lastName", "Coenraets");
        values.put("officePhone", "617-219-0000");
        values.put("cellPhone", "617-666-7777");
        values.put("email", "ccoenrae@adobe.com");
        db.insert("FR_contacts", "lastName", values);

        values.put("firstName", "Paula");
        values.put("lastName", "Brown");
        values.put("officePhone", "617-612-0987");
        values.put("cellPhone", "617-123-9876");
        values.put("email", "pbrown@email.com");
        db.insert("FR_contacts", "lastName", values);

        values.put("firstName", "Mark");
        values.put("lastName", "Taylor");
        values.put("officePhone", "617-444-1122");
        values.put("cellPhone", "617-555-3344");
        values.put("email", "mtaylor@email.com");
        db.insert("FR_contacts", "lastName", values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS FR_contacts");
        onCreate(db);
    }
}