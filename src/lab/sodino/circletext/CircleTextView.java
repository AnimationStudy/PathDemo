package lab.sodino.circletext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/** 
 * @author Sodino E-mail:sodinoopen@hotmail.com 
 * @version Time：Jun 22, 2014 11:00:19 PM 
 */
public class CircleTextView extends View {
	public static final String DEMO = "CircleTextView demo. author:sodino, mail:sodinoopen@hotmail.com";
	public static final int TEXT_SIZE = 30;
	String txt = DEMO;
	Path pathCircle;
	Paint paint;
	RectF circleRect;
	int horizonOffset;
	int startAngle;
	
	public CircleTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CircleTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CircleTextView(Context context) {
		super(context);
	}
	
	void init(){
		if (paint == null || pathCircle == null || circleRect == null) {
			int width = getWidth();
			int radius = 150;
			circleRect = new RectF(width /2 - radius, width /2 - radius, width /2 + radius, width /2 + radius);
			
			paint = new Paint();
			paint.setColor(Color.RED);
			paint.setTextSize(TEXT_SIZE);
			paint.setStyle(Paint.Style.STROKE); // 设置绘画区域空心
			
			pathCircle = new Path();
		}
	}

	public void commitText (String txt) {
		this.txt = txt;
		if ("main".equals(Thread.currentThread())) {
			invalidate();
		} else {
			postInvalidate();
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		init();
		
//		Log.d("ANDROID_LAB", "path is empty:" + pathCircle.isEmpty() +" start");
		if (pathCircle.isEmpty() == false) {
			pathCircle.reset();
		}

//		Log.d("ANDROID_LAB", "path is empty:" + pathCircle.isEmpty() +" end, startAngle=" + startAngle);
		pathCircle.addArc(circleRect, startAngle, 359); //妹呀，是360的话就不转了..
		pathCircle.close();
		
		canvas.drawTextOnPath(txt, pathCircle, 0, 0, paint);
		horizonOffset ++;
		startAngle ++;
		invalidate();
	}
}
