package com.example.aaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressBar wait;
    private EditText Email;
    private EditText Password;
    private Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth =FirebaseAuth.getInstance();

        EditText username = findViewById(R.id.editTextTextPersonName);
        Email = findViewById(R.id.editTextTextPersonName1);
        Password = findViewById(R.id.editTextTextPassword);
         wait = findViewById(R.id.progressBar);


        Register = findViewById(R.id.button);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty()) return;
                inProgress(true);
                mAuth.createUserWithEmailAndPassword(Email.getText().toString() , Password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(sign_up.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        inProgress(false);
                        Intent sign_in = new Intent(sign_up.this , com.example.aaa.sign_in.class);
                        sign_in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        sign_in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        sign_in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        finish();
                        startActivity(sign_in);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(sign_up.this, "Registration Failed!"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        final TextView sign_up = findViewById(R.id.textView14);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign_up = new Intent(com.example.aaa.sign_up.this , sign_in.class);
                startActivity(sign_up);
            }
        });

        ImageView back = findViewById(R.id.imageView6);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(sign_up.this , Sign.class);
                startActivity(back);
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
            }
        });
    }

    private void inProgress(boolean x) {
        if (x) {
            wait.setVisibility(View.VISIBLE);
            Register.setEnabled(false);

        } else {
            wait.setVisibility(View.GONE);
            Register.setEnabled(true);
        }

    }
    private boolean isEmpty() {
        if (TextUtils.isEmpty(Email.getText().toString()))
        {
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