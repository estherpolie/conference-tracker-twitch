package com.manning.sbip.twitch.conference_tracker_twitch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manning.sbip.twitch.conference_tracker_twitch.model.Conference;

@Repository
public interface ConferenceRepository  extends CrudRepository<Conference , Long>{

}
