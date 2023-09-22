import React from 'react';
import './ConcertItem.scss';
import useMovePage from 'hooks/useMovePage.ts';

interface ConcertListItem {
  id: number;
  image: string;
  title: string;
  location: string;
  date: string;
}

function ConcertItem({ concertInfo }: { concertInfo: ConcertListItem }) {
  const movePage = useMovePage();

  const handleConcertClick = () => {
    movePage(`/concert/${concertInfo.id}`, concertInfo);
  };

  return (
    <div
      className="concert-item-outer-box"
      onClick={handleConcertClick}
      aria-hidden
    >
      <div className="concert-left-box">
        <img
          className="rank-frame concert-context"
          src="https://tickets.interpark.com/contents/_next/static/media/ranking_badge_purple.ea646533.svg"
          alt="1"
        />
        <span className="concert-number">{concertInfo.id}</span>
      </div>
      <div className="concert-center-box">
        <div className="concert-context">
          <img src={concertInfo.image} alt="사진" />
        </div>
      </div>
      <div className="concert-right-box">
        <div className="concert-tag">단독 공연</div>
        <div className="concert-title">{concertInfo.title}</div>
        <div className="concert-location">{concertInfo.location}</div>
        <div className="concert-date">{concertInfo.date}</div>
      </div>
    </div>
  );
}

export default ConcertItem;