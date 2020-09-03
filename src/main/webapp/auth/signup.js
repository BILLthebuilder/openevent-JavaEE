// Materialize stuff
M.AutoInit();

//sidenav
const sidenav = document.querySelector('.sidenav');
const sideNavInstance = M.Sidenav.init(sidenav, {});

$(document).ready(function () {
    $('form').submit(function (event) {
        const formData = {
            'first_name': $('input[name=first_name]').val(),
            'last_name': $('input[name=last_name]').val(),
            'repeat_email': $('input[name=repeat_email]').val(),
            'repeat_password': $('input[name=repeat_password]').val()
        };
        console.log(formData);
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8081/openevents/register',
            data: formData,
        })
            .done(function (data) {
                $('input[name=first_name]').val('');
                $('input[name=last_name]').val('');
                $('input[name=repeat_email]').val('');
                $('input[name=repeat_password]').val('');
                //window.location.href = './login.html';
                console.log(data);
            });
        event.preventDefault();
    });

});
