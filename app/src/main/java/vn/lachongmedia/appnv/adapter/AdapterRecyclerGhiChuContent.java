package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.lachongmedia.appnv.R;

import vn.lachongmedia.appnv.databinding.ItemContentdanhsachghichuBinding;
import vn.lachongmedia.appnv.object.GhiChu.DanhSachGhiChu;

public class AdapterRecyclerGhiChuContent extends RecyclerView.Adapter<AdapterRecyclerGhiChuContent.ItemRowHolder> {
    private ArrayList<DanhSachGhiChu> listDanhSachGhiChus;
    private Context context;
    private OnItemClickedListener onItemClickedListener;
    public AdapterRecyclerGhiChuContent(ArrayList<DanhSachGhiChu> listDanhSachGhiChus) {
        this.listDanhSachGhiChus = listDanhSachGhiChus;
        this.context = context;
    }


    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContentdanhsachghichuBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_contentdanhsachghichu, parent, false);
        return new AdapterRecyclerGhiChuContent.ItemRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, int position) {
        DanhSachGhiChu danhSachGhiChu = listDanhSachGhiChus.get(position);
        holder.binding.tvTitle.setText(danhSachGhiChu.getTenCheckList());
        holder.binding.tvContent.setText(danhSachGhiChu.getChiTiet());
    }



    @Override
    public int getItemCount() {
        return listDanhSachGhiChus.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemContentdanhsachghichuBinding binding;

        private ItemRowHolder(ItemContentdanhsachghichuBinding view) {
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
