package br.com.assertiva.comunika.exception;

public class BadRequestException extends Throwable{
    public BadRequestException(String erro) {
        super(erro);
    }
}
