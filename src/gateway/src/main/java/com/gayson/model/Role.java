package com.gayson.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by jixunzhen on 2018/5/14.
 */
@Entity
@Table(name = "t_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Short id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName name;


    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "roles"
    )
    private Set<User> users;


    public enum RoleName {
        ROLE_ADMIN, ROLE_USER,
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
