package ServletsServicos;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unioeste.geral.endereco.bo.Bairro;
import unioeste.geral.endereco.bo.Cidade;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.bo.Estado;
import unioeste.geral.endereco.bo.Logradouro;
import unioeste.geral.endereco.bo.TipoLogradouro;
import unioeste.geral.endereco.manager.UCEnderecoGeralServicos;

public class CadastrarEndereco extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public CadastrarEndereco() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    }

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Estado estado = new Estado();
	        Cidade cidade = new Cidade();
	        Bairro bairro = new Bairro();
	        Logradouro logradouro = new Logradouro();
	        TipoLogradouro tipo_logradouro = new TipoLogradouro();
	        Endereco endereco = new Endereco();
	       
	        
	        estado.setSigla(request.getParameter("sigla_estado"));
	        estado.setNome(request.getParameter("nome_estado"));
	        cidade.setEstado(estado);
	        cidade.setId(Integer.parseInt(request.getParameter("id_cidade")));
	        cidade.setNome(request.getParameter("nome_cidade"));
	        bairro.setId(Integer.parseInt(request.getParameter("id_bairro")));
	        bairro.setNome(request.getParameter("nome_bairro"));
	        logradouro.setId(Integer.parseInt(request.getParameter("id_logradouro")));
	        logradouro.setNome(request.getParameter("nome_logradouro"));
	        tipo_logradouro.setId(Integer.parseInt(request.getParameter("id_tipo_logradouro")));
	        tipo_logradouro.setNome(request.getParameter("nome_tipo_logradouro"));
	        logradouro.setTipo_logradouro(tipo_logradouro);
	        endereco.setBairro(bairro);
	        endereco.setCidade(cidade);
	        endereco.setLogradouro(logradouro);
	        endereco.setCEP(request.getParameter("cep"));
	        
	        
	        JSONObject jsonObject = new JSONObject();
	        
	        try{
	        	UCEnderecoGeralServicos.cadastrarEndereco(endereco);
	        	jsonObject.put("inserido", true);
	        }
	        catch (Exception e) {
	        	jsonObject.put("erro", true);
	        	e.printStackTrace();
	        }
	        
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        response.getWriter().write(jsonObject.toString());
			doGet(request, response);
		}

	}