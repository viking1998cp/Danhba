package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.activity.GiaoHangActivity;
import vn.lachongmedia.appnv.activity.ThanhToanActivity;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;

/**
 * Created by tungda .
 */
public class AdapterRecyclerDanhSachXuLyDonHang extends RecyclerView.Adapter<AdapterRecyclerDanhSachXuLyDonHang.ItemRowHolder> {
    private ArrayList<CuaHang> listCuaHang;
    private Context context;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerDanhSachXuLyDonHang(Context context, ArrayList<CuaHang> listCuaHang) {
        this.context = context;
        this.listCuaHang = listCuaHang;
    }
    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachcuahang_checkin, null);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachxulydonhang, null);
       /* v.setLayoutParams(new RecyclerView.LayoutParams(
                ((RecyclerView) parent).getLayoutManager().getWidth(),
               ViewGroup.LayoutParams.WRAP_CONTENT));*/
        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return listCuaHang.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       private LinearLayout llGiaoHang;
        private LinearLayout llThanhtoan;
        private ItemRowHolder(View view) {
            super(view);
            llGiaoHang=view.findViewById(R.id.llGiaoHang);
            llGiaoHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  context.startActivity(new Intent(context, GiaoHangActivity.class));
                }
            });
            view.setOnClickListener(this);
            llThanhtoan=view.findViewById(R.id.llThanhtoan);
            llThanhtoan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ThanhToanActivity.class));
                }
            });
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
