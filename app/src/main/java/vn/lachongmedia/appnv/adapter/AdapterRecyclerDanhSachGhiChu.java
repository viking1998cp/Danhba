package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.Api;

import vn.lachongmedia.appnv.R;


import vn.lachongmedia.appnv.databinding.ItemHeaderDanhsachphanhoivaghichuBinding;
import vn.lachongmedia.appnv.object.CuaHang;
import vn.lachongmedia.appnv.object.GhiChu.DanhSachGhiChu;
import vn.lachongmedia.appnv.object.GhiChu.LichSuNhom;

import java.util.ArrayList;
/**
 * Created by tungda .
 */
public class AdapterRecyclerDanhSachGhiChu extends RecyclerView.Adapter<AdapterRecyclerDanhSachGhiChu.ItemRowHolder> {
    private ArrayList<LichSuNhom> listLichSuNhoms;
    private Context context;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerDanhSachGhiChu(ArrayList<LichSuNhom> listLichSuNhoms) {
        this.context = context;
        this.listLichSuNhoms = listLichSuNhoms;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemHeaderDanhsachphanhoivaghichuBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_header_danhsachphanhoivaghichu, parent, false);
        return new AdapterRecyclerDanhSachGhiChu.ItemRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {
        LichSuNhom lichSuNhom = listLichSuNhoms.get(position);
        if(lichSuNhom.getTenKhachHang().equals("")){
            holder.binding.tvInformationUserNote.setText("Tự do");
        }else {
            holder.binding.tvInformationUserNote.setText(lichSuNhom.getTenKhachHang());
        }

        holder.binding.recycleContentNote.setLayoutManager(new LinearLayoutManager(holder.binding.getRoot().getContext()));
        AdapterRecyclerGhiChuContent adapter = new AdapterRecyclerGhiChuContent(lichSuNhom.getDanhsachghichu());
        holder.binding.recycleContentNote.setAdapter(adapter);
        adapter.setOnItemClickedListener(new AdapterRecyclerGhiChuContent.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return listLichSuNhoms.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemHeaderDanhsachphanhoivaghichuBinding binding;

        private ItemRowHolder(ItemHeaderDanhsachphanhoivaghichuBinding view) {
            super(view.getRoot());
            binding = view;
            binding.getRoot().setOnClickListener(this);
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
