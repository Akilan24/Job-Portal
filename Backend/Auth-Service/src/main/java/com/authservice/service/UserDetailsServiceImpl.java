//package com.authservice.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import com.authservice.proxyentity.user.Applicant;
//import com.authservice.proxyentity.user.Recruiter;
//import com.authservice.repository.ApplicantRepository;
//import com.authservice.repository.RecruiterRepository;
//
//@Component
//public class UserDetailsServiceImpl implements UserDetailsService{
//
//	@Autowired
//	private ApplicantRepository applicantRepository;
//	@Autowired
//	private RecruiterRepository recruiterRepository;
//	
//	public UserDetails loadUserByApplicantUsername(String username) throws UsernameNotFoundException {
//		Applicant applicant = applicantRepository.findByUsername(username).get();
//		if (applicant == null) {
//			throw new UsernameNotFoundException("User Not Found with username " + username);
//		}
//		return UserDetailsImpl.getApplicant(applicant);
//	}
//	
//	public UserDetails loadUserByRecruiterUsername(String username) throws UsernameNotFoundException {
//		Recruiter recruiter = recruiterRepository.findByUsername(username).get();
//		if (recruiter == null) {
//			throw new UsernameNotFoundException("User Not Found with username " + username);
//		}
//		return UserDetailsImpl.getRecruiter(recruiter);
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}

package com.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.authservice.proxyentity.user.Applicant;
import com.authservice.proxyentity.user.Recruiter;
import com.authservice.repository.ApplicantRepository;
import com.authservice.repository.RecruiterRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ApplicantRepository applicantRepository;
    
    @Autowired
    private RecruiterRepository recruiterRepository;
    
    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        // Try to find the user in the applicant repository
        Applicant applicant = applicantRepository.findByEmailId(emailId).orElse(null);
        if (applicant != null) {
            return UserDetailsImpl.getApplicant(applicant);
        }

        // Try to find the user in the recruiter repository
        Recruiter recruiter = recruiterRepository.findByEmailId(emailId).orElse(null);
        if (recruiter != null) {
            return UserDetailsImpl.getRecruiter(recruiter);
        }

        // If user not found in both repositories, throw exception
        throw new UsernameNotFoundException("User Not Found with username " + emailId);
    }
}

