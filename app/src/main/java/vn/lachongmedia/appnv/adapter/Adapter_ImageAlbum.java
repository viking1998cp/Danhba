package vn.lachongmedia.appnv.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.activity.Activity_ChupAnh_New;
import vn.lachongmedia.appnv.mannager.DataBaseHanlder;
import vn.lachongmedia.appnv.object.AlbumObject;
import vn.lachongmedia.appnv.object.ImageAlbumObject;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;


public class Adapter_ImageAlbum extends ArrayAdapter {
    private final Context context;
    private ArrayList<ImageAlbumObject> data = new ArrayList<>();
    private final int Resid;
    private final AlbumObject albumObject;
    private final Activity activity;
    private final TextView countImageAlbum;
    private int baocaodonhang = 0;

    public Adapter_ImageAlbum(Context context, TextView countImage, ArrayList<ImageAlbumObject> data, Activity activity, AlbumObject albumObject) {
        super(context, R.layout.grid_item_imagealbum, data);
        this.context = context;
        this.data = data;
        this.Resid = R.layout.grid_item_imagealbum;
        this.activity = activity;
        this.albumObject = albumObject;
        this.countImageAlbum = countImage;
        this.baocaodonhang = 0;
    }

    public Adapter_ImageAlbum(Context context, TextView countImage, ArrayList<ImageAlbumObject> data, Activity activity, AlbumObject albumObject, int baocaodonhang) {
        super(context, R.layout.grid_item_imagealbum, data);
        this.context = context;
        this.data = data;
        this.Resid = R.layout.grid_item_imagealbum;
        this.activity = activity;
        this.albumObject = albumObject;
        this.countImageAlbum = countImage;
        this.baocaodonhang = baocaodonhang;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(Resid, parent, false);
            holder = new ViewHolder();
            holder.img = (ImageView)convertView.findViewById(R.id.img_suvu);
            holder.bt_delete = (ImageView)convertView.findViewById(R.id.img_status);
            holder.fr_image = (FrameLayout)convertView.findViewById(R.id.fr_image);
            holder.pb_upload =(ProgressBar) convertView.findViewById(R.id.pd_uploadimage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        try {
            final ImageAlbumObject imageObject = data.get(position);

            if (imageObject.isLocalFile()) {
                try {
                    if (imageObject.getType() == 3) {
                        Picasso.with(activity)
                                .load(imageObject.getIdimage()).resize(80, 106)
                                //.skipMemoryCache()
                                .placeholder(R.drawable.loading)
                                .into(holder.img);
                    } else {
                        File path_ksmart = new File(Environment.getExternalStorageDirectory(), "Ksmart");
                        File sourceFile = new File(path_ksmart.getPath() + "/" + imageObject.getPath());
                        if (sourceFile.exists()) {
                            Picasso.with(activity)
                                    .load(sourceFile).resize(80, 106)
                                    //.skipMemoryCache()
                                    .placeholder(R.drawable.loading)
                                    .into(holder.img);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    if (imageObject.getPath_thumbnail_small().contains("http")) {
                        try {
                            Picasso.with(activity)
                                    .load(Uri.parse(imageObject.getPath_thumbnail_small())).resize(80, 106)
                                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                                    .networkPolicy(NetworkPolicy.NO_CACHE)
                                    .placeholder(R.drawable.loading)
                                    .into(holder.img);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        File path_ksmart = new File(Environment.getExternalStorageDirectory(), "Ksmart");
                        File sourceFile = new File(path_ksmart.getPath() + "/" + imageObject.getPath());
                        if (sourceFile.exists()) {
                            Picasso.with(activity)
                                    .load(sourceFile).resize(80, 106)
                                    //.skipMemoryCache()
                                    .placeholder(R.drawable.loading)
                                    .into(holder.img);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (imageObject.getType() == 1 || imageObject.getType() == 0) {
                holder.bt_delete.setVisibility(View.VISIBLE);
            } else if (imageObject.getType() == 2 || imageObject.getType() == 3) {
                holder.bt_delete.setVisibility(View.INVISIBLE);
            }
            if (imageObject.getType() == 0) {
                holder.bt_delete.setImageResource(R.drawable.ic_new_image);
                holder.pb_upload.setVisibility(View.VISIBLE);
            } else if (imageObject.getType() == 1) {
                holder.bt_delete.setImageResource(R.drawable.ic_delete);
                holder.pb_upload.setVisibility(View.GONE);
            }

            if (imageObject.getType() == 3) {
                holder.fr_image.setBackgroundColor(Color.parseColor("#d8d8d8"));
                holder.pb_upload.setVisibility(View.GONE);

            } else {
                if (imageObject.getIsUpload() == 2 || imageObject.getIsUpload() == 0) {
                    holder.pb_upload.setVisibility(View.GONE);
                } else {
                    holder.pb_upload.setVisibility(View.VISIBLE);
                    if (imageObject.getPercent() == 100) {
                        holder.pb_upload.setVisibility(View.GONE);
                    } else {
                        holder.pb_upload.setVisibility(View.VISIBLE);
                        holder.pb_upload.setProgress(imageObject.getPercent());
                    }
                }

            }

            holder.bt_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imageObject.getType() == 1) {

                        AlertDialog.Builder aBuilder = new AlertDialog.Builder(context);
                        aBuilder.setMessage(activity.getResources().getString(R.string.dialog_want_delete_image));
                        aBuilder.setPositiveButton(activity.getResources().getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    deleteImage(imageObject.getPath());
                                    DataBaseHanlder db = DataBaseHanlder.getInstance(activity);
                                    db.deleteImageALBUM(imageObject.getPath());
                                    data.remove(position);
                                    if (data.size() >= 1) {
                                        countImageAlbum.setText(activity.getResources().getString(R.string.title_image) + " " + (data.size() - 1));
                                    }
                                    notifyDataSetChanged();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                        aBuilder.setNegativeButton(activity.getResources().getString(R.string.dialog_not_acess), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = aBuilder.create();
                        dialog.show();
                    }

                }
            });
            final ImageAlbumObject temp = data.get(position);

            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getType() == 1) {
                            data.get(i).setType(0);
                        }
                    }
                    if (temp.getType() == 3) {
                        if (albumObject.getType() != 2) {
                            Intent intent = new Intent(activity, Activity_ChupAnh_New.class);
                            intent.putExtra("idalbum", albumObject.getId());
                            intent.putExtra("daily", albumObject.getTencuahang());
                            activity.startActivityForResult(intent, 1);
                        } else {
                            try {
                                Toast.makeText(activity, "Vui lòng đợi ảnh gửi xong, bạn mới chụp được ảnh tiếp", Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        /*Intent intent = new Intent(activity, Activity_ChiTietAnhChup_new.class);
                        // intent.putExtra("image",temp);
                        intent.putExtra("type_album", albumObject.getType());
                        intent.putExtra("image_type", temp.getType());
                        intent.putExtra("idimage", temp.getIdimage());
                        intent.putExtra("diachi", albumObject.getDiachi());
                        intent.putExtra("idalbum", albumObject.getId());
                        intent.putExtra("daily", temp.getTendaily());
                        intent.putExtra("path", temp.getPath());
                        intent.putExtra("kinhdo", temp.getKinhdo());
                        intent.putExtra("vido", temp.getVido());
                        intent.putExtra("thoigiangui", temp.getThoigiangui());
                        intent.putExtra("thoigianchup_album", temp.getThoigianchup());
                        intent.putExtra("ghichu", temp.getGhichu());
                        intent.putExtra("type", "album");
                        if (baocaodonhang == 1) {
                            intent.putExtra("baocaodonhang", baocaodonhang);
                        }
                        if (albumObject.getType() == 1) {
                            Check.listImage = albumObject.getListImage();
                        }
                        activity.startActivityForResult(intent, 1);*/
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }


    class ViewHolder {
        ImageView img, bt_delete;
        FrameLayout fr_image;
        ProgressBar pb_upload;
    }

    private void deleteImage(String filename) {
        try {
            File path_Ksmart = new File(Environment.getExternalStorageDirectory(), "Ksmart");
            File to_delete = new File(path_Ksmart.getPath() + "/" + filename);
            if (to_delete.delete()) {
                Common.deleteFileFromMediaStore(activity.getContentResolver(), to_delete);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
