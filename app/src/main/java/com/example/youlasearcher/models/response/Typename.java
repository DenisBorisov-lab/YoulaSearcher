package com.example.youlasearcher.models.response;

import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum Typename {
    IMAGE;

    @JsonValue
    public String toValue() {
        switch (this) {
            case IMAGE: return "Image";
        }
        return null;
    }

    @JsonCreator
    public static Typename forValue(String value) throws IOException {
        if (value.equals("Image")) return IMAGE;
        throw new IOException("Cannot deserialize Typename");
    }
}
