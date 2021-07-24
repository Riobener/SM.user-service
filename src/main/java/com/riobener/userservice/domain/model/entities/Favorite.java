package com.riobener.userservice.domain.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "sample_id")
    private Long sampleId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Favorite(Long sampleId, User user) {
        this.sampleId = sampleId;
        this.user = user;
    }

    public Favorite() {

    }

    public Long getSampleId() {
        return sampleId;
    }

    public void setSampleId(Long sampleId) {
        this.sampleId = sampleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
