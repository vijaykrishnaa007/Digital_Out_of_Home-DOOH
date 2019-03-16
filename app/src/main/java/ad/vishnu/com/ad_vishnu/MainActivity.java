/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package ad.vishnu.com.ad_vishnu;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;

/*
 * MainActivity class that loads {@link MainFragment}.
 */
public class MainActivity extends Activity {
    VideoView videoView;
    String m_Text;
    Button proceed,enterkey;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proceed = findViewById(R.id.proceed);
        enterkey = findViewById(R.id.keyenter);
        SharedPreferences sharedPref = getSharedPreferences("secret", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SingleVideoActivity.class));
            }
        });
        enterkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nokey();
            }
        });
        if (!sharedPref.contains("key")) {

                nokey();
        }
    }

    void nokey()
    {
        SharedPreferences sharedPref = getSharedPreferences("secret", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.input, (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0), false);
        final EditText input = (EditText) viewInflated.findViewById(R.id.input);
        builder.setView(viewInflated);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                m_Text = input.getText().toString();
                editor.putString("key", m_Text);
                editor.commit();
                startActivity(new Intent(MainActivity.this, SingleVideoActivity.class));

            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
