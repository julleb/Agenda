package com.group14.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.example.pl4nn3r3000.R;
import com.group14.model.AgendaApplication;
import com.group14.model.AgendaModel;
import com.group14.model.EventActivity;
import com.group14.view.ActionBarView;
import com.group14.view.MainActivityView;

/**
 * MainActivity, our starting activity, with all the fragments, the
 * scrollhorizontal list.
 */
public class MainActivity extends Activity{

	private AgendaModel model;
	private MainActivityView mainActivityView;

	private Vibrator vibe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get the application model
		model = ((AgendaApplication) this.getApplication()).getModel();
		

		mainActivityView = new MainActivityView(this, model,
				model.getNameOfParkedActivities());
		model.addObserver(mainActivityView);
		vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		setClickListenerOnListView();
		setDragListenerOnListView();
		setClickListenerOnButton();
		setOnDragOnTrashCan();

	}


	/**
	 * creates an option menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}



	/**
	 * what happens when you click an option menu item
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.about:
			View aboutView = View.inflate(this,
					R.layout.about_us, null);
			AlertDialog.Builder about = new AlertDialog.Builder(this);

			about.setTitle("About us");
			about.setView(aboutView);
			
			about.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//nothing
				}
			});

			about.show();
			break;
		case R.id.help:
			View helpView = View.inflate(this,
					R.layout.help, null);
			AlertDialog.Builder help = new AlertDialog.Builder(this);

			help.setTitle("Help");
			help.setView(helpView);
			
			help.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//nothing
				}
			});

			help.show();
			break;
		default:
			break;
		}
		return false;
	}


	/**
	 * sets the ondraglistener for the horizontal listview
	 */
	private void setDragListenerOnListView() {
		mainActivityView.getListView().setOnDragListener(new OnDragListener() {

			@Override
			public boolean onDrag(View v, DragEvent event) {

				switch (event.getAction()) {
				case DragEvent.ACTION_DRAG_STARTED:
					// nothing
					break;
				case DragEvent.ACTION_DRAG_ENTERED:
					// nothing
					break;
				case DragEvent.ACTION_DRAG_EXITED:
					// nothing
					break;
				case DragEvent.ACTION_DROP:
					// TODO
					ClipData.Item item = event.getClipData().getItemAt(0);
					String dragData = "" + item.getText();

					String[] strings = dragData.split(" ");
					
					// if the clipdata ends with a . that means it comes from
					// the vertical listview i.e proceed with drop
					if (dragData.endsWith(".")) {
						int positionsFromWithinList = Integer.parseInt(strings[0]);
						
						model.addParkedActivity(model.getSelectedDay().getActivities().get(positionsFromWithinList));
						
						model.removeActivityFromSelectedDay(positionsFromWithinList);
						
						try {
							FileOutputStream fos = openFileOutput("savefile", MODE_PRIVATE);
							ObjectOutputStream os = new ObjectOutputStream(fos);
							os.writeObject(model);
							os.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
							
					}
					break;
				case DragEvent.ACTION_DRAG_ENDED:
					// nothing
				default:
					break;
				}
				return true;
			}
		});

	}

	/**
	 * returns the view from the main activity
	 * @return
	 */
	public MainActivityView getMainActivityView() {
		return mainActivityView;
	}

	/**
	 * sets DragListener on the Horizonztal Scroll list
	 */
	private void setClickListenerOnListView() {
		mainActivityView.getListView().setOnItemLongClickListener(listener);
	}

	/**
	 * sets ClickListener on the "New Activity" button
	 */
	private void setClickListenerOnButton() {

		mainActivityView.getNewActivityButton().setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						Intent i = new Intent(getBaseContext(),
								CreateEventActivity.class);
						startActivity(i);

					}

				});
	}

	/**
	 * sets OnDrag on the trashcan
	 */
	private void setOnDragOnTrashCan() {

		mainActivityView.getActionBar().getTrashImageView()
				.setOnDragListener(new OnDragListener() {

					@Override
					public boolean onDrag(View v, DragEvent event) {

						switch (event.getAction()) {
						case DragEvent.ACTION_DRAG_STARTED:
							// nothing
							break;
						case DragEvent.ACTION_DRAG_ENTERED:
							// nothing
							break;
						case DragEvent.ACTION_DRAG_EXITED:
							// nothing
							break;
						case DragEvent.ACTION_DROP:
							// TODO
							ClipData.Item item = event.getClipData().getItemAt(
									0);
							String dragData = "" + item.getText();
							String[] strings = dragData.split(" ");
							
							//determine from the clipdata where the drag came from
							//and act accordingly
							if(strings.length == 2){
								int positionFromWithinList = Integer.parseInt(strings[0]);
								model.removeActivityFromSelectedDay(positionFromWithinList);
									
							}else{
								int position = Integer.parseInt(dragData);

								model.removeParkedActivity(position);
							}
							
							try {
								FileOutputStream fos = openFileOutput("savefile", MODE_PRIVATE);
								ObjectOutputStream os = new ObjectOutputStream(fos);
								os.writeObject(model);
								os.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							break;
						case DragEvent.ACTION_DRAG_ENDED:
							// nothing
						default:
							break;
						}
						return true;
					}

				});
	}

	/**
	 * Our class for listening when starting draging. 
	 */
	OnItemLongClickListener listener = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> adapter, View v,
				int position, long arg3) {

			ImageView image = (ImageView) v.findViewById(R.id.list_item_image);
			vibe.vibrate(250);
			DragShadow dragShadow = new DragShadow(v, image);

			ClipData data = ClipData.newPlainText("position", "" + position);
			System.out.println("POSITION : " + position);
			v.startDrag(data, dragShadow, v, 0);
			return false;
		}

	};

	/**
	 * a private class called DragShadow for managing the image getting dragged
	 * @author Niklas
	 *
	 */
	private class DragShadow extends View.DragShadowBuilder {

		Drawable dragImage;

		public DragShadow(View view, ImageView image) {
			super(view);

			dragImage = image.getDrawable();

		}

		@Override
		public void onDrawShadow(Canvas canvas) {

			dragImage.draw(canvas);
		}

		@Override
		public void onProvideShadowMetrics(Point shadowSize,
				Point shadowTouchPoint) {

			Rect rect = dragImage.getBounds();

			shadowSize.set(rect.height(), rect.width());

			shadowTouchPoint.set(rect.height() / 2, rect.width() / 2);

		}

	}
}
