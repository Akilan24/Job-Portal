package com.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.exception.RecruiterNotFoundException;
import com.userservice.model.Recruiter;
import com.userservice.repository.RecruiterRepository;

@Service
public class RecruiterServiceImpl implements RecruiterService {

	@Autowired
	private RecruiterRepository recruiterRepository;

	@Override
	public Recruiter addRecruiter(Recruiter recruiter) {
		return recruiterRepository.save(recruiter);
	}

	@Override
	public List<Recruiter> getAllRecruiters() throws RecruiterNotFoundException {
		List<Recruiter> findAll = recruiterRepository.findAll();
		if (findAll.isEmpty()) {
			throw new RecruiterNotFoundException("Recruiter not found");
		}

		return recruiterRepository.findAll();
	}

	@Override
	public Recruiter getRecruiterByEmailId(String emailId) throws RecruiterNotFoundException {
		return recruiterRepository.findById(emailId)
				.orElseThrow(() -> new RecruiterNotFoundException("Recruiter not found with id " + emailId));
	}

	@Override
	public Recruiter updateRecruiter(Recruiter recruiter) throws RecruiterNotFoundException {
		Optional<Recruiter> existingRecruiter = recruiterRepository.findById(recruiter.getEmailId());
		if (existingRecruiter.isPresent()) {
			Recruiter updatedRecruiter = existingRecruiter.get();
			updatedRecruiter.setCompany(recruiter.getCompany());
			updatedRecruiter.setCity(recruiter.getCity());
			updatedRecruiter.setState(recruiter.getState());
			updatedRecruiter.setCountry(recruiter.getCountry());
			updatedRecruiter.setPincode(recruiter.getPincode());
			updatedRecruiter.setAbout(recruiter.getAbout());
			return recruiterRepository.save(updatedRecruiter);
		} else {
			throw new RecruiterNotFoundException("Recruiter not found with id " + recruiter.getEmailId());
		}
	}

	@Override
	public String deleteRecruiter(String emailId) throws RecruiterNotFoundException {
		if (recruiterRepository.existsById(emailId)) {
			recruiterRepository.deleteById(emailId);
			return "Recruiter deleted";
		} else {
			throw new RecruiterNotFoundException("Recruiter not found with id " + emailId);
		}
	}
}
