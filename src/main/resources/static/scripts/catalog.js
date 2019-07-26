$(document).ready(function () {

    let $pageSize = $('#selectPage');
    let $currentPage = $('#currentPage');
    let $totalElements = $('#totalElements');
    let $totalPages;


    $('.sidenav').sidenav();


    function getProducts() {

        let request = {
            name: $('#findByName').val(),
            minPrice: $('#min-priceFilter').val(),
            maxPrice: $('#max-priceFilter').val(),
            subCategoryId: $('#subCategoryFilter :selected').val(),
            categoryId: $('#categoryFilter :selected').val(),
            cityId: $('#cityFilter :selected').val(),
            regionId: $('#regionFilter :selected').val(),
            paginationRequest: {
                page: +$currentPage.html() - 1,
                size: $pageSize.val()
            }
        };

        $.ajax({
            url: `http://localhost:8080/product/findByFilter`,
            type: 'post',
            contentType: "application/json",
            data: JSON.stringify(request),
            success: (response) => {
                $totalPages = response.totalPages;
                $totalElements.html(response.totalElements);
                $('.contentProduct').html('');
                for (let product of response.data) {
                    addItem(product)
                }
            },
            error: console.log
        });
    }

    getProducts();
    addRegionToSelectFilter();
    addCategoryToSelectFilter();

    function addItem(product) {
        $('.contentProduct').append(`
            <div class="item">
                <img class="img-item" src="http://localhost:8080/img/${product.mainImg}">
                <p>$${product.price}</p>
                <a class="linkToProduct" href="http://localhost:8080/item?id=${product.id}" target="_blank">${product.name}</a>
            </div>
        `);
    }

    $('#clearOfFilterConfiguration').click(() => {
        $('#min-priceFilter').val('');
        $('#max-priceFilter').val('');
        $('#subCategoryFilter [value=""]').prop("selected", true);
        $('#categoryFilter [value=""]').prop("selected", true);
        $('#cityFilter [value=""]').prop("selected", true);
        $('#regionFilter [value=""]').prop("selected", true);
    });

    $('#next').click(() => {
        let $page = $currentPage.html();
        if ($page != $totalPages) {
            $currentPage.html(++$page);
            getProducts();
        } else {
            return;
        }
    });

    $('#prev').click(() => {
        let $page = $currentPage.html();
        if ($page != 1) {
            $currentPage.html(--$page);
            getProducts();
        } else {
            return;
        }
    });

    $pageSize.change(() => {
        getProducts();
    });

    $('#find').click(() => {
        getProducts();
    });

    function addRegionToSelectFilter() {
        $.ajax({
            url: 'http://localhost:8080/region',
            type: 'get',
            success: function (response) {
                for (let region of response) {
                    $('#regionFilter').append(`
                        <option value="${region.id}">${region.name}</option>
                    `);
                    $('#userRegionSelector').append(`
                        <option value="${region.id}">${region.name}</option>
                    `);
                    $('#productRegionSelector').append(`
                        <option value="${region.id}">${region.name}</option>
                    `);
                }
            }
        });
        $("#regionFilter").change(() => {
            let $cityFilter = $('#cityFilter');
            $cityFilter.empty();
            $cityFilter.append('<option value="" selected disabled>Choose city</option>');
            addCityToSelectFilter($('#regionFilter :selected').val());
        });
        $("#userRegionSelector").change(() => {
            let $citySelector = $('#userCitySelector');
            $citySelector.empty();
            $citySelector.append('<option value="" selected disabled>Choose city</option>');
            addUserCityToSelector($('#userRegionSelector :selected').val());
        });
        $("#productRegionSelector").change(() => {
            let $citySelector = $('#productCitySelector');
            $citySelector.empty();
            $citySelector.append('<option value="" selected disabled>Choose city</option>');
            addProductCityToSelector($('#productRegionSelector :selected').val());
        })
    }

    function addCityToSelectFilter(id) {
        $.ajax({
            url: 'http://localhost:8080/city/findByRegionId?id=' + id,
            type: 'get',
            success: function (response) {
                for (let city of response) {
                    $('#cityFilter').append(`
                        <option value="${city.id}">${city.name}</option>
                    `);
                }
            }
        });
    }

    function addUserCityToSelector(id) {
        $.ajax({
            url: 'http://localhost:8080/city/findByRegionId?id=' + id,
            type: 'get',
            success: function (response) {
                for (let city of response) {
                    $('#userCitySelector').append(`
                        <option value="${city.id}">${city.name}</option>
                    `);
                }
            }
        });
    }

    function addProductCityToSelector(id) {
        $.ajax({
            url: 'http://localhost:8080/city/findByRegionId?id=' + id,
            type: 'get',
            success: function (response) {
                for (let city of response) {
                    $('#productCitySelector').append(`
                        <option value="${city.id}">${city.name}</option>
                    `);
                }
            }
        });
    }

    function addCategoryToSelectFilter() {
        $.ajax({
            url: 'http://localhost:8080/category',
            type: 'get',
            success: function (response) {
                for (let category of response) {
                    $('#categoryFilter').append(`
                        <option value="${category.id}">${category.name}</option>
                    `);
                    $('#productCategorySelector').append(`
                        <option value="${category.id}">${category.name}</option>
                    `);
                }
            }
        });
        $("#categoryFilter").change(() => {
            let $subCategoryFilter = $('#subCategoryFilter');
            $subCategoryFilter.empty();
            $subCategoryFilter.append('<option value="" selected disabled>Choose subCategory</option>');
            addSubCategoryToSelectFilter($('#categoryFilter :selected').val());
        });
        $("#productCategorySelector").change(() => {
            let $subCategorySelector = $('#productSubCategorySelector');
            $subCategorySelector.empty();
            $subCategorySelector.append('<option value="" selected disabled>Choose subCategory</option>');
            addSubCategoryToSelector($('#productCategorySelector :selected').val());
        })
    }

    function addSubCategoryToSelectFilter(id) {
        $.ajax({
            url: 'http://localhost:8080/subCategory/findByCategoryId?id=' + id,
            type: 'get',
            success: function (response) {
                for (let subCategory of response) {
                    $('#subCategoryFilter').append(`
                        <option value="${subCategory.id}">${subCategory.name}</option>
                    `);
                }
            }
        });
    }

    function addSubCategoryToSelector(id) {
        $.ajax({
            url: 'http://localhost:8080/subCategory/findByCategoryId?id=' + id,
            type: 'get',
            success: function (response) {
                for (let subCategory of response) {
                    $('#productSubCategorySelector').append(`
                        <option value="${subCategory.id}">${subCategory.name}</option>
                    `);
                }
            }
        });
    }

    $('#create-updateBtn').click(() => {
        let request = {
            name: $('#userName').val(),
            password: $('#userPassword').val(),
            phone_number: $('#userPhoneNumber').val(),
            cityId: $('#userCitySelector :selected').val()
        };

        $.ajax({
            url: 'http://localhost:8080/user',
            type: 'post',
            contentType: "application/json",
            data: JSON.stringify(request),
            success: (response) => {
                productCreate(response)
            }
        })
    });

    function productCreate(id) {
        let request = {
            name: $('#nameInput').val(),
            description: $('#descriptionInput').val(),
            price: $('#priceInput').val(),
            subCategoryId: $('#productSubCategorySelector :selected').val(),
            cityId: $('#productCitySelector :selected').val(),
            userId: id
        };

        $.ajax({
            url: 'http://localhost:8080/product',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify(request),
            success: (response) => {
                uploadPhotos(response)
            }
        })
    }

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

});