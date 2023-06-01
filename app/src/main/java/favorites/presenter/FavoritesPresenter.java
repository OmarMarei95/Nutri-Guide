package favorites.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import favorites.view.FavoritesViewInterface;
import model.Meal;
import model.RepositoryInterface;

public class FavoritesPresenter implements FavoritesPresenterInterface {
    final RepositoryInterface repo;
    final FavoritesViewInterface view;

    public FavoritesPresenter(RepositoryInterface repo, FavoritesViewInterface view) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void getMeals() {
        view.showMeals(repo.getAllStoredMeals());
    }

    @Override
    public void removeMeal(Meal meal) {
        view.removeMeal(meal);
    }

    @Override
    public void retrieveMealsFromFirebase(ValueEventListener listener) {
        DatabaseReference mealsRef = FirebaseDatabase.getInstance().getReference().child("meals");
        mealsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("MuhammadDebug", "onChildAdded" + snapshot);

                for (DataSnapshot next : snapshot.getChildren()) {
                    Log.d("MuhammadDebug", "valuee " + next.getChildrenCount());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("MuhammadDebug", "onChildChanged" + snapshot);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Log.d("MuhammadDebug", "onChildRemoved" + snapshot);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("MuhammadDebug", "onChildMoved" + snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MuhammadDebug", "onCancelled", error.toException());
            }
        });
                /*new ChildEventListener() {
            *//*@Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                *//**//*List<Meal> meals = new ArrayList<>();
                for (DataSnapshot mealSnapshot : dataSnapshot.getChildren()) {
                    Meal meal = mealSnapshot.getValue(Meal.class);
                    meals.add(meal);
                }

                listener.onDataChange(dataSnapshot);*//**//*
                Log.d("MuhammadDebug", dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onCancelled(databaseError);
            }*//*
        });*/
    }
}
