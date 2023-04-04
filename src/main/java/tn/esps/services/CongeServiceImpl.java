package tn.esps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esps.entities.Conge;
import tn.esps.entities.UserInformation;
import tn.esps.repositories.ICongeRepo;
import tn.esps.repositories.IUtilidateurRepo;

@Service
public class CongeServiceImpl {

	private int d = 0;
	@Autowired
	private ICongeRepo congeRepo;

	@Autowired
	private IUtilidateurRepo userRepo;

	public List<Conge> getAllConges(Conge conge) {
		return congeRepo.findAll();
	}

	public Optional<Conge> findCongeById(Long id) {
		return congeRepo.findById(id);
	}

	public Conge createConge(Conge conge) {
		conge.setStatusOfDemand("not_yet_treated");
		return congeRepo.save(conge);
	}

	public Conge updateConge(Conge conge) {
		return congeRepo.save(conge);

	}

	public List<Conge> getAllConges() {
		// TODO Auto-generated method stub
		return null;
	}

	public Conge CreateConge(Conge conge) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteConge(Conge conge) {
		congeRepo.delete(conge);

	}

	public void accepterConge(Long id) {

		Conge conge = congeRepo.findById(id).get();
		UserInformation user = conge.getUser();

		conge.setStatusOfDemand("Accepted");
		congeRepo.save(conge);
		user.setJoursConges(user.getJoursConges() + conge.getDuree());
		user.setSoldeConges(user.getDureeConges() - user.getJoursConges());
		userRepo.save(user);

	}

	public boolean refuserConge(Long id, String commentaire) {
		Optional<Conge> conge = congeRepo.findById(id);
		if (conge.isPresent()) {
			conge.get().setStatusOfDemand("Refused");
			conge.get().setCommentaire(commentaire);
			congeRepo.save(conge.get());
			return true;
		}
		return false;
	}

	public int countDuree(Long id) {

		UserInformation user = userRepo.findById(id).get();
		List<Conge> conges = user.getConges();
		for (Conge conge : conges) {
			d = d + (conge.getDuree());
		}
		return d;
	}

	public void updateDuree(Long id) {

		UserInformation user = userRepo.findById(id).get();
		user.setDureeConges(d);
		userRepo.save(user);
	}

}
