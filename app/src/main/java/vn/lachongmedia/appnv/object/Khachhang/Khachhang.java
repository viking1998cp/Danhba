package vn.lachongmedia.appnv.object.Khachhang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Khachhang {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ArrayList<Data> data = null;
    @SerializedName("dataLoaiKhachHang")
    @Expose
    private ArrayList<DataLoaiKhachHang> dataLoaiKhachHang = null;
    @SerializedName("tongsoitem")
    @Expose
    private int tongsoitem;
    @SerializedName("soluongitemtrentrang")
    @Expose
    private int soluongitemtrentrang;
    @SerializedName("endlist")
    @Expose
    private int endlist;
    @SerializedName("lastid")
    @Expose
    private int lastid;
    @SerializedName("dsidkhachhang")
    @Expose
    private ArrayList<Integer> dsidkhachhang = null;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public ArrayList<DataLoaiKhachHang> getDataLoaiKhachHang() {
        return dataLoaiKhachHang;
    }

    public void setDataLoaiKhachHang(ArrayList<DataLoaiKhachHang> dataLoaiKhachHang) {
        this.dataLoaiKhachHang = dataLoaiKhachHang;
    }

    public int getTongsoitem() {
        return tongsoitem;
    }

    public void setTongsoitem(int tongsoitem) {
        this.tongsoitem = tongsoitem;
    }

    public int getSoluongitemtrentrang() {
        return soluongitemtrentrang;
    }

    public void setSoluongitemtrentrang(int soluongitemtrentrang) {
        this.soluongitemtrentrang = soluongitemtrentrang;
    }

    public int getEndlist() {
        return endlist;
    }

    public void setEndlist(int endlist) {
        this.endlist = endlist;
    }

    public int getLastid() {
        return lastid;
    }

    public void setLastid(int lastid) {
        this.lastid = lastid;
    }

    public ArrayList<Integer> getDsidkhachhang() {
        return dsidkhachhang;
    }

    public void setDsidkhachhang(ArrayList<Integer> dsidkhachhang) {
        this.dsidkhachhang = dsidkhachhang;
    }
}
