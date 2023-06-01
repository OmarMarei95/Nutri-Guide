package favorites.view;

import androidx.lifecycle.LiveData;

import java.util.List;

import model.Meal;

public interface FavoritesViewInterface {
    public void showMeals(LiveData<List<Meal>> meals);

    public void removeMeal(Meal meal);
}
