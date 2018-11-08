package com.contaazul.boleto.services.impl;

import com.contaazul.boleto.beans.BoletoBean;
import com.contaazul.boleto.entities.Boleto;
import com.contaazul.boleto.exceptions.NoResultExceptionApi;
import com.contaazul.boleto.repositories.BoletoRepository;
import com.contaazul.boleto.services.BoletoService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
public class BoletoServiceImpl implements BoletoService {

    private static final String NO_FOUND_MESSAGE = "Bankslip not found with the specified id.";

    @Autowired
    private BoletoRepository boletoRepository;

    @Override
    public List<BoletoBean> listarBoletos() {
        log.info("Listando todos os boletos...");
        return boletoRepository.findAll().stream().map(boleto -> converter(boleto)).collect(Collectors.toList());
    }

    @Override
    public BoletoBean criarBoleto(BoletoBean boletoBean) {
        log.info("Criando um novo Boleto...");
        boletoBean.setTotalInCents(String.format("%.2f", Double.valueOf("1") / 100.0));
//        boletoBean.setTotalInCents(formatNumberDecimal(boletoBean.getTotalInCents()));
        System.out.println(boletoBean.getTotalInCents());
        return converter(boletoRepository.save(converter(boletoBean)));
    }

    @Override
    public BoletoBean detalhesBoleto(@NonNull String id) {
        log.info("Buscando um boleto...");
        Optional<Boleto> data = boletoRepository.findById(UUID.fromString(id));
        return data.map(b -> converter(calcularJuros(data.get()))).orElseThrow(() -> new NoResultExceptionApi(NO_FOUND_MESSAGE));
    }

    @Override
    public void marcarComoPago(@NonNull String id) {

    }

    public static void main(String[] args) {
        System.out.println();
    }

    /**
     * Formatar numero para decimal 2 casa
     *
     * @param valor
     * @return
     */
    private String formatNumberDecimal(String valor) {
        return new DecimalFormat("######0.##").format(Double.valueOf(valor));
    }

    /**
     * Faz o calculo de juros do boleto
     *
     * @param boleto
     * @return
     */
    private Boleto calcularJuros(Boleto boleto) {
        Long diffDias = ChronoUnit.DAYS.between(boleto.getDueDate(), LocalDate.now());
        boleto.setTotalInCents(
                calculaJuros(boleto.getTotalInCents(), diffDias <= 10 ? 0.5D : 1.0, diffDias.intValue())
        );
        return boleto;
    }

    /**
     * Calcular juros simples.
     *
     * @param valor
     * @param juros
     * @param dias
     * @return
     */
    private BigDecimal calculaJuros(BigDecimal valor, double juros, int dias) {
        return new BigDecimal(valor.doubleValue() * (1 + (juros / 100) * dias));
    }

    /**
     * Converter Bean para Entidade
     *
     * @param boleto
     * @return
     */
    private Boleto converter(BoletoBean boleto) {
        return Boleto.builder().id(Optional.ofNullable(boleto.getId()).isPresent() ? UUID.fromString(boleto.getId()) : null)
                .dueDate(boleto.getDueDate())
                .totalInCents(new BigDecimal(boleto.getTotalInCents()))
                .customer(boleto.getCustomer())
                .paymentDate(boleto.getPaymentDate())
                .status(boleto.getStatus())
                .build();
    }

    /**
     * Converter Entidade para Bean
     *
     * @param boleto
     * @return
     */
    private BoletoBean converter(Boleto boleto) {
        return BoletoBean.builder().id(boleto.getId().toString())
                .dueDate(boleto.getDueDate())
                .totalInCents(String.valueOf(boleto.getTotalInCents().intValue()))
                .customer(boleto.getCustomer())
                .paymentDate(boleto.getPaymentDate())
                .status(boleto.getStatus())
                .build();
    }

    /*@Override
    public List<BoletoBean> listarBoletos() {
        log.info("Listando todos os boletos...");
        return boletoRepository.findAll().stream().map(boleto -> toBean(boleto)).collect(Collectors.toList());
    }

    @Override
    public BoletoBean detalhesBoleto(long id) {
        log.info("Pesquisando boleto por ID: ", id, "...");
        Optional<Boleto> data = boletoRepository.findById(id);
        return data.map(b -> toBean(data.get())).orElseThrow(() -> new NoResultExceptionApi(NO_FOUND_MESSAGE));
    }

    @Override
    public BoletoBean criarBoleto(BoletoBean boletoBean) {
        return null;
    }

    @Override
    public void update(BoletoBean boletoBean) {

    }

    @Override
    public void delete(Long id) {

    }

    */
}
