package org.oao.eticket.application.domain.service;

import lombok.RequiredArgsConstructor;
import org.oao.eticket.application.domain.model.Vacancy;
import org.oao.eticket.application.port.in.PreemptVacancyUseCase;
import org.oao.eticket.application.port.out.dto.PreemptVacancyCommand;
import org.oao.eticket.application.port.out.PreemptVacancyPort;
import org.oao.eticket.common.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class PreemptVacancyService implements PreemptVacancyUseCase {
  private final PreemptVacancyPort preemptVacancyPort;

  @Override
  public Boolean preemptVacancy(org.oao.eticket.application.port.in.dto.PreemptVacancyCommand cmd) {
    final var findVacancyCommand =
        PreemptVacancyCommand.builder()
            .performanceScheduleId(cmd.getPerformanceScheduleId())
            .sectionId(cmd.getSectionId())
            .seatId(cmd.getSeatId())
            .build();
    final var vacancyRedisEntity = preemptVacancyPort.preemptVacancy(findVacancyCommand);
    // Entity -> Model Mapping
    return null;
  }
}
