# tamagotchi-client-server

Desenvolvido por: Ana Beatriz Stahl, Emanuele Schlemmer, Gabriela Bley, Kelly Natasha Fernandes e Luisa Becker. Para a cadeira de Redes de Computadores: Aplicação e Transporte, no segundo semestre de 2024.

## Descrição do Trabalho
- desenvolver uma aplicação baseado em uma arquitetura cliente/servidor e
  fazer com que as entidades envolvidas (cliente e servidor) troquem mensagens utilizando os
  conceitos de programação em sockets
- A aplicação necessariamente deverá envolver a presença de um servidor e de pelo menos
  dois clientes. Opções:
  - fazer que o servidor seja apenas um “negociador” da
    comunicação entre os clientes (por exemplo, executando funções de autenticação, controle
    de clientes ativos no sistema, etc.) e que depois a comunicação flua diretamente entre os
    clientes
  - fazer que o servidor seja um participante ativo de todas as
    mensagens trocadas entre os clientes (recebendo as mensagens de um determinado cliente
    e encaminhando essa mensagem para os demais participantes)
- A linguagem de programação e a biblioteca a ser utilizada é de livre escolha

## Conceito do Projeto
- Servidor:
  - Faz o intermédio entre as ações dos clientes e os tamagotchis
  - Consegue mandar mensagem para todos os clientes
- Cliente:
  - Ao conectar no servidor deve escolher um nome para o seu tamagotchi
  - Recebe atualização de todos os tamagotchis do servidor, e pode interagir com o tamagotchi de outros clientes
- Tamagotchi:
  - Consiste de um objeto que possui atributos como nome, idade, fome, saude, energia, e felicidade
  - Possui um scheduler que a cada 10 segundos (valor que pode ser alterado) atualiza as necessidades do tamagotchi
  - As necessidades do tamagotchi são decrementadas em valores aleatórios