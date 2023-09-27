package org.oao.eticket.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.oao.eticket.application.domain.model.Performance;
import org.oao.eticket.application.port.in.GetHotPerformancesUseCase;
import org.oao.eticket.common.annotation.WebAdapter;
import org.oao.eticket.exception.PerformanceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
public class GetHotPerformancesController {
  record GetHotPerformancesResponseBody(List<Performance> hotsList) {
    // TODO(yoo): List<PerformanceSummary>
  }

  private final GetHotPerformancesUseCase getHotPerformancesUseCase;

  @Operation(
      summary = "인기 TOP 10 공연 List GET",
      description = "메인 화면에 보여 지는, 현재 인기 있는 공연 상위 10개 공연의 간단한 정보의 리스트를 불러 옵니다.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK 인기 공연 리스트",
            content =
                @Content(schema = @Schema(implementation = GetHotPerformancesResponseBody.class))),
        @ApiResponse(
            responseCode = "400",
            description = "NO CONTENT. (빈 리스트 - 인기 있는 공연 없다)",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
      })
  @GetMapping("performances/hot")
  @ResponseStatus(HttpStatus.OK)
  ResponseEntity<?> getHotPerformances() {
    try {
      List<Performance> list = getHotPerformancesUseCase.getHotPerformanceList();
      return ResponseEntity.ok(list);
      // ResponseBody에 넣어서 전달
    } catch (PerformanceNotFoundException e) {
      // TODO(yoo) :
      throw ApiException.builder()
          .withCause(e)
          .withStatus(HttpStatus.NO_CONTENT)
          .withMessage(e.getMessage())
          .build();
    } catch (IllegalArgumentException e) {
      throw ApiException.builder()
          .withCause(e)
          .withStatus(HttpStatus.BAD_REQUEST)
          .withMessage(e.getMessage())
          .build();
    } catch (Exception e) {
      throw ApiException.builder()
          .withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
          .withCause(e)
          .withMessage(e.getMessage())
          .build();
    }
  }
}
