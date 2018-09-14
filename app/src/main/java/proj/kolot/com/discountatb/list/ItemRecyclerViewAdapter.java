package proj.kolot.com.discountatb.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import proj.kolot.com.discountatb.R;
import proj.kolot.com.discountatb.model.Product;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    private List<Product> values;
    private Context context;


    public ItemRecyclerViewAdapter(Context context, List<Product> items) {
        this.context = context;
        values = items;
    }

    public void updateData(List<Product> list) {
        this.values = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.name.setText(values.get(position).getTitle());
        holder.description.setText(values.get(position).getDescription());
        String price = String.valueOf(values.get(position).getPrice()) + context.getString(R.string.money);
        holder.price.setText(price);
        double discount = values.get(position).getDiscount();
        if (discount <= 0) {
            holder.discount.setVisibility(View.INVISIBLE);
        } else {
            holder.discount.setVisibility(View.VISIBLE);
            String discountStr = String.valueOf(discount) + context.getString(R.string.percent);
            holder.discount.setText(discountStr);
        }

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(values.get(position).getImg().getHiresPreview())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final TextView description;
        final TextView price;
        final TextView discount;
        final ImageView image;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.description);
            price = view.findViewById(R.id.price);
            discount = view.findViewById(R.id.discount);
            image = view.findViewById(R.id.image);
        }
    }
}
