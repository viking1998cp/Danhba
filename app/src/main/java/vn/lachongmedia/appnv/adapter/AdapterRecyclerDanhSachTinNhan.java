package vn.lachongmedia.appnv.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.databinding.ItemDanhsachtinnhanBinding;

import vn.lachongmedia.appnv.object.TinNhanGroup;

import java.util.ArrayList;

/**
 * Created by tungda .
 */
public class AdapterRecyclerDanhSachTinNhan extends RecyclerView.Adapter<AdapterRecyclerDanhSachTinNhan.ItemRowHolder> {
    private ArrayList<TinNhanGroup> tinNhanGroupArrayList;
    private FragmentActivity context;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerDanhSachTinNhan(ArrayList<TinNhanGroup> tinNhanGroupArrayList) {
        this.context = context;
        this.tinNhanGroupArrayList = tinNhanGroupArrayList;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      /*  v.setLayoutParams(new RecyclerView.LayoutParams(
                ((RecyclerView) parent).getLayoutManager().getWidth(),
                ViewGroup.LayoutParams.WRAP_CONTENT));*/
        ItemDanhsachtinnhanBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_danhsachtinnhan, parent, false);
        return new ItemRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {
        holder.setBinding(tinNhanGroupArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return tinNhanGroupArrayList.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemDanhsachtinnhanBinding itemDanhsachtinnhanBinding;

        private ItemRowHolder(ItemDanhsachtinnhanBinding binding) {
            super(binding.getRoot());
            itemDanhsachtinnhanBinding = binding;
            binding.getRoot().setOnClickListener(this);

        }

        public void setBinding(TinNhanGroup tinNhanGroup) {
            itemDanhsachtinnhanBinding.setTinnhangroup(tinNhanGroup);
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
