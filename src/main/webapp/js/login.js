// Materialize stuff
M.AutoInit();

//sidenav
const sidenav = document.querySelector('.sidenav');
const sideNavInstance = M.Sidenav.init(sidenav, {});

$("#form").validate({
    rules: {
        user_email: {
            required: true,
            email:true
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
    //For custom messages
    messages: {
        // first_name:{
        //     required: "Enter a username",
        //     minlength: "Enter at least 5 characters"
        // },
        // repeat_email:{
        //     equalTo: "email addresses don't match. try again"
        // },
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
        const formData = {
            'user_email': $('input[name=user_email]').val(),
            'repeat_password': $('input[name=repeat_password]').val()
        };
        //console.log(formData);
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/openevents/login',
            data: formData,
        })
            .done(function (data) {
                 if(JSON.parse(data).authenticated){
                    $('input[name=user_email]').val('');
                    $('input[name=password]').val('');
                    $('input[name=repeat_password]').val('');
                     toastr.success(JSON.parse(data).Message,'Success');
                    window.location.href = './create.html';
                    //console.log(data);
                 }else {
                    toastr.error(JSON.parse(data).Message,'Error');
                }

            });
        event.preventDefault();
    });

});
