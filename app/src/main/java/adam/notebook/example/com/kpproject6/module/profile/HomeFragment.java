package adam.notebook.example.com.kpproject6.module.profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.SharedPrefManager;
import adam.notebook.example.com.kpproject6.R;
import adam.notebook.example.com.kpproject6.model.User;
import adam.notebook.example.com.kpproject6.module.login.LoginActivity;
import butterknife.OnClick;

public class HomeFragment extends Fragment {

    private TextView tvEmail, tvName, tvAddress, tvCity, tvPc;
    private ImageView imgprofile;
    private Button btnLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnLogout = view.findViewById(R.id.btn_logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        tvEmail = view.findViewById(R.id.email_profile);
        tvName = view.findViewById(R.id.name_profile);
        tvAddress = view.findViewById(R.id.address_profile);
        tvCity = view.findViewById(R.id.city_profile);
        tvPc = view.findViewById(R.id.pc_profile);
        imgprofile = view.findViewById(R.id.image_profile);

        tvEmail.setText(SharedPrefManager.getInstance(getActivity()).getUser().getEmailUser());
        tvName.setText(SharedPrefManager.getInstance(getActivity()).getUser().getNameUser());
        tvAddress.setText(SharedPrefManager.getInstance(getActivity()).getUser().getAddressUser());
        tvCity.setText(SharedPrefManager.getInstance(getActivity()).getUser().getCityUser());
        tvPc.setText(SharedPrefManager.getInstance(getActivity()).getUser().getPostal_codeUser());

        Picasso.get()
                .load(SharedPrefManager.getInstance(getActivity()).getUser().getProfile_imageUser())
                .placeholder(R.drawable.ic_account_circle_black)
                .error(R.drawable.ic_clear)
                .into(imgprofile);

    }


    private void logout() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Konfirmasi");
        alertDialog.setMessage("Yakin ingin keluar akun?");
        alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPrefManager.getInstance(getActivity()).clear();
                Toast.makeText(getContext(), "Anda telah keluar akun", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });alertDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();

    }
}