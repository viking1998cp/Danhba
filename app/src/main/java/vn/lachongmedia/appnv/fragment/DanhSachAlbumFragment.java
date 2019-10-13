package vn.lachongmedia.appnv.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.activity.AlbumActivity;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerDanhSachAlbum;
import vn.lachongmedia.appnv.databinding.FragmentDanhsachalbumBinding;
import vn.lachongmedia.appnv.evenbus.GuiAnhEvent;
import vn.lachongmedia.appnv.mannager.DataBaseHanlder;
import vn.lachongmedia.appnv.object.AlbumObject;
import vn.lachongmedia.appnv.object.ImageAlbumObject;
import vn.lachongmedia.appnv.viewmodel.DanhSachAlbumViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tungda .
 */
public class DanhSachAlbumFragment extends Fragment {
    public DanhSachAlbumFragment() {

    }

    FragmentDanhsachalbumBinding binding;
    private DataBaseHanlder db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_danhsachalbum, container, false);
        danhSachAlbumViewModel = ViewModelProviders.of(this).get(DanhSachAlbumViewModel.class);
        binding.rvDanhSachAlbum.setLayoutManager(new LinearLayoutManager(getActivity()));
        EventBus.getDefault().register(this);
        getDanhSachAlbum();
        db = DataBaseHanlder.getInstance(KsmartSalesApplication.getInstance());
        return binding.getRoot();
    }

    private ProgressDialog progressDialog;
    private DanhSachAlbumViewModel danhSachAlbumViewModel;
    AdapterRecyclerDanhSachAlbum adapterRecyclerDanhSachAlbum;
    private ArrayList<AlbumObject> listAlbum=new ArrayList<>();

    public void getDanhSachAlbum() {
        try {
            progressDialog = ProgressDialog.show(getActivity(), "", getString(R.string.dialog_waiting), true, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        params.put("type", "danhsach");
        params.put("tungay", Common.getNgayHienTaiHai());
        params.put("denngay", Common.getNgayHienTaiHai());
        params.put("trangthaigps", "" + Common.checkGPS());
        danhSachAlbumViewModel.getListAlbum(params).observe(this, new Observer<ArrayList<AlbumObject>>() {
            @Override
            public void onChanged(@Nullable final ArrayList<AlbumObject> listAlbumTemp) {
                try {
                    progressDialog.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                listAlbum.clear();
                if (listAlbumTemp != null) {

                    for (AlbumObject albumObjectTemp : listAlbumTemp) {
                        albumObjectTemp.setType(1);
                        if (albumObjectTemp.getTencuahang().length() == 0) {
                            albumObjectTemp.setTencuahang(getResources().getString(R.string.title_take_image_free));
                        }

                        if (!db.isContainALBUM(albumObjectTemp.getId_server())) {
                            db.insertALBUM(albumObjectTemp);

                        } else {
                            AlbumObject albumObject = db.select1ALBUMbyidServer(albumObjectTemp.getId_server());
                            albumObjectTemp.setType(albumObject.getType());
                            db.updateAlbumServer(albumObjectTemp);
                        }
                    }
                    try {
                        ArrayList<AlbumObject> list_adb = new ArrayList<>();

                        try {
                            list_adb = db.selectSomeALBUMTheoNgay(SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class), 0, Common.getNgayHienTai(), Common.getNgayHienTai());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (list_adb != null && list_adb.size() > 0) {
                            for (AlbumObject albumObject : list_adb) {
                                albumObject.setListImage((ArrayList<ImageAlbumObject>) db.selectSomeImageALBUM(albumObject.getId()));
                            }
                            listAlbum.addAll(0, list_adb);
                            // Log.d("ListSize", "" + list_album.size());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    adapterRecyclerDanhSachAlbum = new AdapterRecyclerDanhSachAlbum(listAlbum);
                    binding.rvDanhSachAlbum.setAdapter(adapterRecyclerDanhSachAlbum);
                    adapterRecyclerDanhSachAlbum.setOnItemClickedListener(new AdapterRecyclerDanhSachAlbum.OnItemClickedListener() {
                        @Override
                        public void onItemClick(int postion, View v) {
                            AlbumObject albumObject = listAlbum.get(postion);
                            Intent intent = new Intent(getContext(), AlbumActivity.class);
                            intent.putExtra("album", albumObject);
                            intent.putExtra("type", "xem");
                            //intent.putExtra("chucnangalbum", loaiChucNangAlbum);
                            startActivityForResult(intent, 1);
                        }
                    });
                } else {
                    listAlbum = new ArrayList<AlbumObject>();
                    adapterRecyclerDanhSachAlbum = new AdapterRecyclerDanhSachAlbum(listAlbum);
                    binding.rvDanhSachAlbum.setAdapter(adapterRecyclerDanhSachAlbum);
                }
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(GuiAnhEvent guiAnhEvent) {
        try {

            if (guiAnhEvent.getState() == 1) {
                UpdateListView(guiAnhEvent.getIdAlbum());
            }else  if (guiAnhEvent.getState() == 2) {
                refresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * target: load lại activity
     */
    public void refresh() {
     getDanhSachAlbum();

    }

    /**
     * @param id target: update trạng thái gửi ảnh của album trong quá trình gửi ảnh trong album
     */
    public void UpdateListView(int id) {
        for (AlbumObject albumObject : listAlbum) {
            if (albumObject.getId() == id) {
                try {
                    albumObject.getListImage().clear();
                    albumObject.setListImage((ArrayList<ImageAlbumObject>) db.selectSomeImageALBUM(id));
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (adapterRecyclerDanhSachAlbum != null) {
                                    adapterRecyclerDanhSachAlbum.notifyDataSetChanged();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
      //  EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
        EventBus.getDefault().removeStickyEvent(GuiAnhEvent.class);
        if(progressDialog!=null){
            progressDialog.dismiss();;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
