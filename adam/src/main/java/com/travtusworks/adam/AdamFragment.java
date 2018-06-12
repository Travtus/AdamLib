package com.travtusworks.adam;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travtusworks.adam.utils.AdamListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by teodora on 11.06.2018.
 */

public class AdamFragment extends Fragment {

    private static final String TAG = "AdamFragment";

    private AdamListener listener;

    private TextView adamFirst;
    private TextView adamSecond;
    private TextView bookJobButton;
    private TextView askAdamButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adam, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adamFirst = getActivity().findViewById(R.id.adam_first);
        adamSecond = getActivity().findViewById(R.id.adam_second);
        bookJobButton = getActivity().findViewById(R.id.book_job_button);
        askAdamButton = getActivity().findViewById(R.id.ask_adam_button);

        setFonts();

        setListeners();
    }

    public void setListener(AdamListener listener){
        this.listener = listener;
    }

    private void setFonts(){

        Typeface robotoLight = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        adamFirst.setTypeface(robotoLight);
        adamSecond.setTypeface(robotoLight);
        bookJobButton.setTypeface(robotoLight);
        askAdamButton.setTypeface(robotoLight);

    }

    private void setListeners(){

        bookJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.openBuildingSelectionFragment();

            }
        });

        askAdamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.openAdamChatFragment();
            }
        });

    }
}
