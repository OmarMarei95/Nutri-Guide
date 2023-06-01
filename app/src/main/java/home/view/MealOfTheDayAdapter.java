package home.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nutriguide.R;

import java.util.List;

import meal_details.view.MealDetail;
import model.Meal;
import model.MealOfTheDay;
import search.view.OnCategoryClickListener;

public class MealOfTheDayAdapter extends RecyclerView.Adapter<home.view.MealOfTheDayAdapter.MealOfTheDayViewHolder> {
    List<MealOfTheDay> meals;
    List<Meal> list;
    OnCategoryClickListener listener;
    private final Context context;
    private final int margin;

    public List<MealOfTheDay> getCategories() {
        return meals;
    }

    public void setMeals(List<MealOfTheDay> meals) {
        this.meals = meals;
    }

    public MealOfTheDayAdapter(OnCategoryClickListener ownerView, int margin, List<MealOfTheDay> meals, Context context) {
        this.context = context;
        this.listener = ownerView;
        this.meals = meals;
        this.margin = margin;
    }

    @Override
    public home.view.MealOfTheDayAdapter.MealOfTheDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_mod, parent, false);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        layoutParams.setMargins(margin, margin, margin, margin);
        home.view.MealOfTheDayAdapter.MealOfTheDayViewHolder viewHolder = new home.view.MealOfTheDayAdapter.MealOfTheDayViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealOfTheDayViewHolder holder, int position) {
        MealOfTheDay mealOfTheDay = meals.get(position);
        holder.modNameTxt.setText(mealOfTheDay.getName());
        holder.modImg.setOnClickListener(view -> {
            listener.onClick(String.valueOf(mealOfTheDay.getId()));
        });
        //Glide.with(context).load(meals.get(position).getThumbnail()).into(holder.modImg);
        Glide.with(holder.itemView.getContext())
                .load(mealOfTheDay.getThumbnail())
                .into(holder.modImg);

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class MealOfTheDayViewHolder extends RecyclerView.ViewHolder {
        public ImageView modImg;
        public TextView modNameTxt;

        public MealOfTheDayViewHolder(@NonNull View itemView) {
            super(itemView);
            modImg = itemView.findViewById(R.id.modImg);
            modNameTxt = itemView.findViewById(R.id.modName);
        }
    }
    public void updateList(List<Meal> meals) {
        list = meals;
    }
}
