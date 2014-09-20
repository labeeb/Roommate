package com.arshu.roommate.client.adaptor;

import com.arshu.roommate.client.R;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class RoomCursorAdaptor extends CursorAdapter {

	public RoomCursorAdaptor(Context context, Cursor c) {
		super(context, c, true);//  autoRequer
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView d = (TextView)view.findViewById(android.R.id.text1);
		d.setText(cursor.getString(cursor.getColumnIndex("name")));
		
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
		return rowView;
	}



}
