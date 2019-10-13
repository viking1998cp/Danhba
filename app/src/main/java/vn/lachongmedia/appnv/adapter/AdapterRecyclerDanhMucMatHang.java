package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.databinding.ItemDanhmucmathangBinding;
import vn.lachongmedia.appnv.databinding.ItemDanhsachalbumBinding;
import vn.lachongmedia.appnv.mannager.DataBaseHanlder;
import vn.lachongmedia.appnv.object.AlbumObject;
import vn.lachongmedia.appnv.object.DanhmucOBJ;
import vn.lachongmedia.appnv.object.ImageAlbumObject;

/**
 * Created by tungda on 7/28/2019.
 */
public class AdapterRecyclerDanhMucMatHang extends RecyclerView.Adapter<AdapterRecyclerDanhMucMatHang.ItemRowHolder> {
    private ArrayList<DanhmucOBJ> listDanhMuc;
    private Context context;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerDanhMucMatHang(ArrayList<DanhmucOBJ> listDanhMuc) {
        this.context = KsmartSalesApplication.getInstance();
        this.listDanhMuc = listDanhMuc;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemDanhmucmathangBinding itemDanhmucmathangBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_danhmucmathang, parent, false);
      /*  listItemKehoachBinding.getRoot().setLayoutParams(new RecyclerView.LayoutParams(
                ((RecyclerView) parent).getLayoutManager().getWidth(),
                ViewGroup.LayoutParams.WRAP_CONTENT));*/
        return new ItemRowHolder(itemDanhmucmathangBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {
        holder.setBinding(listDanhMuc.get(position),position);

    }

    @Override
    public int getItemCount() {
        return listDanhMuc.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemDanhmucmathangBinding binding;

        public ItemRowHolder(@NonNull ItemDanhmucmathangBinding view) {
            super(view.getRoot());
            binding = view;
            binding.getRoot().setOnClickListener(this);

        }

        public void setBinding(DanhmucOBJ danhmucOBJ,int postion) {
            binding.setDanhmuc(danhmucOBJ);
            if(danhmucOBJ.getSoLuongDanhMucCon()==0){
                binding.ivDetail.setVisibility(View.INVISIBLE);
            }
            else {
                binding.ivDetail.setVisibility(View.VISIBLE);
            }
            if(postion==0){
                binding.ivDetail.setVisibility(View.INVISIBLE);
            }

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
