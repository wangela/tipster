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
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class DetailedActivity extends Activity implements OnSeekBarChangeListener {
	private EditText subtotal;
	private EditText tax;
	private SeekBar bar;
	private TextView barPercentage;
	private TextView detailedTip;
	private TextView detailedTotal;
	private double tipPercentage;
	NumberFormat currency = NumberFormat.getCurrencyInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed);
		
		subtotal = (EditText) findViewById(R.id.etSubtotal);
		tax = (EditText) findViewById(R.id.etTax);
 		bar = (SeekBar) findViewById(R.id.sbTipPercentage);
		barPercentage = (TextView) findViewById(R.id.tvSeekTip);
		detailedTip = (TextView) findViewById(R.id.tvTipResult);
		detailedTotal = (TextView) findViewById(R.id.tvTotalResult);
		bar.setProgress(0);
		bar.setOnSeekBarChangeListener(this);
		
		setupEditTextListener();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void setupEditTextListener() {
		
		subtotal.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// Fires right as the text is being changed (even supplies the range of text)
				detailedTip.setText("");
				detailedTotal.setText("");
				bar.setProgress(0);
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
		
		tax.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// Fires right as the text is being changed (even supplies the range of text)
				detailedTip.setText("");
				detailedTotal.setText("");
				bar.setProgress(0);
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
	
	@Override
    public void onProgressChanged(SeekBar seekBar, int progress,
    		boolean fromUser) {
    	// change tip percentage text label with current seekbar value
    	barPercentage.setText(progress+"%");
    	tipPercentage = (double)progress * 0.01;    	
    }
    
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {	
    }
    
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {  	
    	// calculate and update calculated amounts
    	double billSubtotal = Double.parseDouble(subtotal.getText().toString());
    	double billTax = Double.parseDouble(tax.getText().toString());
		double tip = billSubtotal * tipPercentage;
		double total = billSubtotal + billTax + tip;
		String displayTip = currency.format(tip);
		String displayTotal = currency.format(total);
		detailedTip.setText(displayTip);
		detailedTotal.setText(displayTotal);
    } 
    
	public void onSwitchToSimple(View view) {
		Intent simpleTip = new Intent(DetailedActivity.this, SimpleActivity.class);
		startActivity(simpleTip);
	}
}
