package io.github.devatherock.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.token.delegation.web.HttpUserGroupInformation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.devatherock.service.TimeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final TimeService timeService;

    @GetMapping("/hello")
    public String sayHello() {
        UserGroupInformation info = HttpUserGroupInformation.get();
        return StringUtils.capitalize("Hello at ") + timeService.getTime();
    }

}
