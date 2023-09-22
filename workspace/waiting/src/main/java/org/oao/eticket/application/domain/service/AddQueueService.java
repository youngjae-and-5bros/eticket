package org.oao.eticket.application.domain.service;

import lombok.RequiredArgsConstructor;
import org.oao.eticket.application.port.in.AddQueueUseCase;
import org.oao.eticket.application.port.out.AddQueuePort;
import org.oao.eticket.common.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class AddQueueService implements AddQueueUseCase {

  private final AddQueuePort addQueuePort;

  @Override
  public void addQueue(Integer userId, Integer performanceScheduleId) {
    String key = "Waiting::" + performanceScheduleId;
    addQueuePort.addQueue(key, userId);
  }
}
