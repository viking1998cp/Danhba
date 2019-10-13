package vn.lachongmedia.appnv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.databinding.ItemDanhsachalbumBinding;

import vn.lachongmedia.appnv.mannager.DataBaseHanlder;
import vn.lachongmedia.appnv.object.AlbumObject;
import vn.lachongmedia.appnv.object.ImageAlbumObject;

import java.util.ArrayList;

/**
 * Created by tungda on 7/22/2019.
 */
public class AdapterRecyclerDanhSachAlbum extends RecyclerView.Adapter<AdapterRecyclerDanhSachAlbum.ItemRowHolder> {
    private ArrayList<AlbumObject> listAlbum;
    private Context context;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerDanhSachAlbum(ArrayList<AlbumObject> listAlbum) {
        this.context = KsmartSalesApplication.getInstance();
        this.listAlbum = listAlbum;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemDanhsachalbumBinding itemDanhsachalbumBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_danhsachalbum, parent, false);
      /*  listItemKehoachBinding.getRoot().setLayoutParams(new RecyclerView.LayoutParams(
                ((RecyclerView) parent).getLayoutManager().getWidth(),
                ViewGroup.LayoutParams.WRAP_CONTENT));*/
        return new ItemRowHolder(itemDanhsachalbumBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {
        holder.setBinding(listAlbum.get(position), position);

    }

    @Override
    public int getItemCount() {
        return listAlbum.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemDanhsachalbumBinding itemDanhsachalbumBinding;

        public ItemRowHolder(@NonNull ItemDanhsachalbumBinding view) {
            super(view.getRoot());
            itemDanhsachalbumBinding = view;
            itemDanhsachalbumBinding.getRoot().setOnClickListener(this);

        }

        public void setBinding(AlbumObject albumObject, int postion) {
            itemDanhsachalbumBinding.setAlbum(albumObject);
            if(albumObject.getType()==1){
                itemDanhsachalbumBinding.pbAlbum.setVisibility(View.GONE);
            }else{
                itemDanhsachalbumBinding.pbAlbum.setVisibility(View.GONE);
            }
            //binding.pbAlbum.setProgress(80);
            if(albumObject.getType()==2){
            setProgressBar(postion, itemDanhsachalbumBinding.pbAlbum);
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

    private void setProgressBar(int position, ProgressBar progressBar) {
        AlbumObject albumObject = listAlbum.get(position);
        ArrayList<ImageAlbumObject> list_image = (ArrayList<ImageAlbumObject>) DataBaseHanlder.getInstance(KsmartSalesApplication.getInstance())
                .selectSomeImageALBUM(albumObject.getId());
        int count = 0;
        for (ImageAlbumObject image : list_image) {
            if (image.getType() == 2) {
                count++;
            }
        }
        int per = 0;

        if (list_image.size() == 0) {
            progressBar.setVisibility(View.GONE);
        } else {
            per = count * 100 / list_image.size();
            if (albumObject.getId_server() != 0) {
                if (per < 100) {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(per);
                    // Log.e("Percent", per + "");

                } else {
                    ////////////////// xoa anh khi da gui het len server //////////////////
                   /* DataBaseHanlder db = DataBaseHanlder.getInstance(activity);
                    try {
                        ArrayList<ImageAlbumObject> list_image_hoso = (ArrayList<ImageAlbumObject>) db.selectSomeImageALBUM(datasource.get(position).getId());

                        if (list_image_hoso != null && list_image_hoso.size() > 0) {
                            for (ImageAlbumObject image : list_image_hoso) {
                                HamDungChung.deleteImage(image.getPath(), activity);
                            }
                        }
                        db.deleteALBUM(datasource.get(position).getId());
                        db.deleteAllImageALBUM(datasource.get(position).getId());
                        datasource.remove(position);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                    adapter = new Adapter_Album(activity, datasource, swipeListView);
                    swipeListView.setAdapter(adapter);
                    notifyDataSetChanged();
                    Activity_Album.checkguianh = false;
                    if (view.getContext() instanceof Activity_DanhSachAnhDaChup) {
                        view.getContext().startActivity(new Intent(view.getContext(), Activity_DanhSachAnhDaChup.class));
                    } else {
                        view.getContext().startActivity(new Intent(view.getContext(), Activity_BaoCaoAnhChup.class));
                    }*/
                    ////////////////// het phan xoa anh khi da gui len server //////////////////

                    progressBar.setVisibility(View.GONE);
                    progressBar.setProgress(per);
                    // Log.e("Percent", per + "");
                }

            } else {
                progressBar.setVisibility(View.GONE);
            }
        }
    }
}
