package com.riobener.userservice.model;

import com.riobener.userservice.domain.model.entities.Favorite;

public class FavoriteModel {
    private Long id;
    private Long sampleId;

    public static FavoriteModel toModel(Favorite favorite){
        FavoriteModel model = new FavoriteModel();
        model.setId(favorite.getId());
        model.setSampleId(favorite.getSampleId());
        return model;
    }

    public Long getSampleId() {
        return sampleId;
    }

    public void setSampleId(Long sampleId) {
        this.sampleId = sampleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
