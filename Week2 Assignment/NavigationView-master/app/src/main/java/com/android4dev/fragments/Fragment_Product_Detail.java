package com.android4dev.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android4dev.navigationview.R;

import java.util.ArrayList;

/***
 * class to populate details and implement viewgroup
 * @author ZeNan Gao
 * @since 1.0
 * @version 8/7/2015
 */
public class Fragment_Product_Detail extends Fragment {
    private int pid;
    private String brand,description;
    TextView tvBrand;
    TextView tvDec;
    ViewPager vpPic;
    PagerAdapter mAdapter;
    static ArrayList<String> urls;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rtView = inflater.inflate(R.layout.fragment_product_detail, container, false);
        tvBrand = (TextView)rtView.findViewById(R.id.fragment_product_detail_tv_brand);
        tvDec = (TextView)rtView.findViewById(R.id.fragment_product_detail_tv_des2);
        vpPic = (ViewPager)rtView.findViewById(R.id.fragment_product_detail_vp_pic);

        Bundle bd = getArguments();
        brand = bd.getString("brand");
        description = bd.getString("desc");
        urls = bd.getStringArrayList("list");
        pid = bd.getInt("pid");

        Log.i("des",description);
        tvBrand.setText(brand);
        tvDec.setText(description);
        //urls = new ArrayList<>();
        mAdapter = new MyAdapter(getActivity().getSupportFragmentManager());
        vpPic.setAdapter(mAdapter);
        return rtView;
    }


    public static class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            Log.i("Size",""+urls.size());
            return urls.size();
        }

        @Override
        public Fragment getItem(int position) {

                        Fragment fragment = new Fragment_pic();
                        Bundle bd = new Bundle();
                        bd.putString("url", urls.get(position));
                        fragment.setArguments(bd);
                        return fragment;

        }

    }

}
