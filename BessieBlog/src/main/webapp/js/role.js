/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    //assuming on click should go here, ready from the outset

    //var selectedRole;
    loadRoles();
    $(".roleLink").on("click", function () {
        var roleId = $(this).data("id");
        console.log(roleId);
        showEditDetails(roleId); //not sure on syntax here....
    });

    $("#cancel-edit-button").on("click", function () {
        $('#editRole').hide();

    });

    $("#edit-button").on("click", function () {
        var idToUpdate = $('#hidden-id').val();
        console.log("Below is the Id to update from the edit button: ");
        console.log(idToUpdate);

        // check for errors and display any that we have
        // pass the input associated with the add form to the validation function
        var haveValidationErrors = checkAndDisplayValidationErrors($('#add-form').find('input'));

        // if we have errors, bail out by returning false
        if (haveValidationErrors) {
            return false;
        }

        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/BessieBlog/roles/' + idToUpdate,
            data: JSON.stringify({
                roleId: idToUpdate,
                name: $('#edit-name').val(),
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function () {
                $('#editRole').hide();

                // clear errorMessages
                $('#errorMessages').empty();

                loadRoles();
            },
            error: function () {
                console.log("Another fuck up.....");
                $('#errorMessages')
                        .append($('<li>')
                                .attr({class: 'list-group-item list-group-item-danger'})
                                .text('Error calling web service.  Please try again later.'));

            }

        })

    });
    $('#add-button').click(function (event) {
        // check for errors and display any that we have
        // pass the input associated with the add form to the validation function
        var haveValidationErrors = checkAndDisplayValidationErrors($('#add-form').find('input'));

        // if we have errors, bail out by returning false
        if (haveValidationErrors) {
            return false;
        }

        // if we made it here, there are no errors so make the ajax call
        $.ajax({
            type: 'POST',            
            url: '/BessieBlog/roles/',
            data: JSON.stringify({
                name: $('#add-name').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function () {

                $('#add-name').val('');

                // clear errorMessages
                $('#errorMessages').empty();

                loadRoles();
            },
            error: function () {
                console.log("whoops!");
                $('#errorMessages')
                        .append($('<li>')
                                .attr({class: 'list-group-item list-group-item-danger'})
                                .text('Error calling web service.  Please try again later.'));
            }
        })
    });
});



function showEditDetails(roleId) {
//  $('#errorMessages').empty();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/BessieBlog/roles/' + roleId,
        success: function (data, status) {
            $('#edit-name').val(data.name); //.html
            $('#hidden-id').val(roleId);

        },
        error: function () {
//        $('#errorMessages')
//        .append($('<li>')
//        .attr({class: '<list-group-item list-group-item-danger'})
//        .text('Error calling web service. Please try again later.'));
            console.log("you fucked up");
        }
    })

//  $('#contactTableDiv').hide();
    $('#editRole').show();
}
;

function loadRoles() {
    clearRolesTable();
    var contentRows = $('#rolesTable');


    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/BessieBlog/roles/roleList',
        success: function (roleArray) {
            $.each(roleArray, function (index, role) {

                var name = role.name;
                var roleId = role.id;
                var row = '<tr>';
                row += '<td>' + name + '</td>';
                row += '<td><a onclick="showEditDetails(' + roleId + ')">Edit</a></td>';
                row += '<td><a onclick="deleteRole(' + roleId + ')">Delete</a></td>';
                row += '</tr>'

                contentRows.append(row);

                console.log("You did it!");
            });
        },
        error: function () {
            console.log("hello");


        }

    });
}
function clearRolesTable() {
    $('#rolesTable').empty();
}
function deleteRole(roleId) {

    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/BessieBlog/roles/' + roleId,
        success: function () {
            loadRoles();
        }
    });

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