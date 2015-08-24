package com.group14.model;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import android.app.Application;

public class AgendaApplication extends Application {

	private AgendaModel model;

	public AgendaModel getModel() {

		return model;
	}
	
	
	
	@Override
	public void onCreate() {
		
		try {
			FileInputStream fis = openFileInput("savefile");
			ObjectInputStream is = new ObjectInputStream(fis);
			AgendaModel model = (AgendaModel) is.readObject();
			setModel(model);
			is.close();
				
		} catch (Exception e) {
			model = new AgendaModel();
			e.printStackTrace();
		}
		
		super.onCreate();
	}



	/**
	 * 
	 * @param m
	 */
	public void setModel(AgendaModel model) {

		this.model = model;
	}	
	
}
