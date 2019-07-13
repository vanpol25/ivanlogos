$(document).ready(function () {
    $('#modal1').modal();

    $.ajax({
        url: 'http://localhost:8080/category',
        type: 'get',
        success: function (response) {
            for (let category of response) {
                appendCategoryToTable(category);
            }
            onDeleteBtn();
        }
    });

    $('#createButton').click(function () {
        let createCategoryInput = $('#createCategoryInput');
        let request = {
            name: createCategoryInput.val()
        };
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
    });

    function appendCategoryToTable(category) {
        $('#categories').append(`
            <tr>
            <td class="category-id-col">${category.id}</td>
            <td class="category-name-col">${category.name}</td>
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
            let $btn = $(e.target);

            $('#category-name').val()


        })
    }

});