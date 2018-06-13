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
import com.travtusworks.adam.api.CardDAO;
import com.travtusworks.adam.utils.AdamListener;

import java.util.ArrayList;

/**
 * Created by teodora on 23.04.2018.
 */

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CustomViewHolder> {

    private String TAG = "CarouselAdapter";

    private FragmentActivity context;
    private ArrayList<CardDAO> cards;
    private AdamListener listener;

    public CarouselAdapter(FragmentActivity context, AdamListener listener, ArrayList<CardDAO> cards){
        this.context = context;
        this.listener = listener;
        this.cards = cards;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_carousel, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Picasso.with(context).load(cards.get(position).getImage())
                .fit()
                .centerCrop()
                .placeholder(R.drawable.default_image)
                .into(holder.cardImage);

        holder.cardTitle.setText(cards.get(position).getTitle());
        holder.cardText.setText(cards.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView cardImage;
        private TextView cardTitle;
        private TextView cardText;

        public CustomViewHolder(View itemView) {
            super(itemView);

            cardImage = context.findViewById(R.id.card_image);
            cardTitle = context.findViewById(R.id.card_title);
            cardText = context.findViewById(R.id.card_text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            listener.sendMessage(cards.get(getAdapterPosition()).getInteractionText());

        }
    }



}
