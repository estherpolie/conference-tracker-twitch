package com.manning.sbip.twitch.conference_tracker_twitch.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manning.sbip.twitch.conference_tracker_twitch.model.Conference;
import com.manning.sbip.twitch.conference_tracker_twitch.repository.ConferenceRepository;

@Service
public class ConferenceServiceImpl implements ConferenceService {

	private ConferenceRepository conferencerepository;

	@Autowired
	public ConferenceServiceImpl(ConferenceRepository conferencerepository) {
		this.conferencerepository = conferencerepository;
	}

	@Override
	public Conference createConference(Conference conference) {
		// TODO Auto-generated method stub
		return conferencerepository.save(conference);
	}

	@Override
	public void updateConference(long id, Conference conference) {
	Optional<Conference> optionalConference = conferencerepository.findById(id);
	if(optionalConference.isPresent()) {
Conference dbConference = optionalConference.get();
dbConference.setName(conference.getName());
dbConference.setLocation(conference.getLocation());
dbConference.setDateTime(conference.getDateTime());
dbConference.setSpeaker(conference.getSpeaker());
dbConference.setStatus(conference.isStatus());
conferencerepository.save(conference);
	}
	}

	@Override
	public Iterable<Conference> getConferences() {
		// TODO Auto-generated method stub
		return     conferencerepository.findAll();
	}

	@Override
	public Optional<Conference> getConferenceById(Long id) {
		// TODO Auto-generated method stub
		return conferencerepository.findById(id);
	}

	@Override
	public void deleteConference(long id) {
       conferencerepository.deleteById(id);
	}

}
