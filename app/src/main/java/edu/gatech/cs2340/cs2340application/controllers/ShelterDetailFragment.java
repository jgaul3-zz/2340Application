package edu.gatech.cs2340.cs2340application.controllers;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.gatech.cs2340.cs2340application.model.Model;
import edu.gatech.cs2340.cs2340application.model.Shelter;


/**
 * A fragment representing a single Shelter detail screen.
 * This fragment is either contained in a {@link ShelterListActivity}
 * in two-pane mode (on tablets) or a {@link ShelterDetailActivity}
 * on handsets.
 */
public class ShelterDetailFragment extends Fragment {
//
//    public static final String ARG_SHELTER_ID = "shelter_id";
//
//    private Shelter mShelter;
//
//    private SimpleShelterRecyclerViewAdapter adapter;
//
//    public ShelterDetailFragment() {
//
//    }
//
//    @Override void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //Check if we got a valid course passed to us
//        if (getArguments().containsKey(ARG_SHELTER_ID)) {
//            // Get the id from the intent arguments (bundle) and
//            //ask the model to give us the course object
//            Model model = Model.getInstance();
//            // mCourse = model.getCourseById(getArguments().getInt(ARG_COURSE_ID));
//            mShelter = model.getCurrentShelter();
//            Log.d("CourseDetailFragment", "Passing over shelter: " + mShelter);
//
//            Activity activity = this.getActivity();
//
//            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
//            if (appBarLayout != null) {
//                appBarLayout.setTitle(mShelter.toString());
//            }
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.shelter_detail, container, false);
//
//        //Step 1.  Setup the recycler view by getting it from our layout in the main window
//        View recyclerView = rootView.findViewById(R.id.shelter_list);
//        assert recyclerView != null;
//        //Step 2.  Hook up the adapter to the view
//        setupRecyclerView((RecyclerView) recyclerView);
//
//        return rootView;
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        adapter.notifyDataSetChanged();
//    }
//
//

//    /**
//     * The fragment argument representing the item ID that this fragment
//     * represents.
//     */
//    public static final String ARG_ITEM_ID = "item_id";
//
//    /**
//     * The dummy content this fragment is presenting.
//     */
//    private Shelter mShelter;
//
//    /**
//     * Mandatory empty constructor for the fragment manager to instantiate the
//     * fragment (e.g. upon screen orientation changes).
//     */
//    public ShelterDetailFragment() {
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (getArguments().containsKey(ARG_ITEM_ID)) {
//            // Load the dummy content specified by the fragment
//            // arguments. In a real-world scenario, use a Loader
//            // to load content from a content provider.
//            Model model = Model.getInstance();
//            mShelter = model.getCurrentShelter();
//
//            Activity activity = this.getActivity();
//            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
//            if (appBarLayout != null) {
//                appBarLayout.setTitle(mShelter.toString());
//            }
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.shelter_detail, container, false);
//
//        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.shelter_detail)).setText(mItem.details);
//        }
//
//        return rootView;
//    }
}
