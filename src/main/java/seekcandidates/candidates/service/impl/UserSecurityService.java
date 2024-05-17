package seekcandidates.candidates.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import seekcandidates.candidates.entity.UserEntity;
import seekcandidates.candidates.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {

  private final UserRepository userRepository;

  public UserSecurityService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = this.userRepository.findById(username)
        .orElseThrow(()-> new UsernameNotFoundException("User "+ username + "not found"));
    return User.builder()
        .username(userEntity.getUsername())
        .password(userEntity.getPassword())
        .accountLocked(userEntity.getLocked())
        .disabled(userEntity.getDisabled())
        .build();
  }
}
