package sg.edu.rp.c346.id20014198.ndpsongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    TextView tvSongID, tvTitle, tvSinger, tvYear, tvStar;
    EditText etSongID, etTitle, etSinger, etYear;
    RadioGroup rgStars;
    RadioButton rb1,rb2,rb3,rb4,rb5;
    Button btnupdate, btndelete, btncancel;
    Song data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        tvSongID = findViewById(R.id.tvSongID);
        tvTitle = findViewById(R.id.tvTitle);
        tvSinger = findViewById(R.id.tvSingers);
        tvYear = findViewById(R.id.tvYear);
        tvStar = findViewById(R.id.tvStars);
        etSongID = findViewById(R.id.etSongID);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        btnupdate = findViewById(R.id.btnInsert);
        btndelete = findViewById(R.id.btnShow);
        btncancel = findViewById(R.id.btnCancel);
        rgStars = findViewById(R.id.rgStars);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        Intent i = getIntent();
        final Song currentdata = (Song) i.getSerializableExtra("data");


        etSongID.setText(currentdata.get_id()+"");
        etTitle.setText(currentdata.getTitle());
        etSinger.setText(currentdata.getSingers());
        etYear.setText(currentdata.getYear()+"");
        switch (currentdata.getStars()){
            case 5: rb5.setChecked(true);
            break;
            case 4: rb4.setChecked(true);
            break;
            case 3: rb3.setChecked(true);
            break;
            case 2: rb2.setChecked(true);
            break;
            case 1: rb1.setChecked(true);
            break;
        }
        etSongID.setEnabled(false);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(UpdateActivity.this);
                currentdata.setTitle(etTitle.getText().toString());
                currentdata.setSingers(etSinger.getText().toString());
                int updateyear = Integer.valueOf(etYear.getText().toString());
                currentdata.setYear(updateyear);

                int newselectedrb = rgStars.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(newselectedrb);
                currentdata.setStars(Integer.parseInt(rb.getText().toString()));

                int result = dbh.updateSong(currentdata);
                if (result>0) {
                    Toast.makeText(UpdateActivity.this, "Song Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(UpdateActivity.this, "Song Update fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(UpdateActivity.this);
                dbh.deleteSong(currentdata.get_id());
                Intent intent = new Intent();
                intent.putExtra("key", "value");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
