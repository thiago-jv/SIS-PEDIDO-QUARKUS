package org.cliente.api.v1.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalheMensagem {

    private String mensagem;
    private int status;
    private String dataHora;

    public DetalheMensagem(String mensagem, int status, String dataHora) {
        this.mensagem = mensagem;
        this.status = status;
        this.dataHora = dataHora;
    }
}

