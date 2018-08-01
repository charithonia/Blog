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
        <title>Categories Page</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body>
	        
        <!-- NAVBAR -->

        <div class="navbar">
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
                    <li role="presentation" class="active">
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
	    <h1>Blog Categories</h1>
	</div>
	<div class="container">
	    <div class="row">
		<div class="col-md-6">
		    <h1>Categories</h1>
		    <hr/>
		    <table id="category-table" class="table">
			<thead>
			    <tr>
				<th>Category</th>
				<th width="15%"></th>
				<th width="15%"></th>
			    </tr>
			</thead>
			<tbody></tbody>
		    </table>

		    <div id="edit-category" style="display: none">
			<h1>Edit Category</h1>
			<form class="form-horizontal" role="form" id="edit-form">
			    <div class="form-group">
				<label for="edit-category-name" class="col-md-4 control-label">
				    Category Name:
				</label>
				<div class="col-md-8">
				    <input type="text"
					   class="form-control"
					   id="edit-category-name"
					   placeholder="Category Name" required/>
				</div>
				<input type="hidden" id="hidden-id"/>
				<div class="form-group">
				    <div class="col-md-offset-4 col-md-8">
					<button type="button"
						id="edit-button"
						class="btn btn-default">
					    Edit Category
					</button>
				    </div>
				</div>
			    </div>
			</form>
		    </div>
		</div>
		
		<div class="col-md-6">
		    <ul id="error-messages"></ul>
		    <h1>Add Category</h1>
		    <hr/>
		    <div id="add-category-form">
			<form class="form-horizontal">
			    <div class="form-group">
				<label for="add-category-name"
				       class="control-label col-md-4">
				    Category Name:
				</label>
				<div class="col-md-8">
				    <input id="add-category-name"
					   name="categoryName"
					   type="text"
					   class="form-control"
					   placeholder="Category Name"/>
				</div>
			    </div>
			    <div class="form-group">
				<div class="col-md-offset-4 col-md-8">
				    <button id="add-category-button"
					    type="button"
					    class="btn btn-default">
					Add
				    </button>
				</div>
			    </div>
			</form>
		    </div>
		</div>
	    </div>
	</div>

        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>        
        <script src="${pageContext.request.contextPath}/js/category.js"></script>        
    </body>
</html>