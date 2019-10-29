//Homework 06
//Group no: 21
//Abhishek Tanwer
//Janhvi Chitnis


package com.example.group21_homework06;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements My_Profile.OnFragmentInteractionListener, Select_Avatar.OnSelectAvatarInteraction, DisplayProfile.onDisplayProfileFraagmentListener {

    String backgroundImage = "";
    profileInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Profile");

        getFragmentManager().beginTransaction().add(R.id.container, new My_Profile(), "MY_PROFILE").commit();

    }

    @Override
    public void onFragmentInteraction(String Button1) {
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(info);
        prefsEditor.putString("Profile_Info", json);
        prefsEditor.commit();
        if (Button1 != "select_image") {
            getFragmentManager().beginTransaction().replace(R.id.container, new DisplayProfile(), "DISPLAY_PROFILE").commit();
        } else {
            getFragmentManager().beginTransaction().replace(R.id.container, new Select_Avatar(), "SELECT_AVATAR").addToBackStack(null).commit();
        }
    }


    @Override
    public void onSelectAvatar(String uri) {
        backgroundImage = uri;
        getFragmentManager().beginTransaction().replace(R.id.container, new My_Profile(), "MY_PROFILE").commit();


    }

    public String getMyData() {
        return backgroundImage;
    }

    public profileInfo getMyProfileData() {

        return info;
    }

    @Override
    public void onEditProfile(profileInfo uri) {
        getFragmentManager().beginTransaction().replace(R.id.container, new My_Profile(), "MY_PROFILE").commit();


    }

    @Override
        public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
                finish();
            } else {
                super.onBackPressed();
            }
    }
}

