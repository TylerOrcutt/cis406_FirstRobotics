package cis406.edu.orcutt_fr.Contacts;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    private  EditText editFName;
    private  EditText editLName;
    private  EditText editMobile;
    private  EditText editOffice;
    private  EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        //get passed in extras
        Intent intent = getIntent();
        id=intent.getIntExtra("Contact_id",-1);
        Log.d("EditContact", String.valueOf(id));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_checkmark);

    //    setTitle("Done");
        db = (new DatabaseHelper( this.getBaseContext()).getWritableDatabase());


        Button saveBtn = (Button) findViewById(R.id.Edit_button_save);
        editFName = (EditText) findViewById(R.id.editFirstName);
        editLName = (EditText) findViewById(R.id.editLastName);
        editMobile = (EditText) findViewById(R.id.editMobile);
        editOffice = (EditText) findViewById(R.id.editOffice);
        editEmail = (EditText) findViewById(R.id.editEmail);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Edit", "Contact save button clicked");
                Log.d("Edit", String.valueOf(id));
                if (editFName.getText().toString().equalsIgnoreCase("")) {
                    //need atleast a name
                    //show error
                    return;
                }
                if (id >=0) {
                    ContentValues values = new ContentValues();
                    Log.d("EditContact", "saving contact");
                    values.put("firstName", editFName.getText().toString());
                    values.put("lastName", editLName.getText().toString());
                    values.put("officePhone", editOffice.getText().toString());
                    values.put("cellPhone", editMobile.getText().toString());
                    values.put("email", editEmail.getText().toString());
                    db.update("FR_contacts", values, "_id=" + id, null);
                    Log.d("Edit", "Contact Saved");
                } else {//new contact
                    Log.d("EditContact", "adding new contact");
                      ContentValues values = new ContentValues();

                      values.put("firstName", editFName.getText().toString());
                      values.put("lastName", editLName.getText().toString());
                    values.put("officePhone", editOffice.getText().toString());
                    values.put("cellPhone", editMobile.getText().toString());
                    values.put("email", editEmail.getText().toString());
                     db.insert("FR_contacts", "_id", values);
                }
                finish();
            }
        });



        if(id>-1){
            //find the user that was selected
            cursor = db.rawQuery("SELECT * from FR_contacts where _id="+id,null);
            if(cursor.getCount()>0) {
                cursor.moveToFirst();
                String fName = ( cursor.getString(cursor.getColumnIndex("firstName")));
                String lName=( cursor.getString(cursor.getColumnIndex("lastName")));


                editFName.setText(( cursor.getString(cursor.getColumnIndex("firstName"))));


                editLName.setText(( cursor.getString(cursor.getColumnIndex("lastName"))));


                editMobile.setText(( cursor.getString(cursor.getColumnIndex("cellPhone"))));


                editOffice.setText(( cursor.getString(cursor.getColumnIndex("officePhone"))));


                editEmail.setText(( cursor.getString(cursor.getColumnIndex("email"))));

                cursor.close();


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
        int mid = item.getItemId();
        if (mid == android.R.id.home) {
            //  Log.d("view", "Home pressed");
            finish();
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (mid == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
