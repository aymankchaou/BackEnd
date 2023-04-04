package tn.esps.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tn.esps.entities.Presence;
import tn.esps.repositories.PresenceRepository;

@Service
@AllArgsConstructor
public class PresenceService {
	private PresenceRepository presenceRepository;

	public List<Presence> exportPresenceToExcel(@Param("id") Long id,HttpServletResponse response) throws IOException {
		List<Presence> presence = presenceRepository.findUserPresenceByUserId(id);
		ExportExcelService exportUtils = new ExportExcelService(presence);
		exportUtils.exportDataToExcel(id,response);
		return presence;

	}

}
