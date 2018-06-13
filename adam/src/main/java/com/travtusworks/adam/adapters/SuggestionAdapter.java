package com.travtusworks.adam.adapters;

import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travtusworks.adam.R;
import com.travtusworks.adam.utils.AdamListener;

import java.util.ArrayList;

/**
 * Created by teodora on 20.04.2018.
 */

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.CustomViewHolder>{

    private String TAG = "SuggestionAdapter";

    private FragmentActivity context;
    private ArrayList<String> suggestions;
    private AdamListener listener;

    public SuggestionAdapter(FragmentActivity context, AdamListener listener, ArrayList<String> suggestions){
        this.context = context;
        this.listener = listener;
        this.suggestions = suggestions;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_suggestion, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.suggestion.setText(suggestions.get(position));

    }

    @Override
    public int getItemCount() {
        return suggestions.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView suggestion;

        public CustomViewHolder(View itemView) {
            super(itemView);

            suggestion = context.findViewById(R.id.suggestion);

            Typeface robotoLight = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
            suggestion.setTypeface(robotoLight);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            listener.sendMessage(suggestions.get(getAdapterPosition()));

        }
    }
}
