package com.group14.view;

import java.util.Observable;
import java.util.Observer;

import com.example.pl4nn3r3000.R;
import com.group14.model.AgendaModel;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;

public class CreateEventView implements Observer {

	View view;
	AgendaModel model;
	Activity activity;
	
	ActionBarView actionBar;
	TextView activityField;
	TextView descriptionField;
	Button doneButton;
	NumberPicker durationPicker;
	RadioButton rbWorkout, rbMeal, rbMeeting, rbParty, rbStudies, rbWork,
			rbPleasure, rbOther;

	int duration;
	String activityName;
	String descriptionText;
	int activityType = 8;

	public CreateEventView(View view, AgendaModel model, Activity activity) {

		this.view = view;
		this.activity = activity;
		
		model.addObserver(this);
		buildActionBar();
		setComponents();
	}
	
	/**
	 * builds the actionBar
	 */
	private void buildActionBar() {
		actionBar = new ActionBarView(activity, ViewGroup.INVISIBLE);
	}

	/**
	 * sets the COmponents in the view.
	 */
	private void setComponents() {

		activityField = (TextView) view.findViewById(R.id.acitivty_name_id);
		descriptionField = (TextView) view
				.findViewById(R.id.description_text_id);
		doneButton = (Button) view.findViewById(R.id.done_button_id);
		durationPicker = (NumberPicker) view
				.findViewById(R.id.durationPicker_id);

		durationPicker.setMaxValue(19);
		durationPicker.setMinValue(1);
		durationPicker.setWrapSelectorWheel(false);

		// radiobuttons
		rbWorkout = (RadioButton) view.findViewById(R.id.workout_rb);
		rbMeal = (RadioButton) view.findViewById(R.id.meal_rb);
		rbMeeting = (RadioButton) view.findViewById(R.id.meeting_rb);
		rbParty = (RadioButton) view.findViewById(R.id.party_rb);
		rbStudies = (RadioButton) view.findViewById(R.id.studies_rb);
		rbWork = (RadioButton) view.findViewById(R.id.work_rb);
		rbPleasure = (RadioButton) view.findViewById(R.id.pleasure_rb);
		rbOther = (RadioButton) view.findViewById(R.id.other_rb);
		rbOther.setChecked(true);

	}
	
	public RadioButton[] getAllRadioButtons(){
		
		RadioButton allButtons[] = new RadioButton[8];
		allButtons[0] = rbWorkout;
		allButtons[1] = rbMeal;
		allButtons[2] = rbMeeting;
		allButtons[3] = rbParty;
		allButtons[4] = rbStudies;
		allButtons[5] = rbWork;
		allButtons[6] = rbPleasure;
		allButtons[7] = rbOther;
		return allButtons;
	}
	/**
	 * returns the doneBUtton
	 * @return
	 */
	public Button getDoneButton(){
		
		return doneButton;
	}
	
	/**
	 * returns all the values in the field
	 */
	public void getFields(){
		
		activityName = activityField.getText().toString();
		descriptionText = descriptionField.getText().toString();
		//TODO
		duration = durationPicker.getValue();
		
	}
	
	public String getActivityName(){
		return activityField.getText().toString();
	}
	public String getDescriptionText(){
		
		return descriptionText;
	}
	public int getDuration()
	{
		return duration;
	}
	public int getActivityType(){
		return activityType;
	}
	
	public void setActivityType(int i){
		
		activityType = i;
	}
	
	
	

	@Override
	public void update(Observable arg0, Object arg1) {
		

	}

}
