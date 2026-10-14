-- Migração da base da etapa 2 para a etapa 3.
-- Executar no banco de dados banco. Não apaga os registros existentes.

-- Compatibiliza o nome da coluna de preço caso a base ainda use "preco".
SELECT COUNT(*) INTO @tem_preco_antigo
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'peças'
  AND COLUMN_NAME = 'preco';

SELECT COUNT(*) INTO @tem_preco_novo
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'peças'
  AND COLUMN_NAME = 'preço';

SET @sql_migracao = IF(
    @tem_preco_antigo > 0 AND @tem_preco_novo = 0,
    'ALTER TABLE `peças` CHANGE COLUMN `preco` `preço` DECIMAL(10,2)',
    'SELECT 1'
);
PREPARE migracao_preco FROM @sql_migracao;
EXECUTE migracao_preco;
DEALLOCATE PREPARE migracao_preco;

-- Remove o campo legado substituído pelos checkboxes das subclasses.
SELECT COUNT(*) INTO @tem_mao_obra_propria
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'peças'
  AND COLUMN_NAME = 'mao_obra_propria';

SET @sql_remover_mao_obra = IF(@tem_mao_obra_propria > 0,
    'ALTER TABLE `peças` DROP COLUMN `mao_obra_propria`',
    'SELECT 1');
PREPARE remover_mao_obra FROM @sql_remover_mao_obra;
EXECUTE remover_mao_obra;
DEALLOCATE PREPARE remover_mao_obra;

CREATE TABLE IF NOT EXISTS peça_mecânica (
    categoria_mecânica VARCHAR(40) NOT NULL,
    prazo_garantia INT,
    mecanico_proprio BOOLEAN NOT NULL DEFAULT FALSE,
    codigoId INT NOT NULL,
    FOREIGN KEY (codigoId) REFERENCES peças(codigo) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS peça_acessórios (
    categoria_acessorio VARCHAR(40) NOT NULL,
    cor VARCHAR(20),
    funileiro_proprio BOOLEAN NOT NULL DEFAULT FALSE,
    codigoId INT NOT NULL,
    FOREIGN KEY (codigoId) REFERENCES peças(codigo) ON DELETE CASCADE
);

-- Atualiza tabelas já existentes sem apagar as peças cadastradas.
SELECT COUNT(*) INTO @tem_categoria_lataria
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'peça_acessórios'
  AND COLUMN_NAME = 'categoria_lataria';

SELECT COUNT(*) INTO @tem_categoria_acessorio
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'peça_acessórios'
  AND COLUMN_NAME = 'categoria_acessorio';

SET @sql_categoria = IF(
    @tem_categoria_lataria > 0 AND @tem_categoria_acessorio = 0,
    'ALTER TABLE `peça_acessórios` CHANGE COLUMN `categoria_lataria` `categoria_acessorio` VARCHAR(40) NOT NULL',
    'SELECT 1'
);
PREPARE migracao_categoria FROM @sql_categoria;
EXECUTE migracao_categoria;
DEALLOCATE PREPARE migracao_categoria;

SELECT COUNT(*) INTO @tem_mecanico_proprio
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'peça_mecânica'
  AND COLUMN_NAME = 'mecanico_proprio';

SET @sql_mecanico = IF(@tem_mecanico_proprio = 0,
    'ALTER TABLE `peça_mecânica` ADD COLUMN `mecanico_proprio` BOOLEAN NOT NULL DEFAULT FALSE',
    'SELECT 1');
PREPARE migracao_mecanico FROM @sql_mecanico;
EXECUTE migracao_mecanico;
DEALLOCATE PREPARE migracao_mecanico;

SELECT COUNT(*) INTO @tem_funileiro_proprio
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'peça_acessórios'
  AND COLUMN_NAME = 'funileiro_proprio';

SET @sql_funileiro = IF(@tem_funileiro_proprio = 0,
    'ALTER TABLE `peça_acessórios` ADD COLUMN `funileiro_proprio` BOOLEAN NOT NULL DEFAULT FALSE',
    'SELECT 1');
PREPARE migracao_funileiro FROM @sql_funileiro;
EXECUTE migracao_funileiro;
DEALLOCATE PREPARE migracao_funileiro;
