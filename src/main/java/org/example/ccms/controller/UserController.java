package org.example.ccms.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ccms.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping({"/api/"})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

}
