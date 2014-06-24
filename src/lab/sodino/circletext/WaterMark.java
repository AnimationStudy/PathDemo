package lab.sodino.circletext;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class WaterMark extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new WaterMarkView(this));
	}
	
	private class WaterMarkView extends View{
		private Bitmap mBitmap;
		private Context mContext;
		private Paint mPaint;
		private static final String WATER_MARK_STRING= "Start. sodino text demo. circle. end.";

		public WaterMarkView(Context context) {
			super(context);
			mContext = context;
			mPaint = new Paint();
			mPaint.setAntiAlias(true);
			mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher);
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			
			canvas.drawBitmap(mBitmap, 0, 0, mPaint);
			drawWaterMark(canvas,getWidth(),getHeight());
		}

		private void drawWaterMark(Canvas canvas, int width, int height) {
			int fontSize = 35;
			Path path = new Path();
//			path.moveTo(0, height);
//			path.lineTo(width, 0);
//			path.addCircle(200, 200, 160, Path.Direction.CW);
			path.addArc(new RectF(100, 100, 300, 300), 180, 180);
			path.close();
			
			Paint paint = new Paint();
			paint.setColor(0x88ff0000);
			paint.setTextSize(fontSize);
			paint.setAntiAlias(true);
			paint.setDither(true);
			Rect bounds = new Rect();
			paint.getTextBounds(WATER_MARK_STRING, 0, WATER_MARK_STRING.length(), bounds);
			
//			int length = (int)Math.sqrt(width*width + height*height);
//			int hOffset = (length - (bounds.right - bounds.left)) / 2;
//			canvas.drawTextOnPath(WATER_MARK_STRING, path, hOffset, fontSize/2, paint);
			canvas.drawTextOnPath(WATER_MARK_STRING, path, 0, 0, paint);
			canvas.drawPath(path, paint);
		}
	}
}
