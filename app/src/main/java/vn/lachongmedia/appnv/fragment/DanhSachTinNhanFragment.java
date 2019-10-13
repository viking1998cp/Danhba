package vn.lachongmedia.appnv.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.activity.LichHenActivity;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerDanhSachTinNhan;
import vn.lachongmedia.appnv.databinding.FragmentDanhsachtinnhanBinding;
import vn.lachongmedia.appnv.network.CommonRespon;
import vn.lachongmedia.appnv.object.TinNhanGroup;
import vn.lachongmedia.appnv.object.TinnhanOBJ;
import vn.lachongmedia.appnv.viewmodel.TinNhanConversionViewModel;
import vn.lachongmedia.appnv.viewmodel.TinNhanXuLyViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tungda .
 */
public class DanhSachTinNhanFragment extends Fragment {
    public DanhSachTinNhanFragment() {

    }

    private FragmentDanhsachtinnhanBinding binding;
    private TinNhanConversionViewModel tinNhanConversionViewModel;
    private TinNhanXuLyViewModel tinNhanXuLyViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tinNhanConversionViewModel = ViewModelProviders.of(this).get(TinNhanConversionViewModel.class);
        tinNhanXuLyViewModel=ViewModelProviders.of(this).get(TinNhanXuLyViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_danhsachtinnhan, container, false);
        binding.rvDanhSachTinNhan.setLayoutManager(new LinearLayoutManager(getActivity()));
        getDanhSachTinNhan();
        return binding.getRoot();
    }

    /*
     * api chưa có từ ngày đến ngày*/
    private void getDanhSachTinNhan() {
        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        tinNhanConversionViewModel.getTinNhanConversion(params).observe(getActivity(), new Observer<ArrayList<TinNhanGroup>>() {
            @Override
            public void onChanged(final ArrayList<TinNhanGroup> tinNhanGroups) {
                AdapterRecyclerDanhSachTinNhan adapterRecyclerDanhSachTinNhan = new AdapterRecyclerDanhSachTinNhan(tinNhanGroups);
                binding.rvDanhSachTinNhan.setAdapter(adapterRecyclerDanhSachTinNhan);
                adapterRecyclerDanhSachTinNhan.setOnItemClickedListener(new AdapterRecyclerDanhSachTinNhan.OnItemClickedListener() {
                    @Override
                    public void onItemClick(int postion, View v) {
                        ArrayList<TinnhanOBJ> listTinNhan = tinNhanGroups.get(postion).getListTinNhanGroup();
                        for (int i = 0; i < listTinNhan.size(); i++) {
                            if (listTinNhan.get(i).getLoaitinnhan() == 0 && listTinNhan.get(i).getTrangthai() == 0) {
                                //new TinNhanUpdateTask().execute(String.valueOf(listTinNhan.get(i).getId_tinnhan()));
                                xuLytinNhan(listTinNhan.get(i).getId_tinnhan());
                            }
                        }
                        startActivity(new Intent(getActivity(), LichHenActivity.class));
                    }
                });
            }
        });
    }
    private void xuLytinNhan(int idTinNhan){
        Map<String,String> params=new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        params.put("idtinnhan",""+idTinNhan);
        params.put("loai","capnhatdaxem");
        tinNhanXuLyViewModel.getTinNhanConversion(params).observe(getActivity(), new Observer<CommonRespon>() {
            @Override
            public void onChanged(CommonRespon commonRespon) {

            }
        });
    }


}
