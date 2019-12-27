package vn.lachongmedia.appnv.object.GhiChu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GhiChu {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private Object data;
    @SerializedName("lichsu")
    @Expose
    private Object lichsu;
    @SerializedName("lichsunhom")
    @Expose
    private ArrayList<LichSuNhom> lichsunhom = null;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getLichsu() {
        return lichsu;
    }

    public void setLichsu(Object lichsu) {
        this.lichsu = lichsu;
    }

    public ArrayList<LichSuNhom> getLichsunhom() {
        return lichsunhom;
    }

    public void setLichsunhom(ArrayList<LichSuNhom> lichsunhom) {
        this.lichsunhom = lichsunhom;
    }
}
