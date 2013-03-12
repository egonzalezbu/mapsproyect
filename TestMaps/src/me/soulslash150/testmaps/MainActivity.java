package me.soulslash150.testmaps;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity implements LocationListener {
	 private static final String MAP_URL = "http://gmaps-samples.googlecode.com/svn/trunk/articles-android-webmap/simple-android-map.html";
	 private WebView webView;
	 private Location mostRecentLocation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getLocation();
		setupWebView();
	}
	 /** Sets up the WebView object and loads the URL of the page **/
	private void setupWebView(){
		final String centerURL = "javascript:centerAt(" +
		  mostRecentLocation.getLatitude() + "," +
		  mostRecentLocation.getLongitude()+ ")";
		  webView = (WebView) findViewById(R.id.webView1);
		  webView.getSettings().setJavaScriptEnabled(true);
		  //Wait for the page to load then send the location information
		  webView.setWebViewClient(new WebViewClient());
		  webView.loadUrl(MAP_URL);
		  /** Allows JavaScript calls to access application resources **/
		  webView.addJavascriptInterface(new JavaScriptInterface(), "android");
		}
		/** Sets up the interface for getting access to Latitude and Longitude data from device
		 **/
		private class JavaScriptInterface {
		  public double getLatitude(){
		    return mostRecentLocation.getLatitude();
		  }
		  public double getLongitude(){
		    return mostRecentLocation.getLongitude();
		  }
		}
	  
	  private void getLocation() {
		    LocationManager locationManager =
		      (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		    Criteria criteria = new Criteria();
		    criteria.setAccuracy(Criteria.ACCURACY_FINE);
		    String provider = locationManager.getBestProvider(criteria,true);
		    //In order to make sure the device is getting the location, request updates.
		    locationManager.requestLocationUpdates(provider, 1, 0, this);
		    mostRecentLocation = locationManager.getLastKnownLocation(provider);
		  }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}
