package com.contaazul.boleto.resources;

import com.contaazul.boleto.beans.BoletoBean;
import com.contaazul.boleto.services.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boletos")
public class BoletoResource {

    @Autowired
    private BoletoService boletoService;

    @GetMapping
    public List<BoletoBean> findAll() {
        return boletoService.findAll();
    }

    @GetMapping("/{id}")
    public BoletoBean findById(@PathVariable Long id) {
        return boletoService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public BoletoBean append(@RequestBody BoletoBean boletoBean) {
        return boletoService.append(boletoBean);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody BoletoBean boletoBean) {
        boletoService.update(boletoBean);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boletoService.delete(id);
    }
}
