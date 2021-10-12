package com.example.jpa.domain.superclass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
public abstract class BaseEntity {

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
