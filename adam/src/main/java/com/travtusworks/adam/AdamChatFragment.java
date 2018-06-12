package com.travtusworks.adam;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travtusworks.adam.utils.AdamListener;

import java.io.File;

/**
 * Created by teodora on 12.06.2018.
 */

public class AdamChatFragment extends Fragment {

    private final String TAG = "AdamChatFragment";

    public static final String INITIAL_PHOTO_PATH = Environment.getExternalStorageDirectory() + File.separator + "adam";
    public static final int OPEN_CAMERA_PERMISSION = 1;
    public static final int OPEN_CAMERA_READ_PERMISSION = 2;
    public static final int OPEN_CAMERA = 5;
    private String PHOTO_PATH;

    private AdamListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_adam_chat, container, false);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setFonts();

        setListeners();
    }

    public void setListener(AdamListener listener){
        this.listener = listener;
    }

    private void setFonts(){

    }

    private void setListeners(){

    }



}
