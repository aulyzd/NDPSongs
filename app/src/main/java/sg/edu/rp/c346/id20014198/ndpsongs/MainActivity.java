package sg.edu.rp.c346.id20014198.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvTitle, tvSinger, tvYear, tvStar;
    EditText etTitle, etSinger, etYear;
    RadioGroup rgStars;
    RadioButton rb1,rb2,rb3,rb4,rb5;
    Button btninsert, btnshow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        tvSinger = findViewById(R.id.tvSingers);
        tvYear = findViewById(R.id.tvYear);
        tvStar = findViewById(R.id.tvStars);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        btninsert = findViewById(R.id.btnInsert);
        btnshow = findViewById(R.id.btnShow);
        rgStars = findViewById(R.id.rgStars);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);


        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                String stryear = etYear.getText().toString();
                int year = Integer.valueOf(stryear);
                int stars = getStars();

                DBHelper dbh = new DBHelper(MainActivity.this);
                rgStars.clearCheck();
                etTitle.setText("");
                etSinger.setText("");
                etYear.setText("");
                dbh.insertSong(title, singer, year, stars);

            }
        });

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);
            }
        });



    }
    private int getStars() {
        int stars = 1;
        switch (rgStars.getCheckedRadioButtonId()) {
            case R.id.rb1:
                stars = 1;
                break;
            case R.id.rb2:
                stars= 2;
                break;
            case R.id.rb3:
                stars= 3;
                break;
            case R.id.rb4:
                stars= 4;
                break;
            case R.id.rb5:
                stars= 5;
                break;
        }
        return stars;
    }
}