import { SecureState } from "../store/SecureStore";

const BASE_URL = "http://k7c104.p.ssafy.io:8080/api";


// 참고
// return await fetch(`${BASE_URL}/mainpage/search?${query}`,{
//   headers : {
//        Authorization : //토큰 추가
//   },
// })
// .then((res) => {
//   console.log(res.json());
//   res.json()
// })

// 메인
export const getPreloading = async () => {
  // /mainpage
  return await (
    await fetch(`${BASE_URL}/mainpage`, {
      headers: {
        Authorization: await SecureState.getData("accessToken"),
      },
    })
  ).json();
};

// 검색
const getSearch = async ({ queryKey }) => {
  let [_, query] = queryKey;
  query = new URLSearchParams({
    searchWord: query,
  });
  const response = await (
    await fetch(`${BASE_URL}/mainpage/search?${query}`, {
      headers: {
        Authorization: await SecureState.getData("accessToken"),
      },
    })
  ).json();
  return response;
};

const getCafeDetail = async ({ queryKey }) => {
  let [_, storeId] = queryKey;
  return await (
    await fetch(`${BASE_URL}/book/store/${storeId}`, {
      headers: {
        Authorization: await SecureState.getData("accessToken"),
      },
    })
  ).json();
};

const getThemeDetail = async ({ queryKey }) => {
  let [_, themeId] = queryKey;
  return await (
    await fetch(`${BASE_URL}/book/theme/${themeId}`, {
      headers: {
        Authorization: await SecureState.getData("accessToken"),
      },
    })
  ).json();
};

export const searchApi = { getSearch, getCafeDetail, getThemeDetail };

// 예약
const getMypageActs = () => {
  // api/mypage/acts
  return fetch(`${BASE_URL}/mocksTheme/db`).then((res) => res.json());
};

//예약 상세
const getReservationDetail = (reservationId) => {
  // /api/mypage/{reservationId}
  return fetch(`${BASE_URL}/mocksTheme/db`).then((res) => res.json());
};

//테마 예약 가능 시간
const getReservationTime = async ({ queryKey }) => {
  let [_, themeId] = queryKey;
  themeId = new URLSearchParams({
    themeId: themeId,
  });
  return await (
    await fetch(`${BASE_URL}/reservation?${themeId}`, {
      headers: {
        Authorization: await SecureState.getData("accessToken"),
      },
    })
  ).json();
};

//날짜별 예약 현황
const getReservationDate = async ({ queryKey }) => {
  let [_, themeId, date] = queryKey;
  themeId = new URLSearchParams({
    themeId: themeId,
    date: date,
  });
  return await (
    await fetch(`${BASE_URL}/reservation/date?${themeId}`, {
      headers: {
        Authorization: await SecureState.getData("accessToken"),
      },
    })
  ).json();
};

//예약하기
const postReservation = async ({ queryKey }) => {
  // api/reservation
  const {themeId, selectTime, date} = queryKey[1];
  const data = {
    reservationDate: date,
    themeId: themeId,
    themeTimeId: selectTime,
  }
  console.log(`${BASE_URL}/reservation`);
  const res = 
    await fetch(`${BASE_URL}/reservation`, {
      method: 'POST',
      headers: {
        Authorization: await SecureState.getData("accessToken"),
      },
      body: JSON.stringify(data)
    })
  
  console.log(res);
  return res
};

// 닉네임 유효성 검사
const getSearchUser = () => {
  // api/reservation
  fetch(`${BASE_URL}/mocks/db`);
};

// 양도
const putReservationTransfer = (reservationId, targetId) => {
  // api/reservation/transfer
  fetch(`${BASE_URL}/mocks/db`);
};

export const reservationApi = {
  getMypageActs,
  getReservationDetail,
  getReservationTime,
  getReservationDate,
  postReservation,
  getSearchUser,
  putReservationTransfer,
};

// 커뮤니티
const getCommunityList = (head, page) => {
  // api/community
  return fetch(`${BASE_URL2}/communityList/db`).then((res) => res.json());
};
const getCommunitySearch = (searchWord) => {
  // api/community/search
  return fetch(`${BASE_URL2}/communitySearch/db`).then((res) => res.json());
};
const getCommunityDetail = (articleId) => {
  // api/community/{articleId}
  return fetch(`${BASE_URL2}/communityDetail/db`).then((res) => res.json());
};

export const communityApi = {
  getCommunityList,
  getCommunitySearch,
  getCommunityDetail,
};
