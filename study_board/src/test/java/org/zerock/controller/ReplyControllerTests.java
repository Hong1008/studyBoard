package org.zerock.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.domain.ReplyVO;
import org.zerock.domain.Ticket;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= {
		org.zerock.config.RootConfig.class,
		org.zerock.config.ServletConfig.class
})
@Log4j
public class ReplyControllerTests {
	
	private Long[] bnoArr = {98304L, 98303L, 98302L, 98301L, 98300L};
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	//@Test
	public void testCreate() throws Exception{
		ReplyVO vo = new ReplyVO();
		vo.setBno(bnoArr[0]);
		vo.setReply("testReply");
		vo.setReplyer("user");
		String jsonStr = new Gson().toJson(vo);
		log.info(jsonStr);
		mockMvc.perform(post("/replies/new")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr)).andExpect(status().is(200));
	}
	
	@Test
	public void testGetList() throws Exception{
		mockMvc.perform(get("/replies/pages/"+bnoArr[0]+"/1")).andExpect(status().is(200));
	}
}
