package Finally.createpdf.util;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import Finally.createpdf.R;

public class FavouritesPreferences extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.favourites_preferences);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
