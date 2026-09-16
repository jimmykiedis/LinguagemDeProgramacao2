package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import persistência.BD;

public class PeçaSinistro {

    private final int peca_codigo;
    private final int sinistro_id;

    public PeçaSinistro(int peca_codigo, int sinistro_id) {
        this.peca_codigo = peca_codigo;
        this.sinistro_id = sinistro_id;
    }

    public PeçaSinistro(Peça peca, Sinistro sinistro) {
        this(peca != null ? peca.getCodigo() : 0,
                sinistro != null ? sinistro.getId() : 0);
    }

    public int getPeçaCodigo() {
        return peca_codigo;
    }

    public int getSinistroId() {
        return sinistro_id;
    }

    public String toString() {
        return peca_codigo + " / " + sinistro_id;
    }

    private static Peça criarPeça(ResultSet resultado) throws SQLException {
        int codigo = resultado.getInt("codigo");
        String nome = resultado.getString("nome");
        Peça.MarcaPeça marca = Peça.MarcaPeça.fromTexto(resultado.getString("marca"));
        double preco = resultado.getDouble("preco");
        boolean mao_obra_propria = resultado.getBoolean("mao_obra_propria");
        Integer dias_garantia = resultado.getObject("dias_garantia") == null
                ? null : resultado.getInt("dias_garantia");
        String cor = resultado.getString("cor");

        return new Peça(codigo, nome, marca, preco, mao_obra_propria,
                dias_garantia, cor);
    }

    public static Peça[] buscarPeçasPorSinistro(int sinistroId) {
        ArrayList<Peça> visoes = new ArrayList<>();
        String sql = "SELECT p.codigo, p.nome, p.marca, p.preco, p.mao_obra_propria, "
                + "p.dias_garantia, p.cor "
                + "FROM peças_sinistros ps "
                + "JOIN peças p ON p.codigo = ps.peça_codigo "
                + "WHERE ps.sinistro_id = ?";

        try (PreparedStatement comando = BD.conexao.prepareStatement(sql)) {
            comando.setInt(1, sinistroId);
            try (ResultSet resultados = comando.executeQuery()) {
                while (resultados.next()) {
                    try {
                        visoes.add(criarPeça(resultados));
                    } catch (IllegalArgumentException excecao_enum) {
                        // Ignora peças com dados fora do domínio esperado.
                    }
                }
            }
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
        }

        return visoes.toArray(new Peça[0]);
    }

    public static boolean existePeçasSinistros(int peca_codigo, int sinistro_id) {
        String sql = "SELECT COUNT(*) FROM peças_sinistros WHERE peça_codigo = ? AND sinistro_id = ?";

        try (PreparedStatement comando = BD.conexao.prepareStatement(sql)) {
            comando.setInt(1, peca_codigo);
            comando.setInt(2, sinistro_id);
            try (ResultSet resultados = comando.executeQuery()) {
                return resultados.next() && resultados.getInt(1) > 0;
            }
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
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
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
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
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
            return "Erro na remocao da associacao peca/sinistro no BD";
        }
    }
}
