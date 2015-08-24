package com.group14.view;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pl4nn3r3000.R;
import com.group14.controller.AllDaysFragment;
import com.group14.controller.EventActivityList;
import com.group14.controller.HorizontalListView;
import com.group14.model.AgendaModel;
import com.group14.model.EventActivity;

/**
 * Our MainActivityView.
 * 
 * @author julle
 * 
 */
public class MainActivityView implements Observer {

	private Activity activity;
	private AgendaModel model;
	private ActionBarView actionBarView;

	private EventActivityList adapter;
	private EventActivity[] parkedEvents;
	private int position;
	private HorizontalListView listview;
	private List<String> activityNames;
	private AllDaysFragment frag;

	private Button newActivityButton;

	public MainActivityView(Activity activity, AgendaModel model, List<String> activityNames) {
		this.activityNames = activityNames;
		this.activity = activity;
		this.model = model;
		
		buildActionBar();
		buildComponents();
		buildFragment();
	}

	/**
	 * creates the actionbar
	 */
	private void buildActionBar() {

		actionBarView = new ActionBarView(activity, ViewGroup.VISIBLE);
	}

	/**
	 * returns the actionBar
	 * 
	 * @return
	 */
	public ActionBarView getActionBar() {

		return actionBarView;

	}

	/**
	 * get the acitivtyNames list. Using it to update the list
	 * 
	 * @return
	 */
	public List<String> getActivityNamesList() {

		return activityNames;
	}

	/**
	 * builds the components in. For example, creates the adapter, our
	 * horizontallist
	 */
	private void buildComponents() {

		parkedEvents = model.getParkedActivitiesArray();
		listview = (HorizontalListView) activity.findViewById(R.id.listview);
		adapter = new EventActivityList(activity, model, activityNames);
		
		System.out.println(activityNames.toString());
		listview.setAdapter(adapter);

		newActivityButton = (Button) activity
				.findViewById(R.id.newActivityButton);

	}

	/**
	 * return our horizontalListView
	 * 
	 * @return
	 */
	public HorizontalListView getListView() {

		return listview;
	}

	public EventActivityList getAdapter() {

		return adapter;
	}

	/**
	 * return the new activity button
	 * 
	 * @return
	 */
	public Button getNewActivityButton() {

		return newActivityButton;
	}

	public AllDaysFragment getFragment(){
		return frag;
	}
	
	/**
	 * builds the fragment
	 */
	private void buildFragment() {
		frag = new AllDaysFragment();
		FragmentManager manager = activity.getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.fragment_holder, frag, "alldaysfragment");
		transaction.commit();
	}

	/**
	 * update method for when the observer is notified
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		String[] strings =  arg1.toString().split(" ");
		
		if (strings[0].equals("ParkedActivityRemoved")) {
			getActivityNamesList().remove(Integer.parseInt(strings[1]));
			getAdapter().notifyDataSetChanged();
		}
		
		if(strings[0].equals("ActivityParked")){
			getActivityNamesList().add(model.getNameOfParkedActivities().get(model.getNameOfParkedActivities().size()-1));
			getAdapter().notifyDataSetChanged();
		}

	}

}
