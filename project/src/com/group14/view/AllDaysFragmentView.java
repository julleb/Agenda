package com.group14.view;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.pl4nn3r3000.R;
import com.group14.controller.AllDaysList;
import com.group14.model.AgendaApplication;
import com.group14.model.AgendaModel;
/**
 * the VIEW of alldaysfragment
 * Show every day that is created.
 */

public class AllDaysFragmentView implements Observer {
	
	private View view;
	private ListView listView;
	private AllDaysList adapter;
	private Activity activity;
	private List<String> dayTitles;
	private AgendaModel model;
	
	private Button newDayButton;
	
	public AllDaysFragmentView(Activity activity, AgendaModel model, View view){
		this.activity = activity;
		this.view = view;
		this.model = model;
		buildComponents();
	}
	
	
	/**
	 * returns the view
	 * @return
	 */
	public View getView(){
		return view;
	}
	
	/**
	 * builds the components in the view
	 */
	private void buildComponents(){
		
		dayTitles = model.getNameOfDays();
		newDayButton = (Button) view.findViewById(R.id.newDayButton);
		listView = (ListView) view.findViewById(R.id.allDaysListView);
		adapter = new AllDaysList(activity, dayTitles);
		listView.setAdapter(adapter);
				
	}
	
	/**
	 * return the new day button 
	 * @return
	 */
	public Button getNewDayButton(){
		
		return newDayButton;
	}
	
	/**
	 * returns the adapter for the listView
	 * @return
	 */
	public AllDaysList getAdapter(){
		return adapter;
	}
	
	@Override
	public void update(Observable observable, Object data) {
		
		if(data.equals("DayRemoved")){
			adapter.notifyDataSetChanged();
		}
		
	}
	
	public List<String> getDayTitles(){
		return dayTitles;
	}

	public ListView getListView(){
		return listView;
	}
}
