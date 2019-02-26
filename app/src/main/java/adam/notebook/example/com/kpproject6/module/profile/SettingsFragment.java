package adam.notebook.example.com.kpproject6.module.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.SharedPrefManager;
import adam.notebook.example.com.kpproject6.R;

public class SettingsFragment extends Fragment {

    private TextView etEmail, etName, etAddress, etCity, etPc;
    private ImageView etimgprofile;
    private Button btnEdit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = view.findViewById(R.id.edname_profile);
        etAddress = view.findViewById(R.id.edaddress_profile);
        etCity = view.findViewById(R.id.edcity_profile);
        etPc = view.findViewById(R.id.edpc_profile);
        etimgprofile = view.findViewById(R.id.edimage_profile);

        etName.setText(SharedPrefManager.getInstance(getActivity()).getUser().getNameUser());
        etAddress.setText(SharedPrefManager.getInstance(getActivity()).getUser().getAddressUser());
        etCity.setText(SharedPrefManager.getInstance(getActivity()).getUser().getCityUser());
        etPc.setText(SharedPrefManager.getInstance(getActivity()).getUser().getPostal_codeUser());

        Picasso.get()
                .load(SharedPrefManager.getInstance(getActivity()).getUser().getProfile_imageUser())
                .placeholder(R.drawable.ic_account_circle_black)
                .error(R.drawable.ic_clear)
                .into(etimgprofile);

    }

}