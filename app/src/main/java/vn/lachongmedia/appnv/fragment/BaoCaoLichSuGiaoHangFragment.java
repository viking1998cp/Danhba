package vn.lachongmedia.appnv.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerBaoCaoLichSuGiaoHang;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;
/**
 * Created by tungda .
 */
public class BaoCaoLichSuGiaoHangFragment extends Fragment {
    public BaoCaoLichSuGiaoHangFragment() {

    }
    RecyclerView recyclerViewBaoCaoLichSuGiaoHang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_baocaolichsugiaohang, container, false);
     /*   MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.maps);
*/
        recyclerViewBaoCaoLichSuGiaoHang = rootView.findViewById(R.id.rvBaoCaoLichSuGiaoHang);
        //recyclerViewCongNo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));

        ArrayList<CuaHang> cuaHangs=new ArrayList<>();
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        AdapterRecyclerBaoCaoLichSuGiaoHang adapterRecyclerDanhSachCuaHangCheckIn=new AdapterRecyclerBaoCaoLichSuGiaoHang(getActivity(),cuaHangs);
        recyclerViewBaoCaoLichSuGiaoHang.setAdapter(adapterRecyclerDanhSachCuaHangCheckIn);
        recyclerViewBaoCaoLichSuGiaoHang.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterRecyclerDanhSachCuaHangCheckIn.setOnItemClickedListener(new AdapterRecyclerBaoCaoLichSuGiaoHang.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
               // startActivity(new Intent(getActivity(), LichHenActivity.class));
            }
        });
        return rootView;
    }



}
