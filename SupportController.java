// package com.sysco.mss.order.controller;

import VersionInfo.*;
// import io.swagger.annotations.Api;
// import io.swagger.annotations.ApiOperation;
// import io.swagger.annotations.ApiResponse;
// import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/version")
@Api(value = "Support Api", tags = "Support")
public class SupportController {

    @Value("${version:unknown}")
    private String version;

    @Value("${git.branch:develop}")
    private String gitBranch;

    @Value("${git.sha:latest}")
    private String gitSHA;

    @Value("${builtDate:unknown}")
    private String buildDate;

    private Pattern pattern = Pattern.compile("(?<=release/).*");

    @ApiOperation(value = "Get API Version", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The API Version")
    })
    @GetMapping()
    public String getVersion() {
        if (gitBranch != null) {
            Matcher matcher = pattern.matcher(gitBranch);
            if (matcher.find()) {
                return matcher.group(0);
            }
        }

        return version;
    }

    @ApiOperation(value = "Get API Version Info", response = VersionInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The API Version info")
    })
    @GetMapping(value = "/info")
    public VersionInfo getVersionInfo() {
        return new VersionInfo(this.getVersion(), gitSHA, gitBranch, buildDate);
    }
}
