package com.outerspace.urbandictionary.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface UrbanDictionaryApi {

    @Headers({"x-rapidapi-host: mashape-community-urban-dictionary.p.rapidapi.com",
              "x-rapidapi-key: BdjmYs1AKjmshOpwapzT8a03oWZqp1xHMZAjsn0lNpLCbgzMVy"})
    @GET("define")
    Call<TermDefinitionList> call( @Query("term") String term );
}
