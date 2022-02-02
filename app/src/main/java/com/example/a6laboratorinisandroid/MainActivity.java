package com.example.a6laboratorinisandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    TextView textView1;
    View myView;
    String abc = "abc";
    int a;
    int i = 0;
    final Calendar c = Calendar.getInstance();
    int mHour = c.get(Calendar.HOUR);
    int mMinute = c.get(Calendar.MINUTE);
    int mMinutes = mHour * 60 + mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        textView = findViewById(R.id.textView1);
        textView1 = findViewById(R.id.textView2);
        textView2 = findViewById(R.id.textView3);

        registerForContextMenu(textView);
        registerForContextMenu(textView1);

        int i = 0;
        textView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.search:
                break;
            case R.id.refresh:
                finish();
                Toast.makeText(this, "baige",
                        Toast.LENGTH_LONG).show();
            case R.id.Skaiciuoti:
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                int timeDifference = Math.abs(mMinutes - (hourOfDay * 60 + minute));
                                textView2.setText("Skirtumas yra " + mMinutes + " minutes");
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                                builder1.setMessage("Skirtumas yra " + mMinutes + " minutes");
                                builder1.setCancelable(true);
                                AlertDialog alert11 = builder1.create();
                                alert11.show();
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Pasirinkite veiksma");
        menu.add(0, v.getId(), 0, "Simboliu skaicius");
        menu.add(0, v.getId(), 0, "Vardinti po viena");
        a = v.getId();
        myView = v;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Simboliu skaicius") {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            if (findViewById(a) == findViewById(R.id.textView2)) {
                abc = textView1.getText().toString();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage(abc + " turi " + abc.length() + " simboliu ");
                builder1.setCancelable(true);
                AlertDialog alert11 = builder1.create();
                alert11.show();
                textView2.setText(abc + " turi " + abc.length() + " simboliu ");
            } else if (findViewById(a) == findViewById(R.id.textView1)) {
                abc = textView.getText().toString();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage(abc + " turi " + abc.length() + " simboliu ");
                builder1.setCancelable(true);
                AlertDialog alert11 = builder1.create();
                alert11.show();
                textView2.setText(abc + " turi " + abc.length() + " simboliu ");
            }

        } else if (item.getTitle() == "Vardinti po viena") {
            if (findViewById(a) == findViewById(R.id.textView2)) {
                abc = textView1.getText().toString();
                startTimerThread();
            } else if (findViewById(a) == findViewById(R.id.textView1)) {
                abc = textView.getText().toString();
                startTimerThread();
            }

        } else {
            return false;
        }
        return true;
    }

    private void startTimerThread() {
        Thread th = new Thread(new Runnable() {

            public void run() {
                while (i != abc.length()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView2.setText(abc.valueOf(abc.charAt(i)));
                            i++;
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                i = 0;
            }
        });
        th.start();
    }
}