// HorizontalLineCharts.js
import React,{useState,useEffect} from 'react';
import { Line } from 'react-chartjs-2';
import styled from 'styled-components';
import {getRetailPriceData,getWholesalePriceData} from '../../api/graph'

import { Chart as ChartJS } from 'chart.js/auto'
import { Chart }            from 'react-chartjs-2'

const ChartContainer = styled.div`
  display: flex;
  flex-direction: column;  // 세로로 배열
  align-items: center;     // 중앙 정렬
  padding: 20px;
  background-color: #f4f4f4;
  border-radius: 15px;
`;

const IndividualChartContainer = styled.div`
  width: 90%;  // 너비 조정 (원하는 너비로 변경 가능)
  height: 400px;
  margin-bottom: 20px;  // 아래 여백 추가
  background-color: #fff;
  padding: 20px;
  box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
  border-radius: 15px;
`;

const NoDataDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  color: #999;
  font-size: 16px;
  font-weight: bold;
`;

function PriceChart({itemId,nameId}) {

  //소매 가격 데이터
  const [retailData, setRetailData] = useState([]);

  //도매 가격 데이터
  const [wholesaleData, setWholeSaleData] = useState([]);
  
  //소매가 가격 데이터 가져오기
  useEffect(() => {
    async function fetchData() {
      await getRetailPriceData({
        responseFunc: {
          200: (response) => {
            console.log("retail Price", response.data);
            setRetailData(response.data)
          },
        },
        data: { nameId, itemId },
      });
    }
    fetchData();
  }, [itemId, nameId]);

  //도매가 가격 데이터 가져오기
  useEffect(() => {
    async function fetchData() {
      await getWholesalePriceData({
        responseFunc: {
          200: (response) => {
            console.log("wholesale Price", response.data);
            setWholeSaleData(response.data)
          },
        },
        data: { nameId, itemId },
      });
    }
    fetchData();
  }, [itemId, nameId]);

  const data1 = {
    labels: wholesaleData.map(({recorded_at})=> recorded_at),
    datasets: [{
      label: '도매가',
      data: wholesaleData.map(({price})=> price),
      fill: false,
      borderColor: 'rgba(255, 99, 132, 1)'
    }]
  };

  const data2 = {
    labels: retailData.map(({recorded_at})=> recorded_at),
    datasets: [{
      label: '소매가',
      data: retailData.map(({price})=> price),
      fill: false,
      borderColor: 'rgba(54, 162, 235, 1)'
    }]
  };

  return (
    <ChartContainer>
      <IndividualChartContainer>
        {
          wholesaleData.length > 0 ?
          <Line data={data1} options={{ maintainAspectRatio: false }} /> :
          <NoDataDiv>최근 가격 데이터가 없습니다.</NoDataDiv>
        }
      </IndividualChartContainer>
      <IndividualChartContainer>
        {
          retailData.length > 0 ?
          <Line data={data2} options={{ maintainAspectRatio: false }} /> :
          <NoDataDiv>최근 가격 데이터가 없습니다.</NoDataDiv>
        }
      </IndividualChartContainer>
    </ChartContainer>
  );
}

export default PriceChart;