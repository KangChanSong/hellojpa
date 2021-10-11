package com.example.jpa.domain.relationship;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class GrandChild {

    @EmbeddedId
    private GrandChildId id;

    @MapsId("childId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "child_id", referencedColumnName = "child_id"),
            @JoinColumn(name = "parent_id", referencedColumnName = "parent_id")
    })
    private Child child;
}
