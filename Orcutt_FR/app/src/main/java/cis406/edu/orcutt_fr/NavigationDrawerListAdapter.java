package cis406.edu.orcutt_fr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cis406.edu.orcutt_fr.Contacts.Contact;

/**
 * Created by Tyler Orcutt on 10/4/2015.
 */
public class NavigationDrawerListAdapter extends ArrayAdapter<NavigationListItem> {
    private List<NavigationListItem> items;
    public NavigationDrawerListAdapter(Context context,int id, List<NavigationListItem> resource) {
        super(context,id,resource);
        items=resource;
    }



    //get item at position
    public NavigationListItem getViewItem(int pos){
        return items.get(pos);
    }
    //get total items
    @Override
    public int getViewTypeCount(){
        return items.size();
    }
    @Override
    public View getView(int pos, View convertView,ViewGroup parent){
        //set up the view
        NavigationListItem citem = items.get(pos);
        int type=getItemViewType(pos);
        if(pos>0) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.nav_drawer_list_item, null);
            TextView txt = (TextView)convertView.findViewById(R.id.nav_item_text);
            txt.setText(citem.getText());
            ImageView img = (ImageView) convertView.findViewById(R.id.nav_item_image);
            img.setImageResource(citem.getImgResource());
        }else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.nav_draw_list_header, null);
        }


        //return view
        return convertView;

    }
}
