package model;

import com.google.gson.annotations.SerializedName;

public class MealOfTheDay {
    public MealOfTheDay(String name, String thumbnail, int id) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("strMeal")
    String name;
    @SerializedName("strMealThumb")
    String thumbnail;
    @SerializedName("idMeal")
    int id;
}
