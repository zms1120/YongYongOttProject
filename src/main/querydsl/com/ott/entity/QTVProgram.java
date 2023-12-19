package com.ott.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTVProgram is a Querydsl query type for TVProgram
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTVProgram extends EntityPathBase<TVProgram> {

    private static final long serialVersionUID = -1052439421L;

    public static final QTVProgram tVProgram = new QTVProgram("tVProgram");

    public final StringPath airing_period = createString("airing_period");

    public final StringPath banner_path = createString("banner_path");

    public final StringPath description = createString("description");

    public final ListPath<Episode, QEpisode> Episodes = this.<Episode, QEpisode>createList("Episodes", Episode.class, QEpisode.class, PathInits.DIRECT2);

    public final StringPath image_path = createString("image_path");

    public final StringPath nation = createString("nation");

    public final StringPath p_board_casting = createString("p_board_casting");

    public final StringPath p_cast = createString("p_cast");

    public final StringPath p_category = createString("p_category");

    public final StringPath p_director = createString("p_director");

    public final StringPath p_title = createString("p_title");

    public final StringPath p_writer = createString("p_writer");

    public final NumberPath<Integer> pseq = createNumber("pseq", Integer.class);

    public final StringPath rating = createString("rating");

    public QTVProgram(String variable) {
        super(TVProgram.class, forVariable(variable));
    }

    public QTVProgram(Path<? extends TVProgram> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTVProgram(PathMetadata metadata) {
        super(TVProgram.class, metadata);
    }

}

