**LPII - Checklist da Etapa 4 - 1/9**

# LPII - Checklist da Etapa 4

## 1 - Empacotamento

- a Etapa 4 deverá ser entregue com Prova 2 e será considerada inválida (nota zero) se não seguir rigorosamente todos os procedimentos de Empacotamento e a metodologia ilustrada nos Tutoriais disponibilizados

### 1.1 - Nome do diretório zipado

- procedimento
  - copiar o string abaixo e somente alterar o nome do aluno para o seu nome completo, respeitando os espaçamentos e o padrão de letras maiúsculas e minúsculas
    - LPII - Etapa 4 - Alberto Luis da Silva Torres

### 1.2 - Conteúdo do arquivo zipado

- procedimento
  - incluir somente :
    - diretórios e arquivos para comprovação do sistema
      - diretório sql
        - arquivo : banco.sql (padronizado para todos os projetos dos alunos)
      - diretório src
        - subdiretórios (e seus respectivos arquivos) : controles, entidades, interfaces, persistência
        - não incluir subdiretórios : main, java
      - diretório xml
        - arquivos : pom.xml, nbactions.xml
    - os arquivos para comprovação da Etapa: espec.pdf - fontes.pdf - saída.pdf

### 1.3 - Diretórios e Arquivos para Comprovação da Execução do Sistema

- procedimento
  - copiar somente o diretório src do projeto
    - com seus diretórios padronizados (conforme o sistema de referência do Tutorial da Etapa) e seus arquivos java
    - copiar o arquivo banco.sql contendo o script sql para criar as tabelas do banco de dados utilizadas na Etapa
    - copiar os arquivos xml do projeto : pom.xml e nbactions.xml

### 1.4 - Arquivos pdf para Comprovação da Etapa da Prova Escrita : espec - fontes - saída

#### 1.4.1 - Cabeçalho de cada arquivo pdf

- procedimento
  - copiar o string abaixo e só alterar o nome do aluno para o seu nome completo, respeitando os espaçamentos e o padrão de letras maiúsculas e minúsculas
    - Linguagem de Programação II - Etapa 4 - Alberto Luis da Silva Torres
  - verificar se todos os arquivos pdf contém o mesmo cabeçalho

**Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

**LPII - Checklist da Etapa 4 - 2/9**

#### 1.4.2 - Final do arquivo de cada arquivo pdf : cidade, data -- assinatura

- procedimento
  - utilizar a data de envio da Etapa, que deverá pertencer ao período previsto para a Etapa no Plano de Ensino
  - verificar se os 2 arquivos pdf (espec e fontes) contém no final do arquivo:
    - cidade, dd/mm/aaaa
    - assinatura
  - caso a assinatura não seja digital (portal gov.br)
    - utilizar o nome por extenso com letra legível
      - assinar em um papel com letra cursiva bem legível e inserir a imagem no Adobe Reader para utilizar como assinatura -- não utilizar o mouse para assinatura porque a letra fica ilegível

#### 1.4.3 - Conteúdo do arquivo : espec.pdf

- procedimento
  - será considerada inválida a Etapa
    - cujo arquivo espec.pdf não utilizar somente as entidades e relacionamentos acordados com o professor
    - para a qual, após o cabeçalho do arquivo espec.pdf, não constarem as seguintes informações, seguindo rigorosamente o exemplo seguinte
      - para as entidades cujo plural não for formado apenas acrescentando o caracter 's' (com é o caso de Maestros e Patrocínios), deverá ser informado, além da entidade no singular, também a entidade no plural (como é o caso de Patrocinadores e PeçasMusicais)
        - a terminação dos plurais em vermelho é somente para chamar atenção no Checklist e não deverá ser utilizada na Etapa
      - para as demais informações, que deverão seguir o modelo apresentado na seção 2, será atribuída nota, penalizando as partes do espec que não seguirem as regras definidas na seção 2
    - conforme a proposta de especificação acordada com o professor
      - deve haver somente um relacionamento múltiplo
        - com a cardinalidade [1:n] ou [n:n]
      - a entidade associativa deve referenciar somente duas entidades
        - sendo um delas a principal do relacionamento múltiplo (ex: Repertório)
        - e a outra deverá ser a entidade sem referências, que não participou como entidade secundária do relacionamento múltiplo (ex: Maestro)
    - somente para projetos com cardinalidade [n:n] será necessária acrescentar uma entidade de implementação necessária no banco de dados para relacionar as entidades ligadas por um relacionamento múltiplo [n:n]
      - essa entidade deverá aparecer
        - em Entidades com seu plural (se necessário)
        - e complementando o Relacionamento Múltiplo em Relacionamentos
      - para efeito de ilustração, será mostrada na cor azul para lembrar que só deve ser utilizada para Relacionamento Múltiplo com cardinalidade [n:n]

**Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

**LPII - Checklist da Etapa 4 - 3/9**

Título do projeto: Apresentações de Repertórios Musicais

Entidades

- ApresentaçãoMusical : ApresentaçõesMusicais
- Interpretação : Interpretações
- Maestro
- PeçaMusical : PeçasMusicais
- PeçaMusicalClássica : PeçasMusicaisClássicas
- PeçaMusicalPopular : PeçasMusicaisPopulares
- Repertório

Relacionamentos

- Sem Referências : PeçaMusical, Maestro
- Relacionamento Múltiplo : Repertório [n:n] PeçaMusical (Interpretação)
- Associação : ApresentaçãoMusical [Repertório, Maestro]
- Herança : PeçaMusical [PeçaMusicalClássica, PeçaMusicalPopular]

Observe que a entidade de implementação no banco de dados, deve ser aparecer entre ( ) ao final do
Relacionamento Múltiplo com cardinalidade [n:n].

#### 1.4.4 - Conteúdo do arquivo : saída.pdf

- procedimento
  - conter somente os prints solicitados na seção 4
  - cada print deve focar somente o a visualização da janela sendo mostrada, ocupando toda a página do pdf, e conter um cabeçalho descrevendo o conteúdo do print
    - ex: inserção dos 3 objetos da entidade Amigo
    - ex: alteração do atributo estado_civil do terceiro objeto da entidade Amigo: de solteiro para casado

**Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

**LPII - Checklist da Etapa 4 - 4/9**

#### 1.4.5 - Conteúdo do arquivo : fontes.pdf

- procedimento
  - incluir o script sql utilizado para criar somente as tabelas utilizadas na Etapa
    - o nome do banco utilizado deverá ser padronizado para todos os alunos como: banco
  - incluir o código fonte de cada um dos arquivos do diretório src em uma nova página, na ordem em que eles aparecem no diretório src
    - não pode haver divergência entre o conteúdo do arquivo do diretório src e o arquivo incluído no fontes.pdf
    - utilizar somente o texto (não usar print) com fonte legível
      - ex: Times New Roman ou Arial com fonte 12
    - utilizar fundo branco
  - regras de nomes
    - no diretório controle : todos os arquivos deverão ter o prefixo ControladorCadastro
    - no diretório entidades : todos os arquivos deverão ter o próprio nome da entidade
    - no diretório interfaces
      - a janela do sistema deverá ter seu nome padronizado : JanelaSistema
      - as demais janelas deverão ter o prefixo JanelaCadastro
    - no diretório persistência deverá ser utilizado o arquivo com nome padronizado : BD
  - antes de cada arquivo, incluir o cabeçalho em negrito com o caminho do arquivo, precedido do string `$$$`, conforme os arquivos da Etapa 4 para a especificação ilustrada na seção 1.4.3 --- lembrando que caminhos de arquivo em azul, existem somente para relacionamentos múltiplos com cardinalidade [n:n]
    - `$$$ sql.banco`
    - `$$$ src.controles.ControladorCadastroApresentaçõesMusicais`
    - `$$$ src.controles.ControladorCadastroInterpretações`
    - `$$$ src.controles.ControladorCadastroMaestros`
    - `$$$ src.controles.ControladorCadastroPeçasMusicais`
    - `$$$ src.controles.ControladorCadastroRepertórios`
    - `$$$ src.entidades.ApresentaçãoMusical`
    - `$$$ src.entidades.Interpretação`
    - `$$$ src.entidades.Maestro`
    - `$$$ src.entidades.PeçaMusical`
    - `$$$ src.entidades.PeçaMusicalClássica`
    - `$$$ src.entidades.PeçaMusicalPopular`
    - `$$$ src.entidades.Repertório`
    - `$$$ src.interfaces.JanelaCadastroApresentaçõesMusicais`
    - `$$$ src.interfaces.JanelaCadastroInterpretações`
    - `$$$ src.interfaces.JanelaCadastroMaestros`
    - `$$$ src.interfaces.JanelaCadastroPeçasMusicais`
    - `$$$ src.interfaces.JanelaCadastroRepertórios`
    - `$$$ src.interfaces.JanelaPesquisaApresentaçõesMusicais`
    - `$$$ src.interfaces.JanelaSistema`
    - `$$$ src.interfaces.PainelFiltrosPeçaMusicalClássica`
    - `$$$ src.interfaces.PainelFiltrosMusicalPopular`
    - `$$$ src.interfaces.PainelPeçaMusicalClássica`

**Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

**LPII - Checklist da Etapa 4 - 5/9**

    - `$$$ src.interfaces.PainelPeçaMusicalPopular`
    - `$$$ src.persistência.BD`
  - pular uma linha entre o cabeçalho (em negrito) de cada arquivo e o seu conteúdo (sem negrito)

## 2 - Definição da Especificação

- seguir as regras de nomes para :
  - entidade (ex: Competição, CompetiçãoInternacional), atributo (ex: posição, estado_civil) e referência (ex: competição, competição_internacional)
- com exceção da entidade principal de associação, o primeiro atributo das entidades deve ser sua chave (que identifica unicamente o objeto da entidade) sublinhada (ex: título)
- a única referência da entidade principal do relacionamento múltiplo deve estar no plural e em negrito (ex: peças_musicais)
- as duas referências da entidade associadora devem estar no singular e em negrito (ex: repertório, maestro)
- as entidades devem ter pelo menos três atributos (lembrando que referências não podem ser contadas como atributos)
  - com exceção da entidade associadora que deve ter pelo menos dois atributos
  - todas as entidades deverão ter pelo menos um atributo enumerado (que a partir da Etapa 4 será informado em botões de rádio ou em combo box) e outro booleano (que a partir da Etapa 4 será informado em um check box)
- os atributos das entidades devem ser relevantes para o projeto em questão
  - projeto Venda de Veículos : o atributo valor (do veículo) é relevante
  - projeto Manutenção de Veículos : o atributo valor (do veículo) não é relevante
- um atributo não deve carregar o nome da entidade
  - na entidade Filme não deve ser definido o atributo título e não : título_filme
- o atributo de uma entidade pode ser relacionado como outra entidade que não tenha sido definida no projeto
  - se um projeto tiver a entidade AnimalDoméstico e não tiver a entidade Proprietário, então a entidade AnimalDoméstico poderá utilizar o atributo : nome_proprietário
  - caso contrário, a entidade AnimalDoméstico não deverá utilizar esse atributo, que será definido na entidade Proprietário como : nome
- atributos com um pequeno conjunto de valores possíveis deverão ter seus itens definidos em Enumerados
  - um atributo booleano exibe sempre os valores true e false e, portanto, não deve ser definido em Enumerados

**Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

**LPII - Checklist da Etapa 4 - 6/9**

O restante do modelo da especificação ilustrado na seção 1.4.3 do Empacotamento, é ilustrado a
seguir. Os atributos e enumerados farão parte da avaliação da Etapa. As chaves deverão estar
sublinhadas e as referências em negrito. Somente as subclasses não tem chave sublinhada, porque
herdam a chave da superclasse. A chave sequencial é gerada automaticamente pelo banco de dados.

Atributos e Referências

- ApresentaçãoMusical: sequencial, data, repertório, maestro, 
- Maestro: nome, anos_experiência, estilo_regência, estrangeiro
- PeçaMusical: título, compositor, gênero, duração, tom
- PeçaMusicalClássica : estilo_música_clássica, muito_conhecida
- PeçaMusicalPopular : estilo_música_popular, instrumentação_característica
- Repertório: sequencial, nome, data_montagem, descrição, peças_musicais

Não são definidos os atributos e referências da entidade de implementação Interpretação, dado que
esse tipo de entidade tem sempre uma chave primária sequencial e duas chaves estrangeiras,
referenciando as chaves primárias das tabelas envolvendo as entidades principal e secundária do
relacionamento múltiplo (no exemplo ilustrado : Repertório e PeçaMusical).

Enumerados

- estilo: expressivo, dinâmico, leve, elegante
- gênero : clássico, romântico, jazz, rock, pop, reggae, blues, country, barroco, modernismo, samba
- estilo_musica_clássica : barroco, romântico, clássico, modernismo
- estilo_musica_popular : pop, rock, country, reggae, blues, jazz
- instrumentação_característica : guitarra elétrica, bateria, baixo, teclado, saxofone, violão, trompete, piano, bateria eletrônica, violino, contrabaixo, sintetizador

## 3 - Padronização das Regras de Nomes

### 3.1 - Nomes de Pacotes e Arquivos (com a classes Java)

- pacotes (diretórios) : nomes padronizados com letras minúsculas
  - controle, entidade, interfaces, persistência
- arquivos das entidades : cada palavra com primeira letra maiúscula
  - ex: Amigo - FilmeProduraStreaming
- para nomes de arquivos, seguir os exemplos seguintes, utilizando os prefixos assinalados em negrito
  - pacote : controles
    - para cada controlador : ControladorCadastroPeçasMusicais
  - pacote : interfaces
    - janela principal : JanelaSistema
    - janelas de cadastro : JanelaCadastroPeçasMusicais
    - janela de pesquisa : JanelaPesquisaApresentaçõesMusicais
  - pacote entidades : Maestro, PeçaMusical

**Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

**LPII - Checklist da Etapa 4 - 7/9**

### 3.2 - Métodos orientados a objetos e de Métodos globais (estáticos)

- construtores : mesmo nome da classe
  - ex: Maestro, PeçaMusical, PeçaMusicalClássica
- demais métodos primeira palavra com iniciando com letra minúscula e próximas iniciando com letra maíscula
  - ex: cadastrarPeçasMusicais
- padronizar prefixo dos metódos
  - de leitura : getDataMontagem, getEstiloMúsicaPopular
  - de escrita : getDataMontagem - setEstiloMúsicaPopular

### 3.3 - Nomes de Enumerados, Variáveis e Parâmetros de Métodos

- enumerados : cada palavra com primeira letra maiúscula (mesma regra para classes)
  - ex : Gênero - InstrumentaçãoCaracterística
- variáveis ou parâmetros de métodos : palavras com letras minúsculas interligadas por underscore
  - ex : gênero - instrumentação_característica

### 3.4 - Nome do arquivo com o script para criar as tabelas do banco

O nome do arquivo banco.sql (do diretório sql ), com o script para gerar o banco de dados, deve ter
o nome do banco utilizado na URL da classe BD (do pacote persistência). Representar o nome em
letra minúscula para ficar consistente com as bases de dados criadas no IDE NetBeans.

## 4 - Funcionalidades a serem Implementadas

Todos os componentes gráficos do Tutorial 3 (e anteriores) devem ser utilizados pelo menos uma
vez

- os tratadores de eventos devem utilizar os métodos ilustrados no Tutorial 3 (e anteriores) para a atualização dos componentes gráficos utilizados
- representar enumerados com : grupos de botões de rádio (até 4 elementos) ou ComboBox (a partir de 5 elementos)
- representar atributos booleanos com CheckBox
- as visões dos objetos cadastrados, com a chave e pelo menos mais um atributo, devem ser visualizadas em componentes ComboBox
- as visões dos objetos associados a um relacionamento múltiplo, devem ser visualizadas em componentes List

A implementação deve seguir o modelo de referência do Tutorial 4

- deve ser demonstrado o cadastro das seguintes entidades
  - as entidades : sem referências
  - as entidades envolvidas no relacionamento múltiplo
    - se for relacionamento n:n : tomar como referência, a implementação ilustrada na seção 4 do Tutorial 2
    - se for relacionamento 1:n : tomar como referência, os comentários da seção 5 do Tutorial 2

**Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

**LPII - Checklist da Etapa 4 - 8/9**

Deverão ser comprovados somente pelos prints seguintes das entidades sem referências e de
relacionamento múltiplo. Nenhum print adicional deve ser inserido.

Requisitos para a implementação da Etapa 4:

- cada entidade deverá ter apenas um filtro
  - além do filtro da superclasse, deve ser também previsto um filtro para cada subclasse
- cada tipo de componente deve ser utilizado pelo menos uma vez como filtro de alguma entidade
  - campo de texto, ComboBox associado a enumerado, botões de rádio, CheckBox
- o filtro utilizado nunca poderá ser a chave da entidade (ex: Maestro.nome ou Repertório.sequencial)
  - ou um atributo que identifique unicamente a entidade (ex: Repertório.nome)

Prints para comprovar a Etapa 4

- todos os prints deverão ter um texto, no ínicio de cada página, comentado o que será apresentado no print
- inicialmente : com exceção das remoções, demonstrar os prints solicitados no Checklist da Etapa 3 -- manter todos os objetos sem realizar nenhuma remoção
- para a janela de cadastro da entidade associadora
  - print +1 : após cadastrar somente 3 objetos (nenhum outro objeto da entidade deve ser cadastrado), mostrar o formulário do terceiro objeto preenchido, abrindo o conteúdo do ComboBox
  - print +2 : segundo objeto consultado
- para a janela de pesquisa da entidade associadora
  - requisitos para a demonstração de cada pesquisa
    - todos os prints devem mostrar o resultado completo da pesquisa
      - após cada pesquisa : limpar o resultados da pesquisa anterior
    - para cada filtro adicional utilizado deve haver redução de pelo menos um objeto no resultado da janela de pesquisa
    - no relacionamento múltiplo associar quatro objetos da entidade secundária: dois que atendam o filtro que será utilizado na entidade secundária e dois que não atendam
  - prints solicitados nas pesquisas
    - print +3 : demonstrar a pesquisa sem utilizar nenhum filtro
    - pesquisas com um único filtro por vez
      - print +4 : demonstrar a pesquisa com o filtro de uma entidade sem referência que não é secundária do relacionamento múltiplo
      - print +5 : demonstrar a pesquisa com o filtro de uma entidade sem referência que é secundária do relacionamento múltiplo com flag todos os objetos como falso
      - print +6 : idem com flag todos os objetos como verdadeiro
      - print +7 : demonstrar a pesquisa com o filtro da entidade principal do relacionamento múltiplo
      - print +8 : demonstrar a pesquisa com o filtro da entidade associadora
      - print +9 : demonstrar a pesquisa com o filtro de uma subclasse
      - print +10 : demonstrar a pesquisa com o filtro da outra subclasse
    - pesquisa com dois filtros combinados
      - print +11 : demonstrar pesquisa com um filtro de uma entidade que não seja

**Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

**LPII - Checklist da Etapa 4 - 9/9**

      superclasse, combinado com o filtro de uma subclasse
      - print +12 : idem para a outra subclasse

Devem ser cadastrados pelos menos 4 objetos de subclasses, sendo 2 de cada subclasse. Cada
subclasse deve ter pelo menos 2 atributos específicos e herdar pelo menos 2 atributos comuns da
superclasse.

Nos objetos cadastrados, que aparecem no ComboBox da janela de cadastro, o string da visão deve
informar qual a subclasse do objeto de cada visão, como ilustrado a seguir:

- [1] PeçaMusicalClássica : O Guarani
- [2] PeçaMusicalPopular : Construção

Observe que com exceção da entidade associadora (ex: ApresentaçãoMusical), a superclasse pode
ser qualquer uma das entidades sem referências (ex: Maestro, PeçaMusical) ou a entidade principal
do relacionamento múltiplo (ex: Repertório). No exemplo ilustrado neste Checklist, a superclasse é
Peça Musical.

## 5 - Avaliação

- a Etapa 4 será desconsiderada como Prova 2 (nota zero) se não atender as regras de empacotamento ou não utilizar a metodologia dos tutoriais
- a correção da Etapa levará em consideração :
  - somente as partes da Etapa que executarem corretamente, em relação a todas as funcionalidades solicitadas na Etapa
    - com execução comprovada das telas solicitadas para o arquivo saídas.pdf
    - e que estiverem de acordo com o arquivo espec.pdf
  - e o atendimento das regras das seções anteriores para :
    - definição da especificação
    - padronização de nomes na implementação
    - funcionalidades solicitadas na Etapa

**Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**