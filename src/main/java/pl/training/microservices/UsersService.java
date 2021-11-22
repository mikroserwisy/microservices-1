package pl.training.microservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public UserEntity add(UserEntity user) {
        return usersRepository.save(user);
    }

    public UserEntity getById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

}
