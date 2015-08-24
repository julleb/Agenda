package se.kth.csc.iprog.dinnerplanner.model;

import se.kth.csc.iprog.dinnerplanner.android.DinnerPlannerApplication;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String> {
	private final Activity context;
	private final String[] dishNames;
	private final Integer[] images;
	private int selectedPos = -1;
	private RadioButton selectedRb;
	private Dish selectedDish;
	private float prevCost;
	private float thisCost;
	private TextView txtTitle;
	private ImageView imageView;
	private int position;
	DinnerModel model;
	DinnerPlannerApplication DPA;

	public CustomList(Activity context, String[] dishNames, Integer[] images) {
		super(context, R.layout.list_item, dishNames);
		this.context = context;
		this.dishNames = dishNames;
		this.images = images;

	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		this.position = position;
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.list_item, null, true);
		txtTitle = (TextView) rowView.findViewById(R.id.list_text);
		imageView = (ImageView) rowView.findViewById(R.id.list_image);
		final RadioButton rb = (RadioButton) rowView
				.findViewById(R.id.list_item_radiobutton);
		imageView.setImageResource(images[position]);
		txtTitle.setText(dishNames[position]);

		final TextView people = (TextView) context
				.findViewById(R.id.listviewtv);

		final int numberOfPeople = Integer
				.parseInt(people.getText().toString());
		DPA = ((DinnerPlannerApplication) context.getApplicationContext());
		model = ((DinnerPlannerApplication) context.getApplication()).getModel();
		
		
		//alertdialog för när man klickar på bilden
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				View alertView = View.inflate(v.getContext(), R.layout.alert_dish_description, null);
				AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
				alert.setTitle(model.getDishByName(dishNames[position]).getName());
				alert.setView(alertView);
				ImageView alertImage = (ImageView) alertView.findViewById(R.id.alertImage);
				alertImage.setImageResource(images[position]);
				TextView alertText = (TextView) alertView.findViewById(R.id.alertText);
				alertText.setText(model.getDishByName(dishNames[position]).getDescription());
				
				
				
				//ok knapp för alerten
				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}

				});
				alert.show();
			}
			
		});
		
		//alertdialog om man klickar på texten
		LinearLayout linLay = (LinearLayout) rowView.findViewById(R.id.listTextLayout);
		linLay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				View alertView = View.inflate(v.getContext(), R.layout.alert_dish_description, null);
				AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
				alert.setTitle(model.getDishByName(dishNames[position]).getName());
				alert.setView(alertView);
				ImageView alertImage = (ImageView) alertView.findViewById(R.id.alertImage);
				alertImage.setImageResource(images[position]);
				TextView alertText = (TextView) alertView.findViewById(R.id.alertText);
				alertText.setText(model.getDishByName(dishNames[position]).getDescription());
				
				
				
				//ok knapp för alerten
				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}

				});
				alert.show();
			}
		});
		
		
		//om man klickar på en radiobutton
		rb.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (position != selectedPos) {

					if (position != selectedPos && selectedRb != null) {
						selectedRb.setChecked(false);
					}

					thisCost = (model.getDishPrice(model.getDishByName(dishNames[position]), numberOfPeople));				
					
					if(model.getDishByName(dishNames[position]).getType() == Dish.STARTER){
						model.setStarterPrice(thisCost);
						System.out.println(model.getStarterPrice() + " starter");
					}else if(model.getDishByName(dishNames[position]).getType() == Dish.MAIN){
						model.setMainPrice(thisCost);
						System.out.println(model.getMainPrice() + " main");
					}else if(model.getDishByName(dishNames[position]).getType() == Dish.DESERT){
						model.setDesertPrice(thisCost);
						System.out.println(model.getDesertPrice() + " desert");
					}
					
					selectedPos = position;
					selectedRb = (RadioButton) v;
					selectedDish = model.getDishByName(dishNames[position]);

				}
			}
		});

		return rowView;
	}

	public Dish getSelectedDish() {

		return selectedDish;
	}
}