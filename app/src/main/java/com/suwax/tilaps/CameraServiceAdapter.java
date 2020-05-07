package com.suwax.tilaps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.media.ImageReader;
import android.os.Environment;
import android.util.Log;
import androidx.annotation.NonNull;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.media.Image;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import android.view.TextureView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Calendar;


public class CameraServiceAdapter {


    public static final String LOG_TAG = "myLogs";
    CameraService[] myCameras = null;
    private CameraManager mCameraManager    = null;
    private final int CAMERA1   = 0;
    private final int CAMERA2   = 1;
    private TextureView mImageView = null;
    private HandlerThread mBackgroundThread;
    private Handler mBackgroundHandler = null;
    private Activity activity;


    public void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("CameraBackground");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }

    public void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onCreate(Activity activitys, TextureView textureView) {
        activity=activitys;
        mImageView = textureView;


        mCameraManager = (CameraManager) activity.getSystemService(Context.CAMERA_SERVICE);
        try{
            // Получение списка камер с устройства


            myCameras = new CameraService[mCameraManager.getCameraIdList().length];



            for (String cameraID : mCameraManager.getCameraIdList()) {
                Log.i(LOG_TAG, "cameraID: "+cameraID);
                int id = Integer.parseInt(cameraID);


                // создаем обработчик для камеры
                myCameras[id] = new CameraService(mCameraManager,cameraID);




            }

        }
        catch(CameraAccessException e){
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        }




    }

    public void ShotCamera(int index){

            if (myCameras[CAMERA1].isOpen()) myCameras[CAMERA1].makePhoto();
            if (myCameras[CAMERA2].isOpen()) myCameras[CAMERA2].makePhoto();


    }

    public void ChangingCamera(int index){
        if(index%2==0) {
            if (myCameras[CAMERA2].isOpen()) {
                myCameras[CAMERA2].closeCamera();

            }
            if (myCameras[CAMERA1] != null) {
                if (!myCameras[CAMERA1].isOpen()) myCameras[CAMERA1].openCamera();
            }
        }else{
            if (myCameras[CAMERA1].isOpen()) {myCameras[CAMERA1].closeCamera();}
            if (myCameras[CAMERA2] != null) {
                if (!myCameras[CAMERA2].isOpen()) myCameras[CAMERA2].openCamera();
            }
        }

    }


    private class CameraService {
        Calendar calendar = Calendar.getInstance();
        String datetime =""+ calendar.getTime().getYear()+"_"+calendar.getTime().getMonth()+"_"+calendar.getTime().getDay()+"_"+calendar.getTime().getHours()+"_"+calendar.getTime().getMinutes()+"_"+calendar.getTime().getSeconds()+"_"+calendar.getTimeInMillis();


        private File mFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "tilaps_"+ datetime +".jpg");
        private String mCameraID;
        private CameraDevice mCameraDevice = null;
        private CameraCaptureSession mCaptureSession;
        private ImageReader mImageReader;




        public CameraService(CameraManager cameraManager, String cameraID) {

            mCameraManager = cameraManager;
            mCameraID = cameraID;

        }

        public void makePhoto (){

            try {
                calendar = Calendar.getInstance();
                datetime =""+ calendar.getTime().getYear()+"_"+calendar.getTime().getMonth()+"_"+calendar.getTime().getDay()+"_"+calendar.getTime().getHours()+"_"+calendar.getTime().getMinutes()+"_"+calendar.getTime().getSeconds()+"_"+calendar.getTimeInMillis();


                mFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "tilaps_"+ datetime +".jpg");


                // This is the CaptureRequest.Builder that we use to take a picture.
                final CaptureRequest.Builder captureBuilder =
                        mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
                captureBuilder.addTarget(mImageReader.getSurface());
                CameraCaptureSession.CaptureCallback CaptureCallback = new CameraCaptureSession.CaptureCallback() {

                    @Override
                    public void onCaptureCompleted(@NonNull CameraCaptureSession session,
                                                   @NonNull CaptureRequest request,
                                                   @NonNull TotalCaptureResult result) {


                    }
                };

                mCaptureSession.stopRepeating();
                mCaptureSession.abortCaptures();
                mCaptureSession.capture(captureBuilder.build(), CaptureCallback, mBackgroundHandler);

            }
            catch (CameraAccessException e) {
                e.printStackTrace();
                Log.v("ERROR_SHOT","захват"+e);


            }




        }


        private final ImageReader.OnImageAvailableListener mOnImageAvailableListener = new ImageReader.OnImageAvailableListener() {

            @Override
            public void onImageAvailable(ImageReader reader) {

                mBackgroundHandler.post(new ImageSaver(reader.acquireNextImage(), mFile));


            }

        };


        private CameraDevice.StateCallback mCameraCallback = new CameraDevice.StateCallback() {

            @Override
            public void onOpened(CameraDevice camera) {
                mCameraDevice = camera;
                Log.i(LOG_TAG, "Open camera  with id:"+mCameraDevice.getId());

                createCameraPreviewSession();
            }

            @Override
            public void onDisconnected(CameraDevice camera) {
                mCameraDevice.close();

                Log.i(LOG_TAG, "disconnect camera  with id:"+mCameraDevice.getId());
                mCameraDevice = null;
            }

            @Override
            public void onError(CameraDevice camera, int error) {
                Log.i(LOG_TAG, "error! camera id:"+camera.getId()+" error:"+error);
            }
        };


        private void createCameraPreviewSession() {

            mImageReader = ImageReader.newInstance(1920,1080, ImageFormat.JPEG,1);
            mImageReader.setOnImageAvailableListener(mOnImageAvailableListener, null);

            SurfaceTexture texture = mImageView.getSurfaceTexture();

            texture.setDefaultBufferSize(1920,1080);
            Surface surface = new Surface(texture);

            try {
                final CaptureRequest.Builder builder =
                        mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);

                builder.addTarget(surface);




                mCameraDevice.createCaptureSession(Arrays.asList(surface,mImageReader.getSurface()),
                        new CameraCaptureSession.StateCallback() {

                            @Override
                            public void onConfigured(CameraCaptureSession session) {
                                mCaptureSession = session;
                                try {
                                    mCaptureSession.setRepeatingRequest(builder.build(),null,mBackgroundHandler);
                                } catch (CameraAccessException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onConfigureFailed(CameraCaptureSession session) { }}, mBackgroundHandler);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

        }




        public boolean isOpen() {
            if (mCameraDevice == null) {
                return false;
            } else {
                return true;
            }
        }

        public void openCamera() {
            try {

                if (activity.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {


                    mCameraManager.openCamera(mCameraID,mCameraCallback,mBackgroundHandler);

                }



            } catch (CameraAccessException e) {
                Log.i(LOG_TAG,e.getMessage());

            }
        }

        public void closeCamera() {

            if (mCameraDevice != null) {
                mCameraDevice.close();
                mCameraDevice = null;
            }
        }



    }



    public void onPause() {
        if(myCameras[CAMERA1].isOpen()){myCameras[CAMERA1].closeCamera();}
        if(myCameras[CAMERA2].isOpen()){myCameras[CAMERA2].closeCamera();}
        stopBackgroundThread();
        //super.onPause();
    }


    public void onResume() {
        //super.onResume();
        startBackgroundThread();


    }


    private static class ImageSaver implements Runnable {

        /**
         * The JPEG image
         */
        private final Image mImage;
        /**
         * The file we save the image into.
         */
        private final File mFile;

        ImageSaver(Image image, File file) {
            mImage = image;
            mFile = file;

        }

        @Override
        public void run() {
            Log.v("save_foto","start");
            ByteBuffer buffer = mImage.getPlanes()[0].getBuffer();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            FileOutputStream output = null;
            try {
                output = new FileOutputStream(mFile);
                output.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
                Log.v("save_foto","error");
            } finally {
                mImage.close();
                if (null != output) {
                    try {
                        output.close();
                        Log.v("save_foto","stop");

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.v("save_foto","error");
                    }
                }
            }
        }
    }
}