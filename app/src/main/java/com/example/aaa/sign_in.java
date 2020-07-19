package com.example.aaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sign_in extends AppCompatActivity {
    private Button sign_in;
    private EditText username;
    private EditText Email;
    private EditText Password;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        sign_in = findViewById(R.id.button);
        username = findViewById(R.id.editTextTextPersonName13);
        Email = findViewById(R.id.editTextTextPersonName1);
        Password = findViewById(R.id.editTextTextPassword);
        progressBar = findViewById(R.id.progressBar);





        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty()) return;
                inProgress(true);
                mAuth.signInWithEmailAndPassword(Email.getText().toString(), Password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(sign_in.this, "Welcome ", Toast.LENGTH_SHORT).show();
                        Intent start = new Intent(sign_in.this , MainPage.class);
                        start.putExtra("Email" ,  Email.getText().toString());
                        start.putExtra("username" ,username.getText().toString());
                        start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        start.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        start.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(start);
                        finish();
                        return;

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(sign_in.this, "Sign In Failed!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        ImageView back = findViewById(R.id.imageView6);

        final TextView sign_up = findViewById(R.id.textView14);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign_up = new Intent(sign_in.this , sign_up.class);
                startActivity(sign_up);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(sign_in.this , Sign.class);
                startActivity(back);
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
            }
        });






    }
    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                Intent intent = new Intent(sign_in.this, MainPage.class);
                startActivity(intent);
                finish();
            }
        }
    };

    private void inProgress(boolean x) {
        if (x) {
            progressBar.setVisibility(View.VISIBLE);
            sign_in.setEnabled(false);
        } else {
            progressBar.setVisibility(View.GONE);
            sign_in.setEnabled(true);
        }

    }

    private boolean isEmpty() {
        if (TextUtils.isEmpty(Email.getText().toString())) {
            Email.setError("REQUIRED!");
            return true;

        }
        if (TextUtils.isEmpty(Password.getText().toString())) {
            Password.setError("REQUIRED!");
            return true;
        } else {
            return false;
        }
    }

}