package com.group14.view;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pl4nn3r3000.R;
import com.group14.model.AgendaApplication;
import com.group14.model.AgendaModel;
import com.group14.model.Day;


/**
 * AllDayListView 
 * View for the days list.
 * @author julle
 *
 */
public class AllDaysListView implements Observer {

	private Activity activity;
	private View view;
	
	private View listItemView;
	private TextView dayTitle;
	private TextView dayDescription;
	private ImageView dayDelete;
	private LinearLayout clickLayout;
	public int position;
	private AgendaModel model;
	
	
	
	public AllDaysListView(Activity activity, AgendaModel model, View view,int position){
		
		this.position = position;
		this.view = view;
		this.activity = activity;
		this.model = model;
		
		buildComponents();
		setResourcesForComponents(position);
		
	}
	
	/**
	 * builds the components in the list
	 */
	private void buildComponents(){
		
		LayoutInflater inflater = activity.getLayoutInflater();
		listItemView = inflater.inflate(R.layout.all_days_list_item, null, true);
		
		dayTitle = (TextView) listItemView.findViewById(R.id.day_date);
		dayDescription = (TextView) listItemView.findViewById(R.id.day_description);
		dayDelete  =  (ImageView) listItemView.findViewById(R.id.day_delete);
		clickLayout = (LinearLayout) listItemView.findViewById(R.id.dayClickLayout);

	}
	
	/**
	 * return hte lsitItemView
	 * @return
	 */
	public View getListItemView(){
		return listItemView;
	}
	
	public LinearLayout getClickLayout(){
		return clickLayout;
	}
	
	
	/**
	 * set the resources for all the components in the list
	 * @param position
	 */
	private void setResourcesForComponents(int position) {	
		Day selectedDay = model.getDays().get(position);
		
		dayTitle.setText(selectedDay.getDateString());
		dayDescription.setText(selectedDay.getNumberOfActivities() + " activities across " + selectedDay.getNumberOfHours() + " hours.");		
	}
	
	/**
	 * returns the ImageView of dayDelete
	 * @return
	 */
	public ImageView getDayDelete(){
		
		return dayDelete;
	}
	
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}
	
	

}
