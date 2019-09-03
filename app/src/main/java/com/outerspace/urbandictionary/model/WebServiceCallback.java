package com.outerspace.urbandictionary.model;

import com.outerspace.urbandictionary.api.TermDefinition;

import java.util.List;

public interface WebServiceCallback {

    void onSuccess(List<TermDefinition> termDefinitions);

    void onFailure(String message);
}
