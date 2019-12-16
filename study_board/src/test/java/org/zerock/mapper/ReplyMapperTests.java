package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// Java Config
@ContextConfiguration(classes = { org.zerock.config.RootConfig.class })
@Log4j
public class ReplyMapperTests {

	private Long[] bnoArr = {98304L, 98303L, 98302L, 98301L, 98300L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;

	//@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	//@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i%5]);
			vo.setReply("댓글 테스트 "+i);
			vo.setReplyer("replyer"+i);
			mapper.insert(vo);
		});
	}
	
	//@Test
	public void testUpdate() {
		Long rno = 10L;
		ReplyVO vo = mapper.read(rno);
		vo.setReply("Update reply");
		int count = mapper.update(vo);
		log.info("update count: "+count);
	}
	
	@Test
	public void testList() {
		List<ReplyVO> replies = mapper.getListWithPaging(new Criteria(), bnoArr[0]);
		replies.forEach(reply -> log.info(reply));
	}
}
