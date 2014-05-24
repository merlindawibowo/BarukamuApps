package com.merlinda.barukamu;

import android.app.Application;

/**
 * Created by Merlinda Wibowo on 24/04/14.
 */
public class MainAplication extends Application{
	private Dashboard dashboard;

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}
	
	

}
