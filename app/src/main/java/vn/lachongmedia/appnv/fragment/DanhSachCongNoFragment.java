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
import vn.lachongmedia.appnv.activity.ChiTietCongNoActivity;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerCongNo;
import vn.lachongmedia.appnv.object.CuaHang;

import java.util.ArrayList;

/**
 * Created by tungda .
 */
public class DanhSachCongNoFragment extends Fragment {
    public DanhSachCongNoFragment() {

    }

    RecyclerView recyclerViewCongNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_danhsachcongno, container, false);
     /*   MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.maps);
*/
        recyclerViewCongNo = rootView.findViewById(R.id.rvDanhSachCongNo);
        //recyclerViewCongNo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        recyclerViewCongNo.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        final AdapterRecyclerCongNo adapterRecyclerDanhSachCongNo = new AdapterRecyclerCongNo(getActivity(), cuaHangs);
        recyclerViewCongNo.setAdapter(adapterRecyclerDanhSachCongNo);
        adapterRecyclerDanhSachCongNo.setOnItemClickedListener(new AdapterRecyclerCongNo.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
              /*  if (cuaHangs.get(postion).getType() == 0) {
                    cuaHangs.get(postion).setType(1);
                }else {
                    cuaHangs.get(postion).setType(0);
                }
                adapterRecyclerDanhSachPhanHoi.notifyDataSetChanged();*/
              startActivity(new Intent(getContext(), ChiTietCongNoActivity.class));
            }
        });
        return rootView;
    }


}
