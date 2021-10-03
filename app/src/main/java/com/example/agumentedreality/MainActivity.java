package com.example.agumentedreality;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.agumentedreality.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth=FirebaseAuth.getInstance();

    }

    public void grsclk(View view){
        String email=binding.Mailad.getText().toString();
        String password=binding.Password.getText().toString();

        if (email.equals("")||password.equals("")){
            Toast.makeText(this,"Lüften Email ve şifrenizi giriniz",Toast.LENGTH_SHORT).show();

        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent=new Intent(MainActivity.this,Activity2.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }




    }
    public void Kytclk(View view){
        String email=binding.Mailad.getText().toString();
        String password=binding.Password.getText().toString();
        if(email.equals("")||password.equals("")) {
            Toast.makeText( this,"Lütfen Email ve şifre giriniz",Toast.LENGTH_SHORT).show();
        }else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText( MainActivity.this,"Kullacını başarılı şekilde oluşturuldu",Toast.LENGTH_SHORT).show();
                 //   Intent intent=new Intent(MainActivity.this,MainActivity.this); Sayfalar arası geçiş
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}