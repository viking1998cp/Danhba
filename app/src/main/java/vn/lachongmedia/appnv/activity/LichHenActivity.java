package vn.lachongmedia.appnv.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import vn.lachongmedia.appnv.Common;
import vn.lachongmedia.appnv.R;
import vn.lachongmedia.appnv.SharedPrefs;
import vn.lachongmedia.appnv.databinding.ActivityLichhenBinding;
import vn.lachongmedia.appnv.network.CuaHang;
import vn.lachongmedia.appnv.object.LichHenObject;
import vn.lachongmedia.appnv.viewmodel.DanhSachCuaHangViewModel;
import vn.lachongmedia.appnv.viewmodel.LichHenViewModel;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tungda .
 */
public class LichHenActivity extends AppCompatActivity {
    ActivityLichhenBinding binding;
    LichHenViewModel lichHenViewModel;
    DanhSachCuaHangViewModel danhSachCuaHangViewModel;

    private CuaHang cuaHang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lichhen);
        lichHenViewModel = ViewModelProviders.of(this).get(LichHenViewModel.class);
        danhSachCuaHangViewModel = ViewModelProviders.of(this).get(DanhSachCuaHangViewModel.class);
        getExtra();
        back();
        binding.btKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ketThucLichHen();
            }
        });
        binding.btLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capNhatLichHen();
            }
        });
        binding.tvThoigian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(binding.tvThoigian);
            }
        });
        binding.btTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.edNoiDung.getText().toString().equals("")) {
                    if (cuaHang != null) {
                        taoLichHen();
                    }else {
                        Common.ShowToastLong(getString(R.string.bancanchonkhachhang));
                    }
                } else {
                    Common.ShowToastLong(getString(R.string.bancannhapnoidung));
                }
            }
        });
        binding.tvTenCuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(), ChonKhachHangActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    LichHenObject lichHenObject = new LichHenObject();

    private void getExtra() {
        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                int type = bundle.getInt("type", 0);
                if (type == 1) {
                    lichHenObject = (LichHenObject) bundle.getSerializable("lichhen");
                    if (lichHenObject != null) {
                        if (lichHenObject.getTrangthai() == 0) {
                            binding.btKetThuc.setVisibility(View.VISIBLE);
                            binding.btLuu.setVisibility(View.VISIBLE);
                        } else {
                            binding.btKetThuc.setVisibility(View.GONE);
                            binding.btLuu.setVisibility(View.GONE);
                        }
                        binding.tvTenCuaHang.setEnabled(false);
                        binding.btTao.setVisibility(View.GONE);
                        binding.btLuu.setVisibility(View.VISIBLE);
                        binding.btKetThuc.setVisibility(View.VISIBLE);
                        binding.tvTenCuaHang.setText(lichHenObject.getTenKhachHang());
                        binding.edNoiDung.setText(lichHenObject.getNoiDungHen());
                        binding.tvThoigian.setText(Common.formatChangePointDateTime(lichHenObject.getThoiGianHienThi()));
                        binding.edKetQua.setText(lichHenObject.getKetQua());
                    }
                } else if (type == 0) {
                    binding.tvTenCuaHang.setEnabled(true);
                    binding.btTao.setVisibility(View.VISIBLE);
                    binding.btLuu.setVisibility(View.GONE);
                    binding.btKetThuc.setVisibility(View.GONE);
                    //getDanhSachCuaHang();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void back() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void datePicker(final TextView tvThoiGian) {

        // Get Current Date
       /* final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(LichHenActivity.this,
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


                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();*/
        final Dialog dialog = new Dialog(LichHenActivity.this);
        dialog.setContentView(R.layout.dialog_date_time);
        final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePicker);
        final TimePicker timePicker = (TimePicker) dialog.findViewById(R.id.timePicker);
        Button bt_ok = (Button) dialog.findViewById(R.id.bt_dongy);
        Button bt_huy = (Button) dialog.findViewById(R.id.bt_huy);
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String dateTime = "";
                    if (datePicker.getDayOfMonth() < 10) {
                        dateTime = dateTime + "0" + String.valueOf(datePicker.getDayOfMonth());
                    } else {
                        dateTime = dateTime + String.valueOf(datePicker.getDayOfMonth());
                    }
                    if ((datePicker.getMonth() + 1) < 10) {
                        dateTime += "-" + "0" + String.valueOf(datePicker.getMonth() + 1);
                    } else {
                        dateTime += "-" + String.valueOf(datePicker.getMonth() + 1);
                    }
                    dateTime += "-" + String.valueOf(datePicker.getYear()) + " ";
                    if (timePicker.getCurrentHour() < 10) {
                        dateTime += "0" + String.valueOf(timePicker.getCurrentHour());
                    } else {
                        dateTime += String.valueOf(timePicker.getCurrentHour());
                    }
                    if (timePicker.getCurrentMinute() < 10) {
                        dateTime += ":" + "0" + String.valueOf(timePicker.getCurrentMinute()) + ":" + "00";
                    } else {
                        dateTime += ":" + String.valueOf(timePicker.getCurrentMinute()) + ":" + "00";
                    }
                    //MD5.showLog("testdatetime: " + dateTime);
                    if (Common.convertStringToDate3(dateTime).getTime() > System.currentTimeMillis()) {
                        try {
                            tvThoiGian.setText(Common.formatChangePointDateTime(dateTime));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_message_please_customer_bigger_current_time), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        bt_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setTitle(getResources().getString(R.string.title_appointment_schedule));
        dialog.show();
    }

   /* private void getDanhSachCuaHang() {
        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        danhSachCuaHangViewModel.getDanhSachCuaHang(params).observe(this, new Observer<ArrayList<CuaHang>>() {
            @Override
            public void onChanged(final ArrayList<CuaHang> cuaHangs) {
                ArrayAdapter adapterKhachHang = new ArrayAdapter(LichHenActivity.this,
                        android.R.layout.simple_spinner_item, cuaHangs);
                adapterKhachHang.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                binding.spKhachHang.setAdapter(adapterKhachHang);

                binding.spKhachHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        idkhachhang = cuaHangs.get(position).getIdcuahang();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }*/

    private void capNhatLichHen() {
        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        params.put("type", "capnhat");
        params.put("idlichhen", "" + lichHenObject.getiD_LichHen());
        params.put("idkhachhang", "" + lichHenObject.getiD_khachHang());
        params.put("noidung", URLEncoder.encode(binding.edNoiDung.getText().toString()));
        params.put("KetQua", URLEncoder.encode(binding.edKetQua.getText().toString()));
        params.put("thoigian", Common.formatChangePointDateTime2(binding.tvThoigian.getText().toString()));


        lichHenViewModel.getKeHoachTrongNgay(params).observe(this, new Observer<ArrayList<LichHenObject>>() {
            @Override
            public void onChanged(ArrayList<LichHenObject> lichHenObjects) {
                if (lichHenObjects.size() > 0) {
                    Common.ShowToastLong(getString(R.string.title_appointment_schedule_update_success));
                    setResult(12);
                    finish();
                }
            }
        });
    }

    private void ketThucLichHen() {
        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        params.put("type", "ketthuc");
        params.put("TrangThai", "1");
        params.put("idlichhen", "" + lichHenObject.getiD_LichHen());
        params.put("idkhachhang", "" + lichHenObject.getiD_khachHang());
        params.put("noidung", URLEncoder.encode(binding.edNoiDung.getText().toString()));
        params.put("KetQua", URLEncoder.encode(binding.edKetQua.getText().toString()));
        params.put("thoigian", Common.formatChangePointDateTime2(binding.tvThoigian.getText().toString()));


        lichHenViewModel.getKeHoachTrongNgay(params).observe(this, new Observer<ArrayList<LichHenObject>>() {
            @Override
            public void onChanged(ArrayList<LichHenObject> lichHenObjects) {
                if (lichHenObjects.size() > 0) {
                    Common.ShowToastLong(getString(R.string.title_appointment_schedule_update_success));
                    setResult(12);
                    finish();
                }
            }
        });
    }

    private void taoLichHen() {
        Map<String, String> params = new HashMap<>();
        params.put("token", Common.getToken());
        params.put("idnhanvien", "" + SharedPrefs.getInstance().get(Common.iDNhanVien, Integer.class));
        params.put("type", "them");
        params.put("idkhachhang", "" + cuaHang.getIdcuahang());
        params.put("noidung", URLEncoder.encode(binding.edNoiDung.getText().toString()));
        params.put("thoigian", Common.formatChangePointDateTime2(binding.tvThoigian.getText().toString()));
        lichHenViewModel.getKeHoachTrongNgay(params).observe(this, new Observer<ArrayList<LichHenObject>>() {
            @Override
            public void onChanged(ArrayList<LichHenObject> lichHenObjects) {
                if (lichHenObjects.size() > 0) {
                    Common.ShowToastLong(getString(R.string.toast_msg_create_schedule_succsess));
                    setResult(12);
                    finish();

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            try {
                cuaHang = (CuaHang) data.getSerializableExtra("cuahang");
                binding.tvTenCuaHang.setText(cuaHang.getTenCuaHang());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
