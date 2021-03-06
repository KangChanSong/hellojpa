package com.example.jpashop.domain.item;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.processing.Generated;

import com.mysema.query.types.Path;


/**
 * QAlbum is a Querydsl query type for Album
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAlbum extends EntityPathBase<Album> {

    private static final long serialVersionUID = -1190415600L;

    public static final QAlbum album = new QAlbum("album");

    public final QItem _super = new QItem(this);

    public final StringPath artist = createString("artist");

    //inherited
    public final ListPath<com.example.jpashop.domain.Category, com.example.jpashop.domain.QCategory> categories = _super.categories;

    public final StringPath etc = createString("etc");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final NumberPath<Integer> price = _super.price;

    //inherited
    public final NumberPath<Integer> stockQuantity = _super.stockQuantity;

    public QAlbum(String variable) {
        super(Album.class, forVariable(variable));
    }

    public QAlbum(Path<? extends Album> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAlbum(PathMetadata<?> metadata) {
        super(Album.class, metadata);
    }

}

