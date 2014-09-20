package com.arshu.roommate.client.view;

import java.io.IOException;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.arshu.roommate.RMUtils;
import com.arshu.roommate.client.R;
import com.arshu.roommate.client.appengine.Connector;
import com.arshu.roommate.client.db.entity.CLMate;
import com.arshu.roommate.client.util.RMLog;
import com.arshu.roommate.server.endpoint.rmendpoint.model.AEMate;

public class SignUpActivity extends Activity {

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private RegisterTask mAuthTask = null;

	// UI references.
	private EditText mUserNameView;
	private EditText mPasswordView;
	private EditText mPasswordConfirmView;
	private EditText mEmailView;
	
	private View mProgressView;
	private View mLoginFormView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		// Set up the login form.
		mUserNameView = (EditText) findViewById(R.id.signUp_userName_editText);
		mPasswordView = (EditText) findViewById(R.id.signUp_password_editText);
		mPasswordConfirmView = (EditText) findViewById(R.id.signUp_passwordConfirm_editText);
		mEmailView = (EditText) findViewById(R.id.signUp_email_editText);
		
		mPasswordConfirmView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.signUp_register_button || id == EditorInfo.IME_NULL) {
							attemptRegister();
							return true;
						}
						return false;
					}
				});

		

		mLoginFormView = findViewById(R.id.signUp_form);
		mProgressView = findViewById(R.id.signUp_progress);
	}

	
	public void onRegisterButtonClick(View view){
		attemptRegister();
	}
	
	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptRegister() {
		if (mAuthTask != null) {
			return;
		} 

		// Reset errors.
		mUserNameView.setError(null);
		mPasswordView.setError(null);

		String password = mPasswordView.getText().toString();
		View focusView = null; 

		boolean cancel = false;

		// Check for a valid password, if the user entered one.
		if (!TextUtils.isEmpty(password) && !RMUtils.isPasswordValid(password)) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			mPasswordView.requestFocus();
			cancel = true;
		}else {
			String passwordConfirm = mPasswordConfirmView.getText().toString();
			if (!TextUtils.isEmpty(passwordConfirm) && !RMUtils.isPasswordValid(passwordConfirm)) {
				mPasswordConfirmView.setError(getString(R.string.error_invalid_password));
				mPasswordConfirmView.requestFocus();
				cancel = true;
			}else {
				if(!passwordConfirm.equals(password)){
					mPasswordConfirmView.setError(getString(R.string.error_notMatch_password));
					mPasswordConfirmView.requestFocus();
					cancel = true;
				}
			}
		}
		
		CLMate mate = new CLMate();
		mate.setEmailAddress(mEmailView.getText().toString());
		mate.setUserName(mUserNameView.getText().toString());
		mate.setPassword(password);
		
		
		if (TextUtils.isEmpty(mate.getEmailAddress())) { 
			mEmailView.setError(getString(R.string.error_field_required));
			mEmailView.requestFocus();
			cancel = true;
		} else if (!RMUtils.isUserNameValid(mate.getEmailAddress())) {
			mEmailView.setError(getString(R.string.error_invalid_email));
			mEmailView.requestFocus();;
			cancel = true;
		}
		
		// Check for a valid email address.
		if (TextUtils.isEmpty(mate.getUserName())) { 
			mUserNameView.setError(getString(R.string.error_field_required));
			mUserNameView.requestFocus();
			cancel = true;
		} else if (!RMUtils.isUserNameValid(mate.getUserName())) {
			mUserNameView.setError(getString(R.string.error_invalid_userName));
			mUserNameView.requestFocus();;
			cancel = true;
		}

		if (!cancel) {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			showProgress(true);
			mAuthTask = new RegisterTask(mate);
			mAuthTask.execute();
		}
	}


	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});

			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mProgressView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mProgressView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class RegisterTask extends AsyncTask<Void, Void, Boolean> {

		private CLMate mate;

		RegisterTask(CLMate mate) {
			this.mate = mate;
		}

		@Override
		protected Boolean doInBackground(Void... params) {

			try {
				Connector.registerMate(mate);
				return true;
			} catch (IOException e) {
				RMLog.e(getClass().getSimpleName(),"IOException");
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
				Intent homeActivityIntent = new Intent(SignUpActivity.this,HomeActivity.class);
				//homeActivityIntent.putExtra(RMClientConstants.BK_MATE_ID, mate);
				startActivity(homeActivityIntent);
				finish();
			} else {
				mPasswordView
						.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
}
