package com.sinbangsa.data.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MypageMyRoomDto {


    private List<ReservationDto> reservations;

    private List<PlayedRoomDto> books;

    @Getter
    @Setter
    public static class ReservationDto {

        private long reservationId;

        private String themeName;

        private String storeName;

        private String Date;

        private String reservatedTime;

    }

    @Getter
    @Setter
    public static class PlayedRoomDto {

        private long bookId;

        private String themeName;

        private String storeName;

        private int isClear;

        private boolean isReview;

        private LocalDate doneDate;

        private int usedHint;

        private String clearTime;

    }




}
