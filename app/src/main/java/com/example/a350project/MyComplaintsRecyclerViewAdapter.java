package com.example.a350project;

import android.arch.lifecycle.ViewModel;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a350project.ComplaintsListFragment.OnListFragmentInteractionListener;
import com.example.a350project.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyComplaintsRecyclerViewAdapter extends RecyclerView.Adapter<MyComplaintsRecyclerViewAdapter.MyViewHolder> {

    private final List<ComplaintsObject> mValues;
    private final OnListFragmentInteractionListener mListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView statusView;
        public TextView contentView;
        public TextView targetView;
        public ComplaintsObject mItem;
        public MyViewHolder(View v) {
            super(v);
            view = v;
            statusView = (TextView) view.findViewById(R.id.status);
            contentView = (TextView) view.findViewById(R.id.content);
            targetView = (TextView) view.findViewById(R.id.target);
        }
        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }



    public MyComplaintsRecyclerViewAdapter(List<ComplaintsObject> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_complaints_objects, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.statusView.setText(mValues.get(position).getStatus());
        if (mValues.get(position).getStatus().equals("Approved"))
            holder.statusView.setTextColor(Color.parseColor("green"));
        else if (mValues.get(position).getStatus().equals("Denied"))
            holder.statusView.setTextColor(Color.parseColor("red"));
        holder.contentView.setText(mValues.get(position).getContent());
        holder.targetView.setText(mValues.get(position).getTarget());
        Log.d("ADAPTER", "Binding");
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateData(List<ComplaintsObject> newList) {
        mValues.clear();
        mValues.addAll(newList);
        this.notifyDataSetChanged();
    }

}
