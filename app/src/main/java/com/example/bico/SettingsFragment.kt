package com.example.bico

import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)

        val colorPreference:ListPreference? = findPreference("color")

        colorPreference?.summaryProvider =
            ListPreference.SimpleSummaryProvider.getInstance()

    }
}