package vn.lachongmedia.appnv.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerBaoCaoGuiBanKhachHang;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;
/**
 * Created by tungda .
 */
public class BaoCaoGuiBanFragment extends Fragment {
    public BaoCaoGuiBanFragment() {

    }
    RecyclerView recyclerViewBaoCaoGuiBan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_baocaoguiban, container, false);
     /*   MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.maps);
*/
        recyclerViewBaoCaoGuiBan = rootView.findViewById(R.id.rvBaoCaoGuiBan);
        //recyclerViewCongNo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));

        ArrayList<CuaHang> cuaHangs=new ArrayList<>();
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        AdapterRecyclerBaoCaoGuiBanKhachHang adapterRecyclerDanhSachCuaHangCheckIn=new AdapterRecyclerBaoCaoGuiBanKhachHang(getActivity(),cuaHangs);
        recyclerViewBaoCaoGuiBan.setAdapter(adapterRecyclerDanhSachCuaHangCheckIn);
        recyclerViewBaoCaoGuiBan.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterRecyclerDanhSachCuaHangCheckIn.setOnItemClickedListener(new AdapterRecyclerBaoCaoGuiBanKhachHang.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
               // startActivity(new Intent(getActivity(), LichHenActivity.class));
            }
        });
        return rootView;
    }



}
