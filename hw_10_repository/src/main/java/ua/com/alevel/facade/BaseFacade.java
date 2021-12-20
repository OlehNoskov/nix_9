package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;

public interface BaseFacade <REQ extends RequestDto, RES extends ResponseDto> {
    void create(REQ req);
    void update(REQ req, long id);
    void delete(long id);
    RES findById(long id);
    PageData<RES> findAll(WebRequest request);
}
