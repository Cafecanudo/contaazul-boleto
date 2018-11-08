package com.contaazul.boleto.services.impl;

import com.contaazul.boleto.beans.BoletoBean;
import com.contaazul.boleto.entities.Boleto;
import com.contaazul.boleto.entities.enums.StatusEnum;
import com.contaazul.boleto.exceptions.NoResultExceptionApi;
import com.contaazul.boleto.repositories.BoletoRepository;
import com.contaazul.boleto.services.BoletoService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
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
        boletoBean.setTotalInCents(String.valueOf(Double.valueOf(boletoBean.getTotalInCents()) / 100.0));
        return converter(boletoRepository.save(converter(boletoBean)));
    }

    @Override
    public BoletoBean detalhesBoleto(@NonNull String id) {
        log.info("Buscando um boleto...");
        return converter(calcularJuros(findById(id)));
    }

    @Override
    public void pagarBoleto(@NonNull String id, @NonNull LocalDate data) {
        Boleto boleto = findById(id);
        boleto.setStatus(StatusEnum.PAID);
        boleto.setPaymentDate(data);
        boletoRepository.save(boleto);
    }

    @Override
    public void cancelarBoleto(@NonNull String id) {
        Boleto boleto = findById(id);
        boleto.setStatus(StatusEnum.CANCELED);
        boletoRepository.save(boleto);
    }

    /**
     * Buscar um boleto com ID especifico
     *
     * @param id
     * @return
     */
    private Boleto findById(@NonNull String id) {
        Optional<Boleto> data = boletoRepository.findById(UUID.fromString(id));
        return data.map(b -> data.get()).orElseThrow(() -> new NoResultExceptionApi(NO_FOUND_MESSAGE));
    }

    /**
     * Faz o calculo de juros do boleto
     *
     * @param boleto
     * @return
     */
    private Boleto calcularJuros(Boleto boleto) {
        Long diffDias = ChronoUnit.DAYS.between(boleto.getDueDate(), LocalDate.now());

        //Verifica se boleto ainda está pendente para poder calcular seu juros.
        if (boleto.getStatus().equals(StatusEnum.PENDING)) {
            boleto.setTotalInCents(
                    calculaJuros(boleto.getTotalInCents(), diffDias <= 10 ? 0.5D : 1.0, diffDias.intValue())
            );
        }
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
        return BigDecimal.valueOf(valor.doubleValue() * (1 + (juros / 100.0) * dias)).round(MathContext.DECIMAL32).setScale(2);
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
                .fine(boleto.getFine())
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
                .totalInCents(boleto.getTotalInCents().unscaledValue().toString())
                .customer(boleto.getCustomer())
                .paymentDate(boleto.getPaymentDate())
                .status(boleto.getStatus())
                .fine(boleto.getFine())
                .build();
    }
}
