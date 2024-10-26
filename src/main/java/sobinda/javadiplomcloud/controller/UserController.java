package sobinda.javadiplomcloud.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sobinda.javadiplomcloud.config.AuthenticationConfigConstants;
import sobinda.javadiplomcloud.dto.UserDTO;
import sobinda.javadiplomcloud.model.Token;
import sobinda.javadiplomcloud.repository.UsersRepository;
import sobinda.javadiplomcloud.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class UserController {

    private final UsersRepository usersRepository;

    private final UserService userService;
    //private ObjectMapper objectMapper;



    @PostMapping("login")
    public ResponseEntity<Token> login(@RequestBody UserDTO userDTO) {
        log.info("Попытка авторизоваться на сервере");
        Token token = userService.login(userDTO);
        return ResponseEntity.ok(token);
    }

//    @SneakyThrows
//    @GetMapping("api/all")
//    public String getAll(){
//        List<UserEntity> userEntities=usersRepository.findAll();
//        return objectMapper.writeValueAsString(userEntities);
//    }

    @PostMapping("logout")
    public ResponseEntity<Void> logout(@RequestHeader(value = AuthenticationConfigConstants.AUTH_TOKEN) String authToken,
                                       HttpServletRequest request, HttpServletResponse response) {
        String userLogout = userService.logout(authToken, request, response);
        if (userLogout == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Пользователь: {} успешно вышел из системы. Auth-token: {}", userLogout, authToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    public void setObjectMapper(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
}
