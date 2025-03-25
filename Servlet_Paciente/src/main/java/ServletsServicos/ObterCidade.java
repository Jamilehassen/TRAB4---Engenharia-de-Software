package ServletsServicos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unioeste.geral.endereco.bo.Cidade;
import unioeste.geral.endereco.manager.UCEnderecoGeralServicos;

public class ObterCidade extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObterCidade() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        JSONObject jsonObject = new JSONObject();
        
        try {
            if (id == null || id.isBlank()) { // Caso 1: Retornar todas as cidades
            	List<Cidade> cidades = UCEnderecoGeralServicos.obterTodasCidades();
                JSONArray cidadesArray = new JSONArray();
                
                for (Cidade cidade : cidades) {
                    JSONObject cidadeJson = new JSONObject();
                    adicionarCidadeAoJSON(cidade, cidadeJson);
                    cidadesArray.put(cidadeJson);
                }
                
                jsonObject.put("cidades", cidadesArray);
            } 
            else { // Caso 2: Retornar cidade específica por ID
                int cidadeId = Integer.parseInt(id);
                Cidade cidade = UCEnderecoGeralServicos.obterCidade(cidadeId);
                
                if (cidade == null) {
                    throw new Exception("Cidade não encontrada com o ID: " + cidadeId);
                }
                
                adicionarCidadeAoJSON(cidade, jsonObject);
            }
        } 
        catch (NumberFormatException e) {
            jsonObject.put("erro", "ID inválido: " + id);
        }
        catch (Exception e) {
            jsonObject.put("erro", e.getMessage());
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject.toString());
    }

    // Método auxiliar para adicionar dados da cidade ao JSON
    private void adicionarCidadeAoJSON(Cidade cidade, JSONObject json) throws Exception {
        json.put("sigla estado", cidade.getEstado().getSigla());
        json.put("nome estado", cidade.getEstado().getNome());
        json.put("id cidade", cidade.getId());
        json.put("nome cidade", cidade.getNome());
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
