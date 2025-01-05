package com.school_managemtent.dto.response;

import java.util.List;

public class RelationResponse {

    private String code;
    private String description;
    private List<Long> relation;

    public RelationResponse(){};

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getRelation() {
        return relation;
    }

    public void setRelation(List<Long> relation) {
        this.relation = relation;
    }
}
