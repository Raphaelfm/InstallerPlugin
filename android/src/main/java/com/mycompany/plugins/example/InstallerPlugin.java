package com.mycompany.plugins.example;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.PluginMethod;

import java.io.File;

@CapacitorPlugin(name = "Installer")
public class InstallerPlugin extends Plugin {

    @PluginMethod()
    public void installApk(PluginCall call) {
        Context context = getContext();
        String fileName = call.getString("fileName");

        if (fileName == null || fileName.isEmpty()) {
            call.reject("File name must be provided.");
            return;
        }

        File apkFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

        if (!apkFile.exists()) {
            call.reject("File not found.");
            return;
        }

        try {
            Uri apkUri = getUriFromFile(context, apkFile);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Log.d("InstallerPlugin", "Starting APK installation intent.");
            context.startActivity(intent);
            call.resolve();
        } catch (Exception e) {
            Log.e("InstallerPlugin", "Error installing APK", e);
            call.reject("Error installing APK: " + e.getMessage());
        }
    }

    private Uri getUriFromFile(Context context, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String authority = context.getPackageName() + ".fileprovider";
            return FileProvider.getUriForFile(context, authority, file);
        } else {
            return Uri.fromFile(file);
        }
    }
}
