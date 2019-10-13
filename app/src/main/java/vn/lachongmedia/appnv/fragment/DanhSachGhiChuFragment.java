package vn.lachongmedia.appnv.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerDanhSachGhiChu;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;

/**
 * Created by tungda .
 */
public class DanhSachGhiChuFragment extends Fragment {
    public DanhSachGhiChuFragment() {

    }

    RecyclerView recyclerViewAlbum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_danhsachghichu, container, false);
     /*   MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.maps);
*/
        recyclerViewAlbum = rootView.findViewById(R.id.rvDanhSachKhachHang);
        //recyclerViewCongNo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        recyclerViewAlbum.setLayoutManager(new LinearLayoutManager(getActivity()));
        final ArrayList<CuaHang> cuaHangs = new ArrayList<>();
        CuaHang cuaHang = new CuaHang();
        cuaHang.setType(0);
        CuaHang cuaHangone = new CuaHang();
        cuaHangone.setType(1);
        cuaHangs.add(cuaHang);
        cuaHangs.add(cuaHangone);
        cuaHangs.add(cuaHangone);
        cuaHangs.add(cuaHangone);
        cuaHangs.add(cuaHang);
        cuaHangs.add(cuaHangone);
        cuaHangs.add(cuaHangone);
        final AdapterRecyclerDanhSachGhiChu adapterRecyclerDanhSachGhiChu = new AdapterRecyclerDanhSachGhiChu(getActivity(), cuaHangs);
        recyclerViewAlbum.setAdapter(adapterRecyclerDanhSachGhiChu);
        adapterRecyclerDanhSachGhiChu.setOnItemClickedListener(new AdapterRecyclerDanhSachGhiChu.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
              /*  if (cuaHangs.get(postion).getType() == 0) {
                    cuaHangs.get(postion).setType(1);
                }else {
                    cuaHangs.get(postion).setType(0);
                }
                adapterRecyclerDanhSachPhanHoi.notifyDataSetChanged();*/
            }
        });
        return rootView;
    }


}
