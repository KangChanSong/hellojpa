package com.example.jpa.domain.relationship;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
public class ChildId implements Serializable {

    private String parent;
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildId childId = (ChildId) o;
        return Objects.equals(parent, childId.parent) && Objects.equals(id, childId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, id);
    }
}
