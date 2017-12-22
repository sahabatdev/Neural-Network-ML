package com.sahabatdeveloper.neuralnetwork;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView{
    Spinner spnTipeOperator;
    Button btnProsesData, btnTesting;
    MainPresenter mainPresenter;
    TextView tvData;
    LinearLayout linearLayout;
    ScrollView scrool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this,this);
        spnTipeOperator = (Spinner)findViewById(R.id.spn_tipe_operator);
        btnProsesData = (Button)findViewById(R.id.btn_proses_data);
        tvData = (TextView)findViewById(R.id.tv_data);
        linearLayout = (LinearLayout)findViewById(R.id.linear_main);
        scrool = (ScrollView) findViewById(R.id.scrool);
        btnTesting = new Button(this);
        btnTesting.setText("TESTING");
        btnTesting.setTextColor(getResources().getColor(android.R.color.white));
        btnTesting.setBackgroundColor(getResources().getColor(R.color.colorAccent));


        spnTipeOperator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                linearLayout.removeAllViews();
                mainPresenter.tampilData(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnProsesData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.generateBerat();
                mainPresenter.prosesData();
                mainPresenter.menampilkanBerat();
                scrool.fullScroll(View.FOCUS_DOWN);
                linearLayout.addView(btnTesting);
            }
        });

        btnTesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.prosesTesting();
            }
        });
    }


    @Override
    public void showData(String data) {
        tvData.setText(data);
    }

    @Override
    public void onShowProses(String proses) {
        TextView tvProses = new TextView(this);
        tvProses.setText(proses);
        tvProses.setTextColor(getResources().getColor(android.R.color.black));
        linearLayout.addView(tvProses);
    }

    @Override
    public void onShowInputTesting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("MASUKKAN TESTING");
        final EditText etTesting = new EditText(this);
        etTesting.setHint("Masukkan Testing X,Y");
        builder.setView(etTesting);
        builder.setPositiveButton("CARI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mainPresenter.menghitungTesting(etTesting.getText().toString());
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onShowHasilTesting(String hasil) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("HASIL");
        builder.setMessage(hasil);
        builder.setPositiveButton("CARI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
