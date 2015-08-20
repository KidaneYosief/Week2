package com.android4dev.adapters;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android4dev.activities.MainActivity;
import com.android4dev.helpers.MyViewHolder;
import com.android4dev.helpers.ProductInfo;
import com.android4dev.navigationview.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/***
 * @author ZeNan Gao
 * @version 8/4/2015
 * @since 1.1
 * Modified from Kalpesh program
 * Adapter performs
 * 1. Inflate layout
 * 2. Binds data to view
 * 3. Report item-click events
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<ProductInfo> peopleDataSet;
    //REMEMBER: THERE ARE FOR METHODS TO BE ALWAYS MENTIONED IN THIS CLASS OF ADAPTER
    // 1. Constructor of the class
    // 2. Static inner class to initialize the views of rows
    // 3. onCreateViewHolder(): specify the row layout file and click for each row
    // 4. onBindViewHolder(): load data in each row element
    // 5. getItemCount(): check the size

    public Activity act;
    public MyAdapter(Activity act, ArrayList<ProductInfo> people) {
        this.act = act;
        this.peopleDataSet = people;
    }


    /***
     * inflate layout
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_cardview, parent, false);
//Click event
        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    /***
     * binds data to views
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView pid = holder.pid;
        ImageView ivImage = holder.imPic;
        TextView price = holder.tvPrice;
        final String url = peopleDataSet.get(listPosition).getpUrl();
        final String mprice = peopleDataSet.get(listPosition).getCurrentPrice();
        final int id = peopleDataSet.get(listPosition).getPid();

        // Register item click listener for image view
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("pid",id+"");
                ((MainActivity)act).showProduct(id);
            }
        });


        pid.setText(Integer.toString(id));
        price.setText(mprice);

        //use picasso to process image
        Picasso.with(act)
                .load(Uri.parse(url))
                .resize(600, 600)
                .centerCrop()
                .into(ivImage);

    }

    /***
     * to obtain size
     * @return item count of people size
     */
    @Override
    public int getItemCount() {
        return peopleDataSet.size();
    }
}