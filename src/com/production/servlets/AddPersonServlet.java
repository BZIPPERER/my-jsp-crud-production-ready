package com.production.servlets;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.production.dao.MongoDBPersonDAO;
import com.production.domainmodel.Person;
import com.mongodb.MongoClient;

@WebServlet("/addPerson")
public class AddPersonServlet extends HttpServlet
{
	private static final long serialVersionUID = -7060758261496829905L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
		    {
				String name = request.getParameter("name");
				String country = request.getParameter("country");
				if ((name == null || name.equals(""))
						|| (country == null || country.equals(""))) 
				    {
					request.setAttribute("error", "Pflicht Parameters fehlt");
					RequestDispatcher rd = getServletContext().getRequestDispatcher
					(
					"/persons.jsp");
					rd.forward(request, response);
				} 
				else 
				{
					Person p = new Person();
					p.setCountry(country);
					p.setName(name);
					// the Servlet Context reads the web.xml file
					MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
					MongoDBPersonDAO personDAO = new MongoDBPersonDAO(mongo);
					personDAO.createPerson(p);
					System.out.println("Person mit id="+p.getId()+ "erfolgreich angehängt");
					request.setAttribute("success", "Person erfolgreich angefügt");
					List<Person> persons = personDAO.readAllPerson();
					request.setAttribute("persons", persons);

					RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/persons.jsp");
					rd.forward(request, response);
		}
	}

}
