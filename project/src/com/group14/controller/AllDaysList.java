package com.group14.controller;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pl4nn3r3000.R;
import com.group14.model.AgendaApplication;
import com.group14.model.AgendaModel;
import com.group14.view.AllDaysListView;

public class AllDaysList extends ArrayAdapter<String>{

	Activity context;
	
	private View listItemView;
	private TextView dayTitle;
	private TextView dayDescription;
	private ImageView dayDelete;
	private AllDaysListView alldaysList;
	private List<String> dayTitles;
	
	int position;
	

	public AllDaysList(Activity context, List<String> dayTitles) {
		super(context, R.layout.all_days_list_item, dayTitles);
		this.dayTitles = dayTitles;
		this.context = context;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		AgendaModel model = ((AgendaApplication) context.getApplication()).getModel();
		this.position = position;
		alldaysList = new AllDaysListView(context, model, view,position);
		setClickListenerOnDeleteImage(position);
		return alldaysList.getListItemView();
	
	}
	

	/**
	 * ClickListener on the "Delete X" in the lsitView
	 */
	private void setClickListenerOnDeleteImage(int position){
		final int pos = position;
		alldaysList.getDayDelete().setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				AgendaModel model = ((AgendaApplication) context.getApplication()).getModel();
				model.removeDay(pos);
				//notifyDataSetChanged();
				dayTitles.remove(pos);
				
				try {
					FileOutputStream fos = context.openFileOutput("savefile", Context.MODE_PRIVATE);
					ObjectOutputStream os = new ObjectOutputStream(fos);
					os.writeObject(model);
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			
		});
		
		
	}
	
	public void updateDayTitles(){
		AgendaModel model = ((AgendaApplication) context.getApplication()).getModel();
		dayTitles = model.getNameOfDays();
		for(int i = 0; i<dayTitles.size(); i++){
			System.out.println(dayTitles.get(i));
		}
		System.out.println("dayTitles = " + dayTitles.size());
		System.out.println("model = " + model.getDays().size());
	}
	

}
