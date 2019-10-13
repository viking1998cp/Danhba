package vn.lachongmedia.appnv.object;

import java.util.ArrayList;

/**
 * Created by Hoangdh on 12/07/2016.
 */
public class Check {
    public static boolean isOnline = false;
    public static boolean isUpload = false;
    public static boolean isLogin = false;
    public static boolean chechLogOut=false;
    public static boolean isRunning = false;
    public static int thongbaophienbanmoi = 0;
    public static String newMessage = "";
    public static int thamsovaodiem = 500;
 /*   public static int thoigianCheckKetnoi = 60000;
    public static int netspeed = 64 * 1024;
    public static int taskNum = 0;
    public static int testKill = 3;*/
    public static int ExitState = 1;
    public static boolean check_status_sendimage = false;

   // public static boolean check_status_sendimagekhachhang = false;
    public static boolean dungthemdaucongchupanh = false;// luu trang thai chup anh khi gui anh dang khong cho chup ==false
    public static ArrayList<ImageAlbumObject> listImage = new ArrayList<>();
    public static int formatNumber = 1;

}

