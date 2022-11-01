import React, {useState} from "react";
import { Text, View } from "react-native";
import styled from "styled-components/native";

import LoadingScreen from "./LoadingScreen";
import { useQuery } from "@tanstack/react-query";
import { searchApi } from "../apis/api";

function CafeDetailScreen({ navigation: { navigate } }) {
  const [query, setQuery] = useState("");
  const { isLoading, isFetching, data, refetch } = useQuery(
    ["CafeDetail", query], //토큰 추가
    searchApi.getCafeDetail);
    return (
      isLoading ? <LoadingScreen/> : 
      <MainContainer>
        <Container>
          {/* themeImg 넣기 */}
          <CafeImage />
          <TextContainer>
            <Title>{data.store.storeName}</Title>
            <SubTitle>{data.store.Address}</SubTitle>
            <SubTitle>{data.store.mapX}</SubTitle>
            <SubTitle>{data.store.mapY}</SubTitle>
            
              <SubTitle>{data.store.tel}분 | </SubTitle>
              <SubTitle>난이도 {data.store.storeImg} | </SubTitle>
              <SubTitle>{data.store.homepage} </SubTitle>
            
            <SubTitle>{data.store.region}</SubTitle>
            <SubTitle>{data.store.isClear ? <Text>Clear</Text> : <Text>Fail</Text> }</SubTitle>
          </TextContainer>
        </Container>
        <Title>테마 종류</Title>
        <View>
          {data.store.themeList.map((item) => {
            return (
              <TmpContainer key={item.themeId}>
                <Text>{item.themeName}</Text>
                <Text>{item.themeImg}</Text>
                <Text>{item.likeCount}</Text>
                <Text>{item.star}</Text>
              </TmpContainer>
            )})}
        </View>
        {/* store LikeButton 추가 */}
        <ButtonContainer />
      </MainContainer>
    );
  }
    
  const MainContainer = styled.ScrollView`
    
  `
  const Container = styled.View`
    flex-direction: row;
    margin-bottom: 16px;
    align-items: center;
  `;
  
  const TextContainer = styled.View`
    margin-left: 16px;
  `;
  const RowContainer = styled.View`
    flex-direction: row;
  `;
  
  // 추후 이미지 태그로 대체
  const CafeImage = styled.View`
    width: 100px;
    height: 160px;
    background-color: gray;
  `;
  const Title = styled.Text`
    font-family: "SUIT-SemiBold";
    font-size: ${({ theme }) => theme.fontSizes.body};
    color: #fff;
    margin-bottom: 8px;
  `;
  const SubTitle = styled.Text`
    font-family: "SUIT-Bold";
    font-size: ${({ theme }) => theme.fontSizes.caption1};
    color: #fff;
  `;
  
  const ButtonContainer = styled.TouchableOpacity`
    width: 256px;
    height: 64px;
    border-radius: 40px;
    background-color: #f9dc87;
  `;
  
  const TmpContainer = styled.View`
    background-color: #fff;
  `

export default CafeDetailScreen;