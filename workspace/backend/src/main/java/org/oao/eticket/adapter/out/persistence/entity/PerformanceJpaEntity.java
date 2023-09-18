package org.oao.eticket.adapter.out.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.oao.eticket.application.domain.model.PerformanceGenre;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "performance")
public class PerformanceJpaEntity {
  @Id
  @Column(name = "performance_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  @NotBlank
  private String title;

  @Column(nullable = false)
  @NotNull
  @Convert(converter = PerformanceGenreConverter.class)
  private PerformanceGenre genre;

  @Column private String cast;

  @Column private String description;

  @Column private String posterImagePath;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "venue_id")
  private VenueJpaEntity venueJpaEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserJpaEntity hostJpaEntity;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "performanceJpaEntity")
  private List<SeatClassJpaEntity> seatClassJpaEntityList; // 양방향

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "performanceJpaEntity")
  private List<PerformanceScheduleJpaEntity> performanceScheduleJpaEntityList; // 양방향

  @Builder
  public PerformanceJpaEntity(
      Integer id,
      String title,
      PerformanceGenre genre,
      String cast,
      String description,
      String posterImagePath,
      VenueJpaEntity venueJpaEntity,
      UserJpaEntity hostJpaEntity,
      List<SeatClassJpaEntity> seatClassJpaEntityList,
      List<PerformanceScheduleJpaEntity> performanceScheduleJpaEntityList) {
    this.id = id;
    this.title = title;
    this.genre = genre;
    this.cast = cast;
    this.description = description;
    this.posterImagePath = posterImagePath;
    this.venueJpaEntity = venueJpaEntity;
    this.hostJpaEntity = hostJpaEntity;
    this.seatClassJpaEntityList = seatClassJpaEntityList;
    this.performanceScheduleJpaEntityList = performanceScheduleJpaEntityList;
  }
}