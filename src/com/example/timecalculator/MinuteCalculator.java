package com.example.timecalculator;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class MinuteCalculator extends Activity {
	private TextView textFromDate;
	private Button changeFromDate;
	private TextView textFromTime;
	private Button changeFromTime;
	private TextView textToDate;
	private Button changeToDate;
	private TextView textToTime;
	private Button changeToTime;
	private Button calculate;
	private TextView resultView;
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour;
	private int mMinute;
	private int toYear;
	private int toMonth;
	private int toDay;
	private int toHour;
	private int toMinute;
	private String result;
	static final int FROM_TIME_DIALOG_ID = 0;
	static final int FROM_DATE_DIALOG_ID = 1;
	static final int TO_TIME_DIALOG_ID = 2;
	static final int TO_DATE_DIALOG_ID = 3;
	static final int CALCULATE_DIALOG_ID = 4;
	/** Called when the activity is first created. */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textFromDate = (TextView) findViewById(R.id.TextFromDate);
		changeFromDate = (Button) findViewById(R.id.ButtonFromDate);
		textFromTime = (TextView) findViewById(R.id.TextFromTime);
		changeFromTime = (Button) findViewById(R.id.ButtonFromTime);
		textToDate = (TextView) findViewById(R.id.TextToDate);
		changeToDate = (Button) findViewById(R.id.ButtonToDate);
		textToTime = (TextView) findViewById(R.id.TextToTime);
		changeToTime = (Button) findViewById(R.id.ButtonToTime);
		calculate = (Button) findViewById(R.id.ButtonCalculate);
		resultView = (TextView) findViewById(R.id.ResultView);

		// add a click listener to the button
		changeFromDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(FROM_DATE_DIALOG_ID);
			}
		});

		// add a click listener to the button
		changeFromTime.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(FROM_TIME_DIALOG_ID);
			}
		});

		// add a click listener to the button
		changeToDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(TO_DATE_DIALOG_ID);
			}
		});

		// add a click listener to the button
		changeToTime.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(TO_TIME_DIALOG_ID);
			}
		});

		calculate.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				updateResultDisplay();
			}
		});



		// get the current date
		final Calendar c = Calendar.getInstance();
		mYear = toYear = c.get(Calendar.YEAR);
		mMonth = toMonth = c.get(Calendar.MONTH);
		mDay = toDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = toHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = toMinute = c.get(Calendar.MINUTE);
		result = "0";

		// display the current date (this method is below)
		updateAllDisplay();



	}

	private void updateAllDisplay() {
		textFromDate.setText(
				new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-")
				.append(mDay).append("-")
				.append(mYear).append(" "));

		textToDate.setText(
				new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-")
				.append(mDay).append("-")
				.append(mYear).append(" "));

		textFromTime.setText(
				new StringBuilder()
				.append(pad(mHour)).append(":")
				.append(pad(mMinute)));

		textToTime.setText(
				new StringBuilder()
				.append(pad(mHour)).append(":")
				.append(pad(mMinute)));
		
		resultView.setText(result + " minutes");
	}

	// updates the date in the TextView
	private void updateFromDateDisplay() {
		textFromDate.setText(
				new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-")
				.append(mDay).append("-")
				.append(mYear).append(" "));
	}

	// updates the time we display in the TextView
	private void updateFromTimeDisplay() {
		textFromTime.setText(
				new StringBuilder()
				.append(pad(mHour)).append(":")
				.append(pad(mMinute)));
	}

	// updates the date in the TextView
	private void updateToDateDisplay() {
		textToDate.setText(
				new StringBuilder()
				// Month is 0 based so add 1
				.append(toMonth + 1).append("-")
				.append(toDay).append("-")
				.append(toYear).append(" "));
	}

	// updates the time we display in the TextView
	private void updateToTimeDisplay() {
		textToTime.setText(
				new StringBuilder()
				.append(pad(toHour)).append(":")
				.append(pad(toMinute)));
	}
	
	private void updateResultDisplay() {
		resultView.setText(Calc.calculate(new Date(mYear-1900, mMonth, mDay, mHour, mMinute), new Date(toYear-1900, toMonth, toDay, toHour, toMinute)));
	}

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener fromDateSetListener =
		new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, 
				int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateFromDateDisplay();
		}
	};

	// the callback received when the user "sets" the time in the dialog
	private TimePickerDialog.OnTimeSetListener fromTimeSetListener =
		new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			updateFromTimeDisplay();
		}
	};

	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener toDateSetListener =
		new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, 
				int monthOfYear, int dayOfMonth) {
			toYear = year;
			toMonth = monthOfYear;
			toDay = dayOfMonth;
			updateToDateDisplay();
		}
	};

	// the callback received when the user "sets" the time in the dialog
	private TimePickerDialog.OnTimeSetListener toTimeSetListener =
		new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			toHour = hourOfDay;
			toMinute = minute;
			updateToTimeDisplay();
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case FROM_DATE_DIALOG_ID:
			return new DatePickerDialog(this,
					fromDateSetListener,
					mYear, mMonth, mDay);

		case FROM_TIME_DIALOG_ID:
			return new TimePickerDialog(this,
					fromTimeSetListener, mHour, mMinute, false);
		case TO_DATE_DIALOG_ID:
			return new DatePickerDialog(this,
					toDateSetListener,
					toYear, toMonth, toDay);

		case TO_TIME_DIALOG_ID:
			return new TimePickerDialog(this,
					toTimeSetListener, toHour, toMinute, false);
			
		}
		return null;
	}
}