package com.travtusworks.adam.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
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

import com.travtusworks.adam.R;
import com.travtusworks.adam.api.MessageDAO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
            adamResponse = context.findViewById(R.id.adam_response);
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
