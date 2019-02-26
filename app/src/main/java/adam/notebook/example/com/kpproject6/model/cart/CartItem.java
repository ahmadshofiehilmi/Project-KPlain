package adam.notebook.example.com.kpproject6.model.cart;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import adam.notebook.example.com.kpproject6.model.User;
import adam.notebook.example.com.kpproject6.model.product.Product;

public class CartItem implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("fullname")
    @Expose
    private String fullname;

    @SerializedName("product_name")
    @Expose
    private String product_name;

    @SerializedName("product_price")
    @Expose
    private String product_price;

    @SerializedName("product_image")
    @Expose
    private String product_image;

    @SerializedName("subtotal")
    @Expose
    private String subtotal;


    protected CartItem(Parcel in) {
        id = in.readString();
        qty = in.readString();
        fullname = in.readString();
        product_name = in.readString();
        product_price = in.readString();
        product_image = in.readString();
        subtotal = in.readString();
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };


    public CartItem(String id, String qty, String fullname, String product_name, String Product_price, String product_image, String subtotal) {
        this.id = id;
        this.qty = qty;
        this.fullname = fullname;
        this.product_name = product_name;
        this.product_price = Product_price;
        this.product_image = product_image;
        this.subtotal = subtotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String getProduct_price) {
        this.product_price = getProduct_price;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(qty);
        dest.writeString(fullname);
        dest.writeString(product_name);
        dest.writeString(product_price);
        dest.writeString(product_image);
        dest.writeString(subtotal);
    }
}
