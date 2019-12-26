package com.sysco.mss.order.controller;

import com.sysco.mss.order.model.VersionInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest({SupportController.class})
public class SupportControllerTest {

    @Value("${version}")
    private String version;

    @Value("${git.branch}")
    private String gitBranch;

    @Value("${git.sha}")
    private String gitSHA;

    @Value("${builtDate}")
    private String buildDate;

    @Autowired
    private SupportController supportController;

    private void reset(String field, String value){
        ReflectionTestUtils.setField(supportController, field, value);
    }

    @Test
    public void testGetVersion_success(){
        ReflectionTestUtils.setField(supportController, "gitBranch", "release/1.33.0");
        assertEquals("1.33.0", supportController.getVersion());
    }

    @Test
    public void testGetVersion_null_gitBranch(){
        ReflectionTestUtils.setField(supportController, "gitBranch", null);
        assertEquals("33.0.0-SNAPSHOT", supportController.getVersion());
        reset("gitBranch", gitBranch);
    }

    @Test
    public void testGetVersionInfo_success(){
        assertEquals(version, supportController.getVersionInfo().getVersion());
        assertEquals(gitSHA, supportController.getVersionInfo().getGitSHA());
        assertEquals(gitBranch, supportController.getVersionInfo().getGitBranch());
        assertEquals(buildDate, supportController.getVersionInfo().getBuiltDate());
    }
}
