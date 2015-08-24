package com.group14.view;

import java.util.Observable;
import java.util.Observer;

import com.example.pl4nn3r3000.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScheduleListView implements Observer {

	private View view;
	private Activity activity;
	private View listItemView;
	private LinearLayout listItemHolder;
	private TextView timeTextView;
	private TextView descrTextView;
	private ImageView hourImageView;
	

	public ScheduleListView(Activity activity, View view, int position) {

		this.view = view;
		this.activity = activity;
		

		buildComponents();

	}
	
	/**
	 * build the components for the view
	 */
	private void buildComponents() {
		LayoutInflater inflater = activity.getLayoutInflater();
		listItemView = inflater.inflate(R.layout.hour_list_item, null, true);
		
		listItemHolder = (LinearLayout) listItemView.findViewById(R.id.hour);
		timeTextView = (TextView) listItemView.findViewById(R.id.time);
		descrTextView = (TextView) listItemView.findViewById(R.id.hour_description);
		hourImageView = (ImageView) listItemView.findViewById(R.id.hour_image);
		
		
		
	}
	
	/**
	 * returns the view of a listitem
	 * @return
	 */
	public View getListItemView() {
		return listItemView;
	}

	/**
	 * returns the layout holding the listitem
	 * @return
	 */
	public LinearLayout getListItemHolder() {
		return listItemHolder;
	}

	/**
	 * returns the textview displaying the time 
	 * @return
	 */
	public TextView getTimeTextView() {
		return timeTextView;
	}

	/**
	 * returns the description text
	 * @return
	 */
	public TextView getDescrTextView() {
		return descrTextView;
	}

	/**
	 * returns the icon
	 * @return
	 */
	public ImageView getHourImageView() {
		return hourImageView;
	}

	/**
	 * not used update method
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
	

}
