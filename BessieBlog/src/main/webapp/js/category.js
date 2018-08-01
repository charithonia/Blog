/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    loadCategories();

    $('#add-category-button').click(function (event) {
	var haveValidationErrors = checkAndDisplayValidationErrors($('#add-category-form').find('input'));

	if (haveValidationErrors) {
	    return false;
	}

	$.ajax({
	    type: 'POST',
	    url: 'http://localhost:8080/BessieBlog/categories/',
	    data: JSON.stringify({
		name: $('#add-category-name').val()
	    }),
	    headers: {
		'Accept': 'application/json',
		'Content-Type': 'application/json'
	    },
	    'dataType': 'json',
	    success: function () {

		$('#add-category-name').val('');

		// clear errorMessages
		$('#error-messages').empty();

		loadCategories();
	    },
	    error: function () {
		console.log("something happened");
		$('#error-messages')
			.append($('<li>')
				.attr({class: 'list-group-item list-group-item-danger'})
				.text('Error calling web service.  Please try again later.'));
	    }
	});
    });
});

function loadCategories() {
    clearCategories();

    var categoryTable = $('#category-table tbody');

    $.ajax({
	type: 'GET',
	url: 'http://localhost:8080/BessieBlog/categories/categoryList',
	success: function (categoryList) {
	    $.each(categoryList, function (index, category) {
		var categoryId = category.id;
		var name = category.name;

		var row = "";
		row += "<tr>";
		row += "<td>" + name + "</td>";
		row += "<td><a onclick=editCategory("
			+ categoryId
			+ ")>Edit</a></td>";
		row += "<td><a onclick=deleteCategory("
			+ categoryId
			+ ")>Delete</a></td>";
		row += "</tr>";

		categoryTable.append(row);
	    });
	},
	error: function () {
	    console.log("loadCategories(): something happened");
	}
    });
}

function clearCategories() {
    $('#category-table tbody').empty();
}

function deleteCategory(categoryId) {
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/BessieBlog/categories/' + categoryId,
        success: function () {
            loadCategories();
        }
    });
}

function checkAndDisplayValidationErrors(input) {
    $('#errorMessages').empty();

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

function displayCategoryEditForm() {
    $.ajax ({
	type: 'PUT',
	url: 'http://localhost:8080/BessieBlog/'
    });
}

//  $.ajax ({
//    type: 'GET',
//    url: 'http://localhost:8080/items',
//    success: function(data, status) {
//      $.each(data, function(index, item) {
//        var id = item.id;
//        var name = item.name;
//        var price = item.price;
//        var quantity = item.quantity;
//
//        var item = '<div class="vendingmachine-item" data-id=' + id + '>'
//        item += '<p>' + id + '</p>';
//        item += '<p>' + name + '</p>';
//        item += '<p>$' + price + '</p>';
//        item += '<p>' + quantity + '</p>';
//        item += '</div>';
//
//        items.append(item);
//      });
//    },
//    error: function() {
//      $('#errorMessages')
//        .append($('<li>')
//        .attr({class: 'list-group-item list-group-item-danger'})
//        .text('Error calling web service. Please try again later.'));
//    }
//  });