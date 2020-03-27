package com.example.hrbusteschool.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.hrbusteschool.Activity.LoginActivity;
import com.example.hrbusteschool.Activity.PersonSettingsActivity;
import com.example.hrbusteschool.Adapter.MyListAdapter;
import com.example.hrbusteschool.Adapter.PersonPageAdapter;
import com.example.hrbusteschool.Class.ActivityCollectorUtil;
import com.example.hrbusteschool.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listView;
    private TextView NickNametv;
    private String NickNameSql;
    private ImageView settingimageview;
    public Button ExitButton;
    View view;
    private OnFragmentInteractionListener mListener;
    MyListAdapter myListAdapter;
    ImageView imageView_touxiang;
    private ArrayList arrayList;

    public PersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalFragment newInstance(String param1, String param2) {
        PersonalFragment fragment = new PersonalFragment();
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
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        listView = view.findViewById(R.id.perlv);
        NickNametv = view.findViewById(R.id.textView6);
        settingimageview = view.findViewById(R.id.setimage);
        ExitButton = view.findViewById(R.id.exitbtn);
        PersonPageAdapter personPageAdapter = new PersonPageAdapter(getContext());
        imageView_touxiang = view.findViewById(R.id.ps_imageView);
        /*if (LoginActivity.LogStatus == false)
        {
            ExitButton.setText("您还未登录");
        }
        else if(LoginActivity.LogStatus == true)
        {
            ExitButton.setText("退出登录");
        }*/
        imageView_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(PersonalFragment.this, LoginActivity.class);
            }
        });
        listView.setAdapter(personPageAdapter);
        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginActivity.LogStatus == false) {
                    Toast toast = Toast.makeText(getContext(),"您还未登录",Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                if (LoginActivity.LogStatus == true) {
                    ExitButton.setText("退出登录");
                    LoginActivity.LogStatus = false;
                    Toast toast = Toast.makeText(getContext(),"退出成功",Toast.LENGTH_SHORT);
                    toast.show();
                    ActivityCollectorUtil.finishAllActivity();
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


        imageView_touxiang.setOnClickListener(new View.OnClickListener() {

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
        settingimageview.setOnClickListener(new View.OnClickListener() {
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


    }

    public void InitName() {
        NickNameSql = "select nickname from userinfo where";
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
