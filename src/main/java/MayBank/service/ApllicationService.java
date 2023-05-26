package MayBank.service;


import MayBank.Model.RequestModel;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.text.ParseException;

public interface ApllicationService  {

    Object search(RequestModel appModel) throws IOException, DocumentException;
    Object export(RequestModel appModel) throws IOException, DocumentException, ParseException;
    Object history(RequestModel appModel) throws IOException, DocumentException, ParseException;
    Object download(RequestModel appModel) throws IOException;
    Object downloadfile(String appModel) throws IOException;

}
