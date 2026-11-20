# Comparativo das entidades

## Frase tema comparado com outros projetos
1. **Orçamentos** de **Peças** de um **Sinistro** por uma **Seguradora** 
2. **Avaliações** de **Atores** de um **Filme** por **Amigos** (esse está invertido de propósito "Filme $\rightarrow$ Atores")
3. **Mostra** de **Livros** de um **Autor** por uma **Editora**
4. **Apresentação** de **peças musicais** do **repertório** de um **maestro**
5. **Lançamentos** de **veículos** de uma **montadora** por uma **agência de Publicidade**  

## Papeis lógicos das classes principais comparado com outros projetos
| Frase | Associadora | Secundária do relacionamento múltiplo | Primária do relacionamento múltiplo | Entidade sem referência | Entidade Auxiliar |
|---|---|---|---|---|---|
| 1. | Orçamento | Peças | Sinistro | Seguradora | PeçaSinistro |
| 2. | Avaliações | Filme | Ator | Amigos | Elenco |
| 3. | Mostra | Livros | Autor | Editora | - |
| 4. | Apresentação | Peças musicais | Repertório | Maestro | Setlist |
| 5. | Lançamento | Veículos | Montadora | Agência de publicidade | - |

## Relacionamentos comparado com outros projetos
| Frase | Sem referencia | Relacionamento Múltiplo | Associação | Herança |
|---|---|---|---|---|
| 1. | Peça e Seguradora | Sinistro [n:n] Peça (PeçaSinistro) | Orçamento [Sinistro, Seguradora] | Peça [PeçaMecânica, PeçaAcessório] |
| 2. | Atores e Amigos | Atores [n:n] Filmes (Elenco) | Avaliações [Filme, Amigos] | Filme [Companhias Cinematográfica, Companhias de Streaming] |
| 3. | Livros e Editora | Autor [1:n] Livros | Mostra [Autor, Editora] | Livros [Ebook, Físico] |
| 4. | peças musicais e maestro | repertório [n:n] peças musicais (Setlist) | Apresentação [repertório, maestro] | peças musicais [Classicas, Barroco] |
| 5. | veículos e agência de Publicidade | montadora [1:n] veículos | Lançamentos [montadora, agência de Publicidade] | veículos [Passeio, Caminhão] |

## Papeis lógicos das subclasses comparado com outros projetos
| Entidades | Seguro | Avaliação de filmes | Divulgação de Livros |  Música 🎼 | Lançamentos de carros | 
| --- | --- | --- | --- | --- | --- |
Subclasse 1 | Peças Mecanicas | Companhias Cinematográfica | Ebook | Classicas | Passeio |
Subclasse 2 | Peças Acessórios | Companhias de Streaming | Físico | Barroco | Caminhão |

# Notações deste projeto

## Atributos deste projeto
| Orçamento | Peças | PeçaMecânica | PeçaAcessórios | Sinistro | Seguradora | 
|---|---|---|---|---|---|
| status | <u>código</u> | categoria_mecânica | categoria_acessórios | <u>id</u> | <u>nome</u> |
| data | nome | prazo_garantia | cor | segurado | cidade |
| **sinistro** | marca | mecânico_próprio | funileiro_próprio | cidade | cobertura_percentual |
| **seguradora** | preço | - | - | telefone | possui_atendimento_24h |
| possui_danos_ocultos | sobEncomenda | - | - | grau_monta | forma_pagamento_preferencial |
| - | - | - | - | perda_total |  - |
| - | - | - | - | **peças** | - |

## Enumerados deste projeto
| Variáveis | 1 | 2 | 3 | 4 | 5 |
|---|---|---|---|---|---|
| marca | original | genuína | OEM | - | - 
| categoria_mecânica | motor | transmissão | arrefecimento | suspensão | direção |
| categoria_acessórios | farol_lanterna | parabrisas_vigia | painel | pedal | assento | assoalho |
| forma_pagamento_preferencial | boleto | debito_automatico | pix | cartao | - |
| grau_monta | pequena | media | grande | -| - |
| status | aprovado | pendente | recusado | -| - |
