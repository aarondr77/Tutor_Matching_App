package com.example.a350project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MarketplaceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MarketplaceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarketplaceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static Context context;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MarketplaceListFragment marketplaceList;

    public MarketplaceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Complaints.
     */
    // TODO: Rename and change types and number of parameters
    public static MarketplaceFragment newInstance(String param1, String param2, Context context) {
        MarketplaceFragment fragment = new MarketplaceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        context = context;
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_marketplace, container, false);

        final EditText searchInput = (EditText) v.findViewById(R.id.SearchText);

        Button searchButton = (Button) v.findViewById(R.id.Button_Search_Marketplace);
        searchButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String searchString = searchInput.getText().toString();
                MarketplaceFunctions.onSearchButtonClick(v, searchString);
                LinkedList<SessionObject> foundSessions = MarketplaceFunctions.getFoundSessions();
                if (foundSessions.size() > 0 ) {
                    insertNestedFragment();
                    marketplaceList.updateSessions();
                } else {
                    showUnclaimedSubjectsDialog(v.getContext(), MarketplaceFunctions.getAllSubjects());
                }
            }
        });
        return v;

    }

    private void showUnclaimedSubjectsDialog(Context c, LinkedList<String> subjects) {

        LayoutInflater factory = LayoutInflater.from(c);
        final View textEntryView = factory.inflate(R.layout.opencourses_popup, null);
        //text_entry is an Layout XML file containing two text field to display in alert dialog
        final TextView allSubjects = textEntryView.findViewById(R.id.availableCourses);

        String subjectsText = "";
        Log.d("SUBJECT SIZE", "SIZE: " + subjects.size());
        for (int i = 0; i < subjects.size(); i++) {
            subjectsText += subjects.get(i) + "\n\n";
        }

        Log.d("SUBJECTS", subjectsText);

        allSubjects.setText(subjectsText);
        allSubjects.setGravity(Gravity.CENTER);

        final AlertDialog.Builder alert = new AlertDialog.Builder(c);

        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("No Matches!")
                .setView(textEntryView)
                .setNegativeButton("Back To Search", null)
                .create();
        dialog.show();
    }

    private void insertNestedFragment() {
        Fragment childFragment = MarketplaceListFragment.newInstance(1);
        marketplaceList = (MarketplaceListFragment)childFragment;
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.marketplace_child_fragment_container, childFragment).addToBackStack(null).commit();
        Log.d("Loading Child", "Here");
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
