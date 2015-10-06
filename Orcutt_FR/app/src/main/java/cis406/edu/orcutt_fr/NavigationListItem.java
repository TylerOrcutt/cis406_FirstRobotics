package cis406.edu.orcutt_fr;

/**
 * Created by Tyler Orcutt on 10/5/2015.
 */
public class NavigationListItem {
    private int imgResource;
    private String text;

    public NavigationListItem(String text, int resourceID){
        this.text=text;
        this.imgResource=resourceID;
    }
    public String getText(){
        return text;
    }
    public int getImgResource(){
        return imgResource;
    }
}
