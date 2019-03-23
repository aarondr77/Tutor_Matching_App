package com.example.a350project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfilePageFragment OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfilePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilePageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public static JSONObject currUser;
    private SessionListFragment mySessions;


    public ProfilePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilePageFragment newInstance(String param1, String param2) {
        ProfilePageFragment fragment = new ProfilePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_page, container, false);

        // Set currUser field
        findCurrentUser();

        // Set values of all views related to current user
        final TextView userBalance = (TextView) v.findViewById(R.id.balanceView);
        final TextView userName = (TextView) v.findViewById(R.id.nameView);
        final TextView userRating = (TextView) v.findViewById(R.id.ratingView);
        final TextView userPosition = (TextView) v.findViewById(R.id.positionView);
        final TextView userAvgCost = (TextView) v.findViewById(R.id.avgCostView);
        Log.d("user", currUser.toString());
        String currUserPosition = "student";
        try {
            userBalance.setText("Balance: " + currUser.getDouble("balance"));
            userName.setText("Name: " + currUser.getString("firstName") + " " + currUser.getString("lastName"));
            userPosition.setText("Position: " + currUser.getString("userType"));
            userRating.setText("Rating: " + currUser.getDouble("tutorRating"));
            userAvgCost.setText("Average Session Cost: " + currUser.getDouble("avgCost"));
            currUserPosition = currUser.getString("userType");
            Log.d("curr pos in try is>>>>", currUserPosition);
        } catch(JSONException e) {
            Log.e("tag4", "Error getting user info");
        }
        Log.d("curr pos is>>>>", currUserPosition);
        if(currUserPosition.equals("tutor")) {
            ((TextView) v.findViewById(R.id.textView16)).setText("Student");
        }


        return v;
    }

    public void findCurrentUser() {
        currUser = DataManagement.findUser(MainActivity.currentUserEmail);
    }

    private void insertNestedFragment() {
        Fragment childFragment = SessionListFragment.newInstance(1);
        mySessions = (SessionListFragment)childFragment;
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.mySessionsContainer, childFragment).addToBackStack(null).commit();
        Log.d("Loading Child", "Here");
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        insertNestedFragment();
    }

}
