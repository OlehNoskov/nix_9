package com.project.medical_analize.service.impl;

import com.project.medical_analize.exception.EntityExistException;
import com.project.medical_analize.persistence.crud.CrudRepositoryHelper;
import com.project.medical_analize.persistence.datatable.DataTableRequest;
import com.project.medical_analize.persistence.datatable.DataTableResponse;
import com.project.medical_analize.persistence.entity.user.Doctor;
import com.project.medical_analize.persistence.entity.user.Patient;
import com.project.medical_analize.persistence.entity.user.User;
import com.project.medical_analize.persistence.repository.user.DoctorRepository;
import com.project.medical_analize.persistence.repository.user.PatientRepository;
import com.project.medical_analize.service.PersonalCrudService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonalCrudServiceImpl implements PersonalCrudService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final CrudRepositoryHelper<Doctor, DoctorRepository> crudRepositoryHelperDoctor;
    private final CrudRepositoryHelper<Patient, PatientRepository> crudRepositoryHelperPatient;


    public PersonalCrudServiceImpl(
            BCryptPasswordEncoder bCryptPasswordEncoder,
            DoctorRepository doctorRepository,
            PatientRepository patientRepository,
            CrudRepositoryHelper<Doctor, DoctorRepository> crudRepositoryHelperDoctor,
            CrudRepositoryHelper<Patient, PatientRepository> crudRepositoryHelperPatient) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.crudRepositoryHelperDoctor = crudRepositoryHelperDoctor;
        this.crudRepositoryHelperPatient = crudRepositoryHelperPatient;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(User personal) {
        if (personal instanceof Doctor) {
            if (doctorRepository.existsByEmail(personal.getEmail())) {
                throw new EntityExistException("this doctor is exist");
            }
            Doctor doctor = (Doctor) personal;
            doctor.setPassword(bCryptPasswordEncoder.encode(personal.getPassword()));
            crudRepositoryHelperDoctor.create(doctorRepository, doctor);
        }

        if (personal instanceof Patient) {
            if (patientRepository.existsByEmail(personal.getEmail())) {
                throw new EntityExistException("this patient is exist");
            }
            Patient patient = (Patient) personal;
            patient.setPassword(bCryptPasswordEncoder.encode(personal.getPassword()));
            crudRepositoryHelperPatient.create(patientRepository, patient);
        }
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<User> findById(Long id) {
        System.out.println("personal crud service!");
        return Optional.empty();
    }

//    @Override
//    public User findById(Long id) {
//        return null;
//    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        return null;
    }
}