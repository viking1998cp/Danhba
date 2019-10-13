package vn.lachongmedia.appnv.object;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tungda on 7/19/2019.
 */
public class LichHenObject implements Serializable {
    @SerializedName("ID_QLLH")
    private int iD_QLLH;
    @SerializedName("iD_NhanVien")
    private int iD_NhanVien;
    @SerializedName("ID_KhachHang")
    private int iD_khachHang;
    @SerializedName("ID_LichHen")
    private int iD_LichHen;
    @SerializedName("TrangThai")
    private int trangthai;
    @SerializedName("ThoiGian_HienThi")
    private String thoiGianHienThi;
    private  String Diachi;
    @SerializedName("TenKhachHang")
    private String tenKhachHang;
    @SerializedName("NoiDung")
    private String noiDungHen;
    @SerializedName("ThoiGian")
    private String thoigian;

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    private String tenNhanVien;


    public String getKetQua() {
        return ketQua;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }

    @SerializedName("KetQua")
    private String ketQua;

    public void setiD_QLLH(int iD_QLLH) {
        this.iD_QLLH = iD_QLLH;
    }

    public void setiD_NhanVien(int iD_NhanVien) {
        this.iD_NhanVien = iD_NhanVien;
    }

    public int getiD_khachHang() {
        return iD_khachHang;
    }

    public void setiD_khachHang(int iD_khachHang) {
        this.iD_khachHang = iD_khachHang;
    }

    public int getiD_LichHen() {
        return iD_LichHen;
    }

    public void setiD_LichHen(int iD_LichHen) {
        this.iD_LichHen = iD_LichHen;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getThoiGianHienThi() {
        return thoiGianHienThi;
    }

    public void setThoiGianHienThi(String thoiGianHienThi) {
        this.thoiGianHienThi = thoiGianHienThi;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getNoiDungHen() {
        return noiDungHen;
    }

    public void setNoiDungHen(String noiDungHen) {
        this.noiDungHen = noiDungHen;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }
}
