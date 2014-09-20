package com.arshu.roommate.client.view;

import java.io.IOException;
import java.sql.SQLException;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arshu.roommate.client.R;
import com.arshu.roommate.client.db.RMDBHelper;
import com.arshu.roommate.client.db.RMDBUtil;
import com.arshu.roommate.client.db.entity.CLMate;
import com.arshu.roommate.client.util.RMClientConstants;
import com.arshu.roommate.client.util.RMLog;
import com.google.api.client.json.jackson2.JacksonFactory;

public class HomeActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		//TODO: commenting for testing: CLMate mate = getEntityMateFromBundle(getIntent().getExtras());
		
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}
	
	public void getAllRooms(CLMate mate ){
		try {
			CLMate newEntityMate = RMDBUtil.getMateDao(RMDBHelper.getHelper(this)).queryForSameId(mate);
			if(newEntityMate != null){
				RMLog.d(getClass(), "not empty:"+newEntityMate.getUserName());
			}else{
				RMLog.e(getClass(), "newEntityMate empty!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public CLMate getEntityMateFromBundle(Bundle extras ){
		CLMate mate = null;
		if(extras != null){
			String json = extras.getString(RMClientConstants.BK_MATE_JSON);
			try {
				mate = new JacksonFactory().fromString(json, CLMate.class);
			} catch (IOException e) {
				RMLog.unexpted(this.getClass(), "IOException while creating json from ");
				e.printStackTrace();
				finish();
			}
		}else{
			RMLog.unexpted(this.getClass(), "No extras with bundle");
			finish();
		}
		
		if(mate != null){
			RMLog.d(getClass(), "not empty:"+mate.getUserName());
		}else{
			RMLog.e(getClass(), "mate empty!");
		}
		return mate;
		
	}
	
	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		BaseFragment fragment = PlaceholderFragment.newInstance(position + 1);
		if(position == 0){
			fragment = new ListRoomFragment();
		}
		
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,fragment).commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.home, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends BaseFragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_home, container,
					false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((HomeActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

}
