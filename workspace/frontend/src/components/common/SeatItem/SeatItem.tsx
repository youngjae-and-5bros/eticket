import React from 'react';
import './SeatItem.scss';
import SeatDummy from 'seatDummy';
import SeatBox from 'components/seat/SeatBox/SeatBox';
import { useRecoilState } from 'recoil';
import SelectSeatState from 'atoms/SelectSeatState';
import useMovePage from 'hooks/useMovePage';

function SeatItem({ index }: { index: number }) {
  const { movePage } = useMovePage();
  // const [totalPrice, setTotalPrice] = useState(0);
  const clickBuyBtn = () => {
    movePage('/checkout', null);
  };

  const data = SeatDummy[index].seat;
  const [selectedSeats] = useRecoilState(SelectSeatState);

  return (
    <div>
      <div className="section-number-box">자리 섹션{index}</div>
      <div className="seat-outer-box">
        <div className="seat-item-container2">
          {data.map((_, idx) => (
            // eslint-disable-next-line react/no-array-index-key
            <div key={idx} className="seat-section-wrapper">
              <SeatBox index={idx} state={_} />
            </div>
          ))}
        </div>
      </div>
      <div>
        <div className="section-title">좌석 선택</div>
        {selectedSeats.length > 0 ? (
          <div>
            {selectedSeats.map((seat, i) => (
              // eslint-disable-next-line react/no-array-index-key
              <div key={i} className="select-seat-price-box">
                <div className="selected-seat">
                  {/* <div>{index}섹션</div> */}
                  <div>{seat + 1}번 좌석</div>
                </div>
                <div> 가격: 120,000원</div>
              </div>
            ))}
          </div>
        ) : (
          <div className="no-selected-seats">선택한 좌석이 없습니다.</div>
        )}

        {selectedSeats.length > 0 && (
          <div>
            <div className="total-price">
              합계: {selectedSeats.length * 120000}원
            </div>
            <div className="click-buy-button" onClick={clickBuyBtn} aria-hidden>
              결제하기
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default SeatItem;