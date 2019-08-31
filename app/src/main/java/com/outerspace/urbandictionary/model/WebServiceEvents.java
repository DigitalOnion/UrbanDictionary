package com.outerspace.urbandictionary.model;

import com.outerspace.urbandictionary.api.TermDefinitionList;

public interface WebServiceEvents {

    void onSuccess(TermDefinitionList definitionList);

    void onFailure(String message);
}
