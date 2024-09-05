package com.abhijith.eventservice.service;

import com.abhijith.eventservice.dto.MeetRequestDto;
import com.abhijith.eventservice.model.Meet;
import com.abhijith.eventservice.repo.MeetRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MeetService {

    @Autowired
    private MeetRepositoy meetRepository;

    @Autowired
    private AwsService awsService;

    public Meet createMeet(MeetRequestDto meetRequestDto, MultipartFile photo) throws IOException {
        // Upload the photo to AWS S3
        String photoUrl = awsService.uploadFile(photo.getInputStream(), photo.getOriginalFilename());

        // Create the Meet entity with the photo URL
        Meet meet = new Meet();
        meet.setMeetName(meetRequestDto.getMeetName());
        meet.setPhotoUrl(photoUrl);  // Set the photo URL

        // Save and return the meet
        return meetRepository.save(meet);
    }

    public List<Meet> getMeetList(){
        return meetRepository.findAll();
    }



}
