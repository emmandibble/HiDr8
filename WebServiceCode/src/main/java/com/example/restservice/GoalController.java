package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Integer.parseInt;

@RestController
public class GoalController {

	@GetMapping("/goal")
	public Goal goal(@RequestParam(value = "height", defaultValue = "66") String height, @RequestParam (value = "weight", defaultValue = "140") String weight) {
		long goal = parseInt(weight) / 2;
		return new Goal(parseInt(height), parseInt(weight), goal);
	}
}
