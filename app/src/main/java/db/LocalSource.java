package db;

import androidx.lifecycle.LiveData;

import java.util.List;

import model.Meal;

public interface LocalSource {
    void insertMeal(Meal item);

    void deleteMeal(Meal item);

    LiveData<List<Meal>> getAllStoredMeals();
}
