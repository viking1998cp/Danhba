

package vn.lachongmedia.appnv.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.SensorManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.customcamera.CameraPreview;
import vn.lachongmedia.appnv.customcamera.MyView;
import vn.lachongmedia.appnv.mannager.DataBaseHanlder;
import vn.lachongmedia.appnv.mannager.GoogleApiModel;
import vn.lachongmedia.appnv.object.CameraOBJ;
import vn.lachongmedia.appnv.object.ImageAlbumObject;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


@SuppressLint({"DefaultLocale", "SimpleDateFormat", "ClickableViewAccessibility"})
public class Activity_ChupAnh_New extends Activity implements LocationListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private CameraPreview cameraPreview;
    private RelativeLayout mLayout, toochlayout;
    private LinearLayout camblink;
    private Button btnFlash;
    private Button chupanh, btnClose;
    private ImageView imgPreview, img_change_camera_back;
    private static final int PICTURE_QUALITY = 85;
    private int idnv;
    private MyView drawingView;
    private int idalbum;
    private String tenAlbum = "";
    private double kinhdo;
    private double vido;
    private double acc;
    private DataBaseHanlder db;
    private GoogleApiModel googleGPS;

    private OrientationEventListener myOrientationEventListener;
    private int Orient = 90;
    private int LastOrient = 0;
    private String stateFlash = "auto";
    private final int change_camera = 0;
    private String temp_stateFlash = stateFlash;
    private int temp_change_camera = change_camera;
    private TextView tv_auto;
    private TextView tv_on;
    private TextView tv_off;
    private boolean isSafeToTakePicture = true;
    private List<CameraOBJ> cameraOBJ;
    private CameraOBJ mCameraOBJ;
    private int witdh, height;

    @Override
    protected void onDestroy() {
        if (cameraPreview != null) {
            try {
                cameraPreview.stop();
                mLayout.removeView(cameraPreview);
                cameraPreview = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (imgPreview != null) {
            try {
                if (imgPreview != null && imgPreview.getDrawable() != null) {
                    Bitmap bmNho = ((BitmapDrawable) imgPreview.getDrawable()).getBitmap();
                    if (bmNho != null && !bmNho.isRecycled()) {
                        bmNho.recycle();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                imgPreview.setImageDrawable(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            googleGPS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide title-bar, must be before setContentView
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chup_anh__new);
        Common.Current_Activity = Activity_ChupAnh_New.this;
        witdh = 800;
        height = 600;
        try {
            System.runFinalization();
            Runtime.getRuntime().gc();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Hide status-bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String SPname = "State";
        SharedPreferences SPState = getSharedPreferences(SPname, MODE_PRIVATE);
        idnv = SharedPrefs.getInstance().get(Common.iDNhanVien,Integer.class);
        String IPSV = SPState.getString("IPSV", "");
        //flagobj.KDN = SPState.getInt("KDN", 0);
        cameraOBJ = new ArrayList<>();
        mCameraOBJ = new CameraOBJ();
        try {
            db = DataBaseHanlder.getInstance(Activity_ChupAnh_New.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle.containsKey("idalbum")) {
                idalbum = bundle.getInt("idalbum");
                tenAlbum = bundle.getString("daily");
            }
        } catch (Exception e) {
            e.printStackTrace();
            idalbum = 0;
        }
        try {
            googleGPS = new GoogleApiModel(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        initView();
        initClose();
        initTakePicture();
        initDetectOri();
        initChangeCamera();
    }

    // xu ly view giao dien
    private void initView() {
        // Requires RelativeLayout.
        mLayout = (RelativeLayout) findViewById(R.id.layout);
        toochlayout = (RelativeLayout) findViewById(R.id.toochlayout);
        chupanh = (Button) findViewById(R.id.btnCapture);
        btnClose = (Button) findViewById(R.id.btnClose);
        imgPreview = (ImageView) findViewById(R.id.img_capture_preview);
        // img_change_camera_before = (ImageView) findViewById(R.id.img_change_camera_before);
        img_change_camera_back = (ImageView) findViewById(R.id.img_change_camera_back);
        btnFlash = (Button) findViewById(R.id.btnFlash);
        /* btnQuality = findViewById(R.id.btnQuality);*/
        tv_auto = (TextView) findViewById(R.id.tv_auto);
        tv_on = (TextView) findViewById(R.id.tv_on);
        tv_off = (TextView) findViewById(R.id.tv_off);
        /*  tvQuality = findViewById(R.id.tvQuality);*/
        camblink = (LinearLayout) findViewById(R.id.camblink);
        camblink.setVisibility(View.GONE);
        tv_auto.setVisibility(View.VISIBLE);
        tv_on.setVisibility(View.GONE);
        tv_off.setVisibility(View.GONE);
        try {
            ////////////////////////
            db = DataBaseHanlder.getInstance(getBaseContext());
            cameraOBJ = db.getAllCameraOBJ();
            for (int i = 0; i < cameraOBJ.size(); i++) {
                temp_stateFlash = cameraOBJ.get(i).getChonFlash();
                temp_change_camera = cameraOBJ.get(i).getChuyencamera();
                mCameraOBJ.setChonFlash(temp_stateFlash);
                mCameraOBJ.setChuyencamera(temp_change_camera);
            }
            xulyDB();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        switch (temp_stateFlash) {
            case "on":
                tv_auto.setText(getResources().getString(R.string.title_camera_on));
                break;
            case "off":
                tv_auto.setText(getResources().getString(R.string.title_camera_off));
                break;
            default:
                tv_auto.setText(getResources().getString(R.string.title_camera_auto));
                break;
        }

        initEventCamera();
        initOptionFlash();
    }

    private void xulyDB() {
        //db.deleteConfigCamera();
        mCameraOBJ.setChonFlash(temp_stateFlash);
        mCameraOBJ.setChuyencamera(temp_change_camera);
        if (db.isContainCamera(mCameraOBJ.getChuyencamera())) {
            db.updateInfoCamera(mCameraOBJ);
        } else {
            db.insertConfigCamera(mCameraOBJ);
        }
        switch (temp_stateFlash) {
            case "on":
                tv_on.setText(getResources().getString(R.string.title_camera_on));
                break;
            case "off":
                tv_off.setText(getResources().getString(R.string.title_camera_off));
                break;
            default:
                tv_auto.setText(getResources().getString(R.string.title_camera_auto));
                break;
        }
    }

    private void initOptionFlash() {
        tv_auto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_auto.setVisibility(View.VISIBLE);
                tv_on.setVisibility(View.GONE);
                tv_off.setVisibility(View.GONE);
                tv_auto.setText(getResources().getString(R.string.title_camera_auto));
                stateFlash = "auto";
                temp_stateFlash = stateFlash;
                initEventCamera();
                xulyDB();
            }
        });
        tv_on.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_auto.setVisibility(View.VISIBLE);
                tv_on.setVisibility(View.GONE);
                tv_off.setVisibility(View.GONE);
                tv_auto.setText(getResources().getString(R.string.title_camera_on));
                stateFlash = "on";
                temp_stateFlash = stateFlash;
                initEventCamera();
                xulyDB();
            }
        });
        tv_off.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_auto.setVisibility(View.VISIBLE);
                tv_on.setVisibility(View.GONE);
                tv_off.setVisibility(View.GONE);
                tv_auto.setText(getResources().getString(R.string.title_camera_off));
                stateFlash = "off";
                temp_stateFlash = stateFlash;
                initEventCamera();
                xulyDB();
            }
        });
        btnFlash.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_auto.setVisibility(View.VISIBLE);
                tv_on.setVisibility(View.VISIBLE);
                tv_off.setVisibility(View.VISIBLE);
                tv_auto.setText(getResources().getString(R.string.title_camera_auto));
                tv_on.setText(getResources().getString(R.string.title_camera_on));
                tv_off.setText(getResources().getString(R.string.title_camera_off));

            }
        });
    }

    private void initChangeCamera() {
        img_change_camera_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (temp_change_camera == 0) {
                    temp_change_camera = 1;
                } else {
                    temp_change_camera = 0;
                }
                isSafeToTakePicture = true;
                initEventCamera();
                xulyDB();
            }
        });
     /*   img_change_camera_before.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                change_camera = 1;
                temp_change_camera = change_camera;
                img_change_camera_back.setVisibility(View.VISIBLE);
                img_change_camera_before.setVisibility(View.GONE);
                initEventCamera();
                xulyDB();
            }
        });*/
    }

    private void initEventCamera() {

        if (cameraPreview != null) {

            try {
                cameraPreview.stop();
                mLayout.removeView(cameraPreview);
                cameraPreview = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            cameraPreview = new CameraPreview(this, CameraPreview.LayoutMode.FitToParent);
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int width = displaymetrics.widthPixels;
            int newHeight = (int) (width * ((float) 4 / (float) 3));
            mLayout.addView(cameraPreview, new LayoutParams(width, newHeight));
            cameraPreview.setCamera(temp_change_camera, null);// set chuyen camera truoc sau
            cameraPreview.setFlashMode(temp_stateFlash);// on bat, off tat, auto tu dong bat den flash
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            Common.ShowToastLong(getString(R.string.toast_msg_camera_disconnect));
        } catch (Exception e) {
            e.printStackTrace();
            Common.ShowToastLong(getString(R.string.toast_msg_camera_disconnect));
        } finally {
            try {
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // xu ly nut close khi chup xong anh
    private void initClose() {
        btnClose.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        onBackPressed();
                    }
                });
    }

    // Xu ly nut chup anh
    private void initTakePicture() {
        chupanh.setOnClickListener(
                new OnClickListener() {

                    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        try {

                            if (isSafeToTakePicture) {
                                LastOrient = getLastOrient(Orient);
                                // MD5.showLog("Orient:" + Orient);

                                // MD5.showLog("LastOrient:" + LastOrient);

                                cameraPreview.getmCamera().takePicture(shutterCallback, null, jpegCallback);
                                camblink.setVisibility(View.VISIBLE);
                                new Handler().postDelayed(new Runnable() {
                                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                    @Override
                                    public void run() {
                                        camblink.setVisibility(View.INVISIBLE);
                                    }
                                }, 150);
                                isSafeToTakePicture = false;
                            }
                        } catch (Exception ex) {
                            isSafeToTakePicture = true;
                            ex.printStackTrace();
                        }
                    }
                });
    }

    private void initDetectOri() {
        try {
            myOrientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {

                @Override
                public void onOrientationChanged(int arg0) {
                    Orient = arg0;
                }
            };

            if (myOrientationEventListener.canDetectOrientation()) {
                //  Toast.makeText(this, "Can DetectOrientation", Toast.LENGTH_LONG).show();
                myOrientationEventListener.enable();
            } else {
                //    Toast.makeText(this, "Can't DetectOrientation", Toast.LENGTH_LONG).show();
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (!googleGPS.checkGPSProvider()) {
           /* startActivity(new Intent(getBaseContext(),
                    Activity_Home2.class));
            finish();*/
        } else {

            kinhdo = 0.0;
            vido = 0.0;
            acc = 0.0;
            try {
                Location lo = googleGPS.getLastLocation();
                if (lo != null) {
                    kinhdo = lo.getLongitude();
                    vido = lo.getLatitude();
                    acc = lo.getAccuracy();
                } else {
                    Common.ShowToastLong(getString(R.string.error_message_not_gps));
                }

            } catch (Exception e) {
                e.printStackTrace();
                Common.ShowToastLong(getString(R.string.error_message_not_gps));


            }


        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        int newHeight = (int) (width * ((float) 4 / (float) 3));
        //kien tra them drawingView neu khac bang null thi khong them
        if (drawingView == null) {
            drawingView = new MyView(this);
            toochlayout.addView(drawingView, new LayoutParams(width, newHeight));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        initEventCamera();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      /*  if (resultCode == 1) {
            if (!data.getStringExtra("batFlash").equals("")) {
                stateFlash = data.getStringExtra("batFlash");
                tvFlash.setText(stateFlash);
            }
        }
        if (resultCode == 2) {
            if (!data.getStringExtra("tatFlash").equals("")) {
                stateFlash = data.getStringExtra("tatFlash");
                tvFlash.setText(stateFlash);
            }
        }
        if (resultCode == 3) {
            if (!data.getStringExtra("tudongFlash").equals("")) {
                stateFlash = data.getStringExtra("tudongFlash");
                tvFlash.setText(stateFlash);
            }
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            cameraPreview.stop();
            mLayout.removeView(cameraPreview);
            cameraPreview = null;
            db.deleteConfigCamera();
            db.insertConfigCamera(mCameraOBJ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshGallery(File file) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(Uri.fromFile(file));
        sendBroadcast(mediaScanIntent);
    }

    // bat tieng khi nhan nut chup anh
    private final ShutterCallback shutterCallback = new ShutterCallback() {
        public void onShutter() {
            // Log.d(TAG, "onShutter'd");
        }
    };

    private final PictureCallback jpegCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = null;
            try {
                BitmapFactory.Options opt = new BitmapFactory.Options();
                opt.inJustDecodeBounds = true;
                opt.inMutable = true;
                opt.inJustDecodeBounds = false;
                opt.inDither = false;
                opt.inPurgeable = true;
                opt.inInputShareable = true;
                opt.inPreferredConfig = Bitmap.Config.RGB_565;

                Matrix matrix = new Matrix();
                //int witdh = 800, height = 600;


                opt.inSampleSize = calculateInSampleSize(opt, witdh, height);
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, opt);


                if (temp_change_camera == 1) {
                    // bitmap = getResizedBitmap(bitmap, witdh, height, matrix);
                    float sizew = bitmap.getWidth() / witdh;
                    float sizeh = bitmap.getHeight() / height;
                    float sizewh = Math.max(sizew, sizeh);
                    if (sizewh > 1) {
                        bitmap = resizeBitmap(bitmap, sizewh);
                    }
                    matrix.setScale(-1, 1);
                    matrix.postRotate(LastOrient);
                    bitmap = getResizedBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), matrix);
                } else {

                    float sizew = bitmap.getWidth() / witdh;
                    float sizeh = bitmap.getHeight() / height;
                    float sizewh = Math.max(sizew, sizeh);
                    if (sizewh > 1) {
                        bitmap = resizeBitmap(bitmap, sizewh);
                    }
                    matrix.setScale(1, 1);
                    matrix.postRotate(LastOrient);
                    bitmap = getResizedBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), matrix);
                    //opt.inSampleSize = calculateInSampleSize(opt, witdh, height);
                    // bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, opt);
                    // bitmap = getResizedBitmap(bitmap, witdh, height, matrix);

                }


                bitmap = themTimestampVaoAnh(bitmap);

                if (!SaveImageToDisk(bitmap)) {
                   Common.ShowToastLong(getString(R.string.toast_msg_err_save_picture_please_try));
                }
            } catch (OutOfMemoryError ex) {
                //HamDungChung.ShowToast(Activity_ChupAnh_New.this, "Lỗi trong quá trình chụp và lưu ảnh, vui lòng chụp lại");
                //ex.printStackTrace();
                witdh = 600;
                height = 450;
                try {
                    System.gc();
                } catch (Exception ignored) {

                }

            } catch (Exception e) {
                // HamDungChung.ShowToast(Activity_ChupAnh_New.this, "Lỗi trong quá trình chụp và lưu ảnh, vui lòng chụp lại");
                //e.printStackTrace();
                witdh = 600;
                height = 450;
                try {
                    System.gc();
                } catch (Exception ignored) {

                }
            } finally {
                try {
                    if (bitmap != null && !bitmap.isRecycled()) {
                        bitmap.recycle();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    System.gc();
                } catch (Exception ignored) {

                }
                try {
                    cameraPreview.getmCamera().startPreview();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                isSafeToTakePicture = true;
            }


        }
    };


    private static Bitmap resizeBitmap(Bitmap bitmap, float pickSize) {
        int dstWidth = (int) (bitmap.getWidth() / pickSize);
        int dstHeight = (int) (bitmap.getHeight() / pickSize);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, dstWidth,
                dstHeight, false);
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return scaledBitmap;
    }


    private Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight, Matrix matrix) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        //Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        if (bm != null && !bm.isRecycled()) {
            bm.recycle();
        }
        return resizedBitmap;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    // them thoi gian len tren anh khi chup
    private Bitmap themTimestampVaoAnh(Bitmap toEdit) {

        String dateTime = Common.formatChangePointDateTime(Calendar.getInstance().getTime()); // Lay thoi gian hien tai ve len anh chup

        Canvas cs = new Canvas(toEdit);
        Paint tPaint = new Paint();
        tPaint.setTextSize(35);
        tPaint.setColor(Color.RED);
        tPaint.setStyle(Style.FILL);
        float height = tPaint.measureText("yY");
        float weight = tPaint.measureText(dateTime);
        cs.drawText(dateTime, cs.getWidth() - weight - 10f, cs.getHeight() - height, tPaint);

        return toEdit;
    }

    // luu anh khi chup vao bo nho may
    private boolean SaveImageToDisk(Bitmap bm) {
        FileOutputStream outStream = null;

        // Write to SD Card
        try {
            String fileName = String.format("%d.jpg",
                    System.currentTimeMillis());
            File dir;
            File outFile;
            File sdCard = Environment.getExternalStorageDirectory();
            dir = new File(sdCard.getAbsolutePath() + "/Ksmart");
            dir.mkdirs();
            outFile = new File(dir, fileName);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(CompressFormat.JPEG, PICTURE_QUALITY, stream);
            Bitmap anhNho = Bitmap.createScaledBitmap(bm, 128, 160, false);

            try {
                if (imgPreview != null && imgPreview.getDrawable() != null) {
                    Bitmap bmNho = ((BitmapDrawable) imgPreview.getDrawable()).getBitmap();
                    if (bmNho != null && !bmNho.isRecycled()) {
                        bmNho.recycle();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                imgPreview.setImageDrawable(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                imgPreview.setImageBitmap(anhNho);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            byte[] byteArray = stream.toByteArray();
            outStream = new FileOutputStream(outFile);
            outStream.write(byteArray);
            outStream.flush();

            try {
                ImageAlbumObject image = new ImageAlbumObject();
                image.setGhichu("");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
                String dateTime = sdf.format(Calendar.getInstance().getTime());
                image.setThoigianchup(dateTime);
                image.setIdnhanvien(idnv);
                image.setIdalbum(idalbum);
                image.setTendaily(tenAlbum);
                image.setKinhdo(kinhdo);
                image.setVido(vido);
                image.setDiachi(Common.getDiaChi( vido, kinhdo));
                image.setAcc(acc);
                image.setPath(fileName);
                db.insertImageALBUM(image);
            } catch (Exception e) {
                e.printStackTrace();
            }

            refreshGallery(outFile);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (bm != null && !bm.isRecycled()) {
                try {
                    bm.recycle();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    @Override
    public void onBackPressed() {

        final ProgressDialog pd = ProgressDialog.show(Activity_ChupAnh_New.this, "",
                getResources().getString(R.string.toast_msg_save_picture_please_wait), true, false);
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            pd.cancel();
                            Intent intent = new Intent();
                            setResult(1, intent);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 500);

    }

    // xoay camera mac dinh theo chieu dung
    private int getLastOrient(int orient) {
        Log.d("orient: ", orient + "");

       /* if (temp_change_camera == 0) {
            return 90;
        }else {
            return 270;
        }*/

        if (temp_change_camera == 0) {
            if (orient == -1 || (orient >= 0 && orient < 45) || (orient >= 315)) {
                return 90;
            } else if (orient >= 45 && orient < 180) {
                return 180;// chinh xoay hinh anh chup
            } else if (orient < 315 && orient >= 180) {
                return 0;
            }
        } else {
            if (orient == -1 || (orient >= 0 && orient < 45) || (orient >= 315)) {
                return 90;
            } else if (orient >= 45 && orient < 180) {
                return 180;// chinh xoay hinh anh chup
            } else if (orient < 315 && orient >= 180) {
                return 0;
            }

           /* if (orient == -1 || (orient >= 0 && orient < 45) || (orient >= 315)) {
                return 270;
            } else if (orient >= 45 && orient < 180) {
                return 180;// chinh xoay hinh anh chup
            } else if (orient < 315 && orient >= 180) {
                return 0;
            }*/

        }
        return 0;

    }

    private int getOrientDeConvertXoayFocusRect(int orient) {
        Log.d("orient: ", orient + "");
        //Từ góc 270 đến 360 thì xoay 270 độ: ok
        if (orient >= 270 && orient <= 360) {
            return 270;
        }

        //Từ góc 180 đến góc 270 thì xoay 270 độ: ok
        if (orient >= 180 && orient < 270) {
            return 270;
        }

        //Từ góc 90 đến góc 180 thì xoay -90 độ: ok
        if (orient >= 90 && orient < 180) {
            return -90;
        }

        //Từ góc 0 đến góc 90 thì xoay -90 độ: ok
        if (orient >= 0 && orient < 90) {
            return -90;
        }


        return 0;
    }

    @TargetApi(14)
    public void touchFocus(final Rect tfocusRect) {

        //btn_chupanh.setEnabled(false);

        //camera.stopFaceDetection();

        try {
            Camera.Parameters para = cameraPreview.getmCamera().getParameters();
            cameraPreview.setFocusModeForParameter(para, Camera.Parameters.FOCUS_MODE_AUTO);
            setFocusArea(para, tfocusRect, drawingView);
            setMetering(para, tfocusRect, drawingView);

            cameraPreview.getmCamera().setParameters(para);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            cameraPreview.getmCamera().autoFocus(myAutoFocusCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            drawingView.setHaveTouch(tfocusRect);
            drawingView.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Rect rectFToRect(RectF rectF) {
        Rect rect = new Rect();
        rect.left = Math.round(rectF.left);
        rect.top = Math.round(rectF.top);
        rect.right = Math.round(rectF.right);
        rect.bottom = Math.round(rectF.bottom);
        return rect;
    }

    private final Camera.AutoFocusCallback myAutoFocusCallback = new Camera.AutoFocusCallback() {

        @Override
        public void onAutoFocus(boolean arg0, Camera arg1) {
            // TODO Auto-generated method stub
            if (arg0) {
                try {
                    cameraPreview.getmCamera().cancelAutoFocus();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    };

    private void setFocusArea(Camera.Parameters parameters, Rect touchRect, MyView drawingView) {
        if (parameters.getMaxNumFocusAreas() > 0) {
            List<Camera.Area> middleArea = buildTouchArea(touchRect, drawingView);
            parameters.setFocusAreas(middleArea);
        } else {
            Log.i("setFocusArea", "Device does not support focus areas");
        }
    }

    private void setMetering(Camera.Parameters parameters, Rect touchRect, MyView drawingView) {
        if (parameters.getMaxNumMeteringAreas() > 0) {
            List<Camera.Area> middleArea = buildTouchArea(touchRect, drawingView);
            parameters.setMeteringAreas(middleArea);
        } else {
            Log.i("setMeteringArea", "Device does not support metering areas");
        }
    }

    private List<Camera.Area> buildTouchArea(Rect tfocusRect, MyView drawingView) {
        RectF targetFocusRect = new RectF(
                tfocusRect.left * 2000 / drawingView.getWidth() - 1000,
                tfocusRect.top * 2000 / drawingView.getHeight() - 1000,
                tfocusRect.right * 2000 / drawingView.getWidth() - 1000,
                tfocusRect.bottom * 2000 / drawingView.getHeight() - 1000);
        Matrix m = new Matrix();
        int o = getOrientDeConvertXoayFocusRect(Orient);
        m.postRotate(o);
        m.mapRect(targetFocusRect);

        Rect targetFocusRectNew = rectFToRect(targetFocusRect);
//        Log.d("touchfocusRect: ", tfocusRect.left + " | " + tfocusRect.right
//                + " | " + tfocusRect.top + " | " + tfocusRect.bottom);
//        Log.d("drawingView: ", drawingView.getWidth() + " | " + drawingView.getHeight());
//        Log.d("targetFocusRectNew: ", targetFocusRectNew.left + " | " + targetFocusRectNew.right
//                + " | " + targetFocusRectNew.top + " | " + targetFocusRectNew.bottom);

        return Collections.singletonList(new Camera.Area(targetFocusRectNew, 1000));
    }

}
