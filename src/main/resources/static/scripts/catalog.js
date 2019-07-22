$(document).ready(function () {

    let $pageSize = $('#selectPage');
    let $page = $('#currentPage');
    let $totalPages;


    $('.sidenav').sidenav();

    function getProducts() {
        let request = {
            name: $('#findByName').val(),
            minPrice: $('#min-priceFilter').val(),
            maxPrice: $('#max-priceFilter').val(),
            subCategoryId: $('#subCategoryFilter').val(),
            categoryId: $('#categoryFilter').val(),
            cityId: $('#cityFilter').val(),
            regionId: $('#regionFilter').val()
        };
        $.ajax({
            url: `http://localhost:8080/product/findByFilter`,
            type: 'get',
            contentType: "application/json",
            data: JSON.stringify(request),
            success: (response) => {
                $totalPages = response.totalPages;
                $('.contentProduct').html('');
                for (let product of response.data) {
                    addItem(product)
                }
                onDeleteBtn();
                onUpdateBtn();
                onCreateBtn();
            },
            error: console.log
        });
    }

    getProducts();

    $.ajax({
        url: 'http://localhost:8080/subCategory',
        type: 'get',
        success: function (response) {
            for (let subCategory of response) {
                addSubcategoryToSelect(subCategory)
            }
        }
    });

    $.ajax({
        url: 'http://localhost:8080/city',
        type: 'get',
        success: function (response) {
            for (let city of response) {
                addCityToSelect(city)
            }
        }
    });

    $.ajax({
        url: 'http://localhost:8080/user',
        type: 'get',
        success: function (response) {
            for (let user of response) {
                addUserToSelect(user)
            }
        }
    });

    $('#create-updateBtn').click(function () {
        let id = $('#productId').val();
        let request = {
            name: $('#nameInput').val(),
            description: $('#descriptionInput').val(),
            price: $('#priceInput').val(),
            subCategoryId: $('#subCategorySelector :selected').val(),
            cityId: $('#citySelector :selected').val(),
            userId: $('#userSelector :selected').val()
        };
        if (id === '') {
            $.ajax({
                url: 'http://localhost:8080/product',
                contentType: 'application/json',
                type: 'post',
                data: JSON.stringify(request),
                success: function (response) {
                    console.log('Product created! Id= ' + response);
                    uploadPhotos(response);
                }
            });
        } else {
            $.ajax({
                url: 'http://localhost:8080/product?id=' + id,
                contentType: 'application/json',
                type: 'put',
                data: JSON.stringify(request),
                success: function (response) {
                    console.log('Product updated! Id= ' + response);
                    uploadPhotos(response);
                }
            });
        }
    });

    function uploadPhotos(id) {
        let fileArr = document.getElementById("photoInput").files;
        let counter = 0;
        for (let file of fileArr) {
            getBase64(file).then(data => {
                let request = {
                    data: data,
                    productId: id
                };
                $.ajax({
                    url: "http://localhost:8080/photo",
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(request),
                    success: function (data) {
                        console.log('Photo uploaded!' + data);
                        ++counter;
                        if (counter === fileArr.length) {
                            location.reload();
                        }
                    },
                    error: function (error) {
                        alert(error.message);
                    }
                });
            });
        }
    }

    function getBase64(file) {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => resolve(reader.result);
            reader.onerror = error => reject(error);
        });
    }

    function addItem(product) {
        $('.contentProduct').append(`
            <div class="item">
                <img class="img-item" src="http://localhost:8080/img/${product.mainImg}">
                <p>$${product.price}</p>
                <a class="linkToProduct" href="http://localhost:8080/item?id=${product.id}" target="_blank">${product.name}</a>
                <div>
                    <button value="${product.id}" class="update-btn btn-small waves-effect waves-light">Update</button>
                    <button value="${product.id}" class="delete-btn btn-small waves-effect waves-light">Delete</button>
                </div>
            </div>
        `);
    }

    function onDeleteBtn() {
        $('.delete-btn').click((e) => {
            let id = e.target.value;
            $.ajax({
                url: 'http://localhost:8080/product?id=' + id,
                type: 'delete',
                success: function () {
                    $(e.target.parentElement.parentElement).slideUp();
                    console.log('City with id=' + id + ' is deleted')
                }
            })
        })
    }

    function onUpdateBtn() {
        $('.update-btn').click((e) => {
            let id = e.target.value;
            $.ajax({
                url: `http://localhost:8080/product/item?id=${id}`,
                type: 'get',
                success: function (response) {
                    onUpdateAction(response);
                }
            });

        })
    }

    function onCreateBtn() {
        $('#createBtn').click(() => {
            $('#productId').val('');
            $('#photoPlace').html('');
            $('#nameInput').val('');
            $('#descriptionInput').val('');
            $('#priceInput').val('');
            $(`#subCategorySelector [value='0']`).prop("selected", true);
            $(`#citySelector [value='0']`).prop("selected", true);
            $(`#userSelector [value='0']`).prop("selected", true);
            $('#create-updateBtn').html('Create');
        });

    }

    function onUpdateAction(response) {
        $('#photoPlace').html('');
        $('#productId').val(response.id);
        $('#nameInput').val(response.name);
        $('#descriptionInput').val(response.description);
        $('#priceInput').val(response.price);
        $(`#subCategorySelector [value='${response.subCategory.id}']`).prop("selected", true);
        $(`#citySelector [value='${response.city.id}']`).prop("selected", true);
        $(`#userSelector [value='${response.user.id}']`).prop("selected", true);
        $('#create-updateBtn').html('Update');
        if (response.photos != null) {
            for (let photo of response.photos) {
                $('#photoPlace').append(`
                    <div class="photo-editor">
                         <img src="http://localhost:8080/img/${photo.name}">
                         <div>
                            <button value="${photo.id}" class="delete-photo-btn btn waves-effect waves-light red">X</button>
                            <button value="${photo.id}" class="update-main-photo-btn btn waves-effect waves-light red">M</button>
                        </div> 
                    </div>
                `)
            }
            onDeletePhotoBtn();
            onMainPhotoBtn();
        }
        $('.sidenav').sidenav('open');
    }

    function onDeletePhotoBtn() {
        $('.delete-photo-btn').click((e) => {
            let id = e.target.value;
            $.ajax({
                url: 'http://localhost:8080/photo?id=' + id,
                type: 'delete',
                success: function () {
                    $(e.target.parentElement.parentElement).slideUp();
                    console.log('Photo with id=' + id + ' is deleted')
                }
            })
        })
    }

    function onMainPhotoBtn() {
        $('.update-main-photo-btn').click((e) => {
            let id = e.target.value;
            $.ajax({
                url: 'http://localhost:8080/photo?id=' + id,
                type: 'put',
                success: function () {
                    console.log('Photo with id=' + id + ' being main')
                }
            })
        })
    }

    function addSubcategoryToSelect(subCategory) {
        $('#subCategorySelector').append(`
            <option value="${subCategory.id}">${subCategory.name}</option>
        `);
    }

    function addCityToSelect(city) {
        $('#citySelector').append(`
            <option value="${city.id}">${city.name}</option>
        `);
    }

    function addUserToSelect(user) {
        $('#userSelector').append(`
            <option value="${user.id}">${user.name}</option>
        `);
    }

    $('#next').click(() => {
        let $currentPage = $page.html();
        if ($currentPage == $totalPages) {
            return;
        } else {
            $page.html(+$currentPage + 1);
            getProducts();
        }
    });

    $('#prev').click(() => {
        let $currentPage = $page.html();
        if ($currentPage == 1) {
            return;
        } else {
            $page.html(+$currentPage - 1);
            getProducts();
        }
    });

    $pageSize.change(() => {
        $page.html('1');
        getProducts();
    });
});