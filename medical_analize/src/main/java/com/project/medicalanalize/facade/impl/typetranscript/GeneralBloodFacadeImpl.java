package com.project.medicalanalize.facade.impl.typetranscript;

import com.project.medicalanalize.facade.typetranscript.GeneralBloodFacade;
import com.project.medicalanalize.persistence.entity.order.transcript.GeneralBlood;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.service.TranscriptService;
import com.project.medicalanalize.service.typetranscript.GeneralBloodService;
import com.project.medicalanalize.web.dto.request.typetranscript.GeneralBloodRequestDto;
import com.project.medicalanalize.web.dto.response.PageData;
import com.project.medicalanalize.web.dto.response.typetranscript.GeneralBloodResponseDto;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.text.ParseException;

@Service
public class GeneralBloodFacadeImpl implements GeneralBloodFacade {

    private final TranscriptService transcriptService;
    private final GeneralBloodService generalBloodService;

    public GeneralBloodFacadeImpl(TranscriptService transcriptService, GeneralBloodService generalBloodService) {
        this.transcriptService = transcriptService;
        this.generalBloodService = generalBloodService;
    }

    @Override
    public void create(GeneralBloodRequestDto generalBloodRequestDto) {
        GeneralBlood generalBlood = new GeneralBlood();
        setterFields(generalBloodRequestDto,generalBlood);
        generalBlood.setTranscript(generalBloodService.findById(generalBloodRequestDto.getTranscriptOrder()));
        generalBloodService.create(generalBlood);
    }

    @Override
    public void update(GeneralBloodRequestDto generalBloodRequestDto, long id) throws ParseException {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public GeneralBloodResponseDto findById(long id) {
        return null;
    }

    @Override
    public PageData<GeneralBloodResponseDto> findAll(WebRequest request) {
        return null;
    }

    private GeneralBlood setterFields(GeneralBloodRequestDto generalBloodRequestDto, GeneralBlood generalBlood) {
       generalBlood.setHemoglobin(generalBloodRequestDto.getHemoglobin());
       generalBlood.setErythrocytes(generalBloodRequestDto.getErythrocytes());
       generalBlood.setReticulocytes(generalBloodRequestDto.getReticulocytes());
       generalBlood.setPlatelets(generalBloodRequestDto.getPlatelets());
       generalBlood.setLeukocytes(generalBloodRequestDto.getLeukocytes());
       generalBlood.setSoe(generalBloodRequestDto.getSoe());
       generalBlood.setMyelocytes(generalBloodRequestDto.getMyelocytes());
       generalBlood.setMetamyelocytes(generalBloodRequestDto.getMetamyelocytes());
       generalBlood.setStab(generalBloodRequestDto.getStab());
       generalBlood.setSegmented_nuclear(generalBloodRequestDto.getSegmented_nuclear());
       generalBlood.setEosinophils(generalBloodRequestDto.getEosinophils());
       generalBlood.setBasophils(generalBloodRequestDto.getBasophils());
       generalBlood.setLymphocytes(generalBloodRequestDto.getLymphocytes());
       generalBlood.setMonocytes(generalBloodRequestDto.getMonocytes());
        return generalBlood;
    }
}
