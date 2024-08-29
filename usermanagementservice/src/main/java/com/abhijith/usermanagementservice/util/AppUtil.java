package com.abhijith.usermanagementservice.util;

import com.abhijith.usermanagementservice.client.Users;
import com.abhijith.usermanagementservice.dto.AssistanceRequestDto;
import com.abhijith.usermanagementservice.dto.AthleteRequestDto;
import com.abhijith.usermanagementservice.dto.CoachRequestDto;
import com.abhijith.usermanagementservice.model.*;

import java.util.ArrayList;

public class AppUtil {

    public static Athlete toAthlete(AthleteRequestDto athleteRequestDto,String userId) {
        if (athleteRequestDto == null) {
            return null;
        }
        return new Athlete(
                null,
                userId,
                athleteRequestDto.getFirstName(),
                athleteRequestDto.getLastName(),
                athleteRequestDto.getBirthDate(),
                athleteRequestDto.getGender(),
                athleteRequestDto.getHeight(),
                athleteRequestDto.getWeight(),
                athleteRequestDto.getCategory(),
                null
        );

    }

    public static AssistanceRequest toAssistanceRequest(AssistanceRequestDto assistanceRequestDto, Coach coach, Athlete athlete) {
        if (assistanceRequestDto == null) {
            return null;
        }
        return new AssistanceRequest(
                null,
                athlete,
                RequestStatus.PENDING,
                assistanceRequestDto.getRemarks(),
                coach

        );
    }

    public static Coach toCoach(CoachRequestDto coachRequestDto,String userId){

        if (coachRequestDto == null) {
            return null;
        }
        return  new Coach(
                null,
                userId,
                coachRequestDto.getFirstName(),
                coachRequestDto.getLastName(),
                coachRequestDto.getBirthDate(),
                coachRequestDto.getGender(),
                coachRequestDto.getCategory(),
                new ArrayList<>(),
                new ArrayList<>()


        );
    }
}