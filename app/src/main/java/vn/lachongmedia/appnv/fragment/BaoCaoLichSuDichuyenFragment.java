package vn.lachongmedia.appnv.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.R;

/**
 * Created by tungda .
 */
public class BaoCaoLichSuDichuyenFragment extends Fragment {
    public BaoCaoLichSuDichuyenFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_baocaolichsudichuyen, container, false);
     /*   MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.maps);
*/

        return rootView;
    }



}
