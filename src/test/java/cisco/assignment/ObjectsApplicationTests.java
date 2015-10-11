package cisco.assignment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import cisco.assignment.ObjectsApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ObjectsApplication.class)
public class ObjectsApplicationTests {
	
	RestTemplate restTemplate = new TestRestTemplate();
	
	@Test
	public void contextLoads() {
		
	}

}
