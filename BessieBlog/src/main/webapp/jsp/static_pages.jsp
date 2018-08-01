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
        <title>Static Pages</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body>
        <!-- {pageContext.request.contextPath}/{currentPage.title} don't forget to replace the dollar signs-->
        <div class="container">
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
                <h1>Static Page Management</h1>
            </div>

            <ul class="list-group" id="errorMessages"></ul>

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
                <button id="add-a-page-btn" class="btn btn-primary">Add a Page</button>
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

                    <!-- ADD PAGE -->
                    <!-- this should have every field other than sp id available
                    availability depends on user role-->



                    <div id="add-static-page-div" class="col-sm-6" style="display: none">
                        <h2> Add Static Page</h2>
                        <form class="form-horizontal" role="form" id="add-form">

                            <!-- TITLE -->

                            <div class="form-group">
                                <label for="add-title" class="col-xs-3 control-label">Title</label>
                                <div class="col-xs-5">
                                    <input type="text"
                                           class="form-control"
                                           id="add-title"
                                           placeholder="Page title" required maxlength="100"/>
                                </div>

                            </div>
                            <!-- NEED THE AUTHOR ID!!!-->
                            <!-- 
                            -->
                            <!--PLACE HOLDER AS 1 UNTIL SECURITY INTRODUCED -->
                            <input type="hidden" id="add-user-author-id" value="1"/>

                            <!-- SLUG -->

                            <div class="form-group">
                                <label for="add-slug"class="col-xs-3 control-label"> Slug </label>
                                <div class="col-xs-5">
                                    <input type="text" class="form-control" name="slug"
                                           id="add-slug" required maxlength="150"/>
                                </div>
                            </div>



                            <!-- BODY -->

                            <div class="form-group">
                                <label class="col-xs-3 control-label" for="add-body"> Body </label>
                                <div class="col-xs-12">
                                    <textarea rows ="10" type="textarea" 
                                              class="form-control tinymce" name="add-body" id="add-body" required maxlength="65535"></textarea>
                                </div>
                            </div>


                            <!-- PUBLICATION DATE -->
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <div class="form-group">
                                    <label class="col-xs-3 control-label"> Publication Date </label>
                                    <div class="col-xs-5">
                                        <input type="datetime-local" class="form-control" name="publicationDate"
                                               id="add-publication-date"/>
                                    </div>
                                </div>

                                <!-- EXPIRATION DATE -->
                                <div class="form-group">
                                    <label class="col-xs-3 control-label"> Expiration Date </label>
                                    <div class="col-xs-5">
                                        <input type="datetime-local" class="form-control" name="expirationDate"
                                               id="add-expiration-date"/>
                                    </div>
                                </div>

                                <!-- NAVORDER -->

                                <div class="form-group">
                                    <label for="add-nav-order" class="col-xs-3 control-label"> Order in Nav Bar</label>
                                    <div class="col-xs-5">
                                        <input type="number" class="form-control" name="navOrder"
                                               id="add-nav-order" min="1"/>
                                    </div>
                                </div>



                                <!-- IS APPROVED? -->

                                <div class="form-group">
                                    <label class="col-xs-3 control-label"> Approve page: </label>
                                    <div class="col-xs-5">
                                        <input type="checkbox" class="form-control" name="approval"
                                               id="add-approval"/>
                                    </div>
                                </div>
                            </sec:authorize>
                            <!-- SUBMIT & RESET BUTTONS -->

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="button"
                                            id="submit-add-btn"
                                            class="btn btn-default">
                                        Submit New Page
                                    </button>
                                    <button type="reset"
                                            id="add-reset-btn"
                                            class="btn btn-default">
                                        Reset
                                    </button>
                                    <div class="success-message"></div>
                                </div>
                            </div>

                        </form> <!-- END ADD FORM -->
                    </div>




                    <!-- EDIT PAGE -->




                    <div id="edit-static-page-div" class="col-sm-6" style="display: none">
                        <h2> Edit Static Page</h2>
                        <form class="form-horizontal" role="form" id="edit-form">

                            <!-- TITLE -->

                            <div class="form-group">
                                <label for="edit-title" class="col-xs-3 control-label" for="edit-title">Title</label>

                                <div class="col-xs-5">
                                    <input type="text"
                                           class="form-control"
                                           id="edit-title"
                                           placeholder="Page title" required maxlength="100"/>
                                </div>

                            </div>

                            <!-- SLUG -->

                            <div class="form-group">
                                <label class="col-xs-3 control-label"> Slug </label>
                                <div class="col-xs-5">
                                    <input type="text" class="form-control" name="slug"
                                           id="edit-slug" required maxlength="150"/>
                                </div>
                            </div>

                            <!-- BODY -->
                            <!-- NEEDS TINY MCE -->

                            <div class="form-group">
                                <label class="col-xs-3 control-label" for="edit-body"> Body </label>
                                <div class="col-xs-12">
                                    <textarea rows ="10" type="textarea" 
                                              class="form-control tinymce" name="edit-body" id="edit-body" required maxlength="65535"></textarea>
                                </div>
                            </div>


                            <!-- CREATION DATE -->
                            <input type="hidden" id="edit-creation-date"/>

                            <!-- *** what about the user id?? *** -->
                            <!-- defaults to 1, reset to use ViewModel value, saves as
                                view model value
                            -->
                            <input type="hidden" id="edit-user-id" value="1"/>

                            <!-- PAGE ID -->
                            <input type="hidden" id="edit-page-id"/>


                            <!-- PUBLICATION DATE -->
                            <sec:authorize access="hasRole('ROLE_ADMIN')">

                                <div class="form-group">
                                    <label class="col-xs-3 control-label"> Publication Date </label>
                                    <div class="col-xs-5">
                                        <input type="datetime-local" class="form-control" 
                                               name="publicationDate"
                                               id="edit-publication-date"/>
                                    </div>
                                </div>


                                <!-- EXPIRATION DATE -->

                                <div class="form-group">
                                    <label class="col-xs-3 control-label"> Expiration Date </label>
                                    <div class="col-xs-5">
                                        <input type="datetime-local" class="form-control" name="expirationDate"
                                               id="edit-expiration-date"/>
                                    </div>
                                </div>

                                <!-- NAVORDER -->

                                <div class="form-group">
                                    <label class="col-xs-3 control-label"> Order in Nav Bar</label>
                                    <div class="col-xs-5">
                                        <input type="number" class="form-control" name="navOrder"
                                               id="edit-nav-order"/>
                                    </div>
                                </div>



                                <!-- IS APPROVED? -->

                                <div class="form-group">
                                    <label class="col-xs-3 control-label"> Approve page: </label>
                                    <div class="col-xs-5">
                                        <input type="checkbox" class="form-control" name="edit-approval"
                                               id="edit-approval"/>
                                    </div>
                                </div>
                            </sec:authorize>
                            <!-- SUBMIT & RESET BUTTONS -->

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="button"
                                            id="submit-edit-btn"
                                            class="btn btn-default">
                                        Submit Edit
                                    </button>
                                    <button type="reset"
                                            id="edit-reset-btn"
                                            class="btn btn-default">
                                        Reset
                                    </button>
                                    <div class="success-message"></div>
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
                        <div class="success-message"></div>
                    </div>


                    <!-- PENDING PAGES TABLE-->
                    <div id="pending-table" style="display:none">
                        <table id="pendingPagesTable" class="table table-hover">
                            <tr>
                                <th width="25%">Page Name</th>
                                <th width="15%">Created</th>
                                <th width="15%">Published</th>
                                <th width="15%">Expiration</th>
                                <th width="15%">User</th>
                                <th width="15"></th>
                            </tr>
                            <tbody id="pending-body"></tbody>
                        </table>
                        <div class="success-message"></div>
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
                        <div class="success-message"></div>
                    </div>

                    <dialog id="add-dialog">
                        <p>Page added successfully!</p>
                    </dialog>

                    <dialog id="edit-dialog">
                        <p>Page edited successfully!</p>
                    </dialog>


                    <dialog id="delete-dialog">
                        <p>Page deleted successfully!</p>
                    </dialog>



                    <dialog id="confirm-delete-dialog">
                        <form method="dialog">
                            <div class="form-group">
                                <p>Are you sure you want to delete?</p>
                                <div class="btn-group">
                                    <button type="button" id="confirm-delete">Yes</button>
                                    <button type="button" id="cancel-delete"> No</button>
                                </div>
                            </div>
                        </form>
                    </dialog>

                    <dialog id="page-viewer">
                        <div id="page-viewer-div"></div>
                    </dialog>
                </div>
            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>        
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>        
            <script src="${pageContext.request.contextPath}/js/staticPages.js"></script>        
            <script src="${pageContext.request.contextPath}/tinymce_4.7.2/tinymce/js/tinymce/tinymce.min.js"></script>        
            <script src="${pageContext.request.contextPath}/tinymce_4.7.2/tinymce/js/tinymce/init-tinymce.js"></script>
        </div>
    </body>
</html>



