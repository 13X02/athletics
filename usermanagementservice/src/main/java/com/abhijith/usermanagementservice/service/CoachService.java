package com.abhijith.usermanagementservice.service;

import com.abhijith.usermanagementservice.client.Users;
import com.abhijith.usermanagementservice.dto.CoachRequestDto;
import com.abhijith.usermanagementservice.model.*;
import com.abhijith.usermanagementservice.repository.AssistanceRequestRepository;
import com.abhijith.usermanagementservice.repository.AthleteRepository;
import com.abhijith.usermanagementservice.repository.CoachRepository;
import com.abhijith.usermanagementservice.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private AssistanceRequestRepository assistanceRequestRepository;

    @Autowired
    private AthleteRepository athleteRepository;

//    public Coach createProfile(CoachRequestDto coachRequestDto){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Users user = (Users) authentication.getPrincipal();
//        Coach coach = AppUtil.toCoach(coachRequestDto,user.getUserId());
//        return coachRepository.save(coach);
//    }
//
//    public Coach findById(String coachId){
//        return coachRepository.findById(coachId).orElse(null);
//    }
//
//    public Coach updateProfile(CoachRequestDto coachRequestDto){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Users user = (Users) authentication.getPrincipal();
//        Optional<Coach> coach = coachRepository.findByUserId(user.getUserId());
//        if(coach.isPresent()){
//            coach.get().setCategory(coachRequestDto.getCategory());
//            coach.get().setGender(coachRequestDto.getGender());
//            coach.get().setBirthDate(coachRequestDto.getBirthDate());
//            coach.get().setFirstName(coachRequestDto.getFirstName());
//            coach.get().setLastName(coachRequestDto.getLastName());
//            return coachRepository.save(coach.get());
//        }
//        return null;
//    }
//
//    public Coach setAchievements(Achievments achievements){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Users user = (Users) authentication.getPrincipal();
//        Optional<Coach> coach = coachRepository.findByUserId(user.getUserId());
//        if(coach.isPresent()){
//            coach.get().getAchievements().add(achievements);
//            return coachRepository.save(coach.get());
//        }else{
//            return null;
//        }
//    }
//
//    public List<Coach> findAll(){
//        return coachRepository.findAll();
//    }
//    public List<Coach> searchByName(String name){
//        return coachRepository.findAllByFirstNameContainingIgnoreCase(name);
//    }
//    public List<AssistanceRequest> getAssistanceRequest(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Users user = (Users) authentication.getPrincipal();
//        Optional<Coach> coach = coachRepository.findByUserId(user.getUserId());
//        if (coach.isPresent()){
//            return assistanceRequestRepository.findAllByCoachAndStatus(coach.get(), RequestStatus.PENDING);
//
//        }
//        return  null;
//
//    }
//
//    public String approveRequest(String reqId){
//        Optional<AssistanceRequest> request = assistanceRequestRepository.findById(reqId);
//        if(request.isPresent()){
//            request.get().setStatus(RequestStatus.APPROVED);
//            assistanceRequestRepository.save(request.get());
//            Coach coach = request.get().getCoach();
//
//            Athlete athlete = request.get().getAthlete();
//
//            coach.getAthletes().add(athlete);
//            athlete.setCoach(coach);
//            coachRepository.save(coach);
//            athleteRepository.save(athlete);
//
//            return "Request approved successfully";
//        }
//        return "Request not found";
//    }
//    public String declineRequest(String reqId){
//        Optional<AssistanceRequest> request = assistanceRequestRepository.findById(reqId);
//        if(request.isPresent()){
//            request.get().setStatus(RequestStatus.REJECTED);
//            assistanceRequestRepository.save(request.get());
//            return "Request declined successfully";
//        }
//        return "Request not found";
//    }

}
