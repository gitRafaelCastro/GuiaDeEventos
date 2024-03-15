# Projeto Eventos na Cidade

## Descrição
Este projeto é um sistema desenvolvido em Java para cadastro e notificação de eventos que ocorrem na cidade em que o usuário reside. O sistema permite que os usuários cadastrem-se, cadastrem eventos, consultem eventos cadastrados, confirmem participação em eventos, cancelem a participação, e recebam notificações sobre eventos próximos.

## Funcionalidades
- Cadastro de usuários: Os usuários podem se cadastrar no sistema, fornecendo informações como nome, email e telefone.
- Cadastro de eventos: Os usuários podem cadastrar eventos, fornecendo informações como nome, endereço, categoria, horário e descrição.
- Consulta de eventos: Os usuários podem consultar os eventos cadastrados e decidir participar de qualquer um listado.
- Confirmação de participação: Os usuários podem confirmar sua participação em eventos listados.
- Cancelamento de participação: Os usuários podem cancelar sua participação em eventos confirmados.
- Ordenação de eventos: Os eventos são ordenados pelo horário, mostrando primeiro os eventos mais próximos.
- Notificações de eventos: O sistema informa os eventos que estão ocorrendo no momento e também os eventos que já ocorreram.

![image](https://github.com/gitRafaelCastro/GuiaDeEventos/assets/161896808/96d5f93f-f267-44c2-b10b-a94bad59c760)

![image](https://github.com/gitRafaelCastro/GuiaDeEventos/assets/161896808/34f0df1d-355a-4930-873b-e23aa5565e25)

![image](https://github.com/gitRafaelCastro/GuiaDeEventos/assets/161896808/b9797b2f-a3c4-4be5-a8d1-34f49d9f6073)

## Estrutura do Projeto
O projeto segue o paradigma de programação orientada a objetos e foi organizado de forma simples, sem a utilização de padrões arquiteturais como MVC. No entanto, a estruturação do projeto em um repositório Git foi implementada. A estrutura de classes é representada no diagrama a seguir:

![Diagrama de Classes](https://github.com/gitRafaelCastro/GuiaDeEventos/assets/161896808/2d7ce9dd-dcca-4034-bf40-f39f15cf9263)

## Persistência de Dados
As informações dos eventos são salvas em um arquivo `events.data`. Toda vez que o programa é aberto, os eventos são carregados a partir da leitura deste arquivo.
O mesmo é feito com os usuários, que são salvos num arquivo `users.data`.

## Autor
Este projeto foi desenvolvido por Rafael Castro como parte do trabalho Pratique para Programação de Soluções Computacionais.
