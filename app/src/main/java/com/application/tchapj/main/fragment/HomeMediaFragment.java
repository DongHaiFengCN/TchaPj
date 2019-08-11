package com.application.tchapj.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.main.bean.HomeMediaList;

import java.util.List;

public class HomeMediaFragment extends Fragment {

    private TextView tv01;
    public  HomeMediaFragment newInstance(List<HomeMediaList.HomeMediaListResult.HomeMediaModel> mediaList, String type) {
        HomeMediaFragment fragment = new HomeMediaFragment();
        Bundle args = new Bundle();
        //args.put("arg1", model);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_media, container, false);
        /*tv01 = (TextView) v.findViewById(R.id.tv01);
        String text = getArguments().getString("arg1");
        tv01.setText(text);*/
        return v;
    }

}
