package com.aditya.babylonplus;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class BabylonPlusApplication extends Application {

    public void onCreate(){
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this).
                        enableDumpapp(Stetho.defaultDumperPluginsProvider(this)).
                        enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).
                        build()
        );
    }
}
