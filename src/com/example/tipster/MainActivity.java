package com.example.tipster;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText etBillAmount;
	private TextView tvTotal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etBillAmount = (EditText) findViewById(R.id.etBillAmount);
		tvTotal = (TextView) findViewById(R.id.tvTotal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onTipButton(View view) {
		Toast.makeText(this,  "Hello",  Toast.LENGTH_LONG).show();
		double bill = Double.parseDouble(etBillAmount.getText().toString());
		double tippercent = Double.parseDouble(view.getTag().toString());
		double total = bill * (1 + tippercent);
		String display = String.format("The total is %.2f",total);
		tvTotal.setText(display);
	}

}
