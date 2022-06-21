package com.denisbrisov.youlasearcher.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Execution {
    private Object[] resolvers;

    @JsonProperty("resolvers")
    public Object[] getResolvers() {
        return resolvers;
    }

    @JsonProperty("resolvers")
    public void setResolvers(Object[] value) {
        this.resolvers = value;
    }
}
