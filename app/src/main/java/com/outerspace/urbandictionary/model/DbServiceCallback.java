package com.outerspace.urbandictionary.model;

import com.outerspace.urbandictionary.api.TermDefinition;

import java.util.List;

public interface DbServiceCallback {
    void onQueryTermReady(List<TermDefinition> definitions);
}
