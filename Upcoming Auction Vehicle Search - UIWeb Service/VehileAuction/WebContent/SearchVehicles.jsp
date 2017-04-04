<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
<%@ page import="java.io.*,java.util.*, javax.servlet.*, java.text.*" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>

    <%@page import="Beans.ProductsBeans"%>
    <%@page import="Beans.ProductBean" %>
  
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>View vehicles</title>

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="dashboard.css" rel="stylesheet">
	<script type="text/javascript" src="sorttable.js"></script>
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/assets/js/ie-emulation-modes-warning.js"></script>
	<script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="http://code.jquery.com/jquery-latest.js"> </script>
    <script>
            $(document).ready(function() {                        
                $('#submit').click(function(event) {  
                    var username=$('#user').val();
                 $.get('ActionServlet',{user:username},function(responseText) { 
                        $('#welcometext').text(responseText);         
                    });
                });
            });
        </script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

		<script>
			    $("tr.table").click(function() {
			    var tableData = $(this).children("td").map(function() {
			        return $(this).text();
			    }).get();
			
			    alert($.trim(tableData[0]) + " , " + $.trim(tableData[1]));
			    //Here, Make a Ajax call to your Servlet payCheckInfo 
			});
		 </script>
  </head>

  <body>
  <%
   // Set refresh, autoload time as 5 seconds
   
   // Get current time
   %>

<% 
  
	String user="";
    String userEmail="";
	ProductsBeans products = new ProductsBeans();
	/*if (session == null)
  	{
    	String address = "/login-error.jsp";
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
    	dispatcher.forward(request,response);
  	} 
	
	if (session != null)
	{*/
		products=(ProductsBeans)session.getAttribute("PRODUCTS");
	/*	UserBean userBean=(UserBean)session.getAttribute("USER");
		//UserBean bean=(UserBean)request.getAttribute("bean");
		if(userBean.getUserName() == null || userBean == null || userBean.getUserName() == "" )
		{
	    	String address = "/login-error.jsp";
	    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
	    	dispatcher.forward(request,response);
		}
		
		out.print("Welcome, on selling.jsp page"+ userBean.getUserName());
		user=userBean.getUserName();
		userEmail=userBean.getEmail();

				
		System.out.println("hello user " + userBean.getUserName());
	}*/
%> 
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="btn btn-lg btn-primary"  class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Vehicle Options</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="MainPage.jsp">Back</a></li>
            <li><a href="ProfilePage.jsp">My Account</a></li>
            <li><a href="#">Shopping Cart</a></li>
            <!--  <li><a href="LoginPage.jsp">Sign Out</a></li>-->
          </ul>
          <form class="navbar-form navbar-right" action="SearchVehicles" method="get">
          	<input type="text" class="form-control" name="year" placeholder="YEAR">
            <input type="text" class="form-control" name="model" placeholder="MOdel..">
            <input type="text" class="form-control" name="make" placeholder="Make...">
              <input type="text" class="form-control" name="region" placeholder="Region...">
            <input class="btn btn-lg btn-warning" name="" type="submit" value="Search"></input>
          </form><!-- 
           <form class="navbar-form navbar-right" action="SearchBooksServlet" method="post">
          	<input style="width:500px" type="text" class="form-control" name="input" placeholder="Enter Author Name, Title or ISBN...">
             <input class="btn btn-lg btn-warning" name="" type="submit" value="Search"></input>
          </form> -->
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li>
            <li><a href="#">Reports</a></li>
            <li><a href="#">Analytics</a></li>
            <li><a href="#">Export</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="">Books Room</a></li>
            <li><a href="">Get Resources</a></li>
            <li><a href="">Contact Us</a></li>
            <li><a href="">Copy Center</a></li>
            <li><a href="">View Map</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="">Return Book</a></li>
            <li><a href="">Check Avalability</a></li>
            <li><a href="">Look For Job</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			
          
			<h2 class="sub-header" align="center">Vehicle Resources at copart</h2>
          <div class="row placeholders">
            <div class="col-xs-6 col-sm-3 placeholder">
             <a href="#"> <img src="//farm3.staticflickr.com/2580/33106091985_bee30af541_z.jpg" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail"></a>
              <a href="#"><h4>Course Books</h4></a>
             <a href="#"> <span class="text-muted">View</span></a>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <a href="#"><img src="//farm4.staticflickr.com/3880/33106091965_a81795d639_b.jpg" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail"></a>
              <a href="#"><h4>Novels</h4></a>
              <a href="#"><span class="text-muted">View</span></a>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <a href="#"><img src="//farm1.staticflickr.com/726/33106091915_a5ec47467a_c.jpg" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail"></a>
              <a href="#"><h4>Online Book</h4></a>
              <a href="#"><span class="text-muted">View</span></a>
            </div>
            <div  class="col-xs-6 col-sm-3 placeholder">
              <a href="#"><img  src="//farm1.staticflickr.com/761/32978954591_cb5df7ae44_b.jpg" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
              <a href="#"><h4 >CD/DVD</h4></a>
              <a href="#"><span class="text-muted">View</span></a>
            </div>
          </div>
			 
          <h2 class="sub-header" align="center">Some vehicles For you</h2>
		  <h3 class="sub-header"align="center">Sort vehicles according to your Requirements</h3>
          <div class="table-responsive">
           
            <table class="table table-striped, sortable">
              
                <tr>
                  <th>Year</th>
                  <th>Make</th>
                  <th>Model</th>
                  <th>Auction Date</th>
                  <th>Address</th>
                  <th>Damage Description</th>
                  <th>Vehicle images</th>
                 
                  
                  <td></td>				  
                </tr>
               <% for(int index = 0; index < products.getListSize(); index++)
	    {
            	   
	    	%>
	    	
	    		<tr class="table">
	    		  <td  name ="usernam" value=<%products.getISBN(index); %>><%out.print(products.getISBN(index)); %></td>
	    	      <td  name="itemi" value=<%products.getTITLE(index); %>><%out.print(products.getTITLE(index)); %></td>
	    	      <td  name ="itemnam" value=<%products.getAUTHOR(index);%> ><%out.print(products.getAUTHOR(index)); %></td>
	    	      <td  name="itempric" value=<%products.getAVAILABILITY(index);%>><%out.print(products.getAVAILABILITY(index)); %></td>
	    	     
        		  
        	 </tr>
        	  
        	 <%
	    	//System.out.println(books.getISBN(index));
	    }
    	%>
	    
             
            
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="../../dist/js/bootstrap.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="../../assets/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
