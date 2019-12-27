package vn.lachongmedia.appnv.object.Khachhang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataLoaiKhachHang {
    @SerializedName("ID_LoaiKhachHang")
    @Expose
    private int iDLoaiKhachHang;
    @SerializedName("ID_QLLH")
    @Expose
    private int iDQLLH;
    @SerializedName("TenLoaiKhachHang")
    @Expose
    private String tenLoaiKhachHang;
    @SerializedName("NgayTao")
    @Expose
    private String ngayTao;
    @SerializedName("TongKhachHang")
    @Expose
    private int tongKhachHang;
    @SerializedName("MaMau")
    @Expose
    private String maMau;

    public int getIDLoaiKhachHang() {
        return iDLoaiKhachHang;
    }

    public void setIDLoaiKhachHang(int iDLoaiKhachHang) {
        this.iDLoaiKhachHang = iDLoaiKhachHang;
    }

    public int getIDQLLH() {
        return iDQLLH;
    }

    public void setIDQLLH(int iDQLLH) {
        this.iDQLLH = iDQLLH;
    }

    public String getTenLoaiKhachHang() {
        return tenLoaiKhachHang;
    }

    public void setTenLoaiKhachHang(String tenLoaiKhachHang) {
        this.tenLoaiKhachHang = tenLoaiKhachHang;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTongKhachHang() {
        return tongKhachHang;
    }

    public void setTongKhachHang(int tongKhachHang) {
        this.tongKhachHang = tongKhachHang;
    }

    public String getMaMau() {
        return maMau;
    }

    public void setMaMau(String maMau) {
        this.maMau = maMau;
    }
}
