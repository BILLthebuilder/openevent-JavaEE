// Materialize stuff
M.AutoInit();

//sidenav
const sidenav = document.querySelector('.sidenav');
const sideNavInstance = M.Sidenav.init(sidenav, {});

//carousel
const carousel = document.querySelector('.carousel');
const carouselInstance = M.Carousel.init(carousel, {
    fullWidth: true,
    indicators: true,
    duration: 200
});

var tooltip = document.querySelector('.tooltipped');
var tooltipInstance = M.Tooltip.init(tooltip, {

});

// Custom Javascript
const eventTags = [
    'Seminars',
    'Conferences',
    'Trade Shows',
    'Workshops',
    'Reunions',
    'Parties',
    'Galas'
];

//Render event chips
(function eventChipsRender() {
    const eventChips = document.querySelector('.chip-holder');
    eventTags.forEach(eventTag => {
        const chipHtml = `
                          <div class="chip">
                    <a href="#">${eventTag}</a>
                    <i class="close material-icons">close</i>
                </div>`
        eventChips.insertAdjacentHTML('afterend', chipHtml)
    });
})();

//Render event cards
(function cardRender() {
    fetch('http://localhost:8080/openevents/event')
        .then(response => response.json())
        .then(events => {
            events.forEach(event => {
                const eventTitle = event.title;
                const eventContent = event.eventDescription;
                const fetchedEventTags = event.eventTags.split('-');
                //console.log(fetchedEventTags);
                //const tags = fetchedEventTags.forEach(tag => { return tag });
               // console.log(tags);
                //fetchedEventTags.forEach(tag =>{})
                const card = `
                    <div class="card event-link-card-container">
                            <div class="card-content white-text">
                                <div class="card__date"><span class="card__date__day">23</span><span class="card__date__month">May</span>
                                </div>
                                <div class="card__meta"><a href="#"><i class="small material-icons">room</i>Nairobi</a>
                                </div><span class="card-title grey-text text-darken-4">${eventTitle}</span>
                                <div class="card-image">
                                    <img src="./assets/seyedeh-hamideh-kazemi-PFUqfNsorJM-unsplash.jpg">
                                </div>
                                <p class="card-subtitle grey-text text-darken-2">${eventContent}</p>
                                    <span class="text-darken-2 card-info card-info-tags"><i class="small material-icons">label</i>
                                    tag1 tag2 tag3
                                    </span>
                                 })}    
                            </div>
                            <div class="card-action"><a href="#"><i class="material-icons">info</i>More info</a><a href="#"
                                    class="card-action-right"><i class="material-icons">room</i>Locate</a>
                            </div>
                        </div>
                        `;
                const renderCards = document.querySelector('.events-below');
                renderCards.insertAdjacentHTML('afterbegin', card);
            })
        })
        .catch((error) => console.error(error));
})();
