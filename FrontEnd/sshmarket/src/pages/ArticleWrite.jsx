import styled from "styled-components";
import {useState,useEffect} from 'react'
import ImageUpload from "../components/article/ImageUpload";
import MultipleImageUpload from "../components/article/MultipleImageUpload";
import PriceChart from "../components/common/PriceChart"

const commonStyles = {
  border: '1px solid #B388EB',
  borderRadius: '4px',
  backgroundColor: '#f1f1f1',
  hoverBorderColor: '#5F0080'
};

const Container = styled.div`
  width: 1000px;
  margin: 0 auto;
  font-family: 'Arial', sans-serif;
  padding: 20px;
  background-color: #fff;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  border-radius: 15px;
`;


const Title = styled.h2`
  font-size: 24px;
  margin-bottom: 20px;
  border-bottom: 2px solid #B388EB;
  padding-bottom: 10px;
`;


const DropdownContainer = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
`;

const Dropdown = styled.select`
  width: 24%;
  padding: 8px;
  border: ${commonStyles.border};
  border-radius: ${commonStyles.borderRadius};
  background-color: ${commonStyles.backgroundColor};
  font-size: 16px;
  transition: border 0.3s;

  &:hover {
    border-color: ${commonStyles.hoverBorderColor};
    border-width: 2px;
  }
`;

const Section = styled.div`
  margin-bottom: 20px;
  margin-top: 40px; 
`;

const SectionTitle = styled.div`
  font-weight: bold;
  margin-bottom: 10px;
`;

// const ImageBox = styled.div`
//   width: 100px;
//   height: 100px;
//   border: 1px solid #ccc;
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   font-size: 24px;
// `;

const TextArea = styled.textarea`
  width: 100%;
  height: 150px;
  resize: none;
  padding: 10px;

  border: 1px solid #B388EB;
  border-radius: 4px;
  background-color: #f1f1f1;

  &:hover {
    border-color: #5F0080;  
    border-width: 2px;
`;

const Buttons = styled.div`
  display: flex;
  justify-content: space-between;
`;

const Button = styled.button`
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  color: #fff;
  background-color: ${props => props.secondary ? '#e53e3e' : '#5a67d8'};
`;
const InputLabel = styled.label`
  display: block;
  font-weight: bold;
  margin-top: 20px;
  margin-bottom: 8px;
`;

const StyledInput = styled.input`
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
  border: 1px solid #B388EB;
  border-radius: 4px;
  background-color: #f1f1f1;

  &:hover {
    border-color: #5F0080;   
    border-width: 2px;
  }
`;

const InputContainer = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
`;

const InputGroup = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  &:not(:last-child) {
    margin-right: 100px;  
  }
`;

const GroupLabel = styled.label`
  font-weight: bold;
  margin-bottom: 8px;
`;

const GroupInput = styled.input`
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
  border: 1px solid #B388EB;
  border-radius: 4px;
  background-color: #f1f1f1;

  &:hover {
    border-color: #5F0080;    
    border-width: 2px;
  }
`;
const UnitSelect = styled.select`
  width: 30%;  // or 원하는 너비로 설정
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
  margin-right: 10px; // 오른쪽 간격

  border: 1px solid #B388EB;
  border-radius: 4px;
  background-color: #f1f1f1;

  &:hover {
    border-color: #5F0080;    
    border-width: 2px;
  }
`;
const ToggleButton = styled.button`
  margin-top: 30px;
  margin-bottom: 20px;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  background-color: #B388EB;
  color: #fff;
  font-size: 16px;
  border-radius: 4px;
  transition: background-color 0.3s;

  &:hover {
    background-color: #5F0080;
  }
`;

const ImageUploadSection = styled.section`
  border: 2px dashed #B388EB;
  padding: 20px;
  borderRadius: 10px;
  margin-top: 20px;
`;


export default function ArticleWrite() {
  const [selectedOptions, setSelectedOptions] = useState({});
  const [showWeight, setShowWeight] = useState(false); // 토글 상태를 저장하기 위한 상태

  // 드롭다운 항목들
  const [categoryOptions, setCategoryOptions] = useState([]);
  const [transactionOptions, setTransactionOptions] = useState([]);
  const [regionOptions, setRegionOptions] = useState([]);

  //제목
  const [productTitle, setProductTitle] = useState("");

  //중량
  const [productWeight, setProductWeight] = useState("");

  //개수

  const [productAmount, setProductAmount] = useState("");

  //가격
  const [productPrice,setProductPrice] = useState("");

  useEffect(() => {
    // 페이지 로딩 시 옵션 리스트 설정
    const loadOptions = () => {
      setCategoryOptions(['카테고리1', '카테고리2', '카테고리3']);
      setTransactionOptions(['직거래', '택배', '무관']);
      setRegionOptions(['무관', '서울특별시', '부산광역시','대구광역시',
      '인천광역시','광주광역시','대전광역시','울산광역시','세종특별자치시',
      '경기도','강원특별자치도','충청북도','충청남도',
      '전라북도','전라남도','경상북도','경상남도','제주특별자치도'
    ]);
    };


    loadOptions();
  }, []);


  const handleOptionChange = (index, value) => {
    setSelectedOptions(prevState => ({ ...prevState, [index]: value }));
  };

  return (
    <Container>
      <Title>상품 등록</Title>
      <DropdownContainer>
        {[
          { label: '품목', options: categoryOptions },
          { label: '거래방식', options: transactionOptions },
          { label: '지역', options: regionOptions }
        ].map(({ label, options }, index) => (
          <Dropdown 
            key={label}
            value={selectedOptions[index] || ''}
            onChange={(e) => handleOptionChange(index, e.target.value)}
          >
            <option value=""> {label} </option>
            {options.map(option => (
              <option key={option} value={option}>{option}</option>
            ))}
          </Dropdown>
        ))}
      </DropdownContainer>
      <InputLabel htmlFor="productTitle">제목</InputLabel>
      <StyledInput 
        type="text" 
        id="productTitle" 
        placeholder="제품의 제목을 입력하세요." 
        value = {productTitle}
        onChange={(e)=>setProductTitle(e.target.value)}
      />
      <ImageUploadSection>
        <SectionTitle>대표 이미지등록</SectionTitle>
        <ImageUpload />
      </ImageUploadSection>    
       <ImageUploadSection>
        <SectionTitle>게시글 이미지등록</SectionTitle>
        <MultipleImageUpload />
      </ImageUploadSection>
            

      <PriceChart 
        itemId = '111'
        nameId = '01'
      ></PriceChart>
      <ToggleButton onClick={() => setShowWeight(!showWeight)}>
        {showWeight ? '수량 및 가격 입력창 보기' : '무게 및 가격 입력창 보기'}
      </ToggleButton>


      <InputContainer>
        {showWeight ? (
          <>
            <InputGroup>
              <GroupLabel htmlFor="productWeight">무게</GroupLabel>
              <div style={{ display: 'flex', alignItems: 'center' }}>
                <GroupInput type="number" id="productWeight" placeholder="무게를 입력하세요." />
                <UnitSelect>
                  <option value="kg">kg</option>
                  <option value="g">g</option>
                </UnitSelect>
              </div>
            </InputGroup>
            <InputGroup>
              <GroupLabel htmlFor="productPrice">가격</GroupLabel>
              <GroupInput type="number" id="productPrice" placeholder="가격을 입력하세요." />
            </InputGroup>
          </>
        ) : (
          <>
            <InputGroup>
              <GroupLabel htmlFor="productQuantity">개수</GroupLabel>
              <GroupInput type="number" id="productQuantity" placeholder="개수를 입력하세요." />
            </InputGroup>
            <InputGroup>
              <GroupLabel htmlFor="productPrice">가격</GroupLabel>
              <GroupInput type="number" id="productPrice" placeholder="가격을 입력하세요." />
            </InputGroup>
          </>
        )}
      </InputContainer>


      <Section>
        <SectionTitle>세부사항 입력창</SectionTitle>
        <TextArea></TextArea>
      </Section>
      <Buttons>
        <Button>등록하기</Button>
        <Button secondary>취소하기</Button>
      </Buttons>
    </Container>
  );

}