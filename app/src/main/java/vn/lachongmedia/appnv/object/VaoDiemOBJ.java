package vn.lachongmedia.appnv.object;

import java.util.Date;

/**
 * Created by boykatty on 11/14/2015.
 */
public class VaoDiemOBJ {
    private String tenkhachhang;
    private String diachi;
    private String ghichu;

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    private int idkhachhang, idcheckin;
    private double lat, lng;
    private boolean status;
    private String thoigianvaodiem;
    private Date thoiGianVaoDiemKieuDate;
    private  String makhachhang;//tungda
    //Sử dung cho kế hoạch của khách hàng khu vực
    private int idKeHoach;
    private String soDienThoai;

    public int getIdKeHoach() {
        return idKeHoach;
    }

    public void setIdKeHoach(int idKeHoach) {
        this.idKeHoach = idKeHoach;
    }

    public String getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Date getThoiGianVaoDiemKieuDate(){
        return thoiGianVaoDiemKieuDate;
    }

    public void setThoiGianVaoDiemKieuDate(Date thoiGian){
        thoiGianVaoDiemKieuDate = thoiGian;
    }

    public String getThoigianvaodiem() {
        return thoigianvaodiem;
    }

    public void setThoigianvaodiem(String thoigianvaodiem) {
        this.thoigianvaodiem = thoigianvaodiem;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setIdkhachhang(int idkhachhang) {
        this.idkhachhang = idkhachhang;
    }

    public int getIdkhachhang() {
        return idkhachhang;
    }

    public void setIdcheckin(int idcheckin) {
        this.idcheckin = idcheckin;
    }

    public int getIdcheckin() {
        return idcheckin;
    }



}
