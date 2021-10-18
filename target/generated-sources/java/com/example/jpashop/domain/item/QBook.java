package com.example.jpashop.domain.item;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.mysema.query.types.Path;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = -315462104L;

    public static final QBook book = new QBook("book");

    public final QItem _super = new QItem(this);

    public final StringPath author = createString("author");

    //inherited
    public final ListPath<com.example.jpashop.domain.Category, com.example.jpashop.domain.QCategory> categories = _super.categories;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath isbn = createString("isbn");

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final NumberPath<Integer> price = _super.price;

    //inherited
    public final NumberPath<Integer> stockQuantity = _super.stockQuantity;

    public QBook(String variable) {
        super(Book.class, forVariable(variable));
    }

    public QBook(Path<? extends Book> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBook(PathMetadata<?> metadata) {
        super(Book.class, metadata);
    }

}

