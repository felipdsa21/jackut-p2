# Relatório do milestone 1

Este documento apresenta a arquitetura e as principais decisões de design adotadas durante o desenvolvimento do sistema.

## Arquitetura básica

O sistema foi desenhado para manter uma separação de responsabilidades.  
Assim, as funcionalidades são distribuídas em pacotes separados, que são basicamente:

- Entidades, salvas na pasta `entities`.
- Exceções, salvas na pasta `exceptions`.
- Serviços, salvos na pasta `entities`.
- E `Facade`, servindo como classe de acesso.

### Entidades

As entidades são as classes que modelam os dados do sistema (por exemplo, `Usuario` que armazena os dados do usuário).  
Elas contêm o mínimo de lógica de negócios possível, servindo apenas como representação dos dados.  
A classe especial `Dados` armazena todas as entidades que existem no sistema, fazendo o papel de banco de dados.  
Para persistir essas entidades, elas são salvas em um arquivo `dados.ser` usando o mecanismo
de [serialização do Java](https://docs.oracle.com/en/java/javase/20/docs/specs/serialization/).

### Exceções

As exceções representam todos os potenciais erros que podem ocorrem no sistema.  
Cada erro é representado por uma de exceção específica que já vem com uma mensagem predefinida.

### Serviços

Os serviços são responsáveis por implementar a lógica de negócios da rede social.  
Cada conjunto de funcionalidades (contas, amizades, recados) é encapsulado em um serviço dedicado.  
Uma classe especial `Sistema` instancia todos os serviços e guarda o "banco de dados" usado por eles.

### Façade

O `Facade` implementa a interface esperada pelos testes.  
Ele instancia o `Sistema` e implementa métodos que delegam ao serviço relevante.  
Também é responsável por ler, salvar e apagar os `Dados` em um arquivo quando requisitado pelos testes.

## Detalhes adicionais

- **Sessões**: As sessões são implementadas como um token aleatório de 32 caracteres associado com um usuário. Eles
  também são persistidos no `dados.ser`.

- **Classes auxiliares**: Existe uma classe auxiliar `MiscUtils` que contém várias funções úteis que não se encaixam em
  outros lugares.

## Diagrama de classes

![Diagrama de classes](<./diagrama-milestone1.svg>)
