package seekcandidates.candidates.resource;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seekcandidates.candidates.entity.Candidate;
import seekcandidates.candidates.service.CandidateService;

@RestController
@RequestMapping("/api/candidate")
public class candidateResource {


  private final CandidateService candidateService;

  @Autowired
  public candidateResource(CandidateService candidateService) {
    this.candidateService = candidateService;
  }

  @GetMapping
  @Operation(operationId = "getAllCandidates", description = "get all candidates", summary = "Get all candidates")
  public ResponseEntity<List<Candidate>> getAllCandidates() {
    List<Candidate> candidates = candidateService.getAllCandidates();
    return new ResponseEntity<>(candidates, HttpStatus.OK);
  }

  @PostMapping
  @Operation(summary = "Save a new candidate")
  public ResponseEntity<Candidate> saveCandidate(@RequestBody Candidate candidate) {
    Candidate saveCandidate = candidateService.saveCandidate(candidate);
    return new ResponseEntity<>(saveCandidate, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a candidate by ID")
  public ResponseEntity<?> getCandidateById(@PathVariable Long id) {Candidate candidate = candidateService.getCandidateById(id);
    if (candidate == null) {
      return new ResponseEntity<>("Candidate not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(candidate, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a candidate by ID")
  public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
    Candidate candidateSave = candidateService.updateCandidate(id, candidate);
    return new ResponseEntity<>(candidateSave, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a candidate by ID")
  public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
    candidateService.deleteCandidate(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
