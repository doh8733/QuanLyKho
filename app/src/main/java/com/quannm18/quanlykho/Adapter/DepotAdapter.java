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
import com.quannm18.quanlykho.Interface.GetDepot;
import com.quannm18.quanlykho.Model.KhoHangModel;
import com.quannm18.quanlykho.POST.CallApi;
import com.quannm18.quanlykho.R;

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
        holder.tvName.setText(String.valueOf(depot.getName()));
        holder.tvRow.setText(String.valueOf(depot.getRow()));
        holder.tvFloors.setText(String.valueOf(depot.getFloors()));
        holder.tvPosition.setText(depot.getPosition());
        holder.tvDescription.setText(depot.getDescription());

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Xoa");
                builder.setMessage("ban cho chac muon xoa" + depot.get_id());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                View v = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_edit_depot, null);
                builder.setView(v);
                alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


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
                        if (name.trim().isEmpty()) {
                            tilEditNameWarehouse.setError("Vui lòng không để trắng thông tin!!!");
                            return;
                        } else {
                            tilEditNameWarehouse.setErrorEnabled(false);
                        }
                        if (row.trim().isEmpty()) {
                            tilEditRow.setError("Vui lòng không để trắng thông tin!!!");
                            return;
                        } else {
                            tilEditRow.setErrorEnabled(false);
                        }
                        if (floors.trim().isEmpty()) {
                            tilEditFloors.setError("Vui lòng không để trắng thông tin!!!");
                            return;
                        } else {
                            tilEditFloors.setErrorEnabled(false);
                        }
                        if (position.trim().isEmpty()) {
                            tilEditPosition.setError("Vui lòng không để trắng thông tin!!!");
                            return;
                        } else {
                            tilEditPosition.setErrorEnabled(false);
                        }
                        if (description.trim().isEmpty()) {
                            tilEditDescription.setError("Vui lòng không để trắng thông tin!!!");
                            return;
                        } else {
                            tilEditDescription.setErrorEnabled(false);
                        }

                        CallApi callApi = new CallApi();
                        callApi.Update_Depot(context, new KhoHangModel(_id, name, row, floors, position, description));
                        listKho.clear();
                        listKho = getAllDaTa(view.getContext());
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                    }

                });
                alertDialog.show();
            }

        });


    }

    public List<KhoHangModel> getAllDaTa(Context context) {
        List<KhoHangModel> khoHangModelList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDepot getDepot = retrofit.create(GetDepot.class);
        Call<List<KhoHangModel>> call = getDepot.getKho();
        call.enqueue(new Callback<List<KhoHangModel>>() {
            @Override
            public void onResponse(Call<List<KhoHangModel>> call, Response<List<KhoHangModel>> response) {
                List<KhoHangModel> lisdepot = response.body();
                for (KhoHangModel khoHangModel : lisdepot) {
                    khoHangModelList.add(khoHangModel);
                }
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<KhoHangModel>> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "Loi", Toast.LENGTH_SHORT).show();
            }
        });
        return khoHangModelList;
    }

    @Override
    public int getItemCount() {
        return listKho.size();
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
}
