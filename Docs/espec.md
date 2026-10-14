Linguagem de Programação II - Etapa *n** - Leonardo Farias de Oliveira 

# Projeto : Orçamento de Peças de um Sinistro para uma Seguradora

## Entidades

- Peça : Peças
- Sinistro : Sinistros
- Seguradora : Seguradoras
- PeçaSinistro : PeçasSinistros
- PeçaMecânica : PeçasMecânicas
- PecaLataria : PecasLatarias
- Orçamento : Orçamentos

## Relacionamentos

- Sem Referências : Peça, Seguradora
- Relacionamento Múltiplo : Sinistro [n:n] Peça (PeçaSinistro)
- Associação : Orcamento [Sinistro, Seguradora]
- Herança : Peça [PeçaMecânica, PeçaLataria]

## Atributos e Referências

- Sinistro: id, segurado, cidade, telefone, grau_monta, perda_total, peças
- Seguradora: nome, cidade, cobertura_percentual, possui_atendimento_24h, - 
forma_pagamento_preferencial
- Peça: código, nome, marca, preço
- PeçaMecânica : categoria_mecânica, prazo_garantia, mecânico_próprio
- PeçaAcessórios : categoria_acessórios, cor, funileiro_próprio
- Orçamento: status, data, sinistro, seguradora, possui_danos_ocultos

## Enumerados

- marca: original, genuína, OEM
- categoria_mecânica: motor, transmissão, arrefecimento, suspensão, direção
- categoria_acessórios: carroceria, farol_lanterna, parabrisas_vigia, painel, pedal, assento, 
assoalho
- forma_pagamento_preferencial: boleto, debito_automatico, pix, cartao
- grau_monta: pequena, media, grande
- status: aprovado, pendente, recusado