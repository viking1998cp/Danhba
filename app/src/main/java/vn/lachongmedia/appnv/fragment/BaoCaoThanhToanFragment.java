package vn.lachongmedia.appnv.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerBaoCaoLichSuThanhToan;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;

/**
 * Created by tungda on 7/13/2019.
 */
public class BaoCaoThanhToanFragment  extends Fragment {
    public BaoCaoThanhToanFragment() {

    }
    RecyclerView recyclerViewBaoCaoLichSuThanhToan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_baocaolichsuthanhtoan, container, false);
     /*   MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.maps);
*/
        recyclerViewBaoCaoLichSuThanhToan = rootView.findViewById(R.id.rvBaoCaoLichSuThanhToan);
        //recyclerViewCongNo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));

        ArrayList<CuaHang> cuaHangs=new ArrayList<>();
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        AdapterRecyclerBaoCaoLichSuThanhToan adapterRecyclerBaoCaoMatHang=new AdapterRecyclerBaoCaoLichSuThanhToan(getActivity(),cuaHangs);
        recyclerViewBaoCaoLichSuThanhToan.setAdapter(adapterRecyclerBaoCaoMatHang);
        recyclerViewBaoCaoLichSuThanhToan.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterRecyclerBaoCaoMatHang.setOnItemClickedListener(new AdapterRecyclerBaoCaoLichSuThanhToan.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
                // startActivity(new Intent(getActivity(), LichHenActivity.class));
            }
        });
        return rootView;
    }



}