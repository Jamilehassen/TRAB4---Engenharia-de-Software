package unioeste.geral.endereco.manager;

import java.sql.Connection;
import java.util.List;

import unioeste.geral.endereco.bo.Bairro;
import unioeste.geral.endereco.bo.Cidade;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.bo.Logradouro;
import unioeste.geral.endereco.col.CidadeCOL;
import unioeste.geral.endereco.col.EnderecoCOL;
import unioeste.geral.endereco.dao.BairroDAO;
import unioeste.geral.endereco.dao.CidadeDAO;
import unioeste.geral.endereco.dao.EnderecoDAO;
import unioeste.geral.endereco.dao.LogradouroDAO;
import unioeste.geral.endereco.infra.CepAPI;
import unioeste.apoio.BD.*;


public class UCEnderecoGeralServicos {
	
	
	public static Endereco obterEnderecoExterno (String CEP) throws Exception {
		if(!EnderecoCOL.CEPValido(CEP)) throw new EnderecoException("TESTE de não válido (" + CEP + ")");
		
		Endereco endereco = CepAPI.getCEP(CEP);
		
		if(endereco==null) throw new EnderecoException("TESTE de não válido 2 (" + CEP + ")");
		
		
		return endereco;
	}
	
	public static Cidade obterCidade(int id) throws Exception {
		if(!CidadeCOL.idValido(id)) throw new EnderecoException("ID da cidade inválido (" + id + ")");
		ConnectionBD conexao = new ConnectionBD();
		Connection connection = conexao.getConnection();
		
		Cidade cidade = CidadeDAO.selectCidade(id, conexao.getConnection());
		
		if(cidade==null) throw new EnderecoException("Cidade não cadastrada");
		
		connection.close();
		
		return cidade;
	}
	
	public static List<Cidade> obterTodasCidades() throws Exception {
	    ConnectionBD conexao = new ConnectionBD();
	    Connection connection = conexao.getConnection();
	    
	    List<Cidade> listaCidades = CidadeDAO.selectAllCidades(connection);
	    
	    if (listaCidades == null || listaCidades.isEmpty()) {
	        connection.close();
	        throw new EnderecoException("Nenhuma cidade cadastrada");
	    }


	    connection.close();
	    return listaCidades;
	}
	
	public static List<Bairro> obterTodosBairros() throws Exception {
	    ConnectionBD conexao = new ConnectionBD();
	    Connection connection = conexao.getConnection();

	    List<Bairro> listaBairros = BairroDAO.selectTodosBairros(connection);

	    if (listaBairros == null || listaBairros.isEmpty()) {
	        connection.close();
	        throw new EnderecoException("Nenhum bairro cadastrado");
	    }

	    connection.close();
	    return listaBairros;
	}
	
	public static List<Logradouro> obterTodosLogradouros() throws Exception {
	    ConnectionBD conexao = new ConnectionBD();
	    Connection connection = conexao.getConnection();

	    List<Logradouro> listaLog = LogradouroDAO.selectAllLogradouros(connection);

	    if (listaLog == null || listaLog.isEmpty()) {
	        connection.close();
	        throw new EnderecoException("Nenhum logradouro cadastrado");
	    }

	    connection.close();
	    return listaLog;
	}

	
	public static Endereco obterEnderecoPorCEP(String cep) throws Exception {
		if(!EnderecoCOL.CEPValido(cep)) throw new EnderecoException("Formato de CEP inválido (" + cep + ")");
		ConnectionBD conexao = new ConnectionBD();
		Connection connection = conexao.getConnection();
		Endereco endereco = EnderecoDAO.selectEnderecoCEP(cep, conexao.getConnection());
		
		if(endereco==null) throw new EnderecoException("Endereço não cadastrado");
		
		connection.close();
		
		return endereco;
	}
	
	public static Endereco obterEnderecoPorID(int id) throws Exception {
		if(!EnderecoCOL.idValido(id)) throw new EnderecoException("Id do endereço inválido (" + id + ")");
		ConnectionBD conexao = new ConnectionBD();
		Connection connection = conexao.getConnection();
		Endereco endereco = EnderecoDAO.selectEnderecoId(id, conexao.getConnection());
		
		if(endereco==null) throw new EnderecoException("Endereço não cadastrado");
		
		connection.close();
		
		return endereco;
	}
	
	public static void cadastrarEndereco (Endereco endereco) throws Exception {
		ConnectionBD conexao = new ConnectionBD();
		Connection connection = conexao.getConnection();
		
		/*if(!EnderecoCOL.EnderecoValido(endereco, connection)) throw new EnderecoException("Endereço inválido");
		if(EnderecoCOL.EnderecoCadastrado(endereco, connection)) throw new EnderecoException("CEP já cadastrado");

		
		connection.setAutoCommit(false);
		
		EnderecoDAO.insertEndereco(endereco, connection);
		
		connection.commit();
		connection.close();
		
		System.out.println("Endereço cadastrado");*/
		
		System.out.println("Iniciando cadastro do endereço: " + endereco);
		
		// Validação do endereço
		System.out.println("Validando endereço...");
		if (!EnderecoCOL.EnderecoValido(endereco, connection)) {
			System.out.println("Falha na validação do endereço: " + endereco);
			connection.close();
			throw new EnderecoException("Endereço inválido");
		}

		// Verificação se o endereço já está cadastrado
		System.out.println("Verificando se o endereço já está cadastrado...");
		if (EnderecoCOL.EnderecoCadastrado(endereco, connection)) {
			System.out.println("Endereço já cadastrado para o CEP: " + endereco.getCEP());
			connection.close();
			throw new EnderecoException("CEP já cadastrado");
		}

		// Inicia a transação
		connection.setAutoCommit(false);
		System.out.println("Inserindo o endereço no banco de dados...");
		EnderecoDAO.insertEndereco(endereco, connection);

		// Confirma a transação
		connection.commit();
		connection.close();

		System.out.println("Endereço cadastrado com sucesso.");
	}
}
