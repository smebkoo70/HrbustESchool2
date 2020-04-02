package com.example.hrbusteschool.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hrbusteschool.Activity.EditPersonInfoActivity;
import com.example.hrbusteschool.Activity.LoginActivity;
import com.example.hrbusteschool.Activity.PersonSettingsActivity;
import com.example.hrbusteschool.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewPersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewPersonFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;

    private OnFragmentInteractionListener mListener;


    private LinearLayout head;
    private ImageView psImageView;
    private TextView textView8;
    private TextView tName;
    private TextView textView9;
    private TextView tNumber;
    private LinearLayout presonData;
    private ImageView imageView2;
    private LinearLayout secondlayout;
    private ImageView imageView6;
    private LinearLayout notification;
    private ImageView imageView7;
    private LinearLayout feedback;
    private ImageView imageView8;
    private View view9;
    private LinearLayout meShare;
    private ImageView imageView9;
    private LinearLayout about;
    private ImageView imageView10;
    private LinearLayout settinglayout;
    private ImageView imageView11;
    private Button exitbtn;



    public NewPersonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewPersonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewPersonFragment newInstance(String param1, String param2) {
        NewPersonFragment fragment = new NewPersonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //head = view.findViewById(R.id.head);
        view = inflater.inflate(R.layout.fragment_new_person, container, false);
        psImageView = view.findViewById(R.id.ps_imageView);
        textView8 = view.findViewById(R.id.textView8);
        tName = view.findViewById(R.id.t_name);
        textView9 = view.findViewById(R.id.textView9);
        tNumber = view.findViewById(R.id.t_number);
        presonData = view.findViewById(R.id.preson_data);
        imageView2 = view.findViewById(R.id.imageView2);
        secondlayout = view.findViewById(R.id.secondlayout);
        imageView6 = view.findViewById(R.id.imageView6);
        notification = view.findViewById(R.id.notification);
        imageView7 = view.findViewById(R.id.imageView7);
        feedback = view.findViewById(R.id.feedback);
        imageView8 = view.findViewById(R.id.imageView8);
        view9 = view.findViewById(R.id.view9);
        meShare = view.findViewById(R.id.me_share);
        imageView9 = view.findViewById(R.id.imageView9);
        about = view.findViewById(R.id.about);
        imageView10 = view.findViewById(R.id.imageView10);
        settinglayout = view.findViewById(R.id.settinglayout);
        imageView11 = view.findViewById(R.id.imageView11);
        exitbtn = view.findViewById(R.id.exitbtn);

        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        psImageView.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "success2", 0).show();
                if (LoginActivity.LogStatus == false) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                if (LoginActivity.LogStatus == true) {

                }

            }
        });

        settinglayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginActivity.LogStatus == false) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    //Intent intent = new Intent(getActivity(), LoginActivity.class);
                    //startActivity(intent);
                }
                if (LoginActivity.LogStatus == true) {
                    Intent intent = new Intent(getActivity(), PersonSettingsActivity.class);
                    startActivity(intent);

                }
            }
        });
        presonData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginActivity.LogStatus == false) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    //Intent intent = new Intent(getActivity(), LoginActivity.class);
                    //startActivity(intent);
                }
                if (LoginActivity.LogStatus == true) {
                    Intent intent = new Intent(getActivity(), EditPersonInfoActivity.class);
                    startActivity(intent);

                }
            }
        });
    }

    public void OnClick(View view)
    {
        switch (view.getId())
        {

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //myListener = (MyListener) getActivity();
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
