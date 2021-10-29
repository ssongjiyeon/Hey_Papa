package com.ssafy.heypapa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "유저 api", tags = { "User" })
@RestController
@RequestMapping("/user")
public class UserController {

}
