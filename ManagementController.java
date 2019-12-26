package com.sysco.mss.order.controller;

import com.sysco.mss.order.model.VersionInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.GitProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;


/**
 * Sysco Shop Report Service Management Controller
 */

@RestController
@RequestMapping("/version")
@Api(value = "Order Service Management API", tags = {"Management Controller"})
public class ManagementController {

    @Value("${version:SNAPSHOT}")
    private String version;

    @Value("${git.branch:develop}")
    private String gitBranch;

    @Value("${git.sha:latest}")
    private String gitSHA;

    @Value("${builtDate:unknown}")
    private String buildDate;
    public String getDate() {
        Date now = new Date();
        buildDate = now.toString();
        return buildDate;
    }

    private GitProperties gitProperties;

    @Autowired
    public ManagementController(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @ApiOperation(value = "Get API version", response = String.class)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Version of the API in plain text.")
    })
    @GetMapping()
    public String getVersion() {
        if (StringUtils.isNotEmpty(gitProperties.getBranch())) {
            String[] split = gitProperties.getBranch().split("/", 2);
            return split.length > 1 ? split[1] : split[0];
        }

        return "";
    }
    @ApiOperation(value = "Get API Version Info", response = VersionInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The API Version info")
    })
    @GetMapping(value = "/info")
    public VersionInfo getVersionInfo() {
        return new VersionInfo(this.getVersion(), this.gitProperties.getCommitId(), this.gitProperties.getBranch(), this.getDate() );
    }

}
