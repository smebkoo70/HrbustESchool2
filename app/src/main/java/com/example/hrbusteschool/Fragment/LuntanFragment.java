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
import com.example.hrbusteschool.WebClass.PostQueryGet;
import com.example.hrbusteschool.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.Runnable;


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
    private String infoString;
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
        //new Thread(new InitThread()).start();
        //new Thread(new MyThread()).start();
        AddPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddPostIntent = new Intent(getActivity(), SendPostActivity.class);
                //Intent AddPostIntent = new Intent(getActivity(), TestNavBarActivity.class);
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
    public class InitThread implements Runnable   {
        @Override
        public void run()
        {
            try {
                ///为应用程序加载驱动
                Class.forName("com.mysql.jdbc.Driver");
                //使用JDBC_URL来标识特定的数据库
                String driver_url = "jdbc:mysql://49.233.85.114:3306/android1?characterEncoding=utf-8&serverTimezone=UTC";
                //String driver_url = "jdbc:mysql://localhost:3306/android1?characterEncoding=utf-8&serverTimezone=UTC";

                //接收数据库的URL,数据库用户名，用户口令，即连接数据库
                Connection conn = DriverManager.getConnection(driver_url, "root", "qwe123789");
                //Connection conn = DriverManager.getConnection(driver_url, "root", "");

                //将要执行的SQL语句

                //String sql = "select count(*) from post";//本句解读为从course表中（即，from course）查找所用数据，限制条件为（where后面为限制条件）user_id = ?
                String sql = "select count(*) from userinfo";
                //PreparedStatement用于执行带或不带参数的预编译SQL语句
                PreparedStatement psmt = conn.prepareStatement(sql);
                //调用setString方法给sql中的第一个问号赋值，这里x为变量。即查找course表中限制条件为表中的user_id = x的所有行，所以返回的结果就是course_id = x 的所用行数据。如有两个数据就再加一行为psmt.setString(2, y),y为变量
                //psmt.setString(1, x);
                //psmt.setInt();
                //返回PreparedStatement语句执行的结果
                ResultSet rs = psmt.executeQuery();
                //从rs结果集中取到想要的结果，rs.next()表示指针指向结果集的下一行。一开始为第一行
                while (rs.next()) {
                    //通过rs的get方法得到指针指向当前行的user_id字段对应的值
                    //user_id = rs.getInt("user_id");
                    myListAdapter.PostItemNum = rs.getInt(1);
                    Toast toast = Toast.makeText(getActivity(),myListAdapter.PostItemNum,Toast.LENGTH_SHORT);
                    toast.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public class MyThread implements Runnable {
        @Override
        public void run() {
            infoString = PostQueryGet.executeHttpGet("QueryPostLet");//获取服务器返回的数据

            //更新UI，使用runOnUiThread()方法
            showResponse(infoString);
        }
    }


    private void showResponse(final String response) {
        getActivity().runOnUiThread(new Runnable() {
            //更新UI
            @Override
            public void run() {
                if (response.equals("-1")) {
                    Toast.makeText(getActivity().getApplicationContext(), "查询失败!", Toast.LENGTH_SHORT).show();
                } else {
                    //info.setText(response);
                    Toast.makeText(getActivity().getApplicationContext(), "查询成功！", Toast.LENGTH_SHORT).show();


                }

            }
        });
    }

}
