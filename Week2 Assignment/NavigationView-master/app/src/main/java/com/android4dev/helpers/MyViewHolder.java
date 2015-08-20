package com.android4dev.helpers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android4dev.navigationview.R;


/***
 * View Holder performs:
 * 1. Look up and stores view references
 * 2. Help with detecting item view click
 * @author ZeNan Gao
 * @version 8/4/2015.
 * @since 1.0
 */


public class MyViewHolder extends RecyclerView.ViewHolder {

    // declares view references to be stored
    public TextView pid;
    public ImageView imPic;
    public TextView tvPrice;

    /***
     * Look up and stores view references
     * @param itemView view where references can be located
     */
    public MyViewHolder(View itemView) {
        super(itemView);
        pid = (TextView) itemView.findViewById(R.id.products_cardview_tv_pid);
        imPic = (ImageView) itemView.findViewById(R.id.products_cardview_iv_image);
        tvPrice = (TextView)itemView.findViewById(R.id.products_cardview_tv_price);
    }

}
