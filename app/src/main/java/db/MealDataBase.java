package db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import model.Meal;

@Database(entities = {Meal.class}, version = 1, exportSchema = false)
public abstract class MealDataBase extends RoomDatabase {
    private static MealDataBase instance = null;

    public abstract MealDAO mealDAO();

    public static synchronized MealDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MealDataBase.class,
                            "itemdb")
                    .build();
        }
        return instance;
    }
}
