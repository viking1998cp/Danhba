package vn.lachongmedia.appnv.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.databinding.ItemDanhsachkhachhangBinding;

import vn.lachongmedia.appnv.object.Khachhang.Data;


import java.util.ArrayList;
/**
 * Created by tungda .
 */
public class AdapterRecyclerDanhSachKhachHang extends RecyclerView.Adapter<AdapterRecyclerDanhSachKhachHang.ItemRowHolder> {

    private ArrayList<Data> listData;
    private Context context;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerDanhSachKhachHang(ArrayList<Data> listData) {
        //type==1 xem chi tiết mặt hàng
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDanhsachkhachhangBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_danhsachkhachhang, parent, false);
        return new AdapterRecyclerDanhSachKhachHang.ItemRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {
        Data data = listData.get(position);
        holder.binding.tvName.setText(data.getTenCuaHang());
        holder.binding.tvAddress.setText(data.getDiaChi());
        holder.binding.tvEmail.setText(data.getEmail());
        holder.binding.tvPhoneNumber.setText(data.getDienThoai());
        ArrayList listChucNang = new ArrayList();
        listChucNang.add("Lịch hẹn");
        listChucNang.add("Công nợ");
        listChucNang.add("Chỉ đường");

        ArrayAdapter adapterXa = new ArrayAdapter(holder.binding.getRoot().getContext(),
                R.layout.support_simple_spinner_dropdown_item, listChucNang);
        adapterXa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.binding.spChucNang.setAdapter(adapterXa);
        holder.binding.spChucNang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //getSubCheckListTheoDiem(String.valueOf(listCongViec.get(position).getId_checklist()));
                //getDanhSachDiemTuyen(listCongViec.get(position).getiD_TuyenTuanTra());
                ((TextView)view).setText("");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemDanhsachkhachhangBinding binding;

        private ItemRowHolder(ItemDanhsachkhachhangBinding view) {
            super(view.getRoot());
            binding = view;
            view.getRoot().setOnClickListener(this);
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
