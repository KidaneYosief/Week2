package com.android4dev.models;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android4dev.adapters.MyAdapter;
import com.android4dev.helpers.AppController;
import com.android4dev.helpers.ProductInfo;
import com.android4dev.navigationview.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/***
 * @author ZeNan Gao
 * @version 08/07/2015
 * @since 1.0
 */
public class MyRecyclerView extends Fragment{
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ProductInfo> products;
    private static String url="https://dl.dropboxusercontent.com/u/1559445/ASOS/SampleApi/anycat_products.json?catid=";

    private String catid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rtView = inflater.inflate(R.layout.fragment_products,container,false);
        //STEP 1: Initialize the RecyclerView
        recyclerView = (RecyclerView) rtView.findViewById(R.id.fragment_products_rv_recyclerView);
        recyclerView.setHasFixedSize(true);

        //STEP 2: Set the Layout
        //layoutManager = new GridLayoutManager(this,2);
        layoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
       // layoutManager = new LinearLayoutManager(getActivity());
        //  layoutManager = new StaggeredGridLayoutManager(2,1);
        recyclerView.setLayoutManager(layoutManager);

        //STEP 3: Set the Animator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        products = new ArrayList<ProductInfo>();

        adapter = new MyAdapter(getActivity(),products);
        recyclerView.setAdapter(adapter);
        Bundle bd = getArguments();
        catid = bd.getString("catid");
        makeJsonReq();
        return rtView;
    }

    private void makeJsonReq() {
        //showProgressDialog();
        //Log.e("inside","inside makeJsonReq with "+url+catid);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,url+catid,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d("Volley", response.toString());
                        try {
                            JSONArray c = response.getJSONArray("Listings");
                            //Log.i("jarray",c.toString());
                            JSONObject info;
                            int pid;
                            int price;
                            String currentprice;
                            String imageurl;
                            ProductInfo pi;
                            for(int i = 0; i < c.length(); i++){
                                info = c.getJSONObject(i);
                                currentprice=info.getString("CurrentPrice");
                                pid = info.getInt("ProductId");
                                imageurl = info.getJSONArray("ProductImageUrl").getString(0);
                                price = info.getInt("BasePrice");
                                //Log.i("info",currentprice +" "+ pid+" " + imageurl+" " +price);
                                pi = new ProductInfo(price,currentprice,imageurl,pid);
                                products.add(pi);

                            }
                        }catch (JSONException e){
                            Log.d("Jsonerror",e.toString());
                        }
                        //Log.d("product",products.toString());

                        adapter.notifyDataSetChanged();
                        //setView();
                        //msgResponse.setText(response.toString());
                        //hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "Error: " + error.getMessage());
                //hideProgressDialog();
            }
        });
        //Log.d("req",req.toString());
        // Adding request to request queue

       AppController.getInstance().addToRequestQueue(req, "jSon_obj");

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
    }

}
