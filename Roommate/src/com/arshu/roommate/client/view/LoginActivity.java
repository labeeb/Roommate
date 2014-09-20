package com.arshu.roommate.client.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import com.arshu.roommate.client.db.RMDBHelper;
import com.arshu.roommate.client.db.RMDBUtil;
import com.arshu.roommate.client.db.entity.CLMate;
import com.arshu.roommate.client.db.entity.CLRoom;
import com.arshu.roommate.client.db.entity.CLRoomMate;
import com.arshu.roommate.client.util.RMClientConstants;
import com.arshu.roommate.client.util.RMLog;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * Read user name and password, check with server
 */
public class LoginActivity extends Activity  {

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// UI references.
	private EditText mUserNameView;
	private EditText mPasswordView;
	private View mProgressView;
	private View mLoginFormView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// Set up the login form.
		mUserNameView = (EditText) findViewById(R.id.login_userName_editText);

		mPasswordView = (EditText) findViewById(R.id.login_password_editText);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});

		mLoginFormView = findViewById(R.id.login_form);
		mProgressView = findViewById(R.id.login_progress);
	}

	public void onSignInButtonClick(View view){
		attemptLogin();
	}
	
	public void onRegisterButtonClick(View view){
		Intent signUpActivityIntent = new Intent(LoginActivity.this,SignUpActivity.class);
		startActivity(signUpActivityIntent);
		finish();
	}
	
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		} 

		// Reset errors.
		mUserNameView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		String userName = mUserNameView.getText().toString();
		String password = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password, if the user entered one.
		if (!TextUtils.isEmpty(password) && !RMUtils.isPasswordValid(password)) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid username 
		if (TextUtils.isEmpty(userName)) { 
			mUserNameView.setError(getString(R.string.error_field_required));
			focusView = mUserNameView;
			cancel = true;
		} else if (!RMUtils.isUserNameValid(userName)) {
			mUserNameView.setError(getString(R.string.error_invalid_userName));
			focusView = mUserNameView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			showProgress(true);
			mAuthTask = new UserLoginTask(userName, password);
			mAuthTask.execute((Void) null);
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
	public class UserLoginTask extends AsyncTask<Void, Void, CLMate> {

		private final String mUserName;
		private final String mPassword;

		UserLoginTask(String userName, String password) {
			mUserName = userName;
			mPassword = password;
			
		}

		@Override
		protected CLMate doInBackground(Void... params) {
			CLMate mate = null;
			try {
				mate = Connector.doLogin(mUserName, mPassword);
				if(null != mate){
					RMLog.d(getClass(), "mate id: "+mate.getMateId());
					RMDBHelper helper = RMDBHelper.getHelper(LoginActivity.this);
					RMDBUtil.getMateDao(helper).createOrUpdate(mate);
					List<CLRoom> inRooms = mate.getInRooms();
					if(null != inRooms){
						for(CLRoom room:inRooms){
							RMDBUtil.getRoomDao(helper).createOrUpdate(room);
							RMDBUtil.getRoomMateDao(helper).createIfNotExists(new CLRoomMate(room, mate));
						}
					}
					return mate;
				}else{
					RMLog.d(getClass(), "mate is null ");
				}
			} catch (SQLException e) {
				RMLog.e(getClass(), "IOException");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
			
		}

		@Override
		protected void onPostExecute(final CLMate mate) {
			mAuthTask = null;
			showProgress(false);

			if (null != mate) { 
				Intent homeActivityIntent = new Intent(LoginActivity.this,HomeActivity.class);
				String json = null;
				try {
					json = new JacksonFactory().toPrettyString(mate);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(null != json){
					homeActivityIntent.putExtra(RMClientConstants.BK_MATE_JSON,json);
					startActivity(homeActivityIntent);
					finish();
				}
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
