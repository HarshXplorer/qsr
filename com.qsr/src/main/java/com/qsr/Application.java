package com.qsr;

import com.qsr.dao.UserRepository;
import com.qsr.entity.User;
import com.qsr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

//		User user = new User("abcd@email.com","1234","customer","ab","cd","up","india","28111");
//		User user1 = new User("efgh@email.com","5678","customer","ef","gh","tn","india","288283");
//		User user2 = new User("ijkl@email.com","73492","customer","ij","kl","mh","india","289001");
//		User user3 = new User("mnop@email.com","326880","customer","mn","op","go","india","284567");
//		userRepository.save(user);
//		userRepository.save(user1);
//		userRepository.save(user2);
//		userRepository.save(user3);
	}
}
