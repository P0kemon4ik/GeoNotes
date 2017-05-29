package com.example.vladislavezerski.geonotes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;

import static android.location.LocationManager.NETWORK_PROVIDER;

public class NotesAddActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final int REQUEST_CODE_PHOTO = 2;
    private static final int REQUEST_CODE_MAP = 3;

    final String TAG = "myLogs";

    private MapView mapViewMini;

    private ImageView newNote;
    private ImageView changeColor;
    private EditText txtBody;
    private TextView otmena;
    private ImageView btnMap;
    private ImageView btnCamera;
    private ImageView ivPhoto;
    private GoogleMap googleMap;
    private Bundle savedInstanceState;
    private Note note = new Note();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.notes_add);
        newNote = (ImageView) findViewById(R.id.imageView2);
        changeColor = (ImageView) findViewById(R.id.imgChangeColor);
        txtBody = (EditText) findViewById(R.id.txt_notes_body);
        otmena = (TextView) findViewById(R.id.txt_otmena);
        btnMap = (ImageView) findViewById(R.id.btn_map);
        btnCamera = (ImageView) findViewById(R.id.btn_camera);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        mapViewMini = (MapView) findViewById(R.id.mapViewMini);


        initMap(savedInstanceState);


        changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ColorPickerActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        findViewById(R.id.txt_gotovo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setBody(txtBody.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("text", note);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        otmena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MAP);
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_PHOTO);
            }
        });
    }

    private void initMap(@Nullable Bundle savedInstanceState) {
        mapViewMini.onCreate(savedInstanceState);
        mapViewMini.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                googleMap = map;
                googleMap.getUiSettings().setZoomControlsEnabled(true);
                googleMap.setMyLocationEnabled(true);

                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Location location = locationManager.getLastKnownLocation(NETWORK_PROVIDER);

                if (location != null) {

                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder()
                            .target(new LatLng(location.getLatitude(), location.getLongitude()))
                            .zoom(10f).build()));
                }
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        googleMap.addMarker(new MarkerOptions().position(latLng).title("MyPosition"));
                    }
                });
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            int color = data.getIntExtra("color", Color.BLACK);
            newNote.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            changeColor.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            note.setColor(color);
        }

        if (requestCode == REQUEST_CODE_MAP) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    note.setLatLng((LatLng) data.getParcelableExtra("map"));
                }
            }
        }

        if (requestCode == REQUEST_CODE_PHOTO) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    Log.d(TAG, "Intent is null");
                } else {
                    Log.d(TAG, "Photo uri: " + data.getData());
                    Bundle bndl = data.getExtras();
                    if (bndl != null) {
                        Object obj = data.getExtras().get("data");
                        if (obj instanceof Bitmap) {
                            Bitmap bitmap = (Bitmap) obj;

                            ivPhoto.setImageBitmap(bitmap);

                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                            byte[] byteArray = byteArrayOutputStream .toByteArray();
                            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

                            note.setImgData(encoded);

                        }
                    }
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.d(TAG, "Canceled");
            }
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mapViewMini != null) {
            mapViewMini.onStart();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mapViewMini != null) {
            mapViewMini.onStop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapViewMini != null) {
            mapViewMini.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapViewMini != null) {
            mapViewMini.onDestroy();
        }
//        googleMap.setMyLocationEnabled(false);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapViewMini != null) {
            mapViewMini.onLowMemory();
        }
    }
}