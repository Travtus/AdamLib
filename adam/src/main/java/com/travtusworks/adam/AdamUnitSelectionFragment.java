package com.travtusworks.adam;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.travtusworks.adam.adapters.ScopeAdapter;
import com.travtusworks.adam.api.BuildingUnitDAO;
import com.travtusworks.adam.api.ScopeItem;
import com.travtusworks.adam.utils.AdamListener;

import java.util.ArrayList;

/**
 * Created by teodora on 23.04.2018.
 */

public class AdamUnitSelectionFragment extends Fragment {

    private String TAG = "Adam Unit Selection Fragment";

    private AdamListener listener;
    private ScopeAdapter scopeAdapter;
    private ArrayList<ScopeItem> items;

    private EditText search;
    private RecyclerView list;
    private ImageView nextButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_adam_unit_slection, container, false);

        return view;

    }

    public void setListener(AdamListener listener){
        this.listener = listener;
    }

    public void setUnits(ArrayList<BuildingUnitDAO> units){

        items = new ArrayList<>();
        items.addAll(units);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        list.setLayoutManager(layoutManager);
        list.setItemAnimator(new DefaultItemAnimator());

        scopeAdapter = new ScopeAdapter(getActivity(), listener, items);
        list.setAdapter(scopeAdapter);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        search = getActivity().findViewById(R.id.search);
        list = getActivity().findViewById(R.id.list);
        nextButton = getActivity().findViewById(R.id.next_button);

        setFonts();

        setListeners();
    }

    private void setFonts(){

        Typeface robotoLight = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        search.setTypeface(robotoLight);

    }

    private void setListeners(){

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ArrayList<ScopeItem> result = new ArrayList<>();

                for (ScopeItem item : items){
                    if (item.getName().toLowerCase().contains(s.toString().toLowerCase())){
                        result.add(item);
                    }
                }

                scopeAdapter.setItems(result);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (scopeAdapter.getSelectedItem() != null) {
                    Log.i(TAG, "selected unit " + scopeAdapter.getSelectedItem().getInt());
                    listener.startBook(scopeAdapter.getSelectedItem().getInt());
                }

            }
        });

    }

}
