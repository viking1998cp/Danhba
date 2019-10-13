package vn.lachongmedia.appnv.mannager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import vn.lachongmedia.appnv.object.AlbumObject;
import vn.lachongmedia.appnv.object.CameraOBJ;
import vn.lachongmedia.appnv.object.ImageAlbumObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DataBaseHanlder extends SQLiteOpenHelper {
    private static DataBaseHanlder mInstance = null;
    private static final int DATABASE_VERSION = 25;
    private static final String DATABASE_NAME = "DB_APPNV";

    public static DataBaseHanlder getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new DataBaseHanlder(context.getApplicationContext());
        }
        return mInstance;//new DataBaseHanlder(context.getApplicationContext());
    }

    // Table Names
    private static final String TABLE_DONHANG = "donhang";
    private static final String TABLE_HANG = "hang";
    private static final String TABLE_ADDTEMP = "addtemp";
    private static final String TABLE_PUSH = "push";
    private static final String TABLE_LAPKEHOACH = "lapkehoach";
    private static final String TABLE_ALBUM = "album";
    private static final String TABLE_GPS = "gps";
    private static final String TABLE_IMAGE_ALBUM = "anhalbum";
    private static final String TABLE_IMAGE_QUEUE = "imageQueue";
    private static final String TABLE_CONFIG_CAMERA = "configcamera";
    private static final String TABLE_STATUS_CHECKIN = "statusCheckin";
    private static final String TABLE_NOTIFICATION_CHECKIN = "notificationcheckin";
    private static final String TABLE_NOTIFICATION_APPOINMENT_SCHEDULE = "notificationappointmentschedule";
    private static final String TABLE_TINNHAN = "tinnhan";
    private static final String TABLE_ADDHINHHOC = "addhinhhoc";
    private static final String TABLE_ERROR = "loiphanmem";

    // key chung
    private static final String KEY_ID = "id";
    private static final String KEY_MAHANG = "mahang";
    private static final String KEY_TEN = "ten";
    private static final String KEY_IDHANG = "idhang";

    // key anh album
    private static final String KEY_PATH = "path";
    private static final String KEY_GHICHU_ANH = "ghichu";
    private static final String KEY_THOIGIAN_ANH = "thoigian";
    private static final String KEY_THOIGIAN_GUIANH = "thoigiangui";
    private static final String KEY_KINHDO_ANH = "kinhdo";
    private static final String KEY_VIDO_ANH = "vido";
    private static final String KEY_ACC_ANH = "acc";
    private static final String KEY_IDNV_ANH = "idnv";
    private static final String KEY_DIACHI_ANH = "diachi";

    //key gps
    private static final String KEY_ID_GPS = "id";
    private static final String KEY_KINHDO_GPS = "kinhdo";
    private static final String KEY_VIDO_GPS = "vido";
    private static final String KEY_THOIGIAN_GPS = "thoigian";
    private static final String KEY_THOIGIAN_SERVER_GPS = "thoigianserver";//thoi gian server
    private static final String KEY_PIN = "pin";
    private static final String KEY_DEVICE = "device";
    private static final String KEY_ACC_GPS = "accuracy";
    private static final String KEY_TYPE_GPS = "loai";

    private static final String KEY_STATUS_FAKE_GPS = "isFakeGPS";
    private static final String KEY_MSG_FAKE_GPS = "appfakegps";
    private static final String KEY_TYPE_NETWORK = "loaiketnoi";
    private static final String KEY_STATUS_GPS = "trangthaigps";
    private static final String KEY_STATUS_SAVING_POWER = "isCheDoTietKiemPin";
    private static final String KEY_SPEED = "speed";
    private static final String KEY_GPS_CONFIG = "gps_config";

    //key anh album
    private static final String KEY_IDANHALBUM = "idanhalbum";
    private static final String KEY_IDALBUM = "idalbum";

    //key album
    private static final String KEY_IDALBUM_DB = "id_album_db";
    private static final String KEY_IDALBUM_SV = "id_album_sv";
    private static final String KEY_TENALBUM = "tencuahang";
    private static final String KEY_DIACHIALBUM = "diachicuahang";
    private static final String KEY_DIACHIKHACHHANG = "diachikhachhang";
    private static final String KEY_IDCUAHANGALBUM = "idcuahang";
    private static final String KEY_IDLOAIALBUM = "loaialbum";
    private static final String KEY_ID_CHUCNANGALBUM = "chucnangalbum";
    private static final String KEY_IDNV_ALBUM = "idnv";
    private static final String KEY_THOIGIAN_ALBUM = "thoigian";
    private static final String KEY_KINHDO_ALBUM = "kinhdo";
    private static final String KEY_VIDO_ALBUM = "vido";
    private static final String KEY_ACC_ALBUM = "acc";
    private static final String KEY_GHICHU_ALBUM = "ghichu";
    private static final String KEY_LOAI = "loai";

    // key chitietdonhang
    private static final String KEY_IDDONHANG = "iddonhang";
    private static final String KEY_SOLUONG = "soluong";
    private static final String KEY_GHICHU = "ghichu";
    private static final String KEY_KHUYENMAI = "khuyenmai";

    // key hang
    private static final String KEY_DONGIABUON = "dongiabuon";
    private static final String KEY_DONGIALE = "dongiale";
    private static final String KEY_GIAKHAC = "giakhac";
    private static final String KEY_DONVI = "donvi";
    private static final String KEY_TONKHO = "tonkho";
    // key donhang
    private static final String KEY_IDCUAHANG = "idcuahang";
    private static final String KEY_NGAY = "ngay";
    private static final String KEY_SENDED = "sended";
    private static final String KEY_IDNV = "idnv";
    private static final String KEY_MATHAMCHIEU = "mathamchieu";
    private static final String KEY_TENCUAHANG = "tencuahang";
    private static final String KEY_MAKHACHHANG = "makhachhang";
    private static final String KEY_SDTKHACHHANG = "sodienthoaikhachang";
    private static final String KEY_MADONHANG = "madonhang";
    private static final String KEY_CTKM_DONHANG = "ctkm";
    private static final String KEY_MAKM_DONHANG = "makm";
    private static final String KEY_TIENCHIETKHAU_DONHANG = "tienchietkhau";
    private static final String KEY_TONGTIENCHIETKHAU_DONHANG = "tongtienchietkhau";
    private static final String KEY_PHANTRAMCHIETKHAU_DONHANG = "phanchietkhau";
    private static final String KEY_PHANTRAMCHIETKHAU_DONHANG_KHAC = "phantramchietkhaukhac";
    private static final String KEY_PHANTRAMCHIETKHAU_DONHANG_KM = "phantramchietkhaukhuyenmai";
    private static final String KEY_TIENCHIETKHAU_DONHANG_KM = "tienchietkhaukhuyenmai";
    private static final String KEY_TIENCHIETKHAU_DONHANG_KHAC = "tienchietkhaukhac";
    private static final String KEY_TENCHUONGTRINKHUYENMAI_DONHANG = "tenkhuyenmai";
    private static final String KEY_CHUONGTRINKHUYENMAI_DONHANG_NHIEU = "chuongtrinhkhuyenmai";
    private static final String KEY_THANHTIEN_DONHANG = "thanhtiendonhang";
    private static final String KEY_BUONORLE = "buonorle";

    // key PushLocation
    private static final String KEY_KINHDO = "kinhdo";
    private static final String KEY_VIDO = "vido";

    //key LapKeHoach
    private static final String KEY_IDKEHOACH = "id_kehoach";
    private static final String KEY_IDKHACHHANG = "id_khachhang";
    private static final String KEY_TENKHACHHANG = "ten_khachhang";
    private static final String KEY_TIMEIN = "timein";
    private static final String KEY_TIMEOUT = "timeout";
    private static final String KEY_NOTE = "ghichu";
    private static final String KEY_TRANGTHAI = "trangthai";
    private static final String KEY_DUOCCHON = "duocchon";
    private static final String KEY_DAGUI = "dagui";
    private static final String KEY_NGAYLAPKEHOACH = "ngaylapkehoach";

    private static final String KEY_SOLUONGTONKHO_ORDER = "soluongton";
    private static final String KEY_NHACUNGCAP = "nhacungcap";
    private static final String KEY_MOTA_MATHANG = "motamathang";
    private static final String KEY_LINK_GIOITHIEU_MATHANG = "linkgioithieumathang";
    private static final String KEY_TENNHANHIEU = "tennhanhieu";
    private static final String KEY_TENHIENTHI = "tenhienthi";
    private static final String KEY_GHICHUGIA = "ghichugia";
    private static final String KEY_CTKM_MATHANG = "ctkm";
    private static final String KEY_LOAIHANGHOA = "loaihanghoa";
    private static final String KEY_CTKM_MATHANG_CHITIET = "ctkm_chitiet";
    private static final String KEY_TIENCHIETKHAU_MATHANG = "tienchietkhau";
    private static final String KEY_PHANTRAMCHIETKHAU_MATHANG = "phanchietkhau";
    private static final String KEY_TONGTIENCHIETKHAU_MATHANG = "tongtienchietkhau";
    private static final String KEY_TONGTIENCHIETKHAUGIAKHAC_MATHANG = "tongchietkhau_giakhac";
    private static final String KEY_CTKM_MATHANG_NHIEUKHUYENMAI = "chuongtrinhkhuyenmainhieu";
    private static final String KEY_THANHTIEN_MATHANG = "thanhtien";
    private static final String KEY_IDHAOHUT = "idhaohut";
    private static final String KEY_SOLUONGHAOHUT = "soluonghaohut";
    private static final String KEY_TILEHAOHUT = "phantramhaohut";
    private static final String KEY_TONTHUCTE = "tonthucte";
    private static final String KEY_TONORDER = "tonorder";

    //key CONFIG_CAMERA
    private static final String KEY_CONFIG_FLASH_STATE = "flashstate";
    private static final String KEY_CONFIG_OPTION_STATE = "chuyencamera";

    //key TABLE_STATUS_CHECKIN
    private static final String KEY_STATUS_CHECKIN = "statuscheckin";
    private static final String KEY_IDCUAHANG_CHECKIN = "idcuahangcheckin";
    private static final String KEY_CURRENTTIME = "currenttime";
    private static final String KEY_KINHDO_CHECKIN = "kinhdo";
    private static final String KEY_VIDO_CHECKIN = "vido";
    private static final String KEY_ACC_CHECKIN = "acc";
    private static final String KEY_IDCHECKIN = "idcheckin";
    private static final String KEY_KINHDO_CUAHANG = "kinhdocuahang";
    private static final String KEY_VIDO_CUAHANG = "vidocuahang";
    private static final String KEY_TEN_CUAHANG = "tencuahang";
    private static final String KEY_DIACHI_CUAHANG = "diachicuahang";
    private static final String KEY_ID_KEHOACHKHUVUC = "idkehoachkhuvuc";

    //key TABLE_NOTIFICATION_APPOINMENT_SCHEDULE
    private static final String KEY_IDLICHHEN = "idlichhen";
    private static final String KEY_THOIGIANHEN = "thoigianhen";
    private static final String KEY_NOIDUNGHEN = "noidunghen";
    private static final String KEY_TYPEKEHOACH = "typekehoach";

    //KEY TABLE_TINNHAN
    private static final String KEY_ID_TINNHAN_DB = "idtinnhandb";
    private static final String KEY_TINNHAN_NOIDUNG = "noidungtinnhan";
    private static final String KEY_TINNHAN_TIEUDE = "tieudetinnhan";
    private static final String KEY_TINNHAN_THOIGIAN_LUU = "thoigianluu";
    private static final String KEY_TINNHAN_TYPE = "typetinnhan";
    private static final String KEY_TINNHAN_NGUOIGUI = "nguoiguitinnhan";
    private static final String KEY_ID_NHOM_QUANLY = "idnhomquanly";
    private static final String KEY_ID_QUANLY = "idquanly";
    //KEY TABLE_ADDHINHHOC
    private static final String KEY_CHIEUDAI = "chieudaimathang";
    private static final String KEY_CHIEURONG = "chieurongmathang";
    //KEY TABLE_ERROR
    private static final String KEY_ERROR_TIME = "thoigianloi";
    private static final String KEY_ERROR_CODE = "maloi";
    private static final String KEY_ERROR_CONTENT = "noidungloi";

    private static final String CREATE_TABLE_IMAGE_ALBUM = "CREATE TABLE "
            + TABLE_IMAGE_ALBUM + "("
            + KEY_IDANHALBUM + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_IDALBUM + " INTEGER,"
            + KEY_IDNV_ANH + " INTEGER, "
            + KEY_PATH + " TEXT,"
            + KEY_KINHDO_ANH + " TEXT,"
            + KEY_DIACHI_ANH + " TEXT,"
            + KEY_THOIGIAN_GUIANH + " TEXT,"
            + KEY_TENALBUM + " TEXT,"
            + KEY_THOIGIAN_ANH + " TEXT,"
            + KEY_LOAI + " INTEGER,"
            + KEY_ACC_ANH + " TEXT,"
            + KEY_VIDO_ANH + " TEXT,"
            + KEY_GHICHU_ANH + " TEXT)";

    private static final String CREATE_TABLE_IMAGE_QUEUE = "CREATE TABLE "
            + TABLE_IMAGE_QUEUE + "("
            + KEY_IDANHALBUM + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_IDALBUM + " INTEGER, "
            + KEY_IDALBUM_SV + " TEXT, "
            + KEY_IDNV_ANH + " INTEGER,"
            + KEY_PATH + " TEXT,"
            + KEY_KINHDO_ANH + " TEXT,"
            + KEY_DIACHI_ANH + " TEXT,"
            + KEY_THOIGIAN_GUIANH + " TEXT,"
            + KEY_TENALBUM + " TEXT,"
            + KEY_THOIGIAN_ANH + " TEXT,"
            + KEY_LOAI + " INTEGER,"
            + KEY_ACC_ANH + " TEXT,"
            + KEY_VIDO_ANH + " TEXT,"
            + KEY_GHICHU_ANH + " TEXT)";

    private static final String CREATE_TABLE_ALBUM = "CREATE TABLE "
            + TABLE_ALBUM + "("
            + KEY_IDALBUM_DB + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_IDALBUM_SV + " INTEGER,"
            + KEY_IDNV + " INTEGER,"
            + KEY_IDCUAHANGALBUM + " INTEGER,"
            + KEY_LOAI + " INTEGER,"
            + KEY_IDLOAIALBUM + " INTEGER,"
            + KEY_ID_CHUCNANGALBUM + " INTEGER,"
            + KEY_TENALBUM + " TEXT,"
            + KEY_KINHDO_ALBUM + " TEXT,"
            + KEY_ACC_ALBUM + " TEXT,"
            + KEY_DIACHIALBUM + " TEXT,"
            + KEY_GHICHU_ALBUM + " TEXT,"
            + KEY_VIDO_ALBUM + " TEXT,"
            + KEY_DIACHIKHACHHANG + " TEXT,"
            + KEY_THOIGIAN_ALBUM + " TEXT)";
    private static final String CREATE_TABLE_DONHANG = "CREATE TABLE "
            + TABLE_DONHANG + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_IDCUAHANG + " INTEGER,"
            + KEY_IDALBUM_DB + " INTEGER,"
            + KEY_NGAY + " TEXT,"
            + KEY_MATHAMCHIEU + " TEXT,"
            + KEY_TENCUAHANG + " TEXT,"
            + KEY_BUONORLE + " TEXT,"
            + KEY_TENCHUONGTRINKHUYENMAI_DONHANG + " TEXT,"
            + KEY_GHICHU + " TEXT,"
            + KEY_SENDED + " INTEGER,"
            + KEY_CTKM_DONHANG + " INTEGER,"
            + KEY_MAKM_DONHANG + " INTEGER,"
            + KEY_PHANTRAMCHIETKHAU_DONHANG + " TEXT,"
            + KEY_TONGTIENCHIETKHAU_DONHANG + " TEXT,"
            + KEY_TIENCHIETKHAU_DONHANG + " TEXT,"
            + KEY_THANHTIEN_DONHANG + " TEXT,"
            + KEY_MAKHACHHANG + " TEXT,"
            + KEY_SDTKHACHHANG + " TEXT,"
            + KEY_DIACHIKHACHHANG + " TEXT,"
            + KEY_MADONHANG + " TEXT,"
            + KEY_CHUONGTRINKHUYENMAI_DONHANG_NHIEU + " TEXT,"
            + KEY_PHANTRAMCHIETKHAU_DONHANG_KHAC + " TEXT,"
            + KEY_TIENCHIETKHAU_DONHANG_KHAC + " TEXT,"
            + KEY_PHANTRAMCHIETKHAU_DONHANG_KM + " TEXT,"
            + KEY_TIENCHIETKHAU_DONHANG_KM + " TEXT,"
            + KEY_IDNV + " INTEGER)";

    private static final String CREATE_TABLE_HANG = "CREATE TABLE "
            + TABLE_HANG + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_MAHANG + " TEXT,"
            + KEY_TEN + " TEXT,"
            + KEY_DONGIABUON + " INTEGER,"
            + KEY_BUONORLE + " INTEGER,"
            + KEY_KHUYENMAI + " TEXT, "
            + KEY_GIAKHAC + " TEXT, "
            + KEY_DONGIALE + " INTEGER,"
            + KEY_DONVI + " TEXT,"
            + KEY_TONKHO + " INTEGER)";


    private static final String CREATE_TABLE_ADDTEMP = "CREATE TABLE "
            + TABLE_ADDTEMP + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_IDHANG + " INTEGER,"
            + KEY_LOAIHANGHOA + " INTEGER,"
            + KEY_MAHANG + " TEXT,"
            + KEY_TEN + " TEXT,"
            + KEY_DONVI + " TEXT,"
            + KEY_DONGIABUON + " TEXT,"
            + KEY_DONGIALE + " TEXT,"
            + KEY_SOLUONG + " REAL,"
            + KEY_SOLUONGTONKHO_ORDER + " REAL,"
            + KEY_BUONORLE + " INTEGER,"
            + KEY_KHUYENMAI + " TEXT, "
            + KEY_GIAKHAC + " TEXT, "
            + KEY_THANHTIEN_MATHANG + " TEXT, "
            + KEY_CTKM_MATHANG + " INTEGER,"
            + KEY_CTKM_MATHANG_CHITIET + " INTEGER,"
            + KEY_PHANTRAMCHIETKHAU_MATHANG + " TEXT,"
            + KEY_TIENCHIETKHAU_MATHANG + " TEXT," + " TEXT,"
            + KEY_TONGTIENCHIETKHAU_MATHANG + " TEXT,"
            + KEY_TONGTIENCHIETKHAUGIAKHAC_MATHANG + " TEXT, "
            + KEY_GHICHUGIA + " TEXT,"
            + KEY_GHICHU + " TEXT,"
            + KEY_NHACUNGCAP + " TEXT,"
            + KEY_TENNHANHIEU + " TEXT,"
            + KEY_MOTA_MATHANG + " TEXT,"
            + KEY_LINK_GIOITHIEU_MATHANG + " TEXT,"
            + KEY_TENHIENTHI + " TEXT,"
            + KEY_SOLUONGHAOHUT + " REAL,"
            + KEY_TILEHAOHUT + " REAL,"
            + KEY_IDHAOHUT + " INTEGER,"
            + KEY_TONTHUCTE + " REAL,"
            + KEY_TONORDER + " REAL,"
            + KEY_CTKM_MATHANG_NHIEUKHUYENMAI + " TEXT, "
            + KEY_IDDONHANG + " INTEGER)";
    private static final String CREATE_TABLE_ADD_HINHHOC = "CREATE TABLE "
            + TABLE_ADDHINHHOC + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_IDHANG + " INTEGER,"
            + KEY_TEN + " TEXT,"
            + KEY_SOLUONG + " REAL,"
            + KEY_CHIEUDAI + " REAL,"
            + KEY_CHIEURONG + " REAL,"
            + KEY_IDDONHANG + " INTEGER)";

    private static final String CREATE_TABLE_NOTIFICATION_CHECKIN = "CREATE TABLE "
            + TABLE_NOTIFICATION_CHECKIN + "("
            + KEY_IDKEHOACH + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_IDKHACHHANG + " INTEGER,"
            + KEY_TYPEKEHOACH + " INTEGER,"
            + KEY_TENKHACHHANG + " TEXT,"
            + KEY_GHICHU + " TEXT,"
            + KEY_TIMEIN + " TEXT,"
            + KEY_TIMEOUT + " TEXT)";

    private static final String CREATE_TABLE_NOTIFICATION_APPOINMENT_SCHEDULE = "CREATE TABLE "
            + TABLE_NOTIFICATION_APPOINMENT_SCHEDULE + "("
            + KEY_IDLICHHEN + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_IDKHACHHANG + " INTEGER,"
            + KEY_TRANGTHAI + " INTEGER,"
            + KEY_NOIDUNGHEN + " TEXT,"
            + KEY_TENKHACHHANG + " TEXT,"
            + KEY_THOIGIANHEN + " TEXT)";

    private static final String CREATE_TABLE_PUSH = "CREATE TABLE "
            + TABLE_PUSH + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_KINHDO + " TEXT,"
            + KEY_VIDO + " TEXT)";

    private static final String CREATE_TABLE_GPS = "CREATE TABLE "
            + TABLE_GPS + "("
            + KEY_ID_GPS + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_KINHDO_GPS + " TEXT,"
            + KEY_IDNV + " TEXT,"
            + KEY_ACC_GPS + " TEXT,"
            + KEY_THOIGIAN_GPS + " TEXT,"
            + KEY_THOIGIAN_SERVER_GPS + " TEXT,"
            + KEY_DEVICE + " TEXT,"
            + KEY_PIN + " TEXT,"
            + KEY_TYPE_GPS + " TEXT,"
            + KEY_VIDO_GPS + " TEXT,"
            + KEY_MSG_FAKE_GPS + " TEXT,"
            + KEY_TYPE_NETWORK + " INTEGER,"
            + KEY_STATUS_GPS + " INTEGER,"
            + KEY_STATUS_SAVING_POWER + " INTEGER,"
            + KEY_GPS_CONFIG + " INTEGER,"
            + KEY_SPEED + " DOUBLE,"
            + KEY_STATUS_FAKE_GPS + " INTEGER)";

    private static final String CREATE_TABLE_KEHOACH = "CREATE TABLE "
            + TABLE_LAPKEHOACH + "("
            + KEY_IDKEHOACH + " INTEGER PRIMARY KEY  AUTOINCREMENT ,"
            + KEY_IDKHACHHANG + " TEXT,"
            + KEY_TENKHACHHANG + " TEXT,"
            + KEY_TIMEIN + " TEXT,"
            + KEY_TIMEOUT + " TEXT,"
            + KEY_NOTE + " TEXT,"
            + KEY_TRANGTHAI + " TEXT,"
            + KEY_DUOCCHON + " TEXT,"
            + KEY_DAGUI + " TEXT,"
            + KEY_NGAYLAPKEHOACH + " TEXT)";

    private static final String CREATE_TABLE_CONFIG_CAMERA = "CREATE TABLE if not exists "
            + TABLE_CONFIG_CAMERA + "("
            + KEY_CONFIG_FLASH_STATE + " TEXT,"
            + KEY_CONFIG_OPTION_STATE + " INTEGER)";
    private static final String CREATE_TABLE_TINNHAN = "CREATE TABLE if not exists "
            + TABLE_TINNHAN + "("
            + KEY_ID_TINNHAN_DB + " INTEGER PRIMARY KEY  AUTOINCREMENT ,"
            + KEY_IDNV + " INTEGER,"
            + KEY_ID_NHOM_QUANLY + " INTEGER,"
            + KEY_ID_QUANLY + " INTEGER,"
            + KEY_TINNHAN_TYPE + " INTEGER,"
            + KEY_TINNHAN_NGUOIGUI + " TEXT,"
            + KEY_TINNHAN_THOIGIAN_LUU + " TEXT,"
            + KEY_TINNHAN_TIEUDE + " TEXT,"
            + KEY_TINNHAN_NOIDUNG + " TEXT)";

    private static final String CREATE_TABLE_STATUS_CHECKIN = "CREATE TABLE if not exists "
            + TABLE_STATUS_CHECKIN + "("
            + KEY_STATUS_CHECKIN + " INTEGER,"
            + KEY_IDCUAHANG_CHECKIN + " INTEGER,"
            + KEY_CURRENTTIME + " TEXT,"
            + KEY_KINHDO_CHECKIN + " TEXT,"
            + KEY_VIDO_CHECKIN + " TEXT,"
            + KEY_ACC_CHECKIN + " TEXT,"
            + KEY_KINHDO_CUAHANG + " TEXT,"
            + KEY_VIDO_CUAHANG + " TEXT,"
            + KEY_TEN_CUAHANG + " TEXT,"
            + KEY_MAKHACHHANG + " TEXT,"
            + KEY_SDTKHACHHANG + " TEXT,"
            + KEY_DIACHI_CUAHANG + " TEXT,"
            + KEY_IDNV + " TEXT,"
            + KEY_ID_KEHOACHKHUVUC + " INTEGER,"
            + KEY_IDCHECKIN + " INTEGER)";

    private static final String CREATE_TABLE_ERROR = "CREATE TABLE if not exists "
            + TABLE_ERROR + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_ERROR_TIME + " TEXT,"
            + KEY_ERROR_CODE + " TEXT,"
            + KEY_ERROR_CONTENT + " TEXT)";

    private DataBaseHanlder(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DONHANG);
        db.execSQL(CREATE_TABLE_HANG);
        db.execSQL(CREATE_TABLE_ADDTEMP);
        db.execSQL(CREATE_TABLE_PUSH);
        db.execSQL(CREATE_TABLE_KEHOACH);
        db.execSQL(CREATE_TABLE_ALBUM);
        db.execSQL(CREATE_TABLE_IMAGE_ALBUM);
        db.execSQL(CREATE_TABLE_GPS);
        db.execSQL(CREATE_TABLE_IMAGE_QUEUE);
        db.execSQL(CREATE_TABLE_CONFIG_CAMERA);
        db.execSQL(CREATE_TABLE_STATUS_CHECKIN);
        db.execSQL(CREATE_TABLE_NOTIFICATION_CHECKIN);
        db.execSQL(CREATE_TABLE_NOTIFICATION_APPOINMENT_SCHEDULE);
        db.execSQL(CREATE_TABLE_TINNHAN);
        db.execSQL(CREATE_TABLE_ADD_HINHHOC);
        db.execSQL(CREATE_TABLE_ERROR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.e("UPDATE DB OLD TO NEW", oldVersion + " - " + newVersion);
        if (oldVersion == newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DONHANG);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_HANG);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDTEMP);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PUSH);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_LAPKEHOACH);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALBUM);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE_ALBUM);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_GPS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE_QUEUE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONFIG_CAMERA);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATUS_CHECKIN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION_CHECKIN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION_APPOINMENT_SCHEDULE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TINNHAN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDHINHHOC);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION_APPOINMENT_SCHEDULE);

            onCreate(db);
        }
        if (oldVersion == 1 && newVersion == 3) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_CTKM_DONHANG + " INTEGER");
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_MAKM_DONHANG + " INTEGER");
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_PHANTRAMCHIETKHAU_DONHANG + " TEXT ");
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_TONGTIENCHIETKHAU_DONHANG + " TEXT ");
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_TIENCHIETKHAU_DONHANG + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_THANHTIEN_MATHANG + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_CTKM_MATHANG + " INTEGER");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_CTKM_MATHANG_CHITIET + " INTEGER");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_PHANTRAMCHIETKHAU_MATHANG + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_TIENCHIETKHAU_MATHANG + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_TONGTIENCHIETKHAU_MATHANG + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_TONGTIENCHIETKHAUGIAKHAC_MATHANG + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_TENCHUONGTRINKHUYENMAI_DONHANG + " TEXT");
            } catch (Exception e) {
                Log.e("ERRor, database", e.toString());
            }
        } else if (oldVersion == 2 && newVersion == 3) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_TENCHUONGTRINKHUYENMAI_DONHANG + " TEXT");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 4) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_GHICHUGIA + " TEXT");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 5) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_MADONHANG + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_MAKHACHHANG + " TEXT");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 5) {
            try {
                db.execSQL(CREATE_TABLE_GPS);
                db.execSQL(CREATE_TABLE_IMAGE_QUEUE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 6) {
            try {
                db.execSQL(CREATE_TABLE_CONFIG_CAMERA);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 7) {
            try {
                db.execSQL(CREATE_TABLE_STATUS_CHECKIN);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 8) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_GPS + " ADD COLUMN " + KEY_TYPE_NETWORK + " INTEGER");
                db.execSQL("ALTER TABLE " + TABLE_GPS + " ADD COLUMN " + KEY_STATUS_GPS + " INTEGER");
                db.execSQL("ALTER TABLE " + TABLE_GPS + " ADD COLUMN " + KEY_STATUS_FAKE_GPS + " INTEGER");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 9) {
            try {

                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_SOLUONGTONKHO_ORDER + " REAL");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 10) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_GPS + " ADD COLUMN " + KEY_THOIGIAN_SERVER_GPS + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_STATUS_CHECKIN + " ADD COLUMN " + KEY_DIACHI_CUAHANG + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_STATUS_CHECKIN + " ADD COLUMN " + KEY_TEN_CUAHANG + " TEXT");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 11) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_GPS + " ADD COLUMN " + KEY_STATUS_SAVING_POWER + " INTEGER");
                db.execSQL("ALTER TABLE " + TABLE_GPS + " ADD COLUMN " + KEY_SPEED + " DOUBLE");
                db.execSQL("ALTER TABLE " + TABLE_STATUS_CHECKIN + " ADD COLUMN " + KEY_IDNV + " TEXT");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 12) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_GPS + " ADD COLUMN " + KEY_MSG_FAKE_GPS + " TEXT");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 13) {
            try {
                db.execSQL(CREATE_TABLE_NOTIFICATION_APPOINMENT_SCHEDULE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 14) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_NHACUNGCAP + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_TENNHANHIEU + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_STATUS_CHECKIN + " ADD COLUMN " + KEY_MAKHACHHANG + " TEXT");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 15) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_ALBUM + " ADD COLUMN " + KEY_IDLOAIALBUM + " INTEGER");
                db.execSQL("ALTER TABLE " + TABLE_ALBUM + " ADD COLUMN " + KEY_ID_CHUCNANGALBUM + " INTEGER");
                db.execSQL(CREATE_TABLE_TINNHAN);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 16) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_MOTA_MATHANG + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_LINK_GIOITHIEU_MATHANG + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_TENHIENTHI + " TEXT");
                db.execSQL(CREATE_TABLE_ADDTEMP);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 17) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_LOAIHANGHOA + " INTEGER");
                db.execSQL("ALTER TABLE " + TABLE_NOTIFICATION_CHECKIN + " ADD COLUMN " + KEY_TYPEKEHOACH + " INTEGER");
                db.execSQL("ALTER TABLE " + TABLE_STATUS_CHECKIN + " ADD COLUMN " + KEY_ID_KEHOACHKHUVUC + " INTEGER");
                db.execSQL(CREATE_TABLE_ADD_HINHHOC);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 18) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_IDHAOHUT + " INTEGER");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_SOLUONGHAOHUT + " REAL");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_TILEHAOHUT + " REAL");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 19) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_ALBUM + " ADD COLUMN " + KEY_DIACHIKHACHHANG + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_SDTKHACHHANG + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_DIACHIKHACHHANG + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_STATUS_CHECKIN + " ADD COLUMN " + KEY_SDTKHACHHANG + " TEXT");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 20) {
            try {

                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_IDALBUM_DB + " INTEGER");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_TONTHUCTE + " REAL");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_TONORDER + " REAL");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 21) {
            try {
                //db.execSQL("ALTER TABLE " + TABLE_GPS + " ADD COLUMN " + KEY_GPS_CONFIG+ " INTEGER");
                //db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_IDALBUM_DB + " INTEGER");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 22) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_GPS + " ADD COLUMN " + KEY_GPS_CONFIG + " INTEGER");
                //db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_IDALBUM_DB + " INTEGER");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 23) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_CHUONGTRINKHUYENMAI_DONHANG_NHIEU + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_THANHTIEN_DONHANG + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_ADDTEMP + " ADD COLUMN " + KEY_CTKM_MATHANG_NHIEUKHUYENMAI + " TEXT");
                //db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_IDALBUM_DB + " INTEGER");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 24) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_TIENCHIETKHAU_DONHANG_KHAC + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_PHANTRAMCHIETKHAU_DONHANG_KHAC + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_TIENCHIETKHAU_DONHANG_KM + " TEXT");
                db.execSQL("ALTER TABLE " + TABLE_DONHANG + " ADD COLUMN " + KEY_PHANTRAMCHIETKHAU_DONHANG_KM + " TEXT");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oldVersion != newVersion && newVersion == 25) {
            try {
                db.execSQL(CREATE_TABLE_ERROR);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public List<CameraOBJ> getAllCameraOBJ() {
        List<CameraOBJ> cameraOBJs = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONFIG_CAMERA;

        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CameraOBJ cmOBJ = new CameraOBJ();
                cmOBJ.setChonFlash(cursor.getString(cursor.getColumnIndex(KEY_CONFIG_FLASH_STATE)));
                cmOBJ.setChuyencamera(cursor.getInt(cursor.getColumnIndex(KEY_CONFIG_OPTION_STATE)));
                cameraOBJs.add(cmOBJ);
            } while (cursor.moveToNext());
        }
        db.close();
        return cameraOBJs;
    }
    public boolean isContainCamera(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String select = "SELECT * FROM " + TABLE_CONFIG_CAMERA + " WHERE " + KEY_CONFIG_OPTION_STATE + " = " + id;
            cursor = db.rawQuery(select, null);

            if (cursor.getCount() > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //db.close();
        }
        return false;
    }

    public synchronized void updateInfoCamera(CameraOBJ cameraOBJ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(KEY_CONFIG_FLASH_STATE, cameraOBJ.getChuyencamera());
            values.put(KEY_CONFIG_OPTION_STATE, cameraOBJ.getChonFlash());

            db.update(TABLE_CONFIG_CAMERA, values, KEY_CONFIG_OPTION_STATE + " =? ", new String[]{String.valueOf(cameraOBJ.getChuyencamera())});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void insertConfigCamera(CameraOBJ cameraOBJ) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_CONFIG_FLASH_STATE, cameraOBJ.getChonFlash());
            values.put(String.valueOf(KEY_CONFIG_OPTION_STATE), cameraOBJ.getChuyencamera());
            id = db.insert(TABLE_CONFIG_CAMERA, null, values);
        } catch (SQLiteException e) {
            if (e.getMessage().contains("no such table")) {
                try {
                    db.execSQL(CREATE_TABLE_CONFIG_CAMERA);
                    ContentValues values = new ContentValues();
                    values.put(KEY_CONFIG_FLASH_STATE, cameraOBJ.getChonFlash());
                    values.put(String.valueOf(KEY_CONFIG_OPTION_STATE), cameraOBJ.getChuyencamera());
                    id = db.insert(TABLE_CONFIG_CAMERA, null, values);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public synchronized void deleteConfigCamera() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_CONFIG_CAMERA);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void insertImageALBUM(ImageAlbumObject image) {
        SQLiteDatabase db = this.getWritableDatabase();

        long id = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_IDALBUM, image.getIdalbum());
            values.put(KEY_THOIGIAN_ANH, image.getThoigianchup());
            values.put(KEY_TENALBUM, image.getTendaily());
            values.put(KEY_KINHDO_ANH, image.getKinhdo());
            values.put(KEY_VIDO_ANH, image.getVido());
            values.put(KEY_ACC_ANH, image.getAcc());
            values.put(KEY_DIACHI_ANH, image.getDiachi());
            values.put(KEY_GHICHU_ANH, image.getGhichu());
            values.put(KEY_PATH, image.getPath());
            values.put(KEY_IDNV_ANH, image.getIdnhanvien());
            values.put(KEY_LOAI, image.getType());
            values.put(KEY_THOIGIAN_GUIANH, image.getThoigiangui());
            id = db.insert(TABLE_IMAGE_ALBUM, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db.close();
        }
    }
    public void deleteImageALBUM(String path) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_IMAGE_ALBUM, KEY_PATH + " =? ",
                    new String[]{path});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db.close();
        }
    }
    public List<ImageAlbumObject> selectSomeImageALBUM(int idsuvu) {
        List<ImageAlbumObject> adds = new ArrayList<>();
        String select = "SELECT * FROM " + TABLE_IMAGE_ALBUM + " WHERE "
                + KEY_IDALBUM + " = " + idsuvu + " " +
                "ORDER BY date(thoigian) ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(select, null);

            if (cursor.moveToFirst()) {
                do {
                    ImageAlbumObject image = new ImageAlbumObject();
                    image.setIdimage(cursor.getInt(cursor.getColumnIndex(KEY_IDANHALBUM)));
                    image.setIdalbum(cursor.getInt(cursor.getColumnIndex(KEY_IDALBUM)));
                    image.setIdnhanvien(cursor.getInt(cursor.getColumnIndex(KEY_IDNV_ANH)));
                    image.setGhichu(cursor.getString(cursor.getColumnIndex(KEY_GHICHU)));
                    image.setTendaily(cursor.getString(cursor.getColumnIndex(KEY_TENALBUM)));
                    image.setKinhdo(cursor.getDouble(cursor.getColumnIndex(KEY_KINHDO_ANH)));
                    image.setVido(cursor.getDouble(cursor.getColumnIndex(KEY_VIDO_ANH)));
                    image.setPath(cursor.getString(cursor.getColumnIndex(KEY_PATH)));
                    image.setDiachi(cursor.getString(cursor.getColumnIndex(KEY_DIACHI_ANH)));
                    image.setThoigianchup(cursor.getString(cursor.getColumnIndex(KEY_THOIGIAN_ANH)));
                    image.setThoigiangui(cursor.getString(cursor.getColumnIndex(KEY_THOIGIAN_GUIANH)));
                    image.setType(cursor.getInt(cursor.getColumnIndex(KEY_LOAI)));
                    adds.add(image);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //db.close();
        }
        return adds;
    }
    public List<ImageAlbumObject> selectSomeImageQUEUE(int idnv) {
        List<ImageAlbumObject> adds = new ArrayList<>();
        String select = "SELECT * FROM " + TABLE_IMAGE_QUEUE + " WHERE "
                + KEY_IDNV + " = " + idnv /*+" " +
                "ORDER BY date(thoigian) ASC"*/;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(select, null);

            if (cursor.moveToFirst()) {
                do {
                    ImageAlbumObject image = new ImageAlbumObject();
                    image.setIdimage(cursor.getInt(cursor.getColumnIndex(KEY_IDANHALBUM)));
                    image.setIdalbum(cursor.getInt(cursor.getColumnIndex(KEY_IDALBUM)));
                    image.setIdalbum_server(cursor.getInt(cursor.getColumnIndex(KEY_IDALBUM_SV)));
                    image.setIdnhanvien(cursor.getInt(cursor.getColumnIndex(KEY_IDNV_ANH)));
                    image.setGhichu(cursor.getString(cursor.getColumnIndex(KEY_GHICHU)));
                    image.setTendaily(cursor.getString(cursor.getColumnIndex(KEY_TENALBUM)));
                    image.setKinhdo(cursor.getDouble(cursor.getColumnIndex(KEY_KINHDO_ANH)));
                    image.setVido(cursor.getDouble(cursor.getColumnIndex(KEY_VIDO_ANH)));
                    image.setPath(cursor.getString(cursor.getColumnIndex(KEY_PATH)));
                    image.setDiachi(cursor.getString(cursor.getColumnIndex(KEY_DIACHI_ANH)));
                    image.setThoigianchup(cursor.getString(cursor.getColumnIndex(KEY_THOIGIAN_ANH)));
                    image.setThoigiangui(cursor.getString(cursor.getColumnIndex(KEY_THOIGIAN_GUIANH)));
                    image.setType(cursor.getInt(cursor.getColumnIndex(KEY_LOAI)));
                    adds.add(image);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //db.close();
        }
        return adds;
    }
    public ImageAlbumObject select1Image() {
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + TABLE_IMAGE_QUEUE + " LIMIT 1";/* + KEY_PATH
                + " = " + path;*/
        Cursor cursor = null;
        ImageAlbumObject image = null;
        try {
            cursor = db.rawQuery(select, null);

            image = null;
            if (cursor != null && cursor.moveToNext()) {
                image = new ImageAlbumObject();

                image.setIdimage(cursor.getInt(cursor.getColumnIndex(KEY_IDANHALBUM)));
                image.setIdalbum(cursor.getInt(cursor.getColumnIndex(KEY_IDALBUM)));
                image.setIdalbum_server(cursor.getInt(cursor.getColumnIndex(KEY_IDALBUM_SV)));
                image.setIdnhanvien(cursor.getInt(cursor.getColumnIndex(KEY_IDNV_ANH)));
                image.setGhichu(cursor.getString(cursor.getColumnIndex(KEY_GHICHU)));
                image.setTendaily(cursor.getString(cursor.getColumnIndex(KEY_TENALBUM)));
                image.setKinhdo(cursor.getDouble(cursor.getColumnIndex(KEY_KINHDO_ANH)));
                image.setVido(cursor.getDouble(cursor.getColumnIndex(KEY_VIDO_ANH)));
                image.setAcc(cursor.getDouble(cursor.getColumnIndex(KEY_ACC_ANH)));
                image.setPath(cursor.getString(cursor.getColumnIndex(KEY_PATH)));
                image.setDiachi(cursor.getString(cursor.getColumnIndex(KEY_DIACHI_ANH)));
                image.setType(cursor.getInt(cursor.getColumnIndex(KEY_LOAI)));
                image.setThoigianchup(cursor.getString(cursor.getColumnIndex(KEY_THOIGIAN_ANH)));
                image.setThoigiangui(cursor.getString(cursor.getColumnIndex(KEY_THOIGIAN_GUIANH)));
                return image;


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //db.close();
        }

        return null;
    }

    public void deleteImageQUEUEbyID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_IMAGE_QUEUE, KEY_IDANHALBUM + " =? ",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db.close();
        }
    }
    public void updateImageALBUMByPath(ImageAlbumObject image) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(KEY_GHICHU_ANH, image.getGhichu());
            values.put(KEY_LOAI, image.getType());
            if (!image.getThoigiangui().equals("")) {
                values.put(KEY_THOIGIAN_GUIANH, image.getThoigiangui());
            }
            db.update(TABLE_IMAGE_ALBUM, values, KEY_PATH + " =? ", new String[]{String.valueOf(image.getPath())});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db.close();
        }
    }

    public void deleteAllImageALBUM(int idALBUM) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_IMAGE_ALBUM, KEY_IDALBUM + " =? ",
                    new String[]{String.valueOf(idALBUM)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db.close();
        }
    }
    public int CheckAllSend(int idsuvu) {
        List<ImageAlbumObject> adds = new ArrayList<>();
        String select = "SELECT * FROM " + TABLE_IMAGE_ALBUM + " WHERE "
                + KEY_IDALBUM + " = " + idsuvu + " AND  " + KEY_LOAI
                + " != 2 " +
                "ORDER BY date(thoigian) ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(select, null);

            if (cursor.moveToFirst()) {
                do {
                    ImageAlbumObject image = new ImageAlbumObject();
                    image.setIdimage(cursor.getInt(cursor.getColumnIndex(KEY_IDANHALBUM)));
                    image.setIdalbum(cursor.getInt(cursor.getColumnIndex(KEY_IDALBUM)));
                    image.setIdnhanvien(cursor.getInt(cursor.getColumnIndex(KEY_IDNV_ANH)));
                    image.setGhichu(cursor.getString(cursor.getColumnIndex(KEY_GHICHU)));
                    image.setTendaily(cursor.getString(cursor.getColumnIndex(KEY_TENALBUM)));
                    image.setKinhdo(cursor.getDouble(cursor.getColumnIndex(KEY_KINHDO_ANH)));
                    image.setVido(cursor.getDouble(cursor.getColumnIndex(KEY_VIDO_ANH)));
                    image.setPath(cursor.getString(cursor.getColumnIndex(KEY_PATH)));
                    image.setDiachi(cursor.getString(cursor.getColumnIndex(KEY_DIACHI_ANH)));
                    image.setThoigianchup(cursor.getString(cursor.getColumnIndex(KEY_THOIGIAN_ANH)));
                    image.setThoigiangui(cursor.getString(cursor.getColumnIndex(KEY_THOIGIAN_GUIANH)));
                    image.setType(cursor.getInt(cursor.getColumnIndex(KEY_LOAI)));
                    adds.add(image);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //db.close();
        }
        return adds.size();
    }
    public void deleteALBUM(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_ALBUM, KEY_IDALBUM_DB + " =? ",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db.close();
        }
    }
    //TODO update ho so đã có trong cơ sở dữ liệu
    public void updateAlbumServer(AlbumObject ALBUM) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(KEY_IDALBUM_SV, ALBUM.getId_server());
            values.put(KEY_IDCUAHANGALBUM, ALBUM.getId_khachhang());
            values.put(KEY_TENALBUM, ALBUM.getTencuahang());
            values.put(KEY_GHICHU_ALBUM, ALBUM.getGhichu());
            values.put(KEY_DIACHIALBUM, ALBUM.getDiachi());
            values.put(KEY_DIACHIKHACHHANG, ALBUM.getDiachicuahang());
            values.put(KEY_KINHDO_ALBUM, ALBUM.getKinhdo());
            values.put(KEY_VIDO_ALBUM, ALBUM.getVido());
            values.put(KEY_ACC_ALBUM, ALBUM.getAccuracy());
            values.put(KEY_THOIGIAN_ALBUM, ALBUM.getThoigiantao());
            values.put(KEY_IDNV, ALBUM.getIdnv());
            values.put(KEY_LOAI, ALBUM.getRealType());
            values.put(KEY_IDLOAIALBUM, ALBUM.getIdloaialbum());
            values.put(KEY_ID_CHUCNANGALBUM, ALBUM.getIdchucnangalbum());
            db.update(TABLE_ALBUM, values, KEY_IDALBUM_SV + " =? ", new String[]{String.valueOf(ALBUM.getId_server())});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db.close();
        }
    }
    //hang doi
    public void insertImageQUEUE(ImageAlbumObject image) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(KEY_IDALBUM, image.getIdalbum());
            values.put(KEY_IDALBUM_SV, image.getIdalbum_server());
            values.put(KEY_THOIGIAN_ANH, image.getThoigianchup());
            values.put(KEY_TENALBUM, image.getTendaily());
            values.put(KEY_KINHDO_ANH, image.getKinhdo());
            values.put(KEY_VIDO_ANH, image.getVido());
            values.put(KEY_ACC_ANH, image.getAcc());
            values.put(KEY_DIACHI_ANH, image.getDiachi());
            values.put(KEY_GHICHU_ANH, image.getGhichu());
            values.put(KEY_PATH, image.getPath());
            values.put(KEY_IDNV_ANH, image.getIdnhanvien());
            values.put(KEY_LOAI, image.getType());
            values.put(KEY_THOIGIAN_GUIANH, image.getThoigiangui());
            db.insert(TABLE_IMAGE_QUEUE, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db.close();
        }
    }
    /// ALBUM
    public long insertALBUM(AlbumObject ALBUM) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            values.put(KEY_IDALBUM_SV, ALBUM.getId_server());
            values.put(KEY_TENALBUM, ALBUM.getTencuahang());
            values.put(KEY_GHICHU_ALBUM, ALBUM.getGhichu());
            values.put(KEY_KINHDO_ALBUM, ALBUM.getKinhdo());
            values.put(KEY_VIDO_ALBUM, ALBUM.getVido());
            values.put(KEY_ACC_ALBUM, ALBUM.getAccuracy());
            values.put(KEY_THOIGIAN_ALBUM, ALBUM.getThoigiantao());
            values.put(KEY_DIACHIALBUM, ALBUM.getDiachi());
            values.put(KEY_DIACHIKHACHHANG, ALBUM.getDiachicuahang());
            values.put(KEY_IDNV, ALBUM.getIdnv());
            values.put(KEY_IDCUAHANGALBUM, ALBUM.getId_khachhang());
            values.put(KEY_LOAI, ALBUM.getType());
            values.put(KEY_IDLOAIALBUM, ALBUM.getIdloaialbum());
            values.put(KEY_ID_CHUCNANGALBUM, ALBUM.getIdchucnangalbum());
            return db.insert(TABLE_ALBUM, null, values);
        } catch (Exception e) {
            return -1;
        } finally {
            //db.close();
        }
    }
    //TODO update ho so đã có trong cơ sở dữ liệu
    public void updateALBUM(AlbumObject ALBUM) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(KEY_IDALBUM_SV, ALBUM.getId_server());
            values.put(KEY_IDCUAHANGALBUM, ALBUM.getId_khachhang());
            values.put(KEY_TENALBUM, ALBUM.getTencuahang());
            values.put(KEY_GHICHU_ALBUM, ALBUM.getGhichu());
            values.put(KEY_DIACHIALBUM, ALBUM.getDiachi());
            values.put(KEY_KINHDO_ALBUM, ALBUM.getKinhdo());
            values.put(KEY_VIDO_ALBUM, ALBUM.getVido());
            values.put(KEY_ACC_ALBUM, ALBUM.getAccuracy());
            values.put(KEY_THOIGIAN_ALBUM, ALBUM.getThoigiantao());
            values.put(KEY_IDNV, ALBUM.getIdnv());
            values.put(KEY_ID_CHUCNANGALBUM, ALBUM.getIdchucnangalbum());
            values.put(KEY_LOAI, ALBUM.getRealType());
            db.update(TABLE_ALBUM, values, KEY_IDALBUM_DB + " =? ", new String[]{String.valueOf(ALBUM.getId())});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //db.close();
        }
    }
    public boolean isContainALBUM(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String select = "SELECT * FROM " + TABLE_ALBUM + " WHERE " + KEY_IDALBUM_SV
                    + " = " + id;
            cursor = db.rawQuery(select, null);

            if (cursor.getCount() > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //db.close();
        }
        return false;
    }
    public AlbumObject select1ALBUMbyidServer(long id) {

        String select = "SELECT * FROM " + TABLE_ALBUM + " WHERE " + KEY_IDALBUM_SV
                + " = " + id;
        AlbumObject ALBUM = null;
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(select, null);


            if (cursor != null && cursor.moveToNext()) {
                ALBUM = new AlbumObject();

                ALBUM.setId(cursor.getInt(cursor.getColumnIndex(KEY_IDALBUM_DB)));
                ALBUM.setId_server(cursor.getInt(cursor.getColumnIndex(KEY_IDALBUM_SV)));
                ALBUM.setThoigiantao(cursor.getString(cursor.getColumnIndex(KEY_THOIGIAN_ALBUM)));
                ALBUM.setType(cursor.getInt(cursor.getColumnIndex(KEY_LOAI)));
                ALBUM.setId_khachhang(cursor.getInt(cursor.getColumnIndex(KEY_IDCUAHANGALBUM)));
                ALBUM.setGhichu(cursor.getString(cursor.getColumnIndex(KEY_GHICHU)));
                ALBUM.setDiachi(cursor.getString(cursor.getColumnIndex(KEY_DIACHIALBUM)));
                ALBUM.setKinhdo(cursor.getDouble(cursor.getColumnIndex(KEY_KINHDO_ALBUM)));
                ALBUM.setIdnv(cursor.getInt(cursor.getColumnIndex(KEY_IDNV_ALBUM)));
                ALBUM.setVido(cursor.getDouble(cursor.getColumnIndex(KEY_VIDO_ALBUM)));
                ALBUM.setTencuahang(cursor.getString(cursor.getColumnIndex(KEY_TENALBUM)));
                ALBUM.setIdloaialbum(cursor.getInt(cursor.getColumnIndex(KEY_IDLOAIALBUM)));
                ALBUM.setIdchucnangalbum(cursor.getInt(cursor.getColumnIndex(KEY_ID_CHUCNANGALBUM)));
                return ALBUM;


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public ArrayList<AlbumObject> selectSomeALBUMTheoNgay(int idnv, int idchucnangalbum, String ngayBatDau, String ngayKetThuc) {
        ArrayList<AlbumObject> ALBUMs = new ArrayList<>();
        String select = "";
        Date strDate1 = null;
        Date strDate2 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            strDate1 = sdf.parse((ngayBatDau));
            strDate2 = sdf.parse((ngayKetThuc));
            Calendar c = Calendar.getInstance();
            c.setTime(strDate2);
            c.add(Calendar.DATE, 1);
            strDate2 = c.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (idnv == 0) {
            select = "SELECT * FROM " + TABLE_ALBUM + " WHERE " + KEY_IDNV
                    + " = " + idnv + " AND "
                    + KEY_ID_CHUCNANGALBUM + " = " + idchucnangalbum
                    + " ORDER BY date(thoigian) DESC";

            //+ " ORDER BY date(thoigian) ASC";
        } else {
            select = "SELECT * FROM " + TABLE_ALBUM + " WHERE " + KEY_IDNV
                    + " = " + idnv + " AND "
                    + KEY_ID_CHUCNANGALBUM + " = " + idchucnangalbum
                    + " ORDER BY date(thoigian) DESC";
            //+ " ORDER BY date(thoigian) ASC";
        }


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(select, null);
            if (cursor.moveToFirst()) {
                do {
                    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date dateAlbum = date.parse(cursor.getString(cursor.getColumnIndex(KEY_THOIGIAN_ALBUM)));
                    if (dateAlbum.compareTo(strDate1) >= 0 && dateAlbum.compareTo(strDate2) <= 0) {
                        AlbumObject ALBUM = new AlbumObject();
                        ALBUM.setId(cursor.getInt(cursor.getColumnIndex(KEY_IDALBUM_DB)));
                        ALBUM.setId_server(cursor.getInt(cursor.getColumnIndex(KEY_IDALBUM_SV)));
                        ALBUM.setThoigiantao(cursor.getString(cursor.getColumnIndex(KEY_THOIGIAN_ALBUM)));
                        ALBUM.setType(cursor.getInt(cursor.getColumnIndex(KEY_LOAI)));
                        ALBUM.setId_khachhang(cursor.getInt(cursor.getColumnIndex(KEY_IDCUAHANGALBUM)));
                        ALBUM.setGhichu(cursor.getString(cursor.getColumnIndex(KEY_GHICHU)));
                        ALBUM.setDiachi(cursor.getString(cursor.getColumnIndex(KEY_DIACHIALBUM)));
                        ALBUM.setDiachicuahang(cursor.getString(cursor.getColumnIndex(KEY_DIACHIKHACHHANG)));
                        ALBUM.setKinhdo(cursor.getDouble(cursor.getColumnIndex(KEY_KINHDO_ALBUM)));
                        ALBUM.setIdnv(cursor.getInt(cursor.getColumnIndex(KEY_IDNV_ALBUM)));
                        ALBUM.setVido(cursor.getDouble(cursor.getColumnIndex(KEY_VIDO_ALBUM)));
                        ALBUM.setTencuahang(cursor.getString(cursor.getColumnIndex(KEY_TENALBUM)));
                        ALBUM.setIdloaialbum(cursor.getInt(cursor.getColumnIndex(KEY_IDLOAIALBUM)));
                        ALBUM.setIdchucnangalbum(cursor.getInt(cursor.getColumnIndex(KEY_ID_CHUCNANGALBUM)));
                        ALBUMs.add(ALBUM);
                    }

                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //db.close();
        }

        return ALBUMs;

    }
}
