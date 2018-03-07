package edu.gatech.cs2340.cs2340application.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.*;



/**
 * Created by charl on 3/6/2018.
 */

public class LoadDataActivity extends AppCompatActivity {
    public static String TAG = "MY_APP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    /**
     * Button handler for the load button
     *
     * @param view  the actual button object that was pressed
     */
    public void onLoadButtonPressed(View view) {
        Log.v(LoadDataActivity.TAG, "Pressed the load button");
        readSDFile();
        Intent intent = new Intent(this, DataItemListActivity.class);
        startActivity(intent);
    }
    public static final int NAME_POSITION = 0;
    /**
     * Open the sample.csv file in the /res/raw directory
     * Line Entry format:
     *   [0] - name
     *   [1] - id as a string
     *
     */
    private void readSDFile() {
        ShelterModel model = ShelterModel.INSTANCE;

        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.homeless_shelter_database);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                Log.d(LoadDataActivity.TAG, line);
                String[] tokens = line.split(",");
                int id = Integer.parseInt(tokens[1]);
                model.addItem(new Shelter(tokens[NAME_POSITION], tokens[2], id, tokens[3]));
            }
            br.close();
        } catch (IOException e) {
            Log.e(LoadDataActivity.TAG, "error reading assets", e);
        }

    }

}
