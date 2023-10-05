// HorizontalLineCharts.js
import React, { useState, useEffect } from "react";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import { Line } from "react-chartjs-2";
import styled from "styled-components";
import { getRetailPriceData, getWholesalePriceData } from "../../api/graph";
import { getProductDataWithItemId } from "../../api/product";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

const DropdownContainer = styled.div`
  position: absolute;
  top: 10px;
  right: 10px;
  z-index: 1000;
`;

const ChartContainer = styled.div`
  display: flex;
  flex-direction: ${(props) =>
    props.orientation === "horizontal" ? "row" : "column"};
  align-items: center;
  padding: 20px;
  background-color: #f4f4f4;
  border-radius: 15px;
`;

const ChartFlexContainer = styled.div`
  display: flex;
  justify-content: space-between; // 차트와 드롭다운 간의 간격 최대화
  align-items: flex-start; // 상단 정렬
  width: 100%;
  height: 100%;
`;

const IndividualChartContainer = styled.div`
  position: relative;
  width: ${(props) => (props.orientation === "horizontal" ? "45%" : "90%")};
  height: ${(props) => props.chartHeight || "400px"};
  margin-bottom: 20px;
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

const StyledSelect = styled.select`
  padding: 8px 10px;
  margin: 0;
  font-size: 16px;
  line-height: 1.3;
  border: 1px solid #e1e4e8;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-radius: 4px;
  appearance: none; // 기본 브라우저 스타일 제거
  background-color: #fff;
  &:hover {
    border-color: #a4abb3;
  }
  &:focus {
    outline: none;
    border-color: #0366d6;
    box-shadow: 0 0 0 3px rgba(3, 102, 214, 0.3);
  }
`;

function PriceChart({
  itemId,
  chartHeight = "400px",
  orientation = "horizontal",
}) {
  //소매 가격 데이터
  const [retailData, setRetailData] = useState([]);

  //도매 가격 데이터
  const [wholesaleData, setWholeSaleData] = useState([]);

  //소매가 차트 옵션
  const [retailChartOptions, setRetailChartOptions] = useState({
    maintainAspectRatio: false,
  });

  //도매가 차트 옵션
  const [wholesaleChartOptions, setWholeSaleChartOptions] = useState({
    maintainAspectRatio: false,
  });

  //소매가 품종 드롭다운 리스트 옵션
  const [retailNameDropdownOptions, setRetailNameDropdownOptions] = useState(
    []
  );

  //도매가 품종 드롭다운 리스트 옵션
  const [wholesaleNameDropdownOptions, setWholeSaleNameDropdownOptions] =
    useState([]);

  //소매가 품종 드롭다운 리스트 선택한 옵션
  const [selectedRetailDropdownOption, setSelectedRetailDropdownOption] =
    useState("50");

  //도매가 품종 드롭다운 리스트 선택한 옵션
  const [selectedWholesaleDropdownOption, setSelectedWholesaleDropdownOption] =
    useState("50");

  // 소매가 품종 드롭다운 리스트 가져오기
  useEffect(() => {
    async function fetchData() {
      await getProductDataWithItemId({
        responseFunc: {
          200: (response) => {
            setRetailNameDropdownOptions(response.data);
            if (response.data[0])
              setSelectedRetailDropdownOption(response.data[0].nameId);
          },
        },
        itemId,
      });
    }
    fetchData();
  }, [itemId]);

  // 도매가 품종 드롭다운 리스트 가져오기
  useEffect(() => {
    async function fetchData() {
      await getProductDataWithItemId({
        responseFunc: {
          200: (response) => {
            setWholeSaleNameDropdownOptions(response.data);
            if (response.data[0])
              setSelectedWholesaleDropdownOption(response.data[0].nameId);
          },
        },
        itemId,
      });
    }
    fetchData();
  }, [itemId]);

  //소매가 가격 데이터 가져오기
  useEffect(() => {
    async function fetchData() {
      await getRetailPriceData({
        responseFunc: {
          200: (response) => {
            setRetailData(response.data);
            if (response.data)
              if (response.data.length > 0)
                setRetailChartOptions({
                  ...retailChartOptions,
                  plugins: {
                    title: {
                      display: true,
                      fontSize: 30, // <--- this is not a managed option since CHART.JS 3
                      text: `단위 : ${
                        response.data[0].retailUnit == "kg" ||
                        response.data[0].retailUnit == "g"
                          ? response.data[0].retailMass.slice(0, -8)
                          : parseInt(response.data[0].retailMass)
                      } ${response.data[0].retailUnit} `,
                    },
                  },
                });
          },
        },
        data: { nameId: selectedRetailDropdownOption, itemId },
      });
    }
    fetchData();
  }, [itemId, selectedRetailDropdownOption]);

  //도매가 가격 데이터 가져오기
  useEffect(() => {
    async function fetchData() {
      await getWholesalePriceData({
        responseFunc: {
          200: (response) => {
            setWholeSaleData(response.data);
            if (response.data)
              if (response.data.length > 0)
                setWholeSaleChartOptions({
                  ...wholesaleChartOptions,
                  plugins: {
                    title: {
                      display: true,
                      fontSize: 30, // <--- this is not a managed option since CHART.JS 3
                      text: `단위 : ${
                        response.data[0].wholesaleUnit == "kg" ||
                        response.data[0].wholesaleUnit == "g"
                          ? response.data[0].wholesaleMass.slice(0, -8)
                          : parseInt(response.data[0].wholesaleMass)
                      } ${response.data[0].wholesaleUnit} `,
                    },
                  },
                });
          },
        },
        data: { nameId: selectedWholesaleDropdownOption, itemId },
      });
    }
    fetchData();
  }, [itemId, selectedWholesaleDropdownOption]);

  const data1 = {
    labels: wholesaleData.map(({ recorded_at }) => recorded_at),
    datasets: [
      {
        label: "도매가",
        data: wholesaleData.map(({ price }) => price),
        fill: false,
        borderColor: "rgba(255, 99, 132, 1)",
      },
    ],
  };

  const data2 = {
    labels: retailData.map(({ recorded_at }) => recorded_at),
    datasets: [
      {
        label: "소매가",
        data: retailData.map(({ price }) => price),
        fill: false,
        borderColor: "rgba(54, 162, 235, 1)",
      },
    ],
  };

  //도매가 그래프 드롭다운 변경 이벤트
  const handleWholesaleDropdownChange = (e) => {
    setSelectedWholesaleDropdownOption(e.target.value);
    // 여기에 선택한 드롭다운 값에 따른 로직 추가
  };

  //소매가 그래프 드롭다운 변경 이벤트

  const handleRetailDropdownChange = (e) => {
    setSelectedRetailDropdownOption(e.target.value);
    // 여기에 선택한 드롭다운 값에 따른 로직 추가
  };

  return (
    <ChartContainer orientation={orientation}>
      <IndividualChartContainer chartHeight={chartHeight}>
        <ChartFlexContainer>
          <DropdownContainer>
            <StyledSelect
              value={selectedWholesaleDropdownOption}
              onChange={handleWholesaleDropdownChange}
            >
              <option value="50" disabled>
                품종
              </option>
              {wholesaleNameDropdownOptions.map((option) => (
                <option value={option.nameId}>{option.name}</option>
              ))}
            </StyledSelect>
          </DropdownContainer>
          {wholesaleData.length > 0 ? (
            <Line data={data1} options={wholesaleChartOptions} />
          ) : (
            <NoDataDiv>최근 가격 데이터가 없습니다.</NoDataDiv>
          )}
        </ChartFlexContainer>
      </IndividualChartContainer>
      <IndividualChartContainer chartHeight={chartHeight}>
        <ChartFlexContainer>
          <DropdownContainer>
            <StyledSelect
              value={selectedRetailDropdownOption}
              onChange={handleRetailDropdownChange}
            >
              <option value="50" disabled>
                품종
              </option>
              {retailNameDropdownOptions.map((option, index) => (
                <option value={option.nameId}>{option.name}</option>
              ))}
            </StyledSelect>
          </DropdownContainer>
          {retailData.length > 0 ? (
            <Line data={data2} options={retailChartOptions} />
          ) : (
            <NoDataDiv>최근 가격 데이터가 없습니다.</NoDataDiv>
          )}
        </ChartFlexContainer>
      </IndividualChartContainer>
    </ChartContainer>
  );
}

export default PriceChart;
