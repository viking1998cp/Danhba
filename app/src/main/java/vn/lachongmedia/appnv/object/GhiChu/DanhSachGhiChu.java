package vn.lachongmedia.appnv.object.GhiChu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DanhSachGhiChu {
    @SerializedName("ID_CheckList")
    @Expose
    private int iDCheckList;
    @SerializedName("ID_QLLH")
    @Expose
    private int iDQLLH;
    @SerializedName("ID_NhanVien")
    @Expose
    private int iDNhanVien;
    @SerializedName("ID_KhachHang")
    @Expose
    private int iDKhachHang;
    @SerializedName("ChiTiet")
    @Expose
    private String chiTiet;
    @SerializedName("idcheckin")
    @Expose
    private int idcheckin;
    @SerializedName("ThoiGian")
    @Expose
    private String thoiGian;
    @SerializedName("TenCheckList")
    @Expose
    private String tenCheckList;
    @SerializedName("TenKhachHang")
    @Expose
    private String tenKhachHang;
    @SerializedName("TrangThai")
    @Expose
    private int trangThai;
    @SerializedName("ID_CheckListLichSu_Nhom")
    @Expose
    private int iDCheckListLichSuNhom;

    public int getIDCheckList() {
        return iDCheckList;
    }

    public void setIDCheckList(int iDCheckList) {
        this.iDCheckList = iDCheckList;
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

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
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

    public String getTenCheckList() {
        return tenCheckList;
    }

    public void setTenCheckList(String tenCheckList) {
        this.tenCheckList = tenCheckList;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getIDCheckListLichSuNhom() {
        return iDCheckListLichSuNhom;
    }

    public void setIDCheckListLichSuNhom(int iDCheckListLichSuNhom) {
        this.iDCheckListLichSuNhom = iDCheckListLichSuNhom;
    }
}
