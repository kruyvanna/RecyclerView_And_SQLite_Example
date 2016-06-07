package com.vanna.recyvlerviewexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kruyvanna on 6/7/16.
 */
public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private List<DictionaryItem> mDictionaryItems;

    // Pass in the contact array into the constructor
    public WordAdapter(List<DictionaryItem> dictionaryItemList) {
        mDictionaryItems = dictionaryItemList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView wordTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            wordTextView = (TextView) itemView.findViewById(R.id.wordTextView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View rowView = inflater.inflate(R.layout.item_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the data model based on position
        DictionaryItem dictionaryItem = mDictionaryItems.get(position);

        // Set item views based on the data model
        TextView textView = holder.wordTextView;
        textView.setText(dictionaryItem.getWord());
    }

    @Override
    public int getItemCount() {
        return mDictionaryItems.size();
    }
}
