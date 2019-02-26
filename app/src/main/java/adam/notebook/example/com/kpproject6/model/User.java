package adam.notebook.example.com.kpproject6.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User{
    @Expose
    @SerializedName("id")
    private int idUser;
    @Expose
    @SerializedName("email")
    private String emailUser;
    @Expose
    @SerializedName("fullname")
    private String nameUser;
    @Expose
    @SerializedName("address")
    private String addressUser;
    @Expose
    @SerializedName("city")
    private String cityUser;
    @Expose
    @SerializedName("postal_code")
    private String postal_codeUser;
    @Expose
    @SerializedName("profile_image")
    private String profile_imageUser;
    @Expose
    @SerializedName("api_token")
    private String tokenUser;

    public User(int id, String email, String fullname, String address, String city, String postal_code, String api_token, String profile_image) {
        this.idUser = id;
        this.emailUser = email;
        this.nameUser = fullname;
        this.addressUser = address;
        this.cityUser = city;
        this.postal_codeUser = postal_code;
        this.profile_imageUser = profile_image;
        this.tokenUser = api_token;
    }

    public int getIdUser(){
        return idUser;
    }

    public void setIdUser(int idUser){
        this.idUser = idUser;
    }

    public String getEmailUser(){
        return emailUser;
    }

    public void setEmailUser(String emailUser){
        this.emailUser = emailUser;
    }

    public String getNameUser(){
        return nameUser;
    }

    public void setNameUser(String nameUser){
        this.nameUser = nameUser;
    }

    public String getAddressUser(){
        return addressUser;
    }

    public void setAddressUser(String addressUser){
        this.addressUser = addressUser;
    }

    public String getCityUser(){
        return cityUser;
    }

    public void setCityUser(String cityUser){
        this.cityUser = cityUser;
    }

    public String getPostal_codeUser(){
        return postal_codeUser;
    }

    public void setPostal_codeUser(String postal_codeUser){
        this.postal_codeUser = postal_codeUser;
    }

    public String getProfile_imageUser(){
        return profile_imageUser;
    }

    public void setProfile_imageUser(String profile_imageUser){
        this.profile_imageUser = profile_imageUser;
    }

    public String getTokenUser (){
        return tokenUser;
    }

    public void setTokenUser (String tokenUser){
        this.tokenUser = tokenUser;
    }
}
