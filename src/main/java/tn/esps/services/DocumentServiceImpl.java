package tn.esps.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esps.entities.Document;
import tn.esps.repositories.IDocumentRepo;

@Service
public class DocumentServiceImpl {

	@Autowired
	private IDocumentRepo docRepo;

	public List<Document> getAllDocuments(Document doc) {
		return docRepo.findAll();
	}

	public Document saveDocument(Document doc) {
		return docRepo.save(doc);
	}

}
