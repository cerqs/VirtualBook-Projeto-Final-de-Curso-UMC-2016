package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class newjspaaaa_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <style>\n");
      out.write("            /* The Modal (background) */\n");
      out.write("            .modal {\n");
      out.write("                display: none; /* Hidden by default */\n");
      out.write("                position: fixed; /* Stay in place */\n");
      out.write("                z-index: 1; /* Sit on top */\n");
      out.write("                padding-top: 100px; /* Location of the box */\n");
      out.write("                left: 0;\n");
      out.write("                top: 0;\n");
      out.write("                width: 100%; /* Full width */\n");
      out.write("                height: 100%; /* Full height */\n");
      out.write("                overflow: auto; /* Enable scroll if needed */\n");
      out.write("                background-color: rgb(0,0,0); /* Fallback color */\n");
      out.write("                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            /* Modal Content */\n");
      out.write("            .modal-content {\n");
      out.write("                position: relative;\n");
      out.write("                background-color: #fefefe;\n");
      out.write("                margin: auto;\n");
      out.write("                padding: 0;\n");
      out.write("                border: 1px solid #888;\n");
      out.write("                width: 80%;\n");
      out.write("                box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);\n");
      out.write("                -webkit-animation-name: animatetop;\n");
      out.write("                -webkit-animation-duration: 0.4s;\n");
      out.write("                animation-name: animatetop;\n");
      out.write("                animation-duration: 0.4s\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            /* Add Animation */\n");
      out.write("            @-webkit-keyframes animatetop {\n");
      out.write("                from {top:-300px; opacity:0}\n");
      out.write("                to {top:0; opacity:1}\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            @keyframes animatetop {\n");
      out.write("                from {top:-300px; opacity:0}\n");
      out.write("                to {top:0; opacity:1}\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            /* The Close Button */\n");
      out.write("            .close {\n");
      out.write("                color: white;\n");
      out.write("                float: right;\n");
      out.write("                font-size: 28px;\n");
      out.write("                font-weight: bold;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .close:hover,\n");
      out.write("            .close:focus {\n");
      out.write("                color: #000;\n");
      out.write("                text-decoration: none;\n");
      out.write("                cursor: pointer;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .modal-header {\n");
      out.write("                padding: 2px 16px;\n");
      out.write("                background-color: #5cb85c;\n");
      out.write("                color: white;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .modal-body {padding: 2px 16px;}\n");
      out.write("\n");
      out.write("            .modal-footer {\n");
      out.write("                padding: 2px 16px;\n");
      out.write("                background-color: #5cb85c;\n");
      out.write("                color: white;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <h2>Animated Modal with Header and Footer</h2>\n");
      out.write("\n");
      out.write("        <!-- Trigger/Open The Modal -->\n");
      out.write("        <button id=\"myBtn\">Open Modal</button>\n");
      out.write("\n");
      out.write("        <!-- The Modal -->\n");
      out.write("        <div id=\"myModal\" class=\"modal\">\n");
      out.write("\n");
      out.write("            <!-- Modal content -->\n");
      out.write("            <div class=\"modal-content\">\n");
      out.write("                <div class=\"modal-header\">\n");
      out.write("                    <span class=\"close\">×</span>\n");
      out.write("                    <h2>Modal Header</h2>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"modal-body\">\n");
      out.write("                    <p>Some text in the Modal Body</p>\n");
      out.write("                    <p>Some other text...</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"modal-footer\">\n");
      out.write("                    <h3>Modal Footer</h3>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            // Get the modal\n");
      out.write("            var modal = document.getElementById('myModal');\n");
      out.write("\n");
      out.write("            // Get the button that opens the modal\n");
      out.write("            var btn = document.getElementById(\"myBtn\");\n");
      out.write("\n");
      out.write("            // Get the <span> element that closes the modal\n");
      out.write("            var span = document.getElementsByClassName(\"close\")[0];\n");
      out.write("\n");
      out.write("            // When the user clicks the button, open the modal\n");
      out.write("            btn.onclick = function () {\n");
      out.write("                modal.style.display = \"block\";\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            // When the user clicks on <span> (x), close the modal\n");
      out.write("            span.onclick = function () {\n");
      out.write("                modal.style.display = \"none\";\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            // When the user clicks anywhere outside of the modal, close it\n");
      out.write("            window.onclick = function (event) {\n");
      out.write("                if (event.target == modal) {\n");
      out.write("                    modal.style.display = \"none\";\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
