package ServletsServicos;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import paciente.manager.UCPacienteServicos;
import unioeste.bo.paciente.Paciente;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Servlet implementation class ObterCPFsCadastradosServlet
 */
public class ObterPacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObterPacientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jobject = new JSONObject();
		
		try {
			ArrayList<Paciente> pacientes = UCPacienteServicos.obterPacientes();
			JSONArray jpacientes = new JSONArray();
			for(Paciente paciente : pacientes) {
				JSONObject jpaciente = new JSONObject();
				jpaciente.put("cpf", paciente.getCPF());
				jpaciente.put("rg", paciente.getRG());
				jpaciente.put("nome", paciente.getNome());
				jpacientes.put(jpaciente);
			}
			jobject.put("pacientes", jpacientes);
			
		}
		catch (Exception e) {
        	jobject.put("erro", true);
        	e.printStackTrace();
        }
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(jobject.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
