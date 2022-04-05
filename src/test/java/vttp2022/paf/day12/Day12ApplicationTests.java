package vttp2022.paf.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.paf.day12.service.GiphyService;

@SpringBootTest
class Day12ApplicationTests {
	
	@Autowired
	private GiphyService giphySvc;
	
	@Test
	void shouldLoad10Images() {
		List<String> list  = giphySvc.getGiphs("dog");
		assertEquals(10, list.size(), "Default number of gifs");
	}

	@Test
	void shouldReturnNothing(){
		List<String> List = giphySvc.getGiphs("dsjfgojfdf", "r", 10);
		assertTrue(List.isEmpty());
	}

}
