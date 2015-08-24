package com.group14.controller;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pl4nn3r3000.R;
import com.group14.model.AgendaApplication;
import com.group14.model.AgendaModel;
import com.group14.model.EventActivity;
import com.group14.view.ActionBarView;
import com.group14.view.CreateEventView;

public class CreateEventActivity extends Activity {

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
	Intent oldActivityIntent;
	public Activity activity;

	AgendaModel model;
	CreateEventView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_event);
		model = ((AgendaApplication) getApplication()).getModel();
		view = new CreateEventView(this.findViewById(android.R.id.content),
				model, this);
		this.activity = this;

		setClickOnDoneButton();
		setRbClickListeners();

	}

	/**
	 * creates and adds a new activity when you click the done button
	 */
	private void setClickOnDoneButton() {

		view.getDoneButton().setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (view.getActivityName().length() != 0) {
					view.getFields();
					EventActivity newEventActivity = new EventActivity(view
							.getActivityName(), view.getDescriptionText(), view
							.getDuration(), view.getActivityType());
					model.addParkedActivity(newEventActivity);
					
					try {
						FileOutputStream fos = openFileOutput("savefile", MODE_PRIVATE);
						ObjectOutputStream os = new ObjectOutputStream(fos);
						os.writeObject(model);
						os.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

					finish();
				} else {
					//shows a toast if the name field is empty
					Toast toast = Toast.makeText(activity, "Enter a name", Toast.LENGTH_SHORT);
					toast.show();
				}

			}
		});
	}

	/**
	 * creates onclick listeners for the radiobuttons.
	 * usually we would have used a radiogroup, but
	 * android doesn't support multiple line radiogroups
	 */
	private void setRbClickListeners() {

		view.getAllRadioButtons()[0].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				view.getAllRadioButtons()[0].setChecked(true);
				view.getAllRadioButtons()[1].setChecked(false);
				view.getAllRadioButtons()[2].setChecked(false);
				view.getAllRadioButtons()[3].setChecked(false);
				view.getAllRadioButtons()[4].setChecked(false);
				view.getAllRadioButtons()[5].setChecked(false);
				view.getAllRadioButtons()[6].setChecked(false);
				view.getAllRadioButtons()[7].setChecked(false);
				view.setActivityType(1);
			}
		});

		view.getAllRadioButtons()[1].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				view.getAllRadioButtons()[0].setChecked(false);
				view.getAllRadioButtons()[1].setChecked(true);
				view.getAllRadioButtons()[2].setChecked(false);
				view.getAllRadioButtons()[3].setChecked(false);
				view.getAllRadioButtons()[4].setChecked(false);
				view.getAllRadioButtons()[5].setChecked(false);
				view.getAllRadioButtons()[6].setChecked(false);
				view.getAllRadioButtons()[7].setChecked(false);
				view.setActivityType(3);

			}
		});
		view.getAllRadioButtons()[2].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				view.getAllRadioButtons()[0].setChecked(false);
				view.getAllRadioButtons()[1].setChecked(false);
				view.getAllRadioButtons()[2].setChecked(true);
				view.getAllRadioButtons()[3].setChecked(false);
				view.getAllRadioButtons()[4].setChecked(false);
				view.getAllRadioButtons()[5].setChecked(false);
				view.getAllRadioButtons()[6].setChecked(false);
				view.getAllRadioButtons()[7].setChecked(false);
				view.setActivityType(2);
			}
		});
		view.getAllRadioButtons()[3].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				view.getAllRadioButtons()[0].setChecked(false);
				view.getAllRadioButtons()[1].setChecked(false);
				view.getAllRadioButtons()[2].setChecked(false);
				view.getAllRadioButtons()[3].setChecked(true);
				view.getAllRadioButtons()[4].setChecked(false);
				view.getAllRadioButtons()[5].setChecked(false);
				view.getAllRadioButtons()[6].setChecked(false);
				view.getAllRadioButtons()[7].setChecked(false);
				view.setActivityType(4);

			}
		});
		view.getAllRadioButtons()[4].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				view.getAllRadioButtons()[0].setChecked(false);
				view.getAllRadioButtons()[1].setChecked(false);
				view.getAllRadioButtons()[2].setChecked(false);
				view.getAllRadioButtons()[3].setChecked(false);
				view.getAllRadioButtons()[4].setChecked(true);
				view.getAllRadioButtons()[5].setChecked(false);
				view.getAllRadioButtons()[6].setChecked(false);
				view.getAllRadioButtons()[7].setChecked(false);
				view.setActivityType(5);
			}
		});
		view.getAllRadioButtons()[5].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				view.getAllRadioButtons()[0].setChecked(false);
				view.getAllRadioButtons()[1].setChecked(false);
				view.getAllRadioButtons()[2].setChecked(false);
				view.getAllRadioButtons()[3].setChecked(false);
				view.getAllRadioButtons()[4].setChecked(false);
				view.getAllRadioButtons()[5].setChecked(true);
				view.getAllRadioButtons()[6].setChecked(false);
				view.getAllRadioButtons()[7].setChecked(false);
				view.setActivityType(6);
			}
		});
		view.getAllRadioButtons()[6].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				view.getAllRadioButtons()[0].setChecked(false);
				view.getAllRadioButtons()[1].setChecked(false);
				view.getAllRadioButtons()[2].setChecked(false);
				view.getAllRadioButtons()[3].setChecked(false);
				view.getAllRadioButtons()[4].setChecked(false);
				view.getAllRadioButtons()[5].setChecked(false);
				view.getAllRadioButtons()[6].setChecked(true);
				view.getAllRadioButtons()[7].setChecked(false);
				view.setActivityType(7);
			}
		});
		view.getAllRadioButtons()[7].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				view.getAllRadioButtons()[0].setChecked(false);
				view.getAllRadioButtons()[1].setChecked(false);
				view.getAllRadioButtons()[2].setChecked(false);
				view.getAllRadioButtons()[3].setChecked(false);
				view.getAllRadioButtons()[4].setChecked(false);
				view.getAllRadioButtons()[5].setChecked(false);
				view.getAllRadioButtons()[6].setChecked(false);
				view.getAllRadioButtons()[7].setChecked(true);
				view.setActivityType(8);
			}
		});

	}

}
