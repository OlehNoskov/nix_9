package com.project.medical_analize;

//import com.project.medical_analize.persistence.entity.user.Admin;
//import com.project.medical_analize.persistence.repository.user.AdminRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class })
public class MedicalAnalizeApplication {

//        private final BCryptPasswordEncoder encoder;
//    private final AdminRepository adminRepository;
//
//    public MedicalAnalizeApplication(BCryptPasswordEncoder encoder, AdminRepository adminRepository) {
//        this.encoder = encoder;
//        this.adminRepository = adminRepository;
//    }

    public static void main(String[] args) {
        SpringApplication.run(MedicalAnalizeApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void listen() {
//        Admin admin = new Admin();
//        admin.setEmail("admin@mail.com");
//        admin.setPassword(encoder.encode("rootroot"));
//        adminRepository.save(admin);
//        String pass = "123456789";
//        String encode = encoder.encode(pass);
//        System.out.println("encode = " + encode);
//        String newEncode = encoder.encode(pass);
//        System.out.println("newEncode = " + newEncode);
//        boolean matches = newEncode.matches(pass);
//        System.out.println("matches = " + matches);
//    }
}