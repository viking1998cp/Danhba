package vn.lachongmedia.appnv.object;

/**
 * Created by tungda on 7/21/2019.
 */
public class flagobj {
    // 0 chua gui chua luu
    // 1 da gui
    // 2 chua gui da luu

    public static int KDN = 0; //1:co gps, 0:khong co gps
    public static int KHDC = 0; //1: co ke hoach, 0: khong co ke hoach
    public static int flag;// 0, 2: cho sua, 1: khong cho sua

    public static long iddonhang;
    public static long idcuahang;
    public static VaoDiemOBJ VDOBJ = new VaoDiemOBJ();
}
