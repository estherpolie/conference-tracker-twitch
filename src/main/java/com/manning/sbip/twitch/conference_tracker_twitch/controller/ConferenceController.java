package com.manning.sbip.twitch.conference_tracker_twitch.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.manning.sbip.twitch.conference_tracker_twitch.model.Conference;
import com.manning.sbip.twitch.conference_tracker_twitch.service.ConferenceService;

import jakarta.validation.Valid;

@Controller
public class ConferenceController {
	private ConferenceService conferenceService;

	@Autowired
	public ConferenceController(ConferenceService conferenceService) {
		this.conferenceService = conferenceService;
	}

	@GetMapping
	public String index() {
		return "redirect:/login";

	}

	@GetMapping("/index")
	public String index(Model model) {
		List<Conference> conferenceList = (List<Conference>) conferenceService.getConferences();
		model.addAttribute("conferences", conferenceList.isEmpty() ? Collections.EMPTY_LIST : conferenceList);
		return "index";

	}

	@GetMapping("/addconference")
	public String showAddCourseForm(Conference course) {
		return "add-conference";
	}

	@PostMapping("/addconference")
	public String addCourse(@Valid Conference course, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add:conference";

		}
		conferenceService.createConference(course);
		model.addAttribute("Conferences", conferenceService.getConferences());

		return "redirect:/index";
	}

	@GetMapping("/update/{id}")
	public String showUpdateCourseForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("conference", conferenceService.getConferenceById(id).get());

		return "update-conference";
	}

	@PutMapping("/update/{id}")
	public String updateCourse(@PathVariable("id") Long id, @Valid Conference conference, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			conference.setId(id);
			return "update-conference";

		}
		conferenceService.updateConference(id, conference);
		model.addAttribute("conferences", conferenceService.getConferences());
		return "redirect:/index";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteCourse(@PathVariable("id") Long id, Model model) {
		conferenceService.deleteConference(id);
		model.addAttribute("conferences", conferenceService.getConferences());
		return "redirect:/index";
	}

}
