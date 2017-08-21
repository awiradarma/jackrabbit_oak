package andre.test;

import java.io.IOException;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//if (request.getParameter("name"))
		try {
			InitialContext ctx = new InitialContext();
			Repository repo = (Repository) ctx.lookup("java:/jca/DocumentStore");
			Session session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
			try { 
				Node root = session.getRootNode(); 

				// Store content 
				Node hello = root.addNode("hello"); 
				Node world = hello.addNode("world"); 
				world.setProperty("message", "Hello, World!"); 
				session.save(); 

				// Retrieve content 
				Node node = root.getNode("hello/world"); 
				System.out.println(node.getPath()); 
				System.out.println(node.getProperty("message").getString()); 

				// Remove content 
				root.getNode("hello").remove(); 
				session.save(); 
			} finally { 
				session.logout(); 
			} 

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
