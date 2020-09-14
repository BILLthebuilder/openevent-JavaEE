// Materialize stuff
M.AutoInit();

//sidenav
const sidenav = document.querySelector('.sidenav');
const sideNavInstance = M.Sidenav.init(sidenav, {});

$(document).ready(function () {
    $('form').submit(function (event) {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/openevents/event',
            data: $('form').serialize(),
        })
            .done(function (data) {
                $('input[type=text],input[type=password],input[type=email],textarea').val('');
                //window.location.href = './';
                console.log(data);
            });
        event.preventDefault();
    });

});

    // Check if a user session exists
(function checkForUserSession(){
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/openevents/login',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                if (!data.isLoggedIn)
                    window.location.replace('http://localhost:8080/openevents/login.html');
            },
            error: function (data) {
                toastr.error('Request not sent')
            }
        });
    })()
