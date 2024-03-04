package com.lmph.be.controller;


import com.lmph.be.service.EmployeeService;
import com.lmph.be.service.FlowService;
import com.lmph.be.service.SectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class OnboardingControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlowService flowService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void flow_hasUserRoleStatusNotFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/flow");
        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void flowCreate_hasUserRoleStatusNotFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/flow/create");
        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void flowUpdate_hasUserRoleStatusFNotFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/flow/update/1");
        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void flowSave_hasUserRoleStatusNotFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/flow/save");
        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void flowDelete_hasUserRoleStatusNotFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/flow/delete/1");
        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(404));
    }

}
