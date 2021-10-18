package com.example.jpashop.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.mysema.query.types.Path;


/**
 * QDuck is a Querydsl query type for Duck
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDuck extends EntityPathBase<Duck> {

    private static final long serialVersionUID = 622000437L;

    public static final QDuck duck = new QDuck("duck");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QDuck(String variable) {
        super(Duck.class, forVariable(variable));
    }

    public QDuck(Path<? extends Duck> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDuck(PathMetadata<?> metadata) {
        super(Duck.class, metadata);
    }

}

