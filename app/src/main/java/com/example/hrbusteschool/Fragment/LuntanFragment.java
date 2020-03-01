package com.example.hrbusteschool.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.hrbusteschool.Activity.InvitationActivity;
import com.example.hrbusteschool.Activity.SendPostActivity;
import com.example.hrbusteschool.Adapter.MyListAdapter;
import com.example.hrbusteschool.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LuntanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LuntanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LuntanFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    LinearLayout postlinearLayout = null;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mcontext;
    private ListView listView;
    public String title;
    private Button AddPostButton;
    View view;
    private LuntanFragment.OnFragmentInteractionListener mListener;
    MyListAdapter myListAdapter;
    private OnFragmentInteractionListener onFragmentInteractionListener;

    public LuntanFragment() {
        // Required empty public constructor
    }
    public interface MyListener
    {
        public void sendValue(String value);
    }
    private MyListener myListener;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LuntanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LuntanFragment newInstance(String param1, String param2) {
        LuntanFragment fragment = new LuntanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mcontext = getActivity();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private void InitListView()
    {
        listView = view.findViewById(R.id.luntan_listview);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Log","OnCreatView");
        view = inflater.inflate(R.layout.fragment_luntan, container, false);
        listView = view.findViewById(R.id.luntan_listview);
        AddPostButton = view.findViewById(R.id.addbutton);
        //MyListAdapter myListAdapter = new MyListAdapter(getContext());
        MyListAdapter myListAdapter = new MyListAdapter(getContext());


        listView.setAdapter(myListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int poss = i+1;
                title = String.valueOf(poss);
                //myListener.sendValue(String.valueOf(poss));
                //Log.d("debug",null);
                Toast toast1 = Toast.makeText(mcontext,"Item " + poss +"被点击",Toast.LENGTH_SHORT);
                toast1.show();
                Intent newInvitation = new Intent(getActivity(), InvitationActivity.class);
                startActivity(newInvitation);
            }
        });
        AddPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddPostIntent = new Intent(getActivity(), SendPostActivity.class);
                startActivity(AddPostIntent);
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
