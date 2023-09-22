package org.oao.eticket.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class WaitingRepository {

  private final RedisTemplate<String, Integer> waitingStorage;
  @Autowired
  public WaitingRepository(@Qualifier("waitingStorage") RedisTemplate<String, Integer> waitingStorage) {
    this.waitingStorage = waitingStorage;
  }

  /**
   * @desc: Sorted Set 초기화
   */
  public ZSetOperations<String, Integer> opsForZSet() {
    return waitingStorage.opsForZSet();
  }

  /**
   * @desc: Sorted Set 요소 삽입
   */
  public void zAdd(String key, Integer userId) {
    opsForZSet().add(key, userId, System.currentTimeMillis());
  }

  /**
   * @desc: Sorted Set 요소 삭제
   */
  public void zPop(String key, Long size) {
    opsForZSet().popMin(key, size);
  }

  /**
   * @desc: Sorted Set key 조회
   */
  public Set<String> getKeys(String pattern) {
    return waitingStorage.keys(pattern);
  }

  /**
   * @desc: Sorted Set 삭제
   */
  public void delete(String key) {
    waitingStorage.delete(key);
  }

  /**
   * @desc: Sorted Set 자료형 사이즈
   */
  public Long zCard(String key) {
    return opsForZSet().size(key);
  }

  /**
   * @desc: Sorted Set 자료형 start ~ end 까지 조회.
   */
  public Set<Integer> zRange(String key, Long start, Long end) {
    return opsForZSet().range(key, start, end);
  }

  /**
   * @desc: Sorted Set 자료형 Value의 현재위치 조회.
   */
  public Long zRank(String key, Integer userId) {
    return opsForZSet().rank(key, userId);
  }
}
