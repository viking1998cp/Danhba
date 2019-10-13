package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
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
public class AdapterRecyclerLichSuGiaoHang extends RecyclerView.Adapter<AdapterRecyclerLichSuGiaoHang.ItemRowHolder> {
    private ArrayList<CuaHang> listCuaHang;
    private Context context;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerLichSuGiaoHang(Context context, ArrayList<CuaHang> listCuaHang) {
        this.context = context;
        this.listCuaHang = listCuaHang;
    }
    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachcuahang_checkin, null);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dotgiaohang, null);
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
}
