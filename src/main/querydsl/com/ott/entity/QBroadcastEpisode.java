package com.ott.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBroadcastEpisode is a Querydsl query type for BroadcastEpisode
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBroadcastEpisode extends EntityPathBase<BroadcastEpisode> {

    private static final long serialVersionUID = -765304807L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBroadcastEpisode broadcastEpisode = new QBroadcastEpisode("broadcastEpisode");

    public final StringPath airing_date = createString("airing_date");

    public final StringPath description = createString("description");

    public final StringPath ep_title = createString("ep_title");

    public final StringPath episode_num = createString("episode_num");

    public final StringPath running_time = createString("running_time");

    public final QTVProgram tvProgram;

    public final StringPath video_path = createString("video_path");

    public QBroadcastEpisode(String variable) {
        this(BroadcastEpisode.class, forVariable(variable), INITS);
    }

    public QBroadcastEpisode(Path<? extends BroadcastEpisode> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBroadcastEpisode(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBroadcastEpisode(PathMetadata metadata, PathInits inits) {
        this(BroadcastEpisode.class, metadata, inits);
    }

    public QBroadcastEpisode(Class<? extends BroadcastEpisode> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tvProgram = inits.isInitialized("tvProgram") ? new QTVProgram(forProperty("tvProgram")) : null;
    }

}

