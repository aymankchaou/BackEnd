package tn.esps.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import tn.esps.entities.Presence;
import tn.esps.entities.UserInformation;
import tn.esps.repositories.IUtilidateurRepo;
import tn.esps.repositories.PresenceRepository;
import tn.esps.services.PresenceService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PresenceController {

	@Autowired
	private PresenceRepository presenceRepo;

	@Autowired
	private PresenceService presenceService;

	@Autowired
	private IUtilidateurRepo userRepo;

	@GetMapping("/presences")
	public List<Presence> getAllpresences() {
		return presenceRepo.findAll();
	}

	@PostMapping("/createPresence")
	public Presence createPresence(@RequestBody ObjectNode presence, @RequestParam Long id)
			throws JsonProcessingException {
		Presence pre = new Presence();
		pre.setNbrheures(new ObjectMapper().treeToValue(presence.get("nbrheures"), String.class));
		pre.setDay(LocalDate.parse(new ObjectMapper().treeToValue(presence.get("day"), String.class)));
		Optional<UserInformation> user = userRepo.findById(id);
		if (user.isPresent()) {
			pre.setUserInfo(user.get());
		}
		return presenceRepo.save(pre);
	}

	@GetMapping("/getPresence")
	public List<Presence> findUserPresenceByUserId(@RequestParam Long id) throws IOException {

		return presenceRepo.findUserPresenceByUserId(id);
	}

	@GetMapping("/download")
	private void exportToExcel(@RequestParam Long id, HttpServletResponse response) throws IOException {
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Presence_Information.xlsx";
		response.setHeader(headerKey, headerValue);
		presenceService.exportPresenceToExcel(id, response);

	}
}
