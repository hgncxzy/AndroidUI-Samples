package com.xzy.dialog.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.widget.jcdialog.DialogUtils;
import com.widget.jcdialog.utils.ToastUitl;
import com.widget.jcdialog.widget.JDAddressSeletor.BottomDialog;
import com.widget.jcdialog.widget.JDAddressSeletor.DefaultAddressProvider;
import com.widget.jcdialog.widget.JDAddressSeletor.OnAddressSelectedListener;
import com.widget.jcdialog.widget.pickerview.OptionsPickerView;
import com.widget.jcdialog.widget.pickerview.model.CityModel;
import com.widget.jcdialog.widget.pickerview.model.DistrictModel;
import com.widget.jcdialog.widget.pickerview.model.ProvinceModel;
import com.xzy.dialog.R;

import static com.widget.jcdialog.utils.ToastUitl.showToast;

/**
 * 地址联动选择器的用法。
 *
 * @author xzy
 */
public class AddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        handle();
    }

    private void handle() {
        findViewById(R.id.JDAddress)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final BottomDialog dialog1 = DialogUtils
                                .showAddressDialog(AddressActivity.this);
                        dialog1.setOnAddressSelectedListener(new OnAddressSelectedListener() {
                            @Override
                            public void onAddressSelected(ProvinceModel province,
                                                          CityModel city,
                                                          DistrictModel county) {
                                String s = (province == null ? "" : province.getName()) +
                                        (city == null ? "" : "\n" + city.getName()) +
                                        (county == null ? "" : "\n" + county.getName());
                                showToast(s);
                                dialog1.dismiss();
                            }
                        });
                    }
                });
        findViewById(R.id.address)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final DefaultAddressProvider addressProvider = new
                                DefaultAddressProvider(AddressActivity.this);
                        DialogUtils.showAddrPickView(AddressActivity.this,
                                "选择地址",
                                addressProvider,
                                new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int option2, int options3) {
                                        String tx = addressProvider.provideProvince().get(options1)
                                                + addressProvider.provideCities().get(options1).get(option2)
                                                + addressProvider.provideCounties().get(options1)
                                                .get(option2).get(options3).getPickerViewText()
                                                + addressProvider.provideZipCode()
                                                .get(addressProvider.provideCounties()
                                                        .get(options1).get(option2)
                                                        .get(options3).getPickerViewText());
                                        ToastUitl.showToast(tx);
                                    }
                                });
                    }
                });
    }
}
