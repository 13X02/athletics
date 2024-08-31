package com.abhijith.usermanagementservice.service;

import com.abhijith.usermanagementservice.dto.AssistanceRequestDto;
import com.abhijith.usermanagementservice.dto.AthleteRequestDto;
import com.abhijith.usermanagementservice.exception.AthleteNotFoundException;
import com.abhijith.usermanagementservice.model.AssistanceRequest;
import com.abhijith.usermanagementservice.model.Athlete;
import com.abhijith.usermanagementservice.model.Coach;
import com.abhijith.usermanagementservice.repository.AssistanceRequestRepository;
import com.abhijith.usermanagementservice.repository.AthleteRepository;
import com.abhijith.usermanagementservice.repository.CoachRepository;
import com.abhijith.usermanagementservice.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private AssistanceRequestRepository assitanceReqestRepository;


    @Autowired
    private AwsService awsService;

    public Athlete createProfile(AthleteRequestDto athleteRequestDto , String userId, MultipartFile photo) throws IOException {

        String url = awsService.uploadFile(photo.getInputStream(), photo.getOriginalFilename());
        Athlete athlete = AppUtil.toAthlete(athleteRequestDto,userId,url);
        return athleteRepository.save(athlete);
    }
    public Athlete getAthlete(String userId){

        Optional<Athlete> athlete = athleteRepository.findByUserId(userId);
        if (athlete.isPresent()){
            return athlete.get();
        }
        return null;
    }
    public Athlete getAthleteById(String id){
        return athleteRepository.findById(id).orElse(null);
    }
    public List<Athlete> getAll(){
        return athleteRepository.findAll();
    }
    public Athlete editAthlete(AthleteRequestDto athleteRequestDto, String userId, MultipartFile photo) throws IOException {
        String url = null;

        if (photo != null) {
            url = awsService.uploadFile(photo.getInputStream(), photo.getOriginalFilename());
        }

        Optional<Athlete> optionalAthlete = athleteRepository.findByUserId(userId);
        Athlete athlete;

        if (optionalAthlete.isPresent()) {
            athlete = optionalAthlete.get();

            // Only update fields that are not null
            if (athleteRequestDto.getFirstName() != null) {
                athlete.setFirstName(athleteRequestDto.getFirstName());
            }
            if (athleteRequestDto.getLastName() != null) {
                athlete.setLastName(athleteRequestDto.getLastName());
            }
            if (athleteRequestDto.getBirthDate() != null) {
                athlete.setBirthDate(athleteRequestDto.getBirthDate());
            }
            if (athleteRequestDto.getGender() != null) {
                athlete.setGender(athleteRequestDto.getGender());
            }
            if (athleteRequestDto.getHeight() != null) {
                athlete.setHeight(athleteRequestDto.getHeight());
            }
            if (athleteRequestDto.getWeight() != null) {
                athlete.setWeight(athleteRequestDto.getWeight());
            }
            if (athleteRequestDto.getCategory() != null) {
                athlete.setCategory(athleteRequestDto.getCategory());
            }

            if (url != null) {
                athlete.setPhotoUrl(url);
            }

            return athleteRepository.save(athlete);

        } else {
            // Create a new Athlete if not found
            throw  new AthleteNotFoundException("Athlete not found");

        }


    }


    public AssistanceRequest requestAssistance(AssistanceRequestDto assistanceRequestDto,String userId){

        Optional<Athlete> athlete = athleteRepository.findByUserId(userId);
        if(athlete.isPresent()){
            Optional<Coach> coach = coachRepository.findById(assistanceRequestDto.getCoachId());
            if (coach.isPresent()){
                AssistanceRequest assistanceRequest = AppUtil.toAssistanceRequest(assistanceRequestDto,coach.get(),athlete.get());
                return assitanceReqestRepository.save(assistanceRequest);
            }
            return null;
        }
        return null;

    }


    public Boolean validateAthlete(String id) {

        return athleteRepository.existsById(id);
    }

    public String findAthleteByUserId(String userId) {
        if(athleteRepository.existsByUserId(userId)){
            return athleteRepository.findByUserId(userId).get().getAthleteId();
        }
        return null;
    }
}
