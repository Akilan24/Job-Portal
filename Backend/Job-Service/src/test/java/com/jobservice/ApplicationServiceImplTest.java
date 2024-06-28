package com.jobservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jobservice.exception.ApplicationNotFoundException;
import com.jobservice.model.Application;
import com.jobservice.repository.ApplicationRepository;
import com.jobservice.service.ApplicationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceImplTest {

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    private Application application;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void setUp() throws Exception {
        application = new Application();
        application.setApplicationId(1L);
        application.setAppliedDate(dateFormat.parse("2024-06-28"));  // Correctly setting the date
        application.setStatus("Pending");
        application.setApplicantEmailId("applicant@example.com");
    }

    @Test
    public void testAddJobApplication() {
        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        Application result = applicationService.addJobApplication(application);
        assertNotNull(result);
        assertEquals(1L, result.getApplicationId());
        assertEquals("Pending", result.getStatus());
        assertEquals("2024-06-28", dateFormat.format(result.getAppliedDate()));  // Check the applied date
    }

    @Test
    public void testGetAllJobApplications() throws ApplicationNotFoundException {
        List<Application> applications = List.of(application);

        when(applicationRepository.findAll()).thenReturn(applications);

        List<Application> result = applicationService.getAllJobApplications();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getApplicationId());
    }

    @Test
    public void testGetAllJobApplicationsNotFound() {
        when(applicationRepository.findAll()).thenReturn(List.of());

        assertThrows(ApplicationNotFoundException.class, () -> applicationService.getAllJobApplications());
    }

    @Test
    public void testGetJobApplicationById() throws ApplicationNotFoundException {
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));

        Application result = applicationService.getJobApplicationById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getApplicationId());
        assertEquals("Pending", result.getStatus());
        assertEquals("2024-06-28", dateFormat.format(result.getAppliedDate()));  // Check the applied date
    }

    @Test
    public void testGetJobApplicationByIdNotFound() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ApplicationNotFoundException.class, () -> applicationService.getJobApplicationById(1L));
    }

    @Test
    public void testUpdateJobApplication() throws Exception {
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        application.setStatus("Approved");
        application.setAppliedDate(dateFormat.parse("2024-07-01"));  // Update the applied date
        Application result = applicationService.updateJobApplication(application);
        assertNotNull(result);
        assertEquals("Approved", result.getStatus());
        assertEquals("2024-07-01", dateFormat.format(result.getAppliedDate()));  // Check the updated applied date
    }

    @Test
    public void testUpdateJobApplicationNotFound() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ApplicationNotFoundException.class, () -> applicationService.updateJobApplication(application));
    }

    @Test
    public void testDeleteJobApplication() throws ApplicationNotFoundException {
        when(applicationRepository.existsById(1L)).thenReturn(true);

        String result = applicationService.deleteJobApplication(1L);
        assertEquals("Job application deleted with id :1", result);
        verify(applicationRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteJobApplicationNotFound() {
        when(applicationRepository.existsById(1L)).thenReturn(false);

        assertThrows(ApplicationNotFoundException.class, () -> applicationService.deleteJobApplication(1L));
    }
}
