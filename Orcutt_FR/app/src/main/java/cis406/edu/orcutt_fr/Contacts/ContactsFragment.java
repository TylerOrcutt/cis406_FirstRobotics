package cis406.edu.orcutt_fr.Contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.gesture.GestureOverlayView;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

import cis406.edu.orcutt_fr.Database.DatabaseHelper;
import cis406.edu.orcutt_fr.R;
import android.app.Activity;
import android.os.Bundle;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ContactsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected ContactListAdapter adapter;
    protected ListView contactList;
    private  ArrayList<Contact> contacts;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Animation animation;
   // private ListView contactList;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
     //   args.putString(ARG_PARAM1, param1);
     //   args.putString(ARG_PARAM2, param2);
      //  fragment.setArguments(args);
        return fragment;
    }

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
       //animation = AnimationUtils.loadAnimation(this.getContext(),android.R.anim.slide_out_right);

        db = (new DatabaseHelper(getContext()).getWritableDatabase());

        //query db
      /*  cursor = db.query("FR_contacts",null,null,null,null,null,"firstName ASC");
        contacts = new ArrayList<Contact>();
        cursor.moveToFirst();
        //add each item to the contacts list
        for(int i=0;i<cursor.getCount();i++){
            contacts.add(new Contact(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("firstName")),
                    cursor.getString(cursor.getColumnIndex("lastName")), cursor.getString(cursor.getColumnIndex("cellPhone"))));
            cursor.moveToNext();
        }
        cursor.close();
        //set up the adapter
        adapter = new ContactListAdapter(getContext(),R.layout.contact_list_item,contacts);

*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        cursor = db.query("FR_contacts",null,null,null,null,null,"firstName ASC");
        contacts = new ArrayList<Contact>();
        cursor.moveToFirst();
        //add each item to the contacts list
        for(int i=0;i<cursor.getCount();i++){
            contacts.add(new Contact(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("firstName")),
                    cursor.getString(cursor.getColumnIndex("lastName")), cursor.getString(cursor.getColumnIndex("cellPhone"))));
            cursor.moveToNext();
        }
        cursor.close();
        //set up the adapter
        adapter = new ContactListAdapter(getContext(),R.layout.contact_list_item,contacts);

        // Inflate the layout for this fragment
        View inf = inflater.inflate(R.layout.fragment_contacts, container, false);
        contactList= (ListView) inf.findViewById(R.id.contacts_listView);
        adapter.notifyDataSetChanged();
        contactList.setAdapter(adapter);

        contactList.setOnTouchListener(new View.OnTouchListener() {
            private int initialx = 0;
            private int currentx = 0;
            private int  initialy=0;
            private int currenty=0;
            private int padding = 0;
            private  View viewHolder=null;
            private  View lastView=null;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    padding = 0;
                    initialx = (int) event.getX();
                    currentx = (int) event.getX();
                    initialy=(int)event.getY();
                    currenty=(int)event.getY();
                    viewHolder=v;
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    currentx = (int) event.getX();
                    currenty = (int) event.getY();
                    padding = currentx - initialx;
                }

                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    int pos;
                    try{
                    pos = contactList.pointToPosition(initialx,initialy);

                    }catch(Exception e){
                            return false;
                        }
                    if(pos>=contactList.getCount() || pos<0) {
                        return false;
                    }
                    if(initialx>currentx) {

                        Log.d("Swipe", contacts.get(pos).getFirstName());
                        ViewSwitcher viewSwitcher = (ViewSwitcher) ((ListView)viewHolder).getChildAt(pos).findViewById(R.id.list_switcher);
                        viewSwitcher.showNext();
                        ((ListView)viewHolder).getChildAt(pos).findViewById(R.id.button).setVisibility(View.VISIBLE);
                        ((ListView)viewHolder).getChildAt(pos).findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pos = contactList.pointToPosition(initialx,initialy);
                                Log.d("DELETE", contacts.get(pos).getFirstName());

                              //  contacts.remove(pos);
                              db.execSQL("DELETE FROM FR_contacts where _id=" + contacts.get(pos).getId());
                               // ((ListView)viewHolder).removeViewAt(pos);
                              //  contactList.removeViewAt(pos);
                                //contacts.remove(pos);

                            }
                        });
                        return true;
                    }
                    //animate out
                    if(initialx<currentx){
                        //int pos = contactList.pointToPosition(initialx,initialy);
                        Log.d("Swipe", contacts.get(pos).getFirstName());

                       ((ListView)viewHolder).getChildAt(pos).findViewById(R.id.button).setVisibility(View.GONE);
                        return true;

                    }
                        padding = 0;
                    initialx = 0;
                    currentx = 0;
                }

                return false;
            }
        });

        // click listener for items
       // contactList.setOnGenericMotionListener((View.OnGenericMotionListener) new ListItemGestureListener(contacts,contactList));
        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //show new EditContact activity
                Intent intent = new Intent(getContext(), ViewContact.class);
                // pass in ID pram
                intent.putExtra("Contact_id", contacts.get(position).getId());
                //start the activity
                startActivity(intent);
            }


        });




        return inf;



    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
       MenuItem mi = menu.findItem(R.id.viewContact_add);
        mi.setVisible(true);
        mi = menu.findItem(R.id.viewContact_search);
        mi.setVisible(true);
        Log.d("confrag","creating option menu");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //yea search isnt here yet...
        switch (item.getItemId()) {
            case R.id.viewContact_add:
                Intent intent = new Intent(getContext(),EditContact.class);
                intent.putExtra("Contact_id",-1);
                startActivity(intent);
                break;

            default:
                break;
        }

        return false;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
@Override
public void onStart() {
    super.onStart();
    Log.d("CONTACTSFRAG", "STarted");
    cursor = db.query("FR_contacts",null,null,null,null,null,"firstName ASC");
    contacts = new ArrayList<Contact>();
    cursor.moveToFirst();
    //add each item to the contacts list
    for(int i=0;i<cursor.getCount();i++){
        contacts.add(new Contact(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("firstName")),
                cursor.getString(cursor.getColumnIndex("lastName")), cursor.getString(cursor.getColumnIndex("cellPhone"))));
        cursor.moveToNext();
    }
    cursor.close();
    //set up the adapter
    adapter = new ContactListAdapter(getContext(),R.layout.contact_list_item,contacts);

    // Inflate the layout for this fragment
  //  View inf = inflater.inflate(R.layout.fragment_contacts, container, false);
    contactList.invalidate();
    contactList.setAdapter(adapter);


    // click listener for items
    contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //show new EditContact activity
            Intent intent = new Intent(getContext(), ViewContact.class);
            // pass in ID pram
            intent.putExtra("Contact_id", contacts.get(position).getId());
            //start the activity
            startActivity(intent);
        }
    });



}
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
      /*  try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
        Log.d("CONTACTSFRAG","ATTACHED");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


}
