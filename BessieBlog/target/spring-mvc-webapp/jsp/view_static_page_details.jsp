<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Static Page Details</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-2">
                    <h1>Static Page Details</h1>
                </div>
            </div>
            <hr>
            <div class="row">
                <!-- DETAILS -->
                <div id="staticPageDetails"></div>

            </div>
        </div>
        <!-- Main Page Content Start -->
        <!-- Main Page Content Stop -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function(){

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/displayStaticPageToAdmin/{id}', //same as controller endpoint
        success: function(spvm) { //is this what gets passed in from the controller method?

                var staticPageDiv = $('#staticPageDetails');

                var staticPageInfo = '<p>';
                staticPageInfo += 'Id: ' + spvm.id + '<br>';
                staticPageInfo += 'Title: ' + spvm.title + '<br>';
                staticPageInfo += 'User name: ' + spvm.user.name + '<br>';
                staticPageInfo += 'User role: ' + spvm.user.role.name + '<br>';
                staticPageInfo += 'Body: ' + spvm.body + '<br>';
                staticPageInfo += 'Approved: ' + spvm.isApproved + '<br>';
                staticPageInfo += 'Creation Date: ' + spvm.creationDate + '<br>';
                staticPageInfo += 'Publication Date: ' + spvm.publicationDate + '<br>';
                staticPageInfo += 'Expiration Date: ' + spvm.expirationDate + '<br>';
                staticPageInfo += 'Navbar Location: ' + spvm.navOrder + '<br>';
                staticPageInfo += 'Slug: ' + spvm.slug + '<br>';
                staticPageInfo += '</p>';

                staticPageDiv.append(staticPageInfo);
            
        },
        error: function() {
            alert('FAILURE!');
        }
    });
            });
            
            
        </script>
    </body>
</html>