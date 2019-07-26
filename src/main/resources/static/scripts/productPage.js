$(document).ready(function () {

    let photos;
    let photosLength;
    let index = 0;
    const searchParams = new URLSearchParams(window.location.search);

    $.ajax({
        url: `http://localhost:8080/product/item?id=${searchParams.get('id')}`,
        type: 'get',
        success: function (response) {
            photos = response.photos;
            photosLength = photos.length;
            addPhotosToSlider();
            addInfo(response);
        }
    });

    function addPhotosToSlider() {
        let photo;
        if (photos.length === 0) {
            photo = 'empty.png';
        } else {
            photo = photos[0].name;
        }
        $('#slider').css('backgroundImage',
            `url('http://localhost:8080/img/${photo}')`);

    }

    function addInfo(responce) {
        $('#title').html(responce.name);
        $('#main-info').append(`
            <div class="item-inside-info">Price: $${responce.price}</div>
            <div class="item-inside-info">
                ${responce.user.name}<br>
                Telephone: ${responce.user.phoneNumber}<br>
                City: ${responce.city.name}, ${responce.city.region.name} region<br>
                Email: ${responce.user.email}
            </div>
            <div class="item-inside-info">
                Date published: ${responce.date}
            </div>
        `);
        $('#info').append(`
            <div class="item-inside-description">
                <h4>${responce.name}</h4>
            </div>
            <div class="item-inside-description">
                <a href="#">${responce.subCategory.category.name} </a>=>
                <a href="#"> ${responce.subCategory.name}</a>
            </div>
            <div>
                ${responce.description}
            </div>
        `)
    }

    $('#next').click(() => {
        ++index;
        if (index > photosLength - 1) {
            index = 0;
        }
        showPhoto();
    });

    $('#previous').click(() => {
        --index;
        if (index < 0) {
            index = photosLength - 1;
        }
        showPhoto();
    });

    function showPhoto() {
        $('#slider').css('backgroundImage',
            `url('http://localhost:8080/img/${photos[index].name}')`);
    }

});