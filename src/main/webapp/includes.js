fetch('./include/footer.html')
    .then(response => {
        return response.text()
    })
    .then(footer => {
        document.querySelector("footer").innerHTML = footer;
    });

fetch('./include/styles.html')
    .then(response => {
        return response.text()
    })
    .then(styles => {
        document.querySelector("title").insertAdjacentHTML('afterend', styles)
    });
