package vn.lachongmedia.appnv;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;

import vn.lachongmedia.appnv.R;
import com.google.android.material.textfield.TextInputEditText;

import android.provider.MediaStore;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.util.Strings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by tungda on 7/16/2019.
 */
public class Common {
    public static final String URL_COMMON="http://14.232.243.93:8080";
    public static final String UrlServer = "http://sv.ksmart.vn/";
    public static final String VersionCode_Login = "2.0.1.9";

    public static final String MaCongTy = "macongty";
    public static final String UserName = "username";
    public static final String PassWord = "password";
    public static final String iDNhanVien = "idnhanvien";
    public static final String KEY_IDQLLH = "idqllh";
    public static final String KEY_LOAIALBUM = "loaialbum";

    public static final String TrangThaiNutCheckLogin = "trangthaichecklogin";
    public static final String ListAppFake = "listappfake";
    public static final String ThongTinCongTy = "thongtincongty";
    public static final String ThoiGianGoiApiFakeGps = "thoigiangoifakegps";
    public static final String TAG_STATUS = "status";
    public static Boolean flagguixongalbum = false;
    //bien luu Activity hien tai
    public static Activity Current_Activity = new AppCompatActivity();
    public static void ShowToastLong(String msg) {
        if (Strings.isEmptyOrWhitespace(msg)) {
            msg = "Có lỗi xảy ra, vui lòng kểm tra kết nối internet";
        }
        try {
            Toast.makeText(KsmartSalesApplication.getInstance(), msg, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void ShowToastShort(String msg) {
        if (Strings.isEmptyOrWhitespace(msg)) {
            msg = "Có lỗi xảy ra, vui lòng kểm tra kết nối internet";
        }
        try {
            Toast.makeText(KsmartSalesApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   /* public static String GetNgayHienTai() {
        String Day;
        String ngayhientai;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            Day = "0" + String.valueOf(day);
        } else {
            Day = String.valueOf(day);
        }
        if (month + 1 < 10) {
            ngayhientai = Day + "/0" + String.valueOf(month + 1) + "/" + String.valueOf(year);
        } else {
            ngayhientai = Day + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year);
        }
        return ngayhientai;
    }*/
   SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    //////// bat dau phuong thuc format String to Date
    private static Date date = null;
   public static Date convertStringToDate3(String dateString) {

       DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");// dd-MM-yyyy HH:mm:ss =  11-02-2017 20:57:50
       try {
           date = df2.parse(dateString);//  dateString = "dd-MM-yyyy HH:mm:ss";
           /*formatteddate = df.format(date);*/
       } catch (Exception ex) {
           // System.out.println(ex);
           return null;
       }
       return date;
   }
    public static Date convertStringToDate2(String dateString) {

        DateFormat df2 = new SimpleDateFormat("yyyy-dd-MM'T'HH:mm:ss");// dd-MM-yyyy HH:mm:ss =  11-02-2017 20:57:50
        try {
            date = df2.parse(dateString);//  dateString = "dd-MM-yyyy HH:mm:ss";
            /*formatteddate = df.format(date);*/
        } catch (Exception ex) {
            // System.out.println(ex);
            return null;
        }
        return date;
    }
    public static String formatChangePointDateTime2(String datetime) {
       try {

           SimpleDateFormat simpleDate1 = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
           Date date = simpleDate1.parse(datetime);
           SimpleDateFormat simpleDate2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
           return simpleDate2.format(date);
       } catch (ParseException e) {
           //e.printStackTrace();
       }
       return null;
   }
   public static long getTime(String dateTime) {
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

       try {
           Date date = sdf.parse(dateTime);
           return date.getTime();

       } catch (ParseException e) {
           e.printStackTrace();
       }
       return 0;
   }
   public static String getThoiGianHienTai() {
       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
       return df.format(Calendar.getInstance().getTime());
   }
   public static String formatChangePointDateTime(Date datetime) {
       try {
           SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
           return simpleDate.format(datetime);
       } catch (Exception e) {
           //e.printStackTrace();
       }
       return datetime.toString();
   }
   public static String formatChangePointDateTime(String datetime) {

       try {
           String thoiGian = datetime.replace("-", "/")
                   .replace("'T'", " ").replace("T", " ").replace("  ", " ").trim();
           String[] formatStrings = {"dd/MM/yy HH:mm:ss", "dd/MM/yy HH:mm", "dd.MM/yyyy HH:mm:ss.SSS",
                   "dd.MM/yy HH:mm:ss.SSS",
                   "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy HH:mm", "yyyy/MM/dd HH:mm:ss",
                   "yyyy/MM/dd HH:mm", "yy/MM/dd HH:mm:ss", "yy/MM/dd HH:mm",
                   "yyyy/MM/dd'T'HH:mm:ss", "yyyy/MM/dd'T'HH:mm", "dd/MM/yyyy"};

           for (String fs : formatStrings) {
               SimpleDateFormat sdf1 = new SimpleDateFormat(fs);
               sdf1.setLenient(false);
               try {
                   Date d = sdf1.parse(thoiGian);
                   SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                   return simpleDate.format(d);
               } catch (ParseException e) {
                   //e.printStackTrace();
               }
           }


//            String thoigianbatdau = datetime.replace("-","/").trim();
//            String ngaybatdau = thoigianbatdau.substring(0, 10);
//            String giodau = thoigianbatdau.substring(11, 19);
//            return giodau + " " + ngaybatdau;
       } catch (Exception e) {
           e.printStackTrace();

       }
       return datetime;

   }
    public static String formatChangePointDateTimeHai(String datetime) {

        try {
            String thoiGian = datetime.replace("-", "/")
                    .replace("'T'", " ").replace("T", " ").replace("  ", " ").trim();
            String[] formatStrings = {"dd/MM/yy HH:mm:ss", "dd/MM/yy HH:mm", "dd.MM/yyyy HH:mm:ss.SSS",
                    "dd.MM/yy HH:mm:ss.SSS",
                    "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy HH:mm", "yyyy/MM/dd HH:mm:ss",
                    "yyyy/MM/dd HH:mm", "yy/MM/dd HH:mm:ss", "yy/MM/dd HH:mm",
                    "yyyy/MM/dd'T'HH:mm:ss", "yyyy/MM/dd'T'HH:mm", "dd/MM/yyyy"};

            for (String fs : formatStrings) {
                SimpleDateFormat sdf1 = new SimpleDateFormat(fs);
                sdf1.setLenient(false);
                try {
                    Date d = sdf1.parse(thoiGian);
                    SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
                    return simpleDate.format(d);
                } catch (ParseException e) {
                    //e.printStackTrace();
                }
            }


//            String thoigianbatdau = datetime.replace("-","/").trim();
//            String ngaybatdau = thoigianbatdau.substring(0, 10);
//            String giodau = thoigianbatdau.substring(11, 19);
//            return giodau + " " + ngaybatdau;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return datetime;

    }
   //SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
   public static String getCurentTimeHMS_DMY() {
       SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
       return df.format(Calendar.getInstance().getTime());
   }
   public static String getNgayHienTai() {
       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       return df.format(Calendar.getInstance().getTime());
   }
    public static String getNgayCachMotThang(){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-30);
        return  df.format(calendar.getTime());
    }

   public static String getNgayCachMotThangHai(){
       SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
       Calendar calendar = Calendar.getInstance();
       calendar.add(Calendar.DATE,-30);
       return  df.format(calendar.getTime());
   }
    public static String getNgayHienTaiHai() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(Calendar.getInstance().getTime());
    }
    //format date from dd/mm/yy 11:20:45 into dd-mm-yy 11:20:45
    public static String formatDateTimereplace(String datetime) {
        return datetime.replace("/", "-");
    }
    //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getCurentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(Calendar.getInstance().getTime());
    }
    public static String getToken() {
        String token = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(Calendar.getInstance().getTime());
        token = getMd5Hash("$lachong#") + getMd5Hash(date);
        return token;
    }
    public static String getMd5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            StringBuilder md5 = new StringBuilder(number.toString(16));

            while (md5.length() < 32)
                md5.insert(0, "0");

            return md5.toString();
        } catch (NoSuchAlgorithmException e) {

            return null;
        }
    }
    public static int checkGPS( ) {
        try {
            LocationManager service = (LocationManager) KsmartSalesApplication.getInstance().getSystemService(Context.LOCATION_SERVICE);
            if(service.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                return 1;
            }else {
                return 0;
            }
            //return service.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            //show toast
            Log.e("GPS", "" + ex.getMessage());
        }
        return 0;
    }

    public static int checkPowerSaveMode() {
        try {
            PowerManager powerManager = (PowerManager)
                    KsmartSalesApplication.getInstance().getSystemService(Context.POWER_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                    && powerManager.isPowerSaveMode()) {
                // Animations are disabled in power save mode, so just show a toast instead.
                // Toast.makeText(context, "Bật chế độ tiết kiệm pin", Toast.LENGTH_SHORT).show();
                Log.e("tietkiempin","1");
                return 1;
                //MD5.showLog("Bật chế độ tiết kiệm pin");
            } else {
                //MD5.showLog("Không Bật chế độ tiết kiệm pin");
                return 0;
            }
        } catch (Exception e) {
            // e.printStackTrace();
            return 0;
        }
    }

    // check trang thai fake GPS version >23
    private static List<String> getListAppFake() {
        List<String> listAppFakeGPS = new ArrayList<>();

        String arrayList = SharedPrefs.getInstance().get(Common.ListAppFake,String.class);
        try {
            JSONArray listAppface = new JSONArray(arrayList);
            for (int i = 0; i < listAppface.length(); i++) {
                String temp;
                JSONObject job = listAppface.getJSONObject(i);
                temp = job.getString("packetname");
                listAppFakeGPS.add(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return listAppFakeGPS;
    }
    /**
     * Check connect network
     *
     *
     * @return
     */
    public static Boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) KsmartSalesApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting()
                && cm.getActiveNetworkInfo().isAvailable();
    }
    public static void focusEditext(EditText editText, Context context) {
        try {
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.RESULT_UNCHANGED_SHOWN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void focusTextInputEditText(TextInputEditText editText) {
        try {
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) KsmartSalesApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.RESULT_UNCHANGED_SHOWN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String checkFakeGPS() {
        try {
            List<String> listAppProduct = getListAppFake();
            PackageManager pm = KsmartSalesApplication.getInstance().getPackageManager();
            List<ApplicationInfo> packages = null;
            try {
                packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
            } catch (Exception e) {
                //e.printStackTrace();
            }
            PackageInfo packageInfo;
            String[] requestedPermissions;
            String packetNameface = "";
            StringBuilder packetNameFakes = new StringBuilder();
            if (packages != null) {
                for (ApplicationInfo applicationInfo : packages) {
                    try {
                        //String currAppName = pm.getApplicationLabel(applicationInfo).toString();
                        String srcDir = applicationInfo.sourceDir;
                        if (srcDir.startsWith("/data/app/") && pm.getLaunchIntentForPackage(applicationInfo.packageName) != null) {
                            packageInfo = pm.getPackageInfo(applicationInfo.packageName,
                                    PackageManager.GET_PERMISSIONS);
                            // Get Permissions
                            requestedPermissions = packageInfo.requestedPermissions;
                            if (requestedPermissions != null) {
                                for (String requestedPermission : requestedPermissions) {
                                    if (requestedPermission
                                            .equals("android.permission.ACCESS_MOCK_LOCATION")
                                            && !applicationInfo.packageName.equals(KsmartSalesApplication.getInstance().getPackageName())) {
                                        packetNameface = applicationInfo.packageName;
                                        /*   if (Build.VERSION.SDK_INT >= 24) {*/
                                        Boolean fakeapp = true;
                                        try {
                                            for (int l = 0; l < listAppProduct.size(); l++) {
                                                if (packetNameface.equals(listAppProduct.get(l))) {
                                                    fakeapp = false;
                                                }
                                            }
                                        } catch (Exception e) {
                                            //e.printStackTrace();
                                        }
                                        if (fakeapp) {
                                            packetNameFakes.append(packetNameface).append(",");
                                        }

                                    }
                                }
                            }
                        }

                    } catch (PackageManager.NameNotFoundException e) {
                        // Log.e("Got exception ", e.getMessage());
                        return "";
                    }
                }
            }
            if (!packetNameFakes.toString().equals("")) {
                packetNameFakes = new StringBuilder(packetNameFakes.substring(0, packetNameFakes.length() - 1));
                //MD5.showLog("tungdaisFakeGPS11: "+packetNameFakes);
            }
            return packetNameFakes.toString();
        } catch (Exception e) {
            return "";
            //e.printStackTrace();
        }
    }
    public static String getDiaChi(double lat, double lon) {
        String strAdd = "";
        Context c=KsmartSalesApplication.getInstance();
        try {
            Geocoder geocoder = new Geocoder(c, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);
            if (addresses != null) {

                Address returnedAddress = addresses.get(0);
                strAdd = returnedAddress.getAddressLine(0);
                if (strAdd.equals("")) {
                    strAdd = c.getResources().getString(R.string.message_unknow);
                }
            } else {
                strAdd = c.getResources().getString(R.string.message_unknow);
            }
        } catch (Exception e) {
            strAdd = c.getResources().getString(R.string.message_unknow);
        }

        return strAdd;
    }
    public static void deleteFileFromMediaStore(final ContentResolver contentResolver, final File file) {
        String canonicalPath;
        try {
            canonicalPath = file.getCanonicalPath();
        } catch (IOException e) {
            canonicalPath = file.getAbsolutePath();
        }
        Uri uri = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            uri = MediaStore.Files.getContentUri("external");
        }
        try {
            final int result = contentResolver.delete(uri, MediaStore.Files.FileColumns.DATA + "=?", new String[]{canonicalPath});
            if (result == 0) {
                final String absolutePath = file.getAbsolutePath();
                if (!absolutePath.equals(canonicalPath)) {
                    contentResolver.delete(uri, MediaStore.Files.FileColumns.DATA + "=?", new String[]{absolutePath});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Endcode thủ công bằng cách replace
    public static String EncodeHttpPost(String url) {
        url = url.replace("%", "%25");
        url = url.replace(" ", "%20");
        url = url.replace("#", "%23");
        url = url.replace("\\", "%5C");
        url = url.replace("\"", "%22");
        url = url.replace("<", "%3C");
        url = url.replace(">", "%3E");
        url = url.replace("{", "%7B");
        url = url.replace("}", "%7D");
        url = url.replace("|", "%7C");
        url = url.replace("^", "%5E");
        return url;
    }

}
