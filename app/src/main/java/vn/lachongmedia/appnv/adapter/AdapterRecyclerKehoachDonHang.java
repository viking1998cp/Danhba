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
import vn.lachongmedia.appnv.databinding.ItemKehoachdonhangBinding;
import vn.lachongmedia.appnv.object.KeHoachDonHangObject;

import java.util.ArrayList;

/**
 * Created by tungda .
 */
public class AdapterRecyclerKehoachDonHang extends RecyclerView.Adapter<AdapterRecyclerKehoachDonHang.ItemRowHolder> {
    private ArrayList<KeHoachDonHangObject> listKeHoach;
    private Context context;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerKehoachDonHang(ArrayList<KeHoachDonHangObject> listCuaHang) {
        this.context = KsmartSalesApplication.getInstance();
        this.listKeHoach = listCuaHang;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemKehoachdonhangBinding itemKehoachdonhangBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_kehoachdonhang,parent,false);
      /*  listItemKehoachBinding.getRoot().setLayoutParams(new RecyclerView.LayoutParams(
                ((RecyclerView) parent).getLayoutManager().getWidth(),
                ViewGroup.LayoutParams.WRAP_CONTENT));*/
        return new ItemRowHolder(itemKehoachdonhangBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {
        holder.setBinding(listKeHoach.get(position));
    }

    @Override
    public int getItemCount() {
        return listKeHoach.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemKehoachdonhangBinding itemKehoachdonhangBinding;
        public ItemRowHolder(@NonNull ItemKehoachdonhangBinding view) {
            super(view.getRoot());
            itemKehoachdonhangBinding=view;
            itemKehoachdonhangBinding.getRoot().setOnClickListener(this);

        }
        public void setBinding(KeHoachDonHangObject keHoachDonHangObject) {
            itemKehoachdonhangBinding.setKehoachdonhang(keHoachDonHangObject);
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
