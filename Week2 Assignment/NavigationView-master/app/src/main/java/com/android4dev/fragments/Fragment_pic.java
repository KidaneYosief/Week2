package com.android4dev.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android4dev.navigationview.R;
import com.squareup.picasso.Picasso;

/***
 * class to use picasso to process network image
 * @author ZeNan Gao
 * @version 8/7/2015
 * @since 1.0
 */
public class Fragment_pic extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rtView = inflater.inflate(R.layout.fragment_pic,container,false);

        Bundle bd = getArguments();
        String url = bd.getString("url");
        Log.i("url",url);
        ImageView ivPic = (ImageView) rtView.findViewById(R.id.fragment_pic_iv_pic);
        Picasso.with(getActivity())
                .load(Uri.parse(url))
                .resize(600, 600)
                .centerCrop()
                .into(ivPic);
        return rtView;
    }
}
