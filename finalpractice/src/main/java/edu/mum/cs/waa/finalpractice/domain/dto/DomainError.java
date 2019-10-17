package edu.mum.cs.waa.finalpractice.domain.dto;

public class DomainError {
    private String message;
    private String field;

    public DomainError() {}
    public DomainError(String field, String message) {
        this.field = field;
        this.message = message;
    }
    public DomainError(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage( ) {
        return message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
