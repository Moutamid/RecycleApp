package com.choubapp.myproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.maps.android.data.kml.KmlLayer;

import org.jetbrains.annotations.NotNull;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class LocationFragment extends Fragment {
    //initialize variable
    Spinner spType;
    ImageView logout_btn;
    FirebaseAuth mAuth;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    KmlLayer Layer;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        //Assign variable
        logout_btn = view.findViewById(R.id.logout_btn);
        spType = view.findViewById(R.id.sp_type);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().
                findFragmentById(R.id.google_map);

        //logout
        mAuth = FirebaseAuth.getInstance();

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser()!= null)
                    mAuth.signOut();
                Intent intent = new Intent(getActivity(),Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        //initialize array of place type
        String[] placeTypeList = {"rb", "er", "2ndh", "cft", "lw"};
        //initialize array of place name
        String[] placeNameList = {"Select", "Cash For Trash Stations", "E-Waste Recycling",
                "Lighting Waste Collection Point", "2nd Hand Goods Collection Point"};

        //Set adapter on spinner
        spType.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, placeNameList));

        //initialize fused location provider client
        client = LocationServices.getFusedLocationProviderClient(getActivity());

        //check permission
        if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            //when permission is granted
            getCurrentLocation();
        }

        return view;
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        //check condition
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            //when location service is enabled
            //get last location
            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<Location> task) {
                    //initialize location
                    Location location = task.getResult();
                    //check condition
                    if (location !=null){
                        //sync map
                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {
                                UiSettings uiSettings = googleMap.getUiSettings();
                                uiSettings.setZoomControlsEnabled(true);
                                googleMap.setMyLocationEnabled(true);
                                //initialize lat lng
                                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                                //Create marker option
                                MarkerOptions options = new MarkerOptions().position(latLng)
                                        .title("Current location");
                                //zoom map
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                                //Add marker on map
                                googleMap.addMarker(options);

                                spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        try{
                                            googleMap.clear();

                                            if(spType.getSelectedItem().toString().equals("E-Waste Recycling")){
                                                final KmlLayer ewasteLayer = new KmlLayer(googleMap, R.raw.ewaste,getActivity()
                                                        .getApplicationContext()); //creating the kml layer
                                                ewasteLayer.addLayerToMap(); //adding kml layer with the **google map**
                                            }

                                            if(spType.getSelectedItem().toString().equals("Cash For Trash Stations")){
                                                final KmlLayer cashfortrashLayer = new KmlLayer(googleMap, R.raw.cashfortrash,getActivity()
                                                        .getApplicationContext());
                                                cashfortrashLayer.addLayerToMap();
                                            }

                                            if(spType.getSelectedItem().toString().equals("Lighting Waste Collection Point")){
                                                final KmlLayer lightingwasteLayer = new KmlLayer(googleMap, R.raw.lightingwaste,getActivity()
                                                        .getApplicationContext());
                                                lightingwasteLayer.addLayerToMap();
                                            }

                                            if(spType.getSelectedItem().toString().equals("2nd Hand Goods Collection Point")){
                                                final KmlLayer secondhandLayer = new KmlLayer(googleMap, R.raw.secondhand,getActivity()
                                                        .getApplicationContext());
                                                secondhandLayer.addLayerToMap();
                                            }


                                        } catch (XmlPullParserException e) {
                                            e.printStackTrace();
                                        }catch (IOException e){
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });

                            }
                        });
                    }

                }
            });
        }
    }

}