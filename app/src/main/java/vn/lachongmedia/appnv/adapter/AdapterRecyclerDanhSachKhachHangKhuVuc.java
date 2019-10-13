package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;
/**
 * Created by tungda .
 */
public class AdapterRecyclerDanhSachKhachHangKhuVuc extends RecyclerView.Adapter<AdapterRecyclerDanhSachKhachHangKhuVuc.ItemRowHolder> {
    private ArrayList<CuaHang> listCuaHang;
    private Context context;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerDanhSachKhachHangKhuVuc(Context context, ArrayList<CuaHang> listCuaHang) {
        this.context = context;
        this.listCuaHang = listCuaHang;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachcuahang_checkin, null);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachkhachhangkhuvuc, null);
        v.setLayoutParams(new RecyclerView.LayoutParams(
                ((RecyclerView) parent).getLayoutManager().getWidth(),
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {
        /*ArrayList<String> listChucNang = new ArrayList<>();
        listChucNang.add("Lịch hẹn");
        listChucNang.add("Công nợ");
        listChucNang.add("Chỉ đường");

        ArrayAdapter adapterXa = new ArrayAdapter(context,
                R.layout.support_simple_spinner_dropdown_item, listChucNang);
        adapterXa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spChucNang.setAdapter(adapterXa);
        holder.spChucNang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //getSubCheckListTheoDiem(String.valueOf(listCongViec.get(position).getId_checklist()));
                //getDanhSachDiemTuyen(listCongViec.get(position).getiD_TuyenTuanTra());
                ((TextView)view).setText("");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return listCuaHang.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatSpinner spChucNang;

        private ItemRowHolder(View view) {
            super(view);
            this.spChucNang = view.findViewById(R.id.spChucNang);
            view.setOnClickListener(this);

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
