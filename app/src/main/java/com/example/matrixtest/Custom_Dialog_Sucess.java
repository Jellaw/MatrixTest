package com.example.matrixtest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class Custom_Dialog_Sucess extends Dialog {
    private Button done_btn;
    private EditText edtPointStart;
    MainActivity mainActivity;
    public Context context;
    int size;
    public Custom_Dialog_Sucess(@NonNull Context context, MainActivity mainActivity,int size) {
        super(context);
        this.context = context;
        this.mainActivity=mainActivity;
        this.size=size;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_choosepointstart);

        this.done_btn = findViewById(R.id.btnOK);
        this.edtPointStart = findViewById(R.id.edt_start);
        edtPointStart.setText(mainActivity.startPoint.getText());

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int check=0;
                    String[] words = edtPointStart.getText().toString().split(",");
                    for (String w : words) {
                        if (Integer.parseInt(w) <= size) {
                        check=check+1;
                    }
                }
                    if (check==2){
                        mainActivity.startPoint.setText(edtPointStart.getText().toString());
                        buttonDoneClick();
                    } else Toast.makeText(getContext(), "Vi tri khong thoa man! Nhap lai.", Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    Toast.makeText(getContext(), "Loi: "+e, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void buttonDoneClick()  {
        this.dismiss();
    }
}
