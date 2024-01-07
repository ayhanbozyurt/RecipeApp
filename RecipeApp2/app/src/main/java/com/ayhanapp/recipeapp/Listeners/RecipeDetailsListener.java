package com.ayhanapp.recipeapp.Listeners;

import com.ayhanapp.recipeapp.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);

}
