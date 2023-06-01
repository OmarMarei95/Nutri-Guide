package network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import model.Meal;
import model.MealCategory;

public class MealDetailResponse {
    @SerializedName("meals")
    private List<Meal> meal = null;

    public List<Meal> getMeals() {
        return meal;
    }

    public void setMeals(List<MealCategory> meals) {
        this.meal = meal;
    }
}
