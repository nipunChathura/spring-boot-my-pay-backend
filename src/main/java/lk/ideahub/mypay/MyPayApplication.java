package lk.ideahub.mypay;

import lk.ideahub.mypay.service.impl.EncryptDecryptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyPayApplication {

	private static EncryptDecryptServiceImpl encryptDecryptService = new EncryptDecryptServiceImpl();

	public static void main(String[] args) {
		SpringApplication.run(MyPayApplication.class, args);
		encryptDecryptService.createKey();
	}

}
