package com.gayson.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jixunzhen on 2018/5/13.
 */
@Entity
@Table(name = "t_platform")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Short id;
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private PlatformType type;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "platforms")
    private Set<Restaurant> restaurants = new HashSet<>();

    public Short getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlatformType getType() {
        return type;
    }

    public void setType(PlatformType type) {
        this.type = type;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
