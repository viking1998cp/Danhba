package vn.lachongmedia.appnv.network.Login;

/**
 * Created by tungda on 7/16/2019.
 */
public class TrangThaiDonHang {
    private int ID_TrangThaiDonHang;//1,
    private int ID_QLLH;//1,
    private String TenTrangThai;//Khách hàng đã chốt đơn,
    private String MauTrangThai;//0b2a0b

    public int getID_TrangThaiDonHang() {
        return ID_TrangThaiDonHang;
    }

    public void setID_TrangThaiDonHang(int ID_TrangThaiDonHang) {
        this.ID_TrangThaiDonHang = ID_TrangThaiDonHang;
    }

    public int getID_QLLH() {
        return ID_QLLH;
    }

    public void setID_QLLH(int ID_QLLH) {
        this.ID_QLLH = ID_QLLH;
    }

    public String getTenTrangThai() {
        return TenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        TenTrangThai = tenTrangThai;
    }

    public String getMauTrangThai() {
        return MauTrangThai;
    }

    public void setMauTrangThai(String mauTrangThai) {
        MauTrangThai = mauTrangThai;
    }
}
