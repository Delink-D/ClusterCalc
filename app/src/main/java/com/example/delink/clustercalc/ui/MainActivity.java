package com.example.delink.clustercalc.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.delink.clustercalc.Constants;
import com.example.delink.clustercalc.R;
import com.example.delink.clustercalc.model.Subjects;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.toolbar) Toolbar toolbar;
//    @Bind(R.id.profile_image) ImageView mProfileImage;
    ImageView mProfileImage;

    private StorageReference mStorage;
    private String mCurrentPhotoPath;
    private static final int CAMERA_REQUEST = 1;
    private static final int GALLERY_INTENT = 2;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mStorage = FirebaseStorage.getInstance().getReference();
        mProgressDialog = new ProgressDialog(this);

        if (savedInstanceState == null) {
            //Fragment load code

            Fragment fragment = null;
            Class fragmentClass = null;
            fragmentClass = HomeFragment.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flagContent, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mProfileImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.profile_image);
        mProfileImage.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.nav_home) {
            fragmentClass = HomeFragment.class;
        } else if (id == R.id.nav_grading) {
            fragmentClass = GradingFragment.class;
        } else if (id == R.id.nav_courses) {
            fragmentClass = CoursesFragment.class;
        } else if (id == R.id.nav_manage) {
            fragmentClass = ManageFragment.class;
        }else if (id == R.id.nav_subjects){
            fragmentClass = SubjectsFragment.class;
        } else if (id == R.id.nav_share) {
            fragmentClass = SubjectsFragment.class;
        } else if (id == R.id.nav_send) {
            fragmentClass = SubjectsFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flagContent, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == mProfileImage){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");

            startActivityForResult(intent,GALLERY_INTENT);
        }
//        if (v == mProfileImage){
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(intent,CAMERA_REQUEST);
//        }
    }

//    private void dispatchTakePictureIntent() {
//        Intent intent = new Intent((MediaStore.ACTION_IMAGE_CAPTURE));
//        if (intent.resolveActivity(getPackageManager()) != null){
//            File picPath = null;
//
//            try{
//                picPath = createImageFile();
//            }catch (IOException ex){
//
//            }
//
//            if (picPath != null){
//                Uri uri = FileProvider.getUriForFile(this,"com.example.delink.clustercalc",picPath);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
//                startActivityForResult(intent,CAMERA_REQUEST);
//            }
//        }
//    }
//
//    private File createImageFile() throws IOException {
////        create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
//        mCurrentPhotoPath = image.getAbsolutePath();
//        return image;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            mProgressDialog.setMessage("Uploding Profile Image....");
            mProgressDialog.show();

            Uri uri = data.getData();

//            Log.d(TAG, ">>>> " + uri);

            StorageReference profileImagePath = mStorage.child(Constants.FIREBASE_CHILD_STORAGE_PROFILE_IMAGES)
                    .child(uri.getLastPathSegment());
            profileImagePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    mProgressDialog.dismiss();

                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    Picasso.with(MainActivity.this).load(downloadUrl).fit().centerCrop().into(mProfileImage);

                    Toast.makeText(MainActivity.this, "Profile Image Changed Successfully!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){
//
//            mProgressDialog.setMessage("Uploading the image.....");
//            mProgressDialog.show();
//
////            Bundle extras = data.getExtras();
//
//            Uri uri = data.getData();
////            Log.d(TAG, ">>> "+extras);
//
////            Toast.makeText(MainActivity.this, uri.toString(), Toast.LENGTH_SHORT).show();
//
//            StorageReference imageFilePath = mStorage.child(Constants.FIREBASE_CHILD_STORAGE_PROFILE_IMAGES)
//                    .child(uri.getLastPathSegment());
//
//            imageFilePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    mProgressDialog.dismiss();
//
//                    Toast.makeText(MainActivity.this, "Profile Image Changed Successfully!", Toast.LENGTH_SHORT).show();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(MainActivity.this, "Upload Not Completed!!", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
}