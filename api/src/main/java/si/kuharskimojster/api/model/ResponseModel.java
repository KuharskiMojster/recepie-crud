package si.kuharskimojster.api.model;

public class ResponseModel {

    private String body;
    private int statusCode;

    public ResponseModel(String body, int statusCode) {
        this.body = body;
        this.statusCode = statusCode;
    }

    public ResponseModel(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
