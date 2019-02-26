package adam.notebook.example.com.kpproject6.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("code")
    private String code;
    @SerializedName("status")
    private Boolean status;
    @SerializedName("message")
    private String message;
    @SerializedName("user")
    private User user;

    public BaseResponse(String code, Boolean status, String message, User user) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setError(Boolean error) {
        this.status = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}