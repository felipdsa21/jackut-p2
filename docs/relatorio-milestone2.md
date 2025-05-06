# Relatório do milestone 2

**Continuação do [relatório anterior](<./relatorio-milestone1.md>)**

## Introdução

Este documento apresenta as mudanças feitas no sistema para implementar as funcionalidades do milestone 2 do projeto,
composto pelas seguintes User Stories:

5. Criação de comunidades;
6. Adição de comunidades;
7. Envio de mensagens a comunidades;
8. Criação de novos relacionamentos;
9. Remoção de conta.

## Desenvolvimento

A arquitetura básica do sistema manteve-se a mesma, com entidades, exceções, serviços, `Sistema` e `Facade`. Ainda
assim, algumas alterações foram feitas ao que já existia, principalmente para tornar o código mais organizado e
implementar a funcionalidade de remoção de conta.

### Entidades

Foi adicionada uma nova entidade `Mensagem` para representar um recado/mensagem enviado para usuários/comunidades. Ela
armazena o conteúdo da mensagem e o remetente, permitindo apagar as mensagens quando o usuário remetente é apagado do
sistema.

Também foi adicionada uma nova entidade `Comunidade` para armazenar as informações das comunidades criadas pelos
usuários.

### Exceções

Para facilitar a navegação do código, as exceções foram movidas para subpacotes que correspondem às funcionalidades que
as usam, como `usuario` para funcionalidade de conta/perfil, `comunidade` para funcionalidade de comunidade etc.

Foram adicionadas muitas exceções novas, seguindo o mesmo padrão das preexistentes: uma classe estendendo
JackutException e tendo uma mensagem predefinida.

### Serviços

A classe `Sistema` foi modificada para diretamente ler e salvar o banco de dados, em vez de depender do `Facade` para
isso, que agora tem como única função delegar para `Sistema` e os serviços.

O serviço `AmizadeService` foi renomeado `RelacionamentoService` e agora também implementa outros tipos de
relacionamento (fã-ídolo, paquera e inimigo).

Foi adicionada um serviço `ComunidadeService` para implementar a funcionalidade de comunidades criadas pelos usuários.

### Detalhes adicionais

- **Classes auxiliares**: Foi adicionada uma classe auxiliar `ServiceUtils` para compartilhar código da funcionalidade
  de inimigo.

- **Javadoc**: Foi adicionado o Javadoc no formato HTML.

## Design patterns utilizados

### Façade

O padrão Façade é implementado pela classe `Facade`, utilizada para mediar a interação entre o sistema e os testes de
aceitação.

### Dependency Injection

Os serviços (`UsuarioService`, `PerfilService` etc) recebem no construtor a instância do banco de dados (
`Supplier<Dados>`) que devem usar.

### Singleton

Embora a classe `Sistema` não implemente o padrão Singleton de maneira mais concreta/formal (`getInstance()` e
construtor privado), existe apenas uma instância da classe na prática.

## Desafios

- A funcionalidade de remoção de contas foi a mais complicada de implementar, porque o código existente não antecipava a
  possibilidade de remover usuários. Dessa forma, foi necessário um pouco de experimentação com o código até chegar em
  uma implementação simples e minimamente invasiva.

- Foi considerada a possibilidade de criar uma classe `Repositorio` para compartilhar alguns detalhes de implementação
  entre o armazenamento de usuários e comunidades (como lançar exceções em certas situações), mas o resultado complicava
  o código além do necessário e não funcionava bem com a serialização na hora de salvar os dados.

## Conclusão

A arquitetura adotada no milestone 1 provou-se adequada para permitir a implementação de novas funcionalidades sem
alterar muito as funcionalidades anteriores e, exceto pela reorganização da leitura/salvamento do banco de dados e a
renomeação de alguns pacotes e métodos, pouco foi alterado da funcionalidade preexistente durante o desenvolvimento do
milestone 2.

No fim, foi possível implementar todas as funcionalidades pedidas e todos os testes de aceitação estão passando.

## Diagrama de classes

![Diagrama de classes](<./diagrama-milestone2.svg>)
