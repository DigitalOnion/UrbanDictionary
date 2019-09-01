package com.outerspace.urbandictionary.presenter;

import com.outerspace.urbandictionary.api.TermDefinition;
import com.outerspace.urbandictionary.model.WebService;
import com.outerspace.urbandictionary.model.WebServiceCallback;

import java.util.List;
import java.util.function.Consumer;


public class MainPresenter implements WebServiceCallback {
    private static WebService webService = WebService.getInstance();
    private Consumer<String> stringConsumer;

    public void fetchDefinitions(String term, Consumer<String> stringConsumer) {
        this.stringConsumer = stringConsumer;
        webService.fetchDefinitions(term, this);
    }

    @Override
    public void onSuccess(List<TermDefinition> definitionList) {
        StringBuilder sb = new StringBuilder();
        for(TermDefinition definition : definitionList) {
            sb.append(definition.definition).append('\n');
        }
        stringConsumer.accept(sb.toString());
    }

    @Override
    public void onFailure(String message) {
        stringConsumer.accept(message);
    }
}
