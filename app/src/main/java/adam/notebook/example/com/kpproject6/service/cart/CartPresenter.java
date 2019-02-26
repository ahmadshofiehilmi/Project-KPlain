package adam.notebook.example.com.kpproject6.service.cart;

import java.util.List;

import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.ConnectionUrl;
import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.apiservice.ApiService;
import adam.notebook.example.com.kpproject6.GeneralUtility.LogUtility;
import adam.notebook.example.com.kpproject6.GeneralUtility.Utils;
import adam.notebook.example.com.kpproject6.MyApplication;
import adam.notebook.example.com.kpproject6.model.cart.Cart;
import adam.notebook.example.com.kpproject6.model.cart.CartItem;
import adam.notebook.example.com.kpproject6.model.cart.CartResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartPresenter {

    private static String TAG = "CartPresenter";
    private static ApiService apiService = MyApplication.getApiService();

    public static void getCart(final ServiceCallback callback) {
        callback.showProgress(true);
        apiService.getCartDetail(ConnectionUrl.DETAIL_CART)
                .enqueue(new Callback<CartResponse<List<CartItem>>>() {
                    @Override
                    public void onResponse(Call<CartResponse<List<CartItem>>> call, Response<CartResponse<List<CartItem>>> response) {
                        LogUtility.logging(TAG, LogUtility.debugLog, "getCart", "onResponse", Utils.toJson(response.body()));
                        if (response.body() != null) {
                            if (response.body().getStatus()) {
                                callback.showCartDetail(response.body().getData());
                            } else {
                                callback.showMessage("Permintaan gagal DIPROSES");
                            }
                        } else {
                            callback.showMessage("Permintaan gagal DIPROSES");
                        }
                        callback.showProgress(false);
                    }

                    @Override
                    public void onFailure(Call<CartResponse<List<CartItem>>> call, Throwable t) {
                        LogUtility.logging(TAG, LogUtility.errorLog, "getCart", "onFailure", t.getMessage());
                        t.printStackTrace();
                        callback.showMessage("Permintaan gagal DIPROSES, periksa koneksi internet Anda");
                        callback.showProgress(false);
                    }
                });
    }

    public static void updateCart(final CartItem cartitem, final int position, final ServiceCallback callback) {
        apiService.updateCart(ConnectionUrl.UPDATE_CART, cartitem.getId(), cartitem.getQty())
                .enqueue(new Callback<CartResponse>() {
                    @Override
                    public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                        LogUtility.logging(TAG, LogUtility.debugLog, "updateCart", "onResponse", Utils.toJson(response.body()));
                        if (response.body() != null) {
                            if (response.body().getStatus())
                                callback.onUpdateCartSuccess(cartitem, position);
                            else
                                callback.onUpdateCartFailed();
                        } else
                            callback.onUpdateCartFailed();
                    }

                    @Override
                    public void onFailure(Call<CartResponse> call, Throwable t) {
                        LogUtility.logging(TAG, LogUtility.errorLog, "updateCart", "onFailure", t.getMessage());
                        t.printStackTrace();
                        callback.onUpdateCartFailed();
                    }
                });
    }
}
