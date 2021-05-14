package com.example.matrixtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.zip.GZIPOutputStream;

public class MainActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    Adapter recycleAdapter;
    GoAdapter recycleGoAdapter;
    Button btnIncrease,btnDecrease,btnGenerate,btnExcute;
    TextView row, col, startPoint, changeStartPoint;
    List<Item> data;
    int Gmain[] =new int[100],dem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        changeStartPoint.setEnabled(false);
        btnGenerate.setOnClickListener(view -> {
            data= new ArrayList<>();
            initDataItem(Integer.parseInt(col.getText().toString()));
            int numberOfColumns = Integer.parseInt(col.getText().toString());
            recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
            recycleAdapter = new Adapter(data, this, numberOfColumns,MainActivity.this);
            recyclerView.setAdapter(recycleAdapter);
            changeStartPoint.setEnabled(true);
        });
        btnIncrease.setOnClickListener(view -> {
            int k = Integer.parseInt(row.getText().toString())+1;
            if (k>0){
                btnGenerate.setEnabled(true);
                btnDecrease.setEnabled(true);
            }
            if (k==16)  btnIncrease.setEnabled(false);
            row.setText(""+k);
            col.setText(""+k);
        });
        btnDecrease.setOnClickListener(view -> {
            int k = Integer.parseInt(row.getText().toString())-1;
            if (k==0) {
                btnGenerate.setEnabled(false);
                btnDecrease.setEnabled(false);
            }
            if (k<16)  btnIncrease.setEnabled(true);
            else btnGenerate.setEnabled(true);
            row.setText(""+k);
            col.setText(""+k);
        });
        changeStartPoint.setOnClickListener(view -> {
            OpenDialogClicked();
        });
        btnExcute.setOnClickListener(view -> {
            int numberOfColumns = Integer.parseInt(col.getText().toString());
            recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
            recycleGoAdapter = new GoAdapter(data, this, dem,Gmain,Integer.parseInt(col.getText().toString()));
            recyclerView.setAdapter(recycleGoAdapter);

        });
    }
    private void init(){
        recyclerView=findViewById(R.id.recycleView);
        btnIncrease=findViewById(R.id.buttonIncrease);
        btnDecrease=findViewById(R.id.buttonDecrease);
        row=findViewById(R.id.row);
        col=findViewById(R.id.col);
        startPoint=findViewById(R.id.pointBegin);
        changeStartPoint=findViewById(R.id.SelectPoint);
        btnGenerate=findViewById(R.id.btnGenerate);
        btnExcute=findViewById(R.id.excuteBtn);
    }
    private void initDataItem(int n){
        for (int i=0;i<n*n;i++){
            data.add(new Item(i));
        }
    }
    private void OpenDialogClicked()  {
        Custom_Dialog_Sucess dialog_sucess = new Custom_Dialog_Sucess (this,MainActivity.this,Integer.parseInt(col.getText().toString())) ;
        dialog_sucess.show();
        dialog_sucess.setCancelable(false);
    }
}