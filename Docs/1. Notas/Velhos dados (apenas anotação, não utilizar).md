# Esse nao é um documento utilizável, serve apenas para lembrar o nomes dos objetos que foram utilziados em outros projetos.

# Projeto Seguro 🚗

## 1. Entidades

- Sinistro
- Seguradora
- Peça
- Orçamento

## 2. Enumerados

| Enumerado | Valores |
| --- | --- |
| tipo\_peca\_carro | farol, parachoques, capo, lanternas, portas |
| tipo\_peca\_moto | escapamento, pistao, biela, cabecote |
| marca\_peca | original, genuina, oem |
| forma\_pagamento\_preferencial | boleto, debito\_automatico, pix, cartao |
| grau\_monta | pequena, media, grande |

## 3. Tabela: Seguradoras

| Nome | Cidade | Cobertura (%) | Atendimento 24h | Forma de pagamento |
| --- | --- | --- | --- | --- |
| Porto Seguro | Dourados | 90 | Sim | boleto |
| Bradesco Seguros | Campo Grande | 90 | Sim | debito\_automatico |
| SulAmérica | Ponta Porã | 90 | Não | pix |
| Mapfre | Dourados | 85 | Sim | cartao |
| Allianz | Campo Grande | 75 | Não | boleto |
| Tokio Marine | Ponta Porã | 90 | Sim | pix |
| Azos Seguros | São Paulo | 99 | Sim | debito\_automatico |
| Liberty Seguros | Dourados | 85 | Não | boleto |
| Sompo Seguros | Maracajú | 75 | Não | cartao |
| HDI Seguros | Naviraí | 82 | Sim | pix |

## 4. Tabela: Sinistros

| Segurado | Telefone | Cidade | Grau de monta | Perda total |
| --- | --- | --- | --- | --- |
| Leon | 67 99888-1100 | Dourados | grande | Não |
| Scott | 67 98988-1101 | Campo Grande | media | Não |
| Kennedy | 67 96898-1000 | Ponta Porã | grande | Sim |
| Chris | 67 90898-0100 | Dourados | media | Não |
| Redfield | 66 91681-1198 | Naviraí | grande | Sim |
| Jill | 67 99234-8811 | Campo Grande | pequena | Não |
| Claire | 67 98112-2290 | Dourados | media | Não |
| Ada | 65 99677-4400 | Ponta Porã | pequena | Sim |
| Rebecca | 67 99123-4455 | Maracajú | pequena | Não |
| Carlos | 67 98455-7712 | Naviraí | media | Não |

## 5. Tabela: Peças

| Código | Sinistro | Nome | Marca | Preço | Mão de obra própria | Tipo de registro | Tipo carro | Tipo moto | Garantia (dias) | Cor |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 11 | Leon | Bloco do motor | oem | 245,00 | Não | mecanica | - | cabecote | 180 | - |
| 12 | Leon | Cabeçote | oem | 250,00 | Não | mecanica | - | cabecote | 180 | - |
| 21 | Scott | Parachoques dianteiro | original | 200,00 | Não | lataria | parachoques | - | - | preto |
| 22 | Scott | Capo | original | 180,00 | Não | lataria | capo | - | - | cinza |
| 31 | Kennedy | Pistao | oem | 180,00 | Sim | mecanica | - | pistao | 120 | - |
| 33 | Kennedy | Biela | oem | 220,00 | Sim | mecanica | - | biela | 120 | - |
| 41 | Chris | Painel frontal | oem | 135,00 | Sim | lataria | capo | - | - | cinza |
| 44 | Chris | Porta dianteira | oem | 60,00 | Sim | lataria | portas | - | - | cinza |
| 51 | Redfield | Escapamento | genuina | 150,00 | Sim | mecanica | - | escapamento | 365 | - |
| 55 | Redfield | Biela reforcada | genuina | 230,00 | Sim | mecanica | - | biela | 365 | - |
| 61 | Jill | Bomba de oleo | oem | 150,00 | Não | mecanica | - | cabecote | 180 | - |
| 62 | Jill | Mola | oem | 210,00 | Não | mecanica | - | cabecote | 180 | - |
| 71 | Claire | Retrovisor | genuina | 210,00 | Não | lataria | farol | - | - | cinza |
| 72 | Claire | Lanterna traseira | oem | 170,00 | Não | lataria | lanternas | - | - | cinza |
| 81 | Ada | Radiador | genuina | 160,00 | Sim | mecanica | - | cabecote | 365 | - |
| 82 | Ada | Bieleta | genuina | 140,00 | Sim | mecanica | - | cabecote | 365 | - |
| 91 | Rebecca | Farol | original | 140,00 | Não | lataria | farol | - | - | prata |
| 92 | Rebecca | Lanterna | oem | 200,00 | Sim | lataria | lanternas | - | - | preto |
| 101 | Carlos | Barra estabilizadora | oem | 190,00 | Não | mecanica | - | biela | 180 | - |
| 102 | Carlos | Bucha estabilizadora | original | 320,00 | Sim | mecanica | - | biela | 365 | - |

## 6. Tabela: Orçamentos

| Data | Sinistro (segurado) | Seguradora |
| --- | --- | --- |
| 2026-06-22 | Leon | Porto Seguro |
| 2026-06-23 | Scott | Bradesco Seguros |
| 2026-06-24 | Kennedy | SulAmérica |
| 2026-06-25 | Chris | Mapfre |
| 2026-06-26 | Redfield | Allianz |
| 2026-06-27 | Jill | Tokio Marine |
| 2026-06-28 | Claire | Azos Seguros |
| 2026-06-29 | Ada | Liberty Seguros |
| 2026-06-30 | Rebecca | Sompo Seguros |
| 2026-07-01 | Carlos | HDI Seguros |