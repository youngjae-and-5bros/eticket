package org.oao.eticket.application.port.in;

import org.oao.eticket.application.domain.model.User;
import org.oao.eticket.application.port.in.dto.UserDto;

public interface GetUserDetailsUseCase {

  UserDto getByUserId(final User.UserId userId);
}
