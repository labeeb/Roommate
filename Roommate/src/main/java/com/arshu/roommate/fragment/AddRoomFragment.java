package com.arshu.roommate.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arshu.roommate.R;
import com.arshu.roommate.bean.MateInRoom;
import com.arshu.roommate.bean.Room;
import com.arshu.roommate.bean.TaskCompleteCallBack;
import com.arshu.roommate.exception.RMException;
import com.arshu.roommate.util.RMConstants.TaskStatus;
import com.arshu.roommate.util.RMConstants.UserType;
import com.arshu.roommate.util.RMLog;
import com.parse.ParseObject;

public class AddRoomFragment extends RMBaseFragment implements OnClickListener {
	private Button btnSave;
	private EditText etRoomName;
	private EditText etRoomDescription;

    public AddRoomFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_room, container, false);
        btnSave = (Button) rootView.findViewById(R.id.fragment_addroom_btn_save);
        etRoomName = (EditText) rootView.findViewById(R.id.fragment_addroom_et_roomname);
        etRoomDescription = (EditText) rootView.findViewById(R.id.fragment_addroom_et_roomdescription);
        
        btnSave.setOnClickListener(this);
        
        return rootView;
    }

	@Override
	public void onClick(View v) {
		String name = etRoomName.getText().toString(); 
		String description = etRoomDescription.getText().toString();
		RMLog.d(this.getClass(), "name:"+name+"and description:"+description);
		final Room room = new Room(name, description,getCurrentMate().getParseObject());
		room.saveInBackground(new TaskCompleteCallBack() {
			
			@Override
			public void onComplete(TaskStatus status,ParseObject parseObject, RMException exception) {
				room.setParseObject(parseObject);
				RMLog.d(AddRoomFragment.class, "on Complete of save Room with objectId:"+parseObject+" status:"+status);
				if(status == TaskStatus.SCUCCESS){
					final MateInRoom mateInRoom = new MateInRoom(getCurrentMate(), room, UserType.ADMIN);
					mateInRoom.saveInBackground(new TaskCompleteCallBack() {
						
						@Override
						public void onComplete(TaskStatus status, ParseObject parseObject,
								RMException exception) {
							RMLog.d(AddRoomFragment.class, "on Complete of save MateInRoom with objectId:"+parseObject+" status:"+status);
						}
					});
				}else{
					RMLog.unexpected(AddRoomFragment.class,"room.saveInBackground not scuccess");
				}
			}
		});
	}
}


