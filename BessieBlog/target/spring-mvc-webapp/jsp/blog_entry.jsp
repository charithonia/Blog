<%-- 
    Document   : categories
    Created on : Nov 6, 2017, 7:40:44 PM
    Author     : matt
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog Creation</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body>
        <!-- {pageContext.request.contextPath}/{currentPage.title} don't forget to replace the dollar signs-->
        
         
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
                    <li role="presentation">
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
                
        <div class="jumbotron">
            <h1>Blog Details</h1>
        </div>


                <!-- LOG OUT -->
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <p>Hello : ${pageContext.request.userPrincipal.name}
                | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
            </p>
        </c:if>
        
       
        <div id="sightingDisplayBox">
                <table class="table table-striped table-hover table-responsive" id="sightingOutput">
                    <tr scope="row" class="displaySizingRows">
                        <th class="text-center">BLOG:  </th>
                       
                    </tr>
                    <tr scope="row" class="displaySizingRows">
                        <td class="text-center">Title: </td>
                        <td class="displaySizingColumns"><c:out value="${bvm.title}"/></td>
                    </tr>
                    <tr scope="row" class="displaySizingRows">
                        <td class="displaySizingColumns">Post Date: </td>
                        <td class="displaySizingColumns"><c:out value="${bvm.creationDate}"/></td>
                    </tr>
                    <tr scope="row" class="displaySizingRows">
                        <td class="displaySizingColumns">Expiration Date: </td>
                        <td class="displaySizingColumns"><c:out value="${bvm.expirationDate}"/></td>
                    </tr>
                    <tr scope="row" class="displaySizingRows">
                        <td class="displaySizingColumns">Published Date: </td>
                        <td class="displaySizingColumns"><c:out value="${bvm.publicationDate}"/></td>
                    </tr>
                    <tr scope="row" class="displaySizingRows">
                        <td class="displaySizingColumns">Category: </td>
                        <td class="displaySizingColumns">                      
                            <c:out value="${bvm.category.name}"/>&nbsp;                          
                        </td>
                    </tr>
                    <tr scope="row" class="displaySizingRows">
                        <td class="displaySizingColumns">User: </td>
                        <td class="displaySizingColumns"><c:out value="${bvm.user.name}"/></td>
                    </tr>
                    <tr scope="row" class="displaySizingRows">
                        <td class="displaySizingColumns">Body: </td>
                        <td class="displaySizingColumns">${bvm.body}                     
                    </tr>
                </table>
                </div>
        <div id="editRole" style="display: none">
            <h2> Edit Role</h2>
            <form class="form-inline" role="form" id="edit-form">
                <div class="form-group">
                    <label for="edit-name" class="col-md-4 control-label">
                        Role Name:
                    </label>
                    <input type="hidden" id="hidden-id"/>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="edit-name"
                               placeholder="Role Name" required/>
                    </div><br/><br/>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="button"
                                    id="edit-button"
                                    class="btn btn-default">
                                Edit Role
                            </button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="button"
                                    id="cancel-edit-button"
                                    class="btn btn-warning">
                                Cancel
                            </button>
                        </div>
                    </div>
                </div>


            </form>
        </div>


            

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>        
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>        
        <script src="${pageContext.request.contextPath}/js/role.js"></script>        
    </body>
</html>