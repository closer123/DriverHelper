package com.example.driverhelper.activity;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.driverhelper.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;

public class DirectBroadcastMainActivity extends AppCompatActivity implements View.OnClickListener {
    private CameraManager mCameraManager;
    private CameraDevice.StateCallback mStateCallback;
    private CameraDevice mCameraDevice;
    private TextureView mTextureView;
    private CameraCaptureSession mCaptureSession;
    private Size mPreviewSize;
    private CaptureRequest mPreviewRequest;
    private Surface surface;
    private Button mDirbtn;
private  int CamreaFont=0;
    TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int width, int height) {


            surfaceTexture = mTextureView.getSurfaceTexture();
            surfaceTexture.setDefaultBufferSize(1280, 720);
            surface = new Surface(surfaceTexture);
            openCamera();

        }

        @Override
        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_broadcast_main);
        mDirbtn=findViewById(R.id.dirbt);
        mDirbtn.setOnClickListener(this);
        mTextureView = findViewById(R.id.preview);
        mTextureView.setSurfaceTextureListener(surfaceTextureListener);
        applyForPermission();
        initCamera();

    }


    private void applyForPermission() {
        if (Build.VERSION.SDK_INT > 22) {
            if (ContextCompat.checkSelfPermission(DirectBroadcastMainActivity.this,
                    android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(DirectBroadcastMainActivity.this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                //说明已经获取到摄像头权限了
                Log.i("MainActivity", "已经获取了权限");
            }
        } else {

            Log.i("MainActivity", "这个说明系统版本在6.0之下，不需要动态获取权限。");
        }
    }

    private void initCamera() {

        mStateCallback = new CameraDevice.StateCallback() {
            @Override
            public void onOpened(@NonNull CameraDevice camera) {
                mCameraDevice = camera;
                createCameraPreviewSession();

            }

            @Override
            public void onDisconnected(@NonNull CameraDevice camera) {
                camera.close();
                mCameraDevice = null;
            }

            @Override
            public void onError(@NonNull CameraDevice camera, int error) {
                camera.close();
                mCameraDevice = null;

            }
        };
    }

    public void openCamera() {
        mCameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        try {
            mCameraManager.openCamera(CamreaFont+"", mStateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void createCameraPreviewSession() {
        try {

            final CaptureRequest.Builder previewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            previewRequestBuilder.addTarget(surface);
            previewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            mPreviewRequest = previewRequestBuilder.build();
            mCameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback() {

                @Override
                public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                    mCaptureSession = cameraCaptureSession;
                    try {
                        mCaptureSession.setRepeatingRequest(mPreviewRequest, null, null);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                    Log.e("MainActivity", "onConfigureFailed");
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        closeCamera();
    }

    private void closeCamera() {
        if (mCaptureSession != null) {
            mCaptureSession.close();
            mCaptureSession = null;
        }
        if (mCameraDevice != null) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dirbt:
                Toast.makeText(getApplicationContext(),"开始推流!并转换摄像头",Toast.LENGTH_SHORT).show();
        }

    }
}