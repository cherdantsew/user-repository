package com.orderengine.user.model.entity;

import com.orderengine.user.model.enumeration.AuthoritiesConstants;
import com.orderengine.user.model.enumeration.RolesConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    private static final String AUTHORITIES_DELIMITER = ",";
    @Id
    private Short id;
    @Column(name = "role_name")
    private RolesConstants roleName;
    @Column(name = "authorities")
    private String authorities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return getId() != null && Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setId(Short id) {
        this.id = id;
    }

    public void setRoleName(RolesConstants role) {
        this.roleName = role;
    }

    public void setAuthorities(List<AuthoritiesConstants> authorities) {
        this.authorities = authorities.stream().map(String::valueOf).collect(Collectors.joining(AUTHORITIES_DELIMITER));
    }

}
