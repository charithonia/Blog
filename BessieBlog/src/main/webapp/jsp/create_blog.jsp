<%-- 
    Document   : create_post
    Created on : Nov 6, 2017, 7:49:02 PM
    Author     : matt
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Blog</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
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

            <!-- LOG OUT -->
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>


            <div class="jumbotron">
                <h1>Create Blog Post</h1>
            </div>
            <div id="createBlogForm" class="col-md-6">
                <form class="form-inline" role="form" 
                      action="createBlog" method="POST" >
                    <h3>Create Blog</h3>

                    <!--                        private int blogId;    
                        private String title;
                        private LocalDateTime creationDate;
                        private LocalDateTime experiationDate;
                        private LocalDateTime publicationDate;
                        private int userId;
                        private int categoryId;
                        private String body;
                        private boolean isApproved;
                        private List<Category> categoryList;
                    -->
                    <div>
                        <label for="title" class="col-md-4 control-label">Title: </label>
                        <div id="title">
                            <input type="text" name="title" id="title" />                           
                        </div>
                    </div><br/>
                    <div>
                        <label for="publicationDate" class="col-md-4 control-label">Publication Date: </label>
                        <div id="datepick">
                            <input type="datetime-local" name="publicationDate" id="publicationDate" />                           
                        </div>
                    </div><br/>
                    <div>
                        <label for="expirationDate" class="col-md-4 control-label">Expiration Date: </label>
                        <div id="expirationDate">
                            <input type="datetime-local" name="experiationDate"  id="expirationDate" />  

                        </div>
                    </div><br/>
                    <div>
                        <input type="radio" id="true"
                               name="isApproved" value="true">
                        <label for="true">Approved</label>

                        <input type="radio" id="false"
                               name="isApproved" value="false" checked>
                        <label for="false">Pending</label>
                    </div><br/>
                    <div>

                        <label for="categoryId" class="col-md-4 control-label">Category</label>

                        <select name="categoryId" class="form-control" id="categoryId">
                            <option  disabled selected>Select Category</option>
                            <c:forEach var="cat" items="${cbvm.categoryList}">
                                <option value="${cat.id}"><c:out value="${cat.name}"/></option>
                            </c:forEach>

                        </select>
                    </div><br/>


                    <div>
                        <label for="body">Blog body:</label>
                        <textarea name="body" class="tinymce" rows="5" id="body"></textarea>

                    </div><br/>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <br/>
                            <input type="submit" class="btn btn-primary" value="Create"/>
                            <a href="${pageContext.request.contextPath}/blogs/displayCreateBlog" type="button" class="btn btn-warning" value="Create"/>Cancel</a>
                        </div>
                    </div>
                </form>  

            </div>
        </div>


        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>        
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>        
        <script src="${pageContext.request.contextPath}/js/role.js"></script>
        <script src="${pageContext.request.contextPath}/tinymce_4.7.2/tinymce/js/tinymce/tinymce.min.js"></script>        
        <script src="${pageContext.request.contextPath}/tinymce_4.7.2/tinymce/js/tinymce/init-tinymce.js"></script>        
    </body>
</html>
