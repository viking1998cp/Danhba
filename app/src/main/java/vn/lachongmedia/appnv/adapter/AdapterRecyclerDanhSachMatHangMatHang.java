package vn.lachongmedia.appnv.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import vn.lachongmedia.appnv.R;

import vn.lachongmedia.appnv.databinding.ItemDanhsachmathangchitietBinding;
import vn.lachongmedia.appnv.databinding.ItemDanhsachmathangrutgonBinding;
import vn.lachongmedia.appnv.object.MatHang;

import java.util.ArrayList;

/**
 * Created by tungda .
 */
public class AdapterRecyclerDanhSachMatHangMatHang extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<MatHang> listMatHang;
    private Context context;
    private int type = 0;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerDanhSachMatHangMatHang(ArrayList<MatHang> listMatHang, int type) {
        //type==1 xem chi tiết mặt hàng
        this.context = context;
        this.listMatHang = listMatHang;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachcuahang_checkin, null);
        if (viewType == 0) {
            ItemDanhsachmathangrutgonBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_danhsachmathangrutgon, parent, false);
            return new ItemRowSimpleHolder(binding);
        } else {
            ItemDanhsachmathangchitietBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_danhsachmathangchitiet, parent, false);
            return new ItemRowDetailHolder(binding);
        }
       /* v.setLayoutParams(new RecyclerView.LayoutParams(
                ((RecyclerView) parent).getLayoutManager().getWidth(),
               ViewGroup.LayoutParams.WRAP_CONTENT));*/

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MatHang matHang = listMatHang.get(position);
        if (holder instanceof ItemRowSimpleHolder) {
            ((ItemRowSimpleHolder) holder).setBinding(matHang);
        } else if (holder instanceof ItemRowDetailHolder) {
            ((ItemRowDetailHolder) holder).setBinding(matHang);
            switch (((ItemRowDetailHolder) holder).binding.rdGroupType.getCheckedRadioButtonId()){
                case R.id.rdWholSale:
                    ((ItemRowDetailHolder) holder).binding.tvPrice.setText(matHang.getGiaBuon());
                    break;
                case R.id.rdRetail:
                    ((ItemRowDetailHolder) holder).binding.tvPrice.setText(matHang.getGiaLe());
                    break;
                case R.id.rdOther:
                    ((ItemRowDetailHolder) holder).binding.tvPrice.setText(matHang.getGiaLe());
                    break;
            }
            ((ItemRowDetailHolder) holder).binding.rdGroupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId){
                        case R.id.rdWholSale:
                            ((ItemRowDetailHolder) holder).binding.tvPrice.setText(matHang.getGiaBuon());
                            break;
                        case R.id.rdRetail:
                            ((ItemRowDetailHolder) holder).binding.tvPrice.setText(matHang.getGiaLe());
                            break;
                        case R.id.rdOther:
                            ((ItemRowDetailHolder) holder).binding.tvPrice.setText(matHang.getGiaLe());
                            break;

                    }
                }
            });
            // showLoadingView((LoadingViewHolder) viewHolder, position);
        }
    }

    @Override
    public int getItemCount() {
        return listMatHang.size();
    }

    public class ItemRowDetailHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemDanhsachmathangchitietBinding binding;

        private ItemRowDetailHolder(ItemDanhsachmathangchitietBinding view) {
            super(view.getRoot());
            binding = view;
            binding.getRoot().setOnClickListener(this);
        }

        public void setBinding(MatHang matHang) {
            binding.setMathang(matHang);
        }

        @Override
        public void onClick(View v) {
            onItemClickedListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public class ItemRowSimpleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemDanhsachmathangrutgonBinding binding;

        private ItemRowSimpleHolder(ItemDanhsachmathangrutgonBinding view) {
            super(view.getRoot());
            binding = view;
            view.getRoot().setOnClickListener(this);
        }

        public void setBinding(MatHang matHang) {
            binding.setMathang(matHang);
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

    @Override
    public int getItemViewType(int position) {
        int type = listMatHang.get(position).getType();
        return type;
    }
}
