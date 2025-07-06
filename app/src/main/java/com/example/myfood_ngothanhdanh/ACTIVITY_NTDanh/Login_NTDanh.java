package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfood_ngothanhdanh.MainActivity;
import com.example.myfood_ngothanhdanh.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;


public class Login_NTDanh extends AppCompatActivity {
    private Button btnDangNhap_NTDanh, btn_loginGoogle;
    private EditText edtUserName_NTDanh, edtPass_NTDanh;
    private String username, pass;
    private ImageView btn_close_NTDanh;
    private static final int RC_SIGN_IN = 101;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        btnDangNhap_NTDanh = findViewById(R.id.btnDangNhap_NTDanh);
        edtUserName_NTDanh = findViewById(R.id.edtUserName_NTDanh);
        edtPass_NTDanh = findViewById(R.id.edtPass_NTDanh);
        btnDangNhap_NTDanh.setOnClickListener(view2 ->{
            firebaseAuthWithEmail_NTDanh();
        });

        btn_loginGoogle = findViewById(R.id.btn_loginGoogle);
        btn_loginGoogle.setOnClickListener(view -> {
            loginGoogle();
        });

        btn_close_NTDanh = findViewById(R.id.btn_close_NTDanh);
        btn_close_NTDanh.setOnClickListener(view -> {finish();});

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void loginGoogle(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            }catch (ApiException e){
                Log.w("Login", "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if(user != null){
                            String uid = user.getUid();
                            checkExistAccount(uid);
                        }
                        Log.d("Login", "signInWithCredential:success: " + user.getEmail());
                    } else {
                        Log.w("Login", "signInWithCredential:failure", task.getException());
                    }
                });
    }

    private void checkExistAccount(String uid) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Users").document(uid).get().addOnSuccessListener(documentSnapshot -> {
            if (!documentSnapshot.exists()){
                Intent intent = new Intent(Login_NTDanh.this, FillInformation_NTDanh.class);
                intent.putExtra("user_uid", uid);
                startActivity(intent);
            }else {
                Intent intent = new Intent(Login_NTDanh.this, MainActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(e->{
            Log.e("Firestore", "Error checking user", e);
        });
    }

    private void firebaseAuthWithEmail_NTDanh(){
        username = edtUserName_NTDanh.getText().toString().trim();
        pass = edtPass_NTDanh.getText().toString().trim();
        if (username.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        }else {
            mAuth.signInWithEmailAndPassword(username, pass).addOnCompleteListener(task1 ->{
                if (task1.isSuccessful()) {
                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Đăng nhập thất bại: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }}