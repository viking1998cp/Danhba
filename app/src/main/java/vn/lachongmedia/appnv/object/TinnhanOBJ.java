package vn.lachongmedia.appnv.object;

import vn.lachongmedia.appnv.Common;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by hoangdh on 20/06/2016.
 * edit tungda
 */
public class TinnhanOBJ implements Serializable {
    @SerializedName("ID_TINNHAN")
    private int id_tinnhan;

    private int id_tinnhan_db;

    public int getId_tinnhan_db() {
        return id_tinnhan_db;
    }

    public void setId_tinnhan_db(int id_tinnhan_db) {
        this.id_tinnhan_db = id_tinnhan_db;
    }
    @SerializedName("ID_NGUOIGUI")
    private int id_nguoigui;
    private int id_nhom;
    @SerializedName("NgayGui")
    private String ngaygui;
    public int getId_nhom() {
        return id_nhom;
    }

    public void setId_nhom(int id_nhom) {
        this.id_nhom = id_nhom;
    }

    private String nguoigui;
    @SerializedName("NoiDung")
    private String noidung;
    @SerializedName("Ten_NGUOIGUI")
    private String tennguoigui;
    private String thoigianluu;
    @SerializedName("TrangThai")
    private int trangthai;
    private String tieuDe;

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getThoigianluu() {
        return thoigianluu;
    }

    public void setThoigianluu(String thoigianluu) {
        this.thoigianluu = thoigianluu;
    }

    private int iDnhanvien;
    public int getiDnhanvien() {
        return iDnhanvien;
    }

    public void setiDnhanvien(int iDnhanvien) {
        this.iDnhanvien = iDnhanvien;
    }

    public int getiDQuanLy() {
        return iDQuanLy;
    }

    public void setiDQuanLy(int iDQuanLy) {
        this.iDQuanLy = iDQuanLy;
    }

    private int iDQuanLy;
    @SerializedName("TypeSend")
    private int loaitinnhan; // 0 = gui tu server xuong 1= gui len
    private int  type; //type =0 tin nhan luu type =1 tin nhan đã gửi;
    private ArrayList<NhanVien> list_nhanvien;
    private ArrayList<NhanVien> list_quanly;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId_nguoigui(int id_nguoigui) {
        this.id_nguoigui = id_nguoigui;
    }

    public void setList_nhanvien(ArrayList<NhanVien> list_nhanvien) {
        this.list_nhanvien = list_nhanvien;
    }

    public void setList_quanly(ArrayList<NhanVien> list_quanly) {
        this.list_quanly = list_quanly;
    }

    public String getTennguoigui() {
        return tennguoigui;
    }

    public void setTennguoigui(String tennguoigui) {
        this.tennguoigui = tennguoigui;
    }

    public int getLoaitinnhan() {
        return loaitinnhan;
    }

    public void setLoaitinnhan(int loaitinnhan) {
        this.loaitinnhan = loaitinnhan;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public TinnhanOBJ() {
    }

    public int getId_tinnhan() {
        return id_tinnhan;
    }

    public void setId_tinnhan(int id_tinnhan) {
        this.id_tinnhan = id_tinnhan;
    }

    public String getNgaygui() {
        return ngaygui;
    }

    public void setNgaygui(String ngaygui) {
        this.ngaygui = ngaygui;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
    public long getThoiGian(){
        try {
            if(type==1){
                return Common.getTime(Common.formatChangePointDateTime(ngaygui));
            }else{
                return Common.getTime(Common.formatChangePointDateTime(thoigianluu));
            }
        } catch (Exception e) {
            return 0;
        }

    }
}
