package vn.lachongmedia.appnv.object.Khachhang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("KinhDo")
    @Expose
    private double kinhDo;
    @SerializedName("ViDo")
    @Expose
    private double viDo;
    @SerializedName("TenCuaHang")
    @Expose
    private String tenCuaHang;
    @SerializedName("MaCuaHang")
    @Expose
    private String maCuaHang;
    @SerializedName("DiaChi")
    @Expose
    private String diaChi;
    @SerializedName("DienThoai")
    @Expose
    private String dienThoai;
    @SerializedName("idcuahang")
    @Expose
    private int idcuahang;
    @SerializedName("idloaikhachhang")
    @Expose
    private int idloaikhachhang;
    @SerializedName("idnhomkhachhang")
    @Expose
    private int idnhomkhachhang;
    @SerializedName("idcha")
    @Expose
    private int idcha;
    @SerializedName("tennhomkhachhang")
    @Expose
    private String tennhomkhachhang;
    @SerializedName("RowNum")
    @Expose
    private int rowNum;
    @SerializedName("ngaytao")
    @Expose
    private String ngaytao;
    @SerializedName("trangthai")
    @Expose
    private int trangthai;
    @SerializedName("idphanloaichucnang")
    @Expose
    private int idphanloaichucnang;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("MaSoThue")
    @Expose
    private String maSoThue;
    @SerializedName("DiaChiXuatHoaDon")
    @Expose
    private String diaChiXuatHoaDon;

    public double getKinhDo() {
        return kinhDo;
    }

    public void setKinhDo(double kinhDo) {
        this.kinhDo = kinhDo;
    }

    public double getViDo() {
        return viDo;
    }

    public void setViDo(double viDo) {
        this.viDo = viDo;
    }

    public String getTenCuaHang() {
        return tenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        this.tenCuaHang = tenCuaHang;
    }

    public String getMaCuaHang() {
        return maCuaHang;
    }

    public void setMaCuaHang(String maCuaHang) {
        this.maCuaHang = maCuaHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public int getIdcuahang() {
        return idcuahang;
    }

    public void setIdcuahang(int idcuahang) {
        this.idcuahang = idcuahang;
    }

    public int getIdloaikhachhang() {
        return idloaikhachhang;
    }

    public void setIdloaikhachhang(int idloaikhachhang) {
        this.idloaikhachhang = idloaikhachhang;
    }

    public int getIdnhomkhachhang() {
        return idnhomkhachhang;
    }

    public void setIdnhomkhachhang(int idnhomkhachhang) {
        this.idnhomkhachhang = idnhomkhachhang;
    }

    public int getIdcha() {
        return idcha;
    }

    public void setIdcha(int idcha) {
        this.idcha = idcha;
    }

    public String getTennhomkhachhang() {
        return tennhomkhachhang;
    }

    public void setTennhomkhachhang(String tennhomkhachhang) {
        this.tennhomkhachhang = tennhomkhachhang;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getIdphanloaichucnang() {
        return idphanloaichucnang;
    }

    public void setIdphanloaichucnang(int idphanloaichucnang) {
        this.idphanloaichucnang = idphanloaichucnang;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public String getDiaChiXuatHoaDon() {
        return diaChiXuatHoaDon;
    }

    public void setDiaChiXuatHoaDon(String diaChiXuatHoaDon) {
        this.diaChiXuatHoaDon = diaChiXuatHoaDon;
    }
}
