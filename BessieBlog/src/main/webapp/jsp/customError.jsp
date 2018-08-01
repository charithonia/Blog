<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ERROR!</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <!-- SWC Icon -->
    </head>
    <body>
        <div class="container">
            <h1>Bessie's Blog</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/">Return to a safe place</a>
                    </li>
                </ul>
            </div>
            <div>
                <h1>LOOK OUT! </h1>
                <blockquote>“The world is drowning in weirdness and lies......and here we are, so used to it that we're actually bored!”</blockquote>
                <p>Your request was also drowning in weirdness and lies....</p>
                <h3>${errorMessage}</h3>
            </div>
            <img src="${pageContext.request.contextPath}/images/giphy.gif"/>
        </div>
            <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    </body>
</html>