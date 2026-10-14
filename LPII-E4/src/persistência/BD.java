package persistência;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {

    static final String URL_BD = "jdbc:mysql://localhost/banco?useUnicode=true&characterEncoding=utf8&connectionCollation=utf8mb4_unicode_ci&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    static final String USUARIO = "root";
    static final String SENHA = "admin";
    public static Connection conexao = null;
    
    public static void criaConexao() {
        try {
            conexao = DriverManager.getConnection(URL_BD, USUARIO, SENHA);
            atualizarEsquemaEtapa3();
            atualizarEsquemaEtapa4();
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
        }
    }

    private static void atualizarEsquemaEtapa3() throws SQLException {
        boolean temPrecoAntigo;
        boolean temPrecoNovo;
        String sqlColuna = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS "
                + "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'peças' AND COLUMN_NAME = ?";
        try (PreparedStatement comando = conexao.prepareStatement(sqlColuna)) {
            comando.setString(1, "preco");
            try (ResultSet resultado = comando.executeQuery()) {
                resultado.next();
                temPrecoAntigo = resultado.getInt(1) > 0;
            }
            comando.setString(1, "preço");
            try (ResultSet resultado = comando.executeQuery()) {
                resultado.next();
                temPrecoNovo = resultado.getInt(1) > 0;
            }
        }

        if (temPrecoAntigo && !temPrecoNovo) {
            try (Statement comando = conexao.createStatement()) {
                comando.executeUpdate("ALTER TABLE peças CHANGE COLUMN preco `preço` DECIMAL(10,2)");
            }
        }

        try (Statement comando = conexao.createStatement()) {
            removerColunaSeExistir(comando, "peças", "mao_obra_propria");
            comando.executeUpdate("CREATE TABLE IF NOT EXISTS peça_mecânica ("
                    + "categoria_mecânica VARCHAR(40) NOT NULL, prazo_garantia INT, mecanico_proprio BOOLEAN NOT NULL DEFAULT FALSE, "
                    + "codigoId INT NOT NULL PRIMARY KEY, FOREIGN KEY (codigoId) REFERENCES peças(codigo) ON DELETE CASCADE)");
            comando.executeUpdate("CREATE TABLE IF NOT EXISTS peça_acessórios ("
                    + "categoria_acessorio VARCHAR(40) NOT NULL, cor VARCHAR(20), funileiro_proprio BOOLEAN NOT NULL DEFAULT FALSE, "
                    + "codigoId INT NOT NULL PRIMARY KEY, FOREIGN KEY (codigoId) REFERENCES peças(codigo) ON DELETE CASCADE)");
            renomearCategoriaAcessorioLegada(comando);
            garantirColuna(comando, "peça_mecânica", "mecanico_proprio", "BOOLEAN NOT NULL DEFAULT FALSE");
            garantirColuna(comando, "peça_acessórios", "funileiro_proprio", "BOOLEAN NOT NULL DEFAULT FALSE");
            garantirCodigoUnico(comando, "peça_mecânica");
            garantirCodigoUnico(comando, "peça_acessórios");
        }
    }

    /** Cria a tabela de orçamentos quando o banco existente ainda está na etapa 3. */
    private static void atualizarEsquemaEtapa4() throws SQLException {
        try (Statement comando = conexao.createStatement()) {
            comando.executeUpdate("CREATE TABLE IF NOT EXISTS `Orçamentos` ("
                    + "data DATE NOT NULL, sinistro INT NOT NULL, seguradora VARCHAR(50) NOT NULL, "
                    + "status VARCHAR(10) NOT NULL, valor_total DECIMAL(10,2) NOT NULL, "
                    + "possui_danos_ocultos BOOLEAN NOT NULL DEFAULT FALSE, "
                    + "PRIMARY KEY (sinistro, seguradora), "
                    + "FOREIGN KEY (sinistro) REFERENCES sinistros(id), "
                    + "FOREIGN KEY (seguradora) REFERENCES seguradoras(nome))");
        }
    }

    private static void garantirCodigoUnico(Statement comando, String tabela) throws SQLException {
        String sql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA=DATABASE() "
                + "AND TABLE_NAME='" + tabela + "' AND COLUMN_NAME='codigoId' AND NON_UNIQUE=0";
        try (ResultSet resultado = comando.executeQuery(sql)) {
            resultado.next();
            if (resultado.getInt(1) == 0) {
                // Dados legados podem conter várias linhas para a mesma peça; preserva uma antes da chave única.
                String campos = tabela.equals("peça_mecânica")
                        ? "MAX(categoria_mecânica),MAX(prazo_garantia),MAX(mecanico_proprio),codigoId"
                        : "MAX(categoria_acessorio),MAX(cor),MAX(funileiro_proprio),codigoId";
                String colunas = tabela.equals("peça_mecânica")
                        ? "categoria_mecânica,prazo_garantia,mecanico_proprio,codigoId"
                        : "categoria_acessorio,cor,funileiro_proprio,codigoId";
                comando.executeUpdate("CREATE TEMPORARY TABLE tmp_subclasse AS SELECT " + campos
                        + " FROM `" + tabela + "` GROUP BY codigoId");
                comando.executeUpdate("DELETE FROM `" + tabela + "`");
                comando.executeUpdate("INSERT INTO `" + tabela + "` (" + colunas + ") SELECT * FROM tmp_subclasse");
                comando.executeUpdate("DROP TEMPORARY TABLE tmp_subclasse");
                comando.executeUpdate("ALTER TABLE `" + tabela + "` ADD UNIQUE KEY uq_" + tabela + "_codigoId (codigoId)");
            }
        }
    }

    private static void garantirColuna(Statement comando, String tabela, String coluna, String definicao) throws SQLException {
        String sql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='" + tabela + "' AND COLUMN_NAME='" + coluna + "'";
        try (ResultSet resultado = comando.executeQuery(sql)) {
            resultado.next();
            if (resultado.getInt(1) == 0) comando.executeUpdate("ALTER TABLE `" + tabela + "` ADD COLUMN `" + coluna + "` " + definicao);
        }
    }

    private static void removerColunaSeExistir(Statement comando, String tabela, String coluna) throws SQLException {
        String sql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='" + tabela + "' AND COLUMN_NAME='" + coluna + "'";
        try (ResultSet resultado = comando.executeQuery(sql)) {
            resultado.next();
            if (resultado.getInt(1) > 0) comando.executeUpdate("ALTER TABLE `" + tabela + "` DROP COLUMN `" + coluna + "`");
        }
    }

    private static void renomearCategoriaAcessorioLegada(Statement comando) throws SQLException {
        String sql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='peça_acessórios' AND COLUMN_NAME='categoria_lataria'";
        try (ResultSet resultado = comando.executeQuery(sql)) {
            resultado.next();
            if (resultado.getInt(1) > 0) {
                comando.executeUpdate("ALTER TABLE peça_acessórios CHANGE COLUMN categoria_lataria categoria_acessorio VARCHAR(40) NOT NULL");
            }
        }
    }
    
    public static void fechaConexao() {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
        }
    }
}
