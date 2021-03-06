/*
 * Copyright (C) 2014 The XToast Open Source Project
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

package org.knight;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.knight.widget.XToast;

public class MainActivity extends Activity {
    private boolean isLightTheme;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLightTheme = getIntent().getBooleanExtra("isLightTheme", false);
        if (isLightTheme) {
            setTheme(android.R.style.Theme_Holo_Light);
        } else {
            setTheme(android.R.style.Theme_Holo);
        }
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        menu.getItem(0).setTitle(isLightTheme ? "Dark" : "Light");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.theme) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("isLightTheme", !isLightTheme);
            startActivity(intent);
            finish();
        }
        return true;
    }

    private void showNormal() {
        XToast.create(this, "Normal Toast").show();
    }

    private void showCustomText() {
        XToast.create(this, "CustomText Toast").withTextColor(Color.parseColor("#00FF00")).withTextSize(20).show();
    }

    private void showDuration() {
        XToast.create(this, "Long Duration").withDuration(XToast.Duration.LONG).show();
    }

    private void showGravity() {
        XToast.create(this, "Gravity Top and 60px Offset").withGravity(Gravity.TOP, 60, 60).show();
    }

    private void showPosition() {
        View v = findViewById(R.id.btn_position);
        XToast.create(this, "Beside View").withPosition(v, XToast.Position.LEFT, 0, 0).show();
    }

    private void showBackgroundColor() {
        XToast.create(this, "Background Color(Red)").withBackgroundColor(Color.parseColor("#FF0000")).show();
    }

    private void showBackgroundResource() {
        XToast.create(this, "Background Resource").withBackgroundResource(R.drawable.xtoast_custom_bg).show();
    }

    private void showAnim() {
        XToast.create(this, "Animation FLY").withAnimation(XToast.Anim.FLY).show();
    }

    private void showCover() {
        XToast.create(this, "Cover Previous Toast").withCover(true).show();
    }

    /**
     * If you want only to show the single button,
     * pass null or empty string to the second param for create().
     */
    private void showButton() {
        Drawable d = getResources().getDrawable(R.drawable.icon_undo);
        //listener will be executed on UI thread
        XToast.create(this, "Button").withButton("Undo", d, new XToast.OnButtonClickListener() {
            @Override
            public void onClick(XToast xtoast) {
                xtoast.dismiss();
                XToast.create(MainActivity.this, "Click Event").show();
            }
        }).show();
    }

    private void showButtonForever() {
        Drawable d = getResources().getDrawable(R.drawable.icon_undo);
        //listener will be executed on UI thread
        XToast.create(this, "Button shows forever until dismissed manually").withDuration(XToast.Duration.FOREVER).withButton("Undo", d, new XToast.OnButtonClickListener() {
            @Override
            public void onClick(XToast xtoast) {
                xtoast.dismiss();
                XToast.create(MainActivity.this, "Click Event").show();
            }
        }, true).show();
    }

    private void cancelCurrent() {
        XToast.dismissCurrent();
    }

    private void cancelAll() {
        XToast.dismissAll();
    }

    public void show(View v) {
        switch (v.getId()) {
            case R.id.btn_normal:
                showNormal();
                break;

            case R.id.btn_text:
                showCustomText();
                break;

            case R.id.btn_duration:
                showDuration();
                break;

            case R.id.btn_gravity:
                showGravity();
                break;

            case R.id.btn_position:
                showPosition();
                break;

            case R.id.btn_background_color:
                showBackgroundColor();
                break;

            case R.id.btn_background_res:
                showBackgroundResource();
                break;

            case R.id.btn_anim:
                showAnim();
                break;

            case R.id.btn_cover:
                showCover();
                break;

            case R.id.btn_button:
                showButton();
                break;

            case R.id.btn_button_forever:
                showButtonForever();
                break;

            case R.id.btn_cancel_current:
                cancelCurrent();
                break;

            case R.id.btn_cancel_all:
                cancelAll();
                break;

            default:
                break;
        }
    }
}
