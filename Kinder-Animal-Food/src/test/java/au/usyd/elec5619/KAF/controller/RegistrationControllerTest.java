package au.usyd.elec5619.KAF.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import au.usyd.elec5619.KAF.user.CrmUser;

public class RegistrationControllerTest {
	
	private MockMvc mockMvc;
		
	@Autowired
	MockHttpSession session;
	
	@Before
	public void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
		this.mockMvc = MockMvcBuilders.standaloneSetup(new RegistrationController()).setViewResolvers(viewResolver).build();
	}
	
	@Test
	public void testLocatePage() throws Exception {
		this.mockMvc.perform(get("/register/showRegistrationForm"))
		.andExpect(status().isOk())
		.andExpect(view().name("regis-form"))
		.andDo(MockMvcResultHandlers.print())
		.andReturn();
	}
	
	@Test
	public void testProcessRegistrationFormFail() throws Exception {
		this.mockMvc.perform(get("/register/processRegistrationForm"))
		.andExpect(status().is4xxClientError())
		.andDo(MockMvcResultHandlers.print())
		.andReturn();
	}
	
	@Test
	public void testProcessRegistrationFormSuccess() throws Exception {
		CrmUser crmUser = new CrmUser();
		crmUser.setUserName("testing@email.com");
		crmUser.setBirthDate("Birthday");
		crmUser.setGender("Female");
		crmUser.setMatchingPassword("Password123");
		crmUser.setMatchingPassword("Password123");
		session.setAttribute("crmUser", crmUser);
		this.mockMvc.perform(get("/register/processRegistrationForm").session(session))
		.andExpect(status().isOk())
		.andExpect(view().name("registration-confirmation"))
		.andDo(MockMvcResultHandlers.print())
		.andReturn();;
	}
	
}
