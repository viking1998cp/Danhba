package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
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
    private ArrayList<DanhSachPhanHoi> duLieuPhanHoi;
    public AdapterRecyclerTaoPhanHoi( ArrayList<DanhSachPhanHoi> listDanhSachPhanHois) {
        //type==1 xem chi tiết mặt hàng
        this.context = context;
        this.listDanhSachPhanHois = listDanhSachPhanHois;
        duLieuPhanHoi = new ArrayList<>();
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
        holder.binding.cbTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CCC", "onClick: BBB");
                if(holder.binding.cbTitle.isChecked()){
                    danhSachPhanHoi.setChiTiet(holder.binding.editContent.getText().toString());
                    danhSachPhanHoi.setIDQLLH(danhSachPhanHoi.getIDQLLH());
                    danhSachPhanHoi.setIDPhanHoi(danhSachPhanHoi.getIDPhanHoi());
                    duLieuPhanHoi.add(danhSachPhanHoi);
                    Log.d("BBB", "onClick: "+duLieuPhanHoi.size());
                }
            }
        });
        holder.binding.editContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                for(int i=0;i<duLieuPhanHoi.size();i++){
                    if(duLieuPhanHoi.get(i).getIDPhanHoi()==danhSachPhanHoi.getIDPhanHoi()){
                        duLieuPhanHoi.get(i).setChiTiet(s.toString().trim());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return listDanhSachPhanHois.size();
    }

    public ArrayList<DanhSachPhanHoi> getDuLieuPhanHoi (){
        return duLieuPhanHoi;
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
