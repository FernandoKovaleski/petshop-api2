Exercício de Query Methods:
  - Mapear entidade Produto;
  - Criar métodos no repositório para:
    - Buscar todos os produtos
    - Buscar produtos pelo nome
    - Buscar produtos no intervalo de valor (mínimo, máximo)

Excercício: Repositório de produtos:
  - Usando JPQL, criar método no repositório para listar produtos ativos filtrando por nome
  - Usando SQL, listar todos os produtos ativos ordenando por preço
  - Usando SQL, contar a quantidade de produtos inativos

TODO: corrigir a partir daqui!

Excercício: Repositório de Agendamentos:

- Criar nova entidade Agendamento com os seguintes atributos (id, dataHoraInicio, dataHoraFim, observações)
- Criar Repositório com as seguintes consultas (uma utilizando QueryMethod, outra jpql e outra sql):
  - Buscar agendamentos do dia.
  - Buscar agendamentos dos próximos 7 dias.
  - Buscar agendamento por ID.
  - Para as buscas de data, escolha sua estratégia:
    - deixar as datas no Java, e passa duas datas (between) pro SQL.
    - direto no SQL usando funções do SQL como o CURRENT_DATE()
        - select * from tabela where nascimento between CURRENT_DATE()-7 and CURRENT_DATE()

Exercício: ProdutoService.listar:

- Criar Service ProdutoService.
- Criar método listar produtos.
- Criar DTO ProdutoListagem com ID, NOME, VALOR.
- Usando stream com .map, converter a entidade no DTO.
- Criar factory para fazer a criação do DTO.
- Criar método de teste da lista de produtos.

Exercício: ProdutoService.buscarPorId:

- Criar no Service de Produto o buscar por id
- Utilizar o ProdutoDetalhes como DTO com todos os campos.
- Criar teste positivo (produto encontrado)
- Criar teste negativo (produto não encontrado e exceção disparada)
- Mudar exceção para o tipo ItemNaoEncontradoException (pacote exception).

Exercício: AgendamentoService.buscarPorId e AgendamentoService.listar:

- Alterar a entidade Agendamento para conter os atributos (id, dataHoraInicio, dataHoraFim, observações, ativo)
- Utilizar atributos no dto de listagem: id, dataInicio e dataFim.
- Utilizar atributos no dto de detalhes: id, dataInicio, dataFim, observação e ativo.
- Criar métodos no service e testes conforme praticado no Produto e Cliente

Exercício: Implementar rotas REST de listagem, criação e deleção para o produto.
Exercício: Adicionar o status code correto nos métodos do controller e header "location" se necessário.
Exercício: Adicionar testes de controller para Clientes, Produto e Agendamento.

Exercício - Validações:
- O nome da pessoa deve ser composta de pelo menos duas partes.
- O CPF da pessoa deve conter 11 dígitos (desconsiderando a formatação).
- Cada parte do nome da pessoa deve conter ao menos 2 letras.
- Uma pessoa não pode ser cadastrada com o mesmo CPF de outra já cadastrada.
- Avaliar outros campos obrigatórios e outras validações com @Anotações

OpenFeign: Vamos fazer um caso de uso COMPLETO:

- Implementar a criação do Animal com os atributos: tipo (CAO, GATO, PASSARO), nome, nascimento, foto (String).
- Implementar a seguinte regra:
    - Caso o usuário tente criar um Cão ou Gato, utilizar as APIs externas para obter as fotos.
    - O usuário não informará a foto, somente o tipo, a foto será aleatória.
    - Ambas as Apis retornarão a URL da foto, ela que deverá ser salva no banco.
- Implementar a listagem dos animais com todos os atributos.

APIs EXTERNAS:
https://random.dog/woof.json
https://api.thecatapi.com/v1/images/search

APIS novas:
https://animal-foto-api.herokuapp.com/v1/dog-photos?name={nome}
https://animal-foto-api.herokuapp.com/v1/cat-photos?name={nome}
https://animal-foto-api.herokuapp.com/v1/bird-photos?name={nome}