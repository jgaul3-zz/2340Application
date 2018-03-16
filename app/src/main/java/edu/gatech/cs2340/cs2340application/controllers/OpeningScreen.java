package edu.gatech.cs2340.cs2340application.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.*;

/** Top level window.
 *
 * Directs the user to either the login screen or the guest screen.
 *
 */
public class OpeningScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);

        //Prep buttons
        Button userButton = findViewById(R.id.loginUser);
        Button guestButton = findViewById(R.id.loginGuest);

        //Loads in CSV data
        readSDFile();

        userButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("test", "User Button Pressed.");

                Intent toLogin = new Intent(OpeningScreen.this, RegistrationScreen.class);
                OpeningScreen.this.startActivity(toLogin);
            }
        }); 

        guestButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("test", "Guest Button Pressed.");

                Intent toApp = new Intent(OpeningScreen.this, AppScreen.class);
                OpeningScreen.this.startActivity(toApp);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //Log.v(LoadDataActivity.TAG, "Pressed the load button");

    }

    /**
     * Open the sample.csv file in the /res/raw directory
     * Line Entry format:
     *   [0] - name
     *   [1] - id as a string
     *
     */
    private void readSDFile() {

        try {
            Model model = Model.getInstance();

            // Opens a file using a Scanner object
            Scanner scanner = new Scanner(getResources().openRawResource(R.raw.homeless_shelter_database));

            //Skips over the first line
            scanner.nextLine();


            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());

                int key = Integer.parseInt(line.get(0));
                Log.e("id", line.get(0));

                String name = line.get(1);
                Log.e("name", line.get(1));

                String capacity = line.get(2);
                Log.e("capacity", line.get(2));

                //restrictions is 3 (string)
                double longitude = Double.parseDouble(line.get(4));
                Log.e("longitude", line.get(4));

                double latitude = Double.parseDouble(line.get(5));
                Log.e("latitude", line.get(5));

                String address = line.get(6);
                Log.e("address", line.get(6));

                String phoneNumber = line.get(8);
                Log.e("phoneNumber", line.get(8));

                String notes = line.get(7);
                Log.e("notes", line.get(7));

                Shelter rick = new Shelter(key, name, capacity, latitude, longitude, address, phoneNumber, notes);

                rick.setRestrictions(parseLine(line.get(3), '/', 'w'));
                model.addShelter(rick);
            }
            scanner.close();
//            //Open a stream on the raw file
//            InputStream is = getResources().openRawResource(R.raw.homeless_shelter_database);
//            //From here we probably should call a model method and pass the InputStream
//            //Wrap it in a BufferedReader so that we get the readLine() method
//            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

//            String line;
//            br.readLine(); //get rid of header line



//            while ((line = br.readLine()) != null) {
//                //Log.d(LoadDataActivity.TAG, line);
//                String[] tokens = line.split(",");
//                int key = Integer.parseInt(tokens[0]);
//                String name = tokens[1];
//                int capacity = Integer.parseInt(tokens[2]);
//                //restrictions is 3 (string)
//                double longitude = Double.parseDouble(tokens[4]);
//                double latitude = Double.parseDouble(tokens[5]);
//                //address is 6
//                String address = tokens[6];
//                String phoneNumber = tokens[7];
//                String notes = tokens[8];
//                //special notes is 7
//                //phone number is 8
//                Shelter rick = new Shelter(key, name, capacity, latitude, longitude, address, phoneNumber, notes);//key, tokens[1], capacity, latitude, longitude, tokens[5], tokens[6], tokens[7]);
//                model.addShelter(rick);
//                //System.out.println(rick);
//                //Log.e("Yes", rick.toString());
//            }
//            br.close();
        } catch (Exception e) {
            Log.e("dopnt", "error reading assets");
        }

    }

    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, ',', '"');
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }
}