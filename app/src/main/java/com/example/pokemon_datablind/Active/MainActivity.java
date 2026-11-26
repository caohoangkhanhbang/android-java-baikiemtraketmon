package com.example.pokemon_datablind.Active;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.pokemon_datablind.R;

public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Nếu có kết nối internet, chuyển sang màn hình danh sách Pokemon
        if (isInternetAvailable1()) {
            Intent intent = new Intent(MainActivity.this, ViewListPokemon.class);
            startActivity(intent);
            finish();
        } else {
            // Nếu không có kết nối internet, hiển thị popup thông báo lỗi
//            showAlert();
//            showNoInternetDialog();
            dialogCustom();
        }
    }

    public void showAlert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Không có Internet");
        builder.setCancelable(false);
        builder.setPositiveButton("Cài đặt", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Thoat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create();
    }


    private boolean isInternetAvailable1() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // Phương thức hiển thị popup thông báo lỗi khi không có kết nối internet và mở cài đặt
    private void showNoInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Không có kết nối internet. Vui lòng kiểm tra lại kết nối của bạn.")
                .setCancelable(false)
                .setPositiveButton("Cài đặt", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Mở cài đặt kết nối
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Thoát ứng dụng
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void dialogCustom()
    {
        setContentView(R.layout.exit);
        findViewById(R.id.comfirm).setOnClickListener(suKien -> {
            finish();
        });
        findViewById(R.id.closePopup).setOnClickListener(suKien -> {
            finish();
        });
    }
}