package com.sysco.mss.order.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.GitProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ManagementController.class)
public class ManagementControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GitProperties gitProperties;

    @Test
    public void testGetVersion() throws Exception {
        Mockito.when(gitProperties.getBranch()).thenReturn("master");
        Assert.assertEquals("master", mockMvc.perform(get("/version").contentType(MediaType.TEXT_PLAIN)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString());

        Mockito.when(gitProperties.getBranch()).thenReturn("feature/add-version-endpoint-using-GitProperties");
        Assert.assertEquals("add-version-endpoint-using-GitProperties", mockMvc.perform(get("/version").contentType(MediaType.TEXT_PLAIN)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString());

        Mockito.when(gitProperties.getBranch()).thenReturn("add-version-endpoint-using-GitProperties");
        Assert.assertEquals("add-version-endpoint-using-GitProperties", mockMvc.perform(get("/version").contentType(MediaType.TEXT_PLAIN)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString());

        Mockito.when(gitProperties.getBranch()).thenReturn("");
        Assert.assertEquals("", mockMvc.perform(get("/version").contentType(MediaType.TEXT_PLAIN)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString());

        Mockito.when(gitProperties.getBranch()).thenReturn(null);
        Assert.assertEquals("", mockMvc.perform(get("/version").contentType(MediaType.TEXT_PLAIN)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString());
    }
}
