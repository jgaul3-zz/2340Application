package edu.gatech.cs2340.cs2340application.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.cs2340application.model.Shelter;

/**
 * Created by charl on 3/6/2018.
 */

public class ShelterModel {
        public static final ShelterModel INSTANCE = new ShelterModel();

        private List<Shelter> items;

        private ShelterModel() {
            items = new ArrayList<>();
        }

        public void addItem(Shelter item) {
            items.add(item);
        }

        public List<Shelter> getItems() {
            return items;
        }

        public Shelter findItemById(int key) {
            for (Shelter d : items) {
                if (d.getKey() == key) return d;
            }
            Log.d("MYAPP", "Warning - Failed to find key: " + key);
            return null;
        }
}
