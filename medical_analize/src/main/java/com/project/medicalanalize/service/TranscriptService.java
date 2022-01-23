package com.project.medicalanalize.service;

import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;

import java.util.List;

public interface TranscriptService extends BaseCrudService<TranscriptOrder> {

    Long createAndFind(TranscriptOrder order);
}