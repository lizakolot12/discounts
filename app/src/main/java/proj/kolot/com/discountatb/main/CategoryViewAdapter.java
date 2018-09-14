package proj.kolot.com.discountatb.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import proj.kolot.com.discountatb.R;
import proj.kolot.com.discountatb.model.ProductCategory;

public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewAdapter.ViewHolder> {

    private List<ProductCategory> values;
    private ItemClickListener listener;


    public CategoryViewAdapter(List<ProductCategory> items) {
        values = items;
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public void setValues(List<ProductCategory> values) {
        this.values = values;
    }

    public void updateData(List<ProductCategory> list) {
        this.values = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(values.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView description;


        ViewHolder(View view) {
            super(view);
            description = view.findViewById(R.id.description);

        }

        void bind(final ProductCategory item, final ItemClickListener listener) {
            description.setText(item.getDescription());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }


    interface ItemClickListener {
        void onItemClick(ProductCategory category);
    }
}
