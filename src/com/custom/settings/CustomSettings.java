/*
 * Copyright (C) 2020 AOSP
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
package com.custom.settings;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.internal.logging.nano.MetricsProto;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

import com.custom.settings.tabs.Lockscreen;
import com.custom.settings.tabs.Misc;
import com.custom.settings.tabs.StatusBar;
import com.custom.settings.tabs.System;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class CustomSettings extends SettingsPreferenceFragment {

    Context mContext;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();

    int[] colors = {
            mContext.getColor(R.color.lockscreen),
            mContext.getColor(R.color.misc),
            mContext.getColor(R.color.statusbar),
            mContext.getColor(R.color.system)
    };

        view = inflater.inflate(R.layout.custom_settings, container, false);

        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Magic Centre");
        }

        Drawable unwrappedDrawable = AppCompatResources.getDrawable(mContext, R.drawable.bottom_bar);
        Drawable unwrappedDrawable2 = AppCompatResources.getDrawable(mContext, R.drawable.top_bar);

        Drawable bottomBarBg = DrawableCompat.wrap(unwrappedDrawable);
        Drawable topBarBg = DrawableCompat.wrap(unwrappedDrawable2);

	BubbleNavigationLinearView chipNavigationBar = view.findViewById(R.id.expandable_bottom_bar);
        LinearLayout linearLayout = view.findViewById(R.id.myLinear);

        DrawableCompat.setTint(topBarBg, getAlpha(colors[0], 50));
        DrawableCompat.setTint(bottomBarBg, getAlpha(colors[0], 70));

        TextView textView = view.findViewById(R.id.title);

        chipNavigationBar.setBackground(bottomBarBg);
        linearLayout.setBackground(topBarBg);

        Fragment system = new System();
        Fragment statusbar = new StatusBar();
        Fragment lockscreen = new Lockscreen();
        Fragment misc  = new Misc();

        Fragment fragment = (Fragment) getFragmentManager().findFragmentById(R.id.frame_container);
        if (fragment == null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, lockscreen);
            transaction.addToBackStack(null);
            transaction.commit();
        }

        chipNavigationBar.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
            int viewId = position;
            switch (viewId) {
                default:break;
                case 0:
                    DrawableCompat.setTint(bottomBarBg, getAlpha(colors[0], 70));
                    DrawableCompat.setTint(topBarBg, getAlpha(colors[0], 50));
                    textView.setTextColor(colors[0]);
                    textView.setText("Lockscreen");
                    startFrag(lockscreen);
                    break;
                case 1:
                    DrawableCompat.setTint(bottomBarBg, getAlpha(colors[1], 70));
                    DrawableCompat.setTint(topBarBg, getAlpha(colors[1], 50));
                    textView.setTextColor(colors[1]);
                    textView.setText("Misc");
                    startFrag(misc);
                    break;
                case 2:
                    DrawableCompat.setTint(bottomBarBg, getAlpha(colors[2], 70));
                    DrawableCompat.setTint(topBarBg, getAlpha(colors[2], 50));
                    textView.setTextColor(colors[2]);
                    textView.setText("Statusbar");
                    startFrag(statusbar);
                    break;
                case 3:
                    DrawableCompat.setTint(bottomBarBg, getAlpha(colors[3], 70));
                    DrawableCompat.setTint(topBarBg, getAlpha(colors[3], 50));
                    textView.setTextColor(colors[3]);
                    textView.setText("System");
                    startFrag(system);
                    break;
            }
	  }
        });

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.CUSTOM;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    }

    private void startFrag(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private int getAlpha(int color, int alpha) {
        return ColorUtils.setAlphaComponent(color, alpha);
    }
}
