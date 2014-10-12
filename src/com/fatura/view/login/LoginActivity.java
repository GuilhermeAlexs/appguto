package com.fatura.view.login;

import java.util.ArrayList;

import com.fatura.R;
import com.fatura.database.DatabaseHelper;
import com.fatura.model.Carrier;
import com.fatura.model.PhoneNumber;
import com.fatura.model.PhoneNumberFactory;
import com.fatura.model.User;
import com.fatura.view.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {

	public static String PHONE = "";

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mName;
	private String mPhone, mCarrier, mPaymentDay;

	// UI references.
	private EditText mEmailView, mPhoneView, mNameView, mPaymentDayView;
	private Spinner mCarrierView;
	private Button mButtonView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		DatabaseHelper dataHelper = new DatabaseHelper(this);
		if(dataHelper.checkForSession()) {
			dataHelper.updateSession();

			Intent mainActivityIntent = new Intent(this, MainActivity.class);
			startActivity(mainActivityIntent);
			finish();
		}

		// Set up the login form.
		mEmailView = (EditText) findViewById(R.id.email);
		mNameView = (EditText) findViewById(R.id.name);
		mPhoneView = (EditText) findViewById(R.id.phone);
		mCarrierView = (Spinner) findViewById(R.id.carrier);
		mPaymentDayView = (EditText) findViewById(R.id.paymentDay);
		mButtonView = (Button) findViewById(R.id.sign_in_button);

		mButtonView.setOnClickListener(loginButtonListener);

		ArrayList<String> list = new ArrayList<String>();
		list.add("TIM");
		list.add("VIVO");
		list.add("CLARO");
		list.add("OI");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_spinner_item, list);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mCarrierView.setAdapter(adapter);

		mCarrierView.setSelection(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		//getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	private OnClickListener loginButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			attemptLogin();
		}
	};

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {

		// Reset errors.
		mEmailView.setError(null);
		mNameView.setError(null);
		mPhoneView.setError(null);

		// Store values at the time of the login attempt.
		mEmail = mEmailView.getText().toString();
		mName = mNameView.getText().toString();
		mPhone = mPhoneView.getText().toString();
		mCarrier = mCarrierView.getSelectedItem().toString();
		mPaymentDay = mPaymentDayView.getText().toString();
		
		boolean cancel = false;
		View focusView = null;

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			mEmailView.setError(getString(R.string.error_invalid_email));
			focusView = mEmailView;
			cancel = true;
		}
		
		if(TextUtils.isEmpty(mName)) {
			mNameView.setError(getString(R.string.error_field_required));
			focusView = mNameView;
			cancel = true;
		}
		
		if(TextUtils.isEmpty(mPhone)) {
			mPhoneView.setError(getString(R.string.error_field_required));
			focusView = mPhoneView;
			cancel = true;
		}
		
		if(TextUtils.isEmpty(mPaymentDay)) {
			mPaymentDayView.setError(getString(R.string.error_field_required));
			focusView = mPaymentDayView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			PHONE = mPhone;
			
			DatabaseHelper dataHelper = new DatabaseHelper(this);
			
			User user = new User();
			PhoneNumber phoneNumber = PhoneNumberFactory.createPhoneNumber(mPhone);
			if(phoneNumber == null)
			{
				
			}

			phoneNumber.setCarrier(new Carrier(mCarrier.toUpperCase()));

			user.setPhoneNumber(phoneNumber);
			user.setEmail(mEmail);
			user.setName(mName);
			
			dataHelper.createSession(user, Integer.parseInt(mPaymentDay));
			
			Intent mainActivityIntent = new Intent(this, MainActivity.class);
			startActivity(mainActivityIntent);
			finish();
		}
	}
}
