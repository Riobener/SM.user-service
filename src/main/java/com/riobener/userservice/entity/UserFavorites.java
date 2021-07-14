package com.riobener.userservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "favorites")
public class UserFavorites {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "userId")
    private String userId;
    @Column(name = "sampleId")
    private String sampleId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
