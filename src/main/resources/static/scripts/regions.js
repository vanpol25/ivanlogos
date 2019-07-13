$(document).ready(function () {
    $('#modal1').modal();

    $.ajax({
        url: 'http://localhost:8080/region',
        type: 'get',
        success: function (response) {
            for (let region of response) {
                appendRegionToTable(region);
            }
            onDeleteBtn();
            onUpdateBtn();
        }
    });

    $('#createButton').click(function () {
        let request = {
            name: $('#createRegionInput').val()
        };

        let id = $('#region-id').val();

        console.log(id + ' - ' + $('#createRegionInput').val());
        if (id === '') {
            $.ajax({
                url: 'http://localhost:8080/region',
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
                url: 'http://localhost:8080/region?id=' + id,
                contentType: 'application/json',
                type: 'put',
                data: JSON.stringify(request),
                success: function (response) {
                    console.log(response);

                }
            });
            $('#region-id').val('');
            $(`#region-name-${id}`).text($('#createRegionInput').val());
            $('#createRegionInput').val('');
            $('#createButton').text('Create');
            $('#modal1').modal('close');
        }

    });

    function appendRegionToTable(region) {
        $('#regions').append(`
            <tr>
            <td>${region.id}</td>
            <td id="region-name-${region.id}">${region.name}</td>
            <td>
                <button value="${region.id}" class="delete-btn btn waves-effect waves-light">Delete</button>
                <button value="${region.id}" class="update-btn btn waves-effect waves-light">Update</button>
            </td>
            </tr>
        `)
    }

    function onDeleteBtn() {
        $('.delete-btn').click((e) => {
            let id = e.target.value;
            $.ajax({
                url: 'http://localhost:8080/region?id=' + id,
                type: 'delete',
                success: function () {
                    $(e.target.parentElement.parentElement).slideUp();
                    console.log('Region with id=' + id + ' is deleted')
                }
            });
        })
    }

    function onUpdateBtn() {
        $('.update-btn').click((e) => {
            let id = e.target.value;
            $('#createButton').text('Update');
            $('#modal1').modal('open');
            $('#region-id').val(id);
            $('#createRegionInput').val($(`#region-name-${id}`).text());
        })
    }

});