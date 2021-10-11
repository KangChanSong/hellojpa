package com.example.jpa.domain.relationship;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
