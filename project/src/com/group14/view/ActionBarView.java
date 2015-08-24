package com.group14.view;

import java.util.Observable;
import java.util.Observer;

import android.app.ActionBar;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pl4nn3r3000.R;

/**
 * View class for the actionbar
 *
 *
 */
public class ActionBarView implements Observer{	
	
	Activity activity;
	
	public ActionBarView(Activity activity, int hasTrash){
		final ViewGroup actionBarLayout = (ViewGroup) activity.getLayoutInflater().inflate(R.layout.action_bar_layout, null);
		
		this.activity = activity;
		
		buildComponents(actionBarLayout, activity, hasTrash);
	}
	
	/**
	 * building all the components in the actionbar.
	 * hasTrash is a int, depends if its gonna be invisible or not.
	 * @param actionBarLayout
	 * @param activity
	 * @param hasTrash
	 */
	private void buildComponents(ViewGroup actionBarLayout, Activity activity, int hasTrash) {
		final ActionBar actionBar = activity.getActionBar();
	    actionBar.setDisplayShowHomeEnabled(false);
	    actionBar.setDisplayShowTitleEnabled(false);
	    actionBar.setDisplayShowCustomEnabled(true);
	    actionBar.setCustomView(actionBarLayout);
		
		actionBarLayout.findViewById(R.id.trash).setVisibility(hasTrash);	
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}
	
	/**
	 * returns the imageView of the Trashcan
	 * @return
	 */
	public ImageView getTrashImageView(){
		ImageView trash = (ImageView) activity.findViewById(R.id.trash);
		return trash;
	}

	
}
