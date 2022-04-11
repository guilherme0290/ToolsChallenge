
## Criar uma transacao
- Metodo: POST
- url base: localhost:8080
- endpoint: /transacao
- requerido body: true

## Estorno de uma transacao
- Metodo: PUT 
- url base: localhost:8080
- endpoint: /transacao/estorno/{id}
- requerido body: false

## Observação
Neste projeto estou utilizando um banco de dados carregado na memoria (H2 database).
Segue parametros de conexão caso queira fazer um select.
Após o Tomcat subir basta acessar localhost:8080/h2-console






