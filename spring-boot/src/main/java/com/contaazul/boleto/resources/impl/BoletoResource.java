package com.contaazul.boleto.resources.impl;

import com.contaazul.boleto.beans.BoletoBean;
import com.contaazul.boleto.resources.IBoletoResource;
import com.contaazul.boleto.services.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/bankslips")
public class BoletoResource implements IBoletoResource {

    @Autowired
    private BoletoService boletoService;

    @GetMapping
    @ResponseBody
    public List<BoletoBean> listarBoletos() {
        return boletoService.listarBoletos();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public BoletoBean criarBoleto(@Valid @RequestBody BoletoBean boletoBean) {
        return boletoService.criarBoleto(boletoBean);
    }

    @GetMapping("/{id}")
    public BoletoBean detalhesBoleto(@NotNull(message = "Id required") @PathVariable String id) {
        return boletoService.detalhesBoleto(id);
    }

    @PostMapping(value = "/{id}/payments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void pagarBoleto(@NotNull(message = "Id required") @PathVariable String id,
                            @Valid @RequestBody HashMap<String, LocalDate> requestData) {
        boletoService.pagarBoleto(id, requestData.get("payment_date"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void cancelarBoleto(@NotNull(message = "Id required") @PathVariable String id) {
        boletoService.cancelarBoleto(id);
    }
}
