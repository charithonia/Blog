/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
//target the buttons and the anchor tags
//show form to add a page

    console.log("The page is loaded");
    $('#add-a-page-btn').on("click", function () {
        $('#inner-contents').children("div").hide();
        emptySuccessMessages();
        $('#add-static-page-div').show();
        $('dialog').attr("open", false);
    });
    $('#submit-add-btn').on("click", function () {
        tinyMCE.triggerSave();
        var body = tinyMCE.get('add-body').getContent();
        $('dialog').attr("open", false);

        //validation
        if (isTheBodyFilled(body)) {

            console.log("And the body is... " + body);
            console.log("This is the get test result: " + body);

            // check for errors and display any that we have
            // pass the input associated with the add form to the validation function
//            var haveValidationErrors = checkAndDisplayValidationErrors($('#add-form').find('input'));
//
//            // if we have errors, bail out by returning false
//            if (haveValidationErrors) {
//                return false;
//            }

            //if we make it here there were no errors so make the ajax call

            $.ajax({
                type: 'POST',
                url: '/BessieBlog/user_static_pages/create', //replace with contextPath etc
                data: JSON.stringify({
                    title: $('#add-title').val(),
                    expirationDate: $('#add-expiration-date').val(),
                    publicationDate: $('#add-publication-date').val(),
                    navOrder: $('#add-nav-order').val(),
                    userId: $('#add-user-author-id').val(), //this wont' work until security in place, or we can hack the js?
                    isApproved: $('#add-approval').is(':checked'),
                    body: body,
                    slug: $('#add-slug').val(),
                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'dataType': 'json',
                success: function () {
                    //a message should display at top
                    displaySuccessMessage("Page added");
                    $('#add-reset-btn')[0].click(); //this might not be kosher
                    $('#add-dialog')[0].showModal();
//
                    //success and directions to click to next spot
                    console.log("successfully added");

                    // clear errorMessages
                   // $('#errorMessages').empty();

                },
                error: function () {
                    console.log("the form couldn't be saved through the controller");
//                    $('#errorMessages')
//                            .append($('<li>')
//                                    .attr({class: 'list-group-item list-group-item-danger'})
//                                    .text('Error calling web service.  Please try again later.'));
                }
            });
        } else {
            console.log("The body was blank! Make sure you enter something");
            // check for errors and display any that we have
            // pass the input associated with the add form to the validation function
//            var haveValidationErrors = checkAndDisplayValidationErrors($('#add-form').find('input'));
//
//            var bodyErrorMessage = "The body was blank! Make sure you enter something";
//            $('#errorMessages').append(bodyErrorMessage);
//
//            // if we have errors, bail out by returning false
//            if (haveValidationErrors) {
//                return false;
//            }
        }

    });

    $('#submit-edit-btn').on("click", function () {
        tinyMCE.triggerSave();
        $('dialog').attr("open", false);

        var idToUpdate = $('#edit-page-id').val();
        //var body = $('#edit-body').val();
        var body = tinyMCE.get('edit-body').getContent();
        console.log("And the body in the edit form is... " + body);

        if (isTheBodyFilled(body)) {
      
            // check for errors and display any that we have
            // pass the input associated with the add form to the validation function
            //var haveValidationErrors = checkAndDisplayValidationErrors($('#add-form').find('input'));

            // if we have errors, bail out by returning false
//            if (haveValidationErrors) {
//                return false;
//            }

            //if we make it here there were no errors so make the ajax call

            $.ajax({
                type: 'PUT',
                url: '/BessieBlog/user_static_pages/update/' + idToUpdate,
                data: JSON.stringify({
                    id: idToUpdate,
                    title: $('#edit-title').val(),
                    creationDate: $('#edit-creation-date').val(),
                    expirationDate: $('#edit-expiration-date').val(),
                    publicationDate: $('#edit-publication-date').val(),
                    body: body,
                    userId: $('#edit-user-id').val(),
                    navOrder: $('#edit-nav-order').val(),
                    slug: $('#edit-slug').val(),
                    isApproved: $('#edit-approval').is(':checked'),
                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'dataType': 'json',
                success: function () {
                    displaySuccessMessage("Page edited");
                    $('#edit-reset-btn')[0].click();
                    console.log("Update successful");
                    $('#edit-dialog')[0].showModal();

                    // clear errorMessages
                    $('#errorMessages').empty();

                },
                error: function () {
                    console.log("the form couldn't get through to the controller");
//                    $('#errorMessages')
//                            .append($('<li>')
//                                    .attr({class: 'list-group-item list-group-item-danger'})
//                                    .text('Error calling web service.  Please try again later.'));
                }

            });
        } else {
            console.log("The body was blank! Make sure you enter something");
            // check for errors and display any that we have
            // pass the input associated with the add form to the validation function
//            var haveValidationErrors = checkAndDisplayValidationErrors($('#add-form').find('input'));
//
//            var bodyErrorMessage = "The body was blank! Make sure you enter something";
//            $('#errorMessages').append(bodyErrorMessage);
//
//            // if we have errors, bail out by returning false
//            if (haveValidationErrors) {
//                return false;
//            }
        }
    });
    //show admin table for active pages

    $('#admin-active-pages-btn').on("click", function () {
        $('dialog').attr("open", false);

        console.log("You are trying to load all the active pages");
        $('#active-body').empty();
        emptySuccessMessages();
        $('#inner-contents').children("div").hide();
        $('#active-table').show();
        var contentRows = $('#active-body');
        console.log("You are about to load all the active pages");
        $.ajax({

            type: 'GET',
            url: '/BessieBlog/admin_static_pages/all_active',
            success: function (spvmList) {
                console.log("You are inside the success block for loading all the active pages");
                $.each(spvmList, function (index, spvm) {


                    var title = spvm.title;
                    var creationDate = spvm.creationDate;
                    var publicationDate = spvm.publicationDate;
                    var expirationDate = spvm.expirationDate;
                    var userName = spvm.userName;
                    var pageId = spvm.id;
                    var row = '<tr>';
                    row += '<td>' + title + '</td>';
                    row += '<td>' + creationDate + '</td>';
                    row += '<td>' + publicationDate + '</td>';
                    row += '<td>' + expirationDate + '</td>';
                    row += '<td>' + userName + '</td>';
                    row += '<td><a onclick="showEditPageForm(' + pageId + ')">Edit</a> | <a onclick="deletePage(' + pageId + ')">Delete | <a onclick="viewPage(' + pageId + ')">View</a> </a></td>';
                    row += '</tr>'

                    contentRows.append(row);
                });
            },
            error: function () {
                console.log("you didn't get the list of pages from the controller");
            }

        });
    });
    //show admin table for pending pages

    $('#admin-pending-pages-btn').on("click", function () {

        $('dialog').attr("open", false);

        emptySuccessMessages();
        $('#pending-body').empty();
        $('#inner-contents').children("div").hide();
        $('#pending-table').show();
        var contentRows = $('#pending-body');
        $.ajax({
            type: 'GET',
            url: '/BessieBlog/admin_static_pages/all_pending',
            success: function (spvmList) {
                $.each(spvmList, function (index, spvm) {


                    var title = spvm.title;
                    var creationDate = spvm.creationDate;
                    var publicationDate = spvm.publicationDate;
                    var expirationDate = spvm.expirationDate;
                    var userName = spvm.userName;
                    var pageId = spvm.id;
                    var row = '<tr>';
                    row += '<td>' + title + '</td>';
                    row += '<td>' + creationDate + '</td>';
                    row += '<td>' + publicationDate + '</td>';
                    row += '<td>' + expirationDate + '</td>';
                    row += '<td>' + userName + '</td>';
                    row += '<td><a onclick="showEditPageForm(' + pageId + ')">Edit</a> | <a onclick="deletePage(' + pageId + ')">Delete</a>| <a onclick="viewPage(' + pageId + ')">View</a> </a></td>';
                    row += '</tr>'

                    contentRows.append(row);
                });
            },
            error: function () {
                console.log("you didn't get the list of pages from the controller");
            }

        });
    });
    //show admin table for expired pages
    $('#admin-expired-pages-btn').on("click", function () {
        $('dialog').attr("open", false);

        emptySuccessMessages();
        $('#expired-body').empty();
        $('#inner-contents').children("div").hide();
        $('#expired-table').show();
        var contentRows = $('#expired-body');
        $.ajax({
            type: 'GET',
            url: '/BessieBlog/admin_static_pages/all_expired',
            success: function (spvmList) {
                $.each(spvmList, function (index, spvm) {


                    var title = spvm.title;
                    var creationDate = spvm.creationDate;
                    var publicationDate = spvm.publicationDate;
                    var expirationDate = spvm.expirationDate;
                    var userName = spvm.userName;
                    var pageId = spvm.id;
                    var row = '<tr>';
                    row += '<td>' + title + '</td>';
                    row += '<td>' + creationDate + '</td>';
                    row += '<td>' + publicationDate + '</td>';
                    row += '<td>' + expirationDate + '</td>';
                    row += '<td>' + userName + '</td>';
                    row += '<td><a onclick="showEditPageForm(' + pageId + ')">Edit</a> | <a onclick="deletePage(' + pageId + ')">Delete</a>| <a onclick="viewPage(' + pageId + ')">View</a> </a></td>';
                    row += '</tr>'

                    contentRows.append(row);
                });
            },
            error: function () {
                console.log("you didn't get the list of pages from the controller");
            }

        });
    });
    /*     $('#active-body').empty();
     $('#inner-contents').children("div").hide();
     $('#active-table').show();
     
     
     var contentRows = $('#active-body');
     */

    //show contributor table for active pages
    $('#cont-active-pages-btn').on("click", function () {
        $('dialog').attr("open", false);

        emptySuccessMessages();
        $('#active-body').empty();
        $('#inner-contents').children("div").hide();
        $('#active-table').show();
        var contentRows = $('#active-body');
        $.ajax({
            type: 'GET',
            url: '/BessieBlog/cont_static_pages/active_for_user',
            success: function (spvmList) {
                $.each(spvmList, function (index, spvm) {

                    var title = spvm.title;
                    var creationDate = spvm.creationDate;
                    var publicationDate = spvm.publicationDate;
                    var expirationDate = spvm.expirationDate;
                    var userName = spvm.userName;
                    var pageId = spvm.id;
                    var row = '<tr>';
                    row += '<td>' + title + '</td>';
                    row += '<td>' + creationDate + '</td>';
                    row += '<td>' + publicationDate + '</td>';
                    row += '<td>' + expirationDate + '</td>';
                    row += '<td>' + userName + '</td>';
                    row += '<td><a onclick="showEditPageForm(' + pageId + ')">Edit</a> | <a onclick="deletePage(' + pageId + ')">Delete</a> | <a onclick="viewPage(' + pageId + ')">View</a>| <a onclick="viewPage(' + pageId + ')">View</a> </a></td>';
                    row += '</tr>'

                    contentRows.append(row);
                });
            },
            error: function () {
                console.log("you didn't get the list of pages from the controller");
            }

        });
    });
    //show contributor table for pending pages
    $('#cont-pending-pages-btn').on("click", function () {
        $('dialog').attr("open", false);

        emptySuccessMessages();
        $('#pending-body').empty();
        $('#inner-contents').children("div").hide();
        $('#pending-table').show();
        var contentRows = $('#pending-body');
        $.ajax({
            type: 'GET',
            url: '/BessieBlog/cont_static_pages/pending_for_user',
            success: function (spvmList) {
                $.each(spvmList, function (index, spvm) {


                    var title = spvm.title;
                    var creationDate = spvm.creationDate;
                    var publicationDate = spvm.publicationDate;
                    var expirationDate = spvm.expirationDate;
                    var userName = spvm.userName;
                    var pageId = spvm.id;
                    var row = '<tr>';
                    row += '<td>' + title + '</td>';
                    row += '<td>' + creationDate + '</td>';
                    row += '<td>' + publicationDate + '</td>';
                    row += '<td>' + expirationDate + '</td>';
                    row += '<td>' + userName + '</td>';
                    row += '<td><a onclick="showEditPageForm(' + pageId + ')">Edit</a> | <a onclick="deletePage(' + pageId + ')">Delete</a> | <a onclick="viewPage(' + pageId + ')">View</a>| <a onclick="viewPage(' + pageId + ')">View</a> </a></td>';
                    row += '</tr>'

                    contentRows.append(row);
                });
            },
            error: function () {
                console.log("you didn't get the list of pages from the controller");
            }

        });
    });

    //show contributor table for expired pages
    $('#cont-expired-pages-btn').on("click", function () {
        $('dialog').attr("open", false);

        emptySuccessMessages();
        $('#expired-body').empty();
        $('#inner-contents').children("div").hide();
        $('#expired-table').show();
        var contentRows = $('#expired-body');
        $.ajax({
            type: 'GET',
            url: '/BessieBlog/cont_static_pages/expired_for_user',
            success: function (spvmList) {
                $.each(spvmList, function (index, spvm) {


                    var title = spvm.title;
                    var creationDate = spvm.creationDate;
                    var publicationDate = spvm.publicationDate;
                    var expirationDate = spvm.expirationDate;
                    var userName = spvm.userName;
                    var pageId = spvm.id;
                    var row = '<tr>';
                    row += '<td>' + title + '</td>';
                    row += '<td>' + creationDate + '</td>';
                    row += '<td>' + publicationDate + '</td>';
                    row += '<td>' + expirationDate + '</td>';
                    row += '<td>' + userName + '</td>';
                    row += '<td><a onclick="showEditPageForm(' + pageId + ')">Edit</a> | <a onclick="deletePage(' + pageId + ')">Delete</a>| <a onclick="viewPage(' + pageId + ')">View</a> </a></td>';
                    row += '</tr>'

                    contentRows.append(row);
                });
            },
            error: function () {
                console.log("you didn't get the list of pages from the controller");
            }

        });
    });

});
// EDIT AND DELETE FUNCTIONS

//EDIT

function showEditPageForm(pageId) {

    console.log("You clicked on the edit for pageId: " + pageId);
    emptySuccessMessages();
    var body = tinyMCE.get('edit-body');
    $.ajax({
        type: 'GET',
        url: '/BessieBlog/user_static_pages/get/' + pageId,
        success: function (spvm, status) {
            console.log("In the get details for editing by id endpoint");
            $('#inner-contents').children("div").hide();
            $('#edit-static-page-div').show();
            $('#edit-user-id').val(spvm.userAuthorId);
            $('#edit-title').val(spvm.title);
            //$('#edit-body').val(spvm.body);
            body.setContent(spvm.body);
            var createDateWithT = formatDate(spvm.creationDate);
            if (spvm.expirationDate !== null) {
                var expDateWithT = formatDate(spvm.expirationDate);
            }
            if (spvm.publicationDate !== null) {
                var pubDateWithT = formatDate(spvm.publicationDate);
            }

            $('#edit-creation-date').val(createDateWithT);
            $('#edit-publication-date').val(pubDateWithT);
            $('#edit-expiration-date').val(expDateWithT);
            $('#edit-page-id').val(spvm.id);
            $('#edit-nav-order').val(spvm.navOrder);
            $('#edit-slug').val(spvm.slug);
            console.log("the checkbox should take in: " + spvm.isApproved);
            $('#edit-approval').prop('checked', spvm.isApproved === "true");

        },
        error: function () {
//        $('#errorMessages')
//        .append($('<li>')
//        .attr({class: '<list-group-item list-group-item-danger'})
//        .text('Error calling web service. Please try again later.'));
            console.log("Could not load page data from controller");
        }
    })

}
;
//DELETE

function deletePage(pageId) {
    var deleteDialog = $('#confirm-delete-dialog');
    deleteDialog[0].showModal();


    $('#cancel-delete').on("click", function () {
        deleteDialog[0].close();
    });


    $('#confirm-delete').on("click", function () {
        console.log("Mmade it into the function");
        $.ajax({
            type: 'DELETE',
            url: '/BessieBlog/user_static_pages/delete/' + pageId,
            success: function () {
                //delete successful message

                displaySuccessMessage("Deleted");
                console.log("deletion successful");
                deleteDialog[0].close();
                $('#inner-contents').children("div").hide();
                $('#delete-dialog').attr('open', true);
            }
        });
    });
    return false;
}

function viewPage(pageId) {
    $.ajax({
        type: 'GET',
        url: '/BessieBlog/user_static_pages/get/' + pageId,
        success: function (spvm, status) {

            console.log(spvm.body);
            $('#page-viewer-div').html($.parseHTML(spvm.body));
            $('#page-viewer')[0].showModal();
        }
    });
}

function formatDate(stringDate) {
    var dateAsString = stringDate;
    var dateParts = dateAsString.split(" ");
    var dateWithT = dateParts[0] + "T" + dateParts[1];
    return dateWithT;
}

function displaySuccessMessage(message) {
    $('.success-message').val(message);
}

function emptySuccessMessages() {
    $('.success-message').val("");
}

function isTheBodyFilled(body) {
    return body.trim().length !== 0;
}

// processes validation errors for the given input.  returns true if there
// are validation errors, false otherwise
function checkAndDisplayValidationErrors(input) {
    // clear displayed error message if there are any
    $('#errorMessages').empty();
    // check for HTML5 validation errors and process/display appropriately
    // a place to hold error messages
    var errorMessages = [];

    // loop through each input and check for validation errors
    input.each(function () {
        // Use the HTML5 validation API to find the validation errors
        if (!this.validity.valid)
        {
            var errorField = $('label[for=' + this.id + ']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }
    });

    // put any error messages in the errorMessages div
    if (errorMessages.length > 0) {
        $.each(errorMessages, function (index, message) {
            $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
        });
        // return true, indicating that there were errors
        return true;
    } else {
        // return false, indicating that there were no errors
        return false;
    }
}