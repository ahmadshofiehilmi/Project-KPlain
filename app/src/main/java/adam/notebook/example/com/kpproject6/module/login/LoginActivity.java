package adam.notebook.example.com.kpproject6.module.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.RetrofitClient;
import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.SharedPrefManager;
import adam.notebook.example.com.kpproject6.R;
import adam.notebook.example.com.kpproject6.TablayoutActivity;
import adam.notebook.example.com.kpproject6.model.BaseResponse;
import adam.notebook.example.com.kpproject6.module.register.RegisterActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edit_emaillogin)
    EditText editEmail;
    @BindView(R.id.edit_passwordlogin)
    EditText editPassword;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.im_hidelogin)
    ImageView imHide;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.tv_skip)
    TextView tvSkip;

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);
        findViewById(R.id.tv_skip).setOnClickListener(this);

        imHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imHide.setImageResource(R.drawable.eye);
                } else {
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imHide.setImageResource(R.drawable.hide);
                }
                editPassword.setSelection(editPassword.length());
                isPasswordVisible = !isPasswordVisible;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, TablayoutActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void userLogin() {

        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editEmail.setError("Email is required");
            editEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Enter a valid email");
            editEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editPassword.setError("Password required");
            editPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editPassword.setError("Password should be atleast 6 character long");
            editPassword.requestFocus();
            return;
        }

        Call<BaseResponse> call = RetrofitClient
                .getInstance().getApi().login(email, password);

        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();

                if (!baseResponse.getStatus()) {

                    SharedPrefManager.getInstance(LoginActivity.this)
                            .saveUser(baseResponse.getData());

                    Intent intent = new Intent(LoginActivity.this, TablayoutActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


                } else {
                    Toast.makeText(LoginActivity.this, baseResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                userLogin();
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_skip:
                startActivity(new Intent(this, TablayoutActivity.class));
                finish();
        }
    }
}