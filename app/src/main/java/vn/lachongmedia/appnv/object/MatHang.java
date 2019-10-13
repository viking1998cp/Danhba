
package vn.lachongmedia.appnv.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by tungda on 7/26/2019.
 */
public class MatHang implements Serializable
{

    @SerializedName("idhang")
    @Expose
    private int iDHang;
    @SerializedName("mahang")
    @Expose
    private String maHang;
    @SerializedName("tenhang")
    @Expose
    private String tenHang;
    @SerializedName("tenhienthi")
    @Expose
    private String tenHienThi;
    @SerializedName("giabuon")
    @Expose
    private String giaBuon;
    @SerializedName("giale")
    @Expose
    private String giaLe;
    @SerializedName("donvi")
    @Expose
    private String donVi;
    @SerializedName("tonkho")
    @Expose
    private double tonKho;
    @SerializedName("soluong")
    @Expose
    private double soLuong;
    @SerializedName("khuyenmai")
    @Expose
    private String khuyenMai;
    @SerializedName("tonggiao")
    @Expose
    private double tongGiao;
    @SerializedName("hinhthucban")
    @Expose
    private int hinhThucBan;
    @SerializedName("iddanhmuc")
    @Expose
    private int iDDanhmuc;
    @SerializedName("tendanhmuc")
    @Expose
    private String tenDanhMuc;
    @SerializedName("ghichugia")
    @Expose
    private String ghiChuGia;
    @SerializedName("ctkm")
    @Expose
    private ArrayList<KhuyenMai> listKhuyenMai;
    @SerializedName("tennhacungcap")
    @Expose
    private String tenNhaCungCap;
    @SerializedName("tennhanhieu")
    @Expose
    private String tenNhanHieu;
    @SerializedName("mota")
    @Expose
    private String moTa;
    @SerializedName("linkgioithieu")
    @Expose
    private String linkgioithieu;
    @SerializedName("RowNum")
    @Expose
    private int rowNum;
    @SerializedName("LoaiHangHoa")
    @Expose
    private int loaiHangHoa;
    @SerializedName("anhdaidien")
    @Expose
    private String anhDaiDien;
    @SerializedName("danhsachanh")
    @Expose
    private List<Object> danhSachAnh = null;
    @SerializedName("tonorder")
    @Expose
    private double tonOrder;
    @SerializedName("tonthucte")
    @Expose
    private double tonThucte;
    @SerializedName("mamau")
    @Expose
    private String maMau;

    private int type;

    public int getiDHang() {
        return iDHang;
    }

    public void setiDHang(int iDHang) {
        this.iDHang = iDHang;
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

    public String getTenHienThi() {
        return tenHienThi;
    }

    public void setTenHienThi(String tenHienThi) {
        this.tenHienThi = tenHienThi;
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

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public double getTonKho() {
        return tonKho;
    }

    public void setTonKho(int tonKho) {
        this.tonKho = tonKho;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(String khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public double getTongGiao() {
        return tongGiao;
    }

    public void setTongGiao(int tongGiao) {
        this.tongGiao = tongGiao;
    }

    public int getHinhThucBan() {
        return hinhThucBan;
    }

    public void setHinhThucBan(int hinhThucBan) {
        this.hinhThucBan = hinhThucBan;
    }

    public int getiDDanhmuc() {
        return iDDanhmuc;
    }

    public void setiDDanhmuc(int iDDanhmuc) {
        this.iDDanhmuc = iDDanhmuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getGhiChuGia() {
        return ghiChuGia;
    }

    public void setGhiChuGia(String ghiChuGia) {
        this.ghiChuGia = ghiChuGia;
    }

    public ArrayList<KhuyenMai> getListKhuyenMai() {
        return listKhuyenMai;
    }

    public void setListKhuyenMai(ArrayList<KhuyenMai> listKhuyenMai) {
        this.listKhuyenMai = listKhuyenMai;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getTenNhanHieu() {
        return tenNhanHieu;
    }

    public void setTenNhanHieu(String tenNhanHieu) {
        this.tenNhanHieu = tenNhanHieu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getLinkgioithieu() {
        return linkgioithieu;
    }

    public void setLinkgioithieu(String linkgioithieu) {
        this.linkgioithieu = linkgioithieu;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getLoaiHangHoa() {
        return loaiHangHoa;
    }

    public void setLoaiHangHoa(int loaiHangHoa) {
        this.loaiHangHoa = loaiHangHoa;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public List<Object> getDanhSachAnh() {
        return danhSachAnh;
    }

    public void setDanhSachAnh(List<Object> danhSachAnh) {
        this.danhSachAnh = danhSachAnh;
    }

    public double getTonOrder() {
        return tonOrder;
    }

    public void setTonOrder(int tonOrder) {
        this.tonOrder = tonOrder;
    }

    public double getTonThucte() {
        return tonThucte;
    }

    public void setTonThucte(int tonThucte) {
        this.tonThucte = tonThucte;
    }

    public String getMaMau() {
        return maMau;
    }

    public void setMaMau(String maMau) {
        this.maMau = maMau;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
