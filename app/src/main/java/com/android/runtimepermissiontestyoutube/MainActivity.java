package com.android.runtimepermissiontestyoutube;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    String[] required_permissions = new String[]
            {
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.RECORD_AUDIO
            };
    ImageView img_deny_call, img_given_call, img_deny_contacts, img_given_contacts, img_deny_audio, img_given_audio;
    Button btn_check_all_permissions;
    boolean is_call_phone_permitted = false;
    boolean is_read_contacts_permitted = false;
    boolean is_record_audio_permitted = false;
    boolean is_all_permission_requested = false;
    String TAG = "permission";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        img_deny_call = (ImageView) findViewById(R.id.img_deny_call);
        img_given_call = (ImageView) findViewById(R.id.img_given_call);

        img_deny_contacts = (ImageView) findViewById(R.id.img_deny_contacts);
        img_given_contacts = (ImageView) findViewById(R.id.img_given_contacts);

        img_deny_audio = (ImageView) findViewById(R.id.img_deny_audio);
        img_given_audio = (ImageView) findViewById(R.id.img_given_audio);

        btn_check_all_permissions = (Button) findViewById(R.id.button);

        btn_check_all_permissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        requestPermissions();


    }

    public void requestPermissions() {

        if(!is_call_phone_permitted){
            requestPermissionCallPhone();
        }
    }


    public boolean allPermissionResultCheck() {
        return is_call_phone_permitted && is_read_contacts_permitted && is_record_audio_permitted;
    }

    public void requestPermissionCallPhone() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, required_permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, required_permissions[0] + "Granted");

            is_call_phone_permitted = true;
            img_deny_call.setVisibility(View.GONE);
            img_given_call.setVisibility(View.VISIBLE);
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                Log.d(TAG, required_permissions[0] + "2nd time Dont allow handle here");
            } else {
                Log.d(TAG, required_permissions[0] + "Dont allow handle here");
            }
            //===we handle both byh below code
            request_permission_luncher_call_phone.launch(required_permissions[0]);
        }

    }

    private ActivityResultLauncher<String> request_permission_luncher_call_phone=
    registerForActivityResult(new ActivityResultContracts.RequestPermission(),isGranted->{
        if(isGranted) {
            Log.d(TAG, required_permissions[0] + "Allowed");
            is_call_phone_permitted = true;
            img_deny_call.setVisibility(View.GONE);
            img_given_call.setVisibility(View.VISIBLE);
        }else{
            Log.d(TAG, required_permissions[0] + "Allowed");
            is_call_phone_permitted = false;

        }

    });



















}