package adam.notebook.example.com.kpproject6.module.cart;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adam.notebook.example.com.kpproject6.GeneralUtility.Const;
import adam.notebook.example.com.kpproject6.GeneralUtility.Utils;
import adam.notebook.example.com.kpproject6.R;
import adam.notebook.example.com.kpproject6.model.cart.Cart;
import adam.notebook.example.com.kpproject6.model.cart.CartItem;
import adam.notebook.example.com.kpproject6.model.product.Product;
import adam.notebook.example.com.kpproject6.module.checkout.CheckOutActivity;
import adam.notebook.example.com.kpproject6.service.cart.CartPresenter;
import adam.notebook.example.com.kpproject6.service.cart.ServiceCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends AppCompatActivity implements ServiceCallback, CartAdapter.CartItemListener {

//    , CartAdapter.CartItemListener

    private static final String TAG = "CartActivity";

    @BindView(R.id.rv_cart)
    RecyclerView rvCart;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvHargaFinal)
    TextView tvTotalHarga;
    @BindView(R.id.tv_favorite)
    TextView tvFavorite;
    @BindView(R.id.tvSubtotal)
    TextView tvSubtotal;
    @BindView(R.id.tvDiskon)
    TextView tvDiskon;
//    @BindView(R.id.tvCodeMessage)
//    TextView tvCodeMessage;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.bottomCard)
    CardView cardView;
    @BindView(R.id.rv_wish_list)
    RecyclerView rvWishList;
//    @BindView(R.id.edtPromotionCode)
//    EditText edtPromotionCode;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;

    private ArrayList<CartItem> cartList = new ArrayList<>();
    private CartAdapter cartAdapter = new CartAdapter(cartList, this);
//    private List<Product> wishList = new ArrayList<>();
//    private WishListAdapter wishListAdapter = new WishListAdapter(wishList);
//    private int diskon = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        setupView();
        CartPresenter.getCart(this);
//        CartPresenter.getWishList(this);
    }

    private void setupView() {
        setSupportActionBar(toolbar);
        setTitle("Keranjang Belanja");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        tvCodeMessage.setVisibility(View.GONE);
        nestedScrollView.setVisibility(View.GONE);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.setAdapter(cartAdapter);

//        rvWishList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        rvWishList.setAdapter(wishListAdapter);
    }
    @OnClick(R.id.btnCheckOut)
    public void checkOut(View view) {
        Intent intent = new Intent(this, CheckOutActivity.class);
        intent.putParcelableArrayListExtra(Const.CART, cartList);
//        intent.putExtra("diskon", diskon);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showCartDetail(List<CartItem> cartList) {
        this.cartList.clear();
        this.cartList.addAll(cartList);
        cartAdapter.notifyDataSetChanged();
        akumulasiTotalHarga();

        if (!cartList.isEmpty())
            nestedScrollView.setVisibility(View.VISIBLE);
    }

    private void akumulasiTotalHarga() {
        int subtotal = 0;
        for (CartItem c : cartList) {
            subtotal += Integer.valueOf(c.getProduct_price());
        }
        int totalHarga = subtotal + subtotal;
        totalHarga = (totalHarga >= 0) ? totalHarga : 0;

        tvSubtotal.setText(Utils.toCurrency(subtotal));
//        tvDiskon.setText(Utils.toCurrency(diskon));
        tvTotalHarga.setText("Rp " + Utils.toCurrency(totalHarga));
    }

    @Override
    public void showProgress(boolean show) {
        progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onQtyChanges(CartItem cart, int position) {
//        CartPresenter.updateCart(cart, position, this);
    }

    @Override
    public void onRemove(int position) {
//        final Cart cart = cartList.get(position);
//        new AlertDialog.Builder(this)
//                .setMessage("Hapus item " + cart.getNamaBarang() + "?")
//                .setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
//                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        CartPresenter.removeCart(cart, CartActivity.this);
//                        dialog.dismiss();
//                    }
//                })
//                .show();
    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateCartSuccess(CartItem cartItem, int position) {
        this.cartList.set(position, cartItem);
        /*cartAdapter.notifyItemChanged(position);*/
        akumulasiTotalHarga();
    }

    @Override
    public void onUpdateCartFailed() {
        cartAdapter.notifyDataSetChanged();
        showMessage("Gagal mengubah data keranjang. Ulangi lagi nanti");
    }
}
