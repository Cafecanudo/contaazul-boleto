<h1 id="api-boleto-contaazul">API Boleto ContaAzul</h1>

<a href="https://travis-ci.com/Cafecanudo/contaazul-boleto">
  <img src="https://travis-ci.com/Cafecanudo/contaazul-boleto.svg?branch=master" alt="Build Status">
</a>
<a href="https://sonarcloud.io/dashboard/index/key.com.contaazul.boletos">
  <img src="https://sonarcloud.io/api/project_badges/measure?project=key.com.contaazul.boletos&metric=ncloc">
</a>
<a href="https://sonarcloud.io/dashboard/index/key.com.contaazul.boletos">
  <img src="https://sonarcloud.io/api/project_badges/measure?project=key.com.contaazul.boletos&metric=alert_status">
</a>
<a href="https://sonarcloud.io/dashboard/index/key.com.contaazul.boletos">
  <img src="https://sonarcloud.io/api/project_badges/measure?project=key.com.contaazul.boletos&metric=code_smells">
</a>
<a href="https://sonarcloud.io/dashboard/index/key.com.contaazul.boletos">
  <img src="https://sonarcloud.io/api/project_badges/measure?project=key.com.contaazul.boletos&metric=duplicated_lines_density">
</a>
<a href="https://sonarcloud.io/dashboard/index/key.com.contaazul.boletos">
  <img src="https://sonarcloud.io/api/project_badges/measure?project=key.com.contaazul.boletos&metric=bugs">
</a>
<a href="https://sonarcloud.io/dashboard/index/key.com.contaazul.boletos">
  <img src="https://sonarcloud.io/api/project_badges/measure?project=key.com.contaazul.boletos&metric=vulnerabilities">
</a>
<p>O objetivo do desafio é construir uma API REST para geração de boletos que será consumido por um módulo de um sistema de gestão financeira de microempresas.<br>
Desafio foi proposto pela <strong>Pamêlla Hess</strong> da empresa <a href="https://contaazul.com/">ContaAzul.com</a> tendo como documento com as especificações: <a href="https://drive.google.com/file/d/1DvjRBTvnHwlUOoNBwAsvoRF6aKqYm7pP/view">aqui</a></p>
<h2 id="executando">Executando</h2>
<h5 id="para-fazer-install-das-libs">Para fazer install das LIBs</h5>
<pre><code>mvn clean install -f pom.xml 
</code></pre>
<h5 id="executando-a-aplicação">Executando a aplicação</h5>
<pre><code>mvn spring-boot:run
</code></pre>
<h5 id="executando-a-aplicação">Executando teste de avaliação com SonarCube</h5>
<pre>
<code>mvn sonar: sonar \
  -Dsonar.projectKey = contaazul \
  -Dsonar.organization = cafecanudo-github \
  -Dsonar.host.url = https: //sonarcloud.io \
  -Dsonar.login = ccdb1d261679f21e9ab3cdb3724ef3f05c45f29e
</code>
</pre>
<h2 id="interface-de-testedocumentação">Interface de Teste/Documentação</h2>
<p>Documentacao da API <a href="http://localhost:8080/rest/swagger-ui.html">http://localhost:8080/rest/swagger-ui.html</a></p>
<h2 id="coverage">Coverage</h2>
<h2 id="observações-e-melhorias-sobre-a-documentaçãoregras">Observações e melhorias sobre a documentação(Regras)</h2>
<p>
  <img src="images/unidade-test.png">
<img src="images/coverage.png">
</p>
<ul>
<li>Não esta na documentação, porém, a implementação permite somente que boletos PENDING possam ser calculados os juros.</li>
<li>Seria importante salvar o valor pago pelo BOLETO no momento que alterar seu STATUS para PAID, sem isso não será possível recuperar essa informação.</li>
<li>Não ficou claro se é permitido cancelar o boleto mesmo depois de pago. Cancelado é cancelado, não é estornado.</li>
<li>No momento de ver os detalhes do boleto tem um coluna FINE, não existe no documento seu objetivo.</li>
</ul>
<h2 id="tecnologias">Tecnologias</h2>
<p><img src="https://pbs.twimg.com/media/DU7GUGCV4AAf90X.jpg" alt="" width="200">    <img src="https://blogs.plos.org/tech/files/2018/03/swagger_logo2-690x244.png" alt="" width="200">    <img src="https://avatars2.githubusercontent.com/u/11459762?s=280&amp;v=4" alt="" width="70">  <img src="https://miro.medium.com/max/1400/1*AiTBjfsoj3emarTpaeNgKQ.png" alt="enter image description here" width="200">  <img src="http://hibernate.org/images/hibernate-logo.svg" alt="enter image description here" width="200"> <img src="https://i2.wp.com/www.codeatest.com/wp-content/uploads/2016/11/mockito-logo.png" alt="enter image description here" width="200"> <img src="https://engenharia.elo7.com.br/images/travis-build-stages-1.png" alt="enter image description here" width="200"><img 
src="https://www.sonarsource.com/assets/sonarsource-black-logo-05ca896462d08cc54739bf42e27c778071bdd67301041e4ebcc9206635fe1922.svg" width="200"/></p>

