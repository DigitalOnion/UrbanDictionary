package com.outerspace.urbandictionary.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.outerspace.urbandictionary.api.TermDefinitionList;
import com.outerspace.urbandictionary.api.UrbanDictionaryApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService implements Callback<TermDefinitionList> {
    private static String BASE_URL = "https://mashape-community-urban-dictionary.p.rapidapi.com";
    private static WebService instance = new WebService();

    private WebService() {
        init();
    }

    public static WebService getInstance() {
        return instance;
    }

    private static UrbanDictionaryApi api;
    private static WebServiceEvents webServiceClient;

    public void init() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(UrbanDictionaryApi.class);
    }

    public void fetchDefinitions(String term, WebServiceEvents webServiceClient) {
        this.webServiceClient = webServiceClient;
        Call<TermDefinitionList> call = api.call(term);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<TermDefinitionList> call, Response<TermDefinitionList> response) {
        if(response.isSuccessful())
            webServiceClient.onSuccess(response.body());
        else
            webServiceClient.onFailure(response.message());
    }

    @Override
    public void onFailure(Call<TermDefinitionList> call, Throwable t) {
        webServiceClient.onFailure(t.getMessage());
    }
}
