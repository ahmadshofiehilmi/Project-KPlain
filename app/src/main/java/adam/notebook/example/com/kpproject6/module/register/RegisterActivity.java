package adam.notebook.example.com.kpproject6.module.register;

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
import adam.notebook.example.com.kpproject6.model.BaseResponse;
import adam.notebook.example.com.kpproject6.module.login.LoginActivity;
import adam.notebook.example.com.kpproject6.module.profile.ProfileActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edit_nameregister)
    EditText edit_Namereg;
    @BindView(R.id.edit_emailregister)
    EditText edit_Emailreg;
    @BindView(R.id.edit_passwordregister)
    EditText edit_Passwordreg;
    @BindView(R.id.edit_addressregister)
    EditText edit_Addressreg;
    @BindView(R.id.edit_cityregister)
    EditText edit_Cityreg;
    @BindView(R.id.edit_postalcoderegister)
    EditText edit_Postalcodereg;
    @BindView(R.id.btn_register)
    Button btn_Register;
    @BindView(R.id.tv_login)
    TextView tv_Login;
    @BindView(R.id.im_eyeregister)
    ImageView im_Eyereg;
    @BindView(R.id.progress_register)
    ProgressBar proRegister;

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        findViewById(R.id.btn_register).setOnClickListener(this);
        findViewById(R.id.tv_login).setOnClickListener(this);


        im_Eyereg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    edit_Passwordreg.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    im_Eyereg.setImageResource(R.drawable.eye);
                } else {
                    edit_Passwordreg.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    im_Eyereg.setImageResource(R.drawable.hide);
                }
                edit_Passwordreg.setSelection(edit_Passwordreg.length());
                isPasswordVisible = !isPasswordVisible;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void userSignUp() {
        String email = edit_Emailreg.getText().toString().trim();
        String password = edit_Passwordreg.getText().toString().trim();
        String fullname = edit_Namereg.getText().toString().trim();
        String address = edit_Addressreg.getText().toString().trim();
        String city = edit_Cityreg.getText().toString().trim();
        String postal_code = edit_Postalcodereg.getText().toString().trim();

        if (email.isEmpty()) {
            edit_Emailreg.setError("Email is required");
            edit_Emailreg.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edit_Emailreg.setError("Enter a valid email");
            edit_Emailreg.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            edit_Passwordreg.setError("Password required");
            edit_Passwordreg.requestFocus();
            return;
        }

        if (password.length() < 6) {
            edit_Passwordreg.setError("Password should be atleast 6 character long");
            edit_Passwordreg.requestFocus();
            return;
        }

        if (fullname.isEmpty()) {
            edit_Namereg.setError("Name required");
            edit_Namereg.requestFocus();
            return;
        }

        if (address.isEmpty()) {
            edit_Addressreg.setError("Address required");
            edit_Addressreg.requestFocus();
            return;
        }

        Call<BaseResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .register(email, password, fullname, address, city, postal_code);


        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 201) {

                    BaseResponse dr = response.body();
                    Toast.makeText(RegisterActivity.this, dr.getMessage(), Toast.LENGTH_LONG).show();

                } else if (response.code() == 422) {
                    Toast.makeText(RegisterActivity.this, "User already exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                userSignUp();
                break;
            case R.id.tv_login:

                startActivity(new Intent(this, LoginActivity.class));

                break;
        }
    }
}
