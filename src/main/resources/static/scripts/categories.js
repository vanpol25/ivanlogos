$(document).ready(function () {

    $(".left-pane").load('../admin/listAdmin.html');

    $('#modal1').modal();

    $.ajax({
        url: 'http://localhost:8080/category',
        type: 'get',
        success: function (response) {
            for (let category of response) {
                appendCategoryToTable(category);
                console.log("fwafawf")
            }
            onDeleteBtn();
            onUpdateBtn();
        }
    });

    $('#createButton').click(function () {
        let request = {
            name: $('#createCategoryInput').val()
        };

        let id = $('#category-id').val();

        console.log(id + ' - ' + $('#createCategoryInput').val());
        if (id === '') {
            $.ajax({
                url: 'http://localhost:8080/category',
                contentType: 'application/json',
                type: 'post',
                data: JSON.stringify(request),
                success: function (response) {
                    console.log(response)
                }
            });
            location.reload();
        } else {
            $.ajax({
                url: 'http://localhost:8080/category?id=' + id,
                contentType: 'application/json',
                type: 'put',
                data: JSON.stringify(request),
                success: function (response) {
                    console.log(response);

                }
            });
            $('#category-id').val('');
            $(`#category-name-${id}`).text($('#createCategoryInput').val());
            $('#createCategoryInput').val('');
            $('#createButton').text('Create');
            $('#modal1').modal('close');
        }

    });

    function appendCategoryToTable(category) {
        $('#categories').append(`
            <tr>
            <td>${category.id}</td>
            <td id="category-name-${category.id}">${category.name}</td>
            <td>
                <button value="${category.id}" class="delete-btn btn waves-effect waves-light">Delete</button>
                <button value="${category.id}" class="update-btn btn waves-effect waves-light">Update</button>
            </td>
            </tr>
        `)
    }

    function onDeleteBtn() {
        $('.delete-btn').click((e) => {
            let id = e.target.value;
            $.ajax({
                url: 'http://localhost:8080/category?id=' + id,
                type: 'delete',
                success: function () {
                    $(e.target.parentElement.parentElement).slideUp();
                    console.log('Category with id=' + id + ' is deleted')
                }
            });
        })
    }

    function onUpdateBtn() {
        $('.update-btn').click((e) => {
            let id = e.target.value;
            $('#createButton').text('Update');
            $('#modal1').modal('open');
            $('#category-id').val(id);
            $('#createCategoryInput').val($(`#category-name-${id}`).text());
        })
    }

});