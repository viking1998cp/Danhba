package vn.lachongmedia.appnv.network;


import vn.lachongmedia.appnv.network.Login.CongTyRespon;
import vn.lachongmedia.appnv.network.Login.DieuHuongRespon;
import vn.lachongmedia.appnv.network.Login.ListAppFakeGPSResponse;
import vn.lachongmedia.appnv.network.Login.LoginRespon;
import vn.lachongmedia.appnv.network.Login.VersionAppRespon;
import vn.lachongmedia.appnv.network.checkin.CheckInResponse;
import vn.lachongmedia.appnv.network.checkin.CheckOutResponse;
import vn.lachongmedia.appnv.network.chupanh.AlbumRespon;
import vn.lachongmedia.appnv.network.chupanh.ListAlbumRespon;
import vn.lachongmedia.appnv.network.chupanh.SendAlbumRespon;
import vn.lachongmedia.appnv.network.ghichu.DanhSachGhiChuRespon;
import vn.lachongmedia.appnv.network.ghichu.DanhSachTieuChiRespon;
import vn.lachongmedia.appnv.network.khachhang.DanhSachKhachHangLoadMoreRespon;
import vn.lachongmedia.appnv.network.khachhang.DanhSachKhachHangRespon;
import vn.lachongmedia.appnv.network.mathang.DanhMucMatHangRespon;
import vn.lachongmedia.appnv.network.mathang.DanhSachMatHangLoadMoreRespon;
import vn.lachongmedia.appnv.network.phanhoi.DanhSachPhanHoiRespon;
import vn.lachongmedia.appnv.network.phanhoi.DanhSachThemPhanHoiRespon;
import vn.lachongmedia.appnv.network.tinnhan.TinNhanConversionRespon;
import vn.lachongmedia.appnv.network.trangchu.DanhSachlichHenRespon;
import vn.lachongmedia.appnv.network.trangchu.ListCuaHangRespon;
import vn.lachongmedia.appnv.network.trangchu.ListKeHoachDonHangTrongNgayRespon;
import vn.lachongmedia.appnv.network.trangchu.ListKeHoachTrongNgayRespon;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import vn.lachongmedia.appnv.object.GhiChu.GhiChu;
import vn.lachongmedia.appnv.object.PhanHoi.PhanHoi;

/**
 * Created by tungda on 7/14/2018.
 */

public interface Service {

    @GET("AppLogin_v2.aspx")
    Call<LoginRespon> login(@QueryMap Map<String, String> params );

    @GET("/AppRouterServer.aspx")
    Call<DieuHuongRespon> dieuHuong(@QueryMap Map<String, String> params );

    @GET
    Call<CommonRespon> checkDieuHuongMayChu(@Url String url);

    @GET("/AppDanhSachAppFake.aspx")
    Call<ListAppFakeGPSResponse> listAppFake(@QueryMap Map<String, String> params );

    @GET("/AppVeChungToi.aspx")
    Call<CongTyRespon> thongTinCongTy(@QueryMap Map<String, String> params );

    @GET("/AppCheckPhienBanMoi.aspx")
    Call<VersionAppRespon> checkPhienBanMoi(@QueryMap Map<String, String> params );


    @GET("/AppCuaHangGanNhat.aspx")
    Call<ListCuaHangRespon> getCuaHangGanNhat(@QueryMap Map<String, String> params );

    @GET("/AppKeHoachDiChuyen_v3.aspx")
    Call<ListKeHoachTrongNgayRespon> getKeHoachTrongNgay(@QueryMap Map<String, String> params );

    @GET("/AppLichHen.aspx")
    Call<DanhSachlichHenRespon> getLichHen(@QueryMap Map<String, String> params );

    @GET("/AppKeHoachDonHang.aspx")
    Call<ListKeHoachDonHangTrongNgayRespon> getKeHoachDonHang(@QueryMap Map<String, String> params );
    
    @GET("/AppVaoDiemCuaHangGanNhatKhongKeHoach.aspx")
    Call<ListCuaHangRespon> getCuaHangDeVaoDiem(@QueryMap Map<String, String> params );


    @GET("/AppVaoDiem.aspx")
    Call<CheckInResponse> checkIn(@QueryMap Map<String, String> params );


    @GET("/AppVaoDiem.aspx")
    Call<CheckOutResponse> checkOut(@QueryMap Map<String, String> params );

    @GET("/AppAlbum.aspx")
    Call<ListAlbumRespon> getListAlbum(@QueryMap Map<String, String> params );

    @GET("/AppAlbum.aspx")
    Call<AlbumRespon> getAlbum(@QueryMap Map<String, String> params );

    @GET("/AppAlbum.aspx")
    Call<SendAlbumRespon> sendAlbum(@QueryMap Map<String, String> params );

    @GET("/AppDanhSachTinNhan_v3.aspx")
    Call<TinNhanConversionRespon> getDanhSachTinNhanConversion(@QueryMap Map<String, String> params );
    @GET("/AppXuLyTinNhan.aspx")
    Call<CommonRespon> xuLyTinNhan(@QueryMap Map<String, String> params );

    @GET("/AppDanhSachCuaHang_v3.aspx")
    Call<ListCuaHangLoadMoreRespon> getDanhSachCuaHang(@QueryMap Map<String, String> params );

    @GET("/AppDanhSachMatHang_v5.aspx")
    Call<DanhSachMatHangLoadMoreRespon> getDanhSachMatHang(@QueryMap Map<String, String> params );

    @GET("/AppDanhSachDanhMuc.aspx")
    Call<DanhMucMatHangRespon> getDanhMucMatHang(@QueryMap Map<String, String> params );

    @GET("/AppPhanHoi.aspx")
    Call<DanhSachPhanHoiRespon> getDanhSachPhanHoi(@QueryMap Map<String,String> params);
    @GET("/AppPhanHoi.aspx")
    Call<DanhSachThemPhanHoiRespon> getDanhSachThemPhanHoi(@QueryMap Map<String,String> params);
    @GET("/AppPhanHoi.aspx")
    Call<PhanHoi> themPhanHoi(@QueryMap Map<String,String> params);

    @GET("/AppCheckList.aspx")
    Call<DanhSachGhiChuRespon> getDanhSachGhiChu(@QueryMap Map<String,String> params);
    @GET("/AppCheckList.aspx")
    Call<DanhSachTieuChiRespon> getDanhSachTieuChiGhiChu(@QueryMap Map<String,String> params);
    @GET("/AppCheckList.aspx")
    Call<GhiChu> themGhiChu(@QueryMap Map<String,String> params);

    @GET("/AppDanhSachCuaHang_v3.aspx")
    Call<DanhSachKhachHangLoadMoreRespon> getDanhSachKhachHang(@QueryMap Map<String,String> params);



}
