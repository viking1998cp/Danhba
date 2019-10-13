package vn.lachongmedia.appnv.object;

import java.io.Serializable;

/**
 * Created by Hoangdh on 30/08/2016.
 */
public class NhanVien implements Serializable{
    private int id;
    private String ten;
    private int loai ; // loai = 1 la  nhan vien loai bang 2 la quan ly
    private boolean isChoose;


    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose() {
        isChoose = true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }
}
