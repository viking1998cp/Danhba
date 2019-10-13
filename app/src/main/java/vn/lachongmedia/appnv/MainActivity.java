package vn.lachongmedia.appnv;

import android.content.Intent;
import android.os.Bundle;

import vn.lachongmedia.appnv.R;

import vn.lachongmedia.appnv.activity.LichHenActivity;
import vn.lachongmedia.appnv.fragment.CheckOutFragment;
import vn.lachongmedia.appnv.object.flagobj;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import vn.lachongmedia.appnv.activity.AlbumActivity;
import vn.lachongmedia.appnv.activity.ChiTietKhachHangActivity;
import vn.lachongmedia.appnv.activity.ChiTietSoanTinNhanCuActivity;
import vn.lachongmedia.appnv.activity.DonHangActivity;
import vn.lachongmedia.appnv.activity.LapKeHoachKhachHangActivity;
import vn.lachongmedia.appnv.activity.TaoCongNoActivity;
import vn.lachongmedia.appnv.activity.TaoGhiChuActivity;
import vn.lachongmedia.appnv.activity.TaoPhanHoiActivity;
import vn.lachongmedia.appnv.activity.ThemMatHangGuiBanActivity;
import vn.lachongmedia.appnv.fragment.BaoCaoDoanhThuFragment;
import vn.lachongmedia.appnv.fragment.BaoCaoGuiBanFragment;
import vn.lachongmedia.appnv.fragment.BaoCaoKPIDonHangFragment;
import vn.lachongmedia.appnv.fragment.BaoCaoKPIFragment;
import vn.lachongmedia.appnv.fragment.BaoCaoLichSuDichuyenFragment;
import vn.lachongmedia.appnv.fragment.BaoCaoLichSuGiaoHangFragment;
import vn.lachongmedia.appnv.fragment.BaoCaoLichSuVaoRaDiemFragment;
import vn.lachongmedia.appnv.fragment.BaoCaoMatHangFragment;
import vn.lachongmedia.appnv.fragment.BaoCaoPhienLamViecFragment;
import vn.lachongmedia.appnv.fragment.BaoCaoThanhToanFragment;
import vn.lachongmedia.appnv.fragment.CheckInFragment;
import vn.lachongmedia.appnv.fragment.DanhSachAlbumFragment;
import vn.lachongmedia.appnv.fragment.DanhSachCongNoFragment;
import vn.lachongmedia.appnv.fragment.DanhSachDonHangFragment;
import vn.lachongmedia.appnv.fragment.DanhSachGhiChuFragment;
import vn.lachongmedia.appnv.fragment.DanhSachGiaoViecFragment;
import vn.lachongmedia.appnv.fragment.DanhSachKhachHangFragment;
import vn.lachongmedia.appnv.fragment.DanhSachKhuyenMaiFragment;
import vn.lachongmedia.appnv.fragment.DanhSachLichHenFragment;
import vn.lachongmedia.appnv.fragment.DanhSachMatHangFragment;
import vn.lachongmedia.appnv.fragment.DanhSachPhanHoiFragment;
import vn.lachongmedia.appnv.fragment.DanhSachTinNhanFragment;
import vn.lachongmedia.appnv.fragment.DanhSachXuLyDonHangFragment;
import vn.lachongmedia.appnv.fragment.DoiMatKhauFragment;
import vn.lachongmedia.appnv.fragment.HuongDanFragment;
import vn.lachongmedia.appnv.fragment.KeHoachKhachHangFragment;
import vn.lachongmedia.appnv.fragment.KeHoachKhuVucFragment;
import vn.lachongmedia.appnv.fragment.KeHoachTuyenFragment;
import vn.lachongmedia.appnv.fragment.LienHeFragment;
import vn.lachongmedia.appnv.fragment.QuanLyGuiBanFragment;
import vn.lachongmedia.appnv.fragment.SetupFragment;
import vn.lachongmedia.appnv.fragment.TrangchuFragment;

/**
 * Created by tungda .
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Common.Current_Activity=MainActivity.this;
                drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setPhanQuyenMenu();
       /* updateMenuItemParentVisibly();
        updateMenuItemSubHide();*/
        //  menuRight.findItem(R.id.nav_add).setVisible(false);
        //setPhanQuyenMenu();
     /*   getSupportActionBar().setTitle("Trang chủ");
        replaceFragment(new TrangchuFragment());*/
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        getSupportActionBar().setTitle("Trang chủ");
        replaceFragment(new TrangchuFragment());
        navigationView.setCheckedItem(navigationView.getMenu().findItem(R.id.nav_home));
    }


    private void setPhanQuyenMenu(){
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_home).setVisible(MaChucNang.getInstance().isTrangChu());
        menu.findItem(R.id.nav_sms).setEnabled(MaChucNang.getInstance().isTinNhan());
        menu.findItem(R.id.nav_vaoradiem).setEnabled(MaChucNang.getInstance().isVaoDiem());
        menu.findItem(R.id.nav_takephoto).setEnabled(MaChucNang.getInstance().isChupVaGuiAnh());
        menu.findItem(R.id.nav_lichhen).setEnabled(MaChucNang.getInstance().isLichHen());

        menu.findItem(R.id.nav_taodonhang).setEnabled(MaChucNang.getInstance().isTaoDonHang());
        menu.findItem(R.id.nav_xulydonhang).setEnabled(MaChucNang.getInstance().isXuLyDonHang());
        menu.findItem(R.id.nav_mathang).setEnabled(MaChucNang.getInstance().isDanhSachMatHang());
        menu.findItem(R.id.nav_quanlyguiban).setEnabled(MaChucNang.getInstance().isGuiBanMatHang());
        menu.findItem(R.id.nav_phanhoi).setEnabled(MaChucNang.getInstance().isPhanHoi());
        menu.findItem(R.id.nav_ghichu).setEnabled(MaChucNang.getInstance().isChecklist());
        menu.findItem(R.id.nav_thuhoicongno).setEnabled(MaChucNang.getInstance().isThuHoiCongNo());
        menu.findItem(R.id.nav_khuyenmai).setEnabled(MaChucNang.getInstance().isKhuyenMai());
        menu.findItem(R.id.nav_danhsachkhachhang).setEnabled(MaChucNang.getInstance().isKhachHang());
        menu.findItem(R.id.nav_bienbang).setEnabled(MaChucNang.getInstance().isChupAnhbienBang());

        menu.findItem(R.id.nav_kehoachkhachhang).setEnabled(MaChucNang.getInstance().isLapKeHoach());
        menu.findItem(R.id.nav_kehoachtuyen).setEnabled(MaChucNang.getInstance().isLapKeHoach());
        menu.findItem(R.id.nav_kehoachkhuvuc).setEnabled(MaChucNang.getInstance().isLapKeHoach());
        menu.findItem(R.id.nav_giaoviec).setEnabled(MaChucNang.getInstance().isGiaoViec());

        menu.findItem(R.id.nav_phienlamviec).setEnabled(MaChucNang.getInstance().isPhienLamViec());
        menu.findItem(R.id.nav_lichsudichuyen).setEnabled(MaChucNang.getInstance().isLichSuDiChuyen());
        menu.findItem(R.id.nav_lichsuvaoradiem).setEnabled(MaChucNang.getInstance().isLichSuVaoRaDiem());
        menu.findItem(R.id.nav_baocaokpi).setEnabled(MaChucNang.getInstance().isBaoCaoKPI());
        menu.findItem(R.id.nav_baocaokpidonhang).setEnabled(MaChucNang.getInstance().isDonHangCoBan());
        menu.findItem(R.id.nav_baocaoguiban).setEnabled(MaChucNang.getInstance().isGuiBanMatHang());
        menu.findItem(R.id.nav_baocaodoanhthu).setEnabled(MaChucNang.getInstance().isBaoCaoDoanhThu());
        menu.findItem(R.id.nav_baocaogiaohang).setEnabled(MaChucNang.getInstance().isLichSuGiaoHang());
        menu.findItem(R.id.nav_baocaothanhtoan).setEnabled(MaChucNang.getInstance().isLichSuThanhToan());

        menu.findItem(R.id.nav_cauhinh).setEnabled(MaChucNang.getInstance().isCaiDat());
        menu.findItem(R.id.nav_doimatkhau).setEnabled(MaChucNang.getInstance().isCaiDat());
        menu.findItem(R.id.nav_lienhe).setEnabled(MaChucNang.getInstance().isCaiDat());
        menu.findItem(R.id.nav_huongdansudung).setEnabled(MaChucNang.getInstance().isCaiDat());

        menu.findItem(R.id.nav_logut).setEnabled(MaChucNang.getInstance().isDangXuat());




    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    Menu menuRight;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menuRight = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.nav_add) {
            // Handle the camera action
            // replaceFragment( new FragmentHome());

            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fl_contain);
            if (currentFragment instanceof DanhSachPhanHoiFragment) {
                startActivity(new Intent(getApplication(), TaoPhanHoiActivity.class));
            } else if (currentFragment instanceof DanhSachDonHangFragment) {
                startActivity(new Intent(getApplication(), DonHangActivity.class));
            } else if (currentFragment instanceof DanhSachAlbumFragment) {

                Intent intent = new Intent(getApplication(), AlbumActivity.class);
                intent.putExtra("type", "tao");
                //intent.putExtra("chucnangalbum", loaiChucNangAlbum);
                startActivityForResult(intent, 1);
               // startActivity(new Intent(getApplication(), AlbumActivity.class));
            } else if (currentFragment instanceof DanhSachGhiChuFragment) {
                startActivity(new Intent(getApplication(), TaoGhiChuActivity.class));
            } else if (currentFragment instanceof DanhSachCongNoFragment) {
                startActivity(new Intent(getApplication(), TaoCongNoActivity.class));
            } else if (currentFragment instanceof DanhSachKhachHangFragment) {
                startActivity(new Intent(getApplication(), ChiTietKhachHangActivity.class));
            } else if (currentFragment instanceof QuanLyGuiBanFragment) {
                startActivity(new Intent(getApplication(), ThemMatHangGuiBanActivity.class));
            } else if (currentFragment instanceof KeHoachKhachHangFragment) {
                startActivity(new Intent(getApplication(), LapKeHoachKhachHangActivity.class));
            } else if (currentFragment instanceof DanhSachTinNhanFragment) {
                startActivity(new Intent(getApplication(), ChiTietSoanTinNhanCuActivity.class));
            } else if (currentFragment instanceof DanhSachLichHenFragment) {
                Intent intent=new Intent(getApplication(), LichHenActivity.class);
                intent.putExtra("type",0);
                startActivity(intent );
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.trangchu);
                replaceFragment(new TrangchuFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_sms:
                menuRight.findItem(R.id.nav_add).setVisible(true);
                getSupportActionBar().setTitle(R.string.tinnhan);
                replaceFragment(new DanhSachTinNhanFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_lichhen:
                menuRight.findItem(R.id.nav_add).setVisible(true);
                getSupportActionBar().setTitle(R.string.lichhen);
                replaceFragment(new DanhSachLichHenFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_vaoradiem:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                if(!flagobj.VDOBJ.getStatus()){
                getSupportActionBar().setTitle(getString(R.string.vaodiem));
                replaceFragment(new CheckInFragment());

                }else {
                    getSupportActionBar().setTitle(getString(R.string.radiem));
                    replaceFragment(new CheckOutFragment());
                }
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_takephoto:
                menuRight.findItem(R.id.nav_add).setVisible(true);
                getSupportActionBar().setTitle(getString(R.string.danhsachalbum));
                replaceFragment(new DanhSachAlbumFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_customer:
                updateMenuItemParentHide();
                updateMenuItemSubCustomerVisibly();
                break;
            case R.id.nav_schedule:
                updateMenuItemParentHide();
                updateMenuItemSubSchedulerVisibly();
                break;
            case R.id.nav_report:
                updateMenuItemParentHide();
                updateMenuItemSubReportVisibly();
                break;
            case R.id.nav_backkhachhang:
                updateMenuItemParentVisibly();
                updateMenuItemSubHide();
                break;
            case R.id.nav_kehoachback:
                updateMenuItemParentVisibly();
                updateMenuItemSubHide();
                break;
            case R.id.nav_baocaoback:
                updateMenuItemParentVisibly();
                updateMenuItemSubHide();
                break;
            case R.id.nav_setupback:
                updateMenuItemParentVisibly();
                updateMenuItemSubHide();
                break;
            case R.id.nav_taodonhang:
                menuRight.findItem(R.id.nav_add).setVisible(true);
                getSupportActionBar().setTitle(getString(R.string.danhsachdonhang));
                replaceFragment(new DanhSachDonHangFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_xulydonhang:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(getString(R.string.danhsachxulydonhang));
                replaceFragment(new DanhSachXuLyDonHangFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_mathang:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.danhsachmathang);
                replaceFragment(new DanhSachMatHangFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_phanhoi:
                menuRight.findItem(R.id.nav_add).setVisible(true);
                getSupportActionBar().setTitle(getString(R.string.danhsachphanhoi));
                replaceFragment(new DanhSachPhanHoiFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_ghichu:
                menuRight.findItem(R.id.nav_add).setVisible(true);
                getSupportActionBar().setTitle(getString(R.string.ghichu));
                replaceFragment(new DanhSachGhiChuFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_thuhoicongno:
                menuRight.findItem(R.id.nav_add).setVisible(true);
                getSupportActionBar().setTitle(getString(R.string.congno));
                replaceFragment(new DanhSachCongNoFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_khuyenmai:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.danhsachkhuyenmai);
                replaceFragment(new DanhSachKhuyenMaiFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_danhsachkhachhang:
                menuRight.findItem(R.id.nav_add).setVisible(true);
                getSupportActionBar().setTitle(R.string.danhsachkhachang);
                replaceFragment(new DanhSachKhachHangFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_quanlyguiban:
                menuRight.findItem(R.id.nav_add).setVisible(true);
                getSupportActionBar().setTitle(R.string.guiban);
                replaceFragment(new QuanLyGuiBanFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_kehoachkhachhang:
                menuRight.findItem(R.id.nav_add).setVisible(true);
                getSupportActionBar().setTitle(R.string.kehoachkhachhang);
                replaceFragment(new KeHoachKhachHangFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_kehoachtuyen:
                menuRight.findItem(R.id.nav_add).setVisible(true);
                getSupportActionBar().setTitle(R.string.kehoachtuyen);
                replaceFragment(new KeHoachTuyenFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_kehoachkhuvuc:
                menuRight.findItem(R.id.nav_add).setVisible(true);
                getSupportActionBar().setTitle(R.string.kehoachkhuvuc);
                replaceFragment(new KeHoachKhuVucFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_giaoviec:
                menuRight.findItem(R.id.nav_add).setVisible(true);
                getSupportActionBar().setTitle(R.string.congviec);
                replaceFragment(new DanhSachGiaoViecFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_phienlamviec:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.phienlamviec);
                replaceFragment(new BaoCaoPhienLamViecFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_lichsudichuyen:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.baocaolichsudichuyen);
                replaceFragment(new BaoCaoLichSuDichuyenFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_lichsuvaoradiem:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.baocaolichsudichuyen);
                replaceFragment(new BaoCaoLichSuVaoRaDiemFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_baocaokpi:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.baocaokpi);
                replaceFragment(new BaoCaoKPIFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_baocaokpidonhang:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.baocaokpidonhang);
                replaceFragment(new BaoCaoKPIDonHangFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_baocaoguiban:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.baocaoguiban);
                replaceFragment(new BaoCaoGuiBanFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_baocaodoanhthu:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.baocaodoanhthu);
                replaceFragment(new BaoCaoDoanhThuFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_baocaomathang:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.baocaomathang);
                replaceFragment(new BaoCaoMatHangFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_baocaogiaohang:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.baocaogiaohang);
                replaceFragment(new BaoCaoLichSuGiaoHangFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_baocaothanhtoan:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.baocaothanhtoan);
                replaceFragment(new BaoCaoThanhToanFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_setup:
                updateMenuItemParentHide();
                updateMenuItemSubSetupVisibly();
                break;
            case R.id.nav_cauhinh:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.cauhinh);
                replaceFragment(new SetupFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_doimatkhau:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.doimatkhau);
                replaceFragment(new DoiMatKhauFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_lienhe:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.thongtinlienhe);
                replaceFragment(new LienHeFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_huongdansudung:
                menuRight.findItem(R.id.nav_add).setVisible(false);
                getSupportActionBar().setTitle(R.string.huongdansudung);
                replaceFragment(new HuongDanFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;

        }
        return true;
    }

    DrawerLayout drawer;

    private void updateMenuItemParentVisibly() {
        Menu menu = navigationView.getMenu();
     
        menu.setGroupVisible(R.id.groupCha, true);

    }

    private void updateMenuItemParentHide() {
        Menu menu = navigationView.getMenu();
        menu.setGroupVisible(R.id.groupCha, false);
    }

    private void updateMenuItemSubHide() {
        Menu menu = navigationView.getMenu();
        menu.setGroupVisible(R.id.groupkehoach, false);
        menu.setGroupVisible(R.id.groupkhachhang, false);
        menu.setGroupVisible(R.id.groupbaocao, false);
        menu.setGroupVisible(R.id.groupsetup, false);
    }

    private void updateMenuItemSubReportVisibly() {
        Menu menu = navigationView.getMenu();
        menu.setGroupVisible(R.id.groupbaocao, true);
    }
    private void updateMenuItemSubSetupVisibly() {
        Menu menu = navigationView.getMenu();
        menu.setGroupVisible(R.id.groupsetup, true);
    }

    private void updateMenuItemSubSchedulerVisibly() {
        Menu menu = navigationView.getMenu();
        menu.setGroupVisible(R.id.groupkehoach, true);
    }

    private void updateMenuItemSubCustomerVisibly() {

        Menu menu = navigationView.getMenu();
        menu.setGroupVisible(R.id.groupkhachhang, true);
    }

    FragmentTransaction ft;

    private void replaceFragment(Fragment newFragment) {
        try {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fl_contain, newFragment)
                    .commit();
        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(),"loidelau: "+e.toString(),Toast.LENGTH_LONG).show();
            Log.e("loidelau", e.toString());
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Common.Current_Activity=MainActivity.this;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==10){//AlbumActivity
            navigationView.getMenu().findItem(R.id.nav_takephoto).setChecked(true);
            getSupportActionBar().setTitle(getString(R.string.danhsachalbum));
            replaceFragment(new DanhSachAlbumFragment());
        }else if(resultCode==12){
            navigationView.getMenu().findItem(R.id.nav_lichhen).setChecked(true);
            getSupportActionBar().setTitle(getString(R.string.lichhen));
            replaceFragment(new DanhSachLichHenFragment());
        }
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fl_contain);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}