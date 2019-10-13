package vn.lachongmedia.appnv.object.PhanHoi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DanhSachPhanHoi {
    @SerializedName("ID_PhanHoi")
    @Expose
    private int iDPhanHoi;
    @SerializedName("ID_QLLH")
    @Expose
    private int iDQLLH;
    @SerializedName("ID_NhanVien")
    @Expose
    private int iDNhanVien;
    @SerializedName("ID_KhachHang")
    @Expose
    private int iDKhachHang;
    @SerializedName("idcheckin")
    @Expose
    private int idcheckin;
    @SerializedName("ChiTiet")
    @Expose
    private String chiTiet;
    @SerializedName("ThoiGian")
    @Expose
    private String thoiGian;
    @SerializedName("TenPhanHoi")
    @Expose
    private String tenPhanHoi;
    @SerializedName("TenKhachHang")
    @Expose
    private String tenKhachHang;
    @SerializedName("ID_PhanHoi_LichSu_Nhom")
    @Expose
    private int iDPhanHoiLichSuNhom;

    public int getIDPhanHoi() {
        return iDPhanHoi;
    }

    public void setIDPhanHoi(int iDPhanHoi) {
        this.iDPhanHoi = iDPhanHoi;
    }

    public int getIDQLLH() {
        return iDQLLH;
    }

    public void setIDQLLH(int iDQLLH) {
        this.iDQLLH = iDQLLH;
    }

    public int getIDNhanVien() {
        return iDNhanVien;
    }

    public void setIDNhanVien(int iDNhanVien) {
        this.iDNhanVien = iDNhanVien;
    }

    public int getIDKhachHang() {
        return iDKhachHang;
    }

    public void setIDKhachHang(int iDKhachHang) {
        this.iDKhachHang = iDKhachHang;
    }

    public int getIdcheckin() {
        return idcheckin;
    }

    public void setIdcheckin(int idcheckin) {
        this.idcheckin = idcheckin;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getTenPhanHoi() {
        return tenPhanHoi;
    }

    public void setTenPhanHoi(String tenPhanHoi) {
        this.tenPhanHoi = tenPhanHoi;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getIDPhanHoiLichSuNhom() {
        return iDPhanHoiLichSuNhom;
    }

    public void setIDPhanHoiLichSuNhom(int iDPhanHoiLichSuNhom) {
        this.iDPhanHoiLichSuNhom = iDPhanHoiLichSuNhom;
    }
}
