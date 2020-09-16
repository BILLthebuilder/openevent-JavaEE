// Materialize stuff
M.AutoInit();

//sidenav
const sidenav = document.querySelector('.sidenav');
const sideNavInstance = M.Sidenav.init(sidenav, {});

const toastrOptions = {
    "closeButton": true,
    "progressBar": true,
}

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
        event.preventDefault();
        const formData = {
            'user_email': $('input[name=user_email]').val(),
            'repeat_password': $('input[name=repeat_password]').val()
        };
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/openevents/login',
            data: formData,
            dataType:'json',
            success:function (data) {
                if(data.authenticated){
                    $('input[name=user_email]').val('');
                    $('input[name=password]').val('');
                    $('input[name=repeat_password]').val('');
                    console.log(data);
                    toastr.success(data.Message,'Success:',toastrOptions);
                    window.location.replace('dashboard.html');
                }else {
                    toastr.error(data.Message, 'Error:', toastrOptions);
                }

            },
            error: function(data){
                toastr.error('Request failed','Fatal:')
            }
        })
    });

});
