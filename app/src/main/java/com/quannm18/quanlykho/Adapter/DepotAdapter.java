package com.quannm18.quanlykho.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.quannm18.quanlykho.API.GetListPosition;
import com.quannm18.quanlykho.ChooseDepotActivity;
import com.quannm18.quanlykho.Interface.GetDepot;
import com.quannm18.quanlykho.Model.KhoHangModel;
import com.quannm18.quanlykho.Model.Position;
import com.quannm18.quanlykho.Model.PostionStatus;
import com.quannm18.quanlykho.POST.CallApi;
import com.quannm18.quanlykho.R;
import com.quannm18.quanlykho.TongQuatKhoActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DepotAdapter extends RecyclerView.Adapter<DepotAdapter.KhoViewholder> {
    Context context;
    AlertDialog alertDialog;
    List<KhoHangModel> listKho = new ArrayList<>();
    private TextInputLayout tilNameWarehouse;
    private TextInputLayout tilRow;
    private TextInputLayout tilFloors;
    private TextInputLayout tilPosition;
    private TextInputLayout tilDescription;
    private MaterialButton btnCreate;
    private MaterialButton btnClose;
    ;
    private TextView tvview;
    private TextInputLayout tilEditNameWarehouse;
    private TextInputLayout tilEditRow;
    private TextInputLayout tilEditFloors;
    private TextInputLayout tilEditPosition;
    private TextInputLayout tilEditDescription;
    private MaterialButton btnEdit;
    private MaterialButton btnCloseEdit;
    private KhoHangModel updateKhoHang = new KhoHangModel();
    private List<Position> checkListEmpty;
    public DepotAdapter(Context context, List<KhoHangModel> listKho) {
        this.listKho = listKho;
    }


    @NonNull
    @Override
    public KhoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tong_quat_kho, parent, false);
        KhoViewholder khoViewholder = new KhoViewholder(view);
        return khoViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull KhoViewholder holder, int post) {

        final KhoHangModel depot = listKho.get(post);
        context = holder.itemView.getContext();
        getListPos(context, depot.get_id());
        holder.tvName.setText(String.valueOf(depot.getName()));
        holder.tvRow.setText(String.valueOf(depot.getRow()));
        holder.tvFloors.setText(String.valueOf(depot.getFloors()));
        holder.tvPosition.setText(depot.getPosition());
        holder.tvDescription.setText(depot.getDescription());

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Are you deleting" + depot.get_id());
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CallApi callApi = new CallApi();
                        callApi.deleteDepot(new KhoHangModel(depot.get_id(), depot.getName(), depot.getRow(), depot.getFloors(), depot.getPosition(), depot.getDescription()));
                        listKho.remove(holder.getAdapterPosition());
                        notifyItemChanged(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();

                return false;
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), ChooseDepotActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("kho",depot);
//                intent.putExtra("kho",bundle);
//                view.getContext().startActivity(intent);
                AlertDialog.Builder checkingDialog = new AlertDialog.Builder(view.getContext());
                checkingDialog.setMessage("Checking...");
                alertDialog = checkingDialog.create();

                alertDialog.show();
                CountDownTimer cdt1 = new CountDownTimer(3000,2000) {
                    @Override
                    public void onTick(long l) {
                        getListPos(view.getContext(), depot.get_id());
                    }

                    @Override
                    public void onFinish() {
                        alertDialog.dismiss();
                        if (status!=null){
                            if (status.equals("Data not found")){
//                            if (checkListEmpty.size()==0){
                                AlertDialog.Builder waitingDialog = new AlertDialog.Builder(view.getContext());
                                waitingDialog.setMessage("Waiting a minute...");
                                alertDialog = waitingDialog.create();
                                alertDialog.show();

                                CountDownTimer cdt = new CountDownTimer(3000,2000) {
                                    @Override
                                    public void onTick(long l) {
                                        createPos(view.getContext(), depot.get_id());
                                    }

                                    @Override
                                    public void onFinish() {
                                        alertDialog.dismiss();
                                        Intent i = new Intent(view.getContext(), ChooseDepotActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("depot",depot);
                                        i.putExtra("depot",bundle);
                                        view.getContext().startActivity(i);
                                    }
                                }.start();
//                            }
                            }else {
                                alertDialog.dismiss();
                                Intent i = new Intent(view.getContext(), ChooseDepotActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("depot",depot);
                                i.putExtra("depot",bundle);
                                view.getContext().startActivity(i);
                            }
                        }
                    }
                }.start();

            }

        });


    }

//    public List<KhoHangModel> getAllDaTa(Context context) {
//        List<KhoHangModel> khoHangModelList = new ArrayList<>();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://agile-server-beco.herokuapp.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        GetDepot getDepot = retrofit.create(GetDepot.class);
//        Call<List<KhoHangModel>> call = getDepot.getKho();
//        call.enqueue(new Callback<List<KhoHangModel>>() {
//            @Override
//            public void onResponse(Call<List<KhoHangModel>> call, Response<List<KhoHangModel>> response) {
//                List<KhoHangModel> lisdepot = response.body();
//                for (KhoHangModel khoHangModel : lisdepot) {
//                    khoHangModelList.add(khoHangModel);
//                }
//                notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<KhoHangModel>> call, Throwable t) {
//                Toast.makeText(context.getApplicationContext(), "Loi", Toast.LENGTH_SHORT).show();
//            }
//        });
//        return khoHangModelList;
//    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class KhoViewholder extends RecyclerView.ViewHolder {
        private TextView tvRow;
        private TextView tvFloors;
        private TextView tvPosition;
        private TextView tvDescription;

        private TextView tvName;
        private CardView layout;

        public KhoViewholder(@NonNull View itemView) {

            super(itemView);


            tvDescription = (TextView)  itemView.findViewById(R.id.tvDescription);
            layout = itemView.findViewById(R.id.layout_item);
            tvName = itemView.findViewById(R.id.tvName);
            tvRow = (TextView) itemView.findViewById(R.id.tvRow);
            tvFloors = (TextView) itemView.findViewById(R.id.tvFloors);
            tvPosition = (TextView) itemView.findViewById(R.id.tvPosition);

        }
    }
    void createPos(Context context, String id){
        GetListPosition.getListPosition.postCreatePosition(id).enqueue(new Callback<PostionStatus>() {
            @Override
            public void onResponse(Call<PostionStatus> call, Response<PostionStatus> response) {
                final  PostionStatus pos = response.body();
                Toast.makeText(context, ""+pos.getStatus(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostionStatus> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    String getListPos(Context context, String id){

        GetListPosition.getListPosition.postGetListPosition(id).enqueue(new Callback<List<Position>>() {
            @Override
            public void onResponse(Call<List<Position>> call, Response<List<Position>> response) {
                List<Position> poss = response.body();
                if (poss.size()!=0) {
//                    Toast.makeText(context, "Have data", Toast.LENGTH_SHORT).show();
                    status = "Have data";
                }else {
//                    Toast.makeText(context, "Data not found", Toast.LENGTH_SHORT).show();
                    status = "Data not found";
                }
            }

            @Override
            public void onFailure(Call<List<Position>> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        return status;
    }
    String status;
}
