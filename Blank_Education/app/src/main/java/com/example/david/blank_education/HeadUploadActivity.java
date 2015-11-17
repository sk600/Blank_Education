package com.example.david.blank_education;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dd.CircularProgressButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class HeadUploadActivity extends ActionBarActivity {

    private byte[] mContent;
    Bitmap myBitmap;
    ImageView img_head;
    CircularProgressButton cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_upload);
        Bundle extras = getIntent().getExtras();
        final Uri uri = Uri.parse(extras.getString("Uri"));
        cb = (CircularProgressButton) findViewById(R.id.btn_head_upload);
        img_head = (ImageView) findViewById(R.id.img_head_upload);
        ContentResolver resolver = getContentResolver();
        try {
            mContent = readStream(resolver.openInputStream(Uri.parse(uri.toString())));
            myBitmap = getPicFromBytes(mContent, null);
            img_head.setImageBitmap(myBitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BmobFile bmobFile = new BmobFile(new File(uriToRealPath(uri)));
                bmobFile.uploadblock(HeadUploadActivity.this, new UploadFileListener() {
                    @Override
                    public void onSuccess() {
                        MyUser myUser = new MyUser();
                        MyUser userInfo = BmobUser.getCurrentUser(HeadUploadActivity.this, MyUser.class);
                        myUser.setHeadImage(bmobFile.getFileUrl(HeadUploadActivity.this));
                        myUser.update(HeadUploadActivity.this, userInfo.getObjectId(), new UpdateListener() {
                            @Override
                            public void onSuccess() {
                                cb.setProgress(100);
                                new Toast(HeadUploadActivity.this).makeText(HeadUploadActivity.this, getString(R.string.upload_sucess), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setAction("com.example.david.blank_education.broadcast.action");
                                sendBroadcast(intent);
                                HeadUploadActivity.this.finish();
                            }

                            @Override
                            public void onFailure(int code, String msg) {
                                new Toast(HeadUploadActivity.this).makeText(HeadUploadActivity.this, getString(R.string.upload_failed), Toast.LENGTH_SHORT).show();
                                cb.setProgress(0);
                            }
                        });
                    }

                    @Override
                    public void onProgress(Integer value) {
                        cb.setProgress(value);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        new Toast(HeadUploadActivity.this).makeText(HeadUploadActivity.this, getString(R.string.upload_failed), Toast.LENGTH_SHORT).show();
                        cb.setProgress(0);
                    }
                });
            }
        });

    }

    public static Bitmap getPicFromBytes(byte[] bytes,
                                         BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
                        opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }


    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;

    }

    private String uriToRealPath(Uri uri) {
        Cursor cursor = managedQuery(uri,
                new String[]{MediaStore.Images.Media.DATA},
                null,
                null,
                null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
        return path;
    }
}