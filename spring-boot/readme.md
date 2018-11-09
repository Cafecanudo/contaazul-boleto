---


---

<h1 id="api-boleto-contaazul">API Boleto ContaAzul</h1>
<p>O objetivo do desafio é construir uma API REST para geração de boletos que será consumido por um módulo de um sistema de gestão financeira de microempresas.<br>
Desafio foi proposto pela <strong>Pamêlla Hess</strong> da empresa <a href="https://contaazul.com/">ContaAzul.com</a> tendo como documento com as especificações: <a href="https://drive.google.com/file/d/1DvjRBTvnHwlUOoNBwAsvoRF6aKqYm7pP/view">aqui</a></p>
<h2 id="executando">Executando</h2>
<pre><code># Para fazer install das LIBs
mvn clean install -f pom.xml 

# Executando a aplicação
mvn spring-boot:run
</code></pre>
<h2 id="interface-de-testedocumentação">Interface de Teste/Documentação</h2>
<p>Documentacao da API <a href="http://localhost:8080/rest/swagger-ui.html">http://localhost:8080/rest/swagger-ui.html</a></p>
<h2 id="observações-e-melhorias-sobre-a-documentaçãoregras">Observações e melhorias sobre a documentação(Regras)</h2>
<ul>
<li>Não esta na documentação, porém, a implementação permite somente que boletos PENDING possam ser calculados os juros.</li>
<li>Seria importante salvar o valor pago pelo BOLETO no momento que alterar seu STATUS para PAID, sem isso não será possível recuperar essa informação.</li>
<li>Não ficou claro se é permitido cancelar o boleto mesmo depois de pago. Cancelado é cancelado, não é estornado.</li>
<li>No momento de ver os detalhes do boleto tem um coluna FINE, não existe no documento seu objetivo.</li>
</ul>
<h2 id="tecnologias">Tecnologias</h2>
<p><img src="https://pbs.twimg.com/media/DU7GUGCV4AAf90X.jpg" alt="" width="200">    <img src="https://blogs.plos.org/tech/files/2018/03/swagger_logo2-690x244.png" alt="" width="200">    <img src="https://avatars2.githubusercontent.com/u/11459762?s=280&amp;v=4" alt="" width="70">  <img src="https://miro.medium.com/max/1400/1*AiTBjfsoj3emarTpaeNgKQ.png" alt="enter image description here" width="200">  <img src="http://hibernate.org/images/hibernate-logo.svg" alt="enter image description here" width="200"> <img src="https://i2.wp.com/www.codeatest.com/wp-content/uploads/2016/11/mockito-logo.png" alt="enter image description here" width="200"> <img src="https://engenharia.elo7.com.br/images/travis-build-stages-1.png" alt="enter image description here" width="200"></p>

