package com.arshu.roommate.fragment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.arshu.roommate.R;
import com.arshu.roommate.bean.Room;
import com.arshu.roommate.exception.RMException;
import com.arshu.roommate.task.GetAllRooms;
import com.arshu.roommate.task.GetAllRooms.GetRoomsCallBack;
import com.arshu.roommate.util.RMConstants;
import com.arshu.roommate.util.RMLog;

public class RoomListFragment extends RMBaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

	private ListView listview;

	public RoomListFragment() {
	}

	@Override
	public void onResume() {
		getActivity().registerReceiver(roomListBroadcastReceiver, new IntentFilter(RMConstants.BA_ROOMLIST_UPDATED));
		super.onResume();
	}

	@Override
	public void onPause() {
		getActivity().unregisterReceiver(roomListBroadcastReceiver);
		super.onPause();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_listroom, container, false);

		listview = (ListView) rootView.findViewById(R.id.listrroom_lv_rooms);

		getActivity().setTitle(getCurrentMate().getEmail());
		
		getLoaderManager().initLoader(RMConstants.LOADER_ROOMLIST, null, this);

//		new GetAllRooms(getCurrentMate(), new GetRoomsCallBack() {
//
//			@Override
//			public void onSuccess(List<Room> rooms) {
//				RMLog.d(RoomListFragment.class, "No of rooms:" + rooms.size());
//
//				ArrayAdapter<Room> listAdapter = new ArrayAdapter<Room>(getActivity(), android.R.layout.simple_list_item_1, rooms);
//				listview.setAdapter(listAdapter);
//				
////				for(Room room: rooms){
////					try {
////						room.save(getActivity());
////						RMLog.d(RoomListFragment.class, "laoder save :"+room);
////					} catch (SQLException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
////				}
//			}
//
//			@Override
//			public void onError(RMException exception) {
//				// TODO Auto-generated method stub
//
//			}
//		});

		return rootView;
	}

	private BroadcastReceiver roomListBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
//			if (action.equalsIgnoreCase(TheService.DOWNLOADED)) {
//				// send message to activity
//			}
		}
	};

	
	/*
	* Callback that's invoked when the system has initialized the Loader and
	* is ready to start the query. This usually happens when initLoader() is
	* called. The loaderID argument contains the ID value passed to the
	* initLoader() call.
	*/
	@Override
	public Loader<Cursor> onCreateLoader(int loaderID, Bundle bundle){
		/*
	     * Takes action based on the ID of the Loader that's being created
	     */
	    switch (loaderID) {
	        case RMConstants.LOADER_ROOMLIST:
	            // Returns a new CursorLoader
	            return new DumbLoader( getActivity());
	        default:
	            // An invalid id was passed in
	            return null;
	    }
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
		// TODO Auto-generated method stub
		if(null != cursor){
			List<Room> rooms = new ArrayList<Room>();
			while(cursor.moveToNext()){
				Room room = Room.loadFromCursor(cursor);
				rooms.add(room);
			}

			ArrayAdapter<Room> listAdapter = new ArrayAdapter<Room>(getActivity(), android.R.layout.simple_list_item_1, rooms);
			listview.setAdapter(listAdapter);
			RMLog.d(RoomListFragment.class, "laoder onLoadFinished cursor count:"+rooms.size());
		}else{
			RMLog.d(RoomListFragment.class, "laoder onLoadFinished cursor null");
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub

	}
	
	public static class DumbLoader extends CursorLoader {
	    private static final String TAG = "DumbLoader";
	 
	    public DumbLoader(Context context) {
	        super(context);
	    }
	 
	    @Override
	    public Cursor loadInBackground() {
	        // this is just a simple query, could be anything that gets a cursor
	        try {
				return Room.getCursorForRoomList(getContext());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return null;
	    }
	    
	    
	}

}
