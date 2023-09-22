import React from 'react';
import Carousel from 'components/common/Carousel/Carousel';
import { Button, Typography } from '@mui/material';

import './ConcertRankingList.scss';

const ConcertRankingList = () => {
  return (
    <div className="concert-ranking">
      <Typography variant="h6">공연 랭킹</Typography>
      <Carousel />
      <Button fullWidth variant="contained">
        전체 보기
      </Button>
    </div>
  );
};

export default ConcertRankingList;