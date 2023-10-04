/* eslint-disable @typescript-eslint/no-explicit-any */
import React from 'react';
import './DetailPoster.scss';
import PlaceOutlinedIcon from '@mui/icons-material/PlaceOutlined';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import BackImg from 'assets/BackIcon.svg';
import useMovePage from 'hooks/useMovePage';

// interface concertInfoTypes {
//   performanceId?: number;
//   image: string;
//   title: string;
//   location: string;
//   date: string;
//   concertdetail: string;
//   time: string;
// }

/** params: 이미지, 콘서트 제목, 캐스팅 */
function DetailPoster({ info }: { info: any }) {
  // console.log(info.performanceScheduleList[0]);
  const { name } = info.concertHall;
  // console.log(name);

  const startDate = info.performanceScheduleList[0].startDateTime;
  const datePart = startDate.split('T')[0];
  const endDate =
    info.performanceScheduleList[info.performanceScheduleList.length - 1]
      .startDateTime;

  const endDatePart = endDate.split('T')[0];
  // console.log(date);

  // const { endDate } = info.performanceScheduleList[-1];
  const { goBack } = useMovePage();
  return (
    <div>
      {/* <BackNavBar title="" /> */}
      <div className="back-point" onClick={() => goBack()} aria-hidden>
        <img src={BackImg} alt="뒤로가기" />
      </div>
      <div className="detail-poster-container">
        {/* <div>DetailPoster</div> */}
        <img src={info.posterImagePath} alt="" className="poster-image" />
        <div className="poster-concert-box">
          {/* <div>good</div> */}
          <div className="poster-left-box">
            <img
              src={info.posterImagePath}
              alt="포스토 서잔"
              style={{ width: '90px', height: '120px' }}
            />
          </div>
          <div className="poster-right-box">
            <div className="concert-title-text">{info.title}</div>
            <div className="concert-time">총:{info.runningTime}분</div>
            <div className="concert-period-box">
              <CalendarMonthIcon sx={{ color: 'gray' }} />

              <div className="concert-period">
                {datePart} ~ {endDatePart}
              </div>
            </div>
            <div className="concert-location-box">
              <PlaceOutlinedIcon sx={{ color: 'gray' }} />
              <div className="concert-location">{name}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default DetailPoster;
