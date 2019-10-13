package vn.lachongmedia.appnv;

/**
 * Created by tungda on 7/16/2019.
 */
public class MaChucNang {
    public static final MaChucNang instance=new MaChucNang();
    public static MaChucNang getInstance() {
        return instance;
    }
    private boolean trangChu = false;//APP_001
    private boolean tinNhan = false;//APP_002
    private boolean vaoDiem=false;//APP_003
    private boolean raDiem=false;//APP_004
    private boolean taoDonHang=false;//APP_005
    private boolean xuLyDonHang=false;//APP_006
    private boolean khuyenMai=false;//APP_007
    private boolean khachHang=false;//APP_008
    private boolean xemKeHoach=false;//APP_009
    private boolean lapKeHoach=false;//APP_010
    private boolean chupVaGuiAnh=false;//APP_011
    private boolean baoCaoAnhChup=false;//APP_012
    private boolean phienLamViec=false;//APP_013
    private boolean lichSuDiChuyen=false;//APP_014
    private boolean lichSuVaoRaDiem=false;//APP_015
    private boolean baoCaoDonHang=false;//APP_016
    private boolean baoCaoDoanhThu=false;//APP_017
    private boolean baoCaoMatHang=false;//APP_018
    private boolean lienHe=false;//APP_019
    private boolean doiMatKhau=false;//APP_020
    private boolean caiDat=false;//APP_021
    private boolean dangXuat=false;//APP_022
    private boolean thuHoiCongNo=false;//APP_023
    private boolean phanHoi=false;//APP_024
    private boolean checklist=false;//APP_025
    private boolean lichHen=false;//APP_026
    private boolean chupAnhbienBang=false;//APP_027
    private boolean chupAnhSanPham=false;//APP_028
    private boolean danhSachMatHang=false;//APP_029
    private boolean lichSuGiaoHang=false;//APP_030
    private boolean lichSuThanhToan=false;//APP_031
    private boolean keHoachBaoDuong=false;//APP_032
    private boolean lichSuBaoDuong=false;//APP_033
    private boolean giaoViec=false;//APP_034
    private boolean baoCaoKPI=false;//APP_035
    private boolean donHangCoBan=false;//APP_036
    private boolean guiBanMatHang=false;//APP_037
    private boolean donHangNhieuKhuyenMai=false;//APP_038



    public boolean isDonHangNhieuKhuyenMai() {
        return donHangNhieuKhuyenMai;
    }

    public void setDonHangNhieuKhuyenMai(boolean donHangNhieuKhuyenMai) {
        this.donHangNhieuKhuyenMai = donHangNhieuKhuyenMai;
    }

    public boolean isTrangChu() {
        return trangChu;
    }

    public void setTrangChu(boolean trangChu) {
        this.trangChu = trangChu;
    }

    public boolean isTinNhan() {
        return tinNhan;
    }

    public void setTinNhan(boolean tinNhan) {
        this.tinNhan = tinNhan;
    }

    public boolean isVaoDiem() {
        return vaoDiem;
    }

    public void setVaoDiem(boolean vaoDiem) {
        this.vaoDiem = vaoDiem;
    }

    public boolean isRaDiem() {
        return raDiem;
    }

    public void setRaDiem(boolean raDiem) {
        this.raDiem = raDiem;
    }

    public boolean isTaoDonHang() {
        return taoDonHang;
    }

    public void setTaoDonHang(boolean taoDonHang) {
        this.taoDonHang = taoDonHang;
    }

    public boolean isXuLyDonHang() {
        return xuLyDonHang;
    }

    public void setXuLyDonHang(boolean xuLyDonHang) {
        this.xuLyDonHang = xuLyDonHang;
    }

    public boolean isKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(boolean khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public boolean isKhachHang() {
        return khachHang;
    }

    public void setKhachHang(boolean khachHang) {
        this.khachHang = khachHang;
    }

    public boolean isXemKeHoach() {
        return xemKeHoach;
    }

    public void setXemKeHoach(boolean xemKeHoach) {
        this.xemKeHoach = xemKeHoach;
    }

    public boolean isLapKeHoach() {
        return lapKeHoach;
    }

    public void setLapKeHoach(boolean lapKeHoach) {
        this.lapKeHoach = lapKeHoach;
    }

    public boolean isChupVaGuiAnh() {
        return chupVaGuiAnh;
    }

    public void setChupVaGuiAnh(boolean chupVaGuiAnh) {
        this.chupVaGuiAnh = chupVaGuiAnh;
    }

    public boolean isBaoCaoAnhChup() {
        return baoCaoAnhChup;
    }

    public void setBaoCaoAnhChup(boolean baoCaoAnhChup) {
        this.baoCaoAnhChup = baoCaoAnhChup;
    }

    public boolean isPhienLamViec() {
        return phienLamViec;
    }

    public void setPhienLamViec(boolean phienLamViec) {
        this.phienLamViec = phienLamViec;
    }

    public boolean isLichSuDiChuyen() {
        return lichSuDiChuyen;
    }

    public void setLichSuDiChuyen(boolean lichSuDiChuyen) {
        this.lichSuDiChuyen = lichSuDiChuyen;
    }

    public boolean isLichSuVaoRaDiem() {
        return lichSuVaoRaDiem;
    }

    public void setLichSuVaoRaDiem(boolean lichSuVaoRaDiem) {
        this.lichSuVaoRaDiem = lichSuVaoRaDiem;
    }

    public boolean isBaoCaoDonHang() {
        return baoCaoDonHang;
    }

    public void setBaoCaoDonHang(boolean baoCaoDonHang) {
        this.baoCaoDonHang = baoCaoDonHang;
    }

    public boolean isBaoCaoDoanhThu() {
        return baoCaoDoanhThu;
    }

    public void setBaoCaoDoanhThu(boolean baoCaoDoanhThu) {
        this.baoCaoDoanhThu = baoCaoDoanhThu;
    }

    public boolean isBaoCaoMatHang() {
        return baoCaoMatHang;
    }

    public void setBaoCaoMatHang(boolean baoCaoMatHang) {
        this.baoCaoMatHang = baoCaoMatHang;
    }

    public boolean isLienHe() {
        return lienHe;
    }

    public void setLienHe(boolean lienHe) {
        this.lienHe = lienHe;
    }

    public boolean isDoiMatKhau() {
        return doiMatKhau;
    }

    public void setDoiMatKhau(boolean doiMatKhau) {
        this.doiMatKhau = doiMatKhau;
    }

    public boolean isCaiDat() {
        return caiDat;
    }

    public void setCaiDat(boolean caiDat) {
        this.caiDat = caiDat;
    }

    public boolean isDangXuat() {
        return dangXuat;
    }

    public void setDangXuat(boolean dangXuat) {
        this.dangXuat = dangXuat;
    }

    public boolean isThuHoiCongNo() {
        return thuHoiCongNo;
    }

    public void setThuHoiCongNo(boolean thuHoiCongNo) {
        this.thuHoiCongNo = thuHoiCongNo;
    }

    public boolean isPhanHoi() {
        return phanHoi;
    }

    public void setPhanHoi(boolean phanHoi) {
        this.phanHoi = phanHoi;
    }

    public boolean isChecklist() {
        return checklist;
    }

    public void setChecklist(boolean checklist) {
        this.checklist = checklist;
    }

    public boolean isLichHen() {
        return lichHen;
    }

    public void setLichHen(boolean lichHen) {
        this.lichHen = lichHen;
    }

    public boolean isChupAnhbienBang() {
        return chupAnhbienBang;
    }

    public void setChupAnhbienBang(boolean chupAnhbienBang) {
        this.chupAnhbienBang = chupAnhbienBang;
    }

    public boolean isChupAnhSanPham() {
        return chupAnhSanPham;
    }

    public void setChupAnhSanPham(boolean chupAnhSanPham) {
        this.chupAnhSanPham = chupAnhSanPham;
    }

    public boolean isDanhSachMatHang() {
        return danhSachMatHang;
    }

    public void setDanhSachMatHang(boolean danhSachMatHang) {
        this.danhSachMatHang = danhSachMatHang;
    }

    public boolean isLichSuGiaoHang() {
        return lichSuGiaoHang;
    }

    public void setLichSuGiaoHang(boolean lichSuGiaoHang) {
        this.lichSuGiaoHang = lichSuGiaoHang;
    }

    public boolean isLichSuThanhToan() {
        return lichSuThanhToan;
    }

    public void setLichSuThanhToan(boolean lichSuThanhToan) {
        this.lichSuThanhToan = lichSuThanhToan;
    }

    public boolean isKeHoachBaoDuong() {
        return keHoachBaoDuong;
    }

    public void setKeHoachBaoDuong(boolean keHoachBaoDuong) {
        this.keHoachBaoDuong = keHoachBaoDuong;
    }

    public boolean isLichSuBaoDuong() {
        return lichSuBaoDuong;
    }

    public void setLichSuBaoDuong(boolean lichSuBaoDuong) {
        this.lichSuBaoDuong = lichSuBaoDuong;
    }

    public boolean isGiaoViec() {
        return giaoViec;
    }

    public void setGiaoViec(boolean giaoViec) {
        this.giaoViec = giaoViec;
    }

    public boolean isBaoCaoKPI() {
        return baoCaoKPI;
    }

    public void setBaoCaoKPI(boolean baoCaoKPI) {
        this.baoCaoKPI = baoCaoKPI;
    }

    public boolean isDonHangCoBan() {
        return donHangCoBan;
    }

    public void setDonHangCoBan(boolean donHangCoBan) {
        this.donHangCoBan = donHangCoBan;
    }

    public boolean isGuiBanMatHang() {
        return guiBanMatHang;
    }

    public void setGuiBanMatHang(boolean guiBanMatHang) {
        this.guiBanMatHang = guiBanMatHang;
    }


 }
