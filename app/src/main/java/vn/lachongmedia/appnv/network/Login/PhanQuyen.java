package vn.lachongmedia.appnv.network.Login;

/**
 * Created by tungda on 7/16/2019.
 */
public class PhanQuyen {
    private int ID_Nhom;//0,
    private int ID_ChucNang;//36,
    private String URL;//null,

    public int getID_Nhom() {
        return ID_Nhom;
    }

    public void setID_Nhom(int ID_Nhom) {
        this.ID_Nhom = ID_Nhom;
    }

    public int getID_ChucNang() {
        return ID_ChucNang;
    }

    public void setID_ChucNang(int ID_ChucNang) {
        this.ID_ChucNang = ID_ChucNang;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getMaChucNang() {
        return MaChucNang;
    }

    public void setMaChucNang(String maChucNang) {
        MaChucNang = maChucNang;
    }

    public String getTenChucNang() {
        return TenChucNang;
    }

    public void setTenChucNang(String tenChucNang) {
        TenChucNang = tenChucNang;
    }

    private String MaChucNang;//APP_001,
    private String TenChucNang;//Trang chủ,
    //   InsertedTime;//0001-01-01T00;//00;//00
}
