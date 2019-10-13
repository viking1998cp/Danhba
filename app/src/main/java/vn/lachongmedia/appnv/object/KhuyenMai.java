
package vn.lachongmedia.appnv.object;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by tungda on 7/26/2019.
 */
public class KhuyenMai implements Serializable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("idctkm")
    @Expose
    private int idCTKM;
    @SerializedName("loai")
    @Expose
    private int loai;
    @SerializedName("tenctkm")
    @Expose
    private String tenCTKM;
    @SerializedName("anhdaidienctkm")
    @Expose
    private String anhDAidienCTKN;
    @SerializedName("ngayapdung")
    @Expose
    private String ngayApDung;
    @SerializedName("ngaytao")
    @Expose
    private String ngayTao;
    @SerializedName("ngayketthuc")
    @Expose
    private String ngayKetThuc;
    @SerializedName("trangthai")
    @Expose
    private int trangThai;
    @SerializedName("idhang")
    @Expose
    private int iDHang;
    @SerializedName("chietkhauphantram_banle")
    @Expose
    private double chietKhauPhanTramBanLe;
    @SerializedName("chietkhautien_banle")
    @Expose
    private double chietKhauTienBanLe;
    @SerializedName("chietkhauphantram_banbuon")
    @Expose
    private double chietKhauPhanTramBanBuon;
    @SerializedName("chietkhautien_banbuon")
    @Expose
    private double chietKhauTienBanBuon;
    @SerializedName("ghichu")
    @Expose
    private String ghiChu;
    @SerializedName("mahang")
    @Expose
    private String maHang;
    @SerializedName("tenhang")
    @Expose
    private String tenHang;
    @SerializedName("donvi")
    @Expose
    private String donVi;
    @SerializedName("giabuon")
    @Expose
    private String giaBuon;
    @SerializedName("giale")
    @Expose
    private String giaLe;
    @SerializedName("SoLuongDatKM_Den")
    @Expose
    private int soLuongDatKMDen;
    @SerializedName("SoLuongDatKM_Tu")
    @Expose
    private int soLuongDatKMTu;
    @SerializedName("TongTienDatKM_Den")
    @Expose
    private int tongTienDatKMDen;
    @SerializedName("TongTienDatKM_Tu")
    @Expose
    private int tongTienDatKMTu;
    @SerializedName("ApDungBoiSo")
    @Expose
    private int apDungBoiSo;
    @SerializedName("Check")
    @Expose
    private int check;
    @SerializedName("chitiethangtang")
    @Expose
    private ArrayList<ChiTietHangTang> listChiTietHangTang ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCTKM() {
        return idCTKM;
    }

    public void setIdCTKM(int idCTKM) {
        this.idCTKM = idCTKM;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public String getTenCTKM() {
        return tenCTKM;
    }

    public void setTenCTKM(String tenCTKM) {
        this.tenCTKM = tenCTKM;
    }

    public String getAnhDAidienCTKN() {
        return anhDAidienCTKN;
    }

    public void setAnhDAidienCTKN(String anhDAidienCTKN) {
        this.anhDAidienCTKN = anhDAidienCTKN;
    }

    public String getNgayApDung() {
        return ngayApDung;
    }

    public void setNgayApDung(String ngayApDung) {
        this.ngayApDung = ngayApDung;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getiDHang() {
        return iDHang;
    }

    public void setiDHang(int iDHang) {
        this.iDHang = iDHang;
    }

    public double getChietKhauPhanTramBanLe() {
        return chietKhauPhanTramBanLe;
    }

    public void setChietKhauPhanTramBanLe(int chietKhauPhanTramBanLe) {
        this.chietKhauPhanTramBanLe = chietKhauPhanTramBanLe;
    }

    public double getChietKhauTienBanLe() {
        return chietKhauTienBanLe;
    }

    public void setChietKhauTienBanLe(int chietKhauTienBanLe) {
        this.chietKhauTienBanLe = chietKhauTienBanLe;
    }

    public double getChietKhauPhanTramBanBuon() {
        return chietKhauPhanTramBanBuon;
    }

    public void setChietKhauPhanTramBanBuon(int chietKhauPhanTramBanBuon) {
        this.chietKhauPhanTramBanBuon = chietKhauPhanTramBanBuon;
    }

    public double getChietKhauTienBanBuon() {
        return chietKhauTienBanBuon;
    }

    public void setChietKhauTienBanBuon(int chietKhauTienBanBuon) {
        this.chietKhauTienBanBuon = chietKhauTienBanBuon;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getGiaBuon() {
        return giaBuon;
    }

    public void setGiaBuon(String giaBuon) {
        this.giaBuon = giaBuon;
    }

    public String getGiaLe() {
        return giaLe;
    }

    public void setGiaLe(String giaLe) {
        this.giaLe = giaLe;
    }

    public int getSoLuongDatKMDen() {
        return soLuongDatKMDen;
    }

    public void setSoLuongDatKMDen(int soLuongDatKMDen) {
        this.soLuongDatKMDen = soLuongDatKMDen;
    }

    public int getSoLuongDatKMTu() {
        return soLuongDatKMTu;
    }

    public void setSoLuongDatKMTu(int soLuongDatKMTu) {
        this.soLuongDatKMTu = soLuongDatKMTu;
    }

    public int getTongTienDatKMDen() {
        return tongTienDatKMDen;
    }

    public void setTongTienDatKMDen(int tongTienDatKMDen) {
        this.tongTienDatKMDen = tongTienDatKMDen;
    }

    public int getTongTienDatKMTu() {
        return tongTienDatKMTu;
    }

    public void setTongTienDatKMTu(int tongTienDatKMTu) {
        this.tongTienDatKMTu = tongTienDatKMTu;
    }

    public int getApDungBoiSo() {
        return apDungBoiSo;
    }

    public void setApDungBoiSo(int apDungBoiSo) {
        this.apDungBoiSo = apDungBoiSo;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public ArrayList<ChiTietHangTang> getListChiTietHangTang() {
        return listChiTietHangTang;
    }

    public void setListChiTietHangTang(ArrayList<ChiTietHangTang> listChiTietHangTang) {
        this.listChiTietHangTang = listChiTietHangTang;
    }
}
