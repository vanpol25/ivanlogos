$(document).ready(function () {
    let mainPane = $('.mainPane');
    $('#changeToRed').click(function () {
        mainPane.css('background', 'red');
    });
    $('#changeToBlue').click(function () {
        mainPane.css('background', 'blue');
    });
    $('#addText').click(function () {
        mainPane.append(`${new Date()} - ${mainPane.css('background-color')}<br/>`)
    });

    let images = ['images/a3.jpg', 'images/a6.jpg', 'images/a8.jpg'];
    let imgCont = $('.img');
    let index = 0;
    imgCont.css('background-image', `url('${images[index]}')`);

    $('#next').click(function () {
        index++;
        if (index === images.length) {
            index = 0;
        }
        imgCont.css('background-image', `url('${images[index]}')`);
    });

    $('#prev').click(function () {
        index--;
        if (index === -1) {
            index = images.length - 1;
        }
        imgCont.css('background-image', `url('${images[index]}')`);
    });
});