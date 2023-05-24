package com.android.runtimepermissiontestyoutube;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
               // requestPermissions();
                if(is_all_permission_requested){
                    Toast.makeText(MainActivity.this, "Granted All Permissions", Toast.LENGTH_SHORT).show();
                }else{
                    sendToSettingDialog();
                }

            }
        });

        requestPermissions();
    }

    public void requestPermissions() {

//        if (!is_call_phone_permitted) {
//            requestPermissionCallPhone();
//        }
//        if (!is_read_contacts_permitted) {
//            requestPermissionReadContact();
//        }


        if (!is_record_audio_permitted) {
            requestPermissionRecordAudio();
        }
    }


    public boolean allPermissionResultCheck() {
        return is_call_phone_permitted && is_read_contacts_permitted && is_record_audio_permitted;
    }

    /**************Getting Phone call permission ******************/
    public void requestPermissionCallPhone() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, required_permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, required_permissions[0] + "Granted");

            is_call_phone_permitted = true;
            img_deny_call.setVisibility(View.GONE);
            img_given_call.setVisibility(View.VISIBLE);

            if(is_read_contacts_permitted){
                requestPermissionReadContact();
            }

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

    private ActivityResultLauncher<String> request_permission_luncher_call_phone =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Log.d(TAG, required_permissions[0] + "Allowed");
                    is_call_phone_permitted = true;
                    img_deny_call.setVisibility(View.GONE);
                    img_given_call.setVisibility(View.VISIBLE);
                } else {
                    Log.d(TAG, required_permissions[0] + "Allowed");
                    is_call_phone_permitted = false;

                }

            });
   /**************Getting Phone call permission Ended ***************/



    /**************Getting Read Contact permission ******************/
    public void requestPermissionReadContact() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, required_permissions[1]) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, required_permissions[1] + "Granted");

            is_read_contacts_permitted = true;
            img_deny_contacts.setVisibility(View.GONE);
            img_deny_contacts.setVisibility(View.VISIBLE);
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                Log.d(TAG, required_permissions[1] + "2nd time Dont allow handle here");
            } else {
                Log.d(TAG, required_permissions[1] + "Dont allow handle here");
            }
            //===we handle both byh below code
            request_permission_luncher_read_contacts.launch(required_permissions[1]);
        }

    }

    private ActivityResultLauncher<String>  request_permission_luncher_read_contacts =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Log.d(TAG, required_permissions[1] + "Allowed");
                    is_read_contacts_permitted = true;
                    img_deny_contacts.setVisibility(View.GONE);
                    img_given_contacts.setVisibility(View.VISIBLE);
                } else {
                    Log.d(TAG, required_permissions[1] + "Allowed");
                    is_read_contacts_permitted = true;

                }

            });
    /**************Getting Read Contactl permission Ended ***************/



    /**************Getting Record Audio permission ******************/
    public void requestPermissionRecordAudio() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, required_permissions[2]) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, required_permissions[2] + "Granted");

            is_record_audio_permitted = true;
            img_deny_audio.setVisibility(View.GONE);
            img_deny_audio.setVisibility(View.VISIBLE);
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                Log.d(TAG, required_permissions[2] + "2nd time Dont allow handle here");
            } else {
                Log.d(TAG, required_permissions[2] + "Dont allow handle here");
            }
            //===we handle both byh below code
            request_permission_luncher_record_audio.launch(required_permissions[2]);
        }

    }

    private ActivityResultLauncher<String>  request_permission_luncher_record_audio =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Log.d(TAG, required_permissions[2] + "Allowed");
                    is_record_audio_permitted = true;
                    img_deny_audio.setVisibility(View.GONE);
                    img_given_audio.setVisibility(View.VISIBLE);
                } else {
                    Log.d(TAG, required_permissions[2] + "Allowed");
                    is_record_audio_permitted = true;

                }
                AllCheckPermissionFinalCall();

            });

    /**************Getting Record Audio permission Ended ***************/



    private void AllCheckPermissionFinalCall() {
        is_all_permission_requested=true;
        if(allPermissionResultCheck())
        {
            Toast.makeText(this, "Granted All Permissions", Toast.LENGTH_SHORT).show();
        }else{
            sendToSettingDialog();
        }
    }

    private void sendToSettingDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Alert For Permissions")
                .setMessage("Go to setting to grant permission")
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent =new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri= Uri.fromParts("package",getPackageName(),null);
                        intent.setData(uri);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                })

               .setNegativeButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               finish();
            }
        });
    }


    @Override
    protected void onResume(){
        super.onResume();
        if(is_all_permission_requested){
            boolean  master_check_permission=false;
            for(int i=0;i<required_permissions.length;i++)
            {
                if(ContextCompat.checkSelfPermission(MainActivity.this,required_permissions[i])==PackageManager.PERMISSION_GRANTED)
                {
                    Log.d(TAG, "break after->"+required_permissions[i]);
                }else{
                    master_check_permission=true;
                    break;
                }
            }

            if(master_check_permission){
                sendToSettingDialog();
            }else{
                Toast.makeText(this, "Granted All Permissions", Toast.LENGTH_SHORT).show();
            }
        }
    }










}