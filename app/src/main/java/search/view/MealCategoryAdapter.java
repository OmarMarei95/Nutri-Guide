package search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutriguide.R;

import java.util.List;

import model.MealCategory;

public class MealCategoryAdapter extends RecyclerView.Adapter<MealCategoryAdapter.CategoryViewHolder>{
    List<MealCategory> categories;
    OnCategoryClickListener listener;

    public List<MealCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<MealCategory> categories) {
        this.categories = categories;
    }

    public MealCategoryAdapter(OnCategoryClickListener listener, List<MealCategory> categories) {
        this.listener = listener;
        this.categories = categories;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_item,parent,false);
        MealCategoryAdapter.CategoryViewHolder viewHolder = new MealCategoryAdapter.CategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.btn.setText(categories.get(position).getStrCategory());
        holder.btn.setOnClickListener(view -> listener.onClick(categories.get(position).getStrCategory()));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        public Button btn;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.categoryBtn);
        }
    }
}
