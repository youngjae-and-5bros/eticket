package org.oao.eticket.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oao.eticket.application.domain.model.TicketStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservation")
public class ReservationJpaEntity {

  @Id
  @Column(name = "reservation_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserJpaEntity userJpaEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "performance_schedule_id")
  private PerformanceScheduleJpaEntity performanceScheduleJpaEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "seat_id")
  private SeatJpaEntity seatJpaEntity;

  @Column(name = "payment_amount")
  private Integer paymentAmount;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private TicketStatus ticketStatus;

  @Column(name = "reservation_time")
  private LocalDateTime reservationTime;

  @Builder
  public ReservationJpaEntity(
      Integer id,
      UserJpaEntity userJpaEntity,
      PerformanceScheduleJpaEntity performanceScheduleJpaEntity,
      SeatJpaEntity seatJpaEntity,
      Integer paymentAmount,
      TicketStatus ticketStatus,
      LocalDateTime reservationTime) {
    this.id = id;
    this.userJpaEntity = userJpaEntity;
    this.performanceScheduleJpaEntity = performanceScheduleJpaEntity;
    this.seatJpaEntity = seatJpaEntity;
    this.paymentAmount = paymentAmount;
    this.ticketStatus = ticketStatus;
    this.reservationTime = LocalDateTime.now(ZoneId.of("KST"));
  }
}