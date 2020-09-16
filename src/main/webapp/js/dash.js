// Materialize stuff
M.AutoInit();

//sidenav
const sidenav = document.querySelector('.sidenav');
const sideNavInstance = M.Sidenav.init(sidenav, {});

const modal = document.querySelector('.modal');
const modalInstance = M.Sidenav.init(modal, {});

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
            toastr.error('Request not sent','Fatal:')
        }
    });
})()

$(document).ready(function () {
    $('form').submit(function (event) {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/openevents/event',
            data: $('form').serialize(),
        })
            .done(function (data) {
                $('input[type=text],input[type=password],input[type=email],textarea').val('');
                modalInstance.close();
                toastr.success(data, 'Success');
                //window.location.href
               // console.log(data);
            });
        event.preventDefault();
    });

});

(function rowRender() {
    fetch('http://localhost:8080/openevents/event')
        .then(response => response.json())
        .then(events => {
            console.log(events);
            events.forEach(event => {
                const id = event.id;
                const eventTitle = event.eventTitle;
                const eventOrganizer = event.organizerName;
                const eventDescription = event.eventDescription;
                const eventTags = event.eventTags;
                const eventLocation = event.eventLocation;
                const eventStart = event.eventStart;
                const eventEnd = event.eventEnd;
                const renderEvents = document.querySelector('tbody');
                const row = `
                             <tr class="each-row">
                                    <td>${id}</td>
                                    <td>${eventTitle}</td>
                                    <td>${eventOrganizer}</td>
                                    <td>${eventDescription}</td>
                                    <td>${eventTags}</td>
                                    <td>${eventLocation}</td>
                                    <td>${eventStart}</td>
                                    <td>${eventEnd}</td>
                                    <td>
                                        <div class="row">
                                            <a class="btn-floating btn-small waves-effect waves-light blue"><i class="material-icons left">edit</i>edit</a>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="row">
                                            <a data-id="${id}" class="btn-floating btn-small waves-effect waves-light red" onclick="deleteEvent(this)"><i class="material-icons left">delete</i>delete</a>
                                        </div>
                                    </td>
                             </tr>
                        `;
                renderEvents.insertAdjacentHTML('beforeend', row);
            })
        })
        .catch((error) => console.error(error));
})();

function deleteEvent(obj){
    const id = obj.getAttribute("data-id");

    fetch(`http://localhost:8080/openevents/fetchevent?id=${id}`,{
        method: 'DELETE'
    }).then(data=> toastr.success(`Item with id ${id} has been deleted`))
    .catch( error => toastr.error('An error has occurred','Fatal:'))

}