package com.contaazul.boleto.services;

import com.contaazul.boleto.beans.BoletoBean;

import java.util.List;

public interface BoletoService {

    List<BoletoBean> findAll();

    BoletoBean findById(long id);

    BoletoBean append(BoletoBean boletoBean);

    void update(BoletoBean boletoBean);

    void delete(Long id);
}
