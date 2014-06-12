package codepath.wangela.apps.tipster;

import java.text.NumberFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SimpleActivity extends Activity {
	private EditText pretaxbill;
	private TextView tipamount;
	private TextView total;
	NumberFormat currency = NumberFormat.getCurrencyInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple);

		pretaxbill = (EditText) findViewById(R.id.etBillAmount);
		tipamount = (TextView) findViewById(R.id.tvTipAmount);
		total = (TextView) findViewById(R.id.tvTotal);

		setupETListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void setupETListener() {

		pretaxbill.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// Fires right as the text is being changed (even supplies the range of text)
				tipamount.setText(R.string.label_tip_amount);
				total.setText(R.string.label_total_amount);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// Fires right before text is changing
			}

			@Override
			public void afterTextChanged(Editable s) {
				// Fires right after the text has changed
			}
		});
		
	}

	public void onSwitchToDetailed(View view) {
		Intent detailedTip = new Intent(SimpleActivity.this,
				DetailedActivity.class);
		startActivity(detailedTip);
	}

	public void onTipButton(View view) {
		double bill = Double.parseDouble(pretaxbill.getText().toString());
		double tippercent = Double.parseDouble(view.getTag().toString());
		double tip = bill * tippercent;
		double totalsum = bill + tip;
		String displayTip = "The tip amount is: " + currency.format(tip);
		String displayTotal = "The total bill is: " + currency.format(totalsum);
		tipamount.setText(displayTip);
		total.setText(displayTotal);
	}

}
