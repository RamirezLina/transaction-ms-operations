package com.transactionmgmt.operations.service.exception;

public class BusinessException extends RuntimeException {

    public enum Type{
        ACCOUNT_ALREADY_EXISTS("El número de cuenta ya existe"),
        ACCOUNT_NOT_EXISTS("La cuenta no existe"),
        MOVEMENT_NOT_EXISTS("El movimiento no existe");
        
        private final String message;
        
        public BusinessException build(){
            return new BusinessException(this);
        }

        Type(String message) {
            this.message = message;
        }
    }
    
    private final BusinessException.Type type;
    
    private BusinessException(BusinessException.Type type){
        super(type.message);
        this.type = type;
    }
}
