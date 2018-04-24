package edu.gatech.cs2340.cs2340application.controllers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.DatabaseTools;
import edu.gatech.cs2340.cs2340application.model.FirebaseTools;
import edu.gatech.cs2340.cs2340application.model.Model;
import edu.gatech.cs2340.cs2340application.model.Shelter;

/**
 * An activity representing a single Shelter detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ShelterListActivity}.
 */
public class ShelterDetailActivity extends AppCompatActivity {
    Model model = Model.getInstance();

    TextView name;
    TextView shelterAddress;
    TextView capacity;
    TextView vacancy;
    TextView gender;
    TextView latitude;
    TextView longitude;
    TextView phoneNumber;
    EditText numberOfBeds;
    //FloatingActionButton bedReservationButton;
    Button cancelReservation;

    DatabaseTools tools;
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_detail);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        tools = new FirebaseTools();

        FloatingActionButton bedReservationButton = findViewById(R.id.fab);

        name = findViewById(R.id.nameDetailLabel);
        shelterAddress = findViewById(R.id.addressDetailLabel);
        capacity = findViewById(R.id.capacityDetailLabel);
        vacancy = findViewById(R.id.vacancyDetailLabel);
        gender = findViewById(R.id.genderDetailLabel);
        latitude = findViewById(R.id.latitudeDetailLabel);
        longitude = findViewById(R.id.longitudeDetailLabel);
        phoneNumber = findViewById(R.id.phoneNumberDetailLabel);


        final Shelter currentShelter = model.findShelterById(model.getCurrentShelterId());
        if (currentShelter != null) {
            name.setText("Name: " + currentShelter.getName());
            capacity.setText("Capacity: " + currentShelter.getCapacity());
            vacancy.setText("Open Beds: "  + currentShelter.getVacancy());
            gender.setText("Restrictions: " + currentShelter.getRestrictions());
            longitude.setText("Longitude: " + currentShelter.getLongitude());
            latitude.setText("Latitude: " + currentShelter.getLatitude());
            shelterAddress.setText("Address: " + currentShelter.getAddress());
            phoneNumber.setText("Phone Number: " + currentShelter.getPhoneNumber());

        }

        numberOfBeds = findViewById(R.id.number_of_beds);
        cancelReservation = findViewById(R.id.cancel_Button);

            bedReservationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (numberOfBeds.getText().length() > 0) {

                        int inputBeds = Integer.parseInt(numberOfBeds.getText().toString());

                        int curVacancies = currentShelter.getVacancy();
                        if (curVacancies >= inputBeds) {
                            currentShelter.setOccupancy(currentShelter.getOccupancy() + inputBeds);
                            vacancy.setText("Vacancies: " + currentShelter.getVacancy());
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    inputBeds + " reservation(s) made!",
                                    Toast.LENGTH_LONG);
                            toast.show();
                        }  else {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Sorry, there are " + curVacancies + " vacancies at this time.",
                                    Toast.LENGTH_LONG);
                            toast.show();
                        }
                        tools.updateShelter(currentShelter);
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Please enter the number of beds you need",
                                Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });

            cancelReservation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        int currNumOfBeds = numberOfBeds.getText().toString().equals("")
                                ? 0 : Integer.parseInt(numberOfBeds.getText().toString());
                        if (currNumOfBeds <= currentShelter.getOccupancy()) {
                            currentShelter.setOccupancy(
                                    currentShelter.getOccupancy() - currNumOfBeds);
                        }
                        vacancy.setText("Vacancies: " + currentShelter.getVacancy());
                        tools.updateShelter(currentShelter);
                }
            });


    }
}
