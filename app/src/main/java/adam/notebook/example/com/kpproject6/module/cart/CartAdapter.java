package adam.notebook.example.com.kpproject6.module.cart;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import adam.notebook.example.com.kpproject6.GeneralUtility.Utils;
import adam.notebook.example.com.kpproject6.R;
import adam.notebook.example.com.kpproject6.model.cart.CartItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Holder> {

    private List<CartItem> cartList;
    private CartItemListener listener;
//
    public CartAdapter(List<CartItem> cartList, CartItemListener listener) {
        this.cartList = cartList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_cart, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.bind(i);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }



    @SuppressLint("SetTextI18n")
    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nama_barang)
        TextView tvNamaBarang;
        @BindView(R.id.tv_harga_satuan)
        TextView tvHargaSatuan;
        @BindView(R.id.tv_total_harga)
        TextView tvTotalHarga;
        @BindView(R.id.tvQty)
        TextView tvQty;
        @BindView(R.id.imgProduct)
        ImageView imgProduct;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int i) {
            CartItem cart = cartList.get(i);
//            Picasso.with(itemView.getContext()).load(cart.getProduct().getImage().getProduct_image()).into(imgProduct);
            Picasso.get().load(cart.getProduct_image()).into(imgProduct);
            tvNamaBarang.setText(cart.getProduct_name());
            tvHargaSatuan.setText("Rp " + Utils.toCurrency(Integer.parseInt(cart.getProduct_price())));
            tvTotalHarga.setText("Rp " + Utils.toCurrency(Integer.parseInt(cart.getSubtotal())));
            tvQty.setText(cart.getQty());
        }

        @OnClick(R.id.btnPlus)
        public void onQtyAdd(View view) {
            CartItem cart = cartList.get(getAdapterPosition());
            int harga = Integer.parseInt(cart.getProduct_price());
            int jumlah = Integer.parseInt(cart.getQty());
            jumlah = jumlah + 1;
            int totalHarga = harga * jumlah;
            cart.setQty(String.valueOf(jumlah));
            cart.setSubtotal(String.valueOf(totalHarga));
            tvTotalHarga.setText("Rp " + Utils.toCurrency(totalHarga));
            tvQty.setText(String.valueOf(jumlah));
            listener.onQtyChanges(cart, getAdapterPosition());
        }

        @OnClick(R.id.btnMinus)
        public void onQtyMinus(View view) {
            CartItem cart = cartList.get(getAdapterPosition());
            int harga = Integer.parseInt(cart.getProduct_price());
            int jumlah = Integer.parseInt(cart.getQty());
            if (jumlah <= 1) return;
            jumlah = jumlah - 1;
            int totalHarga = harga * jumlah;
            cart.setQty(String.valueOf(jumlah));
            cart.setSubtotal(String.valueOf(totalHarga));
            tvQty.setText(String.valueOf(jumlah));
            tvTotalHarga.setText("Rp " + Utils.toCurrency(totalHarga));
            listener.onQtyChanges(cart, getAdapterPosition());
        }
    }
    public interface CartItemListener {
        void onQtyChanges(CartItem cart, int position);

        void onRemove(int position);
    }
}
