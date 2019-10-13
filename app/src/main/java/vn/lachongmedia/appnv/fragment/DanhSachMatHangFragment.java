package vn.lachongmedia.appnv.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.activity.ChonDanhMucActivity;
import vn.lachongmedia.appnv.adapter.AdapterRecyclerDanhSachMatHangMatHang;
import vn.lachongmedia.appnv.databinding.FragmentDanhsachmathangBinding;
import vn.lachongmedia.appnv.network.mathang.DanhSachMatHangLoadMoreRespon;
import vn.lachongmedia.appnv.object.DanhmucOBJ;
import vn.lachongmedia.appnv.object.MatHang;
import vn.lachongmedia.appnv.viewmodel.DanhSachMatHangViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tungda .
 */
public class DanhSachMatHangFragment extends Fragment {
    public DanhSachMatHangFragment() {

    }

    private Handler mHander;
    FragmentDanhsachmathangBinding binding;
    AdapterRecyclerDanhSachMatHangMatHang adapter;
    ArrayList<MatHang> listMatHang = new ArrayList<>();
    DanhSachMatHangViewModel danhSachMatHangViewModel;
    private boolean isLoading = false;
    //default value for price and type price
    private int lastID = 0;
    private int endList = 1;
    private int priceFrom = 50;
    private int priceTo = 1000000;
    private int priceType = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_danhsachmathang, container, false);
        danhSachMatHangViewModel = ViewModelProviders.of(this).get(DanhSachMatHangViewModel.class);
        binding.rvDanhSachMatHang.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AdapterRecyclerDanhSachMatHangMatHang(listMatHang, 1);
        binding.rvDanhSachMatHang.setAdapter(adapter);
        mHander = new Handler();
        adapter.setOnItemClickedListener(new AdapterRecyclerDanhSachMatHangMatHang.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
                if (listMatHang.get(postion).getType() == 0) {
                    listMatHang.get(postion).setType(1);
                } else {
                    listMatHang.get(postion).setType(0);
                }
                adapter.notifyDataSetChanged();
            }
        });
        binding.pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLoad();
            }
        });

        binding.tvGiaTu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mHander.removeCallbacksAndMessages(null);
                mHander.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //limit lenght char for search  < 7
                                if (s.length() >= 7) {
                                    binding.tvGiaTu.setText(binding.tvGiaTu.getText().toString().substring(0, s.length() - 1));
                                    binding.tvGiaTu.setSelection(binding.tvGiaTu.length());
                                    Common.ShowToastLong(getString(R.string.vuotquagioihan));
                                } else {
                                    refreshLoad();
                                }
                            }
                        });

                    }
                }, 300);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.tvGiaDen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mHander.removeCallbacksAndMessages(null);
                mHander.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //limit lenght char for search < 7
                                if (s.length() >= 7) {
                                    binding.tvGiaDen.setText(binding.tvGiaDen.getText().toString().substring(0, s.length() - 1));
                                    binding.tvGiaDen.setSelection(binding.tvGiaDen.length());
                                    Common.ShowToastLong(getString(R.string.vuotquagioihan));
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    refreshLoad();
                                }
                            }
                        });

                    }
                }, 300);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.edTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    refreshLoad();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        loadMore();
        getDanhSachMatHang();
        initChondanhmuc();
        setHasOptionsMenu(true);

        return binding.getRoot();
    }


    private void refreshLoad() {

        if (binding.tvGiaTu.getText().toString().equals("")) {
            priceFrom = 0;

        } else {
            priceFrom = Integer.valueOf(binding.tvGiaTu.getText().toString());
        }
        if (binding.tvGiaDen.getText().toString().equals("")) {
            priceTo = 1000000;

        } else {
            priceTo = Integer.valueOf(binding.tvGiaDen.getText().toString());
        }
        Log.d("BBB", "refreshLoad: " + priceFrom + " To " + priceTo);
        // set type Price
        if (!(priceFrom == 0) && priceTo == 1000000) {
            // priceFrom < price < 1000000
            priceType = 2;
        } else if (priceFrom == 0 && !(priceTo == 1000000)) {
            // 0< price < priceTo
            priceType = 3;
        } else {
            // priceFrom < price < priceTo
            priceType = 1;
        }

        Log.d("BBB", "refreshLoad type: " + priceType);
        lastID = 0;
        endList = 1;
        isLoading = false;
        listMatHang.clear();
        adapter.notifyDataSetChanged();
        getDanhSachMatHang();
    }

    private void initChondanhmuc() {
        binding.tvChonDanhMuc.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChonDanhMucActivity.class);
            getActivity().startActivityForResult(intent, 1);
        });
    }

    private void getDanhSachMatHang() {
        binding.pullToRefresh.setRefreshing(true);
        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("giatu", String.valueOf(priceFrom));
        params.put("giaden", String.valueOf(priceTo));
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        params.put("lastid", "" + lastID);
        params.put("loctatca", "" + binding.edTimKiem.getText().toString());
        params.put("idkhachhang", "0");
        params.put("loaigia", String.valueOf(priceType));


        if (danhmucOBJ != null) {
            params.put("iddanhmuc", "" + danhmucOBJ.getiDDanhMuc());
        }
        danhSachMatHangViewModel.getDanhSachMatHang(params).observe(this, new Observer<DanhSachMatHangLoadMoreRespon>() {
            @Override
            public void onChanged(DanhSachMatHangLoadMoreRespon danhSachMatHangLoadMoreRespon) {
                binding.pullToRefresh.setRefreshing(false);
                try {
                    isLoading = false;
                    if (danhSachMatHangLoadMoreRespon != null) {
                        if (danhSachMatHangLoadMoreRespon.isStatus()) {

                            lastID = danhSachMatHangLoadMoreRespon.getLastid();
                            endList = danhSachMatHangLoadMoreRespon.getEndlist();
                            if (danhSachMatHangLoadMoreRespon.getListMatHang() != null && danhSachMatHangLoadMoreRespon.getListMatHang().size() > 0) {

                                listMatHang.addAll(danhSachMatHangLoadMoreRespon.getListMatHang());
                                adapter.notifyDataSetChanged();

                            } else {
                                Common.ShowToastLong(getString(R.string.message_no_data));
                            }
                        } else {
                            //Thêm do khi set tim kiem không thay đổi kịp
                            if (lastID == 0) {
                                listMatHang.clear();
                                adapter.notifyDataSetChanged();
                                ;
                            }
                            //Common.ShowToastLong(danhSachMatHangLoadMoreRespon.getMsg());
                        }
                    } else {

                    }
                } catch (Exception e) {
                    isLoading = false;
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadMore() {
        binding.rvDanhSachMatHang.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading && endList == 0) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listMatHang.size() - 1) {
                        //bottom of list!
                        getDanhSachMatHang();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private DanhmucOBJ danhmucOBJ;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            danhmucOBJ = (DanhmucOBJ) data.getSerializableExtra("danhmuc");
            String danhMucCha = "";
            try {
                danhMucCha = data.getStringExtra("ten_danhmuccha");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (danhmucOBJ.getiDDanhMuc() == -1) {
                binding.tvChonDanhMuc.setText(getResources().getString(R.string.tatca));
            } else if (danhmucOBJ.getiDDanhMuc() == 0) {
                binding.tvChonDanhMuc.setText(getResources().getString(R.string.khac));
            } else {
                if (danhmucOBJ.getTenDanhMuc().equals(getResources().getString(R.string.tatca))) {
                    binding.tvChonDanhMuc.setText(danhMucCha);
                } else {
                    binding.tvChonDanhMuc.setText(danhmucOBJ.getTenDanhMuc());
                }
            }
            refreshLoad();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.pullToRefresh.setRefreshing(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_danhsachmathang, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    int listType = 0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_Retail:
                if (listType == 0) {
                    listType = 1;
                    for (int i = 0; i < listMatHang.size(); i++) {
                        listMatHang.get(i).setType(1);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    listType = 0;
                    for (int i = 0; i < listMatHang.size(); i++) {
                        listMatHang.get(i).setType(0);
                    }
                    adapter.notifyDataSetChanged();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
