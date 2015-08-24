package com.group14.model;

import java.io.Serializable;
import java.util.Observable;

import android.graphics.Color;

import com.example.pl4nn3r3000.R;

public class EventActivity extends Observable implements Serializable{

	// The possible types of the activity
	public static final int WORKOUT = 1;
	public static final int MEETING = 2;
	public static final int MEAL = 3;
	public static final int PARTY = 4;
	public static final int STUDIES = 5;
	public static final int WORK = 6;
	public static final int PLEASURE = 7;
	public static final int OTHER = 8;

	String name;
	String description;

	int length; // in minutes
	int category;

	int image;
	int color;

	int start;
	int end;

	/**
	 * Creates an EventActivity
	 * 
	 * @param name
	 * @param description
	 * @param length
	 * @param category
	 */
	public EventActivity(String name, String description, int length, int category) {
		this.name = name;
		this.description = description;
		this.length = length;
		this.category = category;
		setImage(category);
		start = -1;
		end = -1;
	}

	private void setImage(int category) {
		switch (category) {
		case 1:
			image = R.drawable.workout;
			color = Color.parseColor("#ff5959");
			break;
		case 2:
			image = R.drawable.meeting;
			color = Color.parseColor("#00b4ff");
			break;
		case 3:
			image = R.drawable.meal;
			color = Color.parseColor("#45ba66");
			break;
		case 4:
			image = R.drawable.party;
			color = Color.parseColor("#ff8a00");
			break;
		case 5:
			image = R.drawable.studies;
			color = Color.parseColor("#ff53d0");
			break;
		case 6:
			image = R.drawable.work;
			color = Color.parseColor("#ffd927");
			break;
		case 7:
			image = R.drawable.pleasure;
			color = Color.parseColor("#7e00ff");
			break;
		default: 
			image = R.drawable.other;
			color = Color.parseColor("#393939");
			break;
		}
		
	}

	/**
	 * set Start Time of an EventActivity
	 * 
	 * @param start
	 */
	public void setStartTime(int start) {
		this.start = start;
	}

	/**
	 * get start Time of an EventActivity
	 * 
	 * @return
	 */
	public int getStartTime() {
		return start;
	}

	/**
	 * set end time of an EventActivity
	 * 
	 * @param end
	 */
	public void setEndTime(int end) {
		this.end = end;
	}

	/**
	 * get end time of an EventActivity
	 * 
	 * @return
	 */
	public int getEndTime() {
		return end;
	}

	/**
	 * get Name of an EventActivity
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of an EventActivity
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
		setChanged();
		notifyObservers("NameChanged");
	}

	/**
	 * Get description of an EventActivity
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set decription of an EventActivity
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
		setChanged();
		notifyObservers("DescriptionChanged");
	}

	/**
	 * Get length of an EventActivity
	 * 
	 * @return
	 */
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
		setChanged();
		notifyObservers("LengthChanged");
	}

	/**
	 * Get type of an EventActivity
	 * 
	 * @return
	 */
	public int getType() {
		return category;
	}

	/**
	 * Set an type of an EventActivity
	 * 
	 * @param type
	 */
	public void setType(int type) {
		this.category = type;
		setChanged();
		notifyObservers("TypeChanged");
	}

	
	/**
	 * 
	 * @return retruns an int that pointsTo an EventActivity image
	 */
	public int getImage() {
		return image;
	}
	
	public int getColor(){
		return color;
	}

}
