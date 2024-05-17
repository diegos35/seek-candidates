package seekcandidates.candidates.repository;

import org.springframework.data.repository.CrudRepository;
import seekcandidates.candidates.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {

}
