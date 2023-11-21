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

    public final StringPath airingPeriod = createString("airingPeriod");

    public final ListPath<BroadcastEpisode, QBroadcastEpisode> broadcastEpisodes = this.<BroadcastEpisode, QBroadcastEpisode>createList("broadcastEpisodes", BroadcastEpisode.class, QBroadcastEpisode.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> episodeNum = createNumber("episodeNum", Integer.class);

    public final StringPath image_path = createString("image_path");

    public final StringPath nation = createString("nation");

    public final StringPath pBoardCasting = createString("pBoardCasting");

    public final StringPath pCast = createString("pCast");

    public final StringPath pCategory = createString("pCategory");

    public final StringPath pDirector = createString("pDirector");

    public final NumberPath<Integer> pseq = createNumber("pseq", Integer.class);

    public final StringPath pTitle = createString("pTitle");

    public final StringPath pWriter = createString("pWriter");

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

