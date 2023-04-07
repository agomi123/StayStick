package com.example.staystick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int CONTACT_PERMISSION_CODE = 1;
    RecyclerView recyclerView;
    ArrayList<ArrayList<ContactModal>> list = new ArrayList<>();
    ArrayList<ArrayList<Person>> list2 = new ArrayList<>();
    ArrayList<ContactModal> itemList = new ArrayList<>();
    private static final int REQUEST_CODE = 1234;
    ContactAdapter contactAdapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview1);
        progressBar=findViewById(R.id.progressbar);

        if (checkContactPermission()) {
            getcontactList();
        } else {
            requestContactPermission();
        }
    }
    private void getcontactList(){
        list2=new ArrayList<>();
        itemList=new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list2.add(new ArrayList<>());
        }
        Uri uri= ContactsContract.Contacts.CONTENT_URI;
        String sort=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+"ASC";
        Cursor cursor=getContentResolver().query(uri,null,null,null,sort);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                String id =cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name =cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                //  String address =cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.Si));
                Uri uriphone =ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" =?";
                Cursor phonecursor =getContentResolver().query(uriphone,null,selection,new String[]{id},null);
                if(phonecursor.moveToNext()){
                    String number = phonecursor.getString(phonecursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String address =phonecursor.getString(phonecursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    String imgphoto = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));
                    String names = name.toUpperCase();
                    ContactModal modal=new ContactModal(names.charAt(0),name,number,imgphoto,address);
                    Person pes=new Person(name,imgphoto);
                    int idx=names.charAt(0)-'A';
                    if(idx<26 && idx>=0)
                    list2.get(idx).add(pes);
                    //Log.d("List",name+" "+number+"\n");
                    phonecursor.close();
                }
            }
            cursor.close();
        }
        for(int i=0;i<26;i++){
            itemList.add(new ContactModal((char)('A'+i),list2.get(i)));
           }
//        for(int i=0;i<itemList.size();i++){
//            ContactModal contactModal=itemList.get(i);
//            Log.d("poi",contactModal.getSt()+" "+contactModal.getChildItemList().get(0).name+"\n");
//        }
        //do to operation
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter=new ContactAdapter(this,itemList);
        recyclerView.setAdapter(contactAdapter);
        progressBar.setVisibility(View.INVISIBLE);
    }
    private boolean checkContactPermission() {
        boolean res =( ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == (PackageManager.PERMISSION_GRANTED)) &&
                ( ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)==(PackageManager.PERMISSION_GRANTED)) &&
                ( ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)==(PackageManager.PERMISSION_GRANTED)) &&
                ( ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)==(PackageManager.PERMISSION_GRANTED));
        return res;
    }
    private void requestContactPermission() {
        String[] permission = {Manifest.permission.READ_CONTACTS,Manifest.permission.CALL_PHONE,Manifest.permission.RECORD_AUDIO};
        ActivityCompat.requestPermissions(this, permission, CONTACT_PERMISSION_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CONTACT_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getcontactList();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}