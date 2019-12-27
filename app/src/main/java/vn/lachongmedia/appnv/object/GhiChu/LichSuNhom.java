package vn.lachongmedia.appnv.object.GhiChu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import vn.lachongmedia.appnv.object.PhanHoi.DanhSachPhanHoi;

public class LichSuNhom {
    @SerializedName("ID_CheckListLichSu_Nhom")
    @Expose
    private int iDCheckListLichSuNhom;
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
    @SerializedName("danhsachchecklist")
    @Expose
    private ArrayList<DanhSachGhiChu> danhsachghichu = null;

    public int getIDCheckListLichSuNhom() {
        return iDCheckListLichSuNhom;
    }

    public void setIDCheckListLichSuNhom(int iDCheckListLichSuNhom) {
        this.iDCheckListLichSuNhom = iDCheckListLichSuNhom;
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

    public ArrayList<DanhSachGhiChu> getDanhsachghichu() {
        return danhsachghichu;
    }

    public void setDanhsachghichu(ArrayList<DanhSachGhiChu> danhsachghichu) {
        this.danhsachghichu = danhsachghichu;
    }
}
