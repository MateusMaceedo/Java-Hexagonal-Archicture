# Java-Hexagonal-Archicture
Arquitetura Hexagonal em Java.

#### Aplicação Local

> Para subir a app local, digite o seguinte comando no terminal:
- mvn spring-boot:run

> Para executar o Prometheus, digite o seguinte comando no terminal:
- docker-compose up

#### SRE

> Para acessar a rota de metricas do Prometheus, acessar a seguinte URL:
- http://localhost:{portadaaplicacao}/actuator/prometheus

> Para acessar o dash do Prometheus, acessar a seguinte URL:
- http://localhost:{portadaaplicacao}/

> Para acessar o dash do Grafana, acessar a seguinte URL:
- http://localhost:3000

#### Local Stack

> Para rodar o SQS no docker-compose, digitar o seguinte comando:
- docker-compose -f local-stack-docker-compose.yml up -d

Para acessar o dash do SQS, acesse a URL:
- http://localhost:9325
