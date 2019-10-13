package vn.lachongmedia.appnv.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.databinding.ItemDanhsachlichhenBinding;
import vn.lachongmedia.appnv.object.LichHenObject;

import java.util.ArrayList;

/**
 * Created by tungda on 7/19/2019.
 */
public class AdapterReCyclerSwipeLichHen extends RecyclerSwipeAdapter<AdapterReCyclerSwipeLichHen.ItemLichHenHolder> {
    ArrayList<LichHenObject> listLichHen;
    private OnItemClickedListener onItemClickedListener;
    public AdapterReCyclerSwipeLichHen(ArrayList<LichHenObject> listLichHen) {
        this.listLichHen = listLichHen;
    }

    @Override
    public ItemLichHenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDanhsachlichhenBinding itemDanhsachlichhenBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_danhsachlichhen, parent, false);
        return new ItemLichHenHolder(itemDanhsachlichhenBinding);
    }

    @Override
    public void onBindViewHolder(ItemLichHenHolder viewHolder, int position) {
        viewHolder.setBinding(listLichHen.get(position));

    }

    @Override
    public int getItemCount() {
        return listLichHen.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.llLichHen;
    }

    public class ItemLichHenHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        ItemDanhsachlichhenBinding itemDanhsachlichhenBinding;

        private ItemLichHenHolder(ItemDanhsachlichhenBinding itemView) {
            super(itemView.getRoot());
            itemDanhsachlichhenBinding = itemView;
            itemDanhsachlichhenBinding.getRoot().setOnClickListener(this);

        }

        public void setBinding(LichHenObject lichHenObject) {
            itemDanhsachlichhenBinding.setLichhen(lichHenObject);
            if(lichHenObject.getTrangthai()==0){
                //chua xử lý
                itemDanhsachlichhenBinding.ivTrangThai.setBackgroundDrawable(KsmartSalesApplication.getInstance().getResources().getDrawable(R.drawable.background_circle_yellow));
            }else if(lichHenObject.getTrangthai()==1){
                //hoàn thành lich hen
                itemDanhsachlichhenBinding.ivTrangThai.setBackgroundDrawable(KsmartSalesApplication.getInstance().getResources().getDrawable(R.drawable.background_circle_green));
            }else if(lichHenObject.getTrangthai()==2){
                //xóa lich hen
                itemDanhsachlichhenBinding.ivTrangThai.setBackgroundDrawable(KsmartSalesApplication.getInstance().getResources().getDrawable(R.drawable.background_circle_red));
            }

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
