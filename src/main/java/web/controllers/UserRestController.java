package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.models.Role;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 * @author Pavel Peskov
 */
@RestController
public class UserRestController {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public UserRestController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/getAuthorizedUser")
    public ResponseEntity<?> getAuthorizedUser() {
        /*
         * To get authorized user from Security Context
         * @return: status of HTTP response
         */
        User authorizedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return authorizedUser != null
                ? new ResponseEntity<>(authorizedUser, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Iterable<?>> getAllUsers() {
        /*
         * To get all users
         * @return: List<User>
         */
        final List<User> users = userService.listUsers();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<Iterable<?>> getAllRoles() {
        /*
         * To get all roles
         * @return: List<Role>
         */
        final List<Role> roles = roleService.listRoles();

        return roles != null && !roles.isEmpty()
                ? new ResponseEntity<>(roles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        /*
         * To get an user by ID
         * @return: Http status
         */
        final User user = userService.getUserById(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        /*
         * To remove an user
         * @return: Http status
         */
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userService.removeUser(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        /*
         * To add an user
         * @return: body of user
         */
        userService.addUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        /*
         * To edit an user
         * @return: Http status
         */
        userService.editUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}












