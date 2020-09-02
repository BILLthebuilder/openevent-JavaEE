// Materialize stuff
M.AutoInit();

//sidenav
const sidenav = document.querySelector('.sidenav');
const sideNavInstance = M.Sidenav.init(sidenav, {});

$(document).ready(function () {
    $('form').submit(function (event) {
        const formData = {
            'event_title': $('input[name=event_title]').val(),
            'event_organizer': $('input[name=event_organizer]').val(),
            'event_tags': $('input[name=event_tags]').val(),
            'event_type': $('input[name=event_type]').val(),
            'event_category': $('input[name=event_category]').val(),
            'event_location': $('input[name=event_location]').val(),
            'event_start_date': $('input[name=event_start_date]').val(),
            'event_end_date': $('input[name=event_end_date]').val(),
        };
        console.log(formData);
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/openevents/create-event',
            data: formData,
        })
            .done(function (data) {
                $('input[name=event_title]').val('');
                $('input[name=event_organizer]').val('');
                $('input[name=event_tags]').val('');
                $('input[name=event_type]').val('');
                $('input[name=event_category]').val('');
                $('input[name=event_location]').val('');
                $('input[name=event_start_date]').val('');
                $('input[name=event_end_date]').val('');
                //window.location.href = '../';
                console.log(data);
            });
        event.preventDefault();
    });

});
