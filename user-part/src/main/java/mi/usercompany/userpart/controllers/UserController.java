package mi.usercompany.userpart.controllers;

import lombok.extern.slf4j.Slf4j;
import mi.usercompany.userpart.dto.UserDto;
import mi.usercompany.userpart.dto.UserDtoWithoutCompany;
import mi.usercompany.userpart.services.UserService;
import mi.usercompany.userpart.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserDto createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        log.info("user added");
        return userDto;
    }

    @GetMapping("/read/{page}/{size}")
    public List<UserDto> readUsers(@PathVariable("page") Long page, @PathVariable("size") Long pageSize) {
        return userService.getUsers(page, pageSize);
    }

    @GetMapping("read/{id}")
    public UserDto readUser(@PathVariable Long id) {return userService.getUser(id);}

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable  Long id){
        userService.deleteUser(id);
        log.info("user deleted");
    }
    @PutMapping("/update")
    public UserDto updateCompany(@RequestBody UserDto userDto) {
        userService.updateData(userDto);
        log.info("user updated");
        return userDto;
    }
    @GetMapping("readOnlyUser/{id}")
    public UserDtoWithoutCompany readOnlyUser(@PathVariable Long id) {return userService.getOnlyUser(id);}
}
