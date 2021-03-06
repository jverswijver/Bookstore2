package Book;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookDao bdao = new BookDaoImpl();
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("ISBN");
		
		//double price = Double.parseDouble(request.getParameter("price"));
		String submitType = request.getParameter("submit");
		Book b = new Book(title, author, isbn, 0);
//		b.setPrice(price);
		bdao.register(b);
		if(submitType.equals("submit") && b!=null && b.getTitle()!="" && b.getAuthor()!="" && b.getISBN()!=""){ 
			request.setAttribute("message", b.getTitle() +" by " + b.getAuthor()+ " (ISBN: "+b.getISBN() + ") has been added successfully!");
			request.getRequestDispatcher("addBook.jsp").forward(request, response);
			
		}
		else{
		
		request.setAttribute("message", b.getISBN() + "Unsuccessful entry. Please try again (make sure to fill out all fields)");
		request.getRequestDispatcher("addBook.jsp").forward(request, response);
			}
	}
}