package favorites.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutriguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import db.ConcreteLocalSource;
import favorites.presenter.FavoritesPresenter;
import favorites.presenter.FavoritesPresenterInterface;
import home.view.MealOfTheDayAdapter;
import model.Meal;
import model.Repository;
import network.ApiClient;
import search.view.OnCategoryClickListener;

public class FavoritesView extends Fragment implements FavoritesViewInterface, OnCategoryClickListener {
    RecyclerView recyclerView;
    private MealOfTheDayAdapter adapter;
    FavoritesPresenterInterface presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FavoritesPresenter(Repository.getInstance(ApiClient.getInstance(), ConcreteLocalSource.getInstance(requireContext())), this);
        recyclerView = view.findViewById(R.id.favoritesRecycler);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        adapter = new MealOfTheDayAdapter(this, 0, new ArrayList<>(), requireContext());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        presenter.getMeals();
    }

    @Override
    public void onClick(String id) {
    }

    @Override
    public void showMeals(LiveData<List<Meal>> items) {
        items.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                adapter.updateList(meals);
                adapter.notifyDataSetChanged();
                presenter.retrieveMealsFromFirebase(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    @Override
    public void removeMeal(Meal meal) {
        presenter.removeMeal(meal);
    }
}