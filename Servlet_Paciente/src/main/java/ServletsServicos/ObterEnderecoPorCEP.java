package ServletsServicos;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.manager.UCEnderecoGeralServicos;

public class ObterEnderecoPorCEP extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObterEnderecoPorCEP() {
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
        	Endereco endereco = UCEnderecoGeralServicos.obterEnderecoPorCEP(cep);
        	jsonObject.put("id", endereco.getId());
        	jsonObject.put("cep", endereco.getCEP());
        	jsonObject.put("sigla estado", endereco.getCidade().getEstado().getSigla());
        	jsonObject.put("nome estado", endereco.getCidade().getEstado().getNome());
        	jsonObject.put("id cidade", endereco.getCidade().getId());
        	jsonObject.put("nome cidade", endereco.getCidade().getNome());
        	jsonObject.put("id bairro", endereco.getBairro().getId());
        	jsonObject.put("nome bairro", endereco.getBairro().getNome());
        	jsonObject.put("id logradouro", endereco.getLogradouro().getId());
        	jsonObject.put("nome logradouro", endereco.getLogradouro().getNome());
        	jsonObject.put("tipo logradouro", endereco.getLogradouro().getTipo_logradouro().getNome());
        }
        catch (Exception e) {
        	jsonObject.put("erro", true);
        	e.printStackTrace();
        }
        
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
