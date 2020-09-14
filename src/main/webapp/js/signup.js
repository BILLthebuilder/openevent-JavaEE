// Materialize stuff
M.AutoInit();

//sidenav
const sidenav = document.querySelector('.sidenav');
const sideNavInstance = M.Sidenav.init(sidenav, {});

$("#form").validate({
    rules: {
        first_name: {
            required: true,
            minlength: 2
        },
        last_name: {
            required: true,
            minlength: 2
        },
        user_email: {
            required: true,
            email:true
        },
        repeat_email: {
            required: true,
            email:true,
            equalTo: "#user_email"
        },
        password: {
            required: true,
            minlength: 5
        },
        repeat_password: {
            required: true,
            minlength: 5,
            equalTo: "#password"
        }
    },
    //Custom validation messages
    messages: {
        repeat_email:{
            equalTo: "email addresses don't match. try again"
        },
        repeat_password:{
            equalTo: "passwords don't match. try again"
        }
    },
    errorElement : 'div',
    errorPlacement: function(error, element) {
        var placement = $(element).data('error');
        if (placement) {
            $(placement).append(error)
        } else {
            error.insertAfter(element);
        }
    }
});


$(document).ready(function () {
    $('form').submit(function (event) {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/openevents/register',
            data: $('form').serialize(),
        })
            .done(function (data) {
                $('input[type=text],input[type=password],input[type=email],textarea').val('');
                window.location.href = './login.html';
                toastr.success(data,'Success');
                //console.log(data);
            });
        event.preventDefault();
    });

});
