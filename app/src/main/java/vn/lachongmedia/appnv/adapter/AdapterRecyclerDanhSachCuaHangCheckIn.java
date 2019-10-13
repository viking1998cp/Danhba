package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.databinding.ItemDanhsachcuahangCheckinBinding;
import vn.lachongmedia.appnv.network.CuaHang;


import java.util.ArrayList;

/**
 * Created by tungda .
 */
public class AdapterRecyclerDanhSachCuaHangCheckIn extends RecyclerView.Adapter<AdapterRecyclerDanhSachCuaHangCheckIn.ItemRowHolder> {
    private ArrayList<CuaHang> listCuaHang;
    private Context context;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerDanhSachCuaHangCheckIn(ArrayList<CuaHang> listCuaHang) {
        this.context = KsmartSalesApplication.getInstance();
        this.listCuaHang = listCuaHang;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachcuahang_checkin, null);
        ItemDanhsachcuahangCheckinBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_danhsachcuahang_checkin, parent, false);
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachcuahang_checkin, null);
        return new ItemRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {
        holder.setBinding(listCuaHang.get(position));
    }

    @Override
    public int getItemCount() {
        return listCuaHang.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemDanhsachcuahangCheckinBinding binding;

        private ItemRowHolder(@NonNull ItemDanhsachcuahangCheckinBinding viewBinding) {
            super(viewBinding.getRoot());
            binding = viewBinding;
            binding.getRoot().setOnClickListener(this);

        }

        public void setBinding(CuaHang cuaHang) {
            binding.setCuahang(cuaHang);
        }

        @Override
        public void onClick(View v) {
            onItemClickedListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public interface OnItemClickedListener {
        void onItemClick(int postion, View v);
    }


    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
