package home.view;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

import static meal_details.view.MealDetail.KE_MEAL_ID;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.nutriguide.R;
import com.example.nutriguide.databinding.FragmentHomeViewBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.mig35.carousellayoutmanager.CarouselLayoutManager;
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener;

import java.util.ArrayList;
import java.util.List;

import db.LocalSource;
import home.presenter.HomePresenter;
import model.MealOfTheDay;
import model.Repository;
import network.ApiClient;
import search.view.OnCategoryClickListener;


public class HomeView extends Fragment implements HomeViewInterface, OnCategoryClickListener {

    FragmentHomeViewBinding fragment;
    HomePresenter presenter;
    private MealOfTheDayAdapter gridAdapter;
    private MealOfTheDayAdapter carouselAdapter;
    private MealOfTheDayAdapter linearAdapter;
    LocalSource localSource;

    public HomeView() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment = FragmentHomeViewBinding.inflate(inflater, container, false);
        return fragment.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new HomePresenter(Repository.getInstance(ApiClient.getInstance(), localSource), this);

        //TODO Carousel
        CarouselLayoutManager carouselLayoutManager =new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
        carouselAdapter = new MealOfTheDayAdapter(this, 32, new ArrayList<>(), requireContext());
        carouselLayoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        carouselLayoutManager.setMaxVisibleItems(1);
        fragment.inspirationRecycler.setLayoutManager(carouselLayoutManager);
        fragment.inspirationRecycler.setAdapter(carouselAdapter);

        //TODO linear
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(requireContext());
        linearAdapter = new MealOfTheDayAdapter(this,16, new ArrayList<>(), requireContext());
        linearLayoutManager.setOrientation(HORIZONTAL);
        fragment.trendingRecycler.setLayoutManager(linearLayoutManager);
        fragment.trendingRecycler.setAdapter(linearAdapter);

        //TODO grid
        GridLayoutManager gridLayoutManager =new GridLayoutManager(requireContext(), 2);
        gridAdapter = new MealOfTheDayAdapter(this, 0, new ArrayList<>(), requireContext());
        fragment.allMealsRecycler.setLayoutManager(gridLayoutManager);
        fragment.allMealsRecycler.setAdapter(gridAdapter);

        presenter.getMealOfTheDay();
        presenter.getTrendingMeals();
        presenter.getAllMeals();
    }


    @Override
    public void showMealsForTheDay(List<MealOfTheDay> meals) {
        carouselAdapter.setMeals(meals);
        carouselAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTrendingMeals(List<MealOfTheDay> meals) {
        linearAdapter.setMeals(meals);
        linearAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAllMeals(List<MealOfTheDay> meals) {
        gridAdapter.setMeals(meals);
        gridAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(String id) {
        Bundle args = new Bundle();
        args.putInt(KE_MEAL_ID, Integer.valueOf(id));
        Navigation.findNavController(getView())
                .navigate(R.id.action_homeView_to_mealDetailView, args);
    }
}