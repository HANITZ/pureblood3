package com.sinbangsa.service;


import com.sinbangsa.data.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MypageService {

    MypageInfoDto getMyPageInfo(HttpServletRequest httpServletRequest);

    List<MypageLikeDto> getLikes(int page, HttpServletRequest httpServletRequest);

    List<MypageReviewDto> getReviews(int page, HttpServletRequest httpServletRequest);

    MypageMyRoomDto getMypageMyRooms(HttpServletRequest httpServletRequest);

    void updateUserInfo(UpdateUserInfoRequestDto updateUserInfoRequestDto, HttpServletRequest httpServletRequest);

    ReservationDetailDto getReservationDetail(Long reservationId, HttpServletRequest httpServletRequest);

    boolean doTransfer(Long reservationId, HttpServletRequest httpServletRequest);

}
