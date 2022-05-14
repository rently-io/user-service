package io.rently.userservice.dtos;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "users")
@Getter
@Setter
@ToString
public class User {
    @Id
    @Column(updatable = false, nullable = false, unique = true)
    private String id;
    @Column(updatable = false, nullable = false, columnDefinition = "TEXT")
    private String providerId;
    @Column(updatable = false, nullable = false, columnDefinition = "TEXT")
    private String provider;
    @Column(columnDefinition = "TEXT")
    private String name;
    @Column(columnDefinition = "TEXT")
    private String email;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String createdAt;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}