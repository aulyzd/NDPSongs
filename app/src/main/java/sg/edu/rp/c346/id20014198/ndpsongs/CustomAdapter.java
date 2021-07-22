package sg.edu.rp.c346.id20014198.ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource, ArrayList<Song>objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvtitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvyear = rowView.findViewById(R.id.textViewYear);
        TextView tvstar = rowView.findViewById(R.id.textViewStar);
        TextView tvsinger = rowView.findViewById(R.id.textViewSinger);

        Song currentList = songList.get(position);

        String numberstars = "";
        if (currentList.getStars() == 1){
            numberstars= "*";
        }else if(currentList.getStars() ==2){
            numberstars = "**";
        }else if(currentList.getStars() ==3){
            numberstars="***";
        }else if(currentList.getStars() ==4){
            numberstars="****";
        }else if(currentList.getStars()==5){
            numberstars="*****";
        }

        tvtitle.setText(currentList.getTitle());
        tvyear.setText(currentList.getYear()+"");
        tvstar.setText(numberstars);
        tvsinger.setText(currentList.getSingers());

        return rowView;

    }
}
