package db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import model.Meal;

public class ConcreteLocalSource implements LocalSource {

    private MealDAO dao;
    private static ConcreteLocalSource localSource = null;
    private LiveData<List<Meal>> storedItems;

    private ConcreteLocalSource(Context context) {
        MealDataBase db = MealDataBase.getInstance(context.getApplicationContext());
        dao = db.mealDAO();
        storedItems = dao.getAllItems();
    }

    public static ConcreteLocalSource getInstance(Context context) {
        if (localSource == null) {
            localSource = new ConcreteLocalSource(context);
        }
        return localSource;
    }

    @Override
    public void insertMeal(Meal meal) {
        new Thread(() -> {
            try {
                dao.insertMeal(meal);
            } catch (Throwable t) {

            }
        }).start();
    }

    @Override
    public void deleteMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.deleteMeal(meal);
            }
        }).start();
    }

    @Override
    public LiveData<List<Meal>> getAllStoredMeals() {
        return storedItems;
    }
}
