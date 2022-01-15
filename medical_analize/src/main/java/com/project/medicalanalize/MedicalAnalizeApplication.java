package com.project.medicalanalize;

import com.project.medicalanalize.persistence.entity.user.Admin;
import com.project.medicalanalize.persistence.repository.user.AdminRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class MedicalAnalizeApplication {

    private final BCryptPasswordEncoder encoder;
    private final AdminRepository adminRepository;

    public MedicalAnalizeApplication(BCryptPasswordEncoder encoder, AdminRepository adminRepository) {
        this.encoder = encoder;
        this.adminRepository = adminRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MedicalAnalizeApplication.class, args);

        String dd = "27";
        String mm = "03";
        String yyyy = "2021";

        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 11, 24);
        Date date = calendar.getTime();
        System.out.println(date);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
        if (!adminRepository.existsByEmail("admin@mail.com")) {
            Admin admin = new Admin();
                    admin.setEmail("admin@mail.com");
            admin.setPassword(encoder.encode("rootroot"));
            adminRepository.save(admin);
        }
    }
}