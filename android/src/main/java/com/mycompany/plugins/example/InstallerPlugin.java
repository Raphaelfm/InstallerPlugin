package com.mycompany.plugins.example;

import android.util.Log;

public class InstallerPlugin {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
}
