package fr.iocean.dta;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import fr.iocean.dta.user.model.User;
import fr.iocean.dta.user.repository.UserRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import fr.iocean.dta.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = App.class)
@Transactional
public class UserServiceTest {	

    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private UserRepository userRepository;
	
	@Test
    public void testAppWithHibernate() {
		System.setProperty("spring.profiles.active", "JPA");
        try {
            
			/* Clean */
            userRepository.deleteAllUsers();

            UserService service = (UserService) context.getBean("userService");
	    	 
	        /*
	         * Create User1
	         */
            User user1 = new User();
            user1.setLogin("login user1");
            user1.setPassword("password user1");
            user1.setName("name user1");
	 
	        /*
	         * Create User1
	         */
            User user2 = new User();
            user2.setLogin("login user2");
            user2.setPassword("password user2");
            user2.setName("name user2");
	 
	        /*
	         * Persist both Employees
	         */
            service.create(user1);
            service.create(user2);
	 
	        /*
	         * Get all users list from database
	         */
            List<User> users = service.findAll();
            users.forEach(System.out::println);
	 
            System.out.println("---");
            userRepository.deleteAllUsers();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
