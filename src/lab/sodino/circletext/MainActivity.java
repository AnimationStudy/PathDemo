package lab.sodino.circletext;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	EditText editText;
	Button btnCommit;
	CircleTextView ctView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editText = (EditText) findViewById(R.id.edit);
		
		btnCommit = (Button) findViewById(R.id.btnCommit);
		btnCommit.setOnClickListener(this);
		
		ctView = (CircleTextView) findViewById(R.id.circleTextView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnCommit:
			String txt = editText.getText().toString();
			if (txt != null && txt.length() > 0) {
				ctView.commitText(txt);
			} else {
				Log.d("ANDROID_LAB", "edit text is null.");
			}
			break;
		}
	}

}
