package com.example.youlasearcher.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
    private String operationName;
    private Variables variables;
    private Extensions extensions;

    @JsonProperty("operationName")
    public String getOperationName() {
        return operationName;
    }

    @JsonProperty("operationName")
    public void setOperationName(String value) {
        this.operationName = value;
    }

    @JsonProperty("variables")
    public Variables getVariables() {
        return variables;
    }

    @JsonProperty("variables")
    public void setVariables(Variables value) {
        this.variables = value;
    }

    @JsonProperty("extensions")
    public Extensions getExtensions() {
        return extensions;
    }

    @JsonProperty("extensions")
    public void setExtensions(Extensions value) {
        this.extensions = value;
    }
}
