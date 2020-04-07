package com.example.hidr8;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        //sets the preferences from preferences.xml to the default shared preferences
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}