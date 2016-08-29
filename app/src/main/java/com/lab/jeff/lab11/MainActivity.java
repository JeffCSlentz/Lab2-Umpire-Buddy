package com.lab.jeff.lab11;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final private String TAG = "Count Much More";

    private int strikes = 0;
    private int balls = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "Starting onCreate...");


        setContentView(R.layout.activity_main);


        View strikeButton = findViewById(R.id.strike_button);
        strikeButton.setOnClickListener(this);

        View ballButton = findViewById(R.id.ball_button);
        ballButton.setOnClickListener(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updateCounts();
    }

    private void updateCounts() {
       updateStrikeCount();
       updateBallCount();
    }
    private void updateStrikeCount() {
        TextView t = (TextView)findViewById(R.id.strike_count);
        t.setText(Integer.toString(strikes));
    }
    private void updateBallCount() {
        TextView t = (TextView)findViewById(R.id.ball_count);
        t.setText(Integer.toString(balls));
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.strike_button:
                strikes++;
                if (strikes > 2){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Batter out! New batter up.");
                    builder.setCancelable(false);
                    builder.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            strikes = 0;
                            balls = 0;
                            updateCounts();
                        }
                    });
                    builder.show();
                }

                break;
        }
        switch (v.getId()){
            case R.id.ball_button:
                balls++;

                if (balls > 3){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Batter walks. New batter up.");
                    builder.setCancelable(false);
                    builder.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            strikes = 0;
                            balls = 0;
                            updateCounts();
                        }
                    });
                    builder.show();
                }
                break;
        }

        updateCounts();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
