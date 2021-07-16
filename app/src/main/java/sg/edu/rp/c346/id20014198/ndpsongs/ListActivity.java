package sg.edu.rp.c346.id20014198.ndpsongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    Button btnshowstars;
    Spinner spyear;
    ListView lv;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    int requestCode = 9;

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


        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ListActivity.this, UpdateActivity.class);
                i.putExtra("data", al.get(position));
                startActivityForResult(i, requestCode);
            }
        });


    }
}