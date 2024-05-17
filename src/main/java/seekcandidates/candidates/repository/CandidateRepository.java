package seekcandidates.candidates.repository;

import org.springframework.data.repository.ListCrudRepository;
import seekcandidates.candidates.entity.Candidate;

public interface CandidateRepository  extends ListCrudRepository<Candidate, Long> {

}
