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
import android.widget.ArrayAdapter;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.databinding.ItemDanhsachtaoghichuBinding;
import vn.lachongmedia.appnv.object.CuaHang;
import vn.lachongmedia.appnv.object.GhiChu.DanhSachGhiChu;
import vn.lachongmedia.appnv.object.GhiChu.GhiChu;
import vn.lachongmedia.appnv.object.PhanHoi.DanhSachPhanHoi;

import java.util.ArrayList;
/**
 * Created by tungda .
 */
public class AdapterRecyclerTaoGhiChu extends RecyclerView.Adapter<AdapterRecyclerTaoGhiChu.ItemRowHolder> {
    private ArrayList<DanhSachGhiChu> listDanhSachGhiChus;
    private Context context;
    private OnItemClickedListener onItemClickedListener;
    private ArrayList<DanhSachGhiChu> duLieuGhiChu;
    public AdapterRecyclerTaoGhiChu( ArrayList<DanhSachGhiChu> listDanhSachGhiChus) {
        //type==1 xem chi tiết mặt hàng
        this.context = context;
        this.listDanhSachGhiChus = listDanhSachGhiChus;
        duLieuGhiChu = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDanhsachtaoghichuBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_danhsachtaoghichu, parent, false);
        return new AdapterRecyclerTaoGhiChu.ItemRowHolder(binding);
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachcuahang_checkin, null);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {
        DanhSachGhiChu danhSachGhiChu = listDanhSachGhiChus.get(position);
        holder.binding.cbTitle.setText(danhSachGhiChu.getTenCheckList());
        holder.binding.cbTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CCC", "onClick: BBB");
                if(holder.binding.cbTitle.isChecked()){
                    danhSachGhiChu.setChiTiet(holder.binding.editContent.getText().toString());
                    danhSachGhiChu.setIDQLLH(danhSachGhiChu.getIDQLLH());
                    danhSachGhiChu.setIDCheckList(danhSachGhiChu.getIDCheckList());
                    duLieuGhiChu.add(danhSachGhiChu);
                    Log.d("BBB", "onClick: "+duLieuGhiChu.size());
                }
            }
        });
        holder.binding.editContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                for(int i=0;i<duLieuGhiChu.size();i++){
                    if(duLieuGhiChu.get(i).getIDCheckList()==danhSachGhiChu.getIDCheckList()){
                        duLieuGhiChu.get(i).setChiTiet(s.toString().trim());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final String[] path = {"0","10","20","30" ,"40", "50", "60","70","80","90","100"};
        ArrayAdapter<String> adapterStatusJob = new ArrayAdapter<String>(holder.binding.getRoot().getContext(), android.R.layout.simple_list_item_1, path);
        holder.binding.spProgress.setAdapter(adapterStatusJob);


    }

    @Override
    public int getItemCount() {
        return listDanhSachGhiChus.size();
    }

    public ArrayList<DanhSachGhiChu> getDuLieuPhanHoi (){
        return duLieuGhiChu;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemDanhsachtaoghichuBinding binding;

        private ItemRowHolder(ItemDanhsachtaoghichuBinding view) {
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
