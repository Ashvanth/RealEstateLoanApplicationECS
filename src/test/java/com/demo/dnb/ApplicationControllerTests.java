package com.demo.dnb;

import com.demo.dnb.controller.ApplicationController;
import com.demo.dnb.entity.ApplicantInformation;
import com.demo.dnb.service.ApplicantionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationControllerTests {

    @Mock
    private ApplicantionService applicantionService;

    @InjectMocks
    private ApplicationController applicationController;

    @Test
    public void testSubmitApplicationInformationValid() {
        ApplicantInformation applicantInformation = new ApplicantInformation();
        when(applicantionService.submitApplication(applicantInformation)).thenReturn("12345");

        ResponseEntity responseEntity = applicationController.submitApplicationInformation(applicantInformation);

        verify(applicantionService, times(1)).submitApplication(applicantInformation);
        assert responseEntity.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testSubmitApplicationInformationInvalid() {
        ApplicantInformation applicantInformation = new ApplicantInformation();
        when(applicantionService.submitApplication(applicantInformation)).thenReturn("INVALID");

        ResponseEntity responseEntity = applicationController.submitApplicationInformation(applicantInformation);

        verify(applicantionService, times(1)).submitApplication(applicantInformation);
        assert responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST;
    }

    @Test
    public void testSubmitApplicationInformationException() {
        ApplicantInformation applicantInformation = new ApplicantInformation();
        when(applicantionService.submitApplication(applicantInformation)).thenThrow(new DataAccessException("Mock Exception") {});

        ResponseEntity responseEntity = applicationController.submitApplicationInformation(applicantInformation);

        verify(applicantionService, times(1)).submitApplication(applicantInformation);
        assert responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST;
    }

}
