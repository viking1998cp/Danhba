package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.databinding.ListItemKehoachBinding;
import vn.lachongmedia.appnv.object.KeHoachOBJ;

import java.util.ArrayList;

/**
 * Created by tungda .
 */
public class AdapterRecyclerKehoachTrongNgay extends RecyclerView.Adapter<AdapterRecyclerKehoachTrongNgay.ItemRowHolder> {
    private ArrayList<KeHoachOBJ> listKeHoach;
    private Context context;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerKehoachTrongNgay(ArrayList<KeHoachOBJ> listCuaHang) {
        this.context = KsmartSalesApplication.getInstance();
        this.listKeHoach = listCuaHang;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListItemKehoachBinding listItemKehoachBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.list_item_kehoach,parent,false);
      /*  listItemKehoachBinding.getRoot().setLayoutParams(new RecyclerView.LayoutParams(
                ((RecyclerView) parent).getLayoutManager().getWidth(),
                ViewGroup.LayoutParams.WRAP_CONTENT));*/
        return new ItemRowHolder(listItemKehoachBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {
        holder.setBinding(listKeHoach.get(position));
    }

    @Override
    public int getItemCount() {
        return listKeHoach.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ListItemKehoachBinding listItemKehoachBinding;
        public ItemRowHolder(@NonNull ListItemKehoachBinding view) {
            super(view.getRoot());
            listItemKehoachBinding=view;
            listItemKehoachBinding.getRoot().setOnClickListener(this);

        }
        public void setBinding(KeHoachOBJ keHoachOBJtemp) {
            listItemKehoachBinding.setKehoach(keHoachOBJtemp);
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
