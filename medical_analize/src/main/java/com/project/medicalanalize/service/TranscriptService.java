package com.project.medicalanalize.service;

import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;

public interface TranscriptService extends BaseCrudService<TranscriptOrder> {

    Long createAndFind(TranscriptOrder order);
}