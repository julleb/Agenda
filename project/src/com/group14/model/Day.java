package com.group14.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class Day extends Observable implements Serializable{

	/**
	 * the start of the agenda in min, counted from midnight
	 */
	int day;
	int month;
	int year;

	private boolean[] positions;
	ArrayList<EventActivity> activities;

	public Day(int day, int month, int year) {
		positions = new boolean[19];
		activities = new ArrayList<EventActivity>();
		fillListWithNullElements();
		this.day = day;
		this.month = month;
		this.year = year;
	}

	/**
	 * fills the list with null elements
	 */
	private void fillListWithNullElements() {
		for (int i = 0; i < 19; i++) {
			activities.add(null);
		}

	}

	public ArrayList<EventActivity> getActivities() {
		return activities;
	}

	public int getNumberOfActivities() {
		int sum = 0;
		for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i) != null) {
				sum++;
			}
		}
		return sum;
	}

	public int getNumberOfHours() {
		int sum = 0;

		for (int i = 0; i < positions.length; i++) {
			if (positions[i] == true) {
				sum++;
			}
		}

		return sum;
	}

	/**
	 * get day
	 * 
	 * @return
	 */
	public int getDay() {
		return day;
	}

	public boolean[] getPositionBoolean() {
		return positions;
	}

	/**
	 * set day
	 * 
	 * @param day
	 */
	public void setDay(int day) {
		this.day = day;
		setChanged();
		notifyObservers("DayChanged");
	}

	/**
	 * get month
	 * 
	 * @return
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * set Month of a day
	 * 
	 * @param month
	 */
	public void setMonth(int month) {
		this.month = month;
		setChanged();
		notifyObservers("MonthChanged");
	}

	/**
	 * get the year of the day
	 * 
	 * @return
	 */
	public int getYear() {
		return year;
	}

	/**
	 * set the year of the day
	 * 
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
		setChanged();
		notifyObservers("YearChanged");
	}

	/**
	 * get date in a string
	 * 
	 * @return
	 */
	public String getDateString() {
		String monthString;

		switch (month) {
		case 1:
			monthString = "January";
			break;
		case 2:
			monthString = "February";
			break;
		case 3:
			monthString = "March";
			break;
		case 4:
			monthString = "April";
			break;
		case 5:
			monthString = "May";
			break;
		case 6:
			monthString = "June";
			break;
		case 7:
			monthString = "July";
			break;
		case 8:
			monthString = "August";
			break;
		case 9:
			monthString = "September";
			break;
		case 10:
			monthString = "October";
			break;
		case 11:
			monthString = "November";
			break;
		case 12:
			monthString = "December";
			break;

		default:
			monthString = "Whatup, some kind of error yo";
			break;
		}

		return day + " " + monthString + " " + year;
	}

	/**
	 * returns the total length of the acitivities in a day in minutes
	 */
	public int getTotalLength() {
		int result = 0;
		for (EventActivity act : activities) {
			result += act.getLength();
		}
		return result;
	}

	/**
	 * returns the length (in minutes) of activities of certain type
	 */
	public int getLengthByType(int type) {
		int result = 0;
		for (EventActivity act : activities) {
			if (act.getType() == type) {
				result += act.getLength();
			}
		}
		return result;
	}

	/**
	 * adds an activity to specific position this method will be called when
	 * needed from the model don't call it directly
	 */
	public boolean addActivity(EventActivity act, int position) {

		boolean isEmpty = false;

		// controls if the activity is being placed out of bounds
		// and if there is already an activity on that spot
		if (positions.length >= position + act.length) {
			for (int i = position; i < position + act.length; i++) {
				if (positions[i] == true) {
					isEmpty = false;
					break;
				}
				isEmpty = true;
			}
		}

		// if the spot is empty and not out of bounds
		// complete the add
		if (isEmpty) {

			activities.remove(position);
			activities.add(position, act);

			for (int i = position; i < position + act.length; i++) {
				positions[i] = true;
			}

			setChanged();
			notifyObservers("ActivityAdded");
			return true;
		}
		return false;
	}

	/**
	 * check if the time is free for booking
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean checkIfTimeIsEmpty(int startTime, int endTime) {

		for (EventActivity act : activities) {
			if (startTime > act.getStartTime() && startTime < act.getEndTime())
				return false;
			else if (endTime > act.getStartTime() && endTime < act.getEndTime())
				return false;
			else if (startTime < act.getStartTime()
					&& endTime > act.getEndTime())
				return false;
		}
		return true;
	}

	/**
	 * removes an activity from specific position this method will be called
	 * when needed from the model don't call it directly
	 */
	public void removeActivity(int position) {

		EventActivity act = activities.remove(position);
		activities.add(position, null);

		if (act != null) {
			for (int i = position; i < position + act.length; i++) {
				positions[i] = false;
			}
		}

		setChanged();
		notifyObservers("ActivityRemoved");
	}

	/**
	 * moves activity inside one day this method will be called when needed from
	 * the model don't call it directly
	 */
	public void moveActivity(int oldPosition, int newPosition) {
		if (newPosition > oldPosition) {
			newPosition--;
		}
		EventActivity act = activities.remove(oldPosition);
		activities.add(newPosition, act);
		setChanged();
		notifyObservers("ActivityMoved");
	}

	public String dayToString() {
		String s = "size = " + activities.size();

		for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i) != null) {
				s += activities.get(i).getName() + " ";
			} else if (activities.get(i) == null) {
				s += "null ";
			}
		}

		return s;
	}
}
