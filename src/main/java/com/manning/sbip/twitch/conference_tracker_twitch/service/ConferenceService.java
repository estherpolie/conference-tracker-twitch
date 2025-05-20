package com.manning.sbip.twitch.conference_tracker_twitch.service;

import java.util.Optional;


import com.manning.sbip.twitch.conference_tracker_twitch.model.Conference;

public interface ConferenceService {

	
	Conference createConference(Conference conference);
	
	void updateConference(long id, Conference conference);
	
	Iterable<Conference> getConferences();
	
	Optional<Conference> getConferenceById(Long id);
	
	void deleteConference(long id);
}
