package com.abhijith.eventservice.service;

import com.abhijith.eventservice.dto.MeetRequestDto;
import com.abhijith.eventservice.model.Meet;
import com.abhijith.eventservice.repo.MeetRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetService {

    @Autowired
    private MeetRepositoy meetRepository;

    public Meet createMeet(MeetRequestDto meetDto){
        Meet meet = new Meet(null,meetDto.getMeetName(),meetDto.getPhotoUrl());
        return meetRepository.save(meet);
    }

    public List<Meet> getMeetList(){
        return meetRepository.findAll();
    }



}
