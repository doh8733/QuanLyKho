package com.quannm18.quanlykho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quannm18.quanlykho.Interface.IloadMore;
import com.quannm18.quanlykho.Model.Hang;
import com.quannm18.quanlykho.Model.HoaDonNhap;
import com.quannm18.quanlykho.R;

import java.util.List;

public class FragHang_Adapter extends RecyclerView.Adapter<FragHang_Adapter.FragHHolder> {
    private Context context;
    List<Hang> listH;
    private final int VIEW_TYPE_ITEM =0,VIEW_TYPE_LOADING=1;
    IloadMore iloadMore;
    boolean isLoading;
    Activity activity;
    int visibleThreshold=5;
    int lastVisibleHang,totalItemCount;
    public FragHang_Adapter(Context context, List<Hang> listH) {
        this.context = context;
        this.listH = listH;
    }

    public void setData(List<Hang> listH) {
        this.listH = listH;
        notifyDataSetChanged();
    }
    //    public FragHang_Adapter(RecyclerView recyclerView,Context context, List<Hang> listH) {
//        this.context = context;
//        this.listH = listH;
//
//        final LinearLayoutManager linearLayoutManager=(LinearLayoutManager) recyclerView.getLayoutManager();
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                totalItemCount=linearLayoutManager.getItemCount();
//                lastVisibleHang=linearLayoutManager.findLastVisibleItemPosition();
//                if(!isLoading&&totalItemCount<=(lastVisibleHang+visibleThreshold)){
//                    if(iloadMore!=null){
//                        iloadMore.onLoadMore();
//                    }
//                    isLoading=true;
//                }
//            }
//        });
//    }

    @Override
    public int getItemViewType(int position) {
        return listH.get(position)==null?VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    public void setIloadMore(IloadMore iloadMore) {
        this.iloadMore = iloadMore;
    }

//    @NonNull
//    @Override
//    public FragHang_Adapter.FragHHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if(viewType==VIEW_TYPE_ITEM){
//            View view = LayoutInflater.from(context).inflate(R.layout.custom_hang, parent, false);
//            return new FragHHolder(view);
//
//        }else if(viewType==VIEW_TYPE_LOADING){
//            View view = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false);
//            return new Loa(view);
//        }
//    }

    @NonNull
    @Override
    public FragHHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_hang, parent, false);
        return new FragHHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FragHHolder holder, int position) {
        Hang hang= listH.get(position);
        holder.hdn_txt_LoaiHang.setText("LoaiHang"+hang.getLoaiHang());
        holder.hdn_txt_TenHang.setText("TenHang"+hang.getTenHang());
        holder.hdn_txt_SoLuong.setText("SoLuong"+hang.getSoLuong());
    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//    }

    @Override
    public int getItemCount() {
        return listH == null ? 0 : listH.size();
    }

    public class FragHHolder extends RecyclerView.ViewHolder {
        TextView hdn_txt_LoaiHang, hdn_txt_TenHang, hdn_txt_SoLuong;
        public FragHHolder(@NonNull View itemView) {
            super(itemView);
            hdn_txt_LoaiHang=itemView.findViewById(R.id.hdn_txt_LoaiHang);
            hdn_txt_TenHang=itemView.findViewById(R.id.hdn_txt_TenHang);
            hdn_txt_SoLuong=itemView.findViewById(R.id.hdn_txt_SoLuong);


        }
    }
    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar bar;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            bar=itemView.findViewById(R.id.progress_item);


        }
    }
}
