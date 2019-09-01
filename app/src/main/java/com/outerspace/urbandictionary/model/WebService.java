package com.outerspace.urbandictionary.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.outerspace.urbandictionary.R;
import com.outerspace.urbandictionary.UrbanDictionaryApp;
import com.outerspace.urbandictionary.api.TermDefinition;
import com.outerspace.urbandictionary.api.TermDefinitionList;
import com.outerspace.urbandictionary.api.UrbanDictionaryApi;

import java.util.List;

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
    private static WebServiceCallback webServiceClient;

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

    public void fetchDefinitions(String word, WebServiceCallback webServiceClient) {
        if(UrbanDictionaryApp.getInstance().isNetworkConnected()) {
            this.webServiceClient = webServiceClient;
            Call<TermDefinitionList> call = api.call(word);
            call.enqueue(this);
        } else {
            DbService.getInstance().queryTerm(word, (definitions) -> {
                if(definitions != null && definitions.size() > 0) {
                    webServiceClient.onSuccess(definitions);

                } else {
                    webServiceClient.onFailure(UrbanDictionaryApp.getInstance().getString(R.string.no_network_connection));
                }
            } );
        }
    }

    @Override
    public void onResponse(Call<TermDefinitionList> call, Response<TermDefinitionList> response) {
        if(response.isSuccessful()) {
            List<TermDefinition> definitions = response.body().list;
            webServiceClient.onSuccess(definitions);
            DbService.getInstance().persist(definitions);
        } else {
            webServiceClient.onFailure(response.message());
        }
    }

    @Override
    public void onFailure(Call<TermDefinitionList> call, Throwable t) {
        webServiceClient.onFailure(t.getMessage());
    }

}
