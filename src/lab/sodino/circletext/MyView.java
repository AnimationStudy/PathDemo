package lab.sodino.circletext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	private Paint paint;
	public MyView(Context context) {
		super(context);
		paint = new Paint();
	}

	
	public MyView(Context context, AttributeSet set){
		super(context, set);
		paint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		/* 设置背景为白色 */
		canvas.drawColor(Color.WHITE);
		/* 去锯齿 */
		paint.setAntiAlias(true);
		/* 设置paint的颜色 */
		paint.setColor(Color.RED);
		/* 设置paint的　style　为STROKE：空心 */
		paint.setStyle(Paint.Style.STROKE);
		/* 设置paint的外框宽度 */
		paint.setStrokeWidth(3);

		/* 画一个空心三角形 */
		Path path = new Path();
		path.moveTo(10, 330);
		path.lineTo(70, 330);
		path.lineTo(40, 100);
		path.close();
		canvas.drawPath(path, paint);

		/* 设置paint　的style为　FILL：实心 */
		paint.setStyle(Paint.Style.FILL);
		/* 设置paint的颜色 */
		paint.setColor(Color.BLUE);

		/* 画一个实心三角形 */
		Path path2 = new Path();
		path2.moveTo(90, 330);
		path2.lineTo(150, 330);
		path2.lineTo(120, 270);
		path2.close();
		canvas.drawPath(path2, paint);

		Shader mShader = new LinearGradient(0, 0, 100, 100, new int[] {
				Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW }, null,
				Shader.TileMode.REPEAT);
		paint.setShader(mShader);

		/* 画一个渐变色三角形 */
		Path path4 = new Path();
		path4.moveTo(170, 330);
		path4.lineTo(230, 330);
		path4.lineTo(200, 270);
		path4.close();
		canvas.drawPath(path4, paint);
	}

}
