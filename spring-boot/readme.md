# API Boleto ContaAzul
##### Desafio:
O objetivo do desafio é construir uma API REST para geração de boletos que será consumido por um módulo de um sistema de gestão financeira de microempresas.
Desafio foi proposto pela **Pamêlla Hess** da empresa [ContaAzul.com](https://contaazul.com/) tendo como documento com as especificações: [aqui](https://drive.google.com/file/d/1DvjRBTvnHwlUOoNBwAsvoRF6aKqYm7pP/view)

## Tecnologias usadas
![](https://pbs.twimg.com/media/DU7GUGCV4AAf90X.jpg =200x32)
![](https://blogs.plos.org/tech/files/2018/03/swagger_logo2-690x244.png =200x)
![](https://avatars2.githubusercontent.com/u/11459762?s=280&v=4 =70x)




* permitir que somente boletos pendentes poderiam calcular seu juros, mas deve salvar o valor pago no momento que altere seu staus para PAID.

* No momento do pagamento do boleto deve diser quanto foi pago, pois não sei possivel saber. sendo que altera o STATUS mas não calcula o valor e não sava o valor que deveria ser pago.


* Não ficou claro se é permitido cancelar o boleto mesmo depois de pago. Cancelado é cancelado, não é estornado.


* No momento de ver os detalhes do boleto tem um coluna FINE, não existe documento para saber seu objetivo.

###separação da interface do swagger
http://localhost:8080/rest/swagger-ui.html



Hamcrest
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTkyNDE3NzAxNSwtMjc3ODM5NTg1XX0=
-->