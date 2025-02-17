/**
 *
 */
package com.CodeLearner.HomeTenant.global;

/**
 * @author Franck Sgen Lecroyant
 */
public class DeleteOperationResponse {

    private boolean deleted;

    public DeleteOperationResponse(boolean deleted) {
        this.deleted = deleted;
    }

    public static DeleteOperationResponse deleteCompletedSuccessfully() {
        return new DeleteOperationResponse(true);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
