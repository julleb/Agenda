package com.group14.controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pl4nn3r3000.R;
import com.group14.model.AgendaModel;
import com.group14.model.EventActivity;

public class EventActivityList extends ArrayAdapter<String>{

	private Activity context;
	private AgendaModel model;
	private int position;
	private TextView eventTitle;
	private TextView eventDuration;
	private ImageView eventImage;
	private View listItemView;

	public EventActivityList(Activity context, AgendaModel model,
			List<String> activityNames) {
		super(context, R.layout.list_item, activityNames);
		this.context = context;
		this.model = model;

	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		this.position = position;
		LayoutInflater inflater = context.getLayoutInflater();
		listItemView = inflater.inflate(R.layout.list_item, null, true);

		buildComponents();
		setResourcesForComponents();		

		return listItemView;
	}

	/**
	 * Build the three components for the list items
	 */
	private void buildComponents() {
		eventTitle = (TextView) listItemView.findViewById(R.id.list_item_title);
		eventDuration = (TextView) listItemView
				.findViewById(R.id.list_item_duration);
		eventImage = (ImageView) listItemView
				.findViewById(R.id.list_item_image);
	}

	/**
	 * Sets the resources for all of the components created in the list item
	 */
	private void setResourcesForComponents() {
		EventActivity selectedEvent = model.getParkedActivitiesArray()[position];
		eventTitle.setText("" + selectedEvent.getName());
		eventDuration.setText("" + selectedEvent.getLength() + "h");
		eventImage.setImageResource(selectedEvent.getImage());
	}


}
