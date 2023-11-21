package com.ott.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMovie is a Querydsl query type for Movie
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMovie extends EntityPathBase<Movie> {

    private static final long serialVersionUID = -1790067087L;

    public static final QMovie movie = new QMovie("movie");

    public final StringPath cast = createString("cast");

    public final StringPath category = createString("category");

    public final StringPath description = createString("description");

    public final StringPath director = createString("director");

    public final StringPath genre = createString("genre");

    public final StringPath image_path = createString("image_path");

    public final StringPath keyword = createString("keyword");

    public final StringPath movie_code = createString("movie_code");

    public final StringPath mTitle_ko = createString("mTitle_ko");

    public final StringPath mTitle_ori = createString("mTitle_ori");

    public final StringPath nation = createString("nation");

    public final StringPath openYear = createString("openYear");

    public final StringPath rating = createString("rating");

    public final StringPath running_time = createString("running_time");

    public final StringPath video_path = createString("video_path");

    public QMovie(String variable) {
        super(Movie.class, forVariable(variable));
    }

    public QMovie(Path<? extends Movie> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMovie(PathMetadata metadata) {
        super(Movie.class, metadata);
    }

}

