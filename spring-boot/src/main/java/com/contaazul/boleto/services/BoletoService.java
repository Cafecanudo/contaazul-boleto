package com.contaazul.boleto.services;

import com.contaazul.boleto.beans.BoletoBean;
import com.contaazul.boleto.exceptions.UnprocessableEntityException;

import java.time.LocalDate;
import java.util.List;

public interface BoletoService {

    List<BoletoBean> listarBoletos();

    BoletoBean criarBoleto(BoletoBean boletoBean);

    BoletoBean detalhesBoleto(String id);

    void pagarBoleto(String id, LocalDate data) throws UnprocessableEntityException;

    void cancelarBoleto(String id);

}
