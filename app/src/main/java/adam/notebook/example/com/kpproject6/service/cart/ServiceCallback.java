package adam.notebook.example.com.kpproject6.service.cart;

import java.util.List;

import adam.notebook.example.com.kpproject6.model.cart.Cart;
import adam.notebook.example.com.kpproject6.model.cart.CartItem;
import adam.notebook.example.com.kpproject6.model.product.Product;

public interface ServiceCallback {

    void showCartDetail(List<CartItem> cartList);

    void showMessage(String s);

    void onUpdateCartSuccess(CartItem cartItem, int position);

    void onUpdateCartFailed();

    void showProgress(boolean show);

//    void onResetSuccess();
//
//    void showWishList(List<Product> wishList);
//
//    void onCartRemoved(Cart cart);

//    void showPromo(boolean status, Coupon promo);

}
