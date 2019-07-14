$(document).ready(function () {

    $(".left-pane").load('../admin/listAdmin.html');

    $.ajax({
        url: 'http://localhost:8080/region',
        type: 'get',
        success: function (response) {
            for (let region of response) {
                $('#selector').append(`<option value="${region.id}">${region.name}</option>`);
            }
        }
    });

    $.ajax({
        url: 'http://localhost:8080/city',
        type: 'get',
        success: function (response) {
            for (let city of response) {
                appendCityToTable(city);
                appendRegionName(city.regionId, city.id);
            }
            onDeleteBtn();
            onUpdateBtn();

        }
    });

    function appendRegionName(regionId, id) {
        let $nameRegion = $(`#selector [value='${regionId}']`).text();
        $(`#city-name-${id}`).after(`<td id="city-region-id-${id}">${$nameRegion}</td>`)
    }

    $('#createButton').click(function () {

        let request = {
            name: $('#createCityInput').val(),
            regionId: $('#selector :selected').val()
        };

        console.log($("#selector [value='2']").text());

        return;

        let id = $('#city-id').val();

        if (id === '') {
            $.ajax({
                url: 'http://localhost:8080/city',
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
                url: 'http://localhost:8080/city?id=' + id,
                contentType: 'application/json',
                type: 'put',
                data: JSON.stringify(request),
                success: function (response) {
                    console.log(response);
                }
            });
            location.reload();
        }

    });

    function appendCityToTable(city) {
        $('#cities').append(`
            <tr>
            <td>${city.id}</td>
            <td id="city-name-${city.id}">${city.name}</td>
            <td>
                <button value="${city.id}" class="delete-btn btn waves-effect waves-light">Delete</button>
                <button value="${city.id}" class="update-btn btn waves-effect waves-light">Update</button>
            </td>
            </tr>
        `)
    }

    function onDeleteBtn() {
        $('.delete-btn').click((e) => {
            let id = e.target.value;
            $.ajax({
                url: 'http://localhost:8080/city?id=' + id,
                type: 'delete',
                success: function () {
                    $(e.target.parentElement.parentElement).slideUp();
                    console.log('City with id=' + id + ' is deleted')
                }
            });
        })
    }

    function onUpdateBtn() {
        $('.update-btn').click((e) => {
            let id = e.target.value;
            $('#createButton').text('Update');
            $('#modal1').modal('open');
            $('#city-id').val(id);
            $('#createCityInput').val($(`#city-name-${id}`).text());
            $('#createCityInputRegionId').val($(`#city-region-id-${id}`).text());
        })
    }

    $('#modal1').modal();

});