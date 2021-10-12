package com.example.jpa.domain.relationship.embedded;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter @Setter
@Embeddable
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
