package adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils;

import android.content.Context;
import android.content.SharedPreferences;

import adam.notebook.example.com.kpproject6.model.User;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_preff";

    private static SharedPrefManager mInstance;
    private Context mCtx;

    public SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }


    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }


    public void saveUser(User user) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", user.getIdUser());
        editor.putString("email", user.getEmailUser());
        editor.putString("fullname", user.getNameUser());
        editor.putString("address", user.getAddressUser());
        editor.putString("city", user.getCityUser());
        editor.putString("postal_code", user.getPostal_codeUser());
        editor.putString("api_token", user.getTokenUser());
        editor.putString("profile_image", user.getProfile_imageUser());

        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("id", -0),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("fullname", null),
                sharedPreferences.getString("address", null),
                sharedPreferences.getString("city", null),
                sharedPreferences.getString("postal_code", null),
                sharedPreferences.getString("api_token", null),
                sharedPreferences.getString("profile_image", null)
        );
    }

    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
