package com.example.jpa.domain.relationship;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
public class GrandChildId implements Serializable {

    private String child;
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrandChildId that = (GrandChildId) o;
        return Objects.equals(child, that.child) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(child, id);
    }
}
