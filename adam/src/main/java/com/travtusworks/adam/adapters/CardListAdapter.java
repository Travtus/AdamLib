package com.travtusworks.adam.adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.travtusworks.adam.R;
import com.travtusworks.adam.api.ListCardItemDAO;
import com.travtusworks.adam.utils.AdamListener;

import java.util.ArrayList;

/**
 * Created by teodora on 24.04.2018.
 */

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CustomViewHolder>{

    private String TAG = "CardListAdapter";

    private FragmentActivity context;
    private ArrayList<ListCardItemDAO> items;
    private AdamListener listener;

    public CardListAdapter(FragmentActivity context, AdamListener listener, ArrayList<ListCardItemDAO> items){
        this.context = context;
        this.listener = listener;
        this.items = items;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_list, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        if (!items.get(position).getImage().equals("")) {
            Picasso.with(context).load(items.get(position).getImage())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.default_image)
                    .into(holder.image);
        }

        holder.title.setText(items.get(position).getTitle());
        holder.text.setText(items.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView image;
        private TextView title;
        private TextView text;

        public CustomViewHolder(View itemView) {
            super(itemView);

            image = context.findViewById(R.id.image);
            title = context.findViewById(R.id.title);
            text = context.findViewById(R.id.text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.sendMessage(items.get(getAdapterPosition()).getInteractionText());
        }
    }

}
