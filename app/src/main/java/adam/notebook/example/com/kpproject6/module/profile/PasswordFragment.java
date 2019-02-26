package adam.notebook.example.com.kpproject6.module.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.SharedPrefManager;
import adam.notebook.example.com.kpproject6.R;

public class PasswordFragment extends Fragment {

    private EditText edtEmail, edtPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.password_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtEmail = view.findViewById(R.id.edt_email);
        edtPassword = view.findViewById(R.id.edt_password);

        edtEmail.setText(SharedPrefManager.getInstance(getActivity()).getUser().getNameUser());
        edtPassword.setText(SharedPrefManager.getInstance(getActivity()).getUser().getAddressUser());

    }
}
