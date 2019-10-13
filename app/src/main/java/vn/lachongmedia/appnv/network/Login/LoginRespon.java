package vn.lachongmedia.appnv.network.Login;

import vn.lachongmedia.appnv.network.CommonRespon;

import java.util.ArrayList;

/**
 * Created by tungda on 7/16/2019.
 */
public class LoginRespon extends CommonRespon {

    private int QuyenNhapChiTietMatHang;
    private Data data;

    public ArrayList<PhanQuyen> getDanhsachphanquyen() {
        return danhsachphanquyen;
    }

    public void setDanhsachphanquyen(ArrayList<PhanQuyen> danhsachphanquyen) {
        this.danhsachphanquyen = danhsachphanquyen;
    }

    private ArrayList<PhanQuyen> danhsachphanquyen;
    private ArrayList<TrangThaiDonHang>dulieutrangthaidonhang;
    private ArrayList<TrangThaiHoanTatDonHang>dulieutrangthaihoantatdonhang;
    private ArrayList<TrangThaiDanhGia>dulieutranghthaidanhgia;
    private ArrayList<NhomKhachHang>dataNhomKhachHang;
    private ArrayList<LoaiAlbum>dataLoaiAlbum;

    public ArrayList<TrangThaiDonHang> getDulieutrangthaidonhang() {
        return dulieutrangthaidonhang;
    }

    public void setDulieutrangthaidonhang(ArrayList<TrangThaiDonHang> dulieutrangthaidonhang) {
        this.dulieutrangthaidonhang = dulieutrangthaidonhang;
    }

    public ArrayList<TrangThaiHoanTatDonHang> getDulieutrangthaihoantatdonhang() {
        return dulieutrangthaihoantatdonhang;
    }

    public void setDulieutrangthaihoantatdonhang(ArrayList<TrangThaiHoanTatDonHang> dulieutrangthaihoantatdonhang) {
        this.dulieutrangthaihoantatdonhang = dulieutrangthaihoantatdonhang;
    }

    public ArrayList<TrangThaiDanhGia> getDulieutranghthaidanhgia() {
        return dulieutranghthaidanhgia;
    }

    public void setDulieutranghthaidanhgia(ArrayList<TrangThaiDanhGia> dulieutranghthaidanhgia) {
        this.dulieutranghthaidanhgia = dulieutranghthaidanhgia;
    }

    public ArrayList<NhomKhachHang> getDataNhomKhachHang() {
        return dataNhomKhachHang;
    }

    public void setDataNhomKhachHang(ArrayList<NhomKhachHang> dataNhomKhachHang) {
        this.dataNhomKhachHang = dataNhomKhachHang;
    }

    public ArrayList<LoaiAlbum> getDataLoaiAlbum() {
        return dataLoaiAlbum;
    }

    public void setDataLoaiAlbum(ArrayList<LoaiAlbum> dataLoaiAlbum) {
        this.dataLoaiAlbum = dataLoaiAlbum;
    }

    public int getQuyenNhapChiTietMatHang() {
        return QuyenNhapChiTietMatHang;
    }

    public void setQuyenNhapChiTietMatHang(int quyenNhapChiTietMatHang) {
        QuyenNhapChiTietMatHang = quyenNhapChiTietMatHang;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        private int idct;//1,
        private int idnhanvien;//1279,
        private String tendangnhap;//truongnm,
        private String matkhau;//25d55ad283aa400af464c76d713c07ad,
        private String matkhaucu;//null,
        private String tennhanvien;//Nguyễn Mạnh Trường,
        private int thoigiancapnhatbantin;//30000,
        private String thoigiandangnhapcuoicung;//2019-07-13T12;//13;//03,
        private int dangtructuyen;//0,
        private int goiungdung;//1,
        private String device;//88dee96aaf9fdbe1,
        private String urlserver;//http;////jav.ksmart.vn,
        private int os;//2,
        private String ver;//2.0.1.9,
        private String idpush;//ey5gm3lRdjA;//APA91bGaVnAQWrbXl9RJlG_BzApnz7vksIfKkwOLNbllHxzOkvfptvwKEmWcKVNXpxQHfy-FhVx2bBh5bYCH0tDlkebF01fhtSk5IYFU_-E492Micvc8fzNOH84uihBeeK-TrRhVHhl9,
        //  idpushkit;//"",
        private int hinhthucdangxuat;//0,
        private int TGCanhBaoMatKetNoi;//5,
        private int TGCanhBaoLaiMatKetNoi;//60,
        private int sotinnhanchuadoc;//0,
        private int TypeApp;//0,
        private double kinhdo;//105.836133,
        private double vido;//20.9759676,
        private double accuracy;//19.719999313354492,
        private int idnhom;//2078,
        private int chophepvaodiemkhongkehoach;//1,
        private int bankinhchophep;//1000,
        private int TGLayViTri;//30000,
        private int ThongBaoPhienBanMoi;//0,
        private String newversion;//null,
        private String msg_newversion;//"",
        private int trangthaigps;//1,
        private int sokytusaudauphaydonvitien;//2,
        private String Min_Version;//1.8.0,
        private String Max_Version;//"",
        private String icon_path;///DATA_UPLOADS/Icon/1/1_201809261452.jpg,
        private int MaDonHang_TuSinh;//1,
        private int MaDonHang_TuSinh_TheoKy;//1,
        private String MaDonHang_CauTruc;//gyymmdd0000,
        private String devicename;//xiaomi,
        private String osversion;//8.1.0,
        private String dongmay;//"",
        private String doimay;//Redmi 5 Plus,
        private String imei;//869780030451964,
        private int ChiCaiDatPhanMem1Lan;//0,
        private int isFakeGPS;//0,
        private int isCheDoTietKiemPin;//0,
        private String ngaycaidat;//2019-03-28T17;//22;//23,
        private int TGThongBaoDenKeHoach;//10,
        private int ChanDangNhapFakeGPS;//0,
        private int ChiTietMatHangKhiTaoDonHang;//0,
        private int ChiLapDonHangKhiVaoDiem;//0,
        private String AnhDaiDien;///DATA_UPLOADS/2019/7/9/1/1279/095851975.jpg,
        private String AnhDaiDien_thumbnail_medium;///DATA_UPLOADS/2019/7/9/1/1279/095851975_medium.jpg,
        private String AnhDaiDien_thumbnail_small;///DATA_UPLOADS/2019/7/9/1/1279/095851975_small.jpg,
        private String DiaChi;//"",
        private String Email;//"",
        private String DienThoai;//null,
        private String GioiTinh;//0,
        private String QueQuan;//"",
        private String NgaySinh;//1900-01-01T00;//00;//00,
        private String SessionID;//e76c3084-6e01-465f-afea-d89539a49e08,
        private String CurrentIP;//10.100.1.3,
        private int Loai;//3,
        private int STT_DonHang;//3,
        private String MaNhom;//map2a,
        private String DinhDangNgayHienThi;//dd/MM/yyyy,
        private int DinhDangTienSoThapPhan;//1

        public int getIdct() {
            return idct;
        }

        public void setIdct(int idct) {
            this.idct = idct;
        }

        public int getIdnhanvien() {
            return idnhanvien;
        }

        public void setIdnhanvien(int idnhanvien) {
            this.idnhanvien = idnhanvien;
        }

        public String getTendangnhap() {
            return tendangnhap;
        }

        public void setTendangnhap(String tendangnhap) {
            this.tendangnhap = tendangnhap;
        }

        public String getMatkhau() {
            return matkhau;
        }

        public void setMatkhau(String matkhau) {
            this.matkhau = matkhau;
        }

        public String getMatkhaucu() {
            return matkhaucu;
        }

        public void setMatkhaucu(String matkhaucu) {
            this.matkhaucu = matkhaucu;
        }

        public String getTennhanvien() {
            return tennhanvien;
        }

        public void setTennhanvien(String tennhanvien) {
            this.tennhanvien = tennhanvien;
        }

        public int getThoigiancapnhatbantin() {
            return thoigiancapnhatbantin;
        }

        public void setThoigiancapnhatbantin(int thoigiancapnhatbantin) {
            this.thoigiancapnhatbantin = thoigiancapnhatbantin;
        }

        public String getThoigiandangnhapcuoicung() {
            return thoigiandangnhapcuoicung;
        }

        public void setThoigiandangnhapcuoicung(String thoigiandangnhapcuoicung) {
            this.thoigiandangnhapcuoicung = thoigiandangnhapcuoicung;
        }

        public int getDangtructuyen() {
            return dangtructuyen;
        }

        public void setDangtructuyen(int dangtructuyen) {
            this.dangtructuyen = dangtructuyen;
        }

        public int getGoiungdung() {
            return goiungdung;
        }

        public void setGoiungdung(int goiungdung) {
            this.goiungdung = goiungdung;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getUrlserver() {
            return urlserver;
        }

        public void setUrlserver(String urlserver) {
            this.urlserver = urlserver;
        }

        public int getOs() {
            return os;
        }

        public void setOs(int os) {
            this.os = os;
        }

        public String getVer() {
            return ver;
        }

        public void setVer(String ver) {
            this.ver = ver;
        }

        public String getIdpush() {
            return idpush;
        }

        public void setIdpush(String idpush) {
            this.idpush = idpush;
        }

        public int getHinhthucdangxuat() {
            return hinhthucdangxuat;
        }

        public void setHinhthucdangxuat(int hinhthucdangxuat) {
            this.hinhthucdangxuat = hinhthucdangxuat;
        }

        public int getTGCanhBaoMatKetNoi() {
            return TGCanhBaoMatKetNoi;
        }

        public void setTGCanhBaoMatKetNoi(int TGCanhBaoMatKetNoi) {
            this.TGCanhBaoMatKetNoi = TGCanhBaoMatKetNoi;
        }

        public int getTGCanhBaoLaiMatKetNoi() {
            return TGCanhBaoLaiMatKetNoi;
        }

        public void setTGCanhBaoLaiMatKetNoi(int TGCanhBaoLaiMatKetNoi) {
            this.TGCanhBaoLaiMatKetNoi = TGCanhBaoLaiMatKetNoi;
        }

        public int getSotinnhanchuadoc() {
            return sotinnhanchuadoc;
        }

        public void setSotinnhanchuadoc(int sotinnhanchuadoc) {
            this.sotinnhanchuadoc = sotinnhanchuadoc;
        }

        public int getTypeApp() {
            return TypeApp;
        }

        public void setTypeApp(int typeApp) {
            TypeApp = typeApp;
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

        public double getAccuracy() {
            return accuracy;
        }

        public void setAccuracy(double accuracy) {
            this.accuracy = accuracy;
        }

        public int getIdnhom() {
            return idnhom;
        }

        public void setIdnhom(int idnhom) {
            this.idnhom = idnhom;
        }

        public int getChophepvaodiemkhongkehoach() {
            return chophepvaodiemkhongkehoach;
        }

        public void setChophepvaodiemkhongkehoach(int chophepvaodiemkhongkehoach) {
            this.chophepvaodiemkhongkehoach = chophepvaodiemkhongkehoach;
        }

        public int getBankinhchophep() {
            return bankinhchophep;
        }

        public void setBankinhchophep(int bankinhchophep) {
            this.bankinhchophep = bankinhchophep;
        }

        public int getTGLayViTri() {
            return TGLayViTri;
        }

        public void setTGLayViTri(int TGLayViTri) {
            this.TGLayViTri = TGLayViTri;
        }

        public int getThongBaoPhienBanMoi() {
            return ThongBaoPhienBanMoi;
        }

        public void setThongBaoPhienBanMoi(int thongBaoPhienBanMoi) {
            ThongBaoPhienBanMoi = thongBaoPhienBanMoi;
        }

        public String getNewversion() {
            return newversion;
        }

        public void setNewversion(String newversion) {
            this.newversion = newversion;
        }

        public String getMsg_newversion() {
            return msg_newversion;
        }

        public void setMsg_newversion(String msg_newversion) {
            this.msg_newversion = msg_newversion;
        }

        public int getTrangthaigps() {
            return trangthaigps;
        }

        public void setTrangthaigps(int trangthaigps) {
            this.trangthaigps = trangthaigps;
        }

        public int getSokytusaudauphaydonvitien() {
            return sokytusaudauphaydonvitien;
        }

        public void setSokytusaudauphaydonvitien(int sokytusaudauphaydonvitien) {
            this.sokytusaudauphaydonvitien = sokytusaudauphaydonvitien;
        }

        public String getMin_Version() {
            return Min_Version;
        }

        public void setMin_Version(String min_Version) {
            Min_Version = min_Version;
        }

        public String getMax_Version() {
            return Max_Version;
        }

        public void setMax_Version(String max_Version) {
            Max_Version = max_Version;
        }

        public String getIcon_path() {
            return icon_path;
        }

        public void setIcon_path(String icon_path) {
            this.icon_path = icon_path;
        }

        public int getMaDonHang_TuSinh() {
            return MaDonHang_TuSinh;
        }

        public void setMaDonHang_TuSinh(int maDonHang_TuSinh) {
            MaDonHang_TuSinh = maDonHang_TuSinh;
        }

        public int getMaDonHang_TuSinh_TheoKy() {
            return MaDonHang_TuSinh_TheoKy;
        }

        public void setMaDonHang_TuSinh_TheoKy(int maDonHang_TuSinh_TheoKy) {
            MaDonHang_TuSinh_TheoKy = maDonHang_TuSinh_TheoKy;
        }

        public String getMaDonHang_CauTruc() {
            return MaDonHang_CauTruc;
        }

        public void setMaDonHang_CauTruc(String maDonHang_CauTruc) {
            MaDonHang_CauTruc = maDonHang_CauTruc;
        }

        public String getDevicename() {
            return devicename;
        }

        public void setDevicename(String devicename) {
            this.devicename = devicename;
        }

        public String getOsversion() {
            return osversion;
        }

        public void setOsversion(String osversion) {
            this.osversion = osversion;
        }

        public String getDongmay() {
            return dongmay;
        }

        public void setDongmay(String dongmay) {
            this.dongmay = dongmay;
        }

        public String getDoimay() {
            return doimay;
        }

        public void setDoimay(String doimay) {
            this.doimay = doimay;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public int getChiCaiDatPhanMem1Lan() {
            return ChiCaiDatPhanMem1Lan;
        }

        public void setChiCaiDatPhanMem1Lan(int chiCaiDatPhanMem1Lan) {
            ChiCaiDatPhanMem1Lan = chiCaiDatPhanMem1Lan;
        }

        public int getIsFakeGPS() {
            return isFakeGPS;
        }

        public void setIsFakeGPS(int isFakeGPS) {
            this.isFakeGPS = isFakeGPS;
        }

        public int getIsCheDoTietKiemPin() {
            return isCheDoTietKiemPin;
        }

        public void setIsCheDoTietKiemPin(int isCheDoTietKiemPin) {
            this.isCheDoTietKiemPin = isCheDoTietKiemPin;
        }

        public String getNgaycaidat() {
            return ngaycaidat;
        }

        public void setNgaycaidat(String ngaycaidat) {
            this.ngaycaidat = ngaycaidat;
        }

        public int getTGThongBaoDenKeHoach() {
            return TGThongBaoDenKeHoach;
        }

        public void setTGThongBaoDenKeHoach(int TGThongBaoDenKeHoach) {
            this.TGThongBaoDenKeHoach = TGThongBaoDenKeHoach;
        }

        public int getChanDangNhapFakeGPS() {
            return ChanDangNhapFakeGPS;
        }

        public void setChanDangNhapFakeGPS(int chanDangNhapFakeGPS) {
            ChanDangNhapFakeGPS = chanDangNhapFakeGPS;
        }

        public int getChiTietMatHangKhiTaoDonHang() {
            return ChiTietMatHangKhiTaoDonHang;
        }

        public void setChiTietMatHangKhiTaoDonHang(int chiTietMatHangKhiTaoDonHang) {
            ChiTietMatHangKhiTaoDonHang = chiTietMatHangKhiTaoDonHang;
        }

        public int getChiLapDonHangKhiVaoDiem() {
            return ChiLapDonHangKhiVaoDiem;
        }

        public void setChiLapDonHangKhiVaoDiem(int chiLapDonHangKhiVaoDiem) {
            ChiLapDonHangKhiVaoDiem = chiLapDonHangKhiVaoDiem;
        }

        public String getAnhDaiDien() {
            return AnhDaiDien;
        }

        public void setAnhDaiDien(String anhDaiDien) {
            AnhDaiDien = anhDaiDien;
        }

        public String getAnhDaiDien_thumbnail_medium() {
            return AnhDaiDien_thumbnail_medium;
        }

        public void setAnhDaiDien_thumbnail_medium(String anhDaiDien_thumbnail_medium) {
            AnhDaiDien_thumbnail_medium = anhDaiDien_thumbnail_medium;
        }

        public String getAnhDaiDien_thumbnail_small() {
            return AnhDaiDien_thumbnail_small;
        }

        public void setAnhDaiDien_thumbnail_small(String anhDaiDien_thumbnail_small) {
            AnhDaiDien_thumbnail_small = anhDaiDien_thumbnail_small;
        }

        public String getDiaChi() {
            return DiaChi;
        }

        public void setDiaChi(String diaChi) {
            DiaChi = diaChi;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getDienThoai() {
            return DienThoai;
        }

        public void setDienThoai(String dienThoai) {
            DienThoai = dienThoai;
        }

        public String getGioiTinh() {
            return GioiTinh;
        }

        public void setGioiTinh(String gioiTinh) {
            GioiTinh = gioiTinh;
        }

        public String getQueQuan() {
            return QueQuan;
        }

        public void setQueQuan(String queQuan) {
            QueQuan = queQuan;
        }

        public String getNgaySinh() {
            return NgaySinh;
        }

        public void setNgaySinh(String ngaySinh) {
            NgaySinh = ngaySinh;
        }

        public String getSessionID() {
            return SessionID;
        }

        public void setSessionID(String sessionID) {
            SessionID = sessionID;
        }

        public String getCurrentIP() {
            return CurrentIP;
        }

        public void setCurrentIP(String currentIP) {
            CurrentIP = currentIP;
        }

        public int getLoai() {
            return Loai;
        }

        public void setLoai(int loai) {
            Loai = loai;
        }

        public int getSTT_DonHang() {
            return STT_DonHang;
        }

        public void setSTT_DonHang(int STT_DonHang) {
            this.STT_DonHang = STT_DonHang;
        }

        public String getMaNhom() {
            return MaNhom;
        }

        public void setMaNhom(String maNhom) {
            MaNhom = maNhom;
        }

        public String getDinhDangNgayHienThi() {
            return DinhDangNgayHienThi;
        }

        public void setDinhDangNgayHienThi(String dinhDangNgayHienThi) {
            DinhDangNgayHienThi = dinhDangNgayHienThi;
        }

        public int getDinhDangTienSoThapPhan() {
            return DinhDangTienSoThapPhan;
        }

        public void setDinhDangTienSoThapPhan(int dinhDangTienSoThapPhan) {
            DinhDangTienSoThapPhan = dinhDangTienSoThapPhan;
        }
    }

}
