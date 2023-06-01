package favorites.presenter;

import androidx.lifecycle.LiveData;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

import model.Meal;

public interface FavoritesPresenterInterface {
    public void getMeals();

    public void removeMeal(Meal meal);

    void retrieveMealsFromFirebase(ValueEventListener listener);
}
