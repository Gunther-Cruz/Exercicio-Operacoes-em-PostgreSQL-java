package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.util.ArrayList;

import negocio.Anotacao;

public class AnotacaoDAO {

    public Anotacao obter(int id) throws SQLException {
        Anotacao anotacao = new Anotacao();
        String sql = "select * from anotacao where id = ?";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql); 
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            anotacao.setId(rs.getInt("id"));
            anotacao.setTitulo(rs.getString("titulo"));
            anotacao.setTexto(rs.getString("texto"));
            anotacao.setCor(rs.getString("cor"));
            anotacao.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
            anotacao.setFoto(rs.getBytes("foto"));
        }
        preparedStatement.close();
        conexao.close();
        return anotacao;
    }

    public void atualizar(Anotacao anotacao) throws SQLException{
        String sql = "UPDATE anotacao SET titulo = ?, texto = ?, cor = ?, foto = ?, data_hora = ?  WHERE id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, anotacao.getTitulo());
        preparedStatement.setString(2, anotacao.getTexto());
        preparedStatement.setTimestamp(5, Timestamp.valueOf(anotacao.getDataHora()));
        preparedStatement.setInt(6, anotacao.getId());
        preparedStatement.setBytes(4, anotacao.getFoto());
        preparedStatement.setString(3, anotacao.getCor());
        preparedStatement.execute();
        preparedStatement.close();
        conexao.close();

    }

    public boolean deletar(int id) throws SQLException{
        String sql = "delete from anotacao where id = ?";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        conexao.close();
        return resultado == 1;
    }

    public ArrayList<Anotacao> listar() throws SQLException{
        ArrayList<Anotacao> vetAnotacao = new ArrayList<>();
        String sql = "select * from anotacao order by data_hora desc";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql); 
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Anotacao anotacao = new Anotacao();
            anotacao.setId(rs.getInt("id"));
            anotacao.setTitulo(rs.getString("titulo"));
            anotacao.setTexto(rs.getString("texto"));
            anotacao.setCor(rs.getString("cor"));
            anotacao.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
            anotacao.setFoto(rs.getBytes("foto"));
            vetAnotacao.add(anotacao);
        }
        preparedStatement.close();
        conexao.close();
        return vetAnotacao;
    }

    public void inserir(Anotacao anotacao) throws SQLException{
        String sql = "INSERT INTO anotacao (titulo, texto, data_hora, foto, cor) VALUES (?,?,?,?,?);";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, anotacao.getTitulo());
        preparedStatement.setString(2, anotacao.getTexto());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(anotacao.getDataHora()));
        preparedStatement.setBytes(4, anotacao.getFoto());
        preparedStatement.setString(5, anotacao.getCor());
        preparedStatement.execute();
        preparedStatement.close();
        conexao.close();
    }
    
}
