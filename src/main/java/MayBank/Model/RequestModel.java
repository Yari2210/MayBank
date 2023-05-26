package MayBank.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestModel {

    @JsonProperty("id")
    private String id;

    @JsonProperty("find")
    private String find;

    @JsonProperty("maxresult")
    private Integer maxresult;

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("createddate")
    private String createddate;

    @JsonProperty("createdby")
    private String createdby;

    @JsonProperty("targetpath")
    private String targetpath;

    public String getFind() {
        return find;
    }

    public void setFind(String find) {
        this.find = find;
    }

    public Integer getMaxresult() {
        return maxresult;
    }

    public void setMaxresult(Integer maxresult) {
        this.maxresult = maxresult;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getTargetpath() {
        return targetpath;
    }

    public void setTargetpath(String targetpath) {
        this.targetpath = targetpath;
    }
}
