package com.contaazul.boleto.services;

import com.contaazul.boleto.beans.BoletoBean;

import java.util.List;

public interface BoletoService {

    List<BoletoBean> listarBoletos();

    BoletoBean criarBoleto(BoletoBean boletoBean);

    BoletoBean detalhesBoleto(String id);

    void marcarComoPago(String id);

    /*
    void update(BoletoBean boletoBean);

    void delete(Long id);*/
}
