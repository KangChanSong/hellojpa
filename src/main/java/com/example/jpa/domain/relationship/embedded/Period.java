package com.example.jpa.domain.relationship.embedded;

import lombok.Getter;
import net.bytebuddy.asm.Advice;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Embeddable
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Period(){}
    public Period(LocalDateTime startDate, LocalDateTime endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
