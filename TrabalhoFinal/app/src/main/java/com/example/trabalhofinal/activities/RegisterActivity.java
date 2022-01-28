package com.example.trabalhofinal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.dao.DAOUser;
import com.example.trabalhofinal.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtEmailRegister;
    private EditText edtNameRegister;
    private EditText edtPasswordRegister;
    private EditText edtConfirmPasswordRegister;
    private CheckBox ckbShowPasswordRegister;
    private Button btnSubmitRegister;
    private Button btnBackLogin;
    private ProgressBar progressbarRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        edtEmailRegister = findViewById(R.id.edt_email_register);
        edtNameRegister = findViewById(R.id.edt_name_register);
        edtPasswordRegister = findViewById(R.id.edt_password_register);
        edtConfirmPasswordRegister = findViewById(R.id.edt_confirm_password_register);
        ckbShowPasswordRegister = findViewById(R.id.ckb_show_password_register);
        btnSubmitRegister = findViewById(R.id.btn_submit_register);
        btnBackLogin = findViewById(R.id.btn_back_login);
        progressbarRegister = findViewById(R.id.progressbar_register);

        ckbShowPasswordRegister.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edtPasswordRegister.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    edtConfirmPasswordRegister.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    edtPasswordRegister.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edtConfirmPasswordRegister.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnSubmitRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();

                user.setEmail(edtEmailRegister.getText().toString());
                user.setName(edtNameRegister.getText().toString());

                String password = edtPasswordRegister.getText().toString();
                String confirmPassword = edtConfirmPasswordRegister.getText().toString();

                if (!TextUtils.isEmpty(user.getName()) &&
                        !TextUtils.isEmpty(user.getEmail()) &&
                        !TextUtils.isEmpty(password) &&
                        !TextUtils.isEmpty(confirmPassword)) {
                    if (password.equals(confirmPassword)) {
                        progressbarRegister.setVisibility(View.VISIBLE);

                        mAuth.createUserWithEmailAndPassword(user.getEmail(), password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    DAOUser user_dao = new DAOUser();
                                    user.setId(mAuth.getUid());

                                    user_dao.add(user);

                                    redirectMain();
                                } else {
                                    String error;

                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthWeakPasswordException e) {
                                        error = "A senha deve conter no mínimo 6 caracteres";
                                    } catch (FirebaseAuthInvalidCredentialsException e) {
                                        error = "E-mail inválido";
                                    } catch (FirebaseAuthUserCollisionException e) {
                                        error = "E-mail já existe";
                                    } catch (Exception e) {
                                        error = "Erro ao efetuar o cadastro";
                                        e.printStackTrace();
                                    }

                                    Toast.makeText(RegisterActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                                }
                                progressbarRegister.setVisibility(View.INVISIBLE);
                            }
                        });
                    } else {
                        Toast.makeText(RegisterActivity.this, "A senha deve estar correta em ambos os campos!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void redirectMain() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}