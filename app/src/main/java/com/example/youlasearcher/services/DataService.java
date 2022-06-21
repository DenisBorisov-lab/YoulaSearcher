package com.example.youlasearcher.services;

import com.example.youlasearcher.models.location.LocationModel;
import com.example.youlasearcher.models.request.Attribute;
import com.example.youlasearcher.models.request.DatePublished;
import com.example.youlasearcher.models.request.Extensions;
import com.example.youlasearcher.models.request.Location;
import com.example.youlasearcher.models.request.PersistedQuery;
import com.example.youlasearcher.models.request.Request;
import com.example.youlasearcher.models.request.Variables;
import com.example.youlasearcher.models.response.YoulaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.SneakyThrows;

public class DataService {
    private final String[] attributes;
    private final String json;
    private final ObjectMapper mapper;
    private String category;

    public DataService(String domain, String attributes, String json) {
        String[] split = domain.split("/");
        this.category = split[split.length - 1];
        this.attributes = attributes.split("&");
        this.json = json;
        mapper = JsonMapper.builder()
                .build();
    }

    public Request getModelRequest() {
        Request request = new Request();

        request.setOperationName(getOperationName());
        request.setVariables(getVariables());
        request.setExtensions(getExtensions());

        return request;
    }

    @SneakyThrows
    public YoulaResponse getResultedObject(String json) {
        return mapper.readValue(json, YoulaResponse.class);
    }

    private Variables getVariables() {
        Variables variables = new Variables();
        variables.setSort(getSort());
        variables.setAttributes(getAttributes());
        variables.setDatePublished(getDatePublished());
        variables.setLocation(getLocation());
        variables.setSearch(getSearch());
        variables.setCursor("");
        return variables;
    }

    private String getOperationName() {
        return "catalogProductsBoard";
    }

    private Extensions getExtensions() {
        Extensions extensions = new Extensions();

        extensions.setPersistedQuery(getPersistedQuery());

        return extensions;
    }

    private PersistedQuery getPersistedQuery() {
        PersistedQuery persistedQuery = new PersistedQuery();

        persistedQuery.setVersion(1L);
        persistedQuery.setSha256Hash("6e7275a709ca5eb1df17abfb9d5d68212ad910dd711d55446ed6fa59557e2602");

        return persistedQuery;
    }

    private String getSort() {
        String value = "";
        if (!attributes[0].equals("")) {
            for (String attribute : attributes) {
                if (attribute.charAt(0) == 'a') {
                    if (attribute.startsWith("sort_field", 11)) {
                        String[] splintedAttribute = attribute.split("=");
                        value = splintedAttribute[1];
                    }
                }
            }
        }


        switch (value) {
            case "price":
                value = "PRICE_ASC";
                break;
            case "date_published":
                value = "DATE_PUBLISHED_DESC";
                break;
            default:
                value = "DEFAULT";
                break;
        }

        return value;
    }

    @SneakyThrows
    private String getSearch() {
        String value = "";
        if (attributes[0].equals("")) {
            return value;
        }
        for (String attribute : attributes) {
            if (attribute.charAt(0) == 'q' && attribute.length() > 1) {
                value = URLDecoder.decode(attribute.split("=")[1], StandardCharsets.UTF_8.name());
            }
        }

        return value;
    }

    private DatePublished getDatePublished() {
        DatePublished datePublished = new DatePublished();
        if (attributes[0].equals("")) {
            return null;
        }
        for (String attribute : attributes) {
            if (attribute.charAt(0) == 'a') {
                if (attribute.substring(11).split("]\\[")[0].equals("term_of_placement")) {
                    if (attribute.charAt(30) == 't') {
                        datePublished.setTo(System.currentTimeMillis() / 1000L);
                    } else {
                        datePublished.setFrom(System.currentTimeMillis() / 1000L - ((long) Integer.parseInt(attribute.substring(37, 38)) * 3600 * 24));
                    }
                }
            }
        }

        return datePublished.getTo() == null ? null : datePublished;

    }

    private Attribute[] getAttributes() {
        List<Attribute> result = new ArrayList<>();
        Set<String> slugs = new HashSet<>();
        if (attributes[0].equals("")) {
            Attribute attribute = new Attribute();
            attribute.setSlug("categories");
            attribute.setValue(new String[]{category});
            result.add(attribute);
            Attribute[] list = new Attribute[result.size()];
            result.toArray(list);
            return list;
        }
        for (String attribute : attributes) {
            if (attribute.charAt(0) == 'a') {
                String slug = null;
                String index = null;
                String value = null;
                String[] slugAndParams = attribute.substring(11).split("]\\[");
                slug = slugAndParams[0];
                String[] indexAndValue = slugAndParams[1].split("]=");
                index = indexAndValue[0];
                if (indexAndValue.length == 2) {
                    value = indexAndValue[1];
                }

                if (!slug.equals("term_of_placement")) {
                    if (slugs.contains(slug)) {
                        // если этот обьект уже был
                        for (Attribute object : result) {
                            if (object.getSlug().equals(slug)) {
                                if (index.equals("to") && value != null) {
                                    object.setTo(Long.parseLong(value));
                                } else if (index.equals("from") && value != null) {
                                    object.setFrom(Long.parseLong(value));
                                } else if (value != null) {
                                    String[] values = object.getValue();
                                    object.setValue(getValues(values, value));
                                }
                            }
                        }
                    } else {
                        slugs.add(slug);
                        Attribute object = new Attribute();

                        object.setSlug(slug);
                        if (index.equals("to") && value != null) {
                            object.setTo(Long.parseLong(value));
                        } else if (index.equals("from") && value != null) {
                            object.setFrom(Long.parseLong(value));
                        } else if (value != null) {
                            String[] values = new String[1];
                            values[0] = value;
                            object.setValue(values);
                        }

                        result.add(object);

                    }
                }
            }
        }
        Attribute attribute = new Attribute();
        attribute.setSlug("categories");
        attribute.setValue(new String[]{category});
        result.add(attribute);

        Attribute[] list = new Attribute[result.size()];
        result.toArray(list);
        return list;
    }

    private String[] getValues(String[] values, String value) {
        String[] res = new String[values.length + 1];
        for (int i = 0; i < values.length; i++) {
            res[i] = values[i];
        }
        res[res.length - 1] = value;
        return res;

    }

    @SneakyThrows
    private Location getLocation() {
        LocationModel location = mapper.readValue(json, LocationModel.class);

        Location result = new Location();
        result.setCity(location.getCity().getID());
        result.setDistanceMax(location.getR());
        result.setLatitude(location.getCity().getCoords().getLatitude());
        result.setLongitude(location.getCity().getCoords().getLongitude());

        return result;
    }

}
