package db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import model.Meal;

@Dao
public interface MealDAO {

    @Query("SELECT * from Meals")
    LiveData<List<Meal>> getAllItems();

    @Insert
    void insertMeal(Meal item);

    @Delete
    void deleteMeal(Meal item);

}
