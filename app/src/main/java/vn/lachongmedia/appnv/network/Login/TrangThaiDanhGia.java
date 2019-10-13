package vn.lachongmedia.appnv.network.Login;

/**
 * Created by tungda on 7/16/2019.
 */
public class TrangThaiDanhGia {
    private int ID_TrangThai_DanhGia;//1,
    private int ID_QLLH;//0,
    private String TenTrangThai;//Tốt,
    private String DuongDanhAnh;///DATA_UPLOADS/TrangThaiDanhGiaCheckList/smile.png

    public int getID_TrangThai_DanhGia() {
        return ID_TrangThai_DanhGia;
    }

    public void setID_TrangThai_DanhGia(int ID_TrangThai_DanhGia) {
        this.ID_TrangThai_DanhGia = ID_TrangThai_DanhGia;
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

    public String getDuongDanhAnh() {
        return DuongDanhAnh;
    }

    public void setDuongDanhAnh(String duongDanhAnh) {
        DuongDanhAnh = duongDanhAnh;
    }
}
