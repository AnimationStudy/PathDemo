/*
 * Copyright (C) 2007 The Android Open Source Project
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

package lab.sodino.circletext;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class PathEffects extends Activity {
	private static long lastTime = 0l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }

    private static class SampleView extends View {
        private Paint mPaint;
        private Path mPath;
        private PathEffect[] mEffects;
        private int[] mColors;
        private float mPhase;

        private static PathEffect makeDash(float phase) {
            return new DashPathEffect(new float[] { 15, 5, 8, 5 }, phase);
        }

        private static void makeEffects(PathEffect[] e, float phase) {
            e[0] = null;     // no effect，直接是点对点的折线
            e[1] = new CornerPathEffect(10); // 在点对点的折线上，用圆形拐角替换尖锐的线夹角，构造函数中的数值是圆形半径
            e[2] = new DashPathEffect(new float[] {30, 5, 5, 10}, phase); // 在点对点的折线上，interval[]中的参数中偶数的值表示开启绘画区域长度，奇数的值为关闭绘画区域的长度。interval[]的size只能是偶数。phase表示起始绘画的相位。
            e[3] = new PathDashPathEffect(makePathDash(), 50, phase,
                                          PathDashPathEffect.Style.ROTATE); // Dash可以是一个Path
//            e[4] = new ComposePathEffect(e[2], e[1]);
            e[4] = new ComposePathEffect(e[1], e[2]); // 轮流
//            e[5] = new ComposePathEffect(e[3], e[1]);
            e[5] = new ComposePathEffect(e[4], e[2]);
        }

        public SampleView(Context context) {
            super(context);
            setFocusable(true);
            setFocusableInTouchMode(true);

            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(6);

            mPath = makeFollowPath();

            mEffects = new PathEffect[6];

            mColors = new int[] { Color.BLACK, Color.RED, Color.BLUE,
                                  Color.GREEN, Color.MAGENTA, Color.BLACK
                                };
        }

        @Override protected void onDraw(Canvas canvas) {
        	Log.d("ANDROID_LAB", "onDraw time=" + (System.currentTimeMillis() - lastTime) +" mPhase=" + mPhase);
        	lastTime = System.currentTimeMillis();
            canvas.drawColor(Color.WHITE);

            RectF bounds = new RectF();
            mPath.computeBounds(bounds, false);
            canvas.translate(10 - bounds.left, 10 - bounds.top);// bounds只是刚初始化的RectF，bounds.left和bounds.top都还是0

            makeEffects(mEffects, mPhase);
            mPhase += 1;
            invalidate();

            for (int i = 0; i < mEffects.length; i++) {
                mPaint.setPathEffect(mEffects[i]);
                mPaint.setColor(mColors[i]);
                canvas.drawPath(mPath, mPaint);
                canvas.translate(0, 28);
            }
        }

        @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_CENTER:
                    mPath = makeFollowPath();
                    return true;
            }
            return super.onKeyDown(keyCode, event);
        }

        private static Path makeFollowPath() {
            Path p = new Path();
            p.moveTo(0, 0);
            for (int i = 1; i <= 15; i++) {
                p.lineTo(i*20, (float)Math.random() * 35);
            }
            return p;
        }

        private static Path makePathDash() {
        	// 标定出一个箭头的轮廓来
            Path p = new Path();
            p.moveTo(4, 0);
            p.lineTo(0, -4);
            p.lineTo(8, -4);
            p.lineTo(12, 0);
            p.lineTo(8, 4);
            p.lineTo(0, 4);
            return p;
        }
    }
}

