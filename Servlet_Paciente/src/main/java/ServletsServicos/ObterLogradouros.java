package ServletsServicos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unioeste.geral.endereco.bo.Logradouro;
import unioeste.geral.endereco.manager.UCEnderecoGeralServicos;

public class ObterLogradouros extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonResponse = new JSONObject();

        try {
            // Obtém todos os logradouros
            List<Logradouro> logradouros = UCEnderecoGeralServicos.obterTodosLogradouros();
            JSONArray logradourosArray = new JSONArray();

            // Converte os logradouros para JSON
            for (Logradouro log : logradouros) {
                JSONObject logradouroJson = new JSONObject();
                logradouroJson.put("id_logradouro", log.getId());
                logradouroJson.put("nome_logradouro", log.getNome());

                logradourosArray.put(logradouroJson);
            }

            jsonResponse.put("logradouros", logradourosArray);
        } catch (Exception e) {
            jsonResponse.put("erro", "Erro ao obter logradouros: " + e.getMessage());
        }

        // Configura a resposta HTTP
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
    }
}
