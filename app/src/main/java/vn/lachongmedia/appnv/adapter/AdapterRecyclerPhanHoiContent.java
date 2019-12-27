package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.databinding.ItemContentdanhsachphanhoiBinding;

import vn.lachongmedia.appnv.object.MatHang;
import vn.lachongmedia.appnv.object.PhanHoi.DanhSachPhanHoi;
import vn.lachongmedia.appnv.object.PhanHoi.PhanHoi;


public class AdapterRecyclerPhanHoiContent extends  RecyclerView.Adapter<AdapterRecyclerPhanHoiContent.ItemRowHolder> {
    private ArrayList<DanhSachPhanHoi> listDanhSachPhanHois;
    private Context context;
    private OnItemClickedListener onItemClickedListener;
    public AdapterRecyclerPhanHoiContent(ArrayList<DanhSachPhanHoi> listDanhSachPhanHois) {
        this.listDanhSachPhanHois = listDanhSachPhanHois;
        this.context = context;
    }


    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContentdanhsachphanhoiBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_contentdanhsachphanhoi, parent, false);
        return new AdapterRecyclerPhanHoiContent.ItemRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, int position) {
        DanhSachPhanHoi danhSachPhanHoi = listDanhSachPhanHois.get(position);
        holder.binding.tvNameNote.setText("Tên phản hồi: "+danhSachPhanHoi.getTenPhanHoi());
        holder.binding.tvContentNote.setText("Nội dung: "+danhSachPhanHoi.getChiTiet());
        holder.binding.tvDateNote.setText("Thời gian: "+ Common.formatChangePointDateTimeHai(danhSachPhanHoi.getThoiGian()));
    }



    @Override
    public int getItemCount() {
        return listDanhSachPhanHois.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemContentdanhsachphanhoiBinding binding;

        private ItemRowHolder(ItemContentdanhsachphanhoiBinding view) {
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
