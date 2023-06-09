package com.runtop.android.baselibrary.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.runtop.android.baselibrary.BaseApplication;

public class SharedPreferencesProvider {

    private final static String KV_NAME = "AppClient";
    private volatile static SharedPreferencesProvider instance = null;
    private SharedPreferences preferences = null;

    private SharedPreferencesProvider() {
        if (preferences == null) {
            // 得到sharedPreferences
            preferences = BaseApplication.getInstance().getSharedPreferences(KV_NAME, Context.MODE_PRIVATE);
        }
    }

    public static SharedPreferencesProvider getInstance() {
        if (instance == null) {
            synchronized (SharedPreferencesProvider.class) {
                if (instance == null) {
                    instance = new SharedPreferencesProvider();
                }
            }
        }
        return instance;
    }

    public boolean getBoolean(String key, boolean defValue) {
        if (preferences == null) {
            return defValue;
        }
        return preferences.getBoolean(key, defValue);
    }

    public boolean putBoolean(String key, boolean value) {
        if (preferences == null) {
            return false;
        }
        return preferences.edit().putBoolean(key, value).commit();
    }

    public String getString(String key, String defValue) {
        if (preferences == null) {
            return defValue;
        }
        return preferences.getString(key, defValue);
    }

    public boolean putString(String key, String value) {
        if (preferences == null) {
            return false;
        }
        return preferences.edit().putString(key, value).commit();
    }

    public int getInt(String key, int defValue) {
        if (preferences == null) {
            return defValue;
        }
        return preferences.getInt(key, defValue);
    }

    public boolean putInt(String key, int value) {
        if (preferences == null) {
            return false;
        }
        return preferences.edit().putInt(key, value).commit();
    }

    public long getLong(String key, long defValue) {
        if (preferences == null) {
            return defValue;
        }
        return preferences.getLong(key, defValue);
    }

    public boolean putLong(String key, long value) {
        if (preferences == null) {
            return false;
        }
        return preferences.edit().putLong(key, value).commit();
    }

    public void remove(String key) {
        if (preferences == null) {
            return;
        }
        preferences.edit().remove(key).commit();
    }

    public boolean removeAll() {
        if (preferences == null) {
            return false;
        }
        return preferences.edit().clear().commit();
    }

}
