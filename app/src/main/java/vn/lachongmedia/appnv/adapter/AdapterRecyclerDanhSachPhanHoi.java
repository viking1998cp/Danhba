package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.databinding.ItemDanhsachmathangchitietBinding;
import vn.lachongmedia.appnv.databinding.ItemHeaderDanhsachphanhoiBinding;
import vn.lachongmedia.appnv.object.CuaHang;
import vn.lachongmedia.appnv.object.PhanHoi.LichSuNhom;
import vn.lachongmedia.appnv.object.PhanHoi.PhanHoi;

import java.util.ArrayList;
/**
 * Created by tungda .
 */
public class AdapterRecyclerDanhSachPhanHoi extends RecyclerView.Adapter<AdapterRecyclerDanhSachPhanHoi.ItemRowHolder> {
    private ArrayList<LichSuNhom> listLichSu;
    private Context context;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerDanhSachPhanHoi( ArrayList<LichSuNhom> listLichSu) {
        //type==1 xem chi tiết mặt hàng
        this.context = context;
        this.listLichSu = listLichSu;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHeaderDanhsachphanhoiBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_header_danhsachphanhoi, parent, false);
        return new AdapterRecyclerDanhSachPhanHoi.ItemRowHolder(binding);
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachcuahang_checkin, null);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {
        LichSuNhom lichSuNhom = listLichSu.get(position);
        // set Header for note
        holder.binding.tvInformationUserNote.setText(lichSuNhom.getTenKhachHang());
        //list content
        holder.binding.recycleContentNote.setLayoutManager(new LinearLayoutManager(holder.binding.getRoot().getContext()));
        AdapterRecyclerPhanHoiContent adapter = new AdapterRecyclerPhanHoiContent(lichSuNhom.getDanhsachphanhoi());
        holder.binding.recycleContentNote.setAdapter(adapter);
        adapter.setOnItemClickedListener(new AdapterRecyclerPhanHoiContent.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return listLichSu.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemHeaderDanhsachphanhoiBinding binding;

        private ItemRowHolder(ItemHeaderDanhsachphanhoiBinding view) {
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
