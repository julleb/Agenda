package se.kth.csc.iprog.dinnerplanner.android.view;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.model.CustomList;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.android.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ApetizerView implements Observer {
	Activity activity;
	DinnerModel model;
	View view;
	TextView costView;
	ApetizerController controller;
	ListView listView;
	private CustomList adapter;

	public ApetizerView(View view, DinnerModel model, Activity activity) {

		this.activity = activity;
		this.model = model;
		model.addObserver(this);
	
		
		costView = (TextView) activity.findViewById(R.id.costTv);
		costView.setText(model.getTotalPrice() + "");
		
		//model.setPrice(model.getCurrentPrice());
		
		buildComponents();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		costView.setText(model.getTotalPrice() + "");
	}
	
	private void buildComponents() {
		listView = (ListView) activity.findViewById(R.id.listView1);

		TextView tv = (TextView) activity.findViewById(R.id.listviewtv);
		tv.setText(model.getNumberOfGuests() + "");

		Set<Dish> apetizers = model.getDishesOfType(Dish.STARTER);
		Dish[] dishArray = new Dish[apetizers.size()];
		int i = 0;

		// plockar från set till array
		for (Dish dish : apetizers) {
			dishArray[i] = dish;
			i++;
		}

		String[] dishNames = new String[dishArray.length];
		Integer[] images = new Integer[dishArray.length];
		for (i = 0; i < dishArray.length; i++) {
			dishNames[i] = dishArray[i].getName();
			images[i] = dishArray[i].getImage();
		}

		adapter = new CustomList(activity, dishNames, images);
		listView.setAdapter(adapter);

	}
	
	public CustomList getAdapter(){
		return adapter;
	}
}
