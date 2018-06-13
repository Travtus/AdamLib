package com.travtusworks.adam.adapters;

import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.travtusworks.adam.AdamActivity;
import com.travtusworks.adam.R;
import com.travtusworks.adam.api.MessageDAO;
import com.travtusworks.adam.utils.AdamListener;

import java.util.ArrayList;

/**
 * Created by teodora on 12.06.2018.
 */

public class AdamAdapter extends RecyclerView.Adapter<AdamAdapter.CustomViewHolder>{

    private String TAG = "AdamAdapter";

    private FragmentActivity context;
    private ArrayList<MessageDAO> messages;
    private int currentUserID;
    private String userLetter;
    private AdamListener listener;

    public AdamAdapter(FragmentActivity context, int currentUserID, String userName, AdamListener listener){
        this.context = context;
        this.messages = new ArrayList<>();
        this.currentUserID = currentUserID;
        char aux = userName.toUpperCase().charAt(0);
        this.userLetter = String.valueOf(aux);
        this.listener = listener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_adam, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        MessageDAO message = messages.get(position);

        if (message.getUserID() != currentUserID){
            //incoming message
            holder.incomingMessageLayout.setVisibility(View.VISIBLE);
            holder.outgoingMessageLayout.setVisibility(View.GONE);
            holder.adamResponse.setText(message.getMessage());

            if (position == messages.size()-1) {

                if (message.getSuggestions() != null && message.getSuggestions().size() > 0) {
                    holder.suggestionsList.setVisibility(View.VISIBLE);
                    SuggestionAdapter adapter = new SuggestionAdapter(context, listener, message.getSuggestions());
                    holder.suggestionsList.setAdapter(adapter);
                } else
                    holder.suggestionsList.setVisibility(View.GONE);

                if (message.getCards() != null && message.getCards().size() > 0){
                    holder.carouselList.setVisibility(View.VISIBLE);
                    CarouselAdapter adapter = new CarouselAdapter(context, listener, message.getCards());
                    holder.carouselList.setAdapter(adapter);
                } else {
                    holder.carouselList.setVisibility(View.GONE);
                }

                if (message.getListCard() != null && message.getListCard().getItems().size() > 0){
                    holder.cardsListLayout.setVisibility(View.VISIBLE);
                    holder.cardsListTitle.setText(message.getListCard().getTitle());
                    CardListAdapter adapter = new CardListAdapter(context, listener, message.getListCard().getItems());
                    holder.cardsListItems.setAdapter(adapter);

                } else {
                    holder.cardsListLayout.setVisibility(View.GONE);
                }

            } else {
                holder.suggestionsList.setVisibility(View.GONE);
                holder.carouselList.setVisibility(View.GONE);
                holder.cardsListLayout.setVisibility(View.GONE);
            }

        } else {
            //outgoing message
            holder.outgoingMessageLayout.setVisibility(View.VISIBLE);
            holder.incomingMessageLayout.setVisibility(View.GONE);
            holder.userIdentifier.setText(userLetter);
            if (message.isImage()){
                holder.adamText.setVisibility(View.GONE);
                holder.adamPhoto.setVisibility(View.VISIBLE);
                Picasso.with(context).load(message.getImageUri())
                        .fit()
                        .centerCrop()
                        .into(holder.adamPhoto);
            } else {
                String messageText = message.getMessage();
                if (messageText.startsWith("![](https://")){
                    holder.adamText.setVisibility(View.GONE);
                    holder.adamPhoto.setVisibility(View.VISIBLE);
                    String url = messageText.substring(4,messageText.length()-1);
                    Picasso.with(context).load(url)
                            .fit()
                            .centerCrop()
                            .placeholder(R.drawable.default_image)
                            .into(holder.adamPhoto);

                } else {
                    holder.adamPhoto.setVisibility(View.GONE);
                    holder.adamText.setVisibility(View.VISIBLE);
                    holder.adamText.setText(messageText);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessage(MessageDAO messageDAO){
        messages.add(messageDAO);
        notifyItemChanged(messages.size()-2, messages.size()-1);
    }

    public void loadMessages(ArrayList<MessageDAO> messages) {
        this.messages.addAll(0,messages);
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout incomingMessageLayout;
        private TextView adamResponse;
        private RecyclerView suggestionsList;
        private RecyclerView carouselList;
        private LinearLayout cardsListLayout;
        private TextView cardsListTitle;
        private RecyclerView cardsListItems;
        private RelativeLayout outgoingMessageLayout;
        private TextView adamText;
        private ImageView adamPhoto;
        private TextView userIdentifier;

        public CustomViewHolder(View itemView) {
            super(itemView);

            incomingMessageLayout = context.findViewById(R.id.incoming_message_layout);
            adamResponse = ((AdamActivity)context).findViewById(R.id.adam_response);
            suggestionsList = context.findViewById(R.id.suggestions_list);
            carouselList = context.findViewById(R.id.carousel_list);
            cardsListLayout = context.findViewById(R.id.cards_list_layout);
            cardsListTitle = context.findViewById(R.id.cards_list_title);
            cardsListItems = context.findViewById(R.id.cards_list_items);
            outgoingMessageLayout = context.findViewById(R.id.outgoing_message_layout);
            adamText = context.findViewById(R.id.adam_text);
            adamPhoto = context.findViewById(R.id.adam_photo);
            userIdentifier = context.findViewById(R.id.user_identifier);

            Typeface sfRegular = Typeface.createFromAsset(context.getAssets(), "fonts/SF-UI-Regular.otf");
            adamResponse.setTypeface(sfRegular);
            adamText.setTypeface(sfRegular);

            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            suggestionsList.setLayoutManager(layoutManager);
            suggestionsList.setItemAnimator(new DefaultItemAnimator());

            LinearLayoutManager layoutManager2
                    = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            carouselList.setLayoutManager(layoutManager2);
            carouselList.setItemAnimator(new DefaultItemAnimator());

            RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(context);
            cardsListItems.setLayoutManager(layoutManager3);
            cardsListItems.setItemAnimator(new DefaultItemAnimator());

        }
    }

}
