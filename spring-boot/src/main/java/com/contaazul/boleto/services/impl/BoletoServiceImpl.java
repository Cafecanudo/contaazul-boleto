package com.contaazul.boleto.services.impl;

import com.contaazul.boleto.beans.BoletoBean;
import com.contaazul.boleto.entities.Boleto;
import com.contaazul.boleto.exceptions.NoResultExceptionApi;
import com.contaazul.boleto.repositories.BoletoRepository;
import com.contaazul.boleto.services.BoletoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class BoletoServiceImpl implements BoletoService {

    private static final String NO_FOUND_MESSAGE = "Bankslip not found with the specified id.";

    @Autowired
    private BoletoRepository boletoRepository;

    @Override
    public List<BoletoBean> findAll() {
        log.info("Listando todos os boletos...");
        return boletoRepository.findAll().stream().map(boleto -> toBean(boleto)).collect(Collectors.toList());
    }

    @Override
    public BoletoBean findById(long id) {
        log.info("Pesquisando boleto por ID: ", id, "...");
        Optional<Boleto> data = boletoRepository.findById(id);
        return data.map(b -> toBean(data.get())).orElseThrow(() -> new NoResultExceptionApi(NO_FOUND_MESSAGE));
    }

    @Override
    public BoletoBean append(BoletoBean boletoBean) {
        return null;
    }

    @Override
    public void update(BoletoBean boletoBean) {

    }

    @Override
    public void delete(Long id) {

    }

    private BoletoBean toBean(Boleto boleto) {
        return new BoletoBean(boleto.getId(), boleto.getDueDate(), String.valueOf(boleto.getTotalInCents().intValue()),
                boleto.getCustomer(), boleto.getStatus());
    }
}
