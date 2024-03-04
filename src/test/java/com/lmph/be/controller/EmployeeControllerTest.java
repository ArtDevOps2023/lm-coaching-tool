package com.lmph.be.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.time.LocalDate;

import com.lmph.be.service.EmployeeFlowService;
import com.lmph.be.service.FlowService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.lmph.be.form.EmployeeForm;
import com.lmph.be.service.EmployeeService;

/**
 * Employee Controller Unit Test
 * @author Jhun Tiballa
 */
@SpringBootTest
@AutoConfigureMockMvc
//@GraphQlTest(EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;

	@MockBean
	private EmployeeFlowService employeeFlowService;

	@MockBean
	private FlowService flowService;
	
	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void upsert_hasUserRoleStatusForbidden() throws Exception {
						
		RequestBuilder request = MockMvcRequestBuilders.post("/employees/post");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(405));
	}
	
	
	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void delete_hasUserRoleStatusForbidden() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.delete("/employees/1");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(405));
	}

	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void flowView_hasUserRoleStatusOk() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/employee/flow/view/1");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void flowConfigure_hasUserRoleStatusNotFound() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/employee/flow/configure/1");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(403));
	}

	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void flowSave_hasUserRoleStatusNotFound() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/employee/flow/save");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(404));
	}
	
}
