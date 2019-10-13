package vn.lachongmedia.appnv.object;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tungda on 8/8/2017.
 */

public class TinNhanGroup implements Serializable {
    @SerializedName("ID_QLLH")
    private int iD_QLLH;
    @SerializedName("ID_NHANVIEN")
    private int iD_NhanVien;
    @SerializedName("ID_QUANLY")
    private int iD_QuanLy;
    @SerializedName("TenQuanLy")
    private String tenQuanLy;
    @SerializedName("TenNhanVien")
    private String tenNhanVien;
    @SerializedName("NoiDung")
    private String noidung;//nội dung tin nhắn cuối cùng
    @SerializedName("ThoiGian")
    private String thoigiantao;//thời gian tạo tin nhắn cuối cùng

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getThoigiantao() {
        return thoigiantao;
    }

    public void setThoigiantao(String thoigiantao) {
        this.thoigiantao = thoigiantao;
    }

    @SerializedName("DANHSACH")
    private ArrayList<TinnhanOBJ> listTinNhanGroup;

    public ArrayList<TinnhanOBJ> getListTinNhanGroup() {
        return listTinNhanGroup;
    }

    public void setListTinNhanGroup(ArrayList<TinnhanOBJ> listTinNhanGroup) {
        this.listTinNhanGroup = listTinNhanGroup;
    }

    public void setiD_QLLH(int iD_QLLH) {
        this.iD_QLLH = iD_QLLH;
    }

    public void setiD_NhanVien(int iD_NhanVien) {
        this.iD_NhanVien = iD_NhanVien;
    }

    public int getiD_QuanLy() {
        return iD_QuanLy;
    }

    public void setiD_QuanLy(int iD_QuanLy) {
        this.iD_QuanLy = iD_QuanLy;
    }

    public String getTenQuanLy() {
        return tenQuanLy;
    }

    public void setTenQuanLy(String tenQuanLy) {
        this.tenQuanLy = tenQuanLy;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

}
