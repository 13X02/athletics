package com.abhijith.usermanagementservice.service;

import com.abhijith.usermanagementservice.dto.CoachRequestDto;
import com.abhijith.usermanagementservice.model.*;
import com.abhijith.usermanagementservice.repository.AssistanceRequestRepository;
import com.abhijith.usermanagementservice.repository.AthleteRepository;
import com.abhijith.usermanagementservice.repository.CoachRepository;
import com.abhijith.usermanagementservice.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CoachService {

    private final CoachRepository coachRepository;
    private final AssistanceRequestRepository assistanceRequestRepository;
    private final AthleteRepository athleteRepository;
    private final AwsService awsService;  // Use the AwsService for AWS interactions

    @Autowired
    public CoachService(CoachRepository coachRepository,
                        AssistanceRequestRepository assistanceRequestRepository,
                        AthleteRepository athleteRepository,
                        AwsService awsService) {
        this.coachRepository = coachRepository;
        this.assistanceRequestRepository = assistanceRequestRepository;
        this.athleteRepository = athleteRepository;
        this.awsService = awsService;
    }

    public Coach createProfile(CoachRequestDto coachRequestDto, String userId, MultipartFile photo) throws IOException {
        String photoUrl = awsService.uploadFile(photo.getInputStream(), photo.getOriginalFilename());
        Coach coach = AppUtil.toCoach(coachRequestDto, userId, photoUrl);
        return coachRepository.save(coach);
    }

    public Coach updateProfile(CoachRequestDto coachRequestDto, String userId, MultipartFile photo) throws IOException {
        Coach coach = coachRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Coach not found with user ID: " + userId));

        if (photo != null && !photo.isEmpty()) {
            String photoUrl = awsService.uploadFile(photo.getInputStream(), photo.getOriginalFilename());
            coach.setPhotoUrl(photoUrl);
        }

        if (coachRequestDto.getCategory() != null) {
            coach.setCategory(coachRequestDto.getCategory());
        }
        if (coachRequestDto.getGender() != null) {
            coach.setGender(coachRequestDto.getGender());
        }
        if (coachRequestDto.getBirthDate() != null) {
            coach.setBirthDate(coachRequestDto.getBirthDate());
        }
        if (coachRequestDto.getFirstName() != null) {
            coach.setFirstName(coachRequestDto.getFirstName());
        }
        if (coachRequestDto.getLastName() != null) {
            coach.setLastName(coachRequestDto.getLastName());
        }

        return coachRepository.save(coach);
    }

    public Coach findById(String coachId) {
        return coachRepository.findById(coachId)
                .orElseThrow(() -> new IllegalArgumentException("Coach not found with ID: " + coachId));
    }

    public Coach setAchievements(Achievments achievements, String userId) {
        Coach coach = coachRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Coach not found with user ID: " + userId));

        coach.getAchievements().add(achievements);
        return coachRepository.save(coach);
    }

    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    public List<Coach> searchByName(String name) {
        return coachRepository.findAllByFirstNameContainingIgnoreCase(name);
    }

    public List<AssistanceRequest> getAssistanceRequests(String userId) {
        Coach coach = coachRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Coach not found with user ID: " + userId));

        return assistanceRequestRepository.findAllByCoachAndStatus(coach, RequestStatus.PENDING);
    }

    public String approveRequest(String reqId) {
        AssistanceRequest request = assistanceRequestRepository.findById(reqId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found with ID: " + reqId));

        request.setStatus(RequestStatus.APPROVED);
        assistanceRequestRepository.save(request);

        Coach coach = request.getCoach();
        Athlete athlete = request.getAthlete();

        coach.getAthletes().add(athlete);
        athlete.setCoach(coach);
        coachRepository.save(coach);
        athleteRepository.save(athlete);

        return "Request approved successfully";
    }

    public String declineRequest(String reqId) {
        AssistanceRequest request = assistanceRequestRepository.findById(reqId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found with ID: " + reqId));

        request.setStatus(RequestStatus.REJECTED);
        assistanceRequestRepository.save(request);

        return "Request declined successfully";
    }
}
