package ddd.caffeine.ratrip.module.place.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlace is a Querydsl query type for Place
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlace extends EntityPathBase<Place> {

    private static final long serialVersionUID = 2080744232L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlace place = new QPlace("place");

    public final ddd.caffeine.ratrip.module.place.model.address.QAddress address;

    public final EnumPath<Category> category = createEnum("category", Category.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath imageLink = createString("imageLink");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Long> kakaoId = createNumber("kakaoId", Long.class);

    public final SimplePath<org.geolatte.geom.Point> location = createSimple("location", org.geolatte.geom.Point.class);

    public final StringPath name = createString("name");

    public final StringPath telephone = createString("telephone");

    public QPlace(String variable) {
        this(Place.class, forVariable(variable), INITS);
    }

    public QPlace(Path<? extends Place> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlace(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlace(PathMetadata metadata, PathInits inits) {
        this(Place.class, metadata, inits);
    }

    public QPlace(Class<? extends Place> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new ddd.caffeine.ratrip.module.place.model.address.QAddress(forProperty("address")) : null;
    }

}

