package com.abhijith.usermanagementservice.service;

//import com.abhijith.usermanagementservice.dto.AssistanceRequestDto;
//import com.abhijith.usermanagementservice.dto.AthleteRequestDto;
//import com.abhijith.usermanagementservice.model.AssistanceRequest;
//import com.abhijith.usermanagementservice.model.Athlete;
//import com.abhijith.usermanagementservice.model.Coach;
//import com.abhijith.usermanagementservice.client.Users;
//import com.abhijith.usermanagementservice.repository.AssistanceRequestRepository;
//import com.abhijith.usermanagementservice.repository.AthleteRepository;
//import com.abhijith.usermanagementservice.repository.CoachRepository;
//import com.abhijith.usermanagementservice.util.AppUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

//    @Autowired
//    private AthleteRepository athleteRepository;
//
//    @Autowired
//    private CoachRepository coachRepository;
//
//    @Autowired
//    private AssistanceRequestRepository assitanceReqestRepository;
//
//
//    public Athlete createProfile(AthleteRequestDto athleteRequestDto){
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Users user = (Users) authentication.getPrincipal();
//        Athlete athlete = AppUtil.toAthlete(athleteRequestDto,user.getUserId());
//        return athleteRepository.save(athlete);
//    }
//    public Athlete getAthlete(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Users user = (Users) authentication.getPrincipal();
//        Optional<Athlete> athlete = athleteRepository.findByUserId(user.getUserId());
//        if (athlete.isPresent()){
//            return athlete.get();
//        }
//        return null;
//    }
//    public Athlete getAthleteById(String id){
//        return athleteRepository.findById(id).orElse(null);
//    }
//    public List<Athlete> getAll(){
//        return athleteRepository.findAll();
//    }
//    public Athlete editAthlete(AthleteRequestDto athleteRequestDto){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Users user = (Users) authentication.getPrincipal();
//        Athlete athlete = AppUtil.toAthlete(athleteRequestDto,user.getUserId());
//        return athleteRepository.save(athlete);
//    }
//
//    public AssistanceRequest requestAssistance(AssistanceRequestDto assistanceRequestDto){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Users user = (Users) authentication.getPrincipal();
//        Optional<Athlete> athlete = athleteRepository.findByUserId(user.getUserId());
//        if(athlete.isPresent()){
//            Optional<Coach> coach = coachRepository.findById(assistanceRequestDto.getCoachId());
//            if (coach.isPresent()){
//                AssistanceRequest assistanceRequest = AppUtil.toAssistanceRequest(assistanceRequestDto,coach.get(),athlete.get());
//                return assitanceReqestRepository.save(assistanceRequest);
//            }
//            return null;
//        }
//        return null;
//
//    }

}
