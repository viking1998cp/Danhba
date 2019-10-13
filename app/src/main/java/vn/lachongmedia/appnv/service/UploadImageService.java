
package vn.lachongmedia.appnv.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import vn.lachongmedia.appnv.Common;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.activity.AlbumActivity;
import vn.lachongmedia.appnv.evenbus.GuiAnhEvent;
import vn.lachongmedia.appnv.mannager.AndroidMultiPartEntity;
import vn.lachongmedia.appnv.mannager.DataBaseHanlder;
import vn.lachongmedia.appnv.network.NetContext;
import vn.lachongmedia.appnv.object.Check;
import vn.lachongmedia.appnv.object.ImageAlbumObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;


//import vn.lachongmedia.appnv.activity.Activity_ChupAnh;

/**
 * Created by Hoangdh on 11/10/2016.
 */
public class UploadImageService extends Service {
    private long totalSize = 0;
    private DataBaseHanlder db;
    private String IPSV;
   // private SharedPreferences SPState;
   // private final String SPname = "State";
    private String idnv;
    private ImageAlbumObject img;
    private int gps = 0;
    //boolean anhKhongTheGui = false;
    private int status_code_from_server = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Check.isUpload = true;

        // chay service này
        //lấy 1 cái ảnh

     /*   ArrayList<ImageAlbumObject> images = (ArrayList<ImageAlbumObject>) db.selectSomeImageQUEUE(idnv);
        images.size();*/

        gps= Common.checkGPS();
        try {
            db = DataBaseHanlder.getInstance(getApplicationContext());
            img = db.select1Image();
           // SPState = getApplicationContext().getSharedPreferences(SPname, Context.MODE_PRIVATE);
            idnv = ""+ SharedPrefs.getInstance().get(Common.iDNhanVien,Integer.class);
            // idct = SPState.getString(Common.KEY_IDQLLH, "");
            IPSV = NetContext.getInstance().getBASE_URL()+"/";
            if (img == null) {
                getApplicationContext().stopService(new Intent(getApplicationContext(), UploadImageService.class));
                return;
            }
            String URL = IPSV + "AppUpload.aspx?token="
                    + Common.getToken() + "&idnhanvien=" + idnv
                    + "&kinhdo=" + img.getKinhdo()
                    + "&vido=" + img.getVido()
                    + "&diachi=" + Common.EncodeHttpPost(img.getDiachi())
                    + "&thoigianchup=" + Common.EncodeHttpPost(img.getThoigianchup())
                    + "&ghichu=" + URLEncoder.encode(img.getGhichu())
                    + "&idalbum=" + img.getIdalbum_server()
                    + "&trangthaigps=" + gps;

            //MD5.ghiLog("UploadImageService", MD5.getNgayHienTai(), "URL", URL + " -- " + img.getThoigianchup(), "442");

            if (Common.isNetworkAvailable()) {
                new UploadFileToServer().execute(URL);
            } else {
                getApplicationContext().stopService(new Intent(getApplicationContext(), UploadImageService.class));
                Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.disconnect_server_image_auto_send_login), Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            getApplicationContext().stopService(new Intent(getApplicationContext(), UploadImageService.class));
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class UploadFileToServer extends AsyncTask<String, Integer, String> {
        JSONObject OBJ;
        Boolean status;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
        }

        @Override

        protected String doInBackground(String... params) {

            if (!Common.isNetworkAvailable()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            status_code_from_server = 0;
            return uploadFile(params[0]);
        }

        @SuppressWarnings("deprecation")
        private String uploadFile(String URL) {
            //      count++;

            String responseString = null;
            HttpParams httpParams = new BasicHttpParams();
            int timeoutConnection = 60000;
            HttpConnectionParams.setConnectionTimeout(httpParams, timeoutConnection);
            HttpConnectionParams.setSoTimeout(httpParams, timeoutConnection);
            HttpClient httpclient = new DefaultHttpClient(httpParams);
            HttpPost httppost = new HttpPost(URL);
            status = false;
            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(new AndroidMultiPartEntity.ProgressListener() {
                    @Override
                    public void transferred(final long num) {
                        publishProgress((int) ((num / (float) totalSize) * 100));
                        if(Common.Current_Activity!=null){
                            try {
                                Common.Current_Activity.runOnUiThread(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                if (Common.Current_Activity instanceof AlbumActivity) {
                                                    ((AlbumActivity) Common.Current_Activity).UpdateListImage(img.getIdalbum(),
                                                            img.getPath(), (int) ((num / (float) totalSize) * 100));
                                                    //Log.e("Percent Image", (int) ((num / (float) totalSize) * 100) + "");
                                                }
                                                /*if (Common.Current_Activity instanceof Activity_AlbumBaoCaoAnhChup) {
                                                    ((Activity_AlbumBaoCaoAnhChup) Common.Current_Activity).UpdateListImage(img.getIdalbum(),
                                                            img.getPath(), (int) ((num / (float) totalSize) * 100));
                                                    //Log.e("Percent Image", (int) ((num / (float) totalSize) * 100) + "");
                                                }
                                                if (Common.Current_Activity instanceof Activity_AlbumKeHoachBaoDuong) {
                                                    ((Activity_AlbumKeHoachBaoDuong) Common.Current_Activity).UpdateListImage(img.getIdalbum(),
                                                            img.getPath(), (int) ((num / (float) totalSize) * 100));
                                                    //Log.e("Percent Image", (int) ((num / (float) totalSize) * 100) + "");
                                                }
                                                if (Common.Current_Activity instanceof Activity_ChiTietDonHang_XuLy) {
                                                    ((Activity_ChiTietDonHang_XuLy) Common.Current_Activity).UpdateListImage(img.getIdalbum(),
                                                            img.getPath(), (int) ((num / (float) totalSize) * 100));
                                                    //Log.e("Percent Image", (int) ((num / (float) totalSize) * 100) + "");
                                                }
                                                if (Common.Current_Activity instanceof ActivityChiTietCongViec) {
                                                    ((ActivityChiTietCongViec) Common.Current_Activity).UpdateListImage(img.getIdalbum(),
                                                            img.getPath(), (int) ((num / (float) totalSize) * 100));
                                                    //Log.e("Percent Image", (int) ((num / (float) totalSize) * 100) + "");
                                                }*/
                                            }
                                        });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                File path_ksmart;
                File sourceFile;
                path_ksmart = new File(Environment.getExternalStorageDirectory(), "Ksmart");
                sourceFile = new File(path_ksmart.getPath() + "/" + img.getPath());
                if (!sourceFile.exists()) {
                    db.deleteImageQUEUEbyID(img.getIdimage());
                    img = db.select1Image(); // lay 1 anh moi de up
                    status = false;
                } else {
                    // Adding file data to http body
                    entity.addPart("LHIMAGE", new FileBody(sourceFile));

                    totalSize = entity.getContentLength();
                    httppost.setEntity(entity);

                    // Making server call
                    HttpResponse response = httpclient.execute(httppost);

                    HttpEntity r_entity = response.getEntity();

                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        // Server response
                        responseString = EntityUtils.toString(r_entity);

                        try {
                            OBJ = new JSONObject(responseString);
                            status = OBJ.getBoolean(Common.TAG_STATUS);
                            //msg = OBJ.getString(Common.TAG_MSG);
                            try {
                                if(OBJ.has("status_code"))
                                status_code_from_server = OBJ.getInt("status_code");
                            } catch (JSONException e) {
                                status_code_from_server=0;
                                e.printStackTrace();
                            }

                        } catch (JSONException e) {
                            Log.e("JSONException", e.getMessage());
                        }

//                        try {
//                            status_code_from_server = OBJ.getInt("status_code");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }

                    } else {
                        responseString = "Error occurred! Http Status Code: " + statusCode;
                    }

                    //ảnh này chụp bị lỗi, không thể gửi
                    //lấy ảnh khác để gửi, không gửi ảnh đó nữa
                    if (totalSize == 0 || status_code_from_server == 4) {
                        db.deleteImageQUEUEbyID(img.getIdimage());
                        img = db.select1Image(); // lay 1 anh moi de up
                        status = false;
                    }
                }
                //ảnh này chụp bị lỗi, không thể gửi
                //lấy ảnh khác để gửi, không gửi ảnh đó nữa
               /* if (sourceFile.length()==0) {
                    //anhKhongTheGui = true;
                  *//*  db.deleteImageUpload(img.getPath());
                    img = db.select1ImageUpload(Integer.parseInt(idnv));*//*
                    db.deletepathALBUM(img.getPath());
                    db.deleteImageALBUM(img.getPath());
                    db.deleteImageQUEUE(img.getPath());
                    HamDungChung.deleteFileEmpty(sourceFile);
                    status = false;
                }*/

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();

            } catch (Exception e) {
                responseString = e.toString();
                e.printStackTrace();
            }

            try {

                if (status != null && status) {
                    db.deleteImageQUEUEbyID(img.getIdimage());
                    img.setType(2);
                    db.updateImageALBUMByPath(img);
                    totalSize = 0;
                    if (Common.Current_Activity instanceof AlbumActivity) {
                        ((AlbumActivity) Common.Current_Activity).UpdateListImage(img.getIdalbum(), img.getPath(), 100);
                    }
                   /* if (Common.Current_Activity instanceof Activity_AlbumBaoCaoAnhChup) {
                        ((Activity_AlbumBaoCaoAnhChup) Common.Current_Activity).UpdateListImage(img.getIdalbum(), img.getPath(), 100);
                    }
                    if (Common.Current_Activity instanceof Activity_AlbumKeHoachBaoDuong) {
                        ((Activity_AlbumKeHoachBaoDuong) Common.Current_Activity).UpdateListImage(img.getIdalbum(), img.getPath(), 100);
                    }
                    if (Common.Current_Activity instanceof Activity_ChiTietDonHang_XuLy) {
                        ((Activity_ChiTietDonHang_XuLy) Common.Current_Activity).UpdateListImage(img.getIdalbum(), img.getPath(), 100);
                    }
                    if (Common.Current_Activity instanceof ActivityChiTietCongViec) {
                        ((ActivityChiTietCongViec) Common.Current_Activity).UpdateListImage(img.getIdalbum(), img.getPath(), 100);
                    }*/
                    if (db.CheckAllSend(img.getIdalbum()) == 0) {
                        try {
                            ArrayList<ImageAlbumObject> list_image_album = (ArrayList<ImageAlbumObject>) db.selectSomeImageALBUM(img.getIdalbum());
                            //////// code nay dung xoa anh trong SDCrad khi gui anh ///////
                            if (list_image_album != null && list_image_album.size() > 0) {
                                for (ImageAlbumObject image : list_image_album) {
                                    //HamDungChung.deleteImage(image.getPath(), getApplicationContext());
                                }
                            }
                            ////////  het phan code dung xoa anh trong SDCrad khi gui anh ///////
                         /*   AlbumObject albumObject = db.select1ALBUMbyidServer(img.getIdalbum_server());
                            albumObject.setType(1);
                            db.updateAlbumServer(albumObject);*/

                            db.deleteALBUM(img.getIdalbum());
                            //db.deleteAlbumServer(img.getIdalbum_server());
                            db.deleteAllImageALBUM(img.getIdalbum());

                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                       /* if (Common.Current_Activity instanceof Activity_BaoCaoAnhChup) {
                            ((Activity_BaoCaoAnhChup) Common.Current_Activity).refresh();
                        }*/
                       /* if (Common.Current_Activity instanceof AlbumActivity) {
                            ((AlbumActivity) Common.Current_Activity).CloseAlbum();
                        }*/
                        try {
                            EventBus.getDefault().postSticky(new GuiAnhEvent(2,img.getIdalbum()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                       /* if (Common.Current_Activity instanceof Activity_DanhSachAnhDaChup) {
                            ((Activity_DanhSachAnhDaChup) Common.Current_Activity).refresh();
                        }
                        if (Common.Current_Activity instanceof Activity_LichSuBaoDuong) {
                            ((Activity_LichSuBaoDuong) Common.Current_Activity).refresh();
                        }
                        if (Common.Current_Activity instanceof Activity_Album) {
                            ((Activity_Album) Common.Current_Activity).CloseAlbum();
                        }
                        if (Common.Current_Activity instanceof Activity_ChiTietDonHang_XuLy) {
                            ((Activity_ChiTietDonHang_XuLy) Common.Current_Activity).refresh();
                        }*/
                      /*  if (Common.Current_Activity instanceof ActivityChiTietCongViec) {
                            ((ActivityChiTietCongViec) Common.Current_Activity).refresh();
                        }*/
                        Common.flagguixongalbum=true;

                    } else {

                        try {
                            EventBus.getDefault().postSticky(new GuiAnhEvent(1,img.getIdalbum()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                     /* if (Common.Current_Activity instanceof Activity_BaoCaoAnhChup) {
                            ((Activity_BaoCaoAnhChup) Common.Current_Activity).UpdateListViewDS(img.getIdalbum());
                        }
                        if (Common.Current_Activity instanceof Activity_DanhSachAnhDaChup) {
                            ((Activity_DanhSachAnhDaChup) Common.Current_Activity).UpdateListView(img.getIdalbum());
                        }
                        if (Common.Current_Activity instanceof Activity_LichSuBaoDuong) {
                            ((Activity_LichSuBaoDuong) Common.Current_Activity).UpdateListViewDS(img.getIdalbum());
                        }*/
                    }

                    img = db.select1Image(); // lay 1 anh moi de up
                }

            } catch (Exception e) {
                e.printStackTrace();
                // MD5.ghiLog("tungda",MD5.getCurentTime(),"","","loi gui anh");

            }
            // upload anh
            try {
                if (img != null && img.getType() != 2) {
                    URL = IPSV
                            + "AppUpload.aspx?token=" + Common.getToken()
                            + "&idnhanvien=" + idnv
                            + "&kinhdo=" + img.getKinhdo()
                            + "&vido=" + img.getVido()
                            + "&diachi=" + Common.EncodeHttpPost(img.getDiachi())
                            + "&thoigianchup=" + Common.EncodeHttpPost(img.getThoigianchup())
                            + "&ghichu=" + URLEncoder.encode(img.getGhichu())
                            + "&idalbum=" + img.getIdalbum_server()
                            + "&trangthai=" + gps;
                    Log.e("URL_anh", URL);

                    //MD5.ghiLog("UploadImageService", MD5.getNgayHienTai(), "URL", URL + " -- " + img.getThoigianchup(), "666");

                    new UploadFileToServer().execute(URL);

                } else {
                    getApplicationContext().stopService(new Intent(getApplicationContext(), UploadImageService.class));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Check.isUpload = false;
    }
}
