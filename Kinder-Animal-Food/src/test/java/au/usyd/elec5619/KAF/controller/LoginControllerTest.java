package au.usyd.elec5619.KAF.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class LoginControllerTest {
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
		this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginController()).setViewResolvers(viewResolver).build();
	}
	
	@Test
	public void testLoginPage() throws Exception {
		this.mockMvc.perform(get("/showMyLoginPage"))
		.andExpect(status().isOk())
		.andExpect(view().name("login-form"))
		.andDo(MockMvcResultHandlers.print())
		.andReturn();
	}

	@Test
	public void testAccessDeniedPage() throws Exception {
		this.mockMvc.perform(get("/access-denied"))
		.andExpect(status().isOk())
		.andExpect(view().name("access-denied"))
		.andDo(MockMvcResultHandlers.print())
		.andReturn();
	}

}
