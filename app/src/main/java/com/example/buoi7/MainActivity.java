package com.example.buoi7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnInsert;
    EditText txtName,txtPrice,txtDes;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.L4RetroInsertBtnInsert);
        txtName = findViewById(R.id.L4RetroInserttxtName);
        txtPrice = findViewById(R.id.L4RetroInserttxtPrice);
        txtDes = findViewById(R.id.L4RetroInserttxtDes);
        txtResult = findViewById(R.id.L4RetroInsertResult);
        btnInsert.setOnClickListener(this);
    }
    public void insertData()
    {
        Prd prd = new Prd();
        prd.setName((txtName.getText().toString()));
        prd.setPrice(txtPrice.getText().toString());
        prd.setDescription((txtDes.getText().toString()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InsertInterfacePrd insertInterfacePrd
                = retrofit.create(InsertInterfacePrd.class);

        Call<ServerResPrd> call = insertInterfacePrd.insertPrd(prd.getName(),
                prd.getPrice(),prd.getDescription());

        call.enqueue(new Callback<ServerResPrd>() {
            @Override
            public void onResponse(Call<ServerResPrd> call, Response<ServerResPrd> response) {
                ServerResPrd serverResPrd = response.body();
                txtResult.setText(serverResPrd.getMessage());
            }

            @Override
            public void onFailure(Call<ServerResPrd> call, Throwable t) {
                txtResult.setText(t.getMessage());
            }
        });
    }
    @Override
    public void onClick(View v) {
        insertData();
    }
}