package pck;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String user="admin";
	private static final String pass="admin";
	
	HttpSession sesion;

    /**
     * Default constructor. 
     */
    public Servlet1() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
       //url
		String url="index.html";
		String usu, password;
		
        usu = request.getParameter("user");
        password = request.getParameter("pass");
    	UsuariosJDBC jdbc = new UsuariosJDBC();
		ArrayList<DTOUsuarios> lista=null;
		
			lista = jdbc.muestraUser();
		
		
		
	
        //comprobamos que  usuario y pass sean correctos
        if(usu.equals(user) && password.equals(pass)  ){
        	
            //si coincide usuario y password 
            //Muestro el jsp con la info de bddd
        	//Por tanto hay que recorrer la lista
        	for(int i=0;i<lista.size();i++) {
        		
        			request.setAttribute("Nombre", lista.get(i).getNombre());
        			request.setAttribute("Apellidos", lista.get(i).getApellidos());
        			request.setAttribute("Email", lista.get(i).getEmail());
        			url="/WEB-INF/usuarios.jsp";
        			
        			request.setAttribute("lista", lista);
    		
        	}	
 }else{
        	//Caso que no coincidan pasamos a FormRegistro html
        	//Aqui es donde se hace registro
	 for(int i=0;i<lista.size();i++) {
 		
			request.setAttribute("Nombre", lista.get(i).getNombre());
			request.setAttribute("Apellidos", lista.get(i).getApellidos());
			request.setAttribute("Email", lista.get(i).getEmail());
			url="/WEB-INF/FormRegistro.html";
			request.setAttribute("lista", lista);
	
	}	
        	
        	 
        }
        
        getServletContext().getRequestDispatcher(url).forward(request,response);
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

	