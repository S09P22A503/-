import styled from "styled-components";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import {useState,useEffect} from 'react'
import ImageUpload from "../components/article/ImageUpload";
import MultipleImageUpload from "../components/article/MultipleImageUpload";
import PriceChart from "../components/common/PriceChart"
import { getProductData } from "../api/product";
import { writeArticle } from "../api/articlewrite";

const commonStyles = {
  border: '1px solid #B388EB',
  borderRadius: '4px',
  backgroundColor: '#f1f1f1',
  hoverBorderColor: '#5F0080'
};

const Container = styled.div`
  width: 1000px;
  margin: 0 auto;
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

  //멤버 가져오기
  const member = useSelector((state) => state.MemberReducer)

  // 네비게이트 객체
  const navigate = useNavigate()

  // if(!member.id){
  //   alert("로그인을 해주세요!")
  //   navigate('/')
  // }

  const [showWeight, setShowWeight] = useState(false); // 토글 상태를 저장하기 위한 상태

  // 가져올 상품 리스트들
  const [wholeProductList, setWholeProductList] = useState([]);


  // 처음 렌더링할때 상품 가져오기
  useEffect(() => {
    async function fetchData() {
      await getProductData({
        responseFunc: {
          200: (response) => {
            setWholeProductList(response.data)
            console.log(wholeProductList)
          },
        }
      });
    }
    fetchData()
  }, []);

  //
  
  // 상품 카테고리 항목들
  const categoryOptions = [{key:'1',category:"식량작물"},{key:'2',category:"채소류"},{key:'3',category:"특용작물"},{key:'4',category:"과일류"},{key:'6',category:"수산물"}]

  // 거래 카테고리 항목들
  const transactionOptions = [{label:"직거래",value:"DIRECT"},{label:"택배",value:"PARCEL"},{label:"무관",value:"NONE"}]

  // 지역 카테고리 항목들
  const firstRegionOptions = ['무관','서울특별시', '부산광역시','대구광역시',
                        '인천광역시','광주광역시','대전광역시','울산광역시',
                        '경기도','강원특별자치도','충청북도','충청남도',
                        '전라북도','전라남도','경상북도','경상남도','제주특별자치도','세종특별자치시',
                        ]
  
  const regionOptions = firstRegionOptions.map((region,index)=>{
    return {locationId : index,locationName:region}
  })

  // 상품 항목들 (선택한 상품 카테고리 항목에 따라 동적으로 바뀌어야함)
  const [productOptions,setProductOptions] = useState([]);

  // 선택한 상품 카테고리 항목
  const [selectedCategoryOption, setSelectedCategoryOption] = useState('');

  // 선택한 거래 카테고리 항목
  const [selectedTransactionOption, setSelectedTransactionOption] = useState("");

  // 선택한 지역 카테고리 항목
  const [selectedRegionOption, setSelectedRegionOption] = useState(0);

  // 선택한 상품 항목
  const [selectedProductOption, setSelectedProductOption] = useState("999");
  
  //제목
  const [productTitle, setProductTitle] = useState("");

  //중량
  const [productWeight, setProductWeight] = useState("");

  //중량 단위
  const [productWeightUnit,setProductWeightUnit] = useState("");
  
  //개수

  const [productAmount, setProductAmount] = useState("");

  // 가격
  const [productPrice,setProductPrice] = useState("");

  // 내용
  const [productContent,setProductContent] = useState("");

  // 게시판 이미지들
  const [uploadedImages, setUploadedImages] = useState([]);

  // 대표 이미지
  const [profileImage,setProfileImage] = useState(null);

  //카테고리 옵션 변경 콜백
  const handleCategoryOptionChange = (value) => {
    setSelectedCategoryOption(value)
  };

  //카테고리 옵션 변경 시 품목 리스트를 변경해야함
  useEffect(() => {
    setProductOptions(
      wholeProductList.filter(
        item=>item.itemId.toString().startsWith(selectedCategoryOption)
      )
    )
    setSelectedProductOption("999")
  }, [selectedCategoryOption]);

  const handleProductOptionChange = (value) => {
    setSelectedProductOption(value)
  };

  // 거래 방식 변경시
  const handleTransactionOptionChange = (value) =>{
    setSelectedTransactionOption(value)
  }

  // 거래 지역 변경시
  const handleRegionOptionChange = (value) =>{
    setSelectedRegionOption(value)
    console.log(value)
  }

  // 등록 버튼 눌렀을시
  const onSubmit = ()=>{
    async function fetchData() {
      await writeArticle({
        responseFunc: {
          201: (response) => {
            console.log(response)
          },
        },
        data:{
          memberId : member.memberId,
          productId : selectedProductOption,
          price : productPrice,
          amount : productAmount,
          mass : productWeight,
          locationId : selectedRegionOption,
          title : productTitle,
          content: productContent,
          tradeType : selectedTransactionOption,
          mainImage: profileImage,
          images : uploadedImages
        }
    });
  }
  fetchData();
}

  return (
    <Container>
      <Title>상품 등록</Title>
      <DropdownContainer>
          <Dropdown 
            value={selectedCategoryOption}
            onChange={(e) => handleCategoryOptionChange(e.target.value)}
          >
            <option value='' disabled> 카테고리 </option>
            {categoryOptions.map(categoryOption => (
              <option value={categoryOption.key}>{categoryOption.category}</option>
            ))}
          </Dropdown>

          <Dropdown 
            value={selectedProductOption}
            onChange={(e) => handleProductOptionChange(e.target.value)}
          >
            <option value="999" disabled> 상품 </option>
            {productOptions.map(productOption => (
              <option value={productOption.itemId}>{productOption.itemName}</option>
            ))}
          </Dropdown>

          <Dropdown 
            value={selectedTransactionOption}
            onChange={(e) => handleTransactionOptionChange(e.target.value)}
          >
            <option value="999" disabled> 거래 방식 </option>
            {transactionOptions.map(transactionOption => (
              <option value={transactionOption.value}>{transactionOption.label}</option>
            ))}
          </Dropdown>

          <Dropdown 
            value={selectedRegionOption}
            onChange={(e) => handleRegionOptionChange(e.target.value)}
          >
            <option value="999" disabled> 거래 지역 </option>
            {regionOptions.map(regionOption => (
              <option value={regionOption.locationId}>{regionOption.locationName}</option>
            ))}
          </Dropdown>

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
        <ImageUpload image={profileImage} setImage={setProfileImage}/>
      </ImageUploadSection>    
       <ImageUploadSection>
        <SectionTitle>게시글 이미지등록</SectionTitle>
        <MultipleImageUpload images={uploadedImages} setImages={setUploadedImages}/>
      </ImageUploadSection>
            

      <PriceChart 
        itemId = {selectedProductOption}
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
                <GroupInput type="number" id="productWeight" value={productWeight} onChange={(e)=>setProductWeight(e.target.value)} placeholder="무게를 입력하세요." />
                <UnitSelect id="productWeightUnit" value={productWeightUnit} onChange={(e)=>setProductWeightUnit(e.target.value)} >
                  <option value="kg">kg</option>
                  <option value="g">g</option>
                </UnitSelect>
              </div>
            </InputGroup>
            <InputGroup>
              <GroupLabel htmlFor="productPrice">가격</GroupLabel>
              <GroupInput type="number" id="productPrice" value={productPrice} onChange={(e)=>setProductPrice(e.target.value)} placeholder="가격을 입력하세요." />
            </InputGroup>
          </>
        ) : (
          <>
            <InputGroup>
              <GroupLabel htmlFor="productQuantity">개수</GroupLabel>
              <GroupInput type="number" id="productQuantity" value={productAmount} onChange={(e)=>setProductAmount(e.target.value)} placeholder="개수를 입력하세요." />
            </InputGroup>
            <InputGroup>
              <GroupLabel htmlFor="productPrice">가격</GroupLabel>
              <GroupInput type="number" id="productPrice" value={productPrice} onChange={(e)=>setProductPrice(e.target.value)} placeholder="가격을 입력하세요." />
            </InputGroup>
          </>
        )}
      </InputContainer>


      <Section>
        <SectionTitle>세부사항 입력창</SectionTitle>
        <TextArea id="productContent" value={productContent} onChange={(e)=>setProductContent(e.target.value)} ></TextArea>
      </Section>
      <Buttons>
        <Button onClick={onSubmit}>등록하기</Button>
        <Button secondary>취소하기</Button>
      </Buttons>
    </Container>
  );
}