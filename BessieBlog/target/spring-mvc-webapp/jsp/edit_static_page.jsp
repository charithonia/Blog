<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Static Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-2">
                    <h1>Edit Static Page</h1>
                </div>
            </div>
            <hr>
            <div class="row">

                <!-- FORM START -->

                <form id="updateSuperPersonForm" class="form-horizontal"
                         action="${pageContext.request.contextPath}/static_page/updateStaticPage" method="POST" >


                    <input type="hidden" value="" name="staticPageId"> <!--  CHECK NAME -->                   

                    <!-- TITLE -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label"> Title </label>
                        <div class="col-xs-5">
                            <input type="text" class="form-control" name="title"
                                   value="" id="title"/>
                            </div>
                        </div>

                    <!-- BODY -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label"> Body </label>
                        <div class="col-xs-5">
                                <textarea rows ="10" type="textarea" 
                                          class="form-control" name="body" id="body"></textarea>
                        </div>
                    </div>
                    
                    <!-- CREATION DATE -->
                    <input type="hidden" value="" name="creationDate">                   
                    
                    
                    <!-- PUBLICATION DATE -->
                    
                    <div class="form-group">
                        <label class="col-xs-3 control-label"> Publication Date </label>
                        <div class="col-xs-5">
                            <input type="date" class="form-control" name="publicationDate"
                                   value="" id="publicationDate"/>
                            </div>
                        </div>

                    
                    <!-- EXPIRATION DATE -->
                    
                    <div class="form-group">
                        <label class="col-xs-3 control-label"> Expiration Date </label>
                        <div class="col-xs-5">
                            <input type="date" class="form-control" name="expirationDate"
                                   value="" id="expirationDate"/>
                            </div>
                        </div>

                    <!-- USER NAME -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label"> User Name </label>
                        <div class="col-xs-5">
                            <input type="text" class="form-control" name="userName"
                                   value="" id="userName"/>
                            </div>
                        </div>
                    
                    <!-- USER ROLE -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label"> User Role </label>
                        <div class="col-xs-5">
                            <input type="text" class="form-control" name="userRole"
                                   value="" id="userRole"/>
                            </div>
                        </div>
                    
                    <!-- NAVORDER -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label"> Order in Nav Bar</label>
                        <div class="col-xs-5">
                            <input type="text" class="form-control" name="navOrder"
                                   value="" id="navOrder"/>
                            </div>
                        </div>
                    
                    <!-- SLUG -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label"> Slug </label>
                        <div class="col-xs-5">
                            <input type="text" class="form-control" name="slug"
                                   value="" id="slug"/>
                            </div>
                        </div>

                    
                    <!-- BUTTONS -->

                    <div class="form-group">
                        <div class="col-xs-5 col-xs-offset-3">
                            <button type="submit" class="btn btn-default" id="btnUpdateSuperPerson">Save Edit</button>
                            <button type="submit" class="btn btn-default" id="btnCancel" formaction="${pageContext.request.contextPath}/" formmethod="GET">Cancel</button>
                        </div>
                    </div>
                </form>
                <!-- FORM END -->                   

            </div>
        </div>
        <!-- Main Page Content Start -->
        <!-- Main Page Content Stop -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {

                $.ajax({
                    type: 'GET',
                    url: 'http://localhost:8080/displayEditStaticPage/{id}',
                    success: function (uspvm) {
                        // get a reference to the 'allContacts' div
                        var contactsDiv = $('#allContacts');

                        // go through each of the returned contacts and append the info to the
                        //contactsDiv
                        $.each(contactArray, function (index, contact) {
                            var contactInfo = '<p>';
                            contactInfo += 'Name: ' + contact.firstName + ' ' + contact.lastName + '<br>';
                            contactInfo += 'Company: ' + contact.company + '<br>';
                            contactInfo += 'Email: ' + contact.email + '<br>';
                            contactInfo += 'Phone: ' + contact.phone + '<br>';
                            contactInfo += '<hr>';

                            contactsDiv.append(contactInfo);
                        })
                    },
                    error: function () {
                        alert('FAILURE!');
                    }
                });

                $('#submit').on('click', function () {
                    $.ajax({
                        type: 'POST',
                        url: 'http://localhost:8080/updateStaticPage/{id}',
                        data: JSON.stringify({
                            title: $('#add-first-name').val(),
                            creationDate: $('#add-last-name').val(),
                            expirationDate: $('#add-company').val(),
                            publicationDate: $('#add-phone').val(),
                            userId: $('#add-email').val(),
                            navOrder: $('#add-email').val(),
                            body: $('#add-email').val(),
                            slug: $('#add-email').val(),
                            isApproved: $('#add-email').val()
                        }),
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        'dataType': 'json',
                        success: function (contact) {
                            // get a reference to the 'newContact' div
                            var newContactDiv = $('#newContact');

                            // append contact info to the newContact div
                            var contactInfo = '<p>';
                            contactInfo += 'Name: ' + contact.firstName + ' ' + contact.lastName + '<br>';
                            contactInfo += 'Company: ' + contact.company + '<br>';
                            contactInfo += 'Email: ' + contact.email + '<br>';
                            contactInfo += 'Phone: ' + contact.phone + '<br>';
                            contactInfo += '<hr>';

                            newContactDiv.append(contactInfo);

                        },
                        error: function () {
                            alert('FAILURE');
                        }
                    });

                });

            })
        </script>
    </body>
</html>