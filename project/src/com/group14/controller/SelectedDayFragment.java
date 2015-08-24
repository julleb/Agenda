package com.group14.controller;

import java.util.LinkedList;
import java.util.List;

import com.example.pl4nn3r3000.R;
import com.group14.model.AgendaApplication;
import com.group14.model.AgendaModel;
import com.group14.view.SelectedDayFragmentView;

import android.app.Fragment;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemLongClickListener;


/**
 * fragment for a day. 
 * 
 * @author julle
 *
 */
public class SelectedDayFragment extends Fragment {
	
	private AllDaysFragment frag;
	SelectedDayFragmentView view;
	private LinkedList<String> scheduleTimes = new LinkedList<String>();
	public ScheduleList adapter;
	AgendaModel model;
	private Vibrator vibe;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		model = ((AgendaApplication) this.getActivity().getApplication()).getModel();
		view = new SelectedDayFragmentView(this,inflater.inflate(R.layout.selected_day_fragment_layout,container, false));
		model.addObserver(view);
		vibe = (Vibrator) this.getActivity().getSystemService(Context.VIBRATOR_SERVICE);
		initTextView();
		initScheduleTimes();
		setAdapterForList();
		view.getListView().setOnItemLongClickListener(listener);
		
		
		return view.getView();
	}
	
	
	/**
	 * onclicklistener for the items in the listview
	 */
	OnItemLongClickListener listener = new OnItemLongClickListener() {


		
		@Override
		public boolean onItemLongClick(AdapterView<?> adapter, View v,
				int position, long arg3) {
			
			if(model.getSelectedDay().getPositionBoolean()[position] == true){
				
				for (int i = position; i >= 0; i--) {
					if (model.getSelectedDay().getActivities().get(i) != null) {
						
						System.out.println("Hittat aktiviteten på pos " + i);
						
						vibe.vibrate(250);
						
						ImageView image = (ImageView) v.findViewById(R.id.hour_image);
						DragShadow dragShadow = new DragShadow(v, image);

						ClipData data = ClipData.newPlainText("position", i + " .");
						v.startDrag(data, dragShadow, v, 0);
						break;
					}

				}
			}
			
			return false;
		}

	};
	

	
	/**
	 * set the arrayadapter for the listview
	 */
	private void setAdapterForList() {
		adapter = new ScheduleList(this.getActivity(), scheduleTimes);
		view.getListView().setAdapter(adapter);
		
	}


	/**
	 * creates the list of strings for the arrayadapter
	 */
	private void initScheduleTimes() {
		for(int i = 6; i <= 24; i++){
			String s = "";
			if(i < 10){
				s = "0" + i + ":00";
			}else{
				s = i + ":00";
			}
			scheduleTimes.add(s);
		}	
		
	}

	/**
	 * init the dayTitle text.
	 */
	private void initTextView(){
		
		AgendaModel model = ((AgendaApplication) this.getActivity().getApplication()).getModel();
		view.getDayTitleTextView().setText(model.getSelectedDay().getDateString());
		
	}
	
	
	/**
	 * private dragshadow class for managing the images being dragged
	 * @author Niklas
	 *
	 */
	
	private class DragShadow extends View.DragShadowBuilder {

		Drawable dragImage;

		public DragShadow(View view, ImageView image) {
			super(view);

			dragImage = image.getDrawable();

		}

		@Override
		public void onDrawShadow(Canvas canvas) {

			dragImage.draw(canvas);
		}

		@Override
		public void onProvideShadowMetrics(Point shadowSize,
				Point shadowTouchPoint) {

			Rect rect = dragImage.getBounds();

			shadowSize.set(rect.height(), rect.width());

			shadowTouchPoint.set(rect.height() / 2, rect.width() / 2);

		}

	}	

}
