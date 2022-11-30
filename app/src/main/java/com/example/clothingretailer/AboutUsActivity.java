package com.example.clothingretailer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

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
            String polylines = "\"_cu`AimcjShBm@rAa@zC{@BLhBnGpC|InB`HtAxExCnJpFeAfDq@x@KtCm@`BY~@QZfBd@pCPE~Cm@\"";
            List<LatLng> polyLines = PolyUtil.decode(polylines);
            mGoogleMap.addPolyline(new PolylineOptions().addAll(polyLines));
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
    public void showInputLocation(View view) {
        showOrHideFrameLayout(inputLocFrameLayout);
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