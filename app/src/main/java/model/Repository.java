package model;

import androidx.lifecycle.LiveData;

import java.util.List;

import db.LocalSource;
import network.MealCategoryNetworkDelegate;
import network.MealDetailNetworkDelegate;
import network.MealOfTheDayNetworkDelegate;
import network.RemoteSource;

public class Repository implements RepositoryInterface {
    RemoteSource remote;
    LocalSource localSource;
    private static Repository repository = null;

    private Repository(RemoteSource remote, LocalSource localSource){
        this.remote = remote;
        this.localSource = localSource;
    }

    public static Repository getInstance(RemoteSource remote, LocalSource localSource){
        return new Repository(remote, localSource);
    }

    @Override
    public void getCategory(MealCategoryNetworkDelegate mcNetworkDelegate) {
        remote.mealCategoryEnqueue(mcNetworkDelegate);
    }

    @Override
    public void getMealOfTheDay(MealOfTheDayNetworkDelegate modNetworkDelegate){
        remote.mealOfTheDayEnqueue(modNetworkDelegate);
    }

    @Override
    public void getTrendingMeals(MealOfTheDayNetworkDelegate modNetworkDelegate) {
        remote.trendingMealsEnqueue(modNetworkDelegate);
    }

    @Override
    public void getAllMeals(MealOfTheDayNetworkDelegate modNetworkDelegate) {
        remote.allMealsEnqueue(modNetworkDelegate);
    }

    @Override
    public void getMealDetail(MealDetailNetworkDelegate mdNetworkDelegate, int id) {
        remote.mealDetailEnqueue(mdNetworkDelegate, id);
    }

    @Override
    public void insertMeal(Meal meal) {
        localSource.insertMeal(meal);
    }

    @Override
    public void deleteMeal(Meal meal) {
        localSource.deleteMeal(meal);
    }

    @Override
    public LiveData<List<Meal>> getAllStoredMeals() {
        return localSource.getAllStoredMeals();
    }
}
