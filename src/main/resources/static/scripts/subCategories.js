$(document).ready(function () {

    $(".left-pane").load('../admin/listAdmin.html');

    $.ajax({
        url: 'http://localhost:8080/category',
        type: 'get',
        success: function (response) {
            for (let category of response) {
                $('#selector').append(`<option value="${category.id}">${category.name}</option>`);
            }
        }
    });

    $.ajax({
        url: 'http://localhost:8080/subCategory',
        type: 'get',
        success: function (response) {
            for (let subCategory of response) {
                appendCityToTable(subCategory);
                appendCategoryName(subCategory.categoryId, subCategory.id);
            }
            onDeleteBtn();
            onUpdateBtn();

        }
    });

    function appendCategoryName(categoryId, id) {
        let $nameCategory = $(`#selector [value='${categoryId}']`).text();
        $(`#subCategory-name-${id}`).after(`<td id="subCategory-category-id-${id}">${$nameCategory}</td>`)
    }

    $('#createButton').click(function () {

        console.log($('#createSubCategoryInput').val() + ' ' +
        $('#selector :selected').val());

        let request = {
            name: $('#createSubCategoryInput').val(),
            categoryId: $('#selector :selected').val()
        };

        let id = $('#subCategory-id').val();

        if (id === '') {
            $.ajax({
                url: 'http://localhost:8080/subCategory',
                contentType: 'application/json',
                type: 'post',
                data: JSON.stringify(request),
                success: function (response) {
                    console.log('SubCategory created')
                }
            });
            location.reload();
        } else {
            $.ajax({
                url: 'http://localhost:8080/subCategory?id=' + id,
                contentType: 'application/json',
                type: 'put',
                data: JSON.stringify(request),
                success: function (response) {
                    console.log('SubCategory with id=' + id + ' is updated')
                }
            });
            location.reload();
        }

    });

    function appendCityToTable(subCategory) {
        $('#subCategories').append(`
            <tr>
            <td>${subCategory.id}</td>
            <td id="subCategory-name-${subCategory.id}">${subCategory.name}</td>
            <td>
                <button value="${subCategory.id}" class="delete-btn btn waves-effect waves-light">Delete</button>
                <button value="${subCategory.id}" class="update-btn btn waves-effect waves-light">Update</button>
            </td>
            </tr>
        `)
    }

    function onDeleteBtn() {
        $('.delete-btn').click((e) => {
            let id = e.target.value;
            $.ajax({
                url: 'http://localhost:8080/subCategory?id=' + id,
                type: 'delete',
                success: function () {
                    $(e.target.parentElement.parentElement).slideUp();
                    console.log('SubCategory with id=' + id + ' is deleted')
                }
            });
        })
    }

    function onUpdateBtn() {
        $('.update-btn').click((e) => {
            let id = e.target.value;
            $('#createButton').text('Update');
            $('#modal1').modal('open');
            $('#subCategory-id').val(id);
            $('#createSubCategoryInput').val($(`#subCategory-name-${id}`).text());
            $('#createSubCategoryInputCategoryId').val($(`#subCategory-category-id-${id}`).text());
        })
    }

    $('#modal1').modal();

});