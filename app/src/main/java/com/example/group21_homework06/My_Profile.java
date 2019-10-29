package com.example.group21_homework06;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link My_Profile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class My_Profile extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String Department;
    private int DepartmentID;
    private String my_Image;

    private OnFragmentInteractionListener mListener;


    public My_Profile() {

        // Required empty public constructor
    }

    public profileInfo obj = new profileInfo();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_my__profile, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        final MainActivity activity = (MainActivity) getActivity();
        my_Image = activity.getMyData();
        SharedPreferences mPrefs = this.getActivity().getPreferences(this.getActivity().getApplicationContext().MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("Profile_Info", "");
        activity.info = gson.fromJson(json, profileInfo.class);
        if (activity.info != null) {
            Log.d("resultObj", my_Image);
            if (my_Image == "") {
                my_Image = activity.info.Profile_Image;
                if (activity.info.Profile_Image == "select")
                    activity.info.Profile_Image = "select_image";

            }

            activity.info.Profile_Image = my_Image;


            setProfileDataValue(activity.info);

        } else {
            activity.info = new profileInfo("", "", "", "select", "", 0);
        }
        getActivity().findViewById(R.id.iv_SelectImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onFragmentInteraction("select_image");
            }
        });
        RadioGroup rg = (RadioGroup) getActivity().findViewById(R.id.rg);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                DepartmentID = checkedId;
                switch (checkedId) {
                    case R.id.rb_CS:
                        Department = "CS";
                        break;
                    case R.id.rb_BIO:
                        Department = "BIO";
                        break;
                    case R.id.rb_SIS:
                        Department = "SIS";
                        break;
                    case R.id.rb_Other:
                        Department = "Other";
                        break;

                }

            }
        });

        getActivity().findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText et_firstName = getActivity().findViewById(R.id.et_firstName);
                EditText et_lastName = getActivity().findViewById(R.id.et_lastName);
                EditText student_Id = getActivity().findViewById(R.id.et_studentID);

                activity.info.first_name = et_firstName.getText().toString();
                activity.info.last_name = et_lastName.getText().toString();
                activity.info.student_ID = student_Id.getText().toString();
                activity.info.Profile_Image = my_Image;
                activity.info.Depart = Department;
                activity.info.DepartID = DepartmentID;
                SharedPreferences mPrefs = getActivity().getPreferences(getActivity().getApplicationContext().MODE_PRIVATE);

                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(activity.info);
                prefsEditor.putString("Profile_Info", json);
                prefsEditor.commit();
                if (activity.info.Profile_Image == "") {
                    Toast.makeText(getActivity(), "Please select an Avatar", Toast.LENGTH_SHORT).show();

                } else if (activity.info.first_name.equals("")) {
                    Toast.makeText(getActivity(), "Please insert Your First Name", Toast.LENGTH_SHORT).show();
                } else if (activity.info.last_name.equals("")) {
                    Toast.makeText(getActivity(), "Please insert Your Last Name", Toast.LENGTH_SHORT).show();
                } else if (activity.info.student_ID.equals("")) {
                    Toast.makeText(getActivity(), "Please insert Your Student Id", Toast.LENGTH_SHORT).show();
                } else if (activity.info.DepartID==0) {
                    Toast.makeText(getActivity(), "Please insert Your Department", Toast.LENGTH_SHORT).show();
                } else {
                    mListener.onFragmentInteraction("save");
                }

            }


        });

        super.onActivityCreated(savedInstanceState);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String info);

    }

    public void setBackgroundtoImage(String background) {
        getActivity().findViewById(R.id.iv_SelectImage).setBackground(Drawable.createFromPath(background));
    }

    public void setProfileDataValue(profileInfo obj) {

        EditText et_firstName = getActivity().findViewById(R.id.et_firstName);
        EditText et_lastName = getActivity().findViewById(R.id.et_lastName);
        EditText et_studentID = getActivity().findViewById(R.id.et_studentID);
        RadioGroup rf = getActivity().findViewById(R.id.rg);

        et_firstName.setText(obj.first_name);
        et_lastName.setText(obj.last_name);
        et_studentID.setText(obj.student_ID);
        int department = obj.DepartID;
        DepartmentID = obj.DepartID;
        Department = obj.Depart;
        rf.check(department);
        ImageView viewImage = getActivity().findViewById(R.id.iv_SelectImage);
        Log.d("resultObj", obj.toString());

        viewImage.setImageResource(getContext().getResources().getIdentifier(obj.Profile_Image, "drawable", getContext().getPackageName()));

    }
}
