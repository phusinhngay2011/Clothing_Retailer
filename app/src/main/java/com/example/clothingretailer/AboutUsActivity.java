package com.example.clothingretailer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.List;


public class AboutUsActivity extends FragmentActivity implements OnMapReadyCallback
{

    private GoogleMap mGoogleMap;

    private FrameLayout ggMapFrameLayout;
    private FrameLayout inputLocFrameLayout;
    private EditText inputLocationEditText;


    private Spinner spinner;
    private Marker[] markerStore = new Marker[5];
    private final float zoomLevel = 16.0f;
    private float curZoomLevel;
    private ImageButton mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        mBackButton = findViewById(R.id.back_button_aboutus);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        try {
            ggMapFrameLayout = findViewById(R.id.frameLayoutMap);
            inputLocFrameLayout = findViewById(R.id.framLayoutLocationInput);
            inputLocationEditText = findViewById(R.id.locationInput);

            createSpinnerBranches();
            showOrHideFrameLayout(ggMapFrameLayout);
            showOrHideFrameLayout(inputLocFrameLayout);

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().
                    findFragmentById(R.id.GGmapfragment);
            mapFragment.getMapAsync(this);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT);
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
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT);
        }
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

    public void onCurLocation(View view) {
        try{

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString() , Toast.LENGTH_SHORT).show();
        }
    }

    public void onOpenGoogleMap(View view) {
        showOrHideFrameLayout(ggMapFrameLayout);
    }
    public void onHideMap(View view) {
        showOrHideFrameLayout(ggMapFrameLayout);
    }
    public void showOrHideFrameLayout(FrameLayout frameLayout) {
        if (frameLayout.getVisibility() == View.VISIBLE)
            frameLayout.setVisibility(View.GONE);
        else
            frameLayout.setVisibility(View.VISIBLE);
    }
    public void onSubmitLocation(View view) {
        showOrHideFrameLayout(inputLocFrameLayout);
        try {
            String origin = inputLocationEditText.getText().toString();
            String des = SNIPPET_STORE[spinner.getSelectedItemPosition()];
            Toast.makeText(getApplicationContext(), "Nh??m kh??ng c?? th??? Thanh to??n QT :(",
                    Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
    public void showInputLocation(View view) {
        showOrHideFrameLayout(inputLocFrameLayout);
    }


    public void toHome(View view) {
        //Log.d("log", "toHome called");
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    // Note: C??c chi nh??nh l?? kh??ng c?? th???t
    private final LatLng[] STORE = {
            new LatLng(10.8255013,106.687798),
            new LatLng(10.765372,106.6736618),
            new LatLng(10.7625768,106.6710036),
            new LatLng(10.776435,106.6642603),
            new LatLng(10.7626656,106.6645653),
            new LatLng(10.7385595,106.6068381)};
    private final String[] TITLE_STORE = {"Branch 1",
            "Branch 2", "Branch 3","Branch 4","Branch 5","Branch 6"};
    private final String[] SNIPPET_STORE = {
            "12 ??. Phan V??n Tr???, Ph?????ng 5, G?? V???p, Th??nh ph??? H??? Ch?? Minh",
            "45 L?? Th??nh T??n, B???n Ngh??, Qu???n 1, Th??nh ph??? H??? Ch?? Minh",
            "67 L?? L???i, B???n Ngh??, Qu???n 1, Th??nh ph??? H??? Ch?? Minh",
            "720A ??i???n Bi??n Ph??? Ph?????ng 22 Vincom Center Landmark 81, B??nh Th???nh, Th??nh ph??? H??? Ch?? Minh",
            "11 S?? V???n H???nh, Ph?????ng 12, Qu???n 10, Th??nh ph??? H??? Ch?? Minh",
            "1 ???????ng S??? 17A, B??nh Tr??? ????ng B, B??nh T??n, Th??nh ph??? H??? Ch?? Minh"
    };
    private final String[] BRANCH_NAME = {
                                        "Phan V??n Tr??? Branch",
                                        "L?? Th??nh T??n Branch",
                                        "L?? L???i Branch",
                                        "??i???n Bi??n Ph??? Branch",
                                        "S?? V???n H???nh Branch",
                                        "AEON MALL B??nh T??n Branch"
    };

}