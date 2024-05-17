package seekcandidates.candidates.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seekcandidates.candidates.entity.Candidate;
import seekcandidates.candidates.repository.CandidateRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)

class CandidateServiceImplTest {

  @Mock
  private CandidateRepository candidateRepository;

  @InjectMocks
  private CandidateServiceImpl candidateService;

  private Candidate candidate;

  @BeforeEach
  public void setUp() {
    candidate = new Candidate();
    candidate.setId(1L);
    candidate.setName("John Doe");
    candidate.setEmail("john.doe@example.com");
    candidate.setGender("Male");
    candidate.setSalaryExpected(BigDecimal.valueOf(50000));
  }

  @Test
  public void testSaveCandidate() {
    when(candidateRepository.save(any(Candidate.class))).thenReturn(candidate);

    Candidate savedCandidate = candidateService.saveCandidate(candidate);

    assertNotNull(savedCandidate);
    assertEquals("John Doe", savedCandidate.getName());
    verify(candidateRepository, times(1)).save(candidate);
  }

  @Test
  public void testGetCandidateById() {
    when(candidateRepository.findById(1L)).thenReturn(Optional.of(candidate));

    Candidate foundCandidate = candidateService.getCandidateById(1L);

    assertNotNull(foundCandidate);
    assertEquals("John Doe", foundCandidate.getName());
  }

  @Test
  public void testGetCandidateById_WhenNotFound() {
    when(candidateRepository.findById(1L)).thenReturn(Optional.empty());

    Candidate foundCandidate = candidateService.getCandidateById(1L);

    assertNull(foundCandidate);
  }


  @Test
  public void testGetAllCandidates() {
    List<Candidate> candidates = Arrays.asList(candidate);
    when(candidateRepository.findAll()).thenReturn(candidates);

    List<Candidate> result = candidateService.getAllCandidates();

    assertEquals(1, result.size());
    verify(candidateRepository, times(1)).findAll();
  }

  @Test
  public void testUpdateCandidate() {
    when(candidateRepository.findById(1L)).thenReturn(Optional.of(candidate));
    when(candidateRepository.save(any(Candidate.class))).thenReturn(candidate);

    Candidate updatedCandidate = new Candidate();
    updatedCandidate.setName("Jane Doe");
    updatedCandidate.setEmail("jane.doe@example.com");
    updatedCandidate.setGender("Female");
    updatedCandidate.setSalaryExpected(BigDecimal.valueOf(60000));

    Candidate result = candidateService.updateCandidate(1L, updatedCandidate);

    assertNotNull(result);
    assertEquals("Jane Doe", result.getName());
    assertEquals("jane.doe@example.com", result.getEmail());
  }

  @Test
  public void testUpdateCandidate_WhenNotFound() {
    when(candidateRepository.findById(1L)).thenReturn(Optional.empty());

    Candidate updatedCandidate = new Candidate();
    updatedCandidate.setName("Jane Doe");
    updatedCandidate.setEmail("jane.doe@example.com");
    updatedCandidate.setGender("Female");
    updatedCandidate.setSalaryExpected(BigDecimal.valueOf(60000));

    // Intentamos actualizar el candidato con el ID 1
    assertThrows(EntityNotFoundException.class,
        () -> candidateService.updateCandidate(1L, updatedCandidate),
        "Candidate with ID 1 not found");
  }


  @Test
  public void testDeleteCandidate() {
    doNothing().when(candidateRepository).deleteById(1L);

    candidateService.deleteCandidate(1L);

    verify(candidateRepository, times(1)).deleteById(1L);
  }

  @Test
  public void testUpdateCandidateNotFound() {
    when(candidateRepository.findById(1L)).thenReturn(Optional.empty());

    Candidate updatedCandidate = new Candidate();

    assertThrows(EntityNotFoundException.class, () -> {
      candidateService.updateCandidate(1L, updatedCandidate);
    });
  }
}