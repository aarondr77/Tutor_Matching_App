package com.example.a350project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ComplaintsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComplaintsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComplaintsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ComplaintsListFragment complaintsList;

    public ComplaintsFragment() {
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
    public static ComplaintsFragment newInstance(String param1, String param2) {
        ComplaintsFragment fragment = new ComplaintsFragment();
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

        ComplaintsFunctions.loadComplaints();
        insertNestedFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_complaints, container, false);

        Button btn = (Button) v.findViewById(R.id.launchComplaintButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddItemDialog(v.getContext());
            }
        });

        return v;
    }


    private void showAddItemDialog(Context c) {

        LayoutInflater factory = LayoutInflater.from(c);
        final View textEntryView = factory.inflate(R.layout.complaint_entry_popup, null);
        //text_entry is an Layout XML file containing two text field to display in alert dialog
        final EditText content = (EditText) textEntryView.findViewById(R.id.enterContent);
        final EditText target = (EditText) textEntryView.findViewById(R.id.enterTarget);
        content.setText("example@sample.com");
        target.setText(("I think..."));
        final AlertDialog.Builder alert = new AlertDialog.Builder(c);

        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Submit Complaint")
                .setView(textEntryView)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ComplaintsFunctions.addComplaint(MainActivity.currentUserEmail,
                                String.valueOf(content.getText()), String.valueOf(target.getText()));

                        insertNestedFragment();
                    }


                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    private void insertNestedFragment() {
        Fragment childFragment = ComplaintsListFragment.newInstance(1);
        complaintsList = (ComplaintsListFragment)childFragment;
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.complaint_child_fragment_container, childFragment).addToBackStack(null).commit();
        Log.d("Loading Child", "Here");
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.                        (ComplaintsListFragment)complaintsList.                        (ComplaintsListFragment)complaintsList.
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
