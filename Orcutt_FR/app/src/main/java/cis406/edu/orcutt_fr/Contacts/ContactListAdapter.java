package cis406.edu.orcutt_fr.Contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cis406.edu.orcutt_fr.R;

/**
 * Created by Tyler Orcutt on 9/27/2015.
 */
public class ContactListAdapter extends ArrayAdapter {
    private List<Contact> contacts;

    //constructor
    public ContactListAdapter(Context context,int id, List<Contact> objects){
        super(context,id,objects);
        contacts=objects;

    }
    //get item at position
    public Contact getViewItem(int pos){
        return contacts.get(pos);
    }
    //get total items
    @Override
    public int getViewTypeCount(){
        return contacts.size();
    }
    @Override
    public View getView(int pos, View convertView,ViewGroup parent){
        //set up the view
        Contact citem = contacts.get(pos);
        int type=getItemViewType(pos);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_list_item,null);
        //get the textView
        TextView txt = (TextView)convertView.findViewById(R.id.contacts_Name_TextView);
        //set its value
        txt.setText(citem.getFirstName() + " " + citem.getLastName());

        TextView numberText = (TextView) convertView.findViewById(R.id.contacts_Number_TextView);
        numberText.setText(citem.getNumber());

        //return view
        return convertView;

    }
}
