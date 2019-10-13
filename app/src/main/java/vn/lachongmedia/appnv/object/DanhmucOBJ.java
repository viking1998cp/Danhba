package vn.lachongmedia.appnv.object;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tungda on 27/07/2019.
 */
public class DanhmucOBJ implements Serializable {
    @SerializedName("ID_QLLH")
    private int iDQLLH;
    @SerializedName("TrangThai")
    private int trangthai;
    @SerializedName("TenDanhMuc")
    private String tenDanhMuc;
    @SerializedName("NgayTao")
    private String ngayTao;
    @SerializedName("ID_PARENT")
    private int iDDanhMucCha;
    @SerializedName("ID_DANHMUC")
    private int iDDanhMuc;
    @SerializedName("SoLuongDanhMucCon")
    private int soLuongDanhMucCon;
    @SerializedName("SoLuongMatHang")
    private int soLuongMatHang;

    public int getiDQLLH() {
        return iDQLLH;
    }

    public void setiDQLLH(int iDQLLH) {
        this.iDQLLH = iDQLLH;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getiDDanhMucCha() {
        return iDDanhMucCha;
    }

    public void setiDDanhMucCha(int iDDanhMucCha) {
        this.iDDanhMucCha = iDDanhMucCha;
    }

    public int getiDDanhMuc() {
        return iDDanhMuc;
    }

    public void setiDDanhMuc(int iDDanhMuc) {
        this.iDDanhMuc = iDDanhMuc;
    }

    public int getSoLuongDanhMucCon() {
        return soLuongDanhMucCon;
    }

    public void setSoLuongDanhMucCon(int soLuongDanhMucCon) {
        this.soLuongDanhMucCon = soLuongDanhMucCon;
    }

    public int getSoLuongMatHang() {
        return soLuongMatHang;
    }

    public void setSoLuongMatHang(int soLuongMatHang) {
        this.soLuongMatHang = soLuongMatHang;
    }
}