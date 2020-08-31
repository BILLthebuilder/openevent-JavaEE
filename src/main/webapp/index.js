// Materialize stuff
M.AutoInit();
const sidenav = document.querySelector('.sidenav');
const sideNavInstance = M.Sidenav.init(sidenav, {});

const carousel = document.querySelector('.carousel');
const carouselInstance = M.Carousel.init(carousel, {
    fullWidth: true,
    indicators: true,
    duration: 200
});

// Custom Javascript

//Render event chips
(function eventChipsRender() {
    const events = [
        'Seminars',
        'Conferences',
        'Trade Shows',
        'Workshops',
        'Reunions',
        'Parties',
        'Galas'
    ]
    const eventChips = document.querySelector('.chip-holder');
    events.forEach(event => {
        const chipHtml = `
                          <div class="chip">
                    <a href="#">${event}</a>
                    <i class="close material-icons">close</i>
                </div>`
        eventChips.insertAdjacentHTML('afterend', chipHtml)
    });
})();

//Render event cards
(function cardRender() {
    fetch('https://jsonplaceholder.typicode.com/posts?_start=0&_limit=10')
        .then(data => data.json())
        .then(posts => {
            posts.forEach(post => {
                const eventTitle = post.title;
                const eventContent = post.body;
                const card = `<div class="row">
                    <div class="col s12 cards-container">
                        <div class="card">
                            <div class="card-image">
                                <img src="./assets/seyedeh-hamideh-kazemi-PFUqfNsorJM-unsplash.jpg">
                                <span class="card-title">${eventTitle}</span>
                                <a class="btn-floating halfway-fab waves-effect waves-teal"><i class="material-icons">add</i></a>
                            </div>
                            <div class="card-content">
                                <p>${eventContent}</p>
                            </div>
                            <div class="card-action">
                                <a href="#">This is a link</a>
                            </div>
                        </div>
                    </div>
                </div>`;
            const renderCards = document.querySelector('.events-below');
            renderCards.insertAdjacentHTML('afterend', card);
            })
        });
})();
