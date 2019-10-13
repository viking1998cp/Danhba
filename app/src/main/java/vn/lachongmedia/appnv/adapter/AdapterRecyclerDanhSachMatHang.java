package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;
/**
 * Created by tungda .
 */
public class AdapterRecyclerDanhSachMatHang extends RecyclerView.Adapter<AdapterRecyclerDanhSachMatHang.ItemRowHolder> {
    private ArrayList<CuaHang> listCuaHang;
    private Context context;
    private int type = 0;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerDanhSachMatHang(Context context, ArrayList<CuaHang> listCuaHang) {
        //type==1 xem chi tiết mặt hàng
        this.context = context;
        this.listCuaHang = listCuaHang;
        this.type = type;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachmathang, null);

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
        private ItemRowHolder(View view) {
            super(view);

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
    @Override
    public int getItemViewType(int position) {
        int type = listCuaHang.get(position).getType();
        return type;
    }
}
