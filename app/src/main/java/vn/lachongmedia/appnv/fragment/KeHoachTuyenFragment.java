package vn.lachongmedia.appnv.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.activity.ThongTinTuyenActivity;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerKeHoachTuyen;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;

/**
 * Created by tungda .
 */
public class KeHoachTuyenFragment extends Fragment {
    public KeHoachTuyenFragment() {

    }
    RecyclerView recyclerViewAlbum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kehoachtuyen, container, false);
     /*   MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.maps);
*/
        recyclerViewAlbum = rootView.findViewById(R.id.rvDanhSachTuyen);
        //recyclerViewCongNo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        recyclerViewAlbum.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<CuaHang> cuaHangs=new ArrayList<>();
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        AdapterRecyclerKeHoachTuyen adapterRecyclerKeHoachKhachhang=new AdapterRecyclerKeHoachTuyen(getActivity(),cuaHangs);
        recyclerViewAlbum.setAdapter(adapterRecyclerKeHoachKhachhang);
        adapterRecyclerKeHoachKhachhang.setOnItemClickedListener(new AdapterRecyclerKeHoachTuyen.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
                startActivity(new Intent(getActivity(), ThongTinTuyenActivity.class));
            }
        });
        return rootView;
    }

}
