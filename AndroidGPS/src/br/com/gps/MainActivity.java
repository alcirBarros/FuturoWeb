package br.com.gps;

import br.com.conexao.Conexao;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {

    LocationManager locationManager;
    String provider;
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.main);
       Button button1 = (Button) findViewById(R.id.button1);
       button1.setOnClickListener(btnMessageOnClickListener);
    }
    
//    private void ativarCoordenadas(){
//    
//    // Getting LocationManager object
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        // Creating an empty criteria object
//        Criteria criteria = new Criteria();
//
//        // Getting the name of the provider that meets the criteria
//        provider = locationManager.getBestProvider(criteria, false);
//
//        if (provider != null && !provider.equals("")) {
//
//            // Get the location from the given provider
//            Location location = locationManager.getLastKnownLocation(provider);
//           // if (location != null) {
//                StringBuilder builder = new StringBuilder();
//                builder.append("{\"id\": \"1\",\"latitude\": \"");
//             //   builder.append("-20.030442381294925"); // builder.append(String.valueOf(location.getLatitude()));
//                builder.append(((EditText)findViewById(R.id.latitude)).getText());
//                builder.append("\",\"longitude\": \"");
//             //   builder.append(String.valueOf(location.getLongitude()));
//             //   builder.append("-21.90442381294925");
//                builder.append(((EditText)findViewById(R.id.longetude)).getText());
//                builder.append("\"}");
//
//                String[] resposta = new WebService().post("http://192.168.2.92:8080/FuturoWeb/webservice/gps/post", builder.toString());
//                
//             //   String[] resposta = new WebService().get("http://192.168.2.92:8080/FuturoWeb/webservice/gps/");
//       //     }
//
//            locationManager.requestLocationUpdates(provider, 20000, 1, this);
//
//            if (location != null) {
//                onLocationChanged(location);
//            } else {
//                Toast.makeText(getBaseContext(), resposta[1].toString(), Toast.LENGTH_LONG).show();
//            }
//
//        } else {
//            Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
//        }
//    }
    
    public void updateView(Location locat) {
        Double latitude = locat.getLatitude();
        Double longitude = locat.getLongitude();

        EditText edLatitude = (EditText) findViewById(R.id.latitude);
        EditText edlongetude = (EditText) findViewById(R.id.longetude);
       
        edLatitude.setText(latitude.toString());
        edlongetude.setText(longitude.toString());
    }
    
    private void enviarCoodenadas(Double latitude, Double longitude){
    	
    	StringBuilder builder = new StringBuilder();
    	builder.append("{\"id\": \"1\",\"latitude\": \"");
    	builder.append(latitude);
    	builder.append("\",\"longitude\": \"");
    	builder.append(longitude);
    	builder.append("\"}");
    	
    	WebService webService = new WebService();
    	
    	String[] resposta = webService.post("http://192.168.2.190:8080/tcc/webservice/eventos/post", builder.toString());
    	Toast.makeText(getBaseContext(), resposta[1].toString(), Toast.LENGTH_LONG).show();

    }
    
    public void startGPS() {
        LocationManager lManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        EditText edLatitude = (EditText) findViewById(R.id.latitude);
        EditText edlongetude = (EditText) findViewById(R.id.longetude);
        
        enviarCoodenadas(new Double(edLatitude.getText().toString()) , new Double(edlongetude.getText().toString()));  
        
        LocationListener lListener = new LocationListener() {
            
        	public void onLocationChanged(Location locat) {
            	updateView(locat);
            	enviarCoodenadas(locat.getLatitude(), locat.getLongitude());       	
            }
        	
            public void onStatusChanged(String provider, int status, Bundle extras) {
            	trace("onStatusChanged");
            }

            public void onProviderEnabled(String provider) {
            	trace("onProviderEnabled");
            }

            public void onProviderDisabled(String provider) {
            	trace("onProviderDisabled");
            }
        };
        lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, lListener);
    }
    
    private OnClickListener btnMessageOnClickListener = new OnClickListener() {

        public void onClick(View v) {
        	startGPS();
            Toast.makeText(MainActivity.this, "Hello World", Toast.LENGTH_LONG).show();
        }

    };

    public void onLocationChanged(Location lctn) {
        TextView tv = new TextView(this);

        tv.setText("lat: " + lctn.getLatitude() + "\nlon: " + lctn.getLongitude());

        setContentView(tv);
    }

    public void onStatusChanged(String string, int i, Bundle bundle) {
    	trace("onStatusChanged");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void onProviderEnabled(String string) {
    	trace("onProviderEnabled");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void onProviderDisabled(String string) {
//        TextView tv = new TextView(this);
//
//        tv.setText("Erro no GPS");
//
//        setContentView(tv);
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuItem menuFormatar = menu.add(0, 7, 0,"Conexão");
    	menuFormatar.setIcon(android.R.drawable.ic_menu_edit);
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// de acordo com o item selecionado você executará
		// a função desejada
		switch (item.getItemId()) {

		case 7:
			Intent intent = new Intent(this, Conexao.class);
			startActivity(intent);
			break;
		}
		return true;
	}

    private void trace (String msg){
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
    }
}
