package ca.sheridancollege.shanyunp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.shanyunp.beans.Appointment;
import ca.sheridancollege.shanyunp.database.DatabaseAccess;

@Controller
public class AppointmentController {

	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String getIndex(Appointment appointment, Model model) {
		model.addAttribute("appointments", da.getAllAppointments());
		return "index";
	}
	
	@PostMapping("/")
	public String postIndex(@ModelAttribute Appointment appointment) {
		da.addAppointment(appointment);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteAppointment(@PathVariable int id) {
		da.deleteAppointment(id);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editAppointment(@PathVariable int id, Model model) {
		model.addAttribute("appointment", da.selectAppointment(id));
		return "editAppointment";
	}
	@PostMapping("/doEdit")
	public String doEdit(@ModelAttribute Appointment i) {
		da.updateAppointment(i);
		return "redirect:/";
	}

}









