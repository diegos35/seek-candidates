package seekcandidates.candidates.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seekcandidates.candidates.entity.Candidate;
import seekcandidates.candidates.repository.CandidateRepository;
import seekcandidates.candidates.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {

  private final CandidateRepository candidateRepository;

  @Autowired
  public CandidateServiceImpl(CandidateRepository candidateRepository) {
    this.candidateRepository = candidateRepository;
  }

  @Override
  public Candidate saveCandidate(Candidate candidate) {
    return candidateRepository.save(candidate);
  }

  @Override
  public Candidate getCandidateById(Long id) {
    return this.candidateRepository.findById(id).orElse(null);
  }

  @Override
  public List<Candidate> getAllCandidates() {
    return candidateRepository.findAll();
  }

  @Override
  public Candidate updateCandidate(Long id, Candidate candidateSave) {
    Optional<Candidate> optionalCandidate = candidateRepository.findById(id);

    if (optionalCandidate.isPresent()) {
      Candidate candidate = optionalCandidate.get();
      candidate.setName(candidateSave.getName());
      candidate.setEmail(candidateSave.getEmail());
      candidate.setGender(candidateSave.getGender());
      candidate.setSalaryExpected(candidateSave.getSalaryExpected());

      return candidateRepository.save(candidate);
    } else {
      throw new EntityNotFoundException(
          "Candidate with ID " + id + " not Found");
    }
  }

  @Override
  public void deleteCandidate(Long id) {
    candidateRepository.deleteById(id);
  }
}
