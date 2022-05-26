package com.quannm18.quanlykho.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.quannm18.quanlykho.Model.KhoHangModel;
import com.quannm18.quanlykho.Model.DepotVolleyManager;
import com.quannm18.quanlykho.POST.KhoHangUpdate;
import com.quannm18.quanlykho.POST.CallApi;
import com.quannm18.quanlykho.R;

import java.util.ArrayList;
import java.util.List;

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
//        final  Depot kho = listKho.get(post);
//        context = holder.itemView.getContext();
//        holder.tvRow.setText(String.valueOf(kho.getRow()));
//        holder.tvFloors.setText(String.valueOf(kho.getFloors()));
//        holder.tvPosition.setText(String.valueOf(kho.getPosition()));
//        holder.tvUsed.setText(String.valueOf(kho.getUsed()));
//        holder.tvAvailable.setText(String.valueOf(kho.getAvailable()));
//        holder.tvProductTypes.setText(String.valueOf(kho.getProduct()));
//        holder.tvBroken.setText(String.valueOf(kho.getBroken()));
//        holder.tvFinished.setText(String.valueOf(kho.getFinished()));
        final KhoHangModel depot = listKho.get(post);
        context = holder.itemView.getContext();
        holder.tvName.setText(String.valueOf(depot.getName()));
        holder.tvRow.setText(String.valueOf(depot.getRow()));
        holder.tvFloors.setText(String.valueOf(depot.getFloors()));
        holder.tvPosition.setText(String.valueOf(depot.getPosition()));

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Xoa");
                builder.setMessage("ban cho chac muon xoa" + depot.get_id());
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DepotVolleyManager depotVolleyManager = new DepotVolleyManager();
                        depotVolleyManager.deleteVolley(view.getContext(), depot.get_id());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                View v = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_edit_depot, null);
                builder.setView(v);
                alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                tvview = v.findViewById(R.id.tvview);
                tilEditNameWarehouse = (TextInputLayout) v.findViewById(R.id.tilEditNameWarehouse);
                tilEditRow = (TextInputLayout) v.findViewById(R.id.tilEditRow);
                tilEditFloors = (TextInputLayout) v.findViewById(R.id.tilEditFloors);
                tilEditPosition = (TextInputLayout) v.findViewById(R.id.tilEditPosition);
                tilEditDescription = (TextInputLayout) v.findViewById(R.id.tilEditDescription);
                btnEdit = (MaterialButton) v.findViewById(R.id.btnEdit);
                btnCloseEdit = (MaterialButton) v.findViewById(R.id.btnCloseEdit);
                tilEditNameWarehouse.getEditText().setText(depot.getName());
                tilEditRow.getEditText().setText(depot.getRow());
                tilEditFloors.getEditText().setText(depot.getFloors());
                tilEditPosition.getEditText().setText(depot.getPosition());
                tilEditDescription.getEditText().setText(depot.getDescription());

                Toast.makeText(context, depot.get_id(), Toast.LENGTH_SHORT).show();


                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String _id = depot.get_id();
                        String name = tilEditNameWarehouse.getEditText().getText().toString();
                        String row = tilEditRow.getEditText().getText().toString();
                        String floors = tilEditFloors.getEditText().getText().toString();
                        String position = tilEditPosition.getEditText().getText().toString();
                        String description = tilEditDescription.getEditText().getText().toString();
                        CallApi update = new CallApi();
                        update.Update_Depot(context.getApplicationContext(), new KhoHangModel(_id,name,row,floors,position,description));
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                    }

                });
                alertDialog.show();
            }

        });


    }

    @Override
    public int getItemCount() {
        return listKho.size();
    }

    public class KhoViewholder extends RecyclerView.ViewHolder {
        private TextView tvRow;
        private TextView tvFloors;
        private TextView tvPosition;
        private TextView tvUsed;
        private TextView tvAvailable;
        private TextView tvProductTypes;
        private TextView tvBroken;
        private TextView tvFinished;
        private TextView tvName;
        private CardView layout;

        public KhoViewholder(@NonNull View itemView) {

            super(itemView);
            layout = itemView.findViewById(R.id.layout_item);
            tvName = itemView.findViewById(R.id.tvName);
            tvRow = (TextView) itemView.findViewById(R.id.tvRow);
            tvFloors = (TextView) itemView.findViewById(R.id.tvFloors);
            tvPosition = (TextView) itemView.findViewById(R.id.tvPosition);
            tvUsed = (TextView) itemView.findViewById(R.id.tvUsed);
            tvAvailable = (TextView) itemView.findViewById(R.id.tvAvailable);
            tvProductTypes = (TextView) itemView.findViewById(R.id.tvProduct);
            tvBroken = (TextView) itemView.findViewById(R.id.tvBroke);
            tvFinished = (TextView) itemView.findViewById(R.id.tvFinished);


        }
    }
//    public void updateDpot(String id,String name,String row,String floors,String position,String description){
//            PostDepotUpdate postDepotUpdate = ApiClient.getApiClient().create(PostDepotUpdate.class);
//            Call<KhoHangUpdate> call = postDepotUpdate.updateKhoHang(id,name,row,floors,position,description);
//
//            call.enqueue(new Callback<KhoHangUpdate>() {
//                @Override
//                public void onResponse(Call<KhoHangUpdate> call,@NonNull Response<KhoHangUpdate> response) {
//
//                }
//
//                @Override
//                public void onFailure(Call<KhoHangUpdate> call,@NonNull Throwable t) {
//
//                }
//            });
  //  }
}
