<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Bessie Blog: Login</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
        <!-- NAVBAR -->

        <div class="navbar col-md-12">
            <ul class="nav nav-tabs col-md-10" >
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/">Home</a>
                </li>
                <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_CONTRIBUTOR')">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/blogs/displayCreateBlog">Create Blog</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/blogs/">Blog Post Management</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/user_static_pages/">Static Pages</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/categories/">Category Home</a>
                    </li>

                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/roles/">Role Home</a>
                    </li>
                </sec:authorize>
                <c:forEach items="${hvm.staticPages}"
                           var="currentPage">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayOneStaticPage?staticPageId=${currentPage.id}">
                            <c:out value="${currentPage.title}"/>
                        </a>
                    </li>
                </c:forEach>
            </ul>
            <div class=" btn navbar-btn pull-right col-md-2">
                <a href="${pageContext.request.contextPath}/login/"><button>Log In</button></a>
            </div>
        </div>
        <!-- END NAVBAR -->

            <div class="jumbotron">
            <h1>Bessie Blog</h1>
            </div>
            <hr/>
            <h2>Login Page</h2>

            <c:if test="${param.login_error == 1}">
                <h3>Wrong id or password!</h3>
            </c:if>
            <c:url value="/j_spring_security_check" var="loginUrl"/>
            <form class="form-horizontal" 
                  role="form" 
                  method="post" 
                  action="${loginUrl}">
                <div class="form-group">
                    <label for="j_username" class="col-md-4 control-label">Username:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="j_username" placeholder="Username"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="j_password" class="col-md-4 control-label">Password:</label>
                    <div class="col-md-8">
                        <input type="password" class="form-control" name="j_password" placeholder="Password"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" id="search-button" value="Sign In"/>
                    </div>
                </div>
            </form>    
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>