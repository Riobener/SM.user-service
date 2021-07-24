package com.riobener.userservice.infrastructure.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.riobener.userservice.domain.model.entities.User;
import com.riobener.userservice.infrastructure.dto.response.UserResponseDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateFavoriteDto {
    @JsonProperty("sample_id")
    private Long sampleId;

    @JsonProperty("user_id")
    private Long userId;

    public Long getSampleId() {
        return sampleId;
    }

    public void setSampleId(Long sampleId) {
        this.sampleId = sampleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
