package com.group14.view;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pl4nn3r3000.R;
import com.group14.controller.SelectedDayFragment;


/**
 * The selecteddayFragment View. 
 * @author julle
 *
 */
public class SelectedDayFragmentView implements Observer{

	SelectedDayFragment fragment;
	private View view;
	private ListView listView;
	private TextView dayTitle;
	
	public SelectedDayFragmentView(SelectedDayFragment fragment,View view){
		this.view = view;
		this.fragment=fragment;
		buildComponents();
	}
	
	/**
	 * set the components
	 */
	private void buildComponents(){
		
		listView = (ListView) view.findViewById(R.id.selectedDayListView);  
		dayTitle = (TextView) view.findViewById(R.id.selectedDayTextView);		
		
		
	}
	
	/**
	 * returns the dayTitle TextView
	 * @return
	 */
	public TextView getDayTitleTextView(){
		
		return dayTitle;
	}
	
	/**
	 * returns the View
	 * @return
	 */
	public View getView(){
		
		return view;
	}
	
	/**
	 * returns the listView
	 * @return
	 */
	public ListView getListView(){
		
		return listView;
	}

	@Override
	public void update(Observable observable, Object data) {
		
		String s = data.toString();
		System.out.println(s);
		
		if(s.equals("ActivityRemoved")){
			fragment.adapter.notifyDataSetChanged();
		}
		
	}
	
}
