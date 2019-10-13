package vn.lachongmedia.appnv.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tungda on 7/18/2019.
 */
public class KeHoachOBJ {

    @SerializedName("KinhDo")
    private double kinhdo;
    @SerializedName("ViDo")
    private double vido;
    @SerializedName("TrangThai")
    private int trangthai;
    @SerializedName("IDKeHoach")
    private int idkehoach;
    @SerializedName("IDCuaHang")
    private int idcuahang;
    @SerializedName("IDNhanVien")
    private int idnhanvien;
    @SerializedName("STT")
    private int stt;
    @SerializedName("TenCuaHang")
    private String tencuahang;
    @SerializedName("TenNhanVien")
    private String tennhanvien;
    @SerializedName("ThoiGianVaoDiemThucTe")
    private String thoigianvaodiem;
    @SerializedName("ThoiGianRaDiemThucTe")
    private String thoigianradiem;
    @SerializedName("GhiChu")
    private String ghichu;
    @SerializedName("ThoiGianVaoDiemDuKien")
    private String thoigianvaodiemdukien;
    @SerializedName("ThoiGianRaDiemDuKien")
    private String thoigianradiemdukien;//tungda
    @SerializedName("MaKH")
    private String maKhachHang;
    @SerializedName("ViecCanLam")
    private String vieccanlam;

    private String tenTrangThai;
    private String diaChi;

    @SerializedName("text_color")
    private String maMau;
    @SerializedName("text_color_mota")
    private String moTakehoach;
    private String maDonhang;

    private int loaiKeHoach;

    public String getTenTrangThai() {
        return tenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getLoaiKeHoach() {
        return loaiKeHoach;
    }

    public void setLoaiKeHoach(int loaiKeHoach) {
        this.loaiKeHoach = loaiKeHoach;
    }
    //loaiKeHoach=0, kế hoạch khách hàng
    //loaiKeHoach=1, kế hoạch khu vực

    public String getVieccanlam() {
        return vieccanlam;
    }

    public void setVieccanlam(String vieccanlam) {
        this.vieccanlam = vieccanlam;
    }



    public String getMaDonhang() {
        return maDonhang;
    }

    public void setMaDonhang(String maDonhang) {
        this.maDonhang = maDonhang;
    }



    public String getMaMau() {
        return maMau;
    }

    public void setMaMau(String maMau) {
        this.maMau = maMau;
    }

    public String getMoTakehoach() {
        return moTakehoach;
    }

    public void setMoTakehoach(String moTakehoach) {
        this.moTakehoach = moTakehoach;
    }

    public String getThoigianvaodiemdukien() {
        return thoigianvaodiemdukien;
    }

    public void setThoigianvaodiemdukien(String thoigianvaodiemdukien) {
        this.thoigianvaodiemdukien = thoigianvaodiemdukien;
    }

    public String getThoigianradiemdukien() {
        return thoigianradiemdukien;
    }

    public void setThoigianradiemdukien(String thoigianradiemdukien) {
        this.thoigianradiemdukien = thoigianradiemdukien;
    }

    public void setIdkehoach(int idkehoach) {
        this.idkehoach = idkehoach;
    }

    public int getIdkehoach() {
        return idkehoach;
    }

    public void setIdnhanvien(int idnhanvien) {
        this.idnhanvien = idnhanvien;
    }

    public double getKinhdo() {
        return kinhdo;
    }

    public void setKinhdo(double kinhdo) {
        this.kinhdo = kinhdo;
    }

    public double getVido() {
        return vido;
    }

    public void setVido(double vido) {
        this.vido = vido;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getIdcuahang() {
        return idcuahang;
    }

    public void setIdcuahang(int idcuahang) {
        this.idcuahang = idcuahang;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTencuahang() {
        return tencuahang;
    }

    public void setTencuahang(String tencuahang) {
        this.tencuahang = tencuahang;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public String getThoigianvaodiem() {
        return thoigianvaodiem;
    }

    public void setThoigianvaodiem(String thoigianvaodiem) {
        this.thoigianvaodiem = thoigianvaodiem;
    }

    public String getThoigianradiem() {
        return thoigianradiem;
    }


    public void setThoigianradiem(String thoigianradiem) {
        this.thoigianradiem = thoigianradiem;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }


}
