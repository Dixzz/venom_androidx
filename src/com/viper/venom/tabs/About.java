/*
 * Copyright (C) 2017 The ABC rom
 * Copyright (C) 2018 ViperOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package com.viper.venom.tabs;

import android.content.Context;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.preference.ListPreference;
import androidx.preference.SwitchPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import androidx.preference.Preference.OnPreferenceChangeListener;
import android.graphics.Color;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto;
import com.android.settings.Utils;

import com.viper.venom.about.FancyAboutPage;

public class About extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContentResolver resolver = getActivity().getContentResolver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about, container, false);
        FancyAboutPage fancyAboutPage = (FancyAboutPage)view. findViewById(R.id.fancyaboutpage);

        fancyAboutPage.setCover(R.drawable.coverimg);
        fancyAboutPage.addTelegramLink("https://t.me/viperos_news");
        fancyAboutPage.addGitHubLink("https://github.com/ViperOS");

        return view;

    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.VENOM;
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }
}
