package edu.ifpa.testes.controle;

import edu.ifpa.testes.modelo.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Criptografia;

/**
 *
 * @author admin
 */
@WebServlet(name = "Autenticar", urlPatterns = {"/Autenticar"})
public class Autenticar extends HttpServlet {
 
    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // recupera os parÃ¢metros do formulÃ¡rio
        String codigoLogin = request.getParameter("codigo");
        String senhaLogin = request.getParameter("senha");
        // define o objeto local "usuario"
        Usuario usuario = null;
        try {
            // Ã‰ instanciado o objeto JavaBean (local), e... recuperado do BD o
            // usuÃ¡rio pelo CÃ³digo informado
            usuario = localizaUsuarioPeloCodigo(codigoLogin);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        // define variÃ¡veis de apoio Ã  navegaÃ§Ã£o.
        String paginaDestino = "home.jsp";
        String msgErro = "";
        // faz os testes de validaÃ§Ã£o
        if (usuario == null) {
            msgErro = "Usuário nao encontrado!";
            paginaDestino = "login.jsp?msg=" + msgErro;
        } else {
            String senhaDescriptografada = "";
            try {
                senhaDescriptografada = Criptografia.descripta(usuario.getSenha());
            } catch (Exception e) {
                e.printStackTrace();     // algum erro na rotina de criptografia
            }
            if (senhaDescriptografada.equals(senhaLogin)) {
                paginaDestino = "home.jsp";
            } else {
                msgErro = "Senha incorreta  !";
                paginaDestino = "login.jsp?msg=" + msgErro;
            }
        }
        // O objeto JavaBean (local) Ã© salvo na memÃ³ria para ser lido na pÃ¡gina JSP
        request.getSession().setAttribute("BEAN_USUARIO", usuario);
        // chama a pÃ¡gina definida na aÃ§Ã£o de navegaÃ§Ã£o
        request.getRequestDispatcher(paginaDestino).forward(request, response);
    } //  fim do mÃ©todo "service"

    
    private Usuario localizaUsuarioPeloCodigo(String codigo)
			throws ClassNotFoundException, SQLException {
		// conexÃ£o com o banco de dados...
                Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:h2:tcp://localhost/~/test", "sa", "");
		PreparedStatement st = conn.prepareStatement("SELECT * FROM USUARIOWEB "
				+ "  WHERE CODIGO=?");
		st.setString(1, codigo);
		ResultSet rs = st.executeQuery();
		Usuario usuEncontrado = null;
		if (rs.next()) {
			usuEncontrado = new Usuario(); // instancia o usuÃ¡rio.
			// Os dados sÃ£o repassados ao objeto local
			usuEncontrado.setNome(rs.getString("nome"));
			usuEncontrado.setCodigo(rs.getString("codigo"));
			usuEncontrado.setSenha(rs.getString("senha"));
			usuEncontrado.setUltimoAcesso(rs.getDate("ultimoAcesso"));
		}
		rs.close();
		st.close();
		return usuEncontrado;
	} // fim do mÃ©todo.


}
