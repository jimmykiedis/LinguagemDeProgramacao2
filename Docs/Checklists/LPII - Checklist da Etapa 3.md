<!-- Página 1 -->

> *[Cabeçalho da página]* **LPII - Checklist da Etapa 3 -** 1/10

# LPII - Checklist da Etapa 3

## 1 - Empacotamento

- a Etapa 3 será utilizada apenas para que o aluno possa verificar a sua progressão para realizar a Etapa 4, que será avaliada como Prova 2, sendo que a sua submissão de envio poderá ser verificada desde que siga rigorosamente todos os procedimentos de Empacotamento e a metodologia ilustrada nos Tutoriais disponibilizados

### 1.1 - Nome do diretório zipado

- **procedimento**
    - copiar o string abaixo e somente alterar o nome do aluno para o seu nome completo, respeitando os espaçamentos e o padrão de letras maiúsculas e minúsculas
        - LPII - Etapa 3 - Alberto Luis da Silva Torres

### 1.2 - Conteúdo do arquivo zipado

- **procedimento**
    - incluir <u>somente</u> :
        - diretórios e arquivos para comprovação do sistema
            - diretório sql
                - arquivo : banco.sql (<u>padronizado</u> para todos os projetos dos alunos)
            - diretório src
                - subdiretórios (e seus respectivos arquivos) : controles, entidades, interfaces, persistência
                - <u>não</u> incluir subdiretórios : main, java
            - diretório xml
                - arquivos : pom.xml, nbactions.xml
        - os arquivos para comprovação da Etapa: espec.pdf - fontes.pdf - saída.pdf

### 1.3 - Diretórios e Arquivos para Comprovação da Execução do Sistema

- **procedimento**
    - copiar somente o diretório src do projeto
        - com seus diretórios padronizados (conforme o sistema de referência do Tutorial da Etapa) e seus arquivos java
        - copiar o arquivo <u>banco.sql</u> contendo o script sql para criar as tabelas do banco de dados utilizadas na Etapa
        - copiar os arquivos xml do projeto : pom.xml e nbactions.xml

### 1.4 - Arquivos pdf  para Comprovação da Etapa da Prova Escrita : espec - fontes - saída

#### 1.4.1 - Cabeçalho de cada arquivo pdf

- **procedimento**
    - copiar o string abaixo e só alterar o nome do aluno para o seu nome completo, respeitando os espaçamentos e o padrão de letras maiúsculas e minúsculas
        - Linguagem de Programação II - Etapa 3 - Alberto Luis da Silva Torres
    - verificar se todos os arquivos pdf contém o mesmo cabeçalho

> *[Rodapé da página]* **Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

<!-- Página 2 -->

> *[Cabeçalho da página]* **LPII - Checklist da Etapa 3 -** 2/10

#### 1.4.2 - Final do arquivo de cada arquivo pdf : cidade, data -- assinatura

- **procedimento**
    - utilizar a data de envio da Etapa, que deverá pertencer ao período previsto para a Etapa no Plano de Ensino
    - verificar se os 2 arquivos pdf (espec e fontes) contém no final do arquivo:
        - cidade, dd/mm/aaaa
        - assinatura
    - caso a assinatura não seja digital (portal gov.br)
        - utilizar o nome por extenso com letra legível
            - assinar em um papel com letra cursiva bem legível e inserir a imagem no Adobe Reader para utilizar como assinatura -- <u>não</u> utilizar o mouse para assinatura porque a letra fica ilegível

#### 1.4.3 - Conteúdo do arquivo : espec.pdf

- **procedimento**
    - <u>será considerada inválida a Etapa</u>
        - cujo arquivo espec.pdf <u>não</u> utilizar <u>somente</u> as entidades e relacionamentos acordados com o professor
        - para a qual, após o cabeçalho do arquivo espec.pdf, não constarem as seguintes informações, seguindo <u>rigorosamente</u> o exemplo seguinte
            - para as entidades cujo plural não for formado apenas acrescentando o caracter 's' (com é o caso de Maestro<span style="color:#FF3300">**s**</span> e Patrocínio<span style="color:#FF3300">**s**</span>**)**, deverá ser informado, além da entidade no singular, também a entidade no plural (como é o caso de Patrocinador<span style="color:#FF3300">**es**</span> e Peça<span style="color:#FF3300">**s**</span>Musica<span style="color:#FF3300">**is**</span>)
                - a terminação dos plurais em vermelho é somente para chamar atenção no Checklist e <u>não</u> deverá ser utilizada na Etapa
            - para as demais informações, que deverão seguir o modelo apresentado na seção 2, será atribuída nota, penalizando as partes do espec que não seguirem as regras definidas na seção 2
        - conforme a proposta de especificação acordada com o professor
            - deve haver <u>somente um</u> relacionamento múltiplo
                - com a cardinalidade [1:n] <u>ou</u> [n:n]
            - a entidade associativa deve referenciar somente duas entidades
                - sendo um delas a principal do relacionamento múltiplo (ex: Repertório)
                - e a outra deverá ser a entidade sem referências, que <u>não</u> participou como entidade secundária do relacionamento múltiplo (ex: Maestro)
        - <u>somente</u> para projetos com cardinalidade [n:n] será necessária acrescentar uma entidade de implementação necessária no banco de dados para relacionar as entidades ligadas por um relacionamento múltiplo [n:n]
            - essa entidade deverá aparecer
                - em Entidades com seu plural (se necessário)
                - e complementando o Relacionamento Múltiplo em Relacionamentos
            - para efeito de ilustração, será mostrada na cor azul para lembrar que só deve ser utilizada para Relacionamento Múltiplo com cardinalidade [n:n]

> *[Rodapé da página]* **Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

<!-- Página 3 -->

> *[Cabeçalho da página]* **LPII - Checklist da Etapa 3 -** 3/10

Título do projeto: Apresentações de Repertórios Musicais

Entidades

- ApresentaçãoMusical : ApresentaçõesMusicais
- <span style="color:#0066CC">Interpretação : Interpretações</span>
- Maestro
- PeçaMusical : PeçasMusicais
- PeçaMusicalClássica : PeçasMusicaisClássicas
- PeçaMusicalPopular : PeçasMusicaisPopulares
- Repertório

Relacionamentos

- Sem Referências : PeçaMusical, Maestro
- Relacionamento Múltiplo : Repertório [n:n] PeçaMusical <span style="color:#0066CC">(Interpretação)</span>
- Associação : ApresentaçãoMusical [Repertório, Maestro]
- Herança : PeçaMusical [PeçaMusicalClássica, PeçaMusicalPopular]

Observe que a entidade de implementação no banco de dados, deve ser aparecer entre <span style="color:#0066CC">( )</span> ao final do Relacionamento Múltiplo com cardinalidade [n:n].

#### 1.4.4 - Conteúdo do arquivo : saída.pdf

- **procedimento**
    - conter **somente** os prints solicitados na seção 4
    - cada print deve focar somente o a visualização da janela sendo mostrada, ocupando toda a página do pdf, e conter um cabeçalho descrevendo o conteúdo do print
        - ex: inserção dos 3 objetos da entidade Amigo
        - ex: alteração do atributo estado_civil do terceiro objeto da entidade Amigo: de solteiro para casado

> *[Rodapé da página]* **Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

<!-- Página 4 -->

> *[Cabeçalho da página]* **LPII - Checklist da Etapa 3 -** 4/10

#### 1.4.5 - Conteúdo do arquivo : fontes.pdf

- **procedimento**
    - incluir o script sql utilizado para criar **somente** as tabelas utilizadas na Etapa
        - o nome do banco utilizado deverá ser padronizado para <u>todos</u> os alunos como: banco
    - incluir o código fonte de cada um dos arquivos do diretório src em uma nova página, na ordem em que eles aparecem no diretório src
        - não pode haver divergência entre o conteúdo do arquivo do diretório src e o arquivo incluído no fontes.pdf
        - utilizar somente o texto (não usar print) com fonte legível
            - ex: Times New Roman ou Arial com fonte 12
        - utilizar fundo branco
    - regras de nomes
        - no diretório controle : todos os arquivos <u>deverão</u> ter o prefixo ControladorCadastro
        - no diretório entidades : todos os arquivos <u>deverão</u> ter o próprio nome da entidade
        - no diretório interfaces
            - a janela do sistema deverá ter seu nome padronizado : JanelaSistema
            - as demais janelas deverão ter o prefixo JanelaCadastro
        - no diretório persistência deverá ser utilizado o arquivo com nome padronizado : BD
    - antes de cada arquivo, incluir o cabeçalho em negrito com o caminho do arquivo, precedido do string $$$, conforme os arquivos da Etapa 3 para a especificação ilustrada na seção 1.4.3 --- lembrando que caminhos de arquivo em <span style="color:#0066CC">azul</span>, existem <u>somente</u> para relacionamentos múltiplos com cardinalidade [n:n]
        - $$$ sql.banco
        - <span style="color:#0066CC">$$$ src.controles.ControladorCadastroInterpretações</span>
        - $$$ src.controles.ControladorCadastroMaestros
        - $$$ src.controles.ControladorCadastroPeçasMusicais
        - $$$ src.controles.ControladorCadastroRepertórios
        - <span style="color:#0066CC">$$$ src.entidades.Interpretação</span>
        - $$$ src.entidades.Maestro
        - $$$ src.entidades.PeçaMusical
        - $$$ src.entidades.PeçaMusicalClássica
        - $$$ src.entidades.PeçaMusicalPopular
        - $$$ src.entidades.Repertório
        - <span style="color:#0066CC">$$$ src.interfaces.JanelaCadastroInterpretações</span>
        - $$$ src.interfaces.JanelaCadastroMaestros
        - $$$ src.interfaces.JanelaCadastroPeçasMusicais
        - $$$ src.interfaces.JanelaCadastroRepertórios
        - $$$ src.interfaces.JanelaSistema
        - $$$ src.interfaces.PainelPeçaMusicalClássica
        - $$$ src.interfaces.PainelPeçaMusicalPopular
        - $$$ src.persistência.BD
    - pular uma linha entre o cabeçalho (em negrito) de cada arquivo e o seu conteúdo (sem negrito)

> *[Rodapé da página]* **Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

<!-- Página 5 -->

> *[Cabeçalho da página]* **LPII - Checklist da Etapa 3 -** 5/10

## 2 - Definição da Especificação

- seguir as regras de nomes para :
    - entidade (ex: Competição, CompetiçãoInternacional), atributo (ex: posição, estado_civil) e referência (ex: competição, competição_internacional)
- com exceção da entidade principal de associação, o primeiro atributo das entidades deve ser sua chave (que identifica unicamente o objeto da entidade) sublinhada (ex: <u>título</u>)
- a única referência da entidade principal do relacionamento múltiplo deve estar no plural e em negrito (ex: **peças_musicais**)
- as duas referências da entidade associadora devem estar no singular e em negrito (ex: **repertório, maestro**)
- as entidades devem ter pelo menos três atributos (lembrando que referências não podem ser contadas como atributos)
    - com exceção da entidade associadora que deve ter pelo menos dois atributos
    - todas as entidades deverão ter pelo menos um atributo enumerado (que a partir da Etapa 3 será informado em botões de rádio ou em combo box) e outro booleano (que a partir da Etapa 3 será informado em um check box)
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

O restante do modelo da especificação ilustrado na seção 1.4.3 do Empacotamento, é ilustrado a seguir. Os atributos e enumerados farão parte da avaliação da Etapa. As chaves deverão estar <u>sublinhadas</u> e as referências em **negrito**. Somente as subclasses não tem chave sublinhada, porque herdam a chave da superclasse. A chave sequencial é gerada automaticamente pelo banco de dados.

> *[Rodapé da página]* **Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

<!-- Página 6 -->

> *[Cabeçalho da página]* **LPII - Checklist da Etapa 3 -** 6/10

Atributos e Referências

- ApresentaçãoMusical: <u>sequencial</u>, data, **repertório**, **maestro**,
- Maestro: <u>nome</u>, anos_experiência, estilo_regência, estrangeiro
- PeçaMusical: <u>título</u>, compositor, gênero, duração, tom
- PeçaMusicalClássica : estilo_música_clássica, muito_conhecida
- PeçaMusicalPopular : estilo_música_popular, instrumentação_característica
- Repertório: <u>sequencial</u>, nome, data_montagem, descrição, **peças_musicais**

<u>Não</u> são definidos os atributos e referências da entidade de implementação <span style="color:#0066CC">Interpretação</span>, dado que esse tipo de entidade tem sempre uma chave primária sequencial e duas  chaves estrangeiras, referenciando as chaves primárias das tabelas envolvendo as entidades principal e secundária do relacionamento múltiplo (no exemplo ilustrado : Repertório e PeçaMusical).

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
        - para cada controlador : **ControladorCadastro**PeçasMusicais
    - pacote : interfaces
        - janela principal : **JanelaSistema**
        - janelas de cadastro : **JanelaCadastro**PeçasMusicais
        - janela de pesquisa : **JanelaPesquisa**ApresentaçõesMusicais
    - pacote entidades : Maestro, PeçaMusical

> *[Rodapé da página]* **Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

<!-- Página 7 -->

> *[Cabeçalho da página]* **LPII - Checklist da Etapa 3 -** 7/10

### 3.2 - Métodos orientados a objetos e de Métodos globais (estáticos)

- construtores : mesmo nome da classe
    - ex: Maestro, PeçaMusical, PeçaMusicalClássica
- demais métodos primeira palavra com iniciando com letra minúscula e próximas iniciando com letra maíscula
    - ex: cadastrarPeçasMusicais
- padronizar prefixo dos metódos
    - de leitura : **get**DataMontagem, **get**EstiloMúsicaPopular
    - de escrita : **get**DataMontagem - **set**EstiloMúsicaPopular

### 3.3 - Nomes de Enumerados, Variáveis e Parâmetros de Métodos

- enumerados : cada palavra com primeira letra maiúscula (mesma regra para classes)
    - ex : Gênero - InstrumentaçãoCaracterística
- variáveis ou  parâmetros de métodos : palavras com letras minúsculas interligadas por underscore
    - ex : gênero - instrumentação_característica

### 3.4 - Nome do arquivo com o script para criar as tabelas do banco

O nome do arquivo banco.sql (do diretório sql ), com o script para gerar o banco de dados, deve ter o nome do <u>banco</u> utilizado na URL da classe BD (do pacote persistência). Representar o nome em letra minúscula para ficar consistente com as bases de dados criadas no IDE NetBeans.

## 4 - Funcionalidades a serem Implementadas

Todos os componentes gráficos do Tutorial 3 (e anteriores) devem ser utilizados pelo menos uma vez

- os tratadores de eventos devem utilizar os métodos ilustrados no Tutorial 3 (e anteriores) para a atualização dos componentes gráficos utilizados
- representar enumerados com : grupos de botões de rádio (até 4 elementos) ou ComboBox (a partir de 5 elementos)
- representar atributos booleanos com CheckBox
- as visões dos objetos cadastrados, com a chave e pelo menos mais um atributo, devem ser visualizadas em componentes ComboBox
- as visões dos objetos associados a um relacionamento múltiplo, devem ser visualizadas em componentes List

A implementação deve seguir o modelo de referência do Tutorial 1

- deve ser demonstrado o cadastro das seguintes entidades
    - as entidades : sem referências
    - as entidades envolvidas no relacionamento múltiplo
        - se for relacionamento n:n : tomar como referência, a implementação ilustrada na seção 4 do Tutorial 2
        - se for relacionamento 1:n : tomar como referência, os comentários da seção 5 do Tutorial 2

> *[Rodapé da página]* **Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

<!-- Página 8 -->

> *[Cabeçalho da página]* **LPII - Checklist da Etapa 3 -** 8/10

Deverão ser comprovados <u>somente</u> pelos prints seguintes das entidades sem referências e de relacionamento múltiplo. Nenhum print adicional deve ser inserido.

Para relacionamento múltiplo [n:n]

- para janela de cadastro da entidade sem referência que não faz parte do relacionamento múltiplo
    - print 1 - após cadastrar <u>somente</u> 3 objetos (<u>nenhum</u> outro objeto da entidade deve ser cadastrado), mostrar o formulário do terceiro objeto preenchido, abrindo o conteúdo do ComboBox
    - print 2 - segundo objeto consultado
    - print 3 - alterar no terceiro objeto, <u>somente</u> o atributo que aparece no ComboBox (com exceção da chave, que obviamente não pode ser alterada)
    - print 4 - remover o primeiro objeto e mostrar o conteúdo do ComboBox, após a remoção
- para a para janela de cadastro da outra entidade sem referência que corresponde à secundária do relacionamento múltiplo
    - print 5 - após cadastrar <u>somente</u> 5 objetos (<u>nenhum</u> outro objeto da entidade deve ser cadastrado), mostrar o formulário do terceiro objeto preenchido, abrindo o conteúdo do ComboBox
    - print 6 - segundo objeto consultado
    - print 7 - alterar no terceiro objeto, <u>somente</u> o atributo que aparece no ComboBox (com exceção da chave, que obviamente não pode ser alterada)
    - print 8 - remover o primeiro objeto e mostrar o conteúdo do ComboBox, após a remoção
- para a janela de cadastro da entidade principal do relacionamento múltiplo
    - print 9 - após cadastrar <u>somente</u> 3 objetos (<u>nenhum</u> outro objeto da entidade deve ser cadastrado), mostrar o formulário do terceiro objeto preenchido, abrindo o conteúdo do ComboBox
    - print 10 - segundo objeto consultado com o campo List vazio
    - print 11 - selecionar os dois primeiros objetos (dos 3 restantes) da entidade de implementação (ligação entre entidade principal e secundária do relacionamento múltiplo) associando-os ao segundo objeto da entidade principal
    - print 12 - segundo objeto consultado com o List preenchido com os 2 objetos associados no print anterior
    - print 13 - alterar no terceiro objeto, <u>somente</u> o atributo que aparece no ComboBox (com exceção da chave, que obviamente não pode ser alterada)
    - print 14 - remover o primeiro objeto e mostrar o conteúdo do ComboBox, após a remoção

> *[Rodapé da página]* **Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

<!-- Página 9 -->

> *[Cabeçalho da página]* **LPII - Checklist da Etapa 3 -** 9/10

Para relacionamento múltiplo [1:n]

- para janela de cadastro da entidade sem referência que não faz parte do relacionamento múltiplo
    - print 1 - após cadastrar <u>somente</u> 3 objetos (<u>nenhum</u> outro objeto da entidade deve ser cadastrado), mostrar o formulário do terceiro objeto preenchido, abrindo o conteúdo do ComboBox
    - print 2 - segundo objeto consultado
    - print 3 - alterar no terceiro objeto, <u>somente</u> o atributo que aparece no ComboBox (com exceção da chave, que obviamente não pode ser alterada)
    - print 4 - remover o primeiro objeto e mostrar o conteúdo do ComboBox, após a remoção
- para a janelas de cadastro da entidade principal e secundária do relacionamento múltiplo
    - print 5 - após cadastrar <u>somente</u> 3 objetos (<u>nenhum</u> outro objeto da entidade deve ser cadastrado) da entidade principal, mostrar o formulário do terceiro objeto preenchido, abrindo o conteúdo do ComboBox
    - print 6 - segundo objeto consultado da entidade principal com o campo List vazio
        - print 7 - após cadastrar somente 3 objetos da entidade secundária,  associados ao segundo objeto da entidade principal : mostrar o formulário do terceiro objeto preenchido, abrindo o conteúdo do ComboBox da entidade secundária do relacionamento múltiplo, do relacionamento múltiplo
        - print 8 : consultar o segundo objeto da entidade secundária
        - print 9 : alterar no terceiro objeto da entidade secundária, <u>somente</u> o atributo que aparece no ComboBox (com exceção da chave, que obviamente não pode ser alterada)
        - print 10 : remover o primeiro objeto da entidade secundária e mostrar o conteúdo do ComboBox, após a remoção
    - print 11 - segundo objeto da entidade principal consultado com o List preenchido com os objetos restantes da entidade secundária
    - print 12 - alterar no terceiro objeto, <u>somente</u> o atributo que aparece no ComboBox (com exceção da chave, que obviamente não pode ser alterada)
    - print 13 - remover o primeiro objeto e mostrar o conteúdo do ComboBox, após a remoção

Devem ser cadastrados pelos menos 4 objetos de subclasses, sendo 2 de cada subclasse. Cada subclasse deve ter pelo menos 2 atributos específicos e herdar pelo menos 2 atributos comuns da superclasse.

Nos objetos cadastrados, que aparecem no ComboBox da janela de cadastro, o string da visão deve informar qual a subclasse do objeto de cada visão, como ilustrado a seguir:

- [1] PeçaMusicalClássica : O Guarani
- [2] PeçaMusicalPopular : Construção

Observe que com exceção da entidade associadora (ex: ApresentaçãoMusical), a superclasse  pode ser qualquer uma das entidades sem referências (ex: Maestro, PeçaMusical) ou a entidade principal do relacionamento múltiplo (ex: Repertório). No exemplo ilustrado neste Checklist, a superclasse é Peça Musical.

> *[Rodapé da página]* **Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

<!-- Página 10 -->

> *[Cabeçalho da página]* **LPII - Checklist da Etapa 3 -** 10/10

## 5 - Verificação da Etapa 3

- a verificação da Etapa 3 deverá considerar o atendimento às regras de empacotamento e a utilização da metodologia dos tutoriais
- a correção da Etapa levará em consideração :
    - somente as partes da Etapa que executarem corretamente, em relação a todas as funcionalidades solicitadas na Etapa
        - com execução comprovada das telas solicitadas para o arquivo saídas.pdf
        - e que estiverem de acordo com o arquivo espec.pdf
    - e o atendimento das regras das seções anteriores para :
        - definição da especificação
        - padronização de nomes na implementação
        - funcionalidades solicitadas na Etapa

> *[Rodapé da página]* **Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD**

---

# Auditoria da Transcrição

- Páginas do PDF: 10
- Páginas verificadas: 10
- Imagens encontradas: 0
- Imagens preservadas: 0 (o PDF não contém imagens raster nem gráficos vetoriais; por isso a pasta `imagens/` não foi criada)
- Tabelas encontradas: 0
- Tabelas transcritas: 0
- Blocos de código encontrados: 0 (o PDF não contém código-fonte; as strings literais como `$$$ src.entidades.Maestro` aparecem em itens de lista e foram transcritas como texto)
- Itens de lista transcritos: 207 (com 5 níveis de aninhamento)
- Trechos sublinhados no PDF: 43; sublinhados no `.md`: 43
- Conteúdo ilegível: 0 ocorrências
- Conteúdo não transcrito ou não extraído: nenhum

## Como a verificação foi feita

- Texto: para cada uma das 10 páginas, o texto do `.md` (sem marcadores de Markdown) foi comparado com `pdftotext -layout`, incluindo espaços (após normalizar espaços em branco), e com pypdf e pdfplumber (caracteres alfanuméricos). Resultado: idêntico em todas as páginas.
- Formatação: o `.md` foi lido de volta e cada caractere não-espaço foi comparado com os atributos do PDF (negrito, sublinhado, cor). Resultado: idêntico nas 10 páginas. A única diferença intencional está nos títulos que já eram negrito no PDF (ver abaixo).
- Sublinhados: o número de segmentos de linha sublinhando texto em cada página do PDF é igual ao número de trechos `<u>` do `.md` (p. 1: 4, p. 2: 10, p. 4: 4, p. 5: 2, p. 6: 5, p. 7: 1, p. 8: 10, p. 9: 7; páginas 3 e 10: 0).
- Listas: o nível de cada item foi conferido pela posição e pelo glifo do marcador no PDF, e o aninhamento resultante é válido em todas as páginas.
- As 10 páginas foram renderizadas e conferidas visualmente (negrito, sublinhado, cores, níveis e ordem).

## Convenções usadas nesta transcrição

- A formatação do texto tem significado neste documento (regras de como escrever a especificação e os nomes), por isso foi preservada:
  - negrito: `**texto**`
  - sublinhado: `<u>texto</u>`
  - cor azul do texto (#0066CC): `<span style="color:#0066CC">texto</span>`
  - cor vermelha do texto (#FF3300): `<span style="color:#FF3300">texto</span>`
- Texto em azul no PDF (referente à entidade de implementação do relacionamento múltiplo [n:n]): p. 3 (`Interpretação : Interpretações`, `(Interpretação)` e `( )`), p. 4 (a palavra `azul` e os três caminhos com `Interpretação`/`Interpretações`) e p. 6 (`Interpretação`). Texto em vermelho no PDF: apenas as terminações dos plurais na p. 2 (`s` de Maestros, `s` de Patrocínios, `es` de Patrocinadores, `s` e `is` de PeçasMusicais).
- Negrito, sublinhado e cor podem aparecer combinados no mesmo trecho (por exemplo, as terminações vermelhas dos plurais na p. 2 também estão em negrito).
- Títulos: o título principal e as seções `1` a `5` estão em negrito no PDF e foram transcritos como títulos (`#` e `##`) sem `**`, porque o título já é exibido em negrito. As subseções (`1.1`, `1.4.1`, `3.1`, etc.) estão em fonte normal no PDF e foram transcritas como títulos `###` e `####` apenas para representar a hierarquia numérica. Linhas como `Entidades`, `Relacionamentos`, `Atributos e Referências`, `Enumerados` e `Título do projeto: ...` estão em fonte normal e foram transcritas como parágrafos.
- Listas: o PDF usa 5 níveis de marcador; cada nível é um nível de aninhamento (4 espaços por nível) com o marcador `-`. Os glifos originais por nível são: nível 1 `•`, nível 2 `◦`, nível 3 `▪`, nível 4 `•`, nível 5 `◦`. As cores dos marcadores (verde, azul, vermelho) são decoração dos marcadores e não foram reproduzidas.
- As quebras de linha do PDF dentro de um mesmo parágrafo ou item foram unidas (são quebras automáticas). Nenhuma palavra foi alterada. Espaços duplos existentes no PDF foram mantidos (por exemplo, `pdf  para` na p. 1, `duas  chaves` na p. 6, `superclasse  pode` na p. 9).
- Cabeçalho e rodapé de cada página estão marcados com `[Cabeçalho da página]` e `[Rodapé da página]` em itálico (o negrito que aparece depois do rótulo faz parte do texto original: no cabeçalho, apenas `LPII - Checklist da Etapa 3 -` está em negrito, e o número da página não; no rodapé, todo o texto está em negrito).
- A numeração da página no cabeçalho (`1/10` a `10/10`) foi mantida como no PDF.

## Observações do transcritor sobre o documento original (nada foi corrigido no corpo)

Estas observações não fazem parte do PDF. Servem apenas para quem for usar o documento, indicando pontos em que o PDF é inconsistente ou remete a material que não está nele. O corpo da transcrição mantém tudo exatamente como está no PDF.

Erros de digitação e de redação presentes no PDF, preservados:

- p. 2: "com é o caso de Maestros e Patrocínios" (no lugar de "como"); "sendo um delas".
- p. 3: "deve ser aparecer entre ( )"; "focar somente o a visualização".
- p. 6: "FilmeProduraStreaming" (exemplo de nome de arquivo).
- p. 6: "com a classes Java" (título da seção 3.1).
- p. 7: "com iniciando com letra minúscula", "maíscula", "metódos".
- p. 8: "para a para janela de cadastro".
- p. 9: "para a janelas de cadastro"; "pelos menos 4 objetos"; "Peça Musical" (com espaço) na última frase.

Inconsistências internas do PDF, preservadas:

- Nome do arquivo de prints: "saída.pdf" (p. 1 e p. 3) e "saídas.pdf" (p. 10).
- Nome dos pacotes/diretórios: a p. 1 lista `controles, entidades, interfaces, persistência`; a p. 4 diz "no diretório controle" e "no diretório entidades"; a p. 6 lista os pacotes como `controle, entidade, interfaces, persistência` e, na mesma página, usa "pacote : controles" e "pacote entidades". Os cabeçalhos `$$$` da p. 4 usam `src.controles`, `src.entidades`, `src.interfaces` e `src.persistência`.
- Caminho do script: os cabeçalhos da p. 4 listam `$$$ sql.banco`, enquanto o arquivo é `banco.sql` no diretório `sql` (p. 1, p. 7).
- p. 7 (seção 3.2): em "de escrita", o primeiro exemplo é `getDataMontagem` (prefixo `get`) e o segundo é `setEstiloMúsicaPopular` (prefixo `set`).
- p. 6: os enumerados `estilo_musica_clássica` e `estilo_musica_popular` estão sem acento em "musica", enquanto os atributos correspondentes em "Atributos e Referências" são `estilo_música_clássica` e `estilo_música_popular`. O enumerado se chama `estilo`, e o atributo de Maestro se chama `estilo_regência`.
- p. 6: a linha de `ApresentaçãoMusical` termina com vírgula (`repertório, maestro,`).
- p. 8 e p. 9: o cenário [n:n] tem prints 1 a 14 e o cenário [1:n] tem prints 1 a 13. Na p. 9, os prints 7 a 10 estão um nível abaixo (marcador `▪`) do print 6, ao contrário dos demais.
- p. 3: no exemplo da especificação, a entidade de implementação aparece na lista de Entidades como `Interpretação : Interpretações`, mas na p. 6 o texto diz que seus atributos e referências não são definidos.
- p. 6 (seção 3.1): cita `JanelaPesquisaApresentaçõesMusicais` como exemplo de janela de pesquisa, mas essa janela não consta da lista de arquivos com cabeçalho `$$$` da p. 4.

Itens referenciados neste PDF, mas cujo conteúdo não está nele:

- Tutorial 1 (modelo de referência da implementação), Tutorial 2 (seção 4 para relacionamento n:n e seção 5 para relacionamento 1:n) e Tutorial 3 ("todos os componentes gráficos do Tutorial 3 (e anteriores)").
- "o sistema de referência do Tutorial da Etapa", o "Plano de Ensino" (período da Etapa), a Etapa 4 (Prova 2) e os "arquivos da Etapa 3 para a especificação ilustrada na seção 1.4.3".
- O nome do banco de dados, que deve ser `banco` (p. 4) e aparecer na URL da classe `BD` (p. 7); o conteúdo da classe `BD` não está neste PDF.
