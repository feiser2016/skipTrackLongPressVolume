package com.cilenco.skiptrack.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.cilenco.skiptrack.R;

import net.grandcentrix.tray.AppPreferences;

import static com.cilenco.skiptrack.utils.Constants.PREF_PERMISSION;

public class MainActivity extends AppCompatActivity {
    private static final String PERMISSION = Manifest.permission.SET_VOLUME_KEY_LONG_PRESS_LISTENER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        int permission = checkSelfPermission(PERMISSION);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{PERMISSION}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            AppPreferences prefs = new AppPreferences(this);
            prefs.put(PREF_PERMISSION, true);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setPositiveButton(android.R.string.ok,(dialog, which) -> finishAndRemoveTask());

            builder.setView(R.layout.dialog_permission);
            builder.setMessage(R.string.permission_description);
            builder.setTitle(R.string.permission_title);
            builder.setCancelable(false);

            builder.show();
        }
    }
}
