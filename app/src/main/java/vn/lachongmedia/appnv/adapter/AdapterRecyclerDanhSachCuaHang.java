package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.databinding.ItemDanhsachcuahangBinding;

import vn.lachongmedia.appnv.network.CuaHang;

import java.util.ArrayList;

/**
 * Created by tungda .
 */
public class AdapterRecyclerDanhSachCuaHang extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<CuaHang> listCuaHang;
    private Context context;
    private OnItemClickedListener onItemClickedListener;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public AdapterRecyclerDanhSachCuaHang(ArrayList<CuaHang> listCuaHang) {
        this.context = KsmartSalesApplication.getInstance();
        this.listCuaHang = listCuaHang;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            ItemDanhsachcuahangBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_danhsachcuahang, parent, false);
            return new ItemRowHolder(binding);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemRowHolder) {

           ((ItemRowHolder) holder).setBinding(listCuaHang.get(position));
           if(listCuaHang.get(position).getDiaChi()==null){
               //Ẩn địa chỉ

               ((ItemRowHolder) holder).binding.tvDiaChiCuaHang.setVisibility(View.GONE);
           }
        } else if (holder instanceof LoadingViewHolder) {
           // showLoadingView((LoadingViewHolder) viewHolder, position);
        }
    }

   /* @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {
        holder.setBinding(listCuaHang.get(position));
    }*/

    @Override
    public int getItemCount() {
        return listCuaHang.size();
    }
    /**
     * The following method decides the type of ViewHolder to display in the RecyclerView
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return listCuaHang.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemDanhsachcuahangBinding binding;

        private ItemRowHolder(@NonNull ItemDanhsachcuahangBinding viewBinding) {
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

    public class LoadingViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener  {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
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
