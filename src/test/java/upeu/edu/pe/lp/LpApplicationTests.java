package upeu.edu.pe.lp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
@ActiveProfiles("test")
class LpApplicationTests {

	@MockBean
	private JavaMailSender javaMailSender;


	@Test
	void contextLoads() {
	}

}
