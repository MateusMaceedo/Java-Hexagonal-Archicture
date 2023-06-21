package com.example.springboot.domain.exceptions;

import java.util.List;

public class DomainException extends RuntimeException {

    private int httpsStatus;
    private String httpMethod;
    private String mensagem;
    private List<Campo> campos;

    public DomainException(int httpsStatus, String httpMethod, String mensagem, List<Campo> campos) {
        this.httpsStatus = httpsStatus;
        this.httpMethod = httpMethod;
        this.mensagem = mensagem;
        this.campos = campos;
    }

    public int getHttpStatus() {
        return httpsStatus;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getMensagem() {
        return mensagem;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public static class Campo {
        private String campo;
        private String mensagem;
        private String valor;

        public Campo(String campo, String mensagem, String valor) {
            this.campo = campo;
            this.mensagem = mensagem;
            this.valor = valor;
        }

        public String getCampo() {
            return campo;
        }

        public String getMensagem() {
            return mensagem;
        }

        public String getValor() {
            return valor;
        }
    }
}
