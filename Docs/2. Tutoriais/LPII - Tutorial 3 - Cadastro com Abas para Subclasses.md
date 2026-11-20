<!-- INÍCIO DA NOTA DE RETIFICAÇÃO (inserida posteriormente; não faz parte do material original do professor) -->

# NOTA DE RETIFICAÇÃO — CORREÇÃO DA SUPERCLASSE

> **Status:** esta nota foi inserida antes do conteúdo original e tem precedência sobre ele no que se refere à escolha da superclasse. Todo o conteúdo abaixo da linha `<!-- FIM DA NOTA DE RETIFICAÇÃO -->` foi preservado exatamente como estava, inclusive os trechos que esta nota identifica como conceitualmente equivocados.

## 1. Resumo da retificação

- O relacionamento entre as entidades é **`Filmes [N:N] Atores`**.
- Segundo a explicação do professor, **"a superclasse é sempre a segunda classe do relacionamento múltiplo"**. No relacionamento `Filmes [N:N] Atores`, a segunda classe é `Atores`.
- Portanto, a **superclasse correta é `Ator`**, e não `Filme`.
- O tutorial original foi desenvolvido **equivocadamente** com `Filme` como superclasse, tendo `FilmeCompanhiaCinematográfica` e `FilmeProvedoraStreaming` como subclasses. Essa estrutura **não** é a pretendida pelo professor.

## 2. Regra de interpretação para agentes de IA

**Em caso de conflito entre as instruções, exemplos ou códigos originais deste tutorial e esta nota de retificação, considere esta nota como a referência correta para interpretar a atividade. Não reproduza a hierarquia de herança baseada em `Filme` como se fosse a estrutura pretendida pelo professor.**

Consequências práticas desta regra:

1. Onde o tutorial trata `Filme` como superclasse (por exemplo, "atributos comuns da superclasse Filme", "subclasses da entidade Filme", "chave estrangeira para a chave da superclasse"), leia que a classe-base pretendida é `Ator`.
2. Essa leitura se refere ao **papel de superclasse**. Ela **não** autoriza substituir mecanicamente o texto "Filme" por "Ator" nos exemplos, nem apenas renomear as subclasses existentes.
3. Não gere código, scripts SQL, telas ou explicações que apresentem `FilmeCompanhiaCinematográfica` ou `FilmeProvedoraStreaming` como estrutura correta da atividade.

## 3. Os três elementos que não podem ser confundidos

| Elemento | Definição correta | Situação no tutorial original |
|---|---|---|
| **Relacionamento entre entidades** | `Filmes [N:N] Atores` — uma **associação** entre duas classes independentes. Deve ser preservada como associação. | Aparece de forma indireta, pela lista "Atuações no Filme", pelo botão "Atuações"/"Atores", pela tabela `Atuações` (mencionada nos comandos `DROP TABLE`) e pela chamada `Atuação.removerAtuações`. A definição completa da associação não consta neste arquivo. |
| **Superclasse pretendida** | **`Ator`** | O tutorial usou `Filme` (equivocado). |
| **Subclasses pretendidas** | **Subclasses de `Ator`**, cujos nomes e responsabilidades devem ser definidos de acordo com as especificações efetivamente disponíveis na atividade. | Não especificadas neste arquivo (ver seção 6). As subclasses do tutorial são subclasses de `Filme` e pertencem ao modelo equivocado. |

## 4. Associação N versus herança

**Associação (`Filmes [N:N] Atores`).** É um vínculo entre objetos de duas classes que existem de forma independente: um filme possui vários atores em sua atuação, e um ator atua em vários filmes. A relação é do tipo "se relaciona com". Nenhuma das duas classes é especialização da outra. Em banco de dados, costuma ser representada por uma tabela associativa com chaves estrangeiras para as duas entidades, papel que, no tutorial, é indicado pela tabela `Atuações`.

**Herança.** É uma relação de especialização do tipo "é um": toda instância da subclasse também é uma instância da superclasse, herdando seus atributos e métodos e acrescentando os seus próprios. Em banco de dados, a tabela da subclasse recebe uma chave estrangeira para a chave da tabela da superclasse, como o tutorial ilustra para a hierarquia (equivocada) baseada em `Filme`.

**Distinção obrigatória:**

- Um filme **não é** um ator, e um ator **não é** um filme. Por isso, `Filmes [N:N] Atores` **jamais** deve ser modelado como herança (nem `Filme` estendendo `Ator`, nem `Ator` estendendo `Filme`).
- A associação `Filmes [N:N] Atores` **continua existindo, inalterada**. A correção desta nota diz respeito **exclusivamente** à escolha da superclasse na atividade de herança: a hierarquia de herança deve ser construída **dentro de `Ator`** (especializações de `Ator`), em paralelo à associação com `Filme`, sem substituí-la.
- A frase "a superclasse é a segunda classe do relacionamento múltiplo" indica **qual classe** do relacionamento deve ser a raiz da hierarquia de herança. Ela **não** transforma o relacionamento em herança.

## 5. O que, no conteúdo original, pertence à abordagem equivocada

Todo o material abaixo desta nota foi construído a partir da interpretação de `Filme` como superclasse e deve ser reconhecido como pertencente à abordagem equivocada. Isso inclui:

- **Seção 1:** a declaração de que as subclasses são especializações da entidade `Filme`, os atributos e enumerados de `FilmeCompanhiaCinematográfica` e `FilmeProvedoraStreaming`, a ordem de remoção de tabelas em que as tabelas das subclasses precedem `Filmes`, e os comandos `CREATE TABLE FilmesCompanhiasCinematográficas` e `CREATE TABLE FilmesProvedorasStreaming` (com chave estrangeira `FilmeId` para `Filmes(Sequencial)`).
- **Seção 2:** os painéis auxiliares `PainelFilmeCompanhiaCinematográfica` e `PainelFilmeProvedoraStreaming`, o componente `especialização_filmeTabbedPane` e o construtor de `JanelaCadastroFilmes`.
- **Seção 3:** a sobreposição de construtor reduzido, `getVisão` e `toString` nas subclasses de `Filme`.
- **Seções 4 a 8:** `obtémFilmeInformado`, `buscarFilme`, `getVisões`, `inserirFilme`, `consultarFilme`, `alterarFilme` (janela e classe), `removerFilme` e `limparCampos`, na parte em que tratam as subclasses de `Filme`.
- **As duas imagens** (páginas 2 e 3) e suas transcrições.

**`FilmeCompanhiaCinematográfica` e `FilmeProvedoraStreaming`** são subclasses do modelo equivocado (categorias de **filmes**). Elas **não** são subclasses válidas de `Ator`, pois representam categorias de filmes e não de atores, e **não** devem ser mantidas, nem apenas renomeadas, como subclasses de `Ator`.

## 6. Subclasses de `Ator`: ausência de especificação neste arquivo

**Este arquivo não especifica quais subclasses de `Ator` devem ser implementadas, nem seus atributos, tabelas ou telas.** Essa ausência fica registrada aqui:

> **[SUBCLASSES DE `Ator`: NÃO ESPECIFICADAS NESTE ARQUIVO]**

Orientações:

- Não invente subclasses de `Ator`, atributos ou regras de negócio para preencher essa lacuna.
- Não presuma que as duas subclasses originais devem apenas ter seus nomes alterados.
- Não transfira os atributos das subclasses originais (Oscar, provedora de streaming, produção, total de episódios) para subclasses de `Ator`, como se fossem especificações delas.
- Se as subclasses de `Ator` forem definidas em outra parte do enunciado da atividade, siga essa especificação. Se não estiverem definidas, peça a definição ao professor (ou ao estudante, no caso de um agente de IA) antes de implementar.

## 7. Como usar o restante deste documento

O conteúdo original permanece abaixo, sem alterações, como registro do material distribuído. Os **mecanismos técnicos** ali ilustrados (abas com painéis auxiliares, tabela da subclasse com chave estrangeira para a tabela da superclasse, uso de `instanceof`, sobreposição de métodos nas subclasses) podem servir como referência de **como** implementar herança na interface e no banco de dados. Contudo, a **modelagem do domínio**, isto é, qual é a superclasse e o que são as subclasses, deve seguir esta nota.

Esta nota trata apenas da escolha da superclasse. Ela não corrige os erros de digitação e as inconsistências de código listados na seção "Auditoria da Transcrição", ao final do documento.

<!-- FIM DA NOTA DE RETIFICAÇÃO -->

---

<!-- Página 1 -->

> **[Cabeçalho da página]** Linguagem de Programação II - Tutorial 3 - 1/14

# LPII - Componentes Adicionais e Interface com Subclasses

## 1 – Ampliação da JanelaCadastroFilmes para suportar o Cadastro das Subclasses

Nesta seção, vamos ilustrar a representação de duas subclasses da entidade Filme na JanelaCadastroFilmes. Vamos especificar duas especializações (subclasses) da entidade Filme:

- FilmeCompanhiaCinematográfica
- FilmeProvedoraStreaming

Vários filmes produzidos por uma companhia cinematográfica, como por exemplo a 20th Century Fox, são indicados todos os anos para concorrer à premição do Oscar.

Na entidade FilmeCompanhiaCinematográfica, são representados os atributos: oscar de melhor filme, e nome dos ganhadores de Oscar de melhor: diretor, ator principal e atriz principal. Para não estender demais, o número de atributos da entidade, foram excluídas as premiações para atores coadjuvantes e demais premiações do Oscar. O atributo de melhor filme tem preenchimento obrigatório, dado que um filme não ganhador, pode ser representado com o valor false. Os atributos com os nomes de ganhadores de Oscar não tem preenchimento obrigatório, dado que para um dado filme tanto o diretor, quanto o ator e a atriz principal podem não ter sido premiados com um Oscar.

Os atributos da subclasse FilmeCompanhiaCinematográfica são os seguintes:

```java
private boolean oscar_melhor_filme;
private String oscarMelhorDiretor;
private String oscarMelhorAtor;
private String oscarMelhorAtriz;
```

Provedoras de streaming, como por exemplo a NetFlix, tem disponibilizado produções como filmes (com um único episódio) e ou como séries (com vários episódios). Na subclasse FilmeProvedoraStreaming, são representados os seguintes enumerados e atributos:

```java
public enum ProvedoraStreaming {NetFlix, AmazonPrimeVideo, HBOGo, GooglePlayStore};
public enum Produção {filme, série};

private ProvedoraStreaming provedora;
private Produção produção;
private int total_episódios;
```

No script sql, a ordem de remoção das tabelas deve priorizar as tabelas que dependem de outras tabelas e, portanto, as tabelas que representam as subclasses, que contém uma chave estrangeira para a chave da superclasse, devem ser removidas antes da tabela que representa a superclasse. A seguir, os seguintes comandos de remoção de tabelas existentes:

```sql
DROP TABLE IF EXISTS Atuações;
DROP TABLE IF EXISTS Amigos;
DROP TABLE IF EXISTS Atores;
DROP TABLE IF EXISTS FilmesCompanhiasCinematográficas;
DROP TABLE IF EXISTS FilmesProvedorasStreaming;
DROP TABLE IF EXISTS Filmes;
```

> **[Rodapé da página]** Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD

---

<!-- Página 2 -->

> **[Cabeçalho da página]** Linguagem de Programação II - Tutorial 3 - 2/14

As tabelas que representarm as subclasses da entidade Filme, são incluídas no script sql, logo após a definição da tabela Filmes, pois possem uma chave estrangeira associada à chave da tabela Filmes:

```sql
CREATE TABLE FilmesCompanhiasCinematográficas (
   OscarMelhorFilme BOOLEAN NOT NULL,
   OscarMelhorDiretor VARCHAR(30),
   OscarMelhorAtor VARCHAR(30),
   OscarMelhorAtriz VARCHAR(30),
   FilmeId INT NOT NULL,
   FOREIGN KEY (FilmeId) REFERENCES Filmes(Sequencial));

CREATE TABLE FilmesProvedorasStreaming (
   ProvedoraStreaming INT NOT NULL,
   Produção INT NOT NULL,
   TotalEpisódios INT NOT NULL,
   FilmeId INT NOT NULL,
   FOREIGN KEY (FilmeId) REFERENCES Filmes(Sequencial));
```

A seguir, é ilustrada a JanelaCadastroFilmes preenchida com um filme de companhia cinematográfica.

![Janela "Cadastrar Filmes" (JanelaCadastroFilmes) preenchida com um filme de companhia cinematográfica: Titanic](images/tutorial-003-pagina-002-imagem-001.png)

> **Transcrição de todo o texto e estado visível da imagem (página 2, imagem 1):**
>
> - Barra de título da janela: "Cadastrar Filmes"
> - Rótulo "Filmes Cadastrados" — ComboBox com o item exibido: `[1] Titanic - Oscar Melhor Filme`
> - Rótulo "Identificador Sequencial" — campo com o valor: `1` (campo com aparência desabilitada/fundo cinza)
> - Rótulo "Título" — TextField com o valor: `Titanic`
> - Rótulo "Gênero" — ComboBox com o valor: `romance`
> - Rótulo "Ano" — TextField com o valor: `1997`
> - Rótulo "Atuações no Filme" — lista com dois itens:
>   - `Leonardo DiCaprio [1974]`
>   - `Kate Winslet [1975]`
> - Componente de abas (TabbedPane) com duas abas, nesta ordem:
>   1. "Filme de Companhia Cinematográfica" (aba **selecionada**)
>   2. "Filme de Provedora de Streaming"
> - Conteúdo da aba selecionada "Filme de Companhia Cinematográfica":
>   - CheckBox "Oscar de Melhor Filme" — **marcado**
>   - Rótulo "Oscar de Melhor Diretor" — TextField com o valor: `James Cameron`
>   - Rótulo "Oscar de Melhor Ator" — TextField **vazio**
>   - Rótulo "Oscar de Melhor Atriz" — TextField **vazio**
> - Botões na parte inferior, da esquerda para a direita: "Inserir", "Consultar", "Alterar", "Remover", "Limpar", "Atuações"

<!-- fim da imagem -->

> **[Rodapé da página]** Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD

---

<!-- Página 3 -->

> **[Cabeçalho da página]** Linguagem de Programação II - Tutorial 3 - 3/14

A seguir, é ilustrada a JanelaCadastroFilmes preenchida com um filme de provedora de streaming.

![Janela "Cadastrar Filmes" (JanelaCadastroFilmes) preenchida com um filme de provedora de streaming: Wandinha](images/tutorial-003-pagina-003-imagem-001.png)

> **Transcrição de todo o texto e estado visível da imagem (página 3, imagem 1):**
>
> - Barra de título da janela: "Cadastrar Filmes"
> - Rótulo "Filmes Cadastrados" — ComboBox com o item exibido: `[2] Wandinha --- NetFlix - 8 episódios`
> - Rótulo "Identificador Sequencial" — campo com o valor: `2` (campo com aparência desabilitada/fundo cinza)
> - Rótulo "Título" — TextField com o valor: `Wandinha`
> - Rótulo "Gênero" — ComboBox com o valor: `ficção`
> - Rótulo "Ano" — TextField com o valor: `2022`
> - Rótulo "Atuações no Filme" — lista com dois itens:
>   - `Jenna Ortega [2002]`
>   - `Emma Myers [2002]`
> - Componente de abas (TabbedPane) com duas abas, nesta ordem:
>   1. "Filme de Companhia Cinematográfica"
>   2. "Filme de Provedora de Streaming" (aba **selecionada**)
> - Conteúdo da aba selecionada "Filme de Provedora de Streaming":
>   - Rótulo "Provedora de Streaming" — ComboBox com o valor: `NetFlix`
>   - Rótulo "Produção" — dois RadioButtons: "filme" (**não selecionado**) e "série" (**selecionado**)
>   - Rótulo "Total de Episódios" — TextField com o valor: `8`
> - Botões na parte inferior, da esquerda para a direita: "Inserir", "Consultar", "Alterar", "Remover", "Limpar", "Atores"

<!-- fim da imagem -->

Na seção 2, é detalhada a configuração da JanelaCadastroFilmes para a visualização das abas contendo os atributos das subclasses, bem como, a criação de janelas auxiliares com painéis para para cada uma das subclasses.

Na seção 3, é explicado como as informações das subclasses passam a ser visualizadas no filmes_cadastradosComboBox da JanelaCadastroFilmes.

Na seção 4, 5, 6 e 7 são abordadas as atualizações necessárias respectivamente para: a criação, alteração e remoção de filmes, e a limpeza do preenchimento do formulários de cadastro.

## 2 - Configurando a JanelaCadastroFilmes e as Janelas Auxiliares de Panéis para Subclasses

Na criação de um filme, são informados os atributos comuns da superclasse Filme e, adicionalmente, os atributos da subclasse para a qual se deseja criar o filme. Para informar os atributos específicos de uma das subclasses, é selecionada a aba que contém tais atributos.

> **[Rodapé da página]** Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD

---

<!-- Página 4 -->

> **[Cabeçalho da página]** Linguagem de Programação II - Tutorial 3 - 4/14

Para realizar a seleção da aba que contém os atributos da subclasse desejada, bem como a visualização de seus atributos para preenchimento, é utilizada a seguinte estratégia:

- a janela auxiliar PainelFilmeCompanhiaCinematográfica é implementada, para representar os atributos da subclasse FilmeCompanhiaCinematográfica;
- a janela auxiliar PainelFilmeProvedoraStreaming é implementada, para representar os atributos da subclasse FilmeProvedoraStreaming;
- na JanelaCadastroFilmes é inserido o componente especialização_filmeTabbedPane, ao qual são associadas as abas correspondentes a cada janela auxiliar de painel.

### 2.1 - A janela auxiliar PainelFilmeCompanhiaCinematográfica

A janela auxiliar PainelFilmeCompanhiaCinematográfica herda a classe Panel e, portanto, é criada a partir da opção JPanel Form do NetBeans. Nessa janela, são definidos seguintes os componentes: (a) um CheckBox para o atributo oscar_filme; e (b) 3 TextFields para os atributos oscar_diretor, oscar_ator e oscar_atriz, bem como, seus respectivos Labels.

O construtor desta janela auxiliar não tem parâmetros.

```java
public PainelFilmeCompanhiaCinematográfica() {
    initComponents();
}
```

Adicionalmente, são definidos os métodos de leitura e alteração de cada um dos componentes da janela, bem como o método para limpeza dos campos.

```java
public boolean isOscarMelhorFilme() { return oscar_filmeCheckBox.isSelected(); }

public void setOscarMelhorFilme(boolean oscar_melhor_filme) {
    oscar_filmeCheckBox.setSelected(oscar_melhor_filme);
}

public String getOscarMelhorDiretor() {
    String diretor = oscar_diretorTextField.getText();
    if (diretor.isEmpty()) return null;
    else return diretor;
}

public void setOscarMelhorDiretor(String diretor) { oscar_diretorTextField.setText(diretor); }

public String getOscarMelhorAtor() {
    String ator = oscar_atorTextField.getText();
    if (ator.isEmpty()) return null;
    else return ator;
}

public void setOscarMelhorAtor(String ator) { oscar_atorTextField.setText(ator); }

public String getOscarMelhorAtriz() {
    String atriz = oscar_atrizTextField.getText();
    if (atriz.isEmpty()) return null;
    else return atriz;
}

public void setOscarMelhorAtriz(String atriz) { oscar_atrizTextField.setText(atriz); }
```

> **[Rodapé da página]** Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD

---

<!-- Página 5 -->

> **[Cabeçalho da página]** Linguagem de Programação II - Tutorial 3 - 5/14

### 2.2 - A janela auxiliar PainelFilmeProvedoraStreaming

A janela auxiliar PainelFilmeProvedoraStreaming é criada de forma equivalente, com os seguintes os componentes: (a) um ComboBox para o atributo provedora; (b) um grupo de botões de radio (RadioButton) para o atributo produção; e (c) um TextField para o atributo total_episódios. E obviamente, os repectivos Labels para cada um desses componentes.

De forma similar, seu construtor não tem parâmetros. A implementação dos seus métodos de leitura e alteração dos componentes, bem como o método para limpeza dos campos, é ilustrada a seguir.

```java
public ProvedoraStreaming getSelectedProvedoraStreaming() {
    Object provedora_streaming = provedoraComboBox.getSelectedItem();
    if (provedora_streaming != null) return (ProvedoraStreaming) provedora_streaming;
    else return null;
}

public void setSelectedProvedoraStreaming(ProvedoraStreaming provedora_streaming) {
    provedoraComboBox.setSelectedItem(provedora_streaming);
}

public Produção getSelectedProdução() {
    Produção produção = null;
    if (produçãoButtonGroup.getSelection() != null)
        produção = Produção.values()[produçãoButtonGroup.getSelection().getMnemonic()];
    return produção;
}

public void setSelectedProdução(int índice_produção) {
    switch(índice_produção) {
        case 0: filmeRadioButton.setSelected(true); break;
        case 1: sérieRadioButton.setSelected(true);
    }
}

public int getTotalEpisódios() {
    String total_episódios_str = total_episódiosTextField.getText();
    if (!total_episódios_str.isEmpty()) return Integer.parseInt(total_episódios_str);
    else return -1;
}

public void setTotalEpisódios(int total_episódios) {
    total_episódiosTextField.setText(total_episódios + "");
}

public void limparCampos() {
    provedoraComboBox.setSelectedIndex(-1);
    produçãoButtonGroup.clearSelection();
    total_episódiosTextField.setText("");
}
```

> **[Rodapé da página]** Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD

---

<!-- Página 6 -->

> **[Cabeçalho da página]** Linguagem de Programação II - Tutorial 3 - 6/14

### 2.3 - O dados e o construtor da JanelaCadastroFilmes

Para adicinar as abas, criadas a partir dos painéis das janelas auxiliares ao componente especialização_filmeTabbedPane da JanelaCadastroFilmes, são necessários novos dados na janela e ações adicionais no seu construtor.

```java
ControladorCadastroFilmes controlador;
Filme[] filmes_cadastrados;
DefaultListModel modelo_atuações;
PainelFilmeCompanhiaCinematográfica filme_companhia_cinematográficaPainel;
PainelFilmeProvedoraStreaming filme_provedora_streamingPainel;

public JanelaCadastroFilmes(ControladorCadastroFilmes controlador) {
    this.controlador = controlador;
    filmes_cadastrados = Filme.getVisões();
    initComponents();
    modelo_atuações = (DefaultListModel)atuaçõesList.getModel();
    filme_companhia_cinematográficaPainel = new PainelFilmeCompanhiaCinematográfica();
    filme_provedora_streamingPainel = new PainelFilmeProvedoraStreaming();
    especialização_filmeTabbedPane.addTab("Filme de Companhia Cinematográfica",
        filme_companhia_cinematográficaPainel);
    especialização_filmeTabbedPane.addTab("Filme de Provedora de Streaming",
        filme_provedora_streamingPainel);
    limparCampos(null);
}
```

## 3 - Visualizando alguns Atributos das Subclasses no ComboBox com os Filmes Cadastrados

Observe que no filmes_cadastradosComboBox estão sendo informados alguns atributos das subclasses: (a) para filme de companhia cinematográfica, foi acrescentada a informação de oscar de melhor filme; e (b) para filme de provedora de streaming foram acrescentadas as informações do nome da provedora de streaming e do total de episódios. Obviamente a informação de oscar de melhor filme, só será mostrada se o filme conquistou essa categoria de oscar. O total de episódios é um campo obrigatório e, portanto, deverá ser informado com o valor 1, quando o filme de provedora de streaming não for uma série.

Para que essas informações, oriundas de atributos das subclasses apareçam no filmes_cadastradosComboBox, os métodos construtor reduzido (utilizado para criar a visão), getVisão e toString, herdados da superclasse, devem ser sobrepostos nas subclasses.

As implementações dos métodos sobrepostos, na subclasse FilmeCompanhiaCinematográfica, são ilustradas a seguir.

```java
public FilmeCompanhiaCinematográfica(int sequencial, String título,
         boolean oscar_melhor_filme) {
    super(sequencial, título);
    this.oscar_melhor_filme = oscar_melhor_filme;
}

public FilmeCompanhiaCinematográfica getVisão () {
    return new FilmeCompanhiaCinematográfica (sequencial, título, oscar_melhor_filme);
}

public String toString() {
    String str = "[" + sequencial + "] " + título;
    if (oscar_melhor_filme) str += " - Oscar Melhor Filme";
    return str;
}
```

> **[Rodapé da página]** Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD

---

<!-- Página 7 -->

> **[Cabeçalho da página]** Linguagem de Programação II - Tutorial 3 - 7/14

As implementações dos métodos sobrepostos, na subclasse FilmeProvedoraStreaming, são ilustradas a seguir. O total de episódios só é mostrado no filmes_cadastradosComboBox, quando seu valor é maior que 1, ou seja, somente para séries.

```java
public FilmeProvedoraStreaming(int sequencial, String título, ProvedoraStreaming provedora,
         int total_episódios) {
    super(sequencial, título);
    this.provedora = provedora;
    this.produção = produção;
    this.total_episódios = total_episódios;
}

public FilmeProvedoraStreaming getVisão() {
    return new FilmeProvedoraStreaming(sequencial, título, provedora, total_episódios);
}

public String toString() {
    String str = "[" + sequencial + "] " + título + " --- " + provedora;
    if (total_episódios > 1) str += " - " + total_episódios + " episódios" ;
    return str;
}
```

## 4 - O Tratamento de Eventos para Inserir um Filme

No tratamento de eventos para inserir um filme, ocorrem as seguintes alterações nos seguintes métodos: (a) obtémFilmeInformado da classe JanelaCadastroFilme; e (b) buscarFilme e inserirFilme da classe Filme. As implementações de cada um desses métodos serão ilustradas em novas páginas, devido ao tamanho de seus códigos.

> **[Rodapé da página]** Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD

---

<!-- Página 8 -->

> **[Cabeçalho da página]** Linguagem de Programação II - Tutorial 3 - 8/14

A implementação do método obtémFilmeInformado da classe JanelaCadastroFilme, é ilustrada a seguir.

```java
private Filme obtémFilmeInformado() {
    String sequencial_str = sequencialTextField.getText();
    int sequencial = 0; // se inserção de novo filme : sequencial ainda será atribuído pelo BD
    if (!sequencial_str.isEmpty()) sequencial = Integer.parseInt(sequencial_str);
    String título = títuloTextField.getText();
    if (título.isEmpty()) return null;
    Gênero gênero = null;
    if (gêneroComboBox.getSelectedItem() != null)
       gênero = (Gênero)gêneroComboBox.getSelectedItem();
    else return null;
    String ano_str = anoTextField.getText();
    int ano = -1;
    if (!ano_str.isEmpty()) ano = Integer.parseInt(ano_str);
    else return null;
    Filme filme = null;
    int índice_aba_secionada = especialização_filmeTabbedPane.getSelectedIndex();
    switch (índice_aba_secionada) {
        case 0:
            boolean oscarMelhorFilme =
                filme_companhia_cinematográficaPainel.isOscarMelhorFilme();
            String oscar_melhor_diretor =
                filme_companhia_cinematográficaPainel.getOscarMelhorDiretor();
            String oscar_melhor_ator =
                filme_companhia_cinematográficaPainel.getOscarMelhorAtor();
            String oscar_melhor_atriz =
                filme_companhia_cinematográficaPainel.getOscarMelhorAtriz();
            filme = new FilmeCompanhiaCinematográfica (sequencial, título, gênero, ano,
                oscarMelhorFilme, oscar_melhor_diretor, oscar_melhor_ator, oscar_melhor_atriz);
            break;
        case 1:
            ProvedoraStreaming provedora_streaming
                = filme_provedora_streamingPainel.getSelectedProvedoraStreaming();
            if (provedora_streaming == null) return null;
            Produção produção = filme_provedora_streamingPainel.getSelectedProdução();
            if (produção == null) return null;
            int total_episódios = filme_provedora_streamingPainel.getTotalEpisódios();
            if (total_episódios == -1) return null;
            filme = new FilmeProvedoraStreaming (sequencial, título, gênero, ano,
                provedora_streaming, produção, total_episódios);
    }
    return filme;
}
```

> **[Rodapé da página]** Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD

---

<!-- Página 9 -->

> **[Cabeçalho da página]** Linguagem de Programação II - Tutorial 3 - 9/14

A implementação do método estático buscarFilme da classe Filme, é ilustrada a seguir.

```java
public static Filme buscarFilme (int sequencial) {
    String sql = null;
    ResultSet lista_resultados = null;
    sql = "SELECT Título, Gênero, Ano FROM Filmes WHERE Sequencial = ?";
    String título = null;
    Gênero gênero = null;
    int ano = 0;
    boolean nacional = false;
    try {
        PreparedStatement comando = BD.conexão.prepareStatement(sql);
        comando.setInt(1, sequencial);
        lista_resultados = comando.executeQuery();
        while (lista_resultados.next()) {
            título = lista_resultados.getString("Título");
            gênero = Gênero.values()[lista_resultados.getInt("Gênero")];
            ano = lista_resultados.getInt("Ano");
        }
        lista_resultados.close();
        comando.close();
    } catch (SQLException exceção_sql) { exceção_sql.printStackTrace (); }
    if (título == null) return null;
    sql = "SELECT OscarMelhorFilme, OscarMelhorDiretor, OscarMelhorAtor, OscarMelhorAtriz"
        + " FROM FilmesCompanhiasCinematográficas WHERE FilmeId = ?";
    lista_resultados = null;
    try {
        PreparedStatement comando = BD.conexão.prepareStatement(sql);
        comando.setInt(1, sequencial);
        lista_resultados = comando.executeQuery();
        while (lista_resultados.next()) {
            return new FilmeCompanhiaCinematográfica (sequencial, título, gênero, ano,
                 lista_resultados.getBoolean("OscarMelhorFilme"),
                 lista_resultados.getString("OscarMelhorDiretor"),
                 lista_resultados.getString("OscarMelhorAtor"),
                 lista_resultados.getString("OscarMelhorAtriz"));
        }
        lista_resultados.close();
        comando.close();
    } catch (SQLException exceção_sql) { exceção_sql.printStackTrace (); }
    sql = "SELECT ProvedoraStreaming, Produção, TotalEpisódios FROM FilmesProvedorasStreaming"
        + " WHERE FilmeId = ?";
    lista_resultados = null;
    try {
        PreparedStatement comando = BD.conexão.prepareStatement(sql);
        comando.setInt(1, sequencial);
        lista_resultados = comando.executeQuery();
        while (lista_resultados.next()) {
            return (new FilmeProvedoraStreaming (sequencial, título, gênero, ano,
            ProvedoraStreaming.values()[lista_resultados.getInt("ProvedoraStreaming")],
            Produção.values()[lista_resultados.getInt("Produção")],
            lista_resultados.getInt("TotalEpisódios")));
        }
        lista_resultados.close();
        comando.close();
    } catch (SQLException exceção_sql) { exceção_sql.printStackTrace (); }
    return null;
}
```

Uma observação importante é que será necessário retornar visões de objetos das subclasses no método getVisões. Para que isso seja possível será chamado o método acima buscarFilme no método getVisões, cuja implementação é mostrada na próxima página.

> **[Rodapé da página]** Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD

---

<!-- Página 10 -->

> **[Cabeçalho da página]** Linguagem de Programação II - Tutorial 3 - 10/14

```java
public static Filme[] getVisões () {
    String sql = "SELECT Sequencial FROM Filmes";
    ResultSet lista_resultados = null;
    ArrayList<Filme> visões = new ArrayList();
    try {
        PreparedStatement comando = BD.conexão.prepareStatement(sql);
        lista_resultados = comando.executeQuery();
        while (lista_resultados.next()) {
            visões.add(buscarFilme (lista_resultados.getInt("Sequencial")).getVisão());
        }
        lista_resultados.close();
        comando.close();
    } catch (SQLException exceção_sql) {exceção_sql.printStackTrace ();}
    return visões.toArray(new Filme[visões.size()]);
}
```

A implementação do método estático inserirFilme da classe Filme, é ilustrada a seguir.

```java
public static String inserirFilme (Filme filme) {
    String sql = "INSERT INTO Filmes (Título, Gênero, Ano) VALUES (?, ?, ?)";
    try {
        PreparedStatement comando = BD.conexão.prepareStatement(sql);
        comando.setString(1, filme.getTítulo());
        comando.setInt(2, filme.getGênero().ordinal());
        comando.setInt(3, filme.getAno());
        comando.executeUpdate();
        comando.close();
    } catch (SQLException exceção_sql) {
        exceção_sql.printStackTrace ();
        return "Erro na Inserção do Filme no BD";
    }
    int sequencial = últimoSequencial();
    if(filme instanceof FilmeCompanhiaCinematográfica) {
        FilmeCompanhiaCinematográfica filme_companhia_cinematográfica
            = (FilmeCompanhiaCinematográfica) filme;
        sql = "INSERT INTO FilmesCompanhiasCinematográficas"
            + " (OscarMelhorFilme, OscarMelhorDiretor,"
            + " OscarMelhorAtor, OscarMelhorAtriz, FilmeId) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setBoolean(1, filme_companhia_cinematográfica.isOscarMelhorFilme());
            comando.setString(2, filme_companhia_cinematográfica.getOscarMelhorDiretor());
            comando.setString(3, filme_companhia_cinematográfica.getOscarMelhorAtor());
            comando.setString(4, filme_companhia_cinematográfica.getOscarMelhorAtriz());
            comando.setInt(5, sequencial);
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace ();
            return "Erro na Inserção do FilmeCompanhiaCinematográfica no BD";
        }
    } else if(filme instanceof FilmeProvedoraStreaming) {
        FilmeProvedoraStreaming filme_provedora_streaming = (FilmeProvedoraStreaming) filme;
        sql = "INSERT INTO FilmesProvedorasStreaming"
            + " (ProvedoraStreaming, Produção, TotalEpisódios,"
            + " FilmeId) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, filme_provedora_streaming.getProvedora().ordinal());
            comando.setInt(2, filme_provedora_streaming.getProdução().ordinal());
            comando.setInt(3, filme_provedora_streaming.getTotalEpisódios());
            comando.setInt(4, sequencial);
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace ();
            return "Erro na Inserção do FilmeProvedoraStreaming no BD";
        }
    }
    return null;
}
```

> **[Rodapé da página]** Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD

---

<!-- Página 11 -->

> **[Cabeçalho da página]** Linguagem de Programação II - Tutorial 3 - 11/14

## 5 - O Tratamento de Eventos para Consultar um Filme

No tratamento de eventos para consultar um filme, o único método alterado e ainda não ilustrado, é o método consultarFilme da classe JanelaCadastroFilme, cuja implementação é ilustrada a seguir.

```java
private void consultarFilme(java.awt.event.ActionEvent evt) {
    Filme visão = (Filme) filmes_cadastradosComboBox.getSelectedItem();
    Filme filme = null;
    String mensagem_erro = null;
    int sequencial = -1;
    if (visão != null) {
        sequencial = visão.getSequencial();
        filme = Filme.buscarFilme(sequencial);
        if (filme == null) mensagem_erro = "Filme não cadastrado";
    } else mensagem_erro = "Nenhum filme selecionado";
    if (mensagem_erro == null) {
        sequencialTextField.setText(filme.getSequencial() + "");
        títuloTextField.setText (filme.getTítulo());
        gêneroComboBox.setSelectedItem(filme.getGênero());
        anoTextField.setText (filme.getAno() + "");
        atualizarListaAtuações(sequencial);
        if (filme instanceof FilmeCompanhiaCinematográfica) {
            especialização_filmeTabbedPane.setSelectedIndex(0);
            FilmeCompanhiaCinematográfica filme_companhia_cinematográfica
                = (FilmeCompanhiaCinematográfica) filme;
            filme_companhia_cinematográficaPainel.setOscarMelhorFilme
                (filme_companhia_cinematográfica.isOscarMelhorFilme());
            filme_companhia_cinematográficaPainel.setOscarMelhorDiretor
                (filme_companhia_cinematográfica.getOscarMelhorDiretor());
            filme_companhia_cinematográficaPainel.setOscarMelhorAtor
                (filme_companhia_cinematográfica.getOscarMelhorAtor());
            filme_companhia_cinematográficaPainel.setOscarMelhorAtriz
                (filme_companhia_cinematográfica.getOscarMelhorAtriz());
        } else if (filme instanceof FilmeProvedoraStreaming) {
             especialização_filmeTabbedPane.setSelectedIndex(1);
            FilmeProvedoraStreaming filme_provedora_streaming = (FilmeProvedoraStreaming) filme;
            filme_provedora_streamingPainel.setSelectedProvedoraStreaming
                (filme_provedora_streaming.getProvedora());
            filme_provedora_streamingPainel.setSelectedProdução
                (filme_provedora_streaming.getProdução().ordinal());
            filme_provedora_streamingPainel.setTotalEpisódios
                (filme_provedora_streaming.getTotalEpisódios());
        }
    } else informarErro (mensagem_erro);
}
```

> **[Rodapé da página]** Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD

---

<!-- Página 12 -->

> **[Cabeçalho da página]** Linguagem de Programação II - Tutorial 3 - 12/14

## 6 - O Tratamento de Eventos para Alterar um Filme

No tratamento de eventos para alterar um filme, ocorrem as seguintes alterações nos seguintes: métodos: (a) alterarFilme da classe JanelaCadastroFilme; e (b) alterarFilme da classe Filme.

Observe que como o usuário não consegue editar a chave sequencial, que é gerada pelo banco de dados, não é necessário consultar a visão alterada para modificar os seus atributos que não são chaves. Basta consultar a visão selecionada no ComboBox, porque a única forma que o usuário tem de informar a chave sequencial é consultar o objeto de interesse no ComboBox.

A implementação do método alterarFilme da classe JanelaCadastroFilme é ilustrada a seguir.

```java
private void alterarFilme(java.awt.event.ActionEvent evt) {
    Filme filme = obtémFilmeInformado();
    String mensagem_erro = null;
    if (filme != null) mensagem_erro = controlador.alterarFilme(filme);
    else mensagem_erro = "Algum atributo do filme não foi informado";
    if (mensagem_erro == null) {
        Filme visão = (Filme) filmes_cadastradosComboBox.getSelectedItem();
        if (visão != null) {
            visão.setTítulo(filme.getTítulo());
            if (filme instanceof FilmeCompanhiaCinematográfica) {
                FilmeCompanhiaCinematográfica filme_companhia
                    = (FilmeCompanhiaCinematográfica) filme;
                FilmeCompanhiaCinematográfica visão_companhia
                    = (FilmeCompanhiaCinematográfica) visão;
                visão_companhia.setOscarMelhorFilme(filme_companhia.isOscarMelhorFilme());
            } else if (filme instanceof FilmeProvedoraStreaming) {
                FilmeProvedoraStreaming filme_streaming = (FilmeProvedoraStreaming) filme;
                FilmeProvedoraStreaming visão_streaming = (FilmeProvedoraStreaming) visão;
                visão_streaming.setProvedora(filme_streaming.getProvedora());
                visão_streaming.setTotalEpisódios(filme_streaming.getTotalEpisódios());
            }
            filmes_cadastradosComboBox.updateUI();
        }
    } else informarErro (mensagem_erro);
}
```

> **[Rodapé da página]** Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD

---

<!-- Página 13 -->

> **[Cabeçalho da página]** Linguagem de Programação II - Tutorial 3 - 13/14

A implementação do método alterarFilme da classe Filme é ilustrada a seguir.

```java
public static String alterarFilme (Filme filme) {
    String sql = "UPDATE Filmes SET Título = ?, Gênero = ?, Ano = ? WHERE Sequencial = ?";
    try {
        PreparedStatement comando = BD.conexão.prepareStatement(sql);
        comando.setString(1, filme.getTítulo());
        comando.setInt(2, filme.getGênero().ordinal());
        comando.setInt(3, filme.getAno());
        comando.setInt(4, filme.getSequencial());
        comando.executeUpdate();
        comando.close();
    } catch (SQLException exceção_sql) {
        exceção_sql.printStackTrace ();
        return "Erro na Alteração do Filme no BD";
    }
    if(filme instanceof FilmeCompanhiaCinematográfica) {
        FilmeCompanhiaCinematográfica filme_companhia_cinematográfica
            = (FilmeCompanhiaCinematográfica) filme;
        sql = "UPDATE FilmesCompanhiasCinematográficas"
            + " SET OscarMelhorFilme = ?, OscarMelhorDiretor = ?,"
            + " OscarMelhorAtor = ?, OscarMelhorAtriz = ? WHERE FilmeId = ?";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setBoolean(1, filme_companhia_cinematográfica.isOscarMelhorFilme());
            comando.setString(2, filme_companhia_cinematográfica.getOscarMelhorDiretor());
            comando.setString(3, filme_companhia_cinematográfica.getOscarMelhorAtor());
            comando.setString(4, filme_companhia_cinematográfica.getOscarMelhorAtriz());
            comando.setInt(5, filme_companhia_cinematográfica.getSequencial());
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace ();
            return "Erro na Inserção do FilmeCompanhiaCinematográfica no BD";
        }
    } else if(filme instanceof FilmeProvedoraStreaming) {
        FilmeProvedoraStreaming filme_provedora_streaming = (FilmeProvedoraStreaming) filme;
        sql = "UPDATE FilmesProvedorasStreaming SET ProvedoraStreaming = ?, Produção = ?,"
            + " TotalEpisódios = ? WHERE FilmeId = ?";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, filme_provedora_streaming.getProvedora().ordinal());
            comando.setInt(2, filme_provedora_streaming.getProdução().ordinal());
            comando.setInt(3, filme_provedora_streaming.getTotalEpisódios());
            comando.setInt(4, filme_provedora_streaming.getSequencial());
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace ();
            return "Erro na Alteração do Filme no BD";
        }
    }
    return null;
}
```

> **[Rodapé da página]** Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD

---

<!-- Página 14 -->

> **[Cabeçalho da página]** Linguagem de Programação II - Tutorial 3 - 14/14

## 7 - O Tratamento de Eventos para Remover um Filme

No tratamento de eventos para remover um filme, o único método alterado e ainda não ilustrado, é o método estático removerFilme da classe Filme, cuja implementação é ilustrada a seguir.

```java
public static String removerFilme (Filme filme) {
    int sequencial = filme.getSequencial();
    Atuação.removerAtuações(sequencial);
    if (filme instanceof FilmeCompanhiaCinematográfica) {
        String sql = "DELETE FROM FilmesCompanhiasCinematográficas WHERE FilmeId = ?";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, sequencial);
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace ();
            return "Erro na Remoção do FilmeOriginal do BD";
        }
    } else if (filme instanceof FilmeProvedoraStreaming) {
        String sql = "DELETE FROM FilmesProvedorasStreaming WHERE FilmeId = ?";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, sequencial);
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace ();
            return "Erro na Remoção do FilmeOriginal do BD";
        }
    }
    String sql = "DELETE FROM Filmes WHERE Sequencial = ?";
    try {
        PreparedStatement comando = BD.conexão.prepareStatement(sql);
        comando.setInt(1, sequencial);
        comando.executeUpdate();
        comando.close();
    } catch (SQLException exceção_sql) {
        exceção_sql.printStackTrace ();
        return "Erro na Remoção do Filme do BD";
    }
    return null;
}
```

## 8 - O Tratamento de Eventos para Limpar Campos

No tratamento de eventos para limpar, a alteração ocorre no método auxiliar privado limparCampos da classe JanelaCadastroFilme. Observe que os métodos limparCampos dos painéis não recebem null como argumento porque não foram associados a evento gerado por clicar no botão Limpar, que não foi definido nesses painéis.

```java
private void limparCampos(java.awt.event.ActionEvent evt) {
    sequencialTextField.setText("");
    títuloTextField.setText ("");
    gêneroComboBox.setSelectedIndex(-1);
    anoTextField.setText ("");
    modelo_atuações.clear();
    filme_companhia_cinematográficaPainel.limparCampos();
    filme_provedora_streamingPainel.limparCampos();
}
```

> **[Rodapé da página]** Prof. Joinvile Batista Junior - Sistemas de Informação - FACET/UFGD

---

# Auditoria da Transcrição

- Páginas do PDF: 14
- Páginas verificadas: 14
- Imagens encontradas: 2
- Imagens preservadas: 2 (`imagens/pagina-002-imagem-001.png` e `imagens/pagina-003-imagem-001.png`, extraídas do PDF em resolução original, 1188×896 e 1186×895; texto de ambas transcrito logo abaixo de cada imagem)
- Tabelas encontradas: 0
- Tabelas transcritas: 0
- Blocos de código encontrados: 19
- Blocos de código transcritos: 19
- Conteúdo ilegível: 0 ocorrências
- Conteúdo não transcrito ou não extraído: nenhum

## Como a verificação foi feita

- O texto de cada uma das 14 páginas do `.md` foi comparado com duas extrações independentes do PDF (pypdf e pdfplumber): caracteres alfanuméricos idênticos em todas as páginas.
- Cada linha de código do `.md` foi comparada com as linhas em fonte Courier do PDF (pdfplumber): mesmo número de linhas e mesmo conteúdo em todas as páginas (páginas 1 a 14; a página 3 não tem código).
- As duas imagens foram abertas e inspecionadas visualmente; todo o texto e estado dos componentes visíveis nelas foi transcrito.
- Não existem gráficos vetoriais, tabelas, fórmulas, notas de rodapé ou hyperlinks no PDF (além do cabeçalho e rodapé de cada página, que foram transcritos).

## Convenções usadas nesta transcrição

- As quebras de linha do PDF dentro de um mesmo parágrafo foram unidas (são apenas quebras automáticas de linha). Nenhuma palavra foi alterada.
- Cabeçalho e rodapé de cada página estão marcados com `[Cabeçalho da página]` e `[Rodapé da página]`.
- O código foi copiado sem correção. Foi removido apenas o recuo de margem comum a todo o bloco; a indentação relativa entre as linhas foi mantida.
- Em Markdown, nomes de classes e termos que aparecem em azul no PDF aparecem como texto simples (cor e fonte não foram preservadas).
- Os textos que descrevem as imagens (alt text e a lista em bloco de citação abaixo delas) foram escritos pelo transcritor; o texto contido nas próprias imagens está transcrito literalmente entre aspas.

## Observações do transcritor sobre o documento original (nada foi corrigido no corpo)

Estas observações não fazem parte do PDF. Servem apenas para quem for implementar, indicando pontos em que o PDF é inconsistente ou incompleto. O corpo da transcrição mantém tudo exatamente como está no PDF.

Inconsistências e erros presentes no PDF, preservados na transcrição:

- Erros de digitação no texto: "premição" (p. 1), "representarm" e "possem" (p. 2), "para para" (p. 3), "seguintes os componentes" (p. 4), "com os seguintes os componentes" e "repectivos" (p. 5), "O dados" e "adicinar" (p. 6).
- Na página 3, o texto diz "Na seção 4, 5, 6 e 7" para criação, alteração, remoção e limpeza, mas o documento tem as seções 4, 5, 6, 7 e 8 (a limpeza está na seção 8, p. 14).
- O botão da direita na imagem da página 2 se chama "Atuações"; na imagem da página 3 se chama "Atores".
- A classe da janela aparece como `JanelaCadastroFilmes` na maior parte do texto e como `JanelaCadastroFilme` (sem "s") nas páginas 7, 8, 11, 12 e 14.
- Página 1: os atributos da subclasse misturam `oscar_melhor_filme` (com underscores) e `oscarMelhorDiretor`, `oscarMelhorAtor`, `oscarMelhorAtriz` (camelCase). Página 4: o texto cita os atributos `oscar_filme`, `oscar_diretor`, `oscar_ator`, `oscar_atriz`, enquanto o código usa `oscar_filmeCheckBox`, `oscar_diretorTextField`, etc.
- Página 7: o construtor reduzido de `FilmeProvedoraStreaming` tem 4 parâmetros (`sequencial`, `título`, `provedora`, `total_episódios`), sem `produção`, mas contém a linha `this.produção = produção;`. Além disso, `getVisão()` chama esse construtor de 4 parâmetros.
- Página 9: `buscarFilme` declara `boolean nacional = false;`, que não é usado.
- Página 13: em `alterarFilme` (classe `Filme`), o tratamento de erro do UPDATE de `FilmesCompanhiasCinematográficas` retorna "Erro na Inserção do FilmeCompanhiaCinematográfica no BD".
- Página 14: as mensagens de erro de remoção das subclasses dizem "Erro na Remoção do FilmeOriginal do BD".
- Página 5: `getSelectedProdução()` usa `produçãoButtonGroup.getSelection().getMnemonic()` como índice de `Produção.values()`. O PDF não descreve como os mnemônicos dos RadioButtons são configurados.
- O `switch` de `obtémFilmeInformado` (p. 8) não tem `break` no `case 1`.

Itens usados no código, mas cuja definição não aparece neste PDF (provavelmente definidos em tutoriais anteriores ou gerados pelo NetBeans; o PDF não os define):

- Definição completa das classes `Filme`, `FilmeCompanhiaCinematográfica` e `FilmeProvedoraStreaming` (construtores completos com `(sequencial, título, gênero, ano, ...)`, getters e setters como `getTítulo`, `getGênero`, `getAno`, `getSequencial`, `isOscarMelhorFilme`, `getOscarMelhorDiretor`, `getProvedora`, `getProdução`, `getTotalEpisódios`, `setTítulo`, `setOscarMelhorFilme`, `setProvedora`, `setTotalEpisódios`).
- Enum `Gênero`, comando `CREATE TABLE Filmes` (colunas referenciadas: `Sequencial`, `Título`, `Gênero`, `Ano`), classe `BD` (`BD.conexão`), método `últimoSequencial()`, `Atuação.removerAtuações`, `atualizarListaAtuações`, `informarErro`, `ControladorCadastroFilmes` (incluindo `alterarFilme`), `initComponents()` e o layout gerado pelo NetBeans para `JanelaCadastroFilmes` e para os dois painéis.