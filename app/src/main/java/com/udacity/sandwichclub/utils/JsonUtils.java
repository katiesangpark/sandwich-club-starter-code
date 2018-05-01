package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static final String LOG_TAG = JsonUtils.class.getSimpleName();
    private JsonUtils() {

    }

    public static Sandwich parseSandwichJson(String s)
    {
        if (TextUtils.isEmpty( s )) {
            return null;
        }

        Sandwich sandwich = null;

        try {

            JSONObject parsingText = new JSONObject(s);
            JSONObject name = parsingText.getJSONObject("name");
            String mainName = name.getString("mainName");

            List<String> alsoKnownAsName = new ArrayList<>();
            JSONArray alsoKnownAslist = name.getJSONArray("alsoKnownAs");
            int countAlsoKnownAsArray = alsoKnownAslist.length();
            for (int i = 0; i < countAlsoKnownAsArray; i++) {
                String otherName = alsoKnownAslist.getString(i);
                alsoKnownAsName.add(otherName);
            }

            String placeOfOrigin = parsingText.getString("placeOfOrigin");
            String description = parsingText.getString("description");
            String image = parsingText.getString("image");
            List<String> ingredientslist = new ArrayList<>();

            JSONArray ingredientsArray = parsingText.getJSONArray("ingredients");
            int count = ingredientsArray.length();
            for (int j = 0; j < count; j++) {
                String ingredient = ingredientsArray.getString(j);
                ingredientslist.add(ingredient);
            }

            sandwich = new Sandwich(mainName, alsoKnownAsName, placeOfOrigin, description, image, ingredientslist);


        } catch (JSONException e) {
            Log.e("JsonUtils", "nono parsing", e);
        }

        // return the Sandwich Object
        return sandwich;
    }
}
