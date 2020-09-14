//footer
fetch('./include/footer.html')
    .then(response => {
        return response.text()
    })
    .then(footer => {
        document.querySelector("footer").innerHTML = footer;
    });

//css styles
fetch('./include/styles.html')
    .then(response => {
        return response.text()
    })
    .then(styles => {
        document.querySelector("title").insertAdjacentHTML('afterend', styles);
    });

//js scripts
// fetch('./include/scripts.html')
//     .then(response => {
//         return response.text()
//     })
//     .then(scripts => {
//         document.querySelector(".includes").insertAdjacentHTML('afterend', scripts);
//     });
