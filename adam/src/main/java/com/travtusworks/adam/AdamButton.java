package com.travtusworks.adam;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.travtusworks.adam.utils.Constants;

import dagger.Provides;


/**
 * Created by teodora on 11.06.2018.
 */

public class AdamButton extends FloatingActionButton{

    private static final String TAG = "AdamButton";

    private int userID = -1, companyID = -1;
    private String userName = "";

    public AdamButton(final Context context) {

        super(context);

        Log.i(TAG,"adam 1");
    }

    public void setUserID(int userID){
        Log.i(TAG,"set userID = " + userID);
        this.userID = userID;
    }

    public void setUserName(String userName){
        Log.i(TAG,"set userName = " + userName);
        this.userName = userName;
    }

    public void setCompanyID(int companyID){
        Log.i(TAG,"set companyID = " + companyID);
        this.companyID = companyID;
    }

    public AdamButton(final Context context, AttributeSet attrs) {
        super(context, attrs);

        Log.i(TAG,"adam 2");

        setImageDrawable(context.getResources().getDrawable(R.drawable.adam_large));
        setBackgroundTintList(context.getResources().getColorStateList(R.color.white_color));
        setScaleType(ScaleType.CENTER);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(0);
        }
        ViewCompat.setElevation(this, 0);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG,"the ADAM button was clicked");

                if ((userID != -1) && (companyID != -1) && (!userName.equals(""))) {
                    Intent intent = new Intent(context, AdamActivity.class);
                    intent.putExtra(Constants.EXTRA_USER_ID, userID);
                    intent.putExtra(Constants.EXTRA_USER_NAME, userName);
                    intent.putExtra(Constants.EXTRA_COMPANY_ID, companyID);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    Log.e(TAG,"userID, companyID and userName are MANDATORY!!!");
                }

            }
        });

    }

    public AdamButton(Context context, AttributeSet attrs, int defStyleAttr, Context context1) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG,"adam 3");
    }
}
