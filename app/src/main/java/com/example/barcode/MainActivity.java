package com.example.barcode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class MainActivity extends AppCompatActivity {
    int REQUEST_CAMERA_CODE = 182;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askCameraPermission();
    }
    public void abrirLeitor(View v){
        askCameraPermission();
        Intent tela = new Intent(this, LeitorActivity.class);
        startActivityForResult(tela, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            String tipoBarcode = data.getStringExtra("tipo");
            String barCode = data.getStringExtra("codigo");

            TextView tipoCodigo = (TextView) findViewById(R.id.txtTipo);
            tipoCodigo.setText(tipoBarcode);

            TextView valorCodigo = (TextView) findViewById(R.id.txtCode);
            valorCodigo.setText(barCode);
        }
    }

    private void askCameraPermission(){
        EasyPermissions.requestPermissions(
                new PermissionRequest.Builder(this, REQUEST_CAMERA_CODE, Manifest.permission.CAMERA)
                .setRationale("A permissão de uso de câmera é necessária para que o aplicativo funcione")
                .setPositiveButtonText("Aceitar")
                .setNegativeButtonText("Cancelar")
                .build());
    }

}
