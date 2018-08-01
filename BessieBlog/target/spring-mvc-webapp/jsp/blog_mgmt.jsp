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
        <title>Blog Management</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body>
        <!-- {pageContext.request.contextPath}/{currentPage.title} don't forget to replace the dollar signs-->
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
                        <li role="presentation" class="active">
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
            <!-- END NAVBAR -->

            <!-- LOG OUT -->
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>


            <div class="jumbotron">
                <h1>Blog Post Management</h1>
            </div>




            <!-- 
                what does this page require?
                choice between adding a static page
                and 
            
            
                1. for admin
                    -buttons for displaying the three lists
                    -list items with edit and delete anchor tags
                    -a form for adding a new sp
                    -a form for editing an old sp
                    -a popup for confirming a deletion
                    
                2. for contributor
            
            -->



            <!-- ADMIN -->
            <!-- list selection buttons -->
            <div class="container">
                <!-- TOP BUTTONS -->
                <a href="${pageContext.request.contextPath}/blogs/displayCreateBlog" id="add-a-page-btn" class="btn btn-primary">Add a Page</a>


                <sec:authorize access="hasRole('ROLE_ADMIN')">

                    <div class="btn-group" role="group" aria-lable="Choose page type" id="admin-select-page-type">
                        <!-- select Active Pages button-->
                        <button class="btn btn-success" id="admin-active-pages-btn">Active Pages</button>
                        <!-- select Pending Pages button-->
                        <button class="btn btn-warning" id="admin-pending-pages-btn">Pending Pages</button>
                        <!-- select Expired Pages button-->
                        <button class="btn btn-danger" id="admin-expired-pages-btn">Expired Pages</button>
                    </div>
                </sec:authorize>
                <!-- CONTRIBUTOR -->
                <!-- list selection buttons -->
                <sec:authorize access="hasRole('ROLE_CONTRIBUTOR')">

                    <div class="btn-group" role="group" aria-lable="Choose page type" id="cont-select-page-type">
                        <!-- select Active Pages button-->
                        <button class="btn btn-success" id="cont-active-pages-btn">Active Pages</button>
                        <!-- select Pending Pages button-->
                        <button class="btn btn-warning" id="cont-pending-pages-btn">Pending Pages</button>
                        <!-- select Expired Pages button-->
                        <button class="btn btn-danger" id="cont-expired-pages-btn">Expired Pages</button>
                    </div>            
                </sec:authorize>
                <!-- END TOP BUTTONS -->


                <div id="inner-contents">






                    <!-- EDIT PAGE -->




                    <div id="edit-blog-post-div" class="col-sm-6" style="display: none">
                        <h2> Edit Blog Post</h2>
                        <form class="form-horizontal" role="form" id="edit-form">

                            <!-- TITLE -->

                            <div class="form-group">
                                <label for="edit-title" class="col-xs-3 control-label" for="edit-title">Title</label>

                                <div class="col-xs-5">
                                    <input type="text"
                                           class="form-control"
                                           id="edit-title"
                                           placeholder="Page title" required/>
                                </div>

                            </div>

                            <!-- BODY -->
                            <!-- NEEDS TINY MCE -->

                            <div class="form-group">
                                <label class="col-xs-3 control-label" for="edit-body"> Body </label>
                                <div class="col-xs-12">
                                    <textarea rows ="10" type="textarea" 
                                              class="form-control tinymce" name="edit-body" id="edit-body"></textarea>
                                </div>
                            </div>

                            <!-- CREATION DATE -->
                            <input type="hidden" id="edit-creation-date"/>

                            <!-- *** what about the user id?? *** -->

                            <input type="hidden" id="edit-user-id"/>

                            <!-- PAGE ID -->
                            <input type="hidden" id="edit-page-id"/>
                            <!-- Category ID -->
                            <input type="hidden" id="edit-category-id"/>

                            <!-- PUBLICATION DATE -->

                            <div class="form-group">
                                <label class="col-xs-3 control-label"> Publication Date </label>
                                <div class="col-xs-5">
                                    <input type="date-local" class="form-control" 
                                           name="publicationDate"
                                           id="edit-publication-date"/>
                                </div>
                            </div>


                            <!-- EXPIRATION DATE -->

                            <div class="form-group">
                                <label class="col-xs-3 control-label"> Expiration Date </label>
                                <div class="col-xs-5">
                                    <input type="date-local" class="form-control" name="expirationDate"
                                           id="edit-expiration-date"/>
                                </div>
                            </div>

                            <!-- IS APPROVED? -->

                            <div class="form-group">
                                <label class="col-xs-3 control-label"> Approve page: </label>
                                <div class="col-xs-5">
                                    <input type="checkbox" class="form-control" name="approval"
                                           id="edit-approval"/>
                                </div>
                            </div>

                            <!-- SUBMIT & RESET BUTTONS -->

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="button"
                                            id="submit-edit-btn"
                                            class="btn btn-default">
                                        Submit Edit
                                    </button>
                                    <button type="reset"
                                            id="edit-cancel-button"
                                            class="btn btn-default">
                                        Reset
                                    </button>
                                </div>
                            </div>
                        </form> <!-- END EDIT FORM -->
                    </div>


                    <!-- TABLES -->

                    <!-- ACTIVE PAGES TABLE-->
                    <div id="active-table" style="display:none">
                        <table id="activePagesTable" class="table table-hover">
                            <tr>
                                <th width="25%">Page Title</th>
                                <th width="15%">Created</th>
                                <th width="15%">Published</th>
                                <th width="15%">Expiration</th>
                                <th width="15%">User</th>
                                <th width="15"></th>
                            </tr>
                            <tbody id="active-body"></tbody>
                        </table>
                    </div>
                    <!-- PENDING PAGES TABLE-->
                    <div id="pending-table" style="display:none">
                        <table id="pendingPagesTable" class="table table-hover">
                            <tr>
                                <th width="25%">Page Name</th>
                                <th width="15%">Created</th>
                                <th width="15%">User</th>
                                <th width="15"></th>
                            </tr>
                            <tbody id="pending-body"></tbody>
                        </table>
                    </div>
                    <!-- EXPIRED PAGES TABLE-->
                    <div id="expired-table" style="display:none">
                        <table id="expiredPagesTable" class="table table-hover">
                            <tr>
                                <th width="25%">Page Name</th>
                                <th width="15%">Created</th>
                                <th width="15%">Published</th>
                                <th width="15%">Expiration</th>
                                <th width="15%">User</th>
                                <th width="15"></th>
                            </tr>
                            <tbody id="expired-body"></tbody>

                        </table>
                    </div>
                </div>
            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>        
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>        
            <script src="${pageContext.request.contextPath}/js/blog_mgmt.js"></script>        
            <script src="${pageContext.request.contextPath}/tinymce_4.7.2/tinymce/js/tinymce/tinymce.min.js"></script>        
            <script src="${pageContext.request.contextPath}/tinymce_4.7.2/tinymce/js/tinymce/init-tinymce.js"></script>
    </body>
</html>