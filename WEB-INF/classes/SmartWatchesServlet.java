import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;

public class SmartWatchesServlet extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    XmlSaveToHashmap productHashmap = new XmlSaveToHashmap("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\Product.xml");

    HttpSession session = request.getSession(true);
        Integer itemNum = (Integer) session.getAttribute("itemNum");
        if(null == itemNum) {
            itemNum = 0;
            session.setAttribute("itemNum" , itemNum);
        }
        String username = (String) session.getAttribute("username");
        String usertype = (String) session.getAttribute("usertype");

    PrintWriter out = response.getWriter();
    out.println("<html>");
 
    out.println("<head>");
    out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>");
    out.println("<title> SmartPortables </title>");
    out.println("<link rel=\"stylesheet\" href=\"styles_product_list.css\" type=\"text/css\"/>");
    out.println("</head>");

    out.println("<body>");
    out.println("<div id=\"container\">");
    out.println("<header>");
    out.println("<h1><a href=\"/\">Smart<span>Portables</span></a></h1>");
    out.println("</header>");

 	out.println("<nav>");
 	out.println("<ul>");
 	out.println("<li><a href=\"index.html\">Home</a></li>");
 	out.println("<li><a href=\"PhoneServlet\">Phones</a></li>");
 	out.println("<li><a href=\"LaptopServlet\">Laptops</a></li>");
 	out.println("<li class=\"start selected\"><a href=\"SmartWatchesServlet\">Smart Watches</a></li>");
 	out.println("<li><a href=\"SpeakersServlet\">Speakers</a></li>");
 	out.println("<li><a href=\"HeadphonesServlet\">Headphones</a></li>");
 	out.println("<li><a href=\"ExternalStorageServlet\">External Storage</a></li>");
 	out.println("<li class=\"end\"><a href=\"CartServlet\">Cart(" + itemNum + ")</a></li>");
 	out.println("</ul>");
	out.println("</nav>");

    out.println("<div id=\"body\">");
    out.println("   <aside class=\"sidebar\">");
    out.println("        <ul>");
    out.println("           <li>");
    out.println("                <h4>Categories</h4>");
    out.println("                <ul>");
    out.println("                    <li><a href=\"index.html\">Home Page</a></li>");
    out.println("                    <li><a href=\"PhoneServlet\">Phones</a></li>");
    out.println("                    <li><a href=\"LaptopServlet\">Laptops</a></li>");
    out.println("                    <li><a href=\"SmartWatchesServlet\">Smart Watches</a></li>");
    out.println("                    <li><a href=\"SpeakersServlet\">Speakers</a></li>");
    out.println("                    <li><a href=\"HeadphonesServlet\">Headphones</a></li>");
    out.println("                    <li><a href=\"ExternalStorageServlet\">External storage</a></li>");
    out.println("                    <li><a href=\"AccessariesServlet\">Accessaries</a></li>");
    out.println("                    <li><a href=\"TrendingServlet\">Trending</a></li>");
    out.println("                </ul>");
    out.println("            </li>");
    out.println("            <li>");
    out.println("               <h4>Search</h4>");
    out.println("                <ul>");
    out.println("                   <li class=\"text\">");
    out.println("                        <form method=\"get\" class=\"searchform\" action=\"#\" >");
    out.println("                            <p>");
    out.println("                                <input type=\"text\" size=\"27\" value=\"\" name=\"s\" class=\"s\" />");
    out.println("                            </p>");
    out.println("                        </form>"); 
    out.println("                   </li>");
    out.println("               </ul>");
    out.println("            </li>");
    out.println("        </ul>");   
    out.println("    </aside>");

/*
    out.println("<div>");
    out.println("<a href=\"SpeakersServlet\"><img class=\"content-image\" src=\"images/image28.png\" alt=\"apple watch edition\"/></a>");
    out.println("<a href=\"SpeakersServlet\"><img class=\"content-image\" src=\"images/image29.png\" alt=\"pedometer\" /></a>");
    out.println("<a href=\"SpeakersServlet\"><img class=\"content-image\" src=\"images/image30.png\" alt=\"samsung gear fit2\" /></a>");
    out.println("<a href=\"SpeakersServlet\"><img class=\"content-image\" src=\"images/image31.png\" alt=\"samsung gear s2\" /></a>");
    out.println("<a href=\"SpeakersServlet\"><img class=\"content-image\" src=\"images/image32.png\" alt=\"lg urbane\" /></a>");
    out.println("<a href=\"SpeakersServlet\"><img class=\"content-image\" src=\"images/image33.png\" alt=\"huawei watch\" /></a>");
    out.println("<a href=\"SpeakersServlet\"><img class=\"content-image\" src=\"images/image34.png\" alt=\"sony sw2\" /></a>");
    out.println("<a href=\"SpeakersServlet\"><img class=\"content-image\" src=\"images/image35.png\" alt=\"moto 360\" /></a>");
    out.println("<a href=\"SpeakersServlet\"><img class=\"content-image\" src=\"images/image36.png\" alt=\"pebble time around\" /></a>");
    out.println("<a href=\"SpeakersServlet\"><img class=\"content-image\" src=\"images/image37.png\" alt=\"Apple Watch Hermès\" /></a>");
    out.println("</div>");
*/

    out.println("    <section class=\"content-list\">");

    for(int i = 21; i <= 30; i++){
        Item item = productHashmap.getItem(Integer.toString(i));
        out.println("        <div class=\"product\">");
        out.println("            <ul>");
        out.println("                <li><b>" + item.name + "</b></li>");
        out.println("                <li><b>$ " + item.price + "</b></li>");
        out.println("                <li><a href=\"ProductDetailServlet?id=" + i + "\"><img class=\"content-image\" src=\"images/" + item.image + ".png\" alt=" + item.name + "/></a></li>");
        out.println("                <li><a href=\"CartServlet?id=" + i + "\" class=\"button \">Buy Now</a></li>");
        out.println("                <li><a href=\"WriteReviewServlet\" class=\"button \">Write Review</a></li>");
        out.println("                <li><a href=\"ViewReviewServlet\" class=\"button \">View Review</a></li>");
        out.println("            </ul>");
        out.println("        </div>");
    }

        out.println("           <div class=\"clear\"></div>");
        out.println("           </div>");
        out.println("    <footer>");
        out.println("        <div class=\"footer-content\">");
        out.println("           <h4>My SmartPortables</h4>");
        out.println("            <ul>");
        if(null == username){
            out.println("            <li><a href=\"LoginServlet\">Login</a></li>");
            out.println("            <li><a href=\"CreateAccountServlet\">Create account</a></li>");
        }else{
            out.println("            <li><a href=\"#\">View order</a></li>");
            if(usertype.equalsIgnoreCase("Salesman")){
                out.println("        <li><a href=\"CreateAccountServlet\">Create Customer Account</a></li>");
                out.println("        <li><a href=\"#\">Manage orders</a></li>");
            }
            if(usertype.equalsIgnoreCase("Manager")){
                out.println("        <li><a href=\"ManagerFunctionServlet\">Manage Products</a></li>");
                out.println("        <li><a href=\"InventoryReportServlet\">Inventory Report</a></li>");
                out.println("        <li><a href=\"SalesReportServlet\">Sales Report</a></li>");
            }
            out.println("            <li><a href=\"LogoutServlet\">Log out</a></li>");
        }
        out.println("            </ul>");
        out.println("            <div class=\"clear\"></div>");
        out.println("        </div>");       
        out.println("    </footer>");

    out.println("</body>");
    out.println("</html>");
  }

}



