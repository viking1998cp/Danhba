package vn.lachongmedia.appnv.object.PhanHoi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LichSuNhom {
    @SerializedName("ID_PhanHoi_LichSu_Nhom")
    @Expose
    private int iDPhanHoiLichSuNhom;
    @SerializedName("ID_NhanVien")
    @Expose
    private int iDNhanVien;
    @SerializedName("ID_KhachHang")
    @Expose
    private int iDKhachHang;
    @SerializedName("idcheckin")
    @Expose
    private int idcheckin;
    @SerializedName("ThoiGian")
    @Expose
    private String thoiGian;
    @SerializedName("TenKhachHang")
    @Expose
    private String tenKhachHang;
    @SerializedName("danhsachphanhoi")
    @Expose
    private ArrayList<DanhSachPhanHoi> danhsachphanhoi = null;

    public int getIDPhanHoiLichSuNhom() {
        return iDPhanHoiLichSuNhom;
    }

    public void setIDPhanHoiLichSuNhom(int iDPhanHoiLichSuNhom) {
        this.iDPhanHoiLichSuNhom = iDPhanHoiLichSuNhom;
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

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public ArrayList<DanhSachPhanHoi> getDanhsachphanhoi() {
        return danhsachphanhoi;
    }

    public void setDanhsachphanhoi(ArrayList<DanhSachPhanHoi> danhsachphanhoi) {
        this.danhsachphanhoi = danhsachphanhoi;
    }
}
