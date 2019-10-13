package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.databinding.ItemDanhsachtaophanhoiBinding;
import vn.lachongmedia.appnv.object.CuaHang;
import vn.lachongmedia.appnv.object.PhanHoi.DanhSachPhanHoi;

import java.util.ArrayList;
/**
 * Created by tungda .
 */
public class AdapterRecyclerTaoPhanHoi extends RecyclerView.Adapter<AdapterRecyclerTaoPhanHoi.ItemRowHolder> {
    private ArrayList<DanhSachPhanHoi> listDanhSachPhanHois;
    private Context context;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerTaoPhanHoi( ArrayList<DanhSachPhanHoi> listDanhSachPhanHois) {
        //type==1 xem chi tiết mặt hàng
        this.context = context;
        this.listDanhSachPhanHois = listDanhSachPhanHois;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDanhsachtaophanhoiBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_danhsachtaophanhoi, parent, false);
        return new AdapterRecyclerTaoPhanHoi.ItemRowHolder(binding);
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachcuahang_checkin, null);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {
        DanhSachPhanHoi danhSachPhanHoi = listDanhSachPhanHois.get(position);
        holder.binding.cbTitle.setText(danhSachPhanHoi.getTenPhanHoi());

    }

    @Override
    public int getItemCount() {
        return listDanhSachPhanHois.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemDanhsachtaophanhoiBinding binding;

        private ItemRowHolder(ItemDanhsachtaophanhoiBinding view) {
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

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
