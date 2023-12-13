package com.example.chef_up.adapers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.example.chef_up.R;
import com.example.chef_up.databinding.ItemCategoryBinding;
import com.example.chef_up.models.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * CategoryAdapter is a RecyclerView adapter used to display a list of categories.
 * It manages the creation and binding of CategoryHolder instances, which define
 * the appearance and behavior of individual items in the category list.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    private List<Category> categoryList = new ArrayList<>();

    /**
     * Updates the category list in the adapter and notifies the adapter of data changes.
     *
     * @param categoryList The new list of categories to display.
     */
    public void setCategoryList(List<Category> categoryList){
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    /**
     * Called when RecyclerView needs a new CategoryHolder to represent a category item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new CategoryHolder that holds a View for a category item.
     */
    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryHolder(binding);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The CategoryHolder which should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.onBind(category);
    }

    /**
     * Returns the total number of categories in the list held by the adapter.
     *
     * @return The total number of categories in this adapter.
     */
    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    /**
     * CategoryHolder defines the UI and behavior for individual category items within the RecyclerView.
     */
    public class CategoryHolder extends RecyclerView.ViewHolder {
        private ItemCategoryBinding binding;

        /**
         * Constructs a CategoryHolder with the specified item binding.
         *
         * @param itemView The binding for the category item.
         */
        public CategoryHolder(@NonNull ItemCategoryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        /**
         * Binds a category object to the holder, setting the image and other UI elements as needed.
         *
         * @param category The category to bind to this holder.
         */
        public void onBind(Category category){
            // Load the category image using Glide library
            Glide
                    .with(binding.getRoot().getContext())
                    .load(category.getImage())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher) // Default placeholder image
                    .into(binding.imgCategory);
        }
    }
}



