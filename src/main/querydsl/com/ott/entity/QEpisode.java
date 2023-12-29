package com.ott.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEpisode is a Querydsl query type for Episode
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEpisode extends EntityPathBase<Episode> {

    private static final long serialVersionUID = -760712964L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEpisode episode = new QEpisode("episode");

    public final DateTimePath<java.util.Date> airing_date = createDateTime("airing_date", java.util.Date.class);

    public final StringPath description = createString("description");

    public final StringPath ep_title = createString("ep_title");

    public final StringPath episode_num = createString("episode_num");

    public final StringPath running_time = createString("running_time");

    public final QTVProgram tvProgram;

    public final StringPath video_path = createString("video_path");

    public QEpisode(String variable) {
        this(Episode.class, forVariable(variable), INITS);
    }

    public QEpisode(Path<? extends Episode> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEpisode(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEpisode(PathMetadata metadata, PathInits inits) {
        this(Episode.class, metadata, inits);
    }

    public QEpisode(Class<? extends Episode> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tvProgram = inits.isInitialized("tvProgram") ? new QTVProgram(forProperty("tvProgram")) : null;
    }

}

