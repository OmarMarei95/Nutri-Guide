package meal_details.presenter;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import meal_details.view.MealDetail;
import model.Meal;
import model.RepositoryInterface;
import network.MealDetailNetworkDelegate;
import search.view.SearchViewInterface;

public class MealDetailPresenter implements MealDetailPresenterInterface, MealDetailNetworkDelegate {
    RepositoryInterface repo;
    MealDetail mealDetailView;

    public MealDetailPresenter(MealDetail view, RepositoryInterface repo) {
        this.repo = repo;
        mealDetailView = view;
    }

    public void onSuccessResult(List<Meal> meal) {
        mealDetailView.showData(meal);
    }

    @Override
    public void onFailureResult(String errorMsg) {
    }


    @Override
    public void getMealDetail(int id) {
        repo.getMealDetail(this,id);
    }

    @Override
    public void insertMeal(Meal meal) {
        repo.insertMeal(meal);
    }
    public void insertMealToFirebase(Meal meal){
        /*FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference("Meals");
        String key = reference.push().getKey();
        Map<String,Object> update = new HashMap<>();
        update.put("Meals",meal);
        reference.child(key).updateChildren(update);*/

        FirebaseDatabase.getInstance()
                .getReference()
                .child("meals")
                .child(meal.getIdMeal())
                .setValue(meal);
    }




    @Override
    public void deleteMeal(Meal meal) {
        repo.deleteMeal(meal);
    }

}
