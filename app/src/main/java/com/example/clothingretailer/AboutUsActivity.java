package com.example.clothingretailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class AboutUsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    GoogleMap mGoogleMap;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker mCurrLocationMarker;

    FrameLayout ggMapFrameLayout;
    private Spinner spinner;
    private Marker[] markerStore = new Marker[5];
    private Marker homeMarker;
    private final float zoomLevel = 16.0f;
    private float curZoomLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        try {
            createSpinnerBranches();
            showOrHideFrameLayout();
            checkLocationPermission();
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().
                    findFragmentById(R.id.GGmapfragment);
            mapFragment.getMapAsync(this);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT);
        }
    }

    public void onOpenGoogleMap(View view) {
        showOrHideFrameLayout();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        try {
            mGoogleMap = googleMap;
            for (int i = 0; i < STORE.length; i++) {
                markerStore[i] = mGoogleMap.addMarker(new MarkerOptions()
                        .position(STORE[i])
                        .title(TITLE_STORE[i])
                        .snippet(SNIPPET_STORE[i])
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.loc_icon))
                        .zIndex(1.00f)
                        .flat(true));
            }
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(STORE[2], zoomLevel));

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    //Location Permission already granted
                    buildGoogleApiClient();
                    mGoogleMap.setMyLocationEnabled(true);
                } else {
                    //Request Location Permission
                    checkLocationPermission();
                }
            }
            else {
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
                .setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(500)
                .setMaxUpdateDelayMillis(1000)
                .build();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

        //move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,11));
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(AboutUsActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}



    public void onHideMap(View view) {
        showOrHideFrameLayout();
    }
    public void showOrHideFrameLayout() {
        ggMapFrameLayout = (FrameLayout) findViewById(R.id.frameLayoutMap);
        if (ggMapFrameLayout.getVisibility() == View.VISIBLE)
            ggMapFrameLayout.setVisibility(View.GONE);
        else
            ggMapFrameLayout.setVisibility(View.VISIBLE);
    }
    public void createSpinnerBranches(){
        spinner = (Spinner) findViewById(R.id.branchSpinner);
        SpinnerAdapter spinnerAdapter = new BranchSpinnerAdapter(this, R.layout.custom_branch_spinner, BRANCH_NAME);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(STORE[pos], zoomLevel));
                curZoomLevel = zoomLevel;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void onZoomOutMap(View view) {
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(STORE[spinner.getSelectedItemPosition()], --curZoomLevel));

    }

    public void onZoomInMap(View view) {
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(STORE[spinner.getSelectedItemPosition()], ++curZoomLevel));
    }

    // Note: Các chi nhánh là không có thật
    private final LatLng[] STORE = {new LatLng(10.8255013,106.687798),
            new LatLng(10.765372,106.6736618),
            new LatLng(10.7625768,106.6710036),
            new LatLng(10.776435,106.6642603),
            new LatLng(10.7853291,106.7127872),
            new LatLng(10.7385595,106.6068381)};
    private final String[] TITLE_STORE = {"Branch 1",
            "Branch 2", "Branch 3","Branch 4","Branch 5","Branch 6"};
    private final String[] SNIPPET_STORE = {
            "12 Đ. Phan Văn Trị, Phường 5, Gò Vấp, Thành phố Hồ Chí Minh",
            "45 Lê Thánh Tôn, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh",
            "67 Lê Lợi, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh",
            "720A Điện Biên Phủ Phường 22 Vincom Center Landmark 81, Bình Thạnh, Thành phố Hồ Chí Minh",
            "11 Sư Vạn Hạnh, Phường 12, Quận 10, Thành phố Hồ Chí Minh",
            "1 Đường Số 17A, Bình Trị Đông B, Bình Tân, Thành phố Hồ Chí Minh"
    };
    private final String[] BRANCH_NAME = {
                                        "Phan Văn Trị Branch",
                                        "Lê Thánh Tôn Branch",
                                        "Lê Lợi Branch",
                                        "Điện Biên Phủ Branch",
                                        "Sư Vạn Hạnh Branch",
                                        "AEON MALL Bình Tân Branch"
    };

}