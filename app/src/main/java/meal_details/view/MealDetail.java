package meal_details.view;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Entity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import com.bumptech.glide.Glide;
import com.example.nutriguide.R;
import com.example.nutriguide.databinding.FragmentHomeViewBinding;
import com.example.nutriguide.databinding.FragmentMealDetailBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import db.ConcreteLocalSource;
import db.LocalSource;
import meal_details.presenter.MealDetailPresenter;
import meal_details.presenter.MealDetailPresenterInterface;
import model.Meal;
import model.Repository;
import network.ApiClient;
import network.RemoteSource;

public class MealDetail extends Fragment {
    public static final String KE_MEAL_ID = "MealId";
    private FragmentMealDetailBinding fragment;
    Uri videoURI;
    LocalSource localSource;
    MealDetailPresenter presenter;
    Meal meal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment = FragmentMealDetailBinding.inflate(inflater, container, false);
        return fragment.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int id = getArguments().getInt(KE_MEAL_ID);
        ApiClient instance1 = ApiClient.getInstance();
        ConcreteLocalSource instance2 = ConcreteLocalSource.getInstance(getContext());
        Repository instance = Repository.getInstance(instance1, instance2);
        presenter = new MealDetailPresenter(this, instance);
        presenter.getMealDetail(id);
        fragment.favoriteImageMealDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.insertMeal(meal);
                presenter.insertMealToFirebase(meal);
            }
        });
        /*if (!meal.getStrYoutube().isEmpty()) {
            fragment.videoPlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = extractVideoIdFromUrl(meal.getStrYoutube());
                    youTubePlayer.loadVideo(videoId, 0);
                    youTubePlayer.pause();
                }
            });
        }*/
    }

    public void showData(List<Meal> meal) {
        this.meal = meal.get(0);
        fragment.mealDetailName.setText(meal.get(0).getStrMealName());
        Glide.with(getContext())
                .load(meal.get(0).getStrMealThumb())
                .into(fragment.mealDetailImg);
        fragment.instructionsTxt.setText(meal.get(0).getStrInstructions());
        fragment.ingredient1.setText(meal.get(0).getStrIngredient1());
        fragment.ingredient2.setText(meal.get(0).getStrIngredient2());
        fragment.ingredient3.setText(meal.get(0).getStrIngredient3());
        fragment.ingredient4.setText(meal.get(0).getStrIngredient4());
        fragment.ingredient5.setText(meal.get(0).getStrIngredient5());

        fragment.videoView.getSettings().setJavaScriptEnabled(true);
        fragment.videoView.getSettings().setPluginState(WebSettings.PluginState.ON);
        fragment.videoView.loadUrl(meal.get(0).getStrYoutube());
        fragment.videoView.setWebChromeClient(new WebChromeClient());

    }
   /* public static String extractVideoIdFromUrl(String url) {
        String videoId = null;
        Pattern pattern = Pattern.compile("(?<=v(=|/))([\\w-]+)");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            videoId = matcher.group();
        }
        return videoId;
    }*/
}