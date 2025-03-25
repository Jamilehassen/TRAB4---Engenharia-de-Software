package ServletsServicos;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import paciente.manager.UCPacienteServicos;
import unioeste.bo.paciente.Paciente;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.bo.EnderecoEspecifico;
import unioeste.geral.endereco.manager.UCEnderecoGeralServicos;

public class CadastrarPaciente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CadastrarPaciente() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Paciente paciente = new Paciente();
        EnderecoEspecifico enderecoResidencial = new EnderecoEspecifico();

        String nome_paciente = request.getParameter("nome_paciente");
        String cpf = request.getParameter("cpf");
        String rg = request.getParameter("rg");
        String numero_endereco = request.getParameter("numero_endereco");
        String complemento_endereco = request.getParameter("complemento_endereco");
        String data_nascimento_paciente = request.getParameter("data_nascimento_paciente");
        String cep = request.getParameter("cep"); 
        
        try {
            Endereco endereco = UCEnderecoGeralServicos.obterEnderecoPorCEP(cep);
            enderecoResidencial.setEndereco(endereco);
        } catch (Exception e) {
            JSONObject json = new JSONObject();
            json.put("erro", "CEP inválido ou não encontrado: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(json.toString());
            return;
        }

        paciente.setNome(nome_paciente.strip().toUpperCase());
        paciente.setCPF(cpf.strip());
        paciente.setRG(rg.strip());
        

        int numeroEndereco = 0;
        if (numero_endereco != null && !numero_endereco.isBlank()) {
            numeroEndereco = Integer.parseInt(numero_endereco);
        }

        enderecoResidencial.setNumero(numeroEndereco);
        enderecoResidencial.setComplemento(complemento_endereco);
        paciente.setEnderecoResidencial(enderecoResidencial);

        if (data_nascimento_paciente != null && !data_nascimento_paciente.isBlank()) {
            java.sql.Date dataNascimento = java.sql.Date.valueOf(data_nascimento_paciente);
            paciente.setDataNascimento(dataNascimento);
        }

        JSONObject jsonObject = new JSONObject();
        
        try {
            UCPacienteServicos.cadastrarPaciente(paciente);
            jsonObject.put("inserido", true);
        } catch (Exception e) {
            jsonObject.put("erro", e.getMessage()); // Retornar a mensagem real do erro
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject.toString());
    }
}
