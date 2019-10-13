package vn.lachongmedia.appnv.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.KsmartSalesApplication;
import vn.lachongmedia.appnv.MainActivity;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.adapter.Adapter_ImageAlbum;
import vn.lachongmedia.appnv.databinding.ActivityAlbumanhBinding;
import vn.lachongmedia.appnv.mannager.DataBaseHanlder;
import vn.lachongmedia.appnv.network.CuaHang;
import vn.lachongmedia.appnv.network.Login.LoaiAlbum;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.network.chupanh.SendAlbumRespon;
import vn.lachongmedia.appnv.object.AlbumObject;
import vn.lachongmedia.appnv.object.Check;
import vn.lachongmedia.appnv.object.ImageAlbumObject;
import vn.lachongmedia.appnv.object.flagobj;
import vn.lachongmedia.appnv.service.UploadImageService;
import vn.lachongmedia.appnv.viewmodel.AlbumViewModel;
import vn.lachongmedia.appnv.viewmodel.CuaHangGanNhatViewModel;
import vn.lachongmedia.appnv.viewmodel.SendAlbumViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tungda .
 */
public class AlbumActivity extends AppCompatActivity {
    ActivityAlbumanhBinding binding;
    AlbumViewModel albumViewModel;
    SendAlbumViewModel sendAlbumViewModel;
    CuaHangGanNhatViewModel cuaHangGanNhatViewModel;
    private AlbumObject album;
    private ArrayList<ImageAlbumObject> list_image = new ArrayList<>();
    private ArrayList<ImageAlbumObject> list_image_server = new ArrayList<>();
    private Adapter_ImageAlbum adapter_image;
    private Bundle bundle;
    private FusedLocationProviderClient mFusedLocationClient;
    private String type = "";
    private int idalbum = 0;
    private DataBaseHanlder db;
    private int idnv;
    private int loaiChucNangAlbum = 0;
    /*loaiChucNangAlbum=0 là chụp ảnh cho khách hàng
     * loaiChucNangAlbum=1 là chụp ảnh cho biển bảng
     * loaiChucNangAlbum=2 là chụp ảnh cho sản phẩm*/
    private int flagCheckInChupAnh = 0;
    //task là số task cần thực hiên
    private int Tasks = 0;
    public static Boolean flagsua = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_albumanh);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        albumViewModel = ViewModelProviders.of(this).get(AlbumViewModel.class);
        sendAlbumViewModel = ViewModelProviders.of(this).get(SendAlbumViewModel.class);
        cuaHangGanNhatViewModel = ViewModelProviders.of(this).get(CuaHangGanNhatViewModel.class);
        db = DataBaseHanlder.getInstance(KsmartSalesApplication.getInstance());
        idnv = SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class);
        Common.Current_Activity=AlbumActivity.this;
        initLoaiAlbum();
        getExtraData();
        back();

        sendAlbum();

    }

    private ArrayList<LoaiAlbum> listLoaiAlbum;

    private void getLoaiAlbum() {
        String searchPostCodeStr = SharedPrefs.getInstance().get(Common.KEY_LOAIALBUM, String.class);
        if (searchPostCodeStr != null && !searchPostCodeStr.equals("")) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<LoaiAlbum>>() {
            }.getType();
            listLoaiAlbum = gson.fromJson(searchPostCodeStr, listType);
        }
    }

    LoaiAlbum loaiAlbum;

    private void initLoaiAlbum() {
        getLoaiAlbum();
        ArrayList<String> listDanhMuc = new ArrayList<>();
        listDanhMuc.add(getString(R.string.khachhang));
        listDanhMuc.add(getString(R.string.sanpham));
        ArrayAdapter adapterXa = new ArrayAdapter(getApplicationContext(),
                R.layout.my_spinner, listLoaiAlbum);
        adapterXa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spLoaiAlbum.setAdapter(adapterXa);
        binding.spLoaiAlbum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listLoaiAlbum != null) {
                    loaiAlbum = listLoaiAlbum.get(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initDanhMuc() {
        ArrayList<String> listDanhMuc = new ArrayList<>();
        listDanhMuc.add(getString(R.string.khachhang));
        listDanhMuc.add(getString(R.string.sanpham));
        ArrayAdapter adapterXa = new ArrayAdapter(getApplicationContext(),
                R.layout.my_spinner, listDanhMuc);
        adapterXa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spDanhMuc.setAdapter(adapterXa);
        binding.spDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    loaiChucNangAlbum = 0;
                    binding.spLoaiAlbum.setVisibility(View.VISIBLE);
                } else if (position == 1) {
                    loaiChucNangAlbum = 2;
                    binding.spLoaiAlbum.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initEventChonKhachHang() {
        binding.spCuaHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try {
                    CuaHang item = (CuaHang) parent.getItemAtPosition(position);
                    album.setDiachicuahang(item.getDiaChi());
                    album.setId_khachhang(item.getIdcuahang());
                    album.setTencuahang(item.getTenCuaHang());
                    binding.tvDiaChiKhachHang.setText(album.getDiachicuahang());
                    if (item.getIdcuahang() == 0) {

                    } else {

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void sendAlbum() {
        binding.btGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check.check_status_sendimage = true;
                // Check.dungthemdaucongchupanh = true;
                if (album.getId_server() == 0) {
                    if (list_image.size() > 0) {
                        Tasks = getTaskCount();
                        if (Tasks != 0) {
                            sendAlbumExcute();
                        } else {
                            Common.ShowToastLong(getString(R.string.toast_message_album_condition_send));
                        }
                    } else {
                        Common.ShowToastLong(getString(R.string.toast_message_album_try_send));
                    }
                } else {
                    UploadImage();
                }
            }
        });
    }

    private void sendAlbumExcute() {
        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        params.put("idkhachhang", "" + album.getId_khachhang());
        params.put("kinhdo", "" + album.getKinhdo());
        params.put("vido", "" + album.getVido());
        params.put("diachi", "" + album.getDiachi());
        params.put("ghichu", "" + binding.edGhiChu.getText().toString());
        params.put("loai", "" + loaiChucNangAlbum);
        if (loaiChucNangAlbum == 0) {
            params.put("idloaialbum", "" + loaiAlbum.getID_LoaiAlbum());
        }
        sendAlbumViewModel.sendAlbum(params).observe(AlbumActivity.this, new Observer<SendAlbumRespon>() {
            @Override
            public void onChanged(SendAlbumRespon sendAlbumRespon) {
                if (sendAlbumRespon != null) {
                    if (sendAlbumRespon.isStatus()) {
                        try {
                            if (album.getType() == 0) {
                                album.setType(2);//type = 2 la dang gui
                                album.setId_server(sendAlbumRespon.getIdalbum());
                            }
                            db.updateALBUM(album);
                            // gửi ảnh
                            UploadImage();

                        } catch (Exception e) {
                            // e.printStackTrace();
                            Common.ShowToastLong(getString(R.string.error_message_170704));

                        }
                    } else {
                        Common.ShowToastLong(sendAlbumRespon.getMsg());
                    }
                }
            }
        });


    }

    private int getTaskCount() {
        int count = 0;
        for (ImageAlbumObject img : list_image) {
            if (img.getType() == 1 || img.getType() == 0) {
                count++;
            }
        }
        return count;
    }

    private void back() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getExtraData() {
        bundle = getIntent().getExtras();
        type = bundle.getString("type");
        switch (type) {
            case "xem":
                album = (AlbumObject) bundle.getSerializable("album");
                idalbum = album.getId();
                //binding.tvCuaHang.setText(album.getTencuahang());
                binding.tvDiaChiChupAnh.setText(album.getDiachi());
                binding.tvThoiGian.setText(album.getThoigiantao());
                binding.edGhiChu.setEnabled(false);
                binding.spCuaHang.setVisibility(View.GONE);
                binding.tvTenCuaHang.setVisibility(View.VISIBLE);
                binding.tvTenCuaHang.setText(album.getTencuahang());
                binding.edGhiChu.setText(album.getGhichu());
                loaiChucNangAlbum = album.getIdchucnangalbum();
                for (int i = 0; i < listLoaiAlbum.size(); i++) {
                    if (album.getIdloaialbum() == listLoaiAlbum.get(i).getID_LoaiAlbum()) {
                        binding.spLoaiAlbum.setSelection(i);
                        binding.spLoaiAlbum.setEnabled(false);
                    }
                }
                getAlbum();
                initDanhMuc();
                if (album.getId_khachhang() != 0) {
                    if (loaiChucNangAlbum == 0) {
                        binding.spDanhMuc.setSelection(0);
                        binding.spDanhMuc.setEnabled(false);
                    } else if (loaiChucNangAlbum == 2) {
                        binding.spDanhMuc.setSelection(1);
                        binding.spDanhMuc.setEnabled(false);
                    }
                }
                break;
            case "tao":
                createNewAlbum();
                binding.spCuaHang.setVisibility(View.VISIBLE);
                binding.tvTenCuaHang.setVisibility(View.GONE);


                break;
        }
    }

    /**
     * target: display grid view image
     */
    private void setDataToGrid() {
        adapter_image = new Adapter_ImageAlbum(this, binding.countImage, list_image, AlbumActivity.this, album);
        binding.gridAnh.setAdapter(adapter_image);
        getdata(list_image);
        ////////////// dem so luong anh /////////////////
        int count = 0;
        for (int i = 0; i < list_image.size(); i++) {
            if (list_image.get(i).getType() != 3) {
                count = count + 1;

            }
        }
        binding.countImage.setText(getResources().getString(R.string.title_image) + " " + count);
    }

    /**
     * @param data ImageAlbumObject
     *             target: update data
     */
    private void getdata(ArrayList<ImageAlbumObject> data) {
        try {
            data.clear();
            try {

                /*File path = new File(Environment.getExternalStorageDirectory(), "Ksmart");
                String[] fileNames = null;*/
                // de Bitmap icon ben ngoai if cho chup khi dang gui ben trong if khong cho chup khi dang gui
                /*Bitmap icon = BitmapFactory.decodeResource(Activity_Album.this.getResources(), R.drawable.plus);*/

                try {
                    Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
                    ImageAlbumObject add_Image = new ImageAlbumObject();
                    add_Image.setType(3);
                    add_Image.setIdimage(R.drawable.plus);
                    add_Image.setIdalbum(album.getId());
                    add_Image.setImg_bitmap(icon);
                    data.add(add_Image);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    ArrayList<ImageAlbumObject> list_image_album_db =
                            (ArrayList<ImageAlbumObject>) db.selectSomeImageALBUM(album.getId());

                    if (list_image_album_db != null && list_image_album_db.size() > 0) {
                        for (ImageAlbumObject image : list_image_album_db) {
                            if (image.getType() == 0) {
                                image.setThoigiangui("Chưa gửi");
                                data.add(image);
                            }

                        }
                    }

                    try {
                        List<ImageAlbumObject> uploadlist = db.selectSomeImageQUEUE(idnv);
                        for (ImageAlbumObject img : data) {
                            for (ImageAlbumObject image : uploadlist) {
                                if (img.getPath() != "" && img.getPath().equals(image.getPath())) {
                                    img.setPercent(0);
                                    img.setIsUpload();
                                }
                            }
                        }
                       /* HamDungChung.showLog("List<ImageAlbumObject> uploadlist = " +
                                "db.selectSomeImageQUEUE(idnv)" + uploadlist.size());*/
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (list_image_server.size() > 0) {
                        data.addAll(list_image_server);
                    }
                    adapter_image.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Check.listImage = data;
        album.setListImage(data);
        ////////////// dem so luong anh /////////////////
        int count = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getType() != 3) {
                count = count + 1;
                binding.countImage.setText(getResources().getString(R.string.title_image) + " " + count);
            }
        }
    }

    ProgressDialog progressDialog;

    public void getAlbum() {
        try {
            progressDialog = ProgressDialog.show(this, "", getString(R.string.dialog_waiting), true, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        params.put("type", "chitiet");
        params.put("idalbum", "" + album.getId_server());
        params.put("trangthaigps", "" + Common.checkGPS());
        albumViewModel.getListImage(params).observe(this, new Observer<ArrayList<ImageAlbumObject>>() {
            @Override
            public void onChanged(@Nullable ArrayList<ImageAlbumObject> listAlbum) {
                try {
                    progressDialog.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (listAlbum != null) {
                    list_image_server = listAlbum;
                    for (ImageAlbumObject temp : list_image_server) {
                        temp.setType(2);
                        temp.setPath(NetContext.getInstance().getBASE_URL()+temp.getPath());
                        temp.setPath_thumbnail_small(NetContext.getInstance().getBASE_URL()+temp.getPath_thumbnail_small());
                    }
                    setDataToGrid();
                } else {
                    list_image_server = new ArrayList<>();
                    setDataToGrid();
                }
            }
        });
    }

    private ArrayList<CuaHang> dscuahanggannhat = new ArrayList<>();

    @SuppressLint("MissingPermission")
    public void getDanhSachCuaHangGanNhat() {
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if (location != null) {
                    try {
                        progressDialog = ProgressDialog.show(AlbumActivity.this, "", getString(R.string.dialog_waiting), true, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    cuaHangGanNhatViewModel.getCuaHangGanNhat(location).observe(AlbumActivity.this, new Observer<ArrayList<CuaHang>>() {
                        @Override
                        public void onChanged(@Nullable ArrayList<CuaHang> listCuaHang) {
                            try {
                                progressDialog.cancel();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (listCuaHang != null) {
                                dscuahanggannhat = listCuaHang;
                                //khong co cua hang
                                CuaHang cuaHang = new CuaHang();
                                cuaHang.setIdcuahang(0);
                                cuaHang.setTenCuaHang(getResources().getString(R.string.title_take_image_free));
                                cuaHang.setDiaChi(Common.getDiaChi(location.getLatitude(), location.getLongitude()));
                                dscuahanggannhat.add(cuaHang);
                                ArrayAdapter adapterXa = new ArrayAdapter(getApplicationContext(),
                                        R.layout.my_spinner, dscuahanggannhat);
                                adapterXa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                binding.spCuaHang.setAdapter(adapterXa);
                            } else {
                                dscuahanggannhat = new ArrayList<>();
                                CuaHang cuaHang = new CuaHang();
                                cuaHang.setIdcuahang(0);
                                cuaHang.setTenCuaHang(getResources().getString(R.string.title_take_image_free));
                                cuaHang.setDiaChi(Common.getDiaChi(location.getLatitude(), location.getLongitude()));
                                dscuahanggannhat.add(cuaHang);
                                ArrayAdapter adapterXa = new ArrayAdapter(getApplicationContext(),
                                        R.layout.my_spinner, dscuahanggannhat);
                                adapterXa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                binding.spCuaHang.setAdapter(adapterXa);
                            }
                            initEventChonKhachHang();
                        }
                    });
                } else {

                }
            }
        });

    }

    public void UpdateListImage(int _idalbum, String path, int percent) {
        try {
            if (_idalbum == album.getId()) {
                if (percent < 100) {
                    for (ImageAlbumObject image : list_image) {
                        if (image.getPath().equals(path)) {
                            image.setIsUpload();
                            image.setPercent(percent);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        if (adapter_image != null) {
                                            adapter_image.notifyDataSetChanged();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        }
                    }
                } else {
                    for (ImageAlbumObject image : list_image) {
                        if (image.getPath().equals(path)) {
                            image.setType(2);
                            image.setPercent(percent);
                            //album.setType(1);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        if (adapter_image != null) {
                                            adapter_image.notifyDataSetChanged();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                            binding.btGui.setVisibility(View.GONE);
                            binding.btLuu.setVisibility(View.GONE);

                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CloseAlbum() {
        Common.ShowToastLong(getString(R.string.toast_message_image_send_sucess));
        if (!type.equals("moimenu")) {
            setResult(1);
            finish();
        } else {
            onBackPressed();
        }
    }

    /**
     * target: upload Image to server by service UploadImageService.class
     */
    private void UploadImage() {

        Tasks = getTaskCount();
        if (Tasks > 0) {
            album.setType(2);
            db.updateAlbumServer(album);
            for (ImageAlbumObject img : list_image) {
                if (img.getType() == 1 || img.getType() == 0) {
                    img.setIdalbum_server(album.getId_server());
                    db.insertImageQUEUE(img);
                }
            }
            if (!Check.isUpload) {
                startService(new Intent(AlbumActivity.this, UploadImageService.class));
            }
            Common.ShowToastLong(getString(R.string.toast_message_album_info_update));
            if (!type.equals("moimenu")) {
                if (type.equals("chupanhbienbang")) {
                   /* Intent intent = new Intent(Activity_Album.this, Activity_LichViengTham.class);
                    intent.putExtra("chucnangalbum", loaiChucNangAlbum);
                    //startActivity(intent);
                    setResult(1, intent);
                    finish();*/
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);//Activity_DanhSachAnhDaChup
                    intent.putExtra("chucnangalbum", loaiChucNangAlbum);
                    setResult(10, intent);
                    finish();
                }
            } else {
                if (flagCheckInChupAnh == 1) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);//Activity_Checkin
                    setResult(5, intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);//
                    intent.putExtra("chucnangalbum", loaiChucNangAlbum);
                    setResult(1, intent);
                    finish();
                }
            }
        }
    }

    /**
     * target: create a new album
     *
     * @return
     */
    @SuppressLint("MissingPermission")
    private AlbumObject createNewAlbum() {

        final AlbumObject albumObject = new AlbumObject();
        try {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location loc) {
                    if (loc != null) {
                        albumObject.setKinhdo(loc.getLongitude());
                        albumObject.setVido(loc.getLatitude());
                        albumObject.setAccuracy(loc.getAccuracy());
                        albumObject.setDiachi(Common.getDiaChi(albumObject.getVido(), albumObject.getKinhdo()));

                    } else {
                        albumObject.setKinhdo(0.0);
                        albumObject.setVido(0.0);
                        albumObject.setAccuracy(0.0);
                        albumObject.setDiachi("Không xác định");

                    }
                    try {
                        if (flagobj.VDOBJ.getStatus() && flagobj.VDOBJ.getIdkhachhang() > 0) {
                            albumObject.setTencuahang(flagobj.VDOBJ.getTenkhachhang());
                            albumObject.setId_khachhang(flagobj.VDOBJ.getIdkhachhang());
                        } else {
                            albumObject.setId_khachhang(0);
                            //albumObject.setTencuahang("Khác");
                            albumObject.setTencuahang(getResources().getString(R.string.title_take_image_free));
                        }
                        albumObject.setType(0);
                        albumObject.setThoigiantao((Common.getThoiGianHienTai()));
                        albumObject.setListImage(new ArrayList<ImageAlbumObject>());
                        albumObject.setIdnv(idnv);
                        albumObject.setGhichu("");
                        try {
                            idalbum = (int) db.insertALBUM(albumObject);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        albumObject.setId(idalbum);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    album = albumObject;
                    binding.tvThoiGian.setText(album.getThoigiantao());
                    binding.tvDiaChiChupAnh.setText(album.getDiachi());

                    getDanhSachCuaHangGanNhat();
                    initDanhMuc();
                    setDataToGrid();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            albumObject.setKinhdo(0.0);
            albumObject.setVido(0.0);
            albumObject.setAccuracy(0.0);
            albumObject.setDiachi("Không xác định");
        }
        return albumObject;
    }

    /**
     * @param requestCode
     * @param resultCode  resultCode=1, from Activity_ChupAnh_New
     *                    resultCode=2, from Activity_ChiTietAnhChup_new when save
     *                    resultCode=100, from Activity_ChiTietAnhChup_new when back
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            if (album.getType() == 0 || album.getType() == 1) {
                getdata(list_image);
                adapter_image.notifyDataSetChanged();
                flagsua = false;
            }
        } else if (requestCode == 1 && resultCode == 2) {
            try {
                if (album.getType() == 0 || album.getType() == 1) {
                    getdata(list_image);
                    if (flagsua) {
                        for (ImageAlbumObject img : list_image) {
                            if (img.getType() == 0) {
                                img.setType(1);
                            }
                        }
                        adapter_image.notifyDataSetChanged();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == 1 && resultCode == 100) {
            try {
                if (album.getType() == 0 || album.getType() == 1) {
                    getdata(list_image);
                    if (flagsua) {
                        for (ImageAlbumObject img : list_image) {
                            if (img.getType() == 0) {
                                img.setType(1);
                            }
                        }
                        adapter_image.notifyDataSetChanged();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Common.Current_Activity=AlbumActivity.this;
    }
}
