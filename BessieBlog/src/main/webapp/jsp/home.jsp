<%-- 
    Document   : reader_home
    Created on : Nov 6, 2017, 7:49:42 PM
    Author     : matt
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bessieStyle.css" rel="stylesheet"/>
    </head>
    <body>

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

        <!-- LOG OUT -->
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <p>Hello : ${pageContext.request.userPrincipal.name}
                | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
            </p>
        </c:if>

        <div class="jumbotron">
            <h1>Bessie's Blog</h1>
        </div>

        <c:if test="${empty hvm.pageToDisplay}">
            <div class="col-sm-3" id="displayCategories">
                <h4>Display Blogs by Category</h4>
                <ul>
                    <c:forEach items="${hvm.categoryList}" var = "currentCategory">
                        <li>
                            <a href="${pageContext.request.contextPath}/displayActiveBlogsByCategory?categoryId=${currentCategory.id}"><c:out value="${currentCategory.name}"/></a> 
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div class="col-xs-6">            
                <table class="table table-striped table-hover table-responsive">
                    <tr scope="row">
                        <th>Title: </th>
                        <th>Category:</th>
                        <th>Post Date: </th>
                        <th>Author: </th>
                        <th>Content: </th>
                    </tr>

                    <c:forEach var="currentBlog" items="${hvm.blogs}">
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/blogs/displayBlogDetails?blogId=${currentBlog.blogId}">
                                    <c:out value="${currentBlog.title}"/></a>
                            </td>
                            <td>
                                <c:out value="${currentBlog.category.name}"/>
                            </td>
                            <td>
                                <c:out value="${currentBlog.publicationDate}"/>

                            </td>  
                            <td>
                                <c:out value="${currentBlog.user.name}"/>

                            </td>
                            <td>
                                ${currentBlog.body}                 
                            </td> 
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>
        <c:out value="${hvm.pageToDisplay.body}" escapeXml="false"/>
    </body>
</html>