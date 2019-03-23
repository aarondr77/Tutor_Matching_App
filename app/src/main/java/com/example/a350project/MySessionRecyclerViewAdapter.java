package com.example.a350project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.a350project.SessionListFragment.OnListFragmentInteractionListener;
import com.example.a350project.dummy.DummyContent.DummyItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MySessionRecyclerViewAdapter extends RecyclerView.Adapter<MySessionRecyclerViewAdapter.MyViewHolder> {

    private final List<SessionObject> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView Subject;
        public TextView Time;
        public TextView Tutor;
        public TextView Duration;
        public Button rateButton;
        public SessionObject mItem;
        public MyViewHolder(View v) {
            super(v);
            view = v;
            Tutor = (TextView) view.findViewById(R.id.Tutor);
            Time = (TextView) view.findViewById(R.id.Time);
            Duration = (TextView) view.findViewById(R.id.Duration);
            Subject = (TextView) view.findViewById(R.id.Subject);
            rateButton = (Button) view.findViewById(R.id.Rate);


        }
        /*
        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
        */
    }


    public MySessionRecyclerViewAdapter(List<SessionObject> items, OnListFragmentInteractionListener listener, Context context) {
        mValues = items;
        mListener = listener;
        this.context = context;
        Log.d("CALLED", "CALLED MySessionRecyclerViewAdapter");

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_session_objects_new, parent, false);
        return new MyViewHolder(view);
    }

    private void showRateDialog(final Context c, final String ratedPersonEmail) {
        LayoutInflater factory = LayoutInflater.from(c);
        final View ratingView = factory.inflate(R.layout.rate_popup, null);
        //text_entry is an Layout XML file containing two text field to display in alert dialog
        final RatingBar rating = (RatingBar) ratingView.findViewById(R.id.ratingBar2);

        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Rate this Session:")
                .setView(ratingView)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        JSONObject newUser = DataManagement.addRating(ratedPersonEmail, rating.getRating());
                        try{
                            Double newRating = newUser.getDouble("rating");
                            Double newRateTotal = newUser.getDouble("rateTotal");
                            Integer newRateNum = newUser.getInt("rateNum");
                            DataManagement.updateRating(ratedPersonEmail, newRating, newRateTotal, newRateNum, c);
                        } catch(JSONException e) {
                            Log.e("jsonerror", e.getMessage());
                        }
                    }


                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        holder.Tutor.setText(mValues.get(position).getTutor());
        final String tutorEmail = mValues.get(position).getTutorEmail();
        final String studentEmail = mValues.get(position).getStudentEmail();
        String positionTitle = "tutor";
        try{
            positionTitle = ProfilePageFragment.currUser.getString("userType");
            Log.d("position title", positionTitle);
            if(positionTitle.equals("tutor")) {
                holder.Tutor.setText(mValues.get(position).getStudent());
            }
        } catch(JSONException e) {
            Log.e("jsonerror", e.getMessage());
        }
        holder.Subject.setText(mValues.get(position).getSubject());
        holder.Time.setText(mValues.get(position).getDate());
        holder.Duration.setText(mValues.get(position).getDuration());

/*
        final String descr = mValues.get(position).getDescription();
        holder.dscrButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder ad = new AlertDialog.Builder(context);
            ad.setTitle("Description");
            ad.setMessage(descr);
            AlertDialog dialog = ad.create();
            ad.show();
        }
*/

        final String finalPositionTitle = positionTitle;
        holder.rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ratedEmail;
                if(finalPositionTitle.equals("tutor")) {
                    ratedEmail = studentEmail;
                } else {
                    ratedEmail = tutorEmail;
                }
                showRateDialog(v.getContext(), ratedEmail);
            }
        });


        String pos = Integer.toString(position);
        Log.d("ADAPTER", pos);
        Log.d("ADAPTER", "Binding");
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }
/*
    public void updateData(List<ComplaintsObject> newList) {
        mValues.clear();
        mValues.addAll(newList);
        this.notifyDataSetChanged();
    }
*/
}
