package pl.training.microservices;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("users")
@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<UserEntity> add(@RequestBody UserEntity user) {
        UserEntity savedUser = usersService.add(user);
        return ResponseEntity
                .created(URI.create("http://localhost/users/" + user.getId()))
                .body(savedUser);
    }


    @GetMapping("{id}")
    public UserEntity getById(@PathVariable Long id) {
        return usersService.getById(id);
    }

}
