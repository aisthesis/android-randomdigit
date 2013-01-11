/*
 * Copyright 2013 Marshall Farrier
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codemelon.android.randomdigit;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Generate a random digit between 0 and 9. Refresh the digit when you click
 * anywhere on the screen. Cf.
 * http://stackoverflow.com/questions/4979212/programmatically-creating-a-
 * relativelayout-in-android
 * 
 * @author Marshall Farrier
 * @my.created Jan 5, 2013
 * @my.edited Jan 5, 2013
 */

public class RandomDigit extends Activity {
    private static final int BACKGROUND_COLOR = 0xff444444;
    private static final int TEXT_COLOR = 0xfff8a122;

    private RelativeLayout mRelativeLayout;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set up parent view
        mRelativeLayout = new RelativeLayout(this);
        mRelativeLayout.setBackgroundColor(BACKGROUND_COLOR);
        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        // set up TextView containing the random number
        initRandomNumberContainer();
        initListener();
        // create the view
        setContentView(mRelativeLayout, relativeLayoutParams);
        Toast toast = Toast.makeText(this, "Tap screen to refresh",
                Toast.LENGTH_LONG);
        int verticalOffset = getDisplayHeight() / 5;
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0,
                verticalOffset);
        toast.show();
    }

    private void initRandomNumberContainer() {
        mTextView = new TextView(this);
        mTextView.setText(String.valueOf((int) (Math.random() * 10.0)));
        RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        textViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mTextView.setTextColor(TEXT_COLOR);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 192.0F);

        mTextView.setLayoutParams(textViewParams);
        mRelativeLayout.addView(mTextView);
    }

    private void initListener() {
        mRelativeLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mTextView.setText(String.valueOf((int) (Math.random() * 10.0)));
                mTextView.invalidate();
            }
        });
    }
    
    private int getDisplayHeight() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        return size.y;
    }
}
