package com.devconnect.ui_interaction_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devconnect.ui_interaction_service.dto.PostWithUser;
import com.devconnect.ui_interaction_service.service.UiService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ui")
public class UiController {
	private UiService uiService;

	public UiController(UiService uiService) {
		this.uiService = uiService;
	}

	@GetMapping("/feeds")
	public List<PostWithUser> getAllPost(){
		return uiService.getPostwithUser();
	}
}
