package com.project.medicalanalize;

import com.project.medicalanalize.persistence.entity.user.Admin;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.persistence.repository.user.AdminRepository;

import com.project.medicalanalize.persistence.repository.user.DoctorRepository;
import com.project.medicalanalize.persistence.repository.user.PatientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class MedicalAnalizeApplication {

    private final BCryptPasswordEncoder encoder;
    private final AdminRepository adminRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public MedicalAnalizeApplication(BCryptPasswordEncoder encoder,
                                     AdminRepository adminRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.encoder = encoder;
        this.adminRepository = adminRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MedicalAnalizeApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
        if (!adminRepository.existsByEmail("admin@mail.com")) {
            Admin admin = new Admin();
            admin.setEmail("admin@mail.com");
            admin.setPassword(encoder.encode("rootroot"));
            adminRepository.save(admin);
        }
        if (!patientRepository.existsByEmail("patient@mail.com")) {
            Patient patient = new Patient();
            patient.setEmail("patient@mail.com");
            patient.setPassword(encoder.encode("rootroot"));
            patientRepository.save(patient);
        }
        if (!doctorRepository.existsByEmail("doctor@mail.com")) {
            Doctor doctor = new Doctor();
            doctor.setEmail("doctor@mail.com");
            doctor.setPassword(encoder.encode("rootroot"));
            doctorRepository.save(doctor);
        }
    }
}