package cis406.edu.orcutt_fr.Contacts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

import cis406.edu.orcutt_fr.Database.DatabaseHelper;
import cis406.edu.orcutt_fr.R;

public class ViewContact extends AppCompatActivity {
   protected int id=-1;
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected ArrayList<Contact> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        Intent intent = getIntent();
        id=intent.getIntExtra("Contact_id",-1);
        Log.d("EditContact", String.valueOf(id));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = (new DatabaseHelper( this.getBaseContext()).getWritableDatabase());


    }
    @Override
    public void onStart(){
        super.onStart();
        if(id>-1){
            //find the user that was selected
            cursor = db.rawQuery("SELECT * from FR_contacts where _id="+id,null);
            cursor.moveToFirst();
            String fName = ( cursor.getString(cursor.getColumnIndex("firstName")));
            String lName=( cursor.getString(cursor.getColumnIndex("lastName")));
            setTitle("");
            TextView name_txt= (TextView) findViewById(R.id.view_contact_name);
            name_txt.setText(fName + " " + lName);




            list = new ArrayList<Contact>();

            list.add(new Contact(true,"Phone"));
            list.add(new Contact(true,( cursor.getString(cursor.getColumnIndex("cellPhone"))),"MOBILE"));
            list.add(new Contact(true,( cursor.getString(cursor.getColumnIndex("officePhone"))),"OFFICE"));
            list.add(new Contact(true,"Email"));
            list.add(new Contact(true, (cursor.getString(cursor.getColumnIndex("email"))), "EMAIL"));
            cursor.close();
            ListView listv = (ListView) findViewById(R.id.view_contact_listView);
            listv.setAdapter(new ContactListAdapter(this.getApplicationContext(),R.layout.contact_list_item,list));
        }else {
            setTitle("New Contact");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_contact, menu);
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
        if(mid == R.id.viewContact_edit){
            Intent intent = new Intent(this,EditContact.class);
            int contact_id =getIntent().getIntExtra("Contact_id",-1);
            intent.putExtra("Contact_id",contact_id);
            startActivity(intent);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }



}
