package com.quannm18.quanlykho.Model;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DepotVolleyManager {

    public void insertVolley(Context context, String name, String row, String floors, String position, String description) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://agile-server-beco.herokuapp.com/khoHang/add";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context.getApplicationContext(), "Thanh cong"+response, Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context.getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> stringMap = new HashMap<>();
                stringMap.put("name",name);
                stringMap.put("row",row);
                stringMap.put("floors",floors);
                stringMap.put("position",position);
                stringMap.put("description",description);
                return stringMap;
            }
        };
        queue.add(stringRequest);
    }

    public void updateVolley(Context context,String id,String name, String row, String floors, String position, String description) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://agile-server-beco.herokuapp.com/khoHang/edit/"+id;
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,response, Toast.LENGTH_SHORT).show();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> stringMap = new HashMap<>();
                stringMap.put("id",id);
                stringMap.put("name",name);
                stringMap.put("row",row);
                stringMap.put("floors",floors);
                stringMap.put("position",position);
                stringMap.put("description",description);
                return stringMap;
            }
        };
        queue.add(stringRequest);
    }

    public void deleteVolley(Context context,String id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        KhoHangModel depot = new KhoHangModel();
        String url = "https://agile-server-beco.herokuapp.com/khoHang/delete/"+id ;
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,response, Toast.LENGTH_SHORT).show();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> stringMap = new HashMap<>();
                stringMap.put("_id",id);
                return stringMap;
            }
        };
        queue.add(stringRequest);
    }
}
