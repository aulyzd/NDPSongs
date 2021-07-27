package sg.edu.rp.c346.id20014198.ndpsongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    Button btnshowstars;
    Spinner spyear;
    ListView lv;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    ArrayList<String> al0;
    ArrayAdapter<String> ab;
    int requestCode = 9;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnshowstars = findViewById(R.id.btnshowstars);
        spyear = findViewById(R.id.spYear);
        lv = findViewById(R.id.lv);


        DBHelper dbh = new DBHelper(this);
        al = dbh.getAllSongs();
        dbh.close();

        //aa = new ArrayAdapter<Song>(this,
        //        android.R.layout.simple_list_item_1, al);
        //lv.setAdapter(aa);
        aa = new CustomAdapter(this,R.layout.row,al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ListActivity.this, UpdateActivity.class);
                i.putExtra("data", al.get(position));
                startActivityForResult(i, requestCode);
            }
        });

        btnshowstars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Song> stars = new ArrayList<>();
                for (Song i : al) {
                    if (i.getStars() == 5) {
                        stars.add(i);
                    } else {
                        Toast.makeText(ListActivity.this, "The stars is not 5 stars", Toast.LENGTH_SHORT).show();
                    }
                    aa = new CustomAdapter(ListActivity.this,R.layout.row, stars);

                }
                lv.setAdapter(aa);

            }
        });

        ArrayList<String> al0 = new ArrayList<>();
        al0.add(data.getYear()+"");
        ab = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, al0);
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spyear.setAdapter(ab);

        spyear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String year = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Song> al2;
        DBHelper db = new DBHelper(this);
        al2 = db.getAllSongs();
        al.clear();
        al.addAll(al2);
        aa.notifyDataSetChanged();
    }


}