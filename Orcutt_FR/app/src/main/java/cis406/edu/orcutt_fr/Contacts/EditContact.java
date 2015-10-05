package cis406.edu.orcutt_fr.Contacts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import cis406.edu.orcutt_fr.Database.DatabaseHelper;
import cis406.edu.orcutt_fr.R;

/**
 * Created by Tyler Orcutt on 9/27/2015.
 */
public class EditContact extends AppCompatActivity {
    private int id;
    protected SQLiteDatabase db;
    protected Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        //get passed in extras
        Intent intent = getIntent();
        id=intent.getIntExtra("Contact_id",-1);
        Log.d("EditContact", String.valueOf(id));

        db = (new DatabaseHelper( this.getBaseContext()).getWritableDatabase());
        if(id>-1){
            //find the user that was selected



                   EditText edit1 = (EditText) findViewById(R.id.EditFIrstName);
                    EditText edit2 = (EditText) findViewById(R.id.EditLastName);
            cursor = db.rawQuery("SELECT * from FR_contacts where _id="+id,null);
            if(cursor.getCount()>0) {
                cursor.moveToFirst();
                String fName = ( cursor.getString(cursor.getColumnIndex("firstName")));
                String lName=( cursor.getString(cursor.getColumnIndex("lastName")));

                cursor.close();
                setTitle(fName + " " + lName);
                edit1.setText(fName);
                edit2.setText(lName);
                return;
            }
            Log.d("EditContact", "Could not find contact");


        }
        id=-1;
        setTitle("New Contact");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
