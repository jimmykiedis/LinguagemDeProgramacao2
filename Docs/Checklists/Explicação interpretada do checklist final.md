# Como montar o álbum de prints do trabalho

Pense no seu trabalho como um **álbum de fotos para provar que o programa funciona**. Cada print (foto da tela) precisa mostrar uma coisa bem certinha.

## Antes de começar

No começo de **cada página**, escreva uma frase contando o que o print daquela página vai mostrar.

Por exemplo: **"Este print mostra a pesquisa sem nenhum filtro."**

Primeiro, faça os prints pedidos na Etapa 3, mas **não apague nenhum objeto**. As remoções ficam para outra parte.

## Quem é quem no seu trabalho

Aqui está a tradução das palavras do enunciado para os nomes do seu programa:

- **Entidade associadora** (a "ficha" que liga duas coisas): `Orçamento`, que liga um `Sinistro` a uma `Seguradora`.
- **Relacionamento múltiplo:** `Sinistro` e `Peça`, ligados por `PeçaSinistro`.
- **Entidade principal do relacionamento múltiplo:** `Sinistro`.
- **Entidade secundária do relacionamento múltiplo:** `Peça`.
- **Entidades sem referência:** `Peça` e `Seguradora`.
- **Superclasse:** `Peça`.
- **Subclasses:** `PeçaMecânica` e `PeçaAcessório`.

> ⚠️ **Dúvida para o professor:** o enunciado diz que a entidade associadora é a que liga duas coisas. No seu `espec.md`, quem liga `Sinistro` a `Peça` é `PeçaSinistro`, mas `Orçamento` é quem tem referências a `Sinistro` e `Seguradora`. Não fui capaz de interpretar com certeza qual das duas é a "entidade associadora" dos prints. Nesta versão usei `Orçamento`, mas **pergunte ao professor mais tarde**. Enquanto isso, use os nomes que o programa mostra na tela para identificar cada entidade.

## Na tela de cadastro da entidade associadora (`Orçamento`)

- **Print +1 – Cadastro de `Orçamento`:** cadastre só **3 orçamentos**. Depois, deixe o formulário preenchido com os dados do terceiro e abra a lista do ComboBox para mostrar as opções. Não cadastre um quarto orçamento.
- **Print +2 – Consulta de `Orçamento`:** mostre a consulta do segundo orçamento.

## O que mostrar no ComboBox

Quando o ComboBox listar peças, cada opção deve dizer a qual subclasse a peça pertence. Por exemplo:

- `[1] PeçaMecânica : ...`
- `[2] PeçaAcessório : ...`

Assim, quem olhar consegue saber de que tipo é cada peça.

> ⚠️ **Dúvida para o professor:** a especificação diz que `Orçamento` liga `Sinistro` e `Seguradora`, mas não diz que ele lista `Peça`. Então o ComboBox da tela de orçamento pode não mostrar peças. Não fui capaz de interpretar onde essa regra deve aparecer. Minha suposição é que seja no ComboBox onde as peças aparecem (talvez o cadastro de `Sinistro`), mas **pergunte ao professor mais tarde**.

## Na tela de pesquisa

Em cada print, mostre **todos os resultados** que a pesquisa encontrou. Antes de fazer outra pesquisa, limpe os resultados anteriores.

Quando você acrescentar um filtro, a lista de resultados deve ficar menor em pelo menos um objeto. É como procurar brinquedos: cada pista ajuda a encontrar menos brinquedos. Por isso, prepare seus dados para que isso aconteça.

- **Print +3 – Sem filtros:** pesquise `Orçamento` sem preencher nenhum filtro.
- **Print +4 – Filtro de entidade sem referência que NÃO é a secundária:** filtre por `Seguradora`.
- **Print +5 – Filtro da entidade sem referência que É a secundária, com "todos os objetos" desligado:** filtre por `Peça` relacionada ao `Sinistro`.
- **Print +6 – Mesmo filtro, com "todos os objetos" ligado:** repita a pesquisa por `Peça`, agora com a opção "todos os objetos" ligada.
- **Print +7 – Filtro da entidade principal do relacionamento múltiplo:** filtre por `Sinistro`.
- **Print +8 – Filtro da entidade associadora:** filtre por `Orçamento`.
- **Print +9 – Filtro de uma subclasse:** filtre por `PeçaMecânica`.
- **Print +10 – Filtro da outra subclasse:** filtre por `PeçaAcessório`.
- **Print +11 – Dois filtros juntos:** combine um filtro que não seja da superclasse (por exemplo, `Seguradora`) com o filtro de `PeçaMecânica`.
- **Print +12 – Dois filtros juntos:** faça a mesma coisa, mas usando `PeçaAcessório` no lugar de `PeçaMecânica`.

Nos prints +4 a +10, cada pesquisa usa **só** o filtro indicado. Nos prints +11 e +12, os **dois filtros precisam estar preenchidos ao mesmo tempo**.

> ⚠️ **Dúvida para o professor:** o enunciado fala em "comparar com a pesquisa anterior", mas não explica qual é a pesquisa anterior de cada print. Não fui capaz de interpretar isso com certeza. Pergunte ao professor se a comparação é sempre com o Print +3 (sem filtros) ou com a pesquisa logo antes.

## Quantos objetos cadastrar

Cadastre pelo menos **4 objetos das subclasses**:

- **2 `PeçaMecânica`**;
- **2 `PeçaAcessório`**.

Cada subclasse precisa ter pelo menos **2 atributos próprios** e também herdar pelo menos **2 atributos** da superclasse:

- `PeçaMecânica` tem `categoria_mecânica`, `prazo_garantia` e `mecânico_próprio`.
- `PeçaAcessório` tem `categoria_acessórios`, `cor` e `funileiro_próprio`.
- As duas herdam de `Peça` atributos como `código`, `nome`, `marca`, `preço` e `sobEncomenda`.

No relacionamento múltiplo (`Sinistro` e `Peça`), ligue **4 peças**: **2 que combinem** com o filtro usado em `Peça` e **2 que não combinem**. Assim dá para ver que o filtro realmente muda os resultados.

## Resumo rápido (para não esquecer)

1. Escreva uma frase no começo de cada página.
2. Não apague nenhum objeto.
3. Cadastre só 3 orçamentos.
4. Limpe os resultados antes de cada pesquisa.
5. Mostre todos os resultados em cada print.
6. Cadastre 2 `PeçaMecânica` e 2 `PeçaAcessório`.
7. Ligue 4 peças ao `Sinistro` (2 combinam com o filtro, 2 não).
8. Anote as dúvidas e pergunte ao professor.