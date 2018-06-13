package com.travtusworks.adam;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.travtusworks.adam.adapters.AdamAdapter;
import com.travtusworks.adam.api.MessageDAO;
import com.travtusworks.adam.api.SentMessageDAO;
import com.travtusworks.adam.utils.AdamListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

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

    private ImageView backButton;
    private TextView adamName;
    private RecyclerView requestsList;
    private ImageView cameraButton;
    private ImageView sendButton;
    private EditText requestText;

    private AdamAdapter adapter;
    private LinearLayoutManager manager;
    private boolean loading;
    private int currentUserID;
    private String userName;
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

        backButton = getActivity().findViewById(R.id.back_button);
        adamName = getActivity().findViewById(R.id.adam_name);
        requestsList = getActivity().findViewById(R.id.requests_list);
        cameraButton = getActivity().findViewById(R.id.camera_button);
        sendButton = getActivity().findViewById(R.id.send_button);
        requestText = getActivity().findViewById(R.id.request_text);

        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        manager.setStackFromEnd(true);
        requestsList.setLayoutManager(manager);

        adapter = new AdamAdapter(getActivity(), currentUserID, userName, listener);
        requestsList.setAdapter(adapter);

        setFonts();

        setListeners();
    }

    public void setListener(AdamListener listener){
        this.listener = listener;
    }

    public void setCurrentUser(int userID){
        currentUserID = userID;
    }

    public void setCurrentUserName(String userName){
        this.userName = userName;
    }

    public void setMessages(ArrayList<MessageDAO> messages){

        adapter.loadMessages(messages);
        if (adapter.getItemCount() < 30) {
            requestsList.smoothScrollToPosition(adapter.getItemCount());
        }
        loading = false;

    }

    public void setSentMessage(SentMessageDAO sentMessage){

        //update the list
        adapter.addMessage(sentMessage.getReceivedMessage());
        requestsList.smoothScrollToPosition(adapter.getItemCount());

    }

    public void sendMessage(MessageDAO messageDAO){

        adapter.addMessage(messageDAO);
        requestsList.smoothScrollToPosition(adapter.getItemCount());

    }

    private void setFonts(){

        Typeface robotoLight = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        Typeface sfRegular = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SF-UI-Regular.otf");
        adamName.setTypeface(robotoLight);
        requestText.setTypeface(sfRegular);

    }

    private void takePhoto(){

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, OPEN_CAMERA_PERMISSION);
        } else if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestReadPermission();
        } else {
            startImageCaptureActivity();
        }

    }

    private void requestReadPermission() {
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, OPEN_CAMERA_READ_PERMISSION);
    }

    private void startImageCaptureActivity(){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        PHOTO_PATH = INITIAL_PHOTO_PATH + Calendar.getInstance().getTimeInMillis()/1000 + ".jpg";
        File file = new File(PHOTO_PATH);
        Uri fileURI = FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileURI);
        startActivityForResult(intent, OPEN_CAMERA);
    }

    private void setListeners(){

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (requestText.getText().toString().length() > 0) {
                    listener.sendMessage(requestText.getText().toString());
                    requestText.setText("");
                }

            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                takePhoto();

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == OPEN_CAMERA_PERMISSION) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestReadPermission();
            } else {
                startImageCaptureActivity();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK){

            if (requestCode == OPEN_CAMERA){

                Bitmap inImage = BitmapFactory.decodeFile(PHOTO_PATH);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                if (inImage != null) {
                    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                    String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), inImage, "", null);
                    Uri uri = Uri.parse(path);

                    File file = new File(PHOTO_PATH);
                    byte[] bitmapdata = bytes.toByteArray();
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String key = "adamImage" + Calendar.getInstance().getTimeInMillis() / 1000 + ".png";

                    listener.uploadImage(file, key, uri);
                } else {
                    listener.showErrorMessage(getResources().getString(R.string.photo_error));
                }

            }

        }

    }

}
