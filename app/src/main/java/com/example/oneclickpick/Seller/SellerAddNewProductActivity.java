package com.example.oneclickpick.Seller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oneclickpick.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class SellerAddNewProductActivity extends AppCompatActivity {

    private String CategoryName, Description, Price, PName, saveCurrentDate, saveCurrentTime;
    private ImageView InputProductImage;
    private EditText InputProductName;
    private EditText InputProductDescription;
    private EditText InputProductPrice;
    private Button AddNewProductImage;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImageRef;
    private DatabaseReference ProductsRef,categoryref, sellerRef;
    private ProgressDialog loadingbar;
    private String sName, sAddress, sPhone, sEmail, sID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_add_new_product);

        CategoryName = getIntent().getExtras().get("Category").toString();
        sellerRef = FirebaseDatabase.getInstance().getReference().child("Sellers");
        categoryref = FirebaseDatabase.getInstance().getReference().child("Products").child("Category");
        ProductImageRef = FirebaseStorage.getInstance().getReference().child("Product Image");
        ProductsRef=FirebaseDatabase.getInstance().getReference().child("Products");
        InputProductImage = (ImageView) findViewById(R.id.select_product_image);
        AddNewProductImage =(Button) findViewById(R.id.add_new_product);
        InputProductName=(EditText) findViewById(R.id.product_name);
        InputProductDescription=(EditText) findViewById(R.id.product_description);
        InputProductPrice=(EditText) findViewById(R.id.product_price);
        loadingbar= new ProgressDialog(this);

        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });
        AddNewProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateProduct();
            }
        });

        sellerRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    sName = snapshot.child("name").getValue().toString();
                    sAddress = snapshot.child("address").getValue().toString();
                    sPhone = snapshot.child("phone").getValue().toString();
                    sID = snapshot.child("sid").getValue().toString();
                    sEmail = snapshot.child("email").getValue().toString();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GalleryPick && resultCode== RESULT_OK && data!=null){
            ImageUri= data.getData();
            InputProductImage.setImageURI(ImageUri);

        }
    }

    private void ValidateProduct() {
        Description = InputProductDescription.getText().toString();
        Price=InputProductPrice.getText().toString();
        PName=InputProductName.getText().toString();

        if(ImageUri == null){
            Toast.makeText(this,"Invalid Product Image.", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(Description)){
            Toast.makeText(this,"Invalid Product Description.", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(Price)){
            Toast.makeText(this,"Invalid Product Price.", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(PName)){
            Toast.makeText(this,"Invalid Product Name.", Toast.LENGTH_SHORT).show();
        }else{
            StoreProductInofmrtaion();
        }
    }

    private void StoreProductInofmrtaion() {

        loadingbar.setTitle("Add New Product");
        loadingbar.setMessage("Please Wait");
        loadingbar.setCanceledOnTouchOutside(false);
        loadingbar.show();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate =currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;
        final StorageReference filePate =ProductImageRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");
        final UploadTask uploadTask = filePate.putFile(ImageUri);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               String message = e.toString();
                Toast.makeText(SellerAddNewProductActivity.this,"Error" + message, Toast.LENGTH_SHORT).show();
                loadingbar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(SellerAddNewProductActivity.this,"Product Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()){
                            throw task.getException();
                        }
                        downloadImageUrl = filePate.getDownloadUrl().toString();
                        return filePate.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>(){
                   @Override
                   public void onComplete(@NonNull Task<Uri> task){
                       if (task.isSuccessful()) {
                           downloadImageUrl = task.getResult().toString();
                           Toast.makeText(SellerAddNewProductActivity.this,"Product Url Saved", Toast.LENGTH_SHORT).show();
                           SaveProductInfoToDatabase();
                       }
                   }
                });
            }

        });
    }
    private void SaveProductInfoToDatabase() {

        HashMap<String, Object> productMap= new HashMap<>();
        productMap.put("pid", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("desc", Description);
        productMap.put("image",downloadImageUrl);
        productMap.put("price", Price);
        productMap.put("pname", PName);

        productMap.put("sellerName", sName);
        productMap.put("sellerAddress", sAddress);
        productMap.put("sellerPhone", sPhone);
        productMap.put("sellerEmail", sEmail);
        productMap.put("sid", sID);

        productMap.put("category",CategoryName);
        productMap.put("productState","Not Approved");


        ProductsRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>(){
                    @Override
                    public void onComplete(@NonNull Task<Void> task){
                       if(task.isSuccessful()){

                           Intent intent = new Intent(SellerAddNewProductActivity.this, SellerHomeActivity.class);
                           startActivity(intent);

                           loadingbar.dismiss();
                           Toast.makeText(SellerAddNewProductActivity.this,"Product is Added Successfully", Toast.LENGTH_SHORT).show();

                       }else{
                           loadingbar.dismiss();
                           String message = task.getException().toString();
                           Toast.makeText(SellerAddNewProductActivity.this,"Error" + message, Toast.LENGTH_SHORT).show();
                       }
                    }
                });


    }


}