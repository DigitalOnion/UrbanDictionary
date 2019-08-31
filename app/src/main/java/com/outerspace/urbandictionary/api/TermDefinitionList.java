
package com.outerspace.urbandictionary.api;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TermDefinitionList {

    @SerializedName("list")
    @Expose
    public java.util.List<TermDefinition> list = new ArrayList<TermDefinition>();

}
