package com.retrofitdeneme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {
    private EditText etMusteriNo, etGirisKod;
    private Button btnGiris;
    private String musteriNo, girisKod;
    private RestInterface restInterface;
    private Retrofit restAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMusteriNo = (EditText) findViewById(R.id.etMusteriNo);
        etGirisKod = (EditText) findViewById(R.id.etGirisKod);
        btnGiris = (Button) findViewById(R.id.btnGiris);

        try {
            restAdapter = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.3/ywebservice/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            restInterface = restAdapter.create(RestInterface.class);
        } catch (Exception e) {
            Log.e("TAG", "Url hatalı!");
        }


        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musteriNo = etMusteriNo.getText().toString();
                girisKod = etGirisKod.getText().toString();

                Call<LoginPOJO> call = restInterface.login(musteriNo, girisKod);
                call.enqueue(new Callback<LoginPOJO>() {
                    @Override
                    public void onResponse(Call<LoginPOJO> call, Response<LoginPOJO> response) {
                        if (!response.body().getError()) {
                            if (response.body().getKod().equals(girisKod)) {
                                Toast.makeText(getApplicationContext(), "GİRİŞ BAŞARILI" +
                                        "\nGiriş Yapan:" + response.body().getAd() + " " + response.body().getSoyad() +
                                        "\nGiriş Kodu:" + response.body().getKod(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Hatalı Müşteri No/Giriş Kod.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginPOJO> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Sunucuya bağlanırken hata: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}

