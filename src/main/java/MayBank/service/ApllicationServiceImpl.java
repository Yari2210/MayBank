package MayBank.service;


import MayBank.config.ApplicationException;
import MayBank.config.DateUtils;
import MayBank.config.GeneratorUtils;
import MayBank.config.Status;
import MayBank.Model.AppModel;
import MayBank.Model.AppModel2;
import MayBank.Model.ItemModel;
import MayBank.Model.RequestModel;
import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;


import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
//@Transactional
public class ApllicationServiceImpl implements ApllicationService {

    Logger logger = LogManager.getLogger(ApllicationServiceImpl.class);

    @Override
    public Object search(RequestModel requestModel) throws IOException, DocumentException {

        if (requestModel.getFind() == null || requestModel.getFind().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field find is required"));
        }
        if (requestModel.getMaxresult() == null) {
            throw new ApplicationException(Status.INVALID("field maxresult is required"));
        }

        HttpClient httpClient = HttpClientBuilder.create().build();
        String apiUrl = "https://api.github.com/search/users?q=" + requestModel.getFind() + "&per_page=" + requestModel.getMaxresult();
        HttpGet request = new HttpGet(apiUrl);
        request.setHeader(HttpHeaders.ACCEPT, "application/vnd.github.v3+json");

        try {
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println(responseBody);

                Gson gson = new Gson();
                AppModel usersArray = gson.fromJson(responseBody, AppModel.class);
                List<Object> users = usersArray.getItems();
                return users;
            } else {
                System.out.println("Request failed with status code: " + statusCode);
                String sc = String.valueOf(statusCode);
                throw new ApplicationException(Status.ERROR("Error statuscode "+ sc));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requestModel;
    }

    @Override
    public Object export(RequestModel requestModel) throws IOException, DocumentException, ParseException {

        if (requestModel.getFind() == null || requestModel.getFind().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field find is required"));
        }
        if (requestModel.getMaxresult() == null) {
            throw new ApplicationException(Status.INVALID("field maxresult is required"));
        }
        if (requestModel.getFilename() == null || requestModel.getFilename().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field filename is required"));
        }

        String projectDirectory = System.getProperty("user.dir");
        String sheetName = "Sheet1";
        String dateString = DateUtils.now().toString();
        String targetFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sourceFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        SimpleDateFormat targetDateFormat = new SimpleDateFormat(targetFormat);
        Date date = sourceFormat.parse(dateString);
        String formattedDate = targetDateFormat.format(date);
        String id = GeneratorUtils.GenerateId("", DateUtils.now(), 5);
        String[] rowData = {id, requestModel.getFind(), requestModel.getMaxresult().toString(), requestModel.getFilename(), formattedDate, "System"};
        String filePath = projectDirectory + File.separator + "History.xlsx";

        HttpClient httpClient = HttpClientBuilder.create().build();
        String apiUrl = "https://api.github.com/search/users?q=" + requestModel.getFind() + "&per_page=" + requestModel.getMaxresult();
        HttpGet request = new HttpGet(apiUrl);
        request.setHeader(HttpHeaders.ACCEPT, "application/vnd.github.v3+json");

        try {
            HttpResponse response = httpClient.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println(responseBody);

                Gson gson = new Gson();
                AppModel2 usersArray = gson.fromJson(responseBody, AppModel2.class);
                List<ItemModel> users = usersArray.getItems();

                Document document = new Document();

                PdfWriter.getInstance(document, new FileOutputStream(requestModel.getFilename() + ".pdf"));

                document.open();

                for (ItemModel user : users) {
                    document.add(new Paragraph("Username: " + user.getLogin()));
                }

                document.close();

            } else {
                System.out.println("Request failed with status code: " + statusCode);
                String sc = String.valueOf(statusCode);
                throw new ApplicationException(Status.ERROR("Error statuscode "+ sc));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("PDF exported successfully!");

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            int lastRowNum = sheet.getLastRowNum();

            for (int i = 1; i <= lastRowNum; i++) {
                Cell cell = sheet.getRow(i).getCell(3);
                String cl = cell.getStringCellValue();
                if (cl.equals(requestModel.getFilename())) {
                    throw new ApplicationException(Status.INVALID("filename Already Exist"));
                }
            }

            Row newRow = sheet.createRow(lastRowNum + 1);

            for (int i = 0; i < rowData.length; i++) {
                Cell cell = newRow.createCell(i);
                cell.setCellValue(rowData[i]);
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
                System.out.println("Data added to Excel file successfully.");
            } catch (IOException e) {
                System.out.println("Error writing to the file");
//                throw new ApplicationException(Status.INVALID("Error writing to the file"));
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Error reading the Excel file.");
//            throw new ApplicationException(Status.INVALID("Error reading the Excel file"));
            e.printStackTrace();
        }

        return requestModel;
    }

    @Override
    public Object history(RequestModel requestModel) throws IOException, DocumentException {

        String projectDirectory = System.getProperty("user.dir");
        String sheetName = "Sheet1";
        String filePath = projectDirectory + File.separator + "History.xlsx";
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int lastRowNum = sheet.getLastRowNum();
        List<RequestModel> list = new ArrayList<>();

        for (int i = 1; i <= lastRowNum; i++) {
            RequestModel tampung = new RequestModel();
            tampung.setId(sheet.getRow(i).getCell(0).getStringCellValue());
            tampung.setFind(sheet.getRow(i).getCell(1).getStringCellValue());
            String mr = sheet.getRow(i).getCell(2).getStringCellValue();
            tampung.setMaxresult(Integer.parseInt(mr));
            tampung.setFilename(sheet.getRow(i).getCell(3).getStringCellValue());
            tampung.setCreateddate(sheet.getRow(i).getCell(4).getStringCellValue());
            tampung.setCreatedby(sheet.getRow(i).getCell(5).getStringCellValue());
            list.add(tampung);
        }

        return list;
    }

    @Override
    public Object download(RequestModel requestModel) throws IOException {

        if(requestModel.getId() == null || requestModel.getId().trim().equals("")){
            throw new ApplicationException(Status.INVALID("field id is required"));
        }

        if(requestModel.getTargetpath() == null || requestModel.getTargetpath().trim().equals("")){
            throw new ApplicationException(Status.INVALID("field targetpath is required"));
        }

        String projectDirectory = System.getProperty("user.dir");
        String sheetName = "Sheet1";
        String filePath = projectDirectory + File.separator + "History.xlsx";
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int lastRowNum = sheet.getLastRowNum();
        Boolean cek = false;
        String fl = "";

        for (int i = 1; i <= lastRowNum; i++) {
            Cell cell = sheet.getRow(i).getCell(0);
            String cl = cell.getStringCellValue();
            if(cl.equals(requestModel.getId())){
                cek = true;
                Cell cell2 = sheet.getRow(i).getCell(3);
                String cl2 = cell2.getStringCellValue();
                fl = cl2;
            }
        }

        if (cek.equals(false)) {
            throw new ApplicationException(Status.DATA_NOT_FOUND("data not found"));
        }

        String toLocation = "";
        char lastCharacter = requestModel.getTargetpath().charAt(requestModel.getTargetpath().length() - 1);
        String str = Character.toString(lastCharacter);
        if(str.equals("/")){
            toLocation = requestModel.getTargetpath() + fl+ ".pdf";
        }else{
            toLocation = requestModel.getTargetpath() + "/" + fl+ ".pdf";
        }

        String fileLocation = projectDirectory + File.separator + fl+ ".pdf";
        String downloadLink = generateDownloadLink(fileLocation, toLocation);
        System.out.println("Download Link: " + downloadLink);

        return downloadLink;
    }

    public static String generateDownloadLink(String fileLocation, String tolocation) throws UnsupportedEncodingException {
        String baseUrl = "http://localhost:8080/appfuxion/java/challenge/v1.0";
        String downloadPath = "/maybank/downloadfile";
        String fileUrl = baseUrl + downloadPath + "?file=!" + encodeURL(fileLocation) + "!" + encodeURL(tolocation);
        return fileUrl;
    }

    public static String encodeURL(String url) {
        try {
            return URLEncoder.encode(url, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error encoding URL: " + e.getMessage());
        }
    }

    @Override
    public Object downloadfile(String requestModel) throws IOException {

        String decode = URLDecoder.decode(requestModel, StandardCharsets.UTF_8.toString());
        String d = decode;
        String[] sentences = d.split("[!?]");

        List<String> tampung = new ArrayList<>();
        for (String sentence : sentences) {
            System.out.println(sentence.trim());
            tampung.add(sentence.trim());
        }

        String projectDirectory = System.getProperty("user.dir");
        String sheetName = "Sheet1";
        String filePath = projectDirectory + File.separator + "History.xlsx";

        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        String sourceFilePath = tampung.get(1);
        String targetFilePath = tampung.get(2);

        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(targetFilePath);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            fileInputStream.close();
            fileOutputStream.close();
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while copying the file: " + e.getMessage());
        }

        return requestModel;
    }

}
