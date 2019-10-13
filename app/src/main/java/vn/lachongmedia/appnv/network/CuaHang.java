package vn.lachongmedia.appnv.network;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tungda on 7/18/2019.
 */
public class CuaHang implements Serializable {
    private double KinhDo;//105.8361241,
    private double ViDo;//20.97593,
    private String TenCuaHang;//Tung,
    @SerializedName("DiaChi")
    private String DiaChi;//15 , Nguyễn Công Thái, ,Thanh Xuân, Hà Nội,
    private String DiaChi_GPS;//null,
    private String SoDienThoai;//0948448,
    private String SoDienThoai2;//null,
    private String SoDienThoai3;//null,
    private String SoDienThoaiMacDinh;//null,
    private int idnhanvien;//0,
    private int idcuahang;//54868,
    private int IDCTY;//0,
    private String NguoiLienHe;//null,
    private String Email;//null,
    private String Fax;//null,
    private String Website;//null,
    private String TKNganHang;//null,
    private String MaSoThue;//null,
    private String GhiChu;//null,
    private int trangthai;//0,
    private String ngaytao;//null,
    private int tinhthanhid;//0,
    private int quanhuyenid;//0,
    private int phuongxaid;//0,
    private String tentinh;//null,
    private String tenquan;//null,
    private String tenphuong;//null,
    private String DuongPho;//"",
    private String thoigiancheckindukien;//"",
    private String thoigiancheckoutdukien;//"",
    private String thoigiancheckinthucte;//"",
    private String thoigiancheckoutthucte;//"",
    private int trangthaicheckin;//0,
    private int idcheckin;//0,
    private String MaCuaHang;//qrt,
    private int idloaikhachhang;//0,
    private int idnhomkhachhang;//0,
    private int idcha;//0,
    private String tennhomkhachhang;//null,
    private String GhiChuKhiXoa;//null,
    private int idphanloaichucnang;//0,
    private int idtansuat;//0,
    private int trangthaiviengtham;//0,
    private String ngayviengtham;//0001-01-01T00;//00;//00,
    private String mamau_viengtham;//null,
    private String DiaChiXuatHoaDon;//null,
/*        danhsachtuyen;//null
    danhsachanh;//null,*/

    public double getKinhDo() {
        return KinhDo;
    }

    public void setKinhDo(double kinhDo) {
        KinhDo = kinhDo;
    }

    public double getViDo() {
        return ViDo;
    }

    public void setViDo(double viDo) {
        ViDo = viDo;
    }

    public String getTenCuaHang() {
        return TenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        TenCuaHang = tenCuaHang;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getDiaChi_GPS() {
        return DiaChi_GPS;
    }

    public void setDiaChi_GPS(String diaChi_GPS) {
        DiaChi_GPS = diaChi_GPS;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getSoDienThoai2() {
        return SoDienThoai2;
    }

    public void setSoDienThoai2(String soDienThoai2) {
        SoDienThoai2 = soDienThoai2;
    }

    public String getSoDienThoai3() {
        return SoDienThoai3;
    }

    public void setSoDienThoai3(String soDienThoai3) {
        SoDienThoai3 = soDienThoai3;
    }

    public String getSoDienThoaiMacDinh() {
        return SoDienThoaiMacDinh;
    }

    public void setSoDienThoaiMacDinh(String soDienThoaiMacDinh) {
        SoDienThoaiMacDinh = soDienThoaiMacDinh;
    }

    public int getIdnhanvien() {
        return idnhanvien;
    }

    public void setIdnhanvien(int idnhanvien) {
        this.idnhanvien = idnhanvien;
    }

    public int getIdcuahang() {
        return idcuahang;
    }

    public void setIdcuahang(int idcuahang) {
        this.idcuahang = idcuahang;
    }

    public int getIDCTY() {
        return IDCTY;
    }

    public void setIDCTY(int IDCTY) {
        this.IDCTY = IDCTY;
    }

    public String getNguoiLienHe() {
        return NguoiLienHe;
    }

    public void setNguoiLienHe(String nguoiLienHe) {
        NguoiLienHe = nguoiLienHe;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getTKNganHang() {
        return TKNganHang;
    }

    public void setTKNganHang(String TKNganHang) {
        this.TKNganHang = TKNganHang;
    }

    public String getMaSoThue() {
        return MaSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        MaSoThue = maSoThue;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public int getTinhthanhid() {
        return tinhthanhid;
    }

    public void setTinhthanhid(int tinhthanhid) {
        this.tinhthanhid = tinhthanhid;
    }

    public int getQuanhuyenid() {
        return quanhuyenid;
    }

    public void setQuanhuyenid(int quanhuyenid) {
        this.quanhuyenid = quanhuyenid;
    }

    public int getPhuongxaid() {
        return phuongxaid;
    }

    public void setPhuongxaid(int phuongxaid) {
        this.phuongxaid = phuongxaid;
    }

    public String getTentinh() {
        return tentinh;
    }

    public void setTentinh(String tentinh) {
        this.tentinh = tentinh;
    }

    public String getTenquan() {
        return tenquan;
    }

    public void setTenquan(String tenquan) {
        this.tenquan = tenquan;
    }

    public String getTenphuong() {
        return tenphuong;
    }

    public void setTenphuong(String tenphuong) {
        this.tenphuong = tenphuong;
    }

    public String getDuongPho() {
        return DuongPho;
    }

    public void setDuongPho(String duongPho) {
        DuongPho = duongPho;
    }

    public String getThoigiancheckindukien() {
        return thoigiancheckindukien;
    }

    public void setThoigiancheckindukien(String thoigiancheckindukien) {
        this.thoigiancheckindukien = thoigiancheckindukien;
    }

    public String getThoigiancheckoutdukien() {
        return thoigiancheckoutdukien;
    }

    public void setThoigiancheckoutdukien(String thoigiancheckoutdukien) {
        this.thoigiancheckoutdukien = thoigiancheckoutdukien;
    }

    public String getThoigiancheckinthucte() {
        return thoigiancheckinthucte;
    }

    public void setThoigiancheckinthucte(String thoigiancheckinthucte) {
        this.thoigiancheckinthucte = thoigiancheckinthucte;
    }

    public String getThoigiancheckoutthucte() {
        return thoigiancheckoutthucte;
    }

    public void setThoigiancheckoutthucte(String thoigiancheckoutthucte) {
        this.thoigiancheckoutthucte = thoigiancheckoutthucte;
    }

    public int getTrangthaicheckin() {
        return trangthaicheckin;
    }

    public void setTrangthaicheckin(int trangthaicheckin) {
        this.trangthaicheckin = trangthaicheckin;
    }

    public int getIdcheckin() {
        return idcheckin;
    }

    public void setIdcheckin(int idcheckin) {
        this.idcheckin = idcheckin;
    }

    public String getMaCuaHang() {
        return MaCuaHang;
    }

    public void setMaCuaHang(String maCuaHang) {
        MaCuaHang = maCuaHang;
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

    public String getGhiChuKhiXoa() {
        return GhiChuKhiXoa;
    }

    public void setGhiChuKhiXoa(String ghiChuKhiXoa) {
        GhiChuKhiXoa = ghiChuKhiXoa;
    }

    public int getIdphanloaichucnang() {
        return idphanloaichucnang;
    }

    public void setIdphanloaichucnang(int idphanloaichucnang) {
        this.idphanloaichucnang = idphanloaichucnang;
    }

    public int getIdtansuat() {
        return idtansuat;
    }

    public void setIdtansuat(int idtansuat) {
        this.idtansuat = idtansuat;
    }

    public int getTrangthaiviengtham() {
        return trangthaiviengtham;
    }

    public void setTrangthaiviengtham(int trangthaiviengtham) {
        this.trangthaiviengtham = trangthaiviengtham;
    }

    public String getNgayviengtham() {
        return ngayviengtham;
    }

    public void setNgayviengtham(String ngayviengtham) {
        this.ngayviengtham = ngayviengtham;
    }

    public String getMamau_viengtham() {
        return mamau_viengtham;
    }

    public void setMamau_viengtham(String mamau_viengtham) {
        this.mamau_viengtham = mamau_viengtham;
    }

    public String getDiaChiXuatHoaDon() {
        return DiaChiXuatHoaDon;
    }

    public void setDiaChiXuatHoaDon(String diaChiXuatHoaDon) {
        DiaChiXuatHoaDon = diaChiXuatHoaDon;
    }
    private int type;
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return TenCuaHang;
    }
}
