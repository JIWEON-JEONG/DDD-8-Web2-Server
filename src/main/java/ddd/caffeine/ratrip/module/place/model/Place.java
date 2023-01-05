package ddd.caffeine.ratrip.module.place.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.geolatte.geom.Point;

import ddd.caffeine.ratrip.common.util.SequentialUUIDGenerator;
import ddd.caffeine.ratrip.module.place.model.address.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 장소 도메인.
 * TODO: field columnDefinition 상의 하여 정할 것.
 */
@Getter
@Entity
@NoArgsConstructor
public class Place {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@NotNull
	@Column
	private Long kakaoId;

	@NotNull
	@Column(columnDefinition = "VARCHAR(100)")
	private String name;

	@Enumerated(EnumType.STRING)
	private Category category;

	@NotNull
	@Embedded
	private Address address;

	@NotNull
	@Column(columnDefinition = "POINT")
	//Point pnt = (Point) JTS.to( Wkt.fromWkt( "POINT Z( 3.41127795 8.11062269 2.611)", Wkt.Dialect.SFA_1_2_1 ) );
	private Point location;

	@NotNull
	@Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
	private boolean isDeleted = false;

	@Column(columnDefinition = "VARCHAR(100)")
	private String imageLink;

	@Column(columnDefinition = "VARCHAR(100)")
	private String telephone;

	@PrePersist
	public void createPlacePrimaryKey() {
		//sequential uuid 생성
		this.id = SequentialUUIDGenerator.generate();
	}
}
