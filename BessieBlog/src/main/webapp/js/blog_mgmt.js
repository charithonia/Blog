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
        $('#add-static-page-div').show();
    });

   

    $('#submit-edit-btn').on("click", function () {
        var idToUpdate = $('#edit-page-id').val();
        console.log("Below is the Id to update from the edit button: ");
        console.log(idToUpdate);

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/BessieBlog/blogs/updateBlog',
            data: JSON.stringify({
                blogId:  $('#edit-page-id').val,
                title: $('#edit-title').val(),
                creationDate: $('#edit-creation-date').val(),
                experiationDate: $('#edit-expiration-date').val(),
                publicationDate: $('#edit-publication-date').val(),
                categoryId: $('#edit-category-id').val(),
                body: $('#edit-body').val(),
                userId: $('#edit-user-id').val(),
                isApproved: $('#edit-approval').is(':checked')
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function () {
                console.log("Update successful");

            },
            error: function () {
                console.log("the form couldn't get through to the controller");

            }

        })
    });

    //show admin table for active pages

    $('#admin-active-pages-btn').on("click", function () {
        console.log("You are trying to load all the active pages");
        $('#active-body').empty();
        $('#inner-contents').children("div").hide();
        $('#active-table').show();


        var contentRows = $('#active-body');
        console.log("You are about to load all the active pages");
        $.ajax({

            type: 'GET',
            url: 'http://localhost:8080/BessieBlog/admin_blog_mgmt/admin_blog_view',
            success: function (vm) {
                console.log("You are inside the success block for loading all the active pages");
                $.each(vm.publishedBlogs, function (index, blog) {


                    var title = blog.title;
                    var creationDate = blog.creationDate;                   
                    var creationDay  = creationDate.dayOfMonth;
                    var creationMonth = creationDate.month;
                    var creationYear = creationDate.year;
                    var creationString = creationDay + " " + creationMonth + " " + creationYear;
                    
                    var publicationDate = blog.publicationDate;
                         
                    var publicationDay  = publicationDate.dayOfMonth;
                    var publicationMonth = publicationDate.month;
                    var publicationYear = publicationDate.year;
                    
                    var publicationString = publicationDay + " " + publicationMonth + " " + publicationYear;
                    
                    var expirationDate = blog.expirationDate;
                    var expirationDay  = expirationDate.dayOfMonth;
                    var expirationMonth = expirationDate.month;
                    var expirationYear = expirationDate.year;
                    
                    var expirationString = expirationDay + " " + expirationMonth + " " + expirationYear;
                    
                    var userName = blog.user.name;
                    var blogId = blog.id;
                   

                    var row = '<tr>';
                    row += '<td>' + title + '</td>';
                    row += '<td>' + creationString + '</td>';
                    row += '<td>' + publicationString + '</td>';
                    row += '<td>' + expirationString + '</td>';
                    row += '<td>' + userName + '</td>';
                    row += '<td><a onclick="showEditPageForm(' + blogId + ')">Edit</a> | <a onclick="deletePage(' + blogId + ')">Delete</a></td>';
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



        $('#pending-body').empty();
        $('#inner-contents').children("div").hide();
        $('#pending-table').show();

        var contentRows = $('#pending-body');

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/BessieBlog/admin_blog_mgmt/admin_blog_view',
            success: function (vm) {
                $.each(vm.pendingBlogs, function (index, blog) {


                    var title = blog.title;
                    var creationDate = blog.creationDate;                   
                    var creationDay  = creationDate.dayOfMonth;
                    var creationMonth = creationDate.month;
                    var creationYear = creationDate.year;
                    var creationString = creationDay + " " + creationMonth + " " + creationYear;
                    console.log(creationString);

                    var userName = blog.user.name;
                    var blogId = blog.id;
                   

                    var row = '<tr>';
                    row += '<td>' + title + '</td>';
                    row += '<td>' + creationString + '</td>';
                    row += '<td>' + userName + '</td>';
                    row += '<td><a onclick="showEditPageForm(' + blogId + ')">Edit</a> | <a onclick="deletePage(' + blogId + ')">Delete</a> | \n\
                                <a onclick="approveBlog(' + blogId + ')">Approve</a></td>';
                            
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

        $('#expired-body').empty();
        $('#inner-contents').children("div").hide();
        $('#expired-table').show();


        var contentRows = $('#expired-body');

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/BessieBlog/admin_blog_mgmt/admin_blog_view',
             success: function (vm) {
                $.each(vm.expiredBlogs, function (index, blog) {

   var title = blog.title;
                    var creationDate = blog.creationDate;                   
                    var creationDay  = creationDate.dayOfMonth;
                    var creationMonth = creationDate.month;
                    var creationYear = creationDate.year;
                    var creationString = creationDay + " " + creationMonth + " " + creationYear;
                    console.log(creationString);
                    var publicationDate = blog.publicationDate;
                         
                    var publicationDay  = publicationDate.dayOfMonth;
                    var publicationMonth = publicationDate.month;
                    var publicationYear = publicationDate.year;
                    
                    var publicationString = publicationDay + " " + publicationMonth + " " + publicationYear;
                     console.log(publicationString);
                    var expirationDate = blog.expirationDate;
                    var expirationDay  = expirationDate.dayOfMonth;
                    var expirationMonth = expirationDate.month;
                    var expirationYear = expirationDate.year;
                    
                    var expirationString = expirationDay + " " + expirationMonth + " " + expirationYear;
                     console.log(expirationString);
                    var userName = blog.user.name;
                    var blogId = blog.id;
                   

                    var row = '<tr>';
                    row += '<td>' + title + '</td>';
                    row += '<td>' + creationString + '</td>';
                    row += '<td>' + publicationString + '</td>';
                    row += '<td>' + expirationString + '</td>';
                    row += '<td>' + userName + '</td>';
                    row += '<td><a onclick="showEditPageForm(' + blogId + ')">Edit</a> | <a onclick="deletePage(' + blogId + ')">Delete</a></td>';
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


        $('#active-body').empty();
        $('#inner-contents').children("div").hide();
        $('#active-table').show();

        var contentRows = $('#active-body');

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/BessieBlog/cont_static_pages/active_for_user',
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
                    row += '<td><a onclick="showEditPageForm(' + pageId + ')">Edit</a> | <a onclick="deletePage(' + pageId + ')">Delete</a></td>';
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
        $('#pending-body').empty();
        $('#inner-contents').children("div").hide();
        $('#pending-table').show();

        var contentRows = $('#pending-body');

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/BessieBlog/cont_static_pages/pending_for_user',
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
                    row += '<td><a onclick="showEditPageForm(' + pageId + ')">Edit</a> | <a onclick="deletePage(' + pageId + ')">Delete</a></td>';
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
        $('#expired-body').empty();
        $('#inner-contents').children("div").hide();
        $('#expired-table').show();

        var contentRows = $('#expired-body');

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/BessieBlog/cont_static_pages/expired_for_user',
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
                    row += '<td><a onclick="showEditPageForm(' + pageId + ')">Edit</a> | <a onclick="deletePage(' + pageId + ')">Delete</a></td>';
                    row += '</tr>'

                    contentRows.append(row);

                });
            },
            error: function () {
                console.log("you didn't get the list of pages from the controller");


            }

        });
    });


//    $(".editPage").on("click", function () {
//        var pageId = $(this).data("id");
//        console.log("You clicked edit for this page:" + pageId);
//        showEditForm(pageId);
//    });
    //not sure i need any onclick for the reset button

});

// EDIT AND DELETE FUNCTIONS

//EDIT

function showEditPageForm(pageId) {
    console.log("You clicked on the edit for pageId: " + pageId);
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/BessieBlog/blogs/displayEditBlog/' + pageId,
        success: function (bfcm, status) {
            console.log("In the get details for editing by id endpoint");
            $('#inner-contents').children("div").hide();
            $('#edit-blog-post-div').show();

            $('#edit-title').val(bfcm.title);
            //$('#edit-body').val(bfcm.body);
            tinyMCE.activeEditor.setContent(bfcm.body);
            $('#edit-creation-date').val(bfcm.creationDate);            
                    
                 
            if (bfcm.expiration !== null) {
                   var expirationDate = bfcm.experiationDate;
                    var expirationDay  = expirationDate.dayOfMonth;
                    var expirationMonth = expirationDate.month;
                    var expirationYear = expirationDate.year;
                    
            var expirationString = expirationDay + " " + expirationMonth + " " + expirationYear;
            }
            if (bfcm.publicationDate !== null) {
                 var publicationDate = bfcm.publicationDate;
                         
                    var publicationDay  = publicationDate.dayOfMonth;
                    var publicationMonth = publicationDate.month;
                    var publicationYear = publicationDate.year;
                    
                    var publicationString = publicationDay + " " + publicationMonth + " " + publicationYear;
            }

            $('#edit-publication-date').val(publicationString);
            $('#edit-expiration-date').val(expirationString);
            $('#edit-page-id').val(bfcm.blogId);
            $('#edit-category-id').val(bfcm.categoryId);
            console.log("the checkbox should take in: " + bfcm.isApproved);
            $('#edit-approval').prop('checked', bfcm.isApproved === "true"); //not sure about this one....

        },
//            private int blogId;    
//    private String title;
//    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
//    private LocalDateTime creationDate;
//    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
//    private LocalDateTime experiationDate;
//    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
//    private LocalDateTime publicationDate;
//    private int userId;
//    private int categoryId;
//    private String body;
//    private boolean isApproved;
//    private List<Category> categoryList;

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

    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/BessieBlog/blogs/deleteBlog/' + pageId,
        success: function () {
            //delete successful message
            console.log("deletion successful");
            $('#inner-contents').children("div").hide();
        }
    });
}
//APPROVE

function approveBlog(pageId) {

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/BessieBlog/blogs/approveBlog/' + pageId,
        success: function () {
            //delete successful message
            console.log("approve Success");
            $('#inner-contents').children("div").hide();
        }
    });
}

function formatDate(stringDate) {
    var dateAsString = stringDate;

    var dateParts = dateAsString.split(" ");
    var dateWithT = dateParts[0] + "T" + dateParts[1];
    return dateWithT;

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