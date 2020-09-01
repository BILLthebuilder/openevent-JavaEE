$(document).ready(function () {
    $('form').submit(function (event) {
        const formData = {
            'event_address': $('input[name=event_address]').val(),
            'event_approval': $('input[name=event_approval]').val(),
        };
        console.log(formData);
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/openevents/create-event',
            data: formData,
        })
            .done(function (data) {
                $('input[name=event_address]').val('');
                $('input[name=event_approval]').val('');
                //window.location.href = '../';
                console.log(data);
            });
        event.preventDefault();
    });

});
