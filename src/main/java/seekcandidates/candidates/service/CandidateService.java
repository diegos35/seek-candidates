package seekcandidates.candidates.service;

import java.util.List;
import seekcandidates.candidates.entity.Candidate;

public interface CandidateService {

  Candidate saveCandidate(Candidate candidate);

  Candidate getCandidateById(Long id);

  List<Candidate> getAllCandidates();

  Candidate updateCandidate(Long id, Candidate candidate);

  void deleteCandidate(Long id);
}
