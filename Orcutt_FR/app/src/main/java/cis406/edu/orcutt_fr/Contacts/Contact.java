package cis406.edu.orcutt_fr.Contacts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import cis406.edu.orcutt_fr.Database.DatabaseHelper;
import cis406.edu.orcutt_fr.R;

/**
 * Created by Tyler Orcutt on 9/27/2015.
 */
public class Contact {
    private int id;
    private String firstName, lastName, number;
    private boolean separator;
    private String sepText;
    private boolean isViewEntry=false;
    private String contactType;
//constructor
    public Contact(int id, String first, String last, String number) {
        this.id = id;
        this.firstName = first;
        this.lastName = last;
        this.number = number;
        separator=false;
    }
    //constructor
    public Contact(int id, String first) {
        this.id = id;
        this.firstName = first;
        this.lastName = "";
        this.number = "";
        separator=false;
    }

    public Contact(boolean isSeparator, String sepText) {
        this.id = -1;
        this.firstName = "";
        this.lastName = "";
        this.number = "";
       this.separator=isSeparator;
        this.sepText = sepText;
        isViewEntry=false;

    }
    public Contact(boolean isViewEntry, String number, String type) {
        this.id = -1;
        this.firstName = "";
        this.lastName = "";
        this.number = number;
        this.separator=false;
        this.isViewEntry=isViewEntry;
        this.contactType=type;


    }
//getters setters
    int getId() {
        return id;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    String getNumber() {
        return number;
    }

    void setId(int id) {
        this.id = id;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    void setNumber(String number) {
        this.number = number;
    }

    boolean isSeparator(){
        return separator;
    }
    String getSepText(){
        return sepText;
    }
    boolean isViewEntry(){
        return this.isViewEntry;
    }
    String getContactType(){
        return contactType;
    }
}