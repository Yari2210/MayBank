package MayBank.controller;

import MayBank.config.*;
import MayBank.Model.AppModel;
import MayBank.Model.RequestModel;
import MayBank.service.ApllicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/maybank")
public class ApllicationController{

    Logger logger = LogManager.getLogger();

    @Autowired
    private ApllicationService apllicationService;

    public ApllicationController(ApllicationService apllicationService) {
        this.apllicationService = apllicationService;

    }

    @GetMapping("/search")
    public Object search(@RequestBody BaseRequest<BaseParameter<RequestModel>> request) {
        BaseResponse response = new BaseResponse<AppModel>();
        if(request.getParameter() == null){
            throw new ApplicationException(Status.INVALID("field parameter is required"));
        }
        if(request.getParameter().getData() == null){
            throw new ApplicationException(Status.INVALID("field data is required"));
        }
        RequestModel requestModel = request.getParameter().getData();
        try {

            response.setResult(apllicationService.search(requestModel));
            response.setStatus(Status.SUCCESS("data has been successfully displayed"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(Status.ERROR(e.getMessage()));
        }
        return response;
    }

    @PostMapping("/export")
    public Object export(@RequestBody BaseRequest<BaseParameter<RequestModel>> request) {
        BaseResponse response = new BaseResponse<AppModel>();
        if(request.getParameter() == null){
            throw new ApplicationException(Status.INVALID("field parameter is required"));
        }
        if(request.getParameter().getData() == null){
            throw new ApplicationException(Status.INVALID("field data is required"));
        }
        RequestModel requestModel = request.getParameter().getData();
        try {
            response.setResult(apllicationService.export(requestModel));
            response.setStatus(Status.SUCCESS("data has been successfully exported"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(Status.ERROR(e.getMessage()));
        }
        return response;
    }

    @GetMapping("/history")
    public Object history(@RequestBody BaseRequest<BaseParameter<RequestModel>> request) {
        BaseResponse response = new BaseResponse<AppModel>();
        if(request.getParameter() == null){
            throw new ApplicationException(Status.INVALID("field parameter is required"));
        }
        if(request.getParameter().getData() == null){
            throw new ApplicationException(Status.INVALID("field data is required"));
        }
        RequestModel requestModel = request.getParameter().getData();
        try {
            response.setResult(apllicationService.history(requestModel));
            response.setStatus(Status.SUCCESS("history data has been successfully displayed"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(Status.ERROR(e.getMessage()));
        }
        return response;
    }

    @PostMapping("/generatelinkdownload")
    public Object download(@RequestBody BaseRequest<BaseParameter<RequestModel>> request) {
        BaseResponse response = new BaseResponse<AppModel>();
        if(request.getParameter() == null){
            throw new ApplicationException(Status.INVALID("field parameter is required"));
        }
        if(request.getParameter().getData() == null){
            throw new ApplicationException(Status.INVALID("field data is required"));
        }
        RequestModel requestModel = request.getParameter().getData();
        try {
            response.setResult(apllicationService.download(requestModel));
            response.setStatus(Status.SUCCESS("the download link has been successfully generated"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(Status.ERROR(e.getMessage()));
        }
        return response;
    }

    @GetMapping("/downloadfile")
    public Object downloadfile(@RequestParam(value = "file", required = false) String stringReq) {
        BaseResponse response = new BaseResponse<AppModel>();
        try {
            apllicationService.downloadfile(stringReq);
            response.setStatus(Status.SUCCESS("file has been successfully downloaded"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(Status.ERROR(e.getMessage()));
        }
        return response;
    }

}

