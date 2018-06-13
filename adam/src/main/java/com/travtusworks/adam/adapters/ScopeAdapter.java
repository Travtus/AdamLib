package com.travtusworks.adam.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travtusworks.adam.R;
import com.travtusworks.adam.api.ScopeItem;
import com.travtusworks.adam.utils.AdamListener;

import java.util.ArrayList;

/**
 * Created by teodora on 23.04.2018.
 */

public class ScopeAdapter extends RecyclerView.Adapter<ScopeAdapter.CustomViewHolder> {

    private FragmentActivity context;
    private AdamListener listener;
    private ArrayList<ScopeItem> items;

    private Typeface robotoLight, robotoBold;
    private ScopeItem selectedItem;

    public ScopeAdapter(FragmentActivity context, AdamListener listener, ArrayList<ScopeItem> items) {
        this.context = context;
        this.listener = listener;
        this.items = items;

        robotoLight = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
        robotoBold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selector, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        ScopeItem scope = items.get(position);
        holder.text.setText(scope.getName());

        if (scope.isSelected()){
            holder.text.setTextColor(context.getResources().getColor(R.color.blue_travtus));
            holder.text.setTypeface(robotoBold);
        } else {
            holder.text.setTextColor(context.getResources().getColor(R.color.grey_text));
            holder.text.setTypeface(robotoLight);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<ScopeItem> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public ScopeItem getSelectedItem(){
        return selectedItem;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView text;

        public CustomViewHolder(View itemView) {
            super(itemView);

            text = context.findViewById(R.id.text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            for (int i = 0; i < items.size(); i++){
                if (i == getAdapterPosition()) {
                    selectedItem = items.get(i);
                    items.get(i).setSelected(true);
                }
                else
                    items.get(i).setSelected(false);
            }
            notifyDataSetChanged();

        }
    }

}
