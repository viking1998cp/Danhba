package vn.lachongmedia.appnv.network.Login;

/**
 * Created by tungda on 7/16/2019.
 */
public class TrangThaiHoanTatDonHang {
   private int ID_TrangThaiHoanTatDonHang;//0,

    public int getID_TrangThaiHoanTatDonHang() {
        return ID_TrangThaiHoanTatDonHang;
    }

    public void setID_TrangThaiHoanTatDonHang(int ID_TrangThaiHoanTatDonHang) {
        this.ID_TrangThaiHoanTatDonHang = ID_TrangThaiHoanTatDonHang;
    }

    public String getTenTrangThai() {
        return TenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        TenTrangThai = tenTrangThai;
    }

    private String      TenTrangThai;//Chưa xử lý
}
