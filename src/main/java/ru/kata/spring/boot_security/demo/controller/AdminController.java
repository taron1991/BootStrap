package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;


import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    public String getUsers(Model model) {
        List<User> users = userService.listUsers();
        model.addAttribute("listOfUsers", users);
        return "bootstrap/users";
    }

    @GetMapping("/edit")
    public String getEditForm(@RequestParam Long id, Model model) {
        User userEdit = userService.findUser(id);
        List<Role> roles = roleService.getRolesList();
        model.addAttribute("allRoles", roles);
        model.addAttribute("user", userEdit);
        return "update";
    }

    @PostMapping("/adduser")
    public String addUser(@Validated(User.class) @ModelAttribute("user") User user,
                          @RequestParam("authorities") List<String> values,
                          BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        Set<Role> roleSet = userService.getSetOfRoles(values);
        user.setRoles(roleSet);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("update")
    public String updateUser(@Validated(User.class) @ModelAttribute("user") User user,
                             @RequestParam("authorities") List<String> values,
                             BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        Set<Role> roleSet = userService.getSetOfRoles(values);
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user",new User());
        List<Role> roles = roleService.getRolesList();
        model.addAttribute("allRoles", roles);
        return "bootstrap/create";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Long id, Model model) {
        User user = userService.findUser(id);
        if(user != null) {
            userService.deleteUser(id);
            model.addAttribute("messege", "user " + user.getUsername() + " succesfully deleted");
        } else {
            model.addAttribute("messege", "no such user");
        }

        return "redirect:/admin";
    }



}
