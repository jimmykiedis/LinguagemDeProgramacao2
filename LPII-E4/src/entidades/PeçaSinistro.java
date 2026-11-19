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

    public static Peça[] buscarPeçasPorSinistro(int sinistro_id) {
        ArrayList<Peça> visoes = new ArrayList<>();
        String sql = "SELECT " + Peça.colunas() + Peça.consultaBase()
                + " JOIN peças_sinistros ps ON ps.peça_codigo=p.codigo"
                + " WHERE ps.sinistro_id = ?";

        ResultSet resultados = null;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setInt(1, sinistro_id);
            resultados = comando.executeQuery();
            while (resultados.next()) {
                try {
                    visoes.add(Peça.criarVisao(resultados));
                } catch (IllegalArgumentException excecao_enum) {
                    // Ignora peças com dados fora do domínio esperado.
                }
            }
            resultados.close();
            comando.close();
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
        }

        return visoes.toArray(new Peça[0]);
    }

    public static boolean existePeçasSinistros(int peca_codigo, int sinistro_id) {
        String sql = "SELECT COUNT(*) FROM peças_sinistros WHERE peça_codigo = ? AND sinistro_id = ?";

        boolean existe = false;
        ResultSet resultados = null;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setInt(1, peca_codigo);
            comando.setInt(2, sinistro_id);
            resultados = comando.executeQuery();
            if (resultados.next()) {
                existe = resultados.getInt(1) > 0;
            }
            resultados.close();
            comando.close();
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
        }
        return existe;
    }

    public static String inserirPeçasSinistros(Peça peca, Sinistro sinistro) {
        if (peca == null || sinistro == null) {
            return "Peça ou sinistro nao informado";
        }

        String sql = "INSERT INTO peças_sinistros (peça_codigo, sinistro_id) VALUES (?, ?)";

        String erro = null;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setInt(1, peca.getCodigo());
            comando.setInt(2, sinistro.getId());
            comando.executeUpdate();
            comando.close();
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
            erro = "Erro na insercao da associacao peca/sinistro no BD";
        }
        return erro;
    }

    public static String removerPeçasSinistros(Peça peca, Sinistro sinistro) {
        if (peca == null || sinistro == null) {
            return "Peça ou sinistro nao informado";
        }

        String sql = "DELETE FROM peças_sinistros WHERE peça_codigo = ? AND sinistro_id = ?";

        String erro = null;
        try {
            PreparedStatement comando = BD.conexao.prepareStatement(sql);
            comando.setInt(1, peca.getCodigo());
            comando.setInt(2, sinistro.getId());
            comando.executeUpdate();
            comando.close();
        } catch (SQLException excecao_sql) {
            excecao_sql.printStackTrace();
            erro = "Erro na remocao da associacao peca/sinistro no BD";
        }
        return erro;
    }
}
