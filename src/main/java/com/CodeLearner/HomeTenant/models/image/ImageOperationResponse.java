package com.CodeLearner.HomeTenant.models.image;

/**
 * @author ND. Eric
 */
public class ImageOperationResponse {

    private String operation;
    private Response status;
    private String urlProfile;

    /**
     *
     */
    public ImageOperationResponse() {

    }

    /**
     * @param operation
     * @param status
     * @param urlProfile
     */
    public ImageOperationResponse(String operation, Response status, String urlProfile) {
        this.operation = operation;
        this.status = status;
        this.urlProfile = urlProfile;
    }

    public static Response successResponse() {
        return Response.SUCCESS;
    }

    public static Response failureResponse() {
        return Response.FAILED;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Response getStatus() {
        return status;
    }

    public void setStatus(Response status) {
        this.status = status;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    enum Response {
        SUCCESS, FAILED
    }


}
