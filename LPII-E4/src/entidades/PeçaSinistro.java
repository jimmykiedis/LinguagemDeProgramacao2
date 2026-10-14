package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import persistência.BD;

public class PeçaSinistro {

    private final int pecaCodigo;
    private final int sinistroId;

    public PeçaSinistro(int pecaCodigo, int sinistroId) {
        this.pecaCodigo = pecaCodigo;
        this.sinistroId = sinistroId;
    }

    public PeçaSinistro(Peça peca, Sinistro sinistro) {
        this(peca != null ? peca.getCodigo() : 0,
                sinistro != null ? sinistro.getId() : 0);
    }

    public int getPeçaCodigo() {
        return pecaCodigo;
    }

    public int getSinistroId() {
        return sinistroId;
    }

    public String toString() {
        return pecaCodigo + " / " + sinistroId;
    }

    public static Peça[] buscarPeçasPorSinistro(int sinistroId) {
        ArrayList<Peça> visoes = new ArrayList<>();
        String sql = "SELECT " + Peça.colunas() + Peça.consultaBase()
                + " JOIN peças_sinistros ps ON ps.peça_codigo=p.codigo"
                + " WHERE ps.sinistro_id = ?";

        try (PreparedStatement comando = BD.conexao.prepareStatement(sql)) {
            comando.setInt(1, sinistroId);
            try (ResultSet resultados = comando.executeQuery()) {
                while (resultados.next()) {
                    try {
                        visoes.add(Peça.criarVisao(resultados));
                    } catch (IllegalArgumentException excecaoEnum) {
                        // Ignora peças com dados fora do domínio esperado.
                    }
                }
            }
        } catch (SQLException excecaoSql) {
            excecaoSql.printStackTrace();
        }

        return visoes.toArray(new Peça[0]);
    }

    public static boolean existePeçasSinistros(int pecaCodigo, int sinistroId) {
        String sql = "SELECT COUNT(*) FROM peças_sinistros WHERE peça_codigo = ? AND sinistro_id = ?";

        try (PreparedStatement comando = BD.conexao.prepareStatement(sql)) {
            comando.setInt(1, pecaCodigo);
            comando.setInt(2, sinistroId);
            try (ResultSet resultados = comando.executeQuery()) {
                return resultados.next() && resultados.getInt(1) > 0;
            }
        } catch (SQLException excecaoSql) {
            excecaoSql.printStackTrace();
            return false;
        }
    }

    public static String inserirPeçasSinistros(Peça peca, Sinistro sinistro) {
        if (peca == null || sinistro == null) {
            return "Peça ou sinistro nao informado";
        }

        String sql = "INSERT INTO peças_sinistros (peça_codigo, sinistro_id) VALUES (?, ?)";

        try (PreparedStatement comando = BD.conexao.prepareStatement(sql)) {
            comando.setInt(1, peca.getCodigo());
            comando.setInt(2, sinistro.getId());
            comando.executeUpdate();
            return null;
        } catch (SQLException excecaoSql) {
            excecaoSql.printStackTrace();
            return "Erro na insercao da associacao peca/sinistro no BD";
        }
    }

    public static String removerPeçasSinistros(Peça peca, Sinistro sinistro) {
        if (peca == null || sinistro == null) {
            return "Peça ou sinistro nao informado";
        }

        String sql = "DELETE FROM peças_sinistros WHERE peça_codigo = ? AND sinistro_id = ?";

        try (PreparedStatement comando = BD.conexao.prepareStatement(sql)) {
            comando.setInt(1, peca.getCodigo());
            comando.setInt(2, sinistro.getId());
            comando.executeUpdate();
            return null;
        } catch (SQLException excecaoSql) {
            excecaoSql.printStackTrace();
            return "Erro na remocao da associacao peca/sinistro no BD";
        }
    }
}
