package com.arshu.roommate.client.view;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.arshu.roommate.client.R;
import com.arshu.roommate.client.adaptor.RoomCursorAdaptor;
import com.arshu.roommate.client.loader.RoomCursorLoader;

public class ListRoomFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>  {

	private static final int URL_LOADER = 0;
	
	private ListView roomListView;
	private RoomCursorAdaptor adapter ;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_list_rooms,
				container, false);
		roomListView = (ListView) rootView.findViewById(R.id.listRoom_list_lv);

//		ArrayAdapter<String> adapter= new ArrayAdapter<String>(getActivity(),
//				android.R.layout.simple_list_item_1,listContent);

		
//		 adapter = 
//			      new SimpleCursorAdapter(
//			            getActivity(), 
//			            android.R.layout.simple_list_item_1, 
//			            null, 
//			            new String[] { "name" }, 
//			            new int[] { android.R.id.text1 }, 
//			            0);
		
		adapter = new RoomCursorAdaptor(getActivity(), null);


        // CREATE THE ADAPTER USING THE CURSOR POINTING TO THE DESIRED DATA AS WELL AS THE LAYOUT INFORMATION
       // SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.list_example_entry, cursor, columns, to);

		
		roomListView.setAdapter(adapter);
		
		getLoaderManager().initLoader(URL_LOADER, null, this);
		
		return rootView;
	}

	
	@Override
	public Loader<Cursor> onCreateLoader(int loaderID, Bundle bundle) {
		switch (loaderID) {
        case URL_LOADER:
            // Returns a new CursorLoader
            return new RoomCursorLoader(getActivity());
        default:
            // An invalid id was passed in
            return null;
    }
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		adapter.swapCursor(arg1);
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}
}
