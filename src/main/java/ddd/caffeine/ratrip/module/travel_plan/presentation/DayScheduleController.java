package ddd.caffeine.ratrip.module.travel_plan.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.travel_plan.DayScheduleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/schedule")
public class DayScheduleController {

	private DayScheduleService dayScheduleService;
}
