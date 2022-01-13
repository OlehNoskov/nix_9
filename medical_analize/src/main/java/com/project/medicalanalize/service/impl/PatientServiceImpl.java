package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.exception.EntityExistException;
import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.feedback.Feedback;
import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.persistence.repository.feedback.FeedbacksRepository;
import com.project.medicalanalize.persistence.repository.order.OrderRepository;
import com.project.medicalanalize.persistence.repository.user.PatientRepository;
import com.project.medicalanalize.service.PatientService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CrudRepositoryHelper<Patient, PatientRepository> patientRepositoryHelper;
    private final PatientRepository patientRepository;
    private final OrderRepository orderRepository;
    private final FeedbacksRepository feedbacksRepository;

    public PatientServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
                              CrudRepositoryHelper<Patient, PatientRepository> patientRepositoryHelper,
                              PatientRepository patientRepository,
                              OrderRepository orderRepository, FeedbacksRepository feedbacksRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.patientRepositoryHelper = patientRepositoryHelper;
        this.patientRepository = patientRepository;
        this.orderRepository = orderRepository;
        this.feedbacksRepository = feedbacksRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Patient patient) {
        if(patientRepository.existsByEmail(patient.getEmail())){
            throw new EntityExistException("this patient is exist!");
        }
        patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));
        patientRepositoryHelper.create(patientRepository, patient);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Patient entity) {
    patientRepositoryHelper.update(patientRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        patientRepositoryHelper.delete(patientRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Patient> findById(Long id) {
        return patientRepositoryHelper.findById(patientRepository,id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Patient> findAll(DataTableRequest request) {
        return patientRepositoryHelper.findAll(patientRepository,request);
    }

    @Override
    public void addOrder(Long patientId, Long orderId) {
        Order order = (Order) orderRepository.getById(orderId);
        Patient patient = findById(patientId).get();
        patient.addOrder(order);
        update(patient);
    }

    @Override
    public void addFeedback(Long patientId, Long feedbackId) {
        Feedback feedback = (Feedback) feedbacksRepository.getById(feedbackId);
        Patient patient = findById(patientId).get();
        patient.addFeedback(feedback);
        update(patient);
    }
}