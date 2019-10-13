package vn.lachongmedia.appnv.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerBaoCaoDoanhThu;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;

/**
 * Created by tungda .
 */
public class BaoCaoDoanhThuFragment extends Fragment {
    public BaoCaoDoanhThuFragment() {

    }
    RecyclerView recyclerViewBaoCaoDoanhThu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_baocaodoanhthu, container, false);
     /*   MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.maps);
*/
        recyclerViewBaoCaoDoanhThu = rootView.findViewById(R.id.rvBaoCaodoanhThu);
        //recyclerViewCongNo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));

        ArrayList<CuaHang> cuaHangs=new ArrayList<>();
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        AdapterRecyclerBaoCaoDoanhThu adapterRecyclerDanhSachCuaHangCheckIn=new AdapterRecyclerBaoCaoDoanhThu(getActivity(),cuaHangs);
        recyclerViewBaoCaoDoanhThu.setAdapter(adapterRecyclerDanhSachCuaHangCheckIn);
        recyclerViewBaoCaoDoanhThu.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterRecyclerDanhSachCuaHangCheckIn.setOnItemClickedListener(new AdapterRecyclerBaoCaoDoanhThu.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
               // startActivity(new Intent(getActivity(), LichHenActivity.class));
            }
        });
        return rootView;
    }



}
