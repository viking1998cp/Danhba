package vn.lachongmedia.appnv.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.activity.LichHenActivity;
import vn.lachongmedia.appnv.adapter.AdapterReCyclerSwipeLichHen;
import vn.lachongmedia.appnv.databinding.FragmentDanhsachlichhenBinding;
import vn.lachongmedia.appnv.object.LichHenObject;
import vn.lachongmedia.appnv.viewmodel.LichHenViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tungda .
 */
public class DanhSachLichHenFragment extends Fragment {
    public DanhSachLichHenFragment() {

    }

    FragmentDanhsachlichhenBinding binding;
    LichHenViewModel lichHenViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_danhsachlichhen, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_danhsachlichhen, container, false);
        lichHenViewModel = ViewModelProviders.of(this).get(LichHenViewModel.class);
        binding.rvDanhSachLichHen.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.tvTuNgay.setText(Common.getNgayHienTai());
        binding.tvDenNgay.setText(Common.getNgayHienTai());
        getDanhSachLichHen();
        binding.tvTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker( binding.tvTuNgay);
            }
        });
        binding.tvDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker( binding.tvDenNgay);
            }
        });
        binding.pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDanhSachLichHen();
            }
        });
        return binding.getRoot();
    }
    private void datePicker(final TextView edCalendar) {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date_time = "";
                        String day = "";
                        if (dayOfMonth > 9) {
                            day = "" + dayOfMonth;
                        } else {
                            day = "0" + dayOfMonth;
                        }
                        if (monthOfYear >= 9) {
                            date_time = day + "/" + (monthOfYear + 1) + "/" + year;
                        } else {
                            date_time = day + "/0" + (monthOfYear + 1) + "/" + year;
                        }

                        edCalendar.setText(date_time);
                        getDanhSachLichHen();


                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }
    private void getDanhSachLichHen() {
        try {
            binding.pullToRefresh.setRefreshing(true);
            Map<String, String> params = new HashMap<>();
            params.put("token", Common.getToken());
            params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
            params.put("type", "danhsach");
            params.put("tungay", binding.tvTuNgay.getText().toString().replace("/","-"));
            params.put("denngay", binding.tvDenNgay.getText().toString().replace("/","-"));
            lichHenViewModel.getKeHoachTrongNgay(params).observe(getActivity(), new Observer<ArrayList<LichHenObject>>() {
                @Override
                public void onChanged(final ArrayList<LichHenObject> lichHenObjects) {
                    binding.pullToRefresh.setRefreshing(false);
                    if (lichHenObjects != null && lichHenObjects.size() > 0) {
                        AdapterReCyclerSwipeLichHen adapter = new AdapterReCyclerSwipeLichHen(lichHenObjects);
                        binding.rvDanhSachLichHen.setAdapter(adapter);
                        adapter.setOnItemClickedListener(new AdapterReCyclerSwipeLichHen.OnItemClickedListener() {
                            @Override
                            public void onItemClick(int postion, View v) {
                                //startActivity(new Intent(getActivity(), LichHenActivity.class));
                                Intent intent = new Intent(getContext(), LichHenActivity.class);
                                intent.putExtra("type",1);
                                intent.putExtra("lichhen", lichHenObjects.get(postion));
                                startActivityForResult(intent, 1);
                            }
                        });
                    }else {
                        AdapterReCyclerSwipeLichHen adapter = new AdapterReCyclerSwipeLichHen(new ArrayList<LichHenObject>());
                        binding.rvDanhSachLichHen.setAdapter(adapter);
                    }
                }
            });
        } catch (Exception e) {
            binding.pullToRefresh.setRefreshing(false);
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.pullToRefresh.setRefreshing(false);
    }
}
