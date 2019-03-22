package com.example.a350project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;


import com.example.a350project.MarketplaceListFragment.OnListFragmentInteractionListener;
import com.example.a350project.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMarketplaceRecyclerViewAdapter extends RecyclerView.Adapter<MyMarketplaceRecyclerViewAdapter.MyViewHolder> {

    private final List<SessionObject> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView Tutor;
        public TextView Time;
        public TextView Price;
        public TextView Duration;
        public Button ClaimButton;
        public SessionObject mItem;
        public MyViewHolder(View v) {
            super(v);
            view = v;
            Tutor = (TextView) view.findViewById(R.id.Tutor);
            Time = (TextView) view.findViewById(R.id.Time);
            Duration = (TextView) view.findViewById(R.id.Duration);
            Price = (TextView) view.findViewById(R.id.Price);
            ClaimButton = (Button) view.findViewById(R.id.Claim);

        }
        /*
        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
        */
    }


    public MyMarketplaceRecyclerViewAdapter(List<SessionObject> items, OnListFragmentInteractionListener listener, Context context) {
        mValues = items;
        mListener = listener;
        this.context = context;
        Log.d("CALLED", "CALLED MyMarketplaceRecyclerViewAdapter");

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_marketplace_objects_new, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        holder.Tutor.setText(mValues.get(position).getTutor());
        holder.Price.setText(mValues.get(position).getPrice());
        holder.Time.setText(mValues.get(position).getDate());
        holder.Duration.setText(mValues.get(position).getDuration());
        final String sessionID = mValues.get(position).getSessionID();
        final double price = Double.parseDouble(mValues.get(position).getPrice());

        holder.ClaimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double userBalance = MarketplaceFunctions.getBalance(MainActivity.currentUserEmail);
                // I NEED TO GET THE EMAIL ADDRESS OF THE TUTOR AND UPDATE HIS BALANCE ALSO!!
                if (userBalance >= price) {
                    DataManagement.updateBalance(MainActivity.currentUserEmail, userBalance - price, context);
                    Log.i("CLAIM CLICKED", sessionID);
                    SessionFunctions.claimSession(sessionID);
                    holder.ClaimButton.setClickable(false);
                    holder.ClaimButton.setTextSize(15);
                    holder.ClaimButton.setBackgroundColor(Color.rgb(0,153,0));
                    holder.ClaimButton.setText(":)");
                } else {
                    Toast.makeText(context, "Insufficient funds in account", Toast.LENGTH_SHORT).show();
                }
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

}
