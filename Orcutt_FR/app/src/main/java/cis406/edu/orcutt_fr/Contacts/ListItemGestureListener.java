package cis406.edu.orcutt_fr.Contacts;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;

import java.util.ArrayList;

import cis406.edu.orcutt_fr.Contacts.Contact;

/**
 * Created by Tyler Orcutt on 10/29/2015.
 */
public class ListItemGestureListener extends GestureDetector.SimpleOnGestureListener {
    private ArrayList<Contact> list;
    ListView listView;
    public ListItemGestureListener(){

    }
    public ListItemGestureListener( ArrayList<Contact> list, ListView v){
        this.list=list;
        this.listView = v;
    }
    @Override
    public boolean onFling(MotionEvent e, MotionEvent e2, float vx,float vy){
        int pos = listView.pointToPosition((int)e.getX(),(int)e.getY());
        Log.d("ListItem SWIPED", list.get(pos).getFirstName());
        return false;
    }
    public boolean onSingleTapUp(MotionEvent e) {

        int pos = listView.pointToPosition((int) e.getX(), (int) e.getY());

        return true;
    }
}
