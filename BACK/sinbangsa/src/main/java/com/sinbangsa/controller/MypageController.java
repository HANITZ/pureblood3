package com.sinbangsa.controller;


import com.sinbangsa.data.dto.*;
import com.sinbangsa.service.MypageService;
import com.sinbangsa.utils.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
@Api(value = "마이페이지 API", tags = "마이페이지 API")
public class MypageController {

    private final Logger LOGGER = LoggerFactory.getLogger(MypageController.class);

    private final MypageService mypageService;

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    @ApiOperation(value = "정보 받아오기")
    public ResponseEntity<MypageInfoDto> getMypageInfo(HttpServletRequest httpServletRequest) {
        LOGGER.info("[MypageController] getMyPageInfo 호출");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        try {
            MypageInfoDto result = mypageService.getMyPageInfo(httpServletRequest);
            return new ResponseEntity<MypageInfoDto>(result, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }


    }


    @GetMapping("/likes")
    @ApiOperation(value = "찜 목록")
    public ResponseEntity<List<MypageLikeDto>> getLikes(@RequestParam int page, HttpServletRequest httpServletRequest) {
        LOGGER.info("[MypageController] getLikes 호출");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        try {
            List<MypageLikeDto> result = mypageService.getLikes(page,httpServletRequest);
            return new ResponseEntity<List<MypageLikeDto>>(result, headers, HttpStatus.OK);
        } catch (Exception e) {
            List<MypageLikeDto> result = new ArrayList<>();
            return new ResponseEntity<List<MypageLikeDto>>(result, headers, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/reviews")
    @ApiOperation(value = "리뷰 목록")
    public ResponseEntity<List<MypageReviewDto>> getReviews(@RequestParam int page, HttpServletRequest httpServletRequest) {
        LOGGER.info("[MypageController] getReviews 호출");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        try {
            List<MypageReviewDto> result = mypageService.getReviews(page, httpServletRequest);
            return new ResponseEntity<List<MypageReviewDto>>(result, headers, HttpStatus.OK);
        } catch (Exception e) {
            List<MypageReviewDto> result = new ArrayList<>();
            return new ResponseEntity<List<MypageReviewDto>>(result, headers, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/acts")
    @ApiOperation(value = "활동 내역 목록")
    public ResponseEntity<MypageMyRoomDto> getMyRooms(HttpServletRequest httpServletRequest) {
        LOGGER.info("[MypageController] getMyRooms 호출");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        try {
            MypageMyRoomDto result = mypageService.getMypageMyRooms(httpServletRequest);
            return new ResponseEntity<MypageMyRoomDto>(result, headers, HttpStatus.OK);
        } catch (Exception e) {
            MypageMyRoomDto result = new MypageMyRoomDto();
            return new ResponseEntity<MypageMyRoomDto>(result, headers, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update")
    @ApiOperation(value = "개인 정보 수정")
    public ResponseEntity updateUserInfo(@RequestBody UpdateUserInfoRequestDto updateUserInfoRequestDto, HttpServletRequest httpServletRequest) {
        LOGGER.info("[MypageController] updateUserInfo 호출");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        try {
            mypageService.updateUserInfo(updateUserInfoRequestDto, httpServletRequest);
            return new ResponseEntity(headers, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/{reservationId}")
    @ApiOperation(value = "예약 상세")
    public ResponseEntity<ReservationDetailDto> getReservationDetail(@PathVariable Long reservationId, HttpServletRequest httpServletRequest) {
        LOGGER.info("[MypageController] getReservationDetail 호출");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        try {
            ReservationDetailDto result = mypageService.getReservationDetail(reservationId, httpServletRequest);
            return new ResponseEntity<ReservationDetailDto>(result, headers, HttpStatus.ACCEPTED);
        } catch (Exception e) {

            return new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{reservationId}")
    @ApiOperation(value = "양도 올리기")
    public ResponseEntity<Boolean>doTransfer(@PathVariable Long reservationId, HttpServletRequest httpServletRequest) {
        LOGGER.info("[MypageController] getReservationDetail 호출");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        try {
            boolean result = mypageService.doTransfer(reservationId, httpServletRequest);
            return new ResponseEntity(result, headers, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            boolean result = false;
            return new ResponseEntity(result, headers, HttpStatus.BAD_REQUEST);
        }

    }
}
