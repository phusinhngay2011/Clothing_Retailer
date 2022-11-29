package com.example.clothingretailer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class AboutUsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    FrameLayout ggMapFrameLayout;
    private Spinner spinner;
    private Marker[] markerStore = new Marker[5];

    private final float zoomLevel = 16.0f;
    private float curZoomLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        try {
            createSpinnerBranches();
            showOrHideFrameLayout();
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
    public void onMapReady(@NonNull GoogleMap googleMap) {
        try {
            mGoogleMap = googleMap;
            for(int i = 0; i < STORE.length; i++){
                markerStore[i] = mGoogleMap.addMarker(new MarkerOptions()
                        .position(STORE[i])
                        .title(TITLE_STORE[i])
                        .snippet(SNIPPET_STORE[i])
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.loc_icon))
                        .zIndex(1.00f)
                        .flat(true));
            }
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(STORE[2], zoomLevel));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT);
        }
    }

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