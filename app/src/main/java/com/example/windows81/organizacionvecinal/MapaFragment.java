package com.example.windows81.organizacionvecinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class MapaFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;


    Spinner pLista;
    Spinner otro;

    ArrayList<String> al;
    LatLng bp;
    LatLng b1;
    LatLng b2;

    LatLng mp;
    LatLng m1;
    LatLng m2;

    LatLng pp;
    LatLng p1;
    LatLng p2;

    LatLng up;

    View v;
    public MapaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_mapa, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);


        // TODO: Construcción del GoogleApiClient
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mGoogleApiClient.connect();

    }

    private void displayPlacePicker() {
        if( mGoogleApiClient == null || !mGoogleApiClient.isConnected() )
            return;

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult( builder.build(getActivity()), CAUSE_NETWORK_LOST );
        } catch ( GooglePlayServicesRepairableException e ) {
            Log.d( "PlacesAPI Demo", "GooglePlayServicesRepairableException thrown" );
        } catch ( GooglePlayServicesNotAvailableException e ) {
            Log.d( "PlacesAPI Demo", "GooglePlayServicesNotAvailableException thrown" );
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;


        bp = new LatLng(-16.497389, -68.134275);
        MarkerOptions mkt = new MarkerOptions().position(bp).title("Contenedor").snippet("Contenedor de reciclaje");
        mMap.addMarker(mkt);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bp, 16));
        b1 = new LatLng(-16.496293, -68.134686);
        MarkerOptions mktb1 = new MarkerOptions().position(b1).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_delete_black_24dp)).title("Contenedor").snippet("Contenedor de basura 1");
        mMap.addMarker(mktb1);

        b2 = new LatLng(-16.4987404, -68.134274);
        MarkerOptions mktb2 = new MarkerOptions().position(b2).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_delete_black_24dp)).title("Contenedor").snippet("Contenedor de basura 2");
        mMap.addMarker(mktb2);

        mp = new LatLng(-16.495833, -68.134445);
        MarkerOptions mktm = new MarkerOptions().position(mp).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_delete_black_24dp)).title("Contenedor").snippet("Contenedor de basura 3");
        mMap.addMarker(mktm);

        m1 = new LatLng(-16.492513, -68.136221);
        MarkerOptions mktm1 = new MarkerOptions().position(m1).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_delete_black_24dp)).title("Contenedor").snippet("Contenedor de basura 4");
        mMap.addMarker(mktm1);

        m2 = new LatLng(-16.497131, -68.132730);
        MarkerOptions mktm2 = new MarkerOptions().position(m2).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_delete_black_24dp)).title("Contenedor").snippet("Contenedor de basura 5");
        mMap.addMarker(mktm2);

        pp = new LatLng(-16.495701, -68.133460);
        MarkerOptions mktp = new MarkerOptions().position(pp).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_delete_black_24dp)).title("Contenedor").snippet("Contenedor de basura 6");
        mMap.addMarker(mktp);

        p1 = new LatLng(-16.498538, -68.135101);
        MarkerOptions mktp1 = new MarkerOptions().position(p1).title("Contenedor").snippet("Contenedor de reciclaje 1");
        mMap.addMarker(mktp1);

        p2 = new LatLng(-16.495565, -68.137224);
        MarkerOptions mktp2 = new MarkerOptions().position(p2).title("Contenedor").snippet("Contenedor de reciclaje 2");
        mMap.addMarker(mktp2);

        up = new LatLng(-16.504787, -68.129852);
        MarkerOptions mktu = new MarkerOptions().position(up).title("Contenedor").snippet("Contenedor de reciclaje 3");
        mMap.addMarker(mktu);

       /* CircleOptions coa = new CircleOptions().center(new LatLng(-16.504787, -68.141852)).radius(1000).visible(true).strokeWidth(3).strokeColor(ContextCompat.getColor(getActivity(), R.color.colorAccent)).fillColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        mMap.addCircle(coa);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bp, 17));*/



        pLista = (Spinner) v.findViewById(R.id.spiLugares);

        // otra forma
        final String[] datos =
                new String[]{"Contenedores", "Sitios de reciclaje"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, datos);

        pLista.setAdapter(adaptador);

        pLista.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                        String a = parent.getItemAtPosition(position)+"";
                        switch (a){
                            case "Contenedores":  otroSnipper(1); break;
                            case "Sitios de reciclaje":  otroSnipper(2); break;
                        }
                    }
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
    }


    public void otroSnipper(int x) {
        otro = (Spinner) v.findViewById(R.id.spiSucursales);
        final String[] da;
        ArrayAdapter<String> adap;
        switch (x){
            case 1: da = new String[]{"Contenedor de reciclaje","Contenedor de basura 1","Contenedor de basura 2","Contenedor de basura 3","Contenedor de basura 4","Contenedor de reciclaje 1"};
                adap = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, da);
                otro.setAdapter(adap);
                otro.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                                //Toast.makeText(getApplicationContext(), "Seleccionado: " + parent.getItemAtPosition(position),
                                //      Toast.LENGTH_LONG).show();
                                String a = parent.getItemAtPosition(position)+"";
                                switch (a){
                                    case "Contenedor de reciclaje": CircleOptions co = new CircleOptions().center(bp).radius(100).visible(true).strokeWidth(3).strokeColor(ContextCompat.getColor(getActivity(), R.color.colorAccent)).fillColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                                        mMap.addCircle(co); mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bp, 17)); break;
                                    case "Contenedor de basura 1":  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b1, 17)); break;
                                    case "Contenedor de basura 2":  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b2, 17)); break;
                                }
                            }
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });

        }




    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

}
