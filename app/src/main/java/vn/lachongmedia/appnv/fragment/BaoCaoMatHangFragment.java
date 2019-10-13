package vn.lachongmedia.appnv.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerBaoCaoMatHang;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;

/**
 * Created by tungda .
 */
public class BaoCaoMatHangFragment extends Fragment {
    public BaoCaoMatHangFragment() {

    }
    RecyclerView recyclerViewBaoCaoMatHang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_baocaomathang, container, false);
     /*   MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.maps);
*/
        recyclerViewBaoCaoMatHang = rootView.findViewById(R.id.rvBaoCaoMatHang);
        //recyclerViewCongNo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));

        ArrayList<CuaHang> cuaHangs=new ArrayList<>();
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        cuaHangs.add(new CuaHang());
        AdapterRecyclerBaoCaoMatHang adapterRecyclerBaoCaoMatHang=new AdapterRecyclerBaoCaoMatHang(getActivity(),cuaHangs);
        recyclerViewBaoCaoMatHang.setAdapter(adapterRecyclerBaoCaoMatHang);
        recyclerViewBaoCaoMatHang.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterRecyclerBaoCaoMatHang.setOnItemClickedListener(new AdapterRecyclerBaoCaoMatHang.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
               // startActivity(new Intent(getActivity(), LichHenActivity.class));
            }
        });
        return rootView;
    }



}
