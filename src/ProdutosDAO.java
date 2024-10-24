import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        try {
            conn = new conectaDAO().connectDB();

            String sql = "INSERT INTO produtos(nome, valor, status) VALUE(?, ?, ?)";
            PreparedStatement query = conn.prepareStatement(sql);

            query.setString(1, produto.getNome());
            query.setDouble(2, produto.getValor());
            query.setString(3, produto.getStatus());

            query.execute();
            query.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        try {
            conn = new conectaDAO().connectDB();
            String sql = "SELECT * FROM produtos"; 
            PreparedStatement query = conn.prepareStatement(sql);
            resultset = query.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getDouble("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }

            resultset.close(); 
            query.close(); 
            conn.close(); 

        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return listagem;
    }
}

