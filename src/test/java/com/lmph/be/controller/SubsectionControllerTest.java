
package com.lmph.be.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
 

/**
 * Subsection Controller Unit Test
 * @author Ryan Valmoria
 */
@SpringBootTest
@AutoConfigureMockMvc
class SubsectionControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	/**
	 * Unit test to check if role user has access to add or update a subsection
	 * @throws Exception
	 */
	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void upsertSubsection_hasUserRoleStatusForbidden() throws Exception {
						
		RequestBuilder request = MockMvcRequestBuilders.post("/onboarding/section/1/subsection/1");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(405));
	}
	
	/**
	 * Unit test to check if role user has access to delete a subsection
	 * @throws Exception
	 */
	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void deleteSubsection_hasUserRoleStatusForbidden() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.delete("/onboarding/section/1/subsection/1");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(405));
	}
	
}
