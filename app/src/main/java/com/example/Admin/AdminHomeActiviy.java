package com.example.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oneclickpick.HomeActivity;
import com.example.oneclickpick.MainActivity;
import com.example.oneclickpick.R;


public class AdminHomeActiviy extends AppCompatActivity {

    private Button LogoutBtn, CheckOrdersBtn,maintainProductsBtn, checkApproveProductsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_activiy);

        LogoutBtn=(Button) findViewById(R.id.admin_logout_btn);
        CheckOrdersBtn=(Button) findViewById(R.id.check_order_btn);
        checkApproveProductsBtn = (Button) findViewById(R.id.check_approve_products_btn);


        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActiviy.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        CheckOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActiviy.this, AdminNewOrdersActivity.class);
                startActivity(intent);

            }
        });



        checkApproveProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActiviy.this, AdminCheckNewProductsActivity.class);
                intent.putExtra("Admin", "Admin");
                startActivity(intent);
            }
        });

    }





}