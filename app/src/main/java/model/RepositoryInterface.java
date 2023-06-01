package model;

import androidx.lifecycle.LiveData;

import java.util.List;

import network.MealCategoryNetworkDelegate;
import network.MealDetailNetworkDelegate;
import network.MealOfTheDayNetworkDelegate;

public interface RepositoryInterface {
    void getCategory(MealCategoryNetworkDelegate mcNetworkDelegate);
    void getMealOfTheDay(MealOfTheDayNetworkDelegate modNetworkDelegate);
    void getTrendingMeals(MealOfTheDayNetworkDelegate modNetworkDelegate);
    void getAllMeals(MealOfTheDayNetworkDelegate modNetworkDelegate);
    void getMealDetail(MealDetailNetworkDelegate mdNetworkDelegate, int id);
    public void insertMeal(Meal meal);

    public LiveData<List<Meal>> getAllStoredMeals();

    public void deleteMeal(Meal item);
}
