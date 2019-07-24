package com.jay.blowfishdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText et_to_encrypt;
    private EditText et_to_decrypt;
    private TextView tv_result;

    private String key = BuildConfig.APP_ID;

    private StringBuilder result = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        et_to_encrypt = findViewById(R.id.et_to_encrypt);
        et_to_decrypt = findViewById(R.id.et_to_decrypt);
        tv_result = findViewById(R.id.tv_result);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_encrypt:
                encrypt();
                break;
            case R.id.btn_decrypt:
                decrypt();
                break;
        }
    }

    private void encrypt() {
        if (!"".equals(et_to_encrypt.getText().toString().trim())){
            result.append(et_to_encrypt.getText().toString().trim());
            result.append("\n加密后：");
            String resultEncrypt = EncryptUtil.encrypt(et_to_encrypt.getText().toString().trim(),key);
            result.append(resultEncrypt);

            et_to_decrypt.setText(resultEncrypt);

            updateLog();
        }

    }

    private void decrypt() {
        if (!"".equals(et_to_decrypt.getText().toString().trim())){
            result.append(et_to_decrypt.getText().toString().trim());
            result.append("\n解密后：");
            String resultDecrypt = EncryptUtil.decrypt(et_to_decrypt.getText().toString().trim(),key);
            result.append(resultDecrypt);

            et_to_encrypt.setText(resultDecrypt);

            updateLog();
        }
    }

    private void updateLog(){
        result.append("\n");
        tv_result.setText(result.toString());
    }
}
