package ServletsServicos;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.manager.UCEnderecoGeralServicos;

public class ObterEnderecoExterno extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObterEnderecoExterno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cep = request.getParameter("cep");
        
        JSONObject jsonObject = new JSONObject();
        
        try{
        	Endereco endereco = UCEnderecoGeralServicos.obterEnderecoExterno(cep);
        	jsonObject.put("cep", endereco.getCEP());
        	jsonObject.put("sigla estado", endereco.getCidade().getEstado().getSigla());
        	jsonObject.put("nome cidade", endereco.getCidade().getNome());
        	jsonObject.put("nome bairro", endereco.getBairro().getNome());
        	jsonObject.put("nome logradouro", endereco.getLogradouro().getNome());
        }
        catch (Exception e) {
        	jsonObject.put("erro", true);
        	e.printStackTrace();
        }
        
        // Configurar a resposta para retornar JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(jsonObject.toString());
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
